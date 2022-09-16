package cg.hdk.slshop.utils;

import java.util.Scanner;

public class AppUtils {
    static Scanner scanner = new Scanner(System.in);

    public static int retryChoose(int min, int max) {
        int choose;
        do {
            try {
                choose = Integer.parseInt(scanner.nextLine());
                if (choose > max || choose < min) {
                    System.out.println("Chọn chức năng không hợp lệ, vui lòng chọn lại!");
                    System.out.print("➥ ");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Nhập không hợp lệ, vui lòng nhập lại! ");
                System.out.print("➥ ");
            }
        } while (true);
        return choose;
    }

    public static int retryParseInt() {
        int result;
        do {
            try {
                result = Integer.parseInt(scanner.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println("Nhập không hợp lệ, vui lòng nhập lại!");
                System.out.print("➥ ");
            }
        } while (true);
    }

    public static String retryString(String fieldName) {
        String result = scanner.nextLine();
        System.out.print("➥ ");
        while (result.isEmpty()) {
            System.out.printf("%s không được để trống! \n", fieldName);
            System.out.print("➥ ");
            break;

        }
        return result;
    }

    public static double retryParseDouble() {
        double result;
        do {
            try {
                result = Double.parseDouble(scanner.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println("Nhập không hợp lệ, vui lòng nhập lại (số). ");
                System.out.print("➥ ");
            }
        } while (true);
    }

    public static Long retryParseLong() {
        long result;
        do {
            try {
                result = Long.parseLong(scanner.nextLine());
                return result;
            } catch (Exception e) {
                System.out.println("Nhập không hợp lệ, Id là một chuỗi số.");
                System.out.print("➥ ");

            }
        }
        while (true);
    }
    public static boolean isRetry(InputOption inputOption) {
        do {
            switch (inputOption) {
                case ADD:
                    System.out.println("Nhập 'y' để tiếp tục thêm \t|\t 'q' để quay lại \t|\t 't' để thoát chương trình");
                    break;
                case UPDATE:
                    System.out.println("Nhập 'y' để tiếp tục sửa \t|\t 'q' để quay lại\t|\t 't' để thoát chương trình");
                    break;
                case DELETE:
                    System.out.println("Nhập 'q' để quay lại\t|\t 't' để thoát chương trình");
                    break;
                case SHOW:
                    System.out.println("Nhập 'y' để tiếp tục\t|\tNhập 'q' để quay lại \t|\t 't' để thoát chương trình");
                    break;
                default:
                    throw new IllegalStateException("giá trị không mong đợi " + inputOption);
            }
            System.out.print("➥ ");
            String option = scanner.nextLine();
            switch (option) {
                case "y":
                    return true;
                case "q":
                    return false;
                case "t":
                    System.out.println("Bye Bye");
                    System.exit(5);
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! vui lòng nhập lại");
                    break;
            }
        } while (true);

    }
}
