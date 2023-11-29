package main;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GetFrequencyTest {
    @Test
    void getFrequency() throws IOException {
        //这里的文件路径，记得改成你们自己的
        String filePath = "./test/java/resources/f1.txt";
        Map<Character,Integer> charFrequency = new HashMap<>();

        charFrequency = GetFrequency.getFrequency(filePath);

        Map<Character,Integer> expected = new HashMap<>();
        expected.put('A',7);
        expected.put('C',2);
        expected.put('S',4);
        expected.put('T',5);

        for(Map.Entry<Character,Integer> entry : expected.entrySet()){
            assertEquals(entry.getValue(),charFrequency.get(entry.getKey()));
        }
    }
}