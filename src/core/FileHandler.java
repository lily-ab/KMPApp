package core;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileHandler {

    public void writeFiles(String subTaskName, int k, char[] charArray, File phrase) throws IOException {
        File taskFile = new File(subTaskName+".txt");
        FileWriter fileWriter = new FileWriter(taskFile);
        fileWriter.write(charArray);
        fileWriter.flush();
        fileWriter.close();
        makeArchive(phrase,taskFile,"src/core/resources/task"+k);
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
        while ((len = in.read(buffer)) != -1)
            out.write(buffer, 0, len);
        in.close();
    }
}
