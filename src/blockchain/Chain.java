package blockchain;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chain {
	private ArrayList<Block> chain = new ArrayList<>();

	public Chain() {
		Block genesisBlock = new Block("Hello World!", "nulln");
		chain.add(genesisBlock);
	}
	public Chain(String s){
		Pattern pattern = Pattern.compile("\"(data|prevHash|timeStamp)\":\"([^\"]*)\"");
		Matcher matcher = pattern.matcher(s);
		// ArrayList<String> array = new ArrayList<>();
		BlockBuilder bBuilder = new BlockBuilder(this);
		while(matcher.find()){
			String key = matcher.group(1).replace("\"","");
			String value = matcher.group(2).replace("\"","");
			bBuilder.update(key, value);
		}


		//System.out.println(array.toString());
	}
	public Chain(ArrayList<Block> chain) {
		this.chain = chain;
	}
	public Chain(Path p){
		this(openFromFile(p));
	}
	//for testing validity
	public void setBlockData(int i, String hash) {
		chain.get(i).setData(hash);
	}

	public Block getBlock() {
		return chain.get(chain.size() - 1);
	}

	public boolean isValid() {
		for (int i = 1; i < chain.size(); i++) {
			Block currentBlock = chain.get(i);
			Block prevBlock = chain.get(i - 1);
			if (!currentBlock.calcHash().equals(currentBlock.getHash())) return false;
			if (!new Block(currentBlock.getData(), prevBlock.getHash(), currentBlock.getTimeStamp()).getHash().equals(currentBlock.getHash())) return false;
		}
		return true;

	}

	public void addBlock(String data) {
		Block block = new Block(data, getBlock().getHash());
		chain.add(block);
	}
	public void addBlock(Block b){
		this.chain.add(b);
	}
	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < chain.size(); i++) {
			if (i == chain.size() - 1) {
				out += chain.get(i).toString();

			} else {
				out += chain.get(i).toString() + ",\n";

			}
		}
		return String.format("[%s]", out);
	}
	public static String openFromFile(){
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(System.getProperty("user.dir"), "chain.json"));
			return new String(encoded, StandardCharsets.UTF_8);

		} catch (Exception e ){
			return "";
		}
	}
	public static String openFromFile(Path p){
		try {
			byte[] encoded = Files.readAllBytes(p);
			return new String(encoded, StandardCharsets.UTF_8);

		} catch (Exception e ){
			e.printStackTrace();

			return "";
		}
	}
	public void saveToFile() {
		try {
			Files.write(Paths.get(System.getProperty("user.dir"), "chain.json"), chain.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
