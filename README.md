### Blockchain-based Secure Voting System

This Java program implements a simple Blockchain-based Secure Voting System. Here's a brief overview of the implementation:

### Classes:

1. **Block**: Represents each block in the blockchain. It contains fields such as index, timestamp, list of transactions, previous hash, and current hash. The `calculateHash()` method is used to compute the hash of the block.
2. **Transaction**: Represents a voting transaction. It contains fields such as voter ID, candidate ID, and timestamp.
3. **Blockchain**: Manages the blockchain. It contains methods for adding a new block, verifying the integrity of the blockchain, and validating new transactions.
4. **VotingSystem**: Main class that simulates the voting process. It allows voters to cast their votes, validates the votes, and adds them to the blockchain.

### Features:

- Uses a blockchain to store voting transactions securely.
- Each block contains a list of transactions, making it tamper-proof.
- Implements basic validation to ensure the integrity of the blockchain.
- Simulates the process of casting and recording votes securely.

### Usage:

To run the program, simply execute the `VotingSystem` class. It will prompt users to cast their votes and display the results at the end.
