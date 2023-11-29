package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 这个类用来通过读取文件来获取其中各个字符出现的频率
public class GetFrequency {

    // 空参构造方法
    public GetFrequency(){}

    // 用来获取字符出现的频率的方法
    //静态方法不用实例化即可调用
    public static Map<Character,Integer> getFrequency(String filePath) throws IOException {

        Map<Character,Integer> charFrequency = new HashMap<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            int curChar ;

            // 逐字符读取文件内容
            while((curChar = reader.read()) != -1){
                char character = (char) curChar;

                // 更新字符频率
                charFrequency.put(character,charFrequency.getOrDefault(character,0) + 1);
            }
        }

        return charFrequency;
    }

}