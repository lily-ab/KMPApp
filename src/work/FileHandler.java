package work;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileHandler {

    public void writeFiles(String subTaskName, int k, char[] charArray, File phrase) throws IOException {
        File taskFile = new File("file.txt");
        FileWriter fileWriter = new FileWriter(taskFile);
        fileWriter.write(charArray);
        fileWriter.flush();
        fileWriter.close();
        makeArchive(phrase,taskFile,"src/core/resources/"+subTaskName+k);
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
    public static void write(InputStream in, OutputStream out, long size) throws IOException {
        byte[] buffer = new byte[(int) size];
        int len;
        while ((len = in.read(buffer)) != -1)
            out.write(buffer, 0, len);
        in.close();
    }

    public static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        out.close();
        in.close();
    }

    public void unarchive(String address){

        File file = new File(address);
        if (!file.exists() || !file.canRead()) {
            try {
                throw new FileNotFoundException("File cannot be read");
            } catch (FileNotFoundException e) {
                e.getMessage();
            }
        }

        try {
            ZipFile zip = new ZipFile(address);
            Enumeration entries = zip.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                System.out.println(entry.getName());

                if (entry.isDirectory()) {
                    new File(file.getParent(), entry.getName()).mkdirs();
                } else {
                    new FileHandler().write(zip.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(
                                    new File(file.getParent(), entry.getName()))));
                }
            }

            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
