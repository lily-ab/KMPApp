package core;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskGenerator {

    public Set<String> generate(File phrase) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(phrase));
        StringBuilder stringBuilder=new StringBuilder();
        String line;
        String ls=System.getProperty("line.separator");
        while((line = br.readLine())!=null){
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        String bestSentence = stringBuilder.toString();

        return GetUniqueWordPairs(bestSentence);
    }


    private Set<String> GetUniqueWordPairs(String str) {
        Set<String> sentences = new HashSet<>();
        List<String> strArray = Arrays.asList(str.split(" "));
        for (int i = 0; i < strArray.size(); i++) {
            StringBuilder sentence = new StringBuilder(strArray.get(i));
            sentences.add(sentence.toString());
            for (int j = i + 1; j < strArray.size(); j++) {
                sentence.append(" ").append(strArray.get(j));
                sentences.add(sentence.toString());
            }
        }
        return sentences;
    }
}
