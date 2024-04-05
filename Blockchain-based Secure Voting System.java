import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

// Class to represent a block in the blockchain
class Block {
    private int index;
    private long timestamp;
    private ArrayList<String> transactions;
    private String previousHash;
    private String hash;

    public Block(int index, long timestamp, ArrayList<String> transactions, String previousHash) {
        this.index = index;
        this.timestamp = timestamp;
        this.transactions = transactions;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    // Function to calculate the hash of the block
    private String calculateHash() {
        String dataToHash = index + timestamp + transactions.toString() + previousHash;
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = sha.digest(dataToHash.getBytes());
            for (byte b : hashedBytes) {
                hash.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }
}

// Class to represent the blockchain
class Blockchain {
    private ArrayList<Block> chain;

    public Blockchain() {
        this.chain = new ArrayList<>();
        // Genesis block
        this.chain.add(new Block(0, System.currentTimeMillis(), new ArrayList<>(), "0"));
    }

    // Function to add a new block to the blockchain
    public void addBlock(Block newBlock) {
        newBlock.previousHash = chain.get(chain.size() - 1).getHash();
        newBlock.hash = newBlock.calculateHash();
        chain.add(newBlock);
    }

    // Function to validate the integrity of the blockchain
    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }
}

public class VotingSystem {
    public static void main(String[] args) {
        // Create a new blockchain
        Blockchain blockchain = new Blockchain();

        // Add some sample blocks
        ArrayList<String> transactions1 = new ArrayList<>();
        transactions1.add("Vote for Candidate A");
        blockchain.addBlock(new Block(1, System.currentTimeMillis(), transactions1, ""));

        ArrayList<String> transactions2 = new ArrayList<>();
        transactions2.add("Vote for Candidate B");
        blockchain.addBlock(new Block(2, System.currentTimeMillis(), transactions2, ""));

        // Print the blockchain
        for (Block block : blockchain.chain) {
            System.out.println("Block #" + block.getIndex());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println("Transactions: " + block.getTransactions());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Hash: " + block.getHash());
            System.out.println();
        }

        // Validate the blockchain
        System.out.println("Is blockchain valid? " + blockchain.isChainValid());
    }
}
