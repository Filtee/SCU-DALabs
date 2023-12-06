package main;

import java.util.*;

public class HuffmanTree {
    /****************************************************************
     *                 public methods and properties                *
     ****************************************************************/

    public HuffmanTree() {
        this.codeTable = new HashMap<>();
    }

    /**
     * Stores the frequency table into the Huffman tree.
     * Key is the character, value is the frequency.
     *
     * @param frequencyTable the frequency table
     */
    public void Store(Map<Character, Integer> frequencyTable) {
        this.frequencyTable = frequencyTable;
        huffmanBuild();
    }

    /**
     * Encodes the input character.
     *
     * @param input the input character
     *              (must be one of the keys in the frequency table)
     * @return the encoded byte
     */
    public byte encode(char input) {
        String code = codeTable.get(input);
        byte output = 0;

        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                codeByte |= 1 << (7 - encodeCount);
            }

            if (encodeCount == 7) {
                output = codeByte;
                codeByte = 0;
                encodeCount = 0;
            } else {
                encodeCount++;
            }
        }
        return output;
    }

    /**
     * Decodes the input byte.
     *
     * @param input the input byte
     * @return the decoded character
     */
    public String decode(byte input) {
        String output = "";
        for (int i = 0; i < 8; i++) {
            if ((input & (1 << (7 - i))) != 0) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }

            if (curr.left == null && curr.right == null) {
                output += curr.character;
                curr = root;
            }
        }
        return output;
    }

    /**
     * Returns the frequency table.
     * Key is the character, value is the frequency.
     *
     * @return the frequency table
     */
    public Map<Character, String> getCodeTable() {
        return codeTable;
    }

    /****************************************************************
     *                private methods and properties                *
     ****************************************************************/
    class HuffmanNode {
        private final char character;
        private final int frequency;
        private final HuffmanNode left;
        private final HuffmanNode right;

        HuffmanNode(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
            this.left = null;
            this.right = null;
        }

        HuffmanNode(HuffmanNode left, HuffmanNode right) {
            this.character = '\0';
            this.frequency = left.frequency + right.frequency;
            this.left = left;
            this.right = right;
        }
    }

    private Map<Character, Integer> frequencyTable;
    private final Map<Character, String> codeTable;
    private HuffmanNode root;
    private HuffmanNode curr;

    private byte codeByte;
    private int encodeCount;


    private void huffmanBuild() {
        Comparator<HuffmanNode> comparator = new Comparator<>() {
            @Override
            public int compare(HuffmanNode o1, HuffmanNode o2) {
                return o1.frequency - o2.frequency;
            }
        };
        MinPQ<HuffmanNode> minPQ = new MinPQ<>(comparator);

        // Insert all the nodes into the priority queue.
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            HuffmanNode node = new HuffmanNode(entry.getKey(), entry.getValue());
            minPQ.insert(node);
        }

        // Build the Huffman tree.
        while (minPQ.size() > 1) {
            HuffmanNode left = minPQ.delMin();
            HuffmanNode right = minPQ.delMin();
            HuffmanNode node = new HuffmanNode(left, right);
            minPQ.insert(node);
        }

        // Traverse the Huffman tree to build the code table.
        root = minPQ.delMin();
        curr = root;
        huffmanCode(root);
    }

    // Traverse the Huffman tree to build the code table.
    private void huffmanCode(HuffmanNode root) {
        huffmanCode(root, "");
    }

    // Helper function for huffmanCode(HuffmanNode root).
    private void huffmanCode(HuffmanNode root, String code) {
        // Base case.
        if (root.left == null && root.right == null) {
            codeTable.put(root.character, String.valueOf(code));
            return;
        }

        // Recursive case.
        huffmanCode(root.left, code + "0");
        huffmanCode(root.right, code + "1");
    }
}
