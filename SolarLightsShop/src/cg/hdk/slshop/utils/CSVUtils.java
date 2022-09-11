package cg.hdk.slshop.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static <T> void write(String path, List<T> items) {
        try {
            PrintWriter printWriter = new PrintWriter(path);
            for (T item : items){
                printWriter.println(item.toString());
            }
            printWriter.flush();
            printWriter.close();
        }
        catch (FileNotFoundException e){
            throw new IllegalArgumentException(path + " không có dữ liệu");
        }
    }
    public static List<String> read (String path){
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null && !line.trim().isEmpty())
                lines.add(line);
        }
        catch (IOException e){
            throw new IllegalArgumentException(path + " không có dữ liệu");
        }
        return lines;
    }
}
