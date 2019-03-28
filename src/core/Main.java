package core;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {
//        Random rnd = new Random(1);
//        for (int step = 0; step < 10_000; step++) {
//            String s = getRandomString(rnd, 100);
//            String pattern = getRandomString(rnd, 5);
//            int pos1 = kmpMatcher(s, pattern);
//            int pos2 = s.indexOf(pattern);
//            System.out.println(pos1+" "+pos2);
//            if (pos1 != pos2)
//                throw new RuntimeException();
//        }

//        String s1 = "Мама мыла раму весной";
//        String s2 = "раму";
//        int pos1 = work.KMPImplementer.kmpMatcher(s1,s2);
//        int pos2 = s1.indexOf(s2);
//        System.out.println(pos1+" "+pos2);
//        if (pos1 != pos2)
//            throw new RuntimeException();
//        try {
//            File file1=new File("src/core.resources/file1.txt");
//            File file2=new File("src/core.resources/file2.txt");
//            System.out.println(file1.getName());
//          //  core.TaskSeparator.makeArchive(file1.getName(),file2.getName());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        TaskSeparator ts=new TaskSeparator();
        File file1=new File("src/core/resources/file1.txt");
        File file2=new File("src/core/resources/file2.txt");
        ts.separate(file1,file2,"task");
    }


}
