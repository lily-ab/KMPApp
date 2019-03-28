package work;

import java.io.*;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main {

    public static void main(String[] args){

        FileHandler fileHandler=new FileHandler();
        //"src/work/resources/task0.zip"
        fileHandler.unarchive(args[0]);
        File phraseFile = new File("src/work/resources/phrase.txt");
        File textFile = new File("src/work/resources/file2.txt");

        try {
            String s1 = readFile(textFile);
            String s2= readFile(phraseFile);
            int pos1 = work.KMPImplementer.kmpMatcher(s1,s2);
            int pos2 = s1.indexOf(s2);
            System.out.println(pos1+" "+pos2);
            if (pos1 != pos2)
            throw new RuntimeException();
            if (pos1 >= 0) {
                File file = new File("src/work/resources/results/file.txt");
                Files.copy(textFile.toPath(),file.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder=new StringBuilder();
        String line;
        while((line = br.readLine())!=null){
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
