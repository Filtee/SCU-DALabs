package main;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class FileCoder {
    /****************************************************************
     *                        public methods                        *
     ****************************************************************/

    /**
     * 用Huffman树对文件进行编码的方法
     * 静态方法不用实例化即可调用
     *
     * @param sourcePath    源文件路径
     * @param targetPath    编码输出文件路径
     * @param frequencyPath 频度集路径
     */
    public static void encodeFile(String sourcePath, String targetPath, String frequencyPath) {
        // 从频度集文件中读取频度集 并存入Huffman树
        Map<Character, Integer> map = FrequencyHandler.readMapFromFile(frequencyPath);
        HuffmanTree huffmanTree = new HuffmanTree(map);

        // 调用helper方法进行编码, 写入给定文件
        try {
            encodeFile(sourcePath, targetPath, huffmanTree);
        } catch (IOException e) {
            System.out.println("编码失败，请检查原文件或频度集码字集文件是否存在");
        }

        // 计算压缩比
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);

        System.out.println("编码成功");
        System.out.println("原文件大小:" + sourceFile.length() + " B");
        System.out.println("编码文件大小:" + targetFile.length() + " B");

        // 查到压缩比是指压缩前/压缩后，压缩率是压缩后/压缩前，这里按照ppt要求算压缩比
        float compressRatio = (float) sourceFile.length() / targetFile.length();
        System.out.println("压缩比(压缩前文件大小/压缩后文件大小):" + String.format("%.3f", compressRatio));
    }

    /**
     * 用Huffman树对文件进行解码的方法
     * 静态方法不用实例化即可调用
     *
     * @param sourcePath    源文件路径
     * @param targetPath    解码输出文件路径
     * @param frequencyPath 频度集路径
     */
    public static void decodeFile(String sourcePath, String targetPath, String frequencyPath) {
        // 从频度集文件中读取频度集 并存入Huffman树
        Map<Character, Integer> map = FrequencyHandler.readMapFromFile(frequencyPath);
        HuffmanTree huffmanTree = new HuffmanTree(map);

        // 调用helper方法进行解码, 写入给定文件
        try {
            decodeFile(sourcePath, targetPath, huffmanTree);
            System.out.println("解码成功");
        } catch (IOException e) {
            System.out.println("解码失败，请检查编码文件或频度集码字集文件是否存在");
        }
    }

    /****************************************************************
     *                private methods and properties                *
     ****************************************************************/

    /**
     * Helper method for encodeFile(String sourcePath, String targetPath, String frequencyPath)
     *
     * @param sourcePath  源文件路径
     * @param targetPath  编码输出文件路径
     * @param huffmanTree Huffman树
     * @throws IOException 读写文件时可能抛出IOException
     */
    private static void encodeFile(String sourcePath, String targetPath, HuffmanTree huffmanTree)
            throws IOException {
        // 编码
        OutputStream os = new FileOutputStream(targetPath);
        BufferedReader reader = new BufferedReader(new FileReader(sourcePath));
        int curChar;
        byte[] encodeByte;

        // 逐字符读取文件内容
        while ((curChar = reader.read()) != -1) {
            char character = (char) curChar;
            encodeByte = huffmanTree.encode(character);
            os.write(encodeByte);
        }
        os.close();
        reader.close();
    }

    /**
     * Helper method for decodeFile(String sourcePath, String targetPath, String frequencyPath)
     *
     * @param sourcePath  源文件路径
     * @param targetPath  解码输出文件路径
     * @param huffmanTree Huffman树
     * @throws IOException 读写文件时可能抛出IOException
     */
    private static void decodeFile(String sourcePath, String targetPath, HuffmanTree huffmanTree)
            throws IOException {
        // 解码
        BufferedWriter writer = new BufferedWriter(new FileWriter(sourcePath));
        InputStream ris = new FileInputStream(targetPath);
        byte[] bytes = ris.readAllBytes();

        // 逐个字节读取编码文件并解码
        for (byte b : bytes) {
            String curStr = huffmanTree.decode(b);
            if (!curStr.isEmpty()) {
                writer.write(curStr);
            }
        }
        writer.close();
        ris.close();
    }
}
