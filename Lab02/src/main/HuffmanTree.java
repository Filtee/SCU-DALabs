package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HuffmanTree {
    /****************************************************************
     *                 public methods and properties                *
     ****************************************************************/

    public HuffmanTree() {
        this.codeTable = new HashMap<>();
    }

    public HuffmanTree(Map<Character, Integer> frequencyTable) {
        this.codeTable = new HashMap<>();
        Store(frequencyTable);
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
    public byte[] encode(char input) {
        byte[] outputBuffer = new byte[8];
        int bytecount = 0;

        // 遍历code的每一位
        String code = codeTable.get(input);
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '1') {
                // 如果该位置为1，则将codeByte的对应位设为1
                codeByte |= (byte) (1 << (7 - encodeCount));
            }

            // Check if the code byte is full.
            if (encodeCount == 7 && input != '\0') {
                // If the code byte is full, add the encoded byte into the buffer.
                outputBuffer[bytecount] = codeByte;
                bytecount++;
                // Reset the code byte.
                codeByte = 0;
                encodeCount = 0;
            } else if (encodeCount == 7) {
                // If the current node is the end of the file,
                // return the encoded byte.
                outputBuffer[bytecount] = codeByte;
                break;
            } else {
                // Move to the next bit.
                encodeCount++;
            }
        }

        byte[] output = new byte[bytecount];
        System.arraycopy(outputBuffer, 0, output, 0, bytecount);
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
        // Traverse the Huffman tree.
        for (int i = 0; i < 8; i++) {
            // 检查byte中每一位的值
            if ((input & (1 << (7 - i))) != 0) {
                // 如果值为1，则移动到当前结点的右子结点
                curr = curr.right;
            } else {
                // 如果值为0，移动到当前结点的左子结点
                curr = curr.left;
            }

            // If the current node is a leaf node, return the decoded character.
            assert curr != null;
            if (curr.left == null && curr.right == null) {
                // If the current node is the end of the file,
                // return the decoded string.
                if (curr.character == '\0') {
                    break;
                }

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

    //将码字集写入文件的静态方法
    public static void writeMapToFile(Map<Character, String> map, String fileName) {
        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(fileName))) {
            // 遍历Map的键值对，将其写入文件
            for (Map.Entry<Character, String> entry : map.entrySet()) {
                writer2.write(entry.getKey() + "\\" + entry.getValue() + "\\");
                // writer2.newLine();  // 换行
            }
            System.out.println("码字集写入文件成功！");
        } catch (IOException e) {
            System.err.println("写入文件时发生错误：" + e.getMessage());
        }
    }
}
