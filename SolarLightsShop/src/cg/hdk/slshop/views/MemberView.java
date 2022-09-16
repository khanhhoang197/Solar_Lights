package cg.hdk.slshop.views;

import cg.hdk.slshop.model.Role;
import cg.hdk.slshop.model.User;
import cg.hdk.slshop.service.UserService;
import cg.hdk.slshop.utils.AppUtils;
import cg.hdk.slshop.utils.CSVUtils;
import cg.hdk.slshop.utils.InputOption;
import cg.hdk.slshop.utils.InstantUtils;

import java.time.Instant;
import java.util.List;
import java.util.Scanner;

import static cg.hdk.slshop.views.UserView.*;

public class MemberView {
    static Scanner scanner = new Scanner(System.in);
    static final String PATH_USER = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\users.csv";
    static OrderView orderView = new OrderView();
    static OrderItemView orderItemView = new OrderItemView();
    static ProductsView productsView = new ProductsView();

    public static void menuMember() {
        do {
            try {
                System.out.println("▓░░░░░░░░░░░░░░░░░░░   MEMBER   ░░░░░░░░░░░░░░░░░░░▓");
                System.out.println("▓                                                  ▓");
                System.out.println("▓               1. Sửa thông tin cá nhân           ▓");
                System.out.println("▓               2. Xem menu sản phẩm               ▓");
                System.out.println("▓               3. Mua hàng                        ▓");
                System.out.println("▓               4. Log Out                         ▓");
                System.out.println("▓               5. Thoát                           ▓");
                System.out.println("▓                                                  ▓");
                System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
                System.out.println("Chọn chức năng: ");
                System.out.print("➥ ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        editMember();
                        break;
                    case 2:
                        productsView.showProducts();
                        break;
                    case 3:
                        orderView.addOrder();
                        orderItemView.addOrderItem();
                        orderView.showOrder();
                        OrderItemView.showOrderItem();
                        System.out.println("\t▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
                        System.out.printf("    |                                                                                      Tổng tiền:      %s       |\n", InstantUtils.doubleToVND(orderItemView.totalPrice()));
                        System.out.println("\t▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\n");
                        break;
                    case 4:
                        AdminView.loginUser(Role.USER);
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
        } while (true);
    }

    public static void editMember() {
        try {
            UserService userService1 = new UserService();
            List<User> userList = userService1.findAll();
            System.out.print("Nhập Username: ");
            String username = scanner.nextLine();
            System.out.print("\nNhập Password: ");
            String password = scanner.nextLine();
            for (User user : userList) {
                String tempPassword = user.getPassword();
                String tempUsername = user.getUsername();
                if (tempUsername.equals(username) && tempPassword.equals(password)) {
                    menuEdit();
                    int option = AppUtils.retryChoose(1, 5);
                    switch (option) {
                        case 1:
                            String phone = inputPhone(InputOption.UPDATE);
                            user.setPhoneNumber(phone);
                            CSVUtils.write(PATH_USER, userList);
                            System.out.println("Cập nhật số điện thoại thành công!\n");
                            menuMember();
                            break;
                        case 2:
                            String address = inputAddress(InputOption.UPDATE);
                            user.setAddress(address);
                            CSVUtils.write(PATH_USER, userList);
                            System.out.println("Cập nhật địa chỉ thành công! \n");
                            menuMember();
                            break;
                        case 3:
                            String email = inputEmail();
                            user.setEmail(email);
                            CSVUtils.write(PATH_USER, userList);
                            System.out.println("Cập nhật Email thành công!\n");
                            menuMember();
                            break;
                        case 4:
                            MemberView.menuMember();
                            break;
                        case 5:
                            System.exit(5);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Nhập sai rồi, vui lòng nhập lại!");
        }
    }

    public static void adminEdit() {
        try {
            UserService userService1 = new UserService();
            List<User> userList = userService1.findAll();
            System.out.print("Nhập ID User cần sửa ");
            Long id = Long.parseLong(scanner.nextLine());
            for (User user : userList) {
                Long temp = user.getIdUser();
                if (temp.equals(id)) {
                    menuEdit();
                    int option = AppUtils.retryChoose(1, 5);
                    switch (option) {
                        case 1:
                            String phone = inputPhone(InputOption.UPDATE);
                            user.setPhoneNumber(phone);
                            Instant timeUpdatePhone = Instant.now();
                            user.setTimeUpdate(timeUpdatePhone);
                            CSVUtils.write(PATH_USER, userList);
                            System.out.println("Cập nhật số điện thoại thành công!\n");
                            AdminView.userManagement();
                            break;
                        case 2:
                            String address = inputAddress(InputOption.UPDATE);
                            user.setAddress(address);
                            Instant timeUpdateAddress = Instant.now();
                            user.setTimeUpdate(timeUpdateAddress);
                            CSVUtils.write(PATH_USER, userList);
                            System.out.println("Cập nhật địa chỉ thành công! \n");
                            AdminView.userManagement();
                            break;
                        case 3:
                            String email = inputEmail();
                            user.setEmail(email);
                            Instant timeUpdateEmail = Instant.now();
                            user.setTimeUpdate(timeUpdateEmail);
                            CSVUtils.write(PATH_USER, userList);
                            System.out.println("Cập nhật Email thành công!\n");
                            AdminView.userManagement();
                            break;
                        case 4:

                            break;
                        case 5:
                            System.exit(5);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Nhập sai rồi, vui lòng nhập lại!");
        }
    }

    public static void menuEdit() {
        System.out.println("▓░░░░░░░░░░░░░░░░░░░    EDIT    ░░░░░░░░░░░░░░░░░░░▓");
        System.out.println("▓                                                  ▓");
        System.out.println("▓               1. Sửa số điện thoại               ▓");
        System.out.println("▓               2. Sửa địa chỉ                     ▓");
        System.out.println("▓               3. Sửa Email                       ▓");
        System.out.println("▓               4. Quay lại                        ▓");
        System.out.println("▓               5. Thoát                           ▓");
        System.out.println("▓                                                  ▓");
        System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
        System.out.println("Chọn chức năng: ");
        System.out.print("➥ ");
    }
}
