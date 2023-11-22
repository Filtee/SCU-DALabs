package main;

import java.util.*;

public class HuffmanTree {
    /****************************************************************
     *                private methods and properties                *
     ****************************************************************/

    private Map<Character, Integer> frequencyTable;
    private Map<String, String> codeTable;
    private Map<String, String> decodeTable;

    private void huffmanTree() {
        MinPQ<String> minPQ = new MinPQ<>();
    }

    private void huffmanBuild() {

    }

    private void huffmanCode() {

    }

    /****************************************************************
     *                 public methods and properties                *
     ****************************************************************/

    public HuffmanTree() {
        this.codeTable = new HashMap<>();
        this.decodeTable = new HashMap<>();
    }

    /**
     * Stores the frequency table into the Huffman tree.
     * Key is the character, value is the frequency.
     *
     * @param frequencyTable the frequency table
     */
    public void Store(HashMap<Character, Integer> frequencyTable) {
        this.frequencyTable = frequencyTable;
    }

    /**
     * Returns the frequency table.
     * Key is the character, value is the frequency.
     *
     * @return the frequency table
     */
    public Map<String, String> getCodeTable() {
        return codeTable;
    }

    /**
     * Returns the decode table.
     * Key is the code, value is the character.
     *
     * @return the decode table
     */
    public Map<String, String> getDecodeTable() {
        return decodeTable;
    }
}
