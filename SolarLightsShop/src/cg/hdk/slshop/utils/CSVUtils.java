package cg.hdk.slshop.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVUtils {
    static Scanner scanner = new Scanner(System.in);
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

    public static Integer inputQuantity() {
        int quantity = 0;
        do {
            System.out.println("Nhập số lượng vật phẩm (không được < 1):");
            quantity = Integer.parseInt(scanner.nextLine());
            if (quantity < 1) {
                System.out.println("Số lượng không đúng quy định, vui lòng nhập lại!!");
            }
        } while (quantity < 1);
        return quantity;
    }
}
