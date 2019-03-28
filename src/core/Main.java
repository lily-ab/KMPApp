package core;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws IOException {

        TaskSeparator ts=new TaskSeparator();
        File file1=new File("src/core/resources/file1.txt");
        File file2=new File("src/core/resources/file2.txt");
        ts.separate(file1,file2,"task");
    }


}
