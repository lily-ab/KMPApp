import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.lang.System.out;

public class TaskSeparator {

    public static void main(String[] args) throws IOException {
        TaskSeparator t=new TaskSeparator();
       // t.writeSubTasks(new File("src/resources/file1.txt"),new File("src/resources/file2.txt"),"src/resources/text");
        SubTaskGenerator subTaskGenerator=new SubTaskGenerator();
        subTaskGenerator.generate();
    }

    public Set<File> makePhrases(File file) {
        Set<File> files = new HashSet<>();

        return files;
    }

    public void genarateSubTask(File phrase, File file, String subTaskName) throws IOException {
        if (file.length() / (1024 * 1024) <= 230) {
            makeArchive(phrase,file,subTaskName);
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
        long delta =0;
        while((delta =fileLen-i)>=len){
            k++;
            char[] charArray = new char[(int) len];
            bufferedReader.read(charArray, 0, (int) len);

            i+=len;
            writeFiles(subTaskName,k,charArray,phrase);
        }
        if(delta>0){
            char[] charArray = new char[(int) delta];
            bufferedReader.read(charArray, 0, (int) delta);
            writeFiles(subTaskName,k,charArray,phrase);
        }
    }

    private void writeFiles(String subTaskName, int k, char[] charArray, File phrase) throws IOException {
        File taskFile = new File(subTaskName+".txt");
        FileWriter fileWriter = new FileWriter(taskFile);
        fileWriter.write(charArray);
        fileWriter.flush();
        fileWriter.close();
        makeArchive(phrase,taskFile,"src/resources/task"+k);
        taskFile.delete();
    }

    public void makeArchive(File file1, File file2,String zipName) throws IOException {

        FileOutputStream fout = new FileOutputStream(zipName+".zip");
        ZipOutputStream zout = new ZipOutputStream(fout);

        ZipEntry ze = new ZipEntry(file1.getName());
        ZipEntry ze2 = new ZipEntry(file2.getName());

        zout.putNextEntry(ze);
        write(new FileInputStream(file1), zout,file1.length());

        zout.putNextEntry(ze2);
        write(new FileInputStream(file2), zout,file2.length());

        zout.closeEntry();
        zout.close();
    }
    private static void write(InputStream in, OutputStream out, long size) throws IOException {
        byte[] buffer = new byte[(int) size];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        in.close();
    }
}
