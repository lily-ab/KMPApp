package core;

import java.io.*;
import java.util.Set;

public class TaskSeparator {

    private FileHandler fileHandler= new FileHandler();

    public void separate(File phrase, File text,String subTaskName) throws IOException {

        Set<String> sequences = makePhrases(phrase);
        int k=0;
        for(String sequence: sequences){

            File file = new File("phrase.txt");
            FileWriter fw=new FileWriter(file);
            fw.write(sequence);
            fw.flush();
            fw.close();
            long i=file.length();
            generateSubTask(file,text,subTaskName+k++);
            file.delete();
        }
    }

    public Set<String> makePhrases(File file) throws IOException {

        return new TaskGenerator().generate(file);
    }

    public void generateSubTask(File phrase, File file, String subTaskName) throws IOException {
        if (file.length() / (1024 * 1024) <= 230) {
            fileHandler.makeArchive(phrase,file,subTaskName);
        }
        else{
            writeSubTasks(phrase,file,subTaskName);
        }
    }

    private void writeSubTasks(File phrase, File file,String subTaskName) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        int k=0;
        int i=0;
        long len=phrase.length()*2;
        long fileLen=file.length();
        long delta;

        while((delta =fileLen-i)>=len){

            char[] charArray = new char[(int) len];
            bufferedReader.read(charArray, 0, (int) len);

            i+=len;
            fileHandler.writeFiles(subTaskName,k++,charArray,phrase);
        }
        if(delta>0){
            char[] charArray = new char[(int) delta];
            bufferedReader.read(charArray, 0, (int) delta);
            fileHandler.writeFiles(subTaskName,k,charArray,phrase);
        }
    }


}
