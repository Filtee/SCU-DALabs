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
            File sourceFile=new File(filePath);
            File targetFile=new File(targetFilePath);

            float compressRatio;
            System.out.println("编码成功");
            System.out.println("原文件大小:"+sourceFile.length()+" B");
            System.out.println("编码文件大小:"+targetFile.length()+" B");
            // 查到压缩比是指压缩前/压缩后，压缩率是压缩后/压缩前，这里按照ppt要求算压缩比
            compressRatio= (float) sourceFile.length()/targetFile.length();
            System.out.println("压缩比(压缩前文件大小/压缩后文件大小):"+String.format("%.3f",compressRatio));
        }catch (IOException e){
            System.out.println("编码失败，请检查原文件或频度集码字集文件是否存在");
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
            System.out.println("解码成功");
        }catch (IOException e){
            System.out.println("解码失败，请检查编码文件或频度集码字集文件是否存在");
        }

    }

}


