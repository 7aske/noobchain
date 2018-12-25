package blockchain;

import utils.StringUtil;

import java.util.Date;

public class Block {
	private String data;
	private String prevHash;
	private String hash;
	private long timeStamp;
	public Block(String data, String prevHash) {
		this.data = data;
		this.prevHash = prevHash;
		this.timeStamp = new Date().getTime();
		this.hash = calcHash();

	}
	public Block(String data, String prevHash, long timestamp) {
		this.data = data;
		this.prevHash = prevHash;
		this.timeStamp = timestamp;
		this.hash = calcHash();

	}
	public String calcHash(){
		try {
			return StringUtil.applySha256(prevHash + timeStamp + data);
		} catch (Exception e) {
			throw new Error("Error generating hash");
		}
	}

	public String getHash() {
		return hash;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return '{' +
				"\"data\":\"" + data + '"' +
				", \"prevHash\":\"" + prevHash + '\"' +
				",\"timeStamp\":\""+ timeStamp + "\""+
				'}';
	}
}
