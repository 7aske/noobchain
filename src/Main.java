import blockchain.Chain;

public class Main {
	public static void main(String[] args) {
		Chain chain1 = new Chain();
		chain1.addBlock("Taske");
		chain1.addBlock("Sava");
		chain1.addBlock("Some data to be saved");
		//chain.setBlockData(1,"Sava gej");

		System.out.println(chain1.toString());
		Chain chain2 = new Chain("[{\"data\":\"Genesis Block\", \"prevHash\":\"0\",\"timeStamp\":\"1545761486598\"},\n" +
				"{\"data\":\"Taske\", \"prevHash\":\"120a0e8cb25f9b8f10f3b6c0b7e25bfd6c2c9aab430f50ba77c14b84982ab5b0\",\"timeStamp\":\"1545761486631\"},\n" +
				"{\"data\":\"Sava\", \"prevHash\":\"c94c73a1bde23077a34aab2f06711ecfa6ae6b95ae616c73c6208ea8d161bb30\",\"timeStamp\":\"1545761486631\"},\n" +
				"{\"data\":\"Some data to be saved\", \"prevHash\":\"373c11235670bdee4a73cf983e89003dd525fe579ad6176aa86a4a8aeaa343ff\",\"timeStamp\":\"1545761486632\"}]\n");
		chain1.setBlockData(1,"Some changed data");
		System.out.printf("%s\n", chain1.isValid());
		System.out.println(chain2.toString());
		System.out.println(chain2.isValid());
		chain1.saveToFile();
	}
}
