package Blockchain;

import java.util.*;

public class block {
    private int indx;
    private long time;
    private String data;
    private String previousHash;
    private String hash;
    private int nonce;

    public block(String data,String previousHash ) {
        this.data = data;
        this.previousHash = previousHash;
        this.time = new Date().getTime();
        this.hash = calculateHash(); //Making sure we do this after we set the other values.
    }


    public String calculateHash() {

        String calculatedhash = algorithms.applySha256(
                previousHash +
                        Long.toString(time) +
                        data
        );
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public void setIndx(int indx) {
        this.indx = indx;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public int getIndx() {
        return indx;
    }

    public long getTime() {
        return time;
    }

    public String getData() {
        return data;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }
}
