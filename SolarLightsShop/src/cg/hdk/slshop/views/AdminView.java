package cg.hdk.slshop.views;

import cg.hdk.slshop.service.IUserService;
import cg.hdk.slshop.service.UserService;
import cg.hdk.slshop.utils.AppUtils;
import cg.hdk.slshop.utils.InputOption;

import java.util.Scanner;

public class AdminView {
    private final Scanner scanner = new Scanner(System.in);
    private final IUserService userService = UserService.getInstance();

    public void optionAdminView() {
        int choice;
        do {
            try {
                System.out.println("            ▂▃▄▅▆▇█▇▆▅▄▃▂          ");
                System.out.println("▂▃▄▅▆▇████████████████████████▇▆▅▄▃▂");
                System.out.println("▐                                  ▐");
                System.out.println("▐            1.Đăng nhập           ▐");
                System.out.println("▐            0.Thoát               ▐");
                System.out.println("▐▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▐");
                System.out.println("Chọn chức năng: ");
                System.out.print("➥ ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        adminLogin();
                        break;
                    case 2:
                        System.exit(0);
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng  chọn lại");
                ex.printStackTrace();
            }
        } while (true);
    }

    public void adminLogin() {
        boolean isRetry;
        System.out.println("▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰ ĐĂNG NHẬP HỆ THỐNG ▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰ ");
        do {
            System.out.print("Username: ");
            String username = AppUtils.retryString("Username");
            System.out.print("Password: ");
            String password = AppUtils.retryString("Password");
            if (userService.adminLogin(username, password) == null) {
                System.out.println("Tài khoản không hợp lệ!");
                isRetry = isRetry();
            } else {
                System.out.println("CHÀO ADMIN\n");
                isRetry = false;
            }
        } while (isRetry);
        renderMenuManager();
    }

    private boolean isRetry() {
        do {
            try {
                System.out.println("\t ████████████████████ CHON ██████████████████████");
                System.out.println("\t █                                              █");
                System.out.println("\t █           1. Đăng nhập lại                   █");
                System.out.println("\t █           2. Thoát chương trình              █");
                System.out.println("\t █                                              █");
                System.out.println("\t ████████████████████████████████████████████████");
                System.out.print("➥ ");
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        return true;
                    case 2:
                        System.out.println("\tTạm biệt. Hẹn gặp lại!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng, vui lòng chọn lại.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai, vui lòng nhập lại.");
                e.printStackTrace();
            }
        } while (true);
    }

    public void renderMenuManager() {
        int choice;
        do {
            try {
                System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
                System.out.println("▓                                                  ▓");
                System.out.println("▓               1. Quản lý người dùng              ▓");
                System.out.println("▓               2. Quản lý sản phẩm                ▓");
                System.out.println("▓               3. Thống kê doanh thu              ▓");
                System.out.println("▓               0. Thoát                           ▓");
                System.out.println("▓                                                  ▓");
                System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
                System.out.println("Chọn chức năng: ");
                System.out.print("➥ ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        userManagement();
                        break;
                    case 2:
                        productManagement();
                        break;
                    case 3:
                        turnover();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng, vui lòng chọn lại.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai, vui lòng nhập lại.");
                e.printStackTrace();
            }
        } while (true);

    }

    public void userManagement() {
        int choice;
        do {
            try {
                System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
                System.out.println("▓                                                  ▓");
                System.out.println("▓           1. Thêm người dùng                     ▓");
                System.out.println("▓           2. Sửa thông tin người dùng            ▓");
                System.out.println("▓           3. Xóa người dùng                      ▓");
                System.out.println("▓           4. Hiển thị danh sách người dùng       ▓");
                System.out.println("▓           5. Quay lại                            ▓");
                System.out.println("▓           6. Thoát                               ▓");
                System.out.println("▓                                                  ▓");
                System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
                System.out.println("Chọn chức năng: ");
                System.out.print("➥ ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        UserView.addUser();
                        break;
                    case 2:
                        UserView.updateUser();
                        break;
                    case 3:
                        UserView.inputId(InputOption.DELETE);
                        break;
                    case 4:
                        UserView.showUsers(InputOption.SHOW);
                        break;
                    case 5:
                        renderMenuManager();
                        break;
                    case 0:
                        System.exit(0);
                        System.out.println("Bye Bye! ");
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng, vui lòng chọn lại.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai, vui lòng nhập lại.");
                e.printStackTrace();
            }
        } while (true);

    }

    public void productManagement() {
        ProductsView productsView = new ProductsView();
        int choice;
        do {
            try {
                System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
                System.out.println("▓                                                  ▓");
                System.out.println("▓               1. Thêm sản phẩm                   ▓");
                System.out.println("▓               2. Sửa sản phẩm                    ▓");
                System.out.println("▓               3. Xóa sản phẩm                    ▓");
                System.out.println("▓               4. Tìm kiếm sảm phẩm               ▓");
                System.out.println("▓               5. Quay lại                        ▓");
                System.out.println("▓               0. Thoát                           ▓");
                System.out.println("▓                                                  ▓");
                System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
                System.out.println("Chọn chức năng: ");
                System.out.print("➥ ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productsView.addProducts();
                        break;
                    case 2:
                        productsView.updateProducts();
                        break;
                    case 3:
                        productsView.removeProduct();
                        break;
                    case 4:
                        break;
                    case 5:
                        renderMenuManager();
                        break;
                    case 0:
                        System.exit(0);
                        System.out.println("Bye Bye! ");
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng, vui lòng chọn lại.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai, vui lòng nhập lại.");
                e.printStackTrace();
            }
        } while (true);

    }

    public void turnover() {
        int choice;
        do {
            try {
                System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
                System.out.println("▓                                                  ▓");
                System.out.println("▓            1. Doanh thu trong ngày               ▓");
                System.out.println("▓            2. Doanh thu trong tháng              ▓");
                System.out.println("▓            3. Doanh thu trong quý(3 tháng)       ▓");
                System.out.println("▓            4. Quay lại                           ▓");
                System.out.println("▓            0. Thoát                              ▓");
                System.out.println("▓                                                  ▓");
                System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
                System.out.println("Chọn chức năng: ");
                System.out.print("➥ ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        renderMenuManager();
                        break;
                    case 0:
                        System.exit(0);
                        System.out.println("Bye Bye! ");
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng, vui lòng chọn lại.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai, vui lòng nhập lại.");
                e.printStackTrace();
            }
        } while (true);

    }

    public static void main(String[] args) {
        AdminView adminView = new AdminView();
        adminView.optionAdminView();
    }
}
