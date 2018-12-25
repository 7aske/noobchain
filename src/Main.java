import blockchain.Block;
import blockchain.Chain;

import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Chain chain = new Chain();
		String user;
		do {
			user = getUserInput("Open existing chain ('y'/'n')? ");
		} while (!user.equals("y") && !user.equals("n"));
		if (user.equals("y")){
			chain = new Chain(Paths.get(System.getProperty("user.dir"),"chain.json"));
		} else {
			System.out.println("Started new blockchain!");
		}
		if (chain.isValid()){
			System.out.println(chain.toString());
			System.out.println("Chain is valid!");
		} else {
			System.out.println("Chain is not valid!");
			System.exit(-1);
		}
		for(;;){
			user = getUserInput("Add data ('n' to cancel):");
			if (!user.equals("n")) chain.addBlock(new Block(user, chain.getBlock().getHash()));
			else break;
		}
		do {
			user = getUserInput("Save ('y'/'n')?");
		} while (!user.equals("y") && !user.equals("n"));
		if (user.equals("y")) chain.saveToFile();
		System.out.println(chain.toString());

	}
	public static String getUserInput(String text){
		Scanner input = new Scanner(System.in);
		try {
			System.out.printf(text);
			return input.nextLine();
		} catch (Exception e) {
			return getUserInput(text);
		}
	}

}
