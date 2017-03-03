package SomeClass;

import java.io.*;

public class FileIO {
    public static void main(String[] args) {

        File file = new File("F:/Code/java/student.txt");
        try{
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        String str = "";
        byte[] bt = new byte[1024];
        bt = str.getBytes();
        //向文件写入内容
        try{
            FileOutputStream in = new FileOutputStream(file);
            try{
                in.write(bt, 0, bt.length);
                in.close();
                System.out.println("Input successfully");
            }catch (IOException e){
                e.printStackTrace();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        // 向文件读取内容
        try{
            FileInputStream out = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(out);
            int ch = 0;
            while ((ch = isr.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

