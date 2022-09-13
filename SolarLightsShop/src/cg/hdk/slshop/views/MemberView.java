package cg.hdk.slshop.views;

import cg.hdk.slshop.utils.AppUtils;

import java.util.Scanner;

public class MemberView {
    public static void lauch() {
        Scanner scanner = new Scanner(System.in);
        UserView userView = new UserView();
        int option = -1;
        do {
            menuMember();
            try {
                do {
                    System.out.println("Chọn chức năng: ");
                    System.out.print("➥ ");
                    option = Integer.parseInt(scanner.nextLine());
                    if (option > 5 || option < 1)
                        System.out.println("Chọn chức năng không hợp lệ, vui lòng chọn lại");
                } while (option > 5 || option < 1);
                switch (option) {
                    case 1:
                        userView.updateUser();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        System.out.println("Tạm biệt! Hẹn gặp lại!");
                        System.exit(5);
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng, vui lòng nhập lại.");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Nhập sai, vui lòng nhập lại");
            }
        } while (option != 5);
    }

    public static void menuMember() {
        System.out.println("▓░░░░░░░░░░░░░░░░░░░   MEMBER   ░░░░░░░░░░░░░░░░░░░▓");
        System.out.println("▓                                                  ▓");
        System.out.println("▓               1. Sửa thông tin cá nhân           ▓");
        System.out.println("▓               2. Xem menu sản phẩm               ▓");
        System.out.println("▓               3. Mua hàng                        ▓");
        System.out.println("▓               4. Log Out                         ▓");
        System.out.println("▓               5. Thoát                           ▓");
        System.out.println("▓                                                  ▓");
        System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
    }
}
