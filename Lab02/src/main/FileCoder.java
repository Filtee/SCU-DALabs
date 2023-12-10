package main;

import java.io.*;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

public class FileCoder {

    //空参构造方法
    public FileCoder(){}

    public static void encodeFile(String filePath,String targetFilePath,String fFilePath,HuffmanTree huffmanTree)
            throws IOException {

        // 用Huffman树对文件进行编码的方法
        // 静态方法不用实例化即可调用
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        try {
            // 读取频度集
            BufferedReader mReader = new BufferedReader(new FileReader(fFilePath));
            String tempString;
            int linenum=0;
            while((tempString = mReader.readLine())!=null){
                StringTokenizer tokenizer =new StringTokenizer(tempString,"\\");
                if(linenum==0){
                    while(tokenizer.hasMoreElements()){
                        map.put(tokenizer.nextToken().charAt(0),Integer.parseInt(tokenizer.nextToken()));
                    }
                }else if(linenum==1){
                    char LF=10;
                    map.put(LF,Integer.parseInt(tokenizer.nextToken()));
                }else{
                    char CR=13;
                    map.put(CR,Integer.parseInt(tokenizer.nextToken()));
                    while(tokenizer.hasMoreElements()){
                        map.put(tokenizer.nextToken().charAt(0),Integer.parseInt(tokenizer.nextToken()));
                    }
                }
                linenum++;
            }
            mReader.close();
            huffmanTree.Store(map);

            // 编码
            OutputStream os = new FileOutputStream(targetFilePath);
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            int curChar;
            byte[] outputByte;
            // 逐字符读取文件内容
            while ((curChar=reader.read())!=-1) {
                char character = (char) curChar;
                outputByte=huffmanTree.encode(character);
                // 判断应写入多少个字节
                if(outputByte[0]==1){
                    os.write(outputByte[1]);
                }else if(outputByte[0]==2){
                    os.write(outputByte[1]);
                    os.write(outputByte[2]);
                }else{
                }
            }
            // 在编码文件末尾写入\0
            os.write(huffmanTree.encode('\0')[1]);
            os.close();
            reader.close();

        }catch (IOException e){
            System.out.print("IOException");
        }

    }
    public static void decodeFile(String outputFilePath,String targetFilePath,String fFilePath,HuffmanTree huffmanTree)
            throws IOException {

        // 用Huffman树对文件进行编码的方法
        // 静态方法不用实例化即可调用
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        try {
            // 读取频度集
            BufferedReader mReader = new BufferedReader(new FileReader(fFilePath));
            String tempString;
            int linenum=0;
            while((tempString = mReader.readLine())!=null){
                StringTokenizer tokenizer =new StringTokenizer(tempString,"\\");
                if(linenum==0){
                    while(tokenizer.hasMoreElements()){
                        map.put(tokenizer.nextToken().charAt(0),Integer.parseInt(tokenizer.nextToken()));
                    }
                }else if(linenum==1){
                    // 录入换行字符
                    char LF=10;
                    map.put(LF,Integer.parseInt(tokenizer.nextToken()));
                }else{
                    // 录入回车字符以及后续其它字符
                    char CR=13;
                    map.put(CR,Integer.parseInt(tokenizer.nextToken()));
                    while(tokenizer.hasMoreElements()){
                        map.put(tokenizer.nextToken().charAt(0),Integer.parseInt(tokenizer.nextToken()));
                    }
                }
                linenum++;
            }
            mReader.close();
            huffmanTree.Store(map);

            // 解码
            byte[] bytes;
            BufferedWriter writer=new BufferedWriter(new FileWriter(outputFilePath));
            InputStream ris = new FileInputStream(targetFilePath);
            bytes= ris.readAllBytes();

            // 逐个字节读取编码文件并解码
            for (byte aByte : bytes) {
                String curStr=huffmanTree.decode(aByte);
                if(!curStr.isEmpty()) {
                    writer.write(curStr);
                }
            }
            writer.close();
            ris.close();

        }catch (IOException e){
            System.out.print("IOException");
        }

    }

}


