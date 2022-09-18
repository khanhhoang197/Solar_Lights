package cg.hdk.slshop.views;

import cg.hdk.slshop.model.Role;
import cg.hdk.slshop.service.IUserService;
import cg.hdk.slshop.service.UserService;
import cg.hdk.slshop.utils.AppUtils;
import cg.hdk.slshop.utils.InputOption;

import java.util.Scanner;

public class AdminView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IUserService userService = UserService.getInstance();

    public static void loginUser(Role role) {
        int choice;
        do {
            try {
                System.out.println("            ▂▃▄▅▆▇█▇▆▅▄▃▂          ");
                System.out.println("▂▃▄▅▆▇████████████████████████▇▆▅▄▃▂");
                System.out.println("▐                                  ▐");
                System.out.println("▐            1. Đăng nhập          ▐");
                System.out.println("▐            2. Đăng ký            ▐");
                System.out.println("▐            0. Thoát              ▐");
                System.out.println("▐▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▐");
                System.out.println("Chọn chức năng: ");
                System.out.print("➥ ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        if (role == Role.ADMIN) {
                            adminLogin(role);
                        }
                        if (role == Role.USER) {
                            memberLogin(role);
                        }
                        break;
                    case 2:
                        UserView.createAddUser();
                        System.out.println("Chọn 1 để đăng nhập, 2 để thoát.");
                        String option = scanner.next();
                        switch (option) {
                            case "1":
                                if (role == Role.ADMIN) {
                                    adminLogin(role);
                                }
                                if (role == Role.USER) {
                                    memberLogin(role);
                                }
                                break;
                            case "2":
                                System.exit(0);
                                break;
                        }
                    case 0:
                        System.exit(0);
                        break;
                }
            } catch (Exception ex) {
                System.out.println("Nhập sai! Vui lòng  chọn lại");
                loginUser(role);
                return;
            }
        } while (true);
    }

    public static void loginAdmin(Role role) {
        System.out.println("            ▂▃▄▅▆▇█▇▆▅▄▃▂          ");
        System.out.println("▂▃▄▅▆▇████████████████████████▇▆▅▄▃▂");
        System.out.println("▐                                  ▐");
        System.out.println("▐            1. Đăng nhập          ▐");
        System.out.println("▐            0. Thoát              ▐");
        System.out.println("▐▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▐");
        System.out.println("Chọn chức năng: ");
        System.out.print("➥ ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                adminLogin(Role.ADMIN);
                break;
            case "0":
                System.out.println("Bye Bye!");
                System.exit(0);
                break;
            default:
                System.out.println("Không hợp lệ, đăng nhập lại");
                loginAdmin(Role.ADMIN);
                break;
        }
    }

    public static void adminLogin(Role role) {
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
                System.out.println("________________________________________________________");
                System.out.println("   |                       CHÀO ADMIN                     |");
                System.out.println("   ▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
                isRetry = false;
            }
        } while (isRetry);
        renderMenuManager();
    }

    public static void memberLogin(Role role) {
        boolean isRetry;
        System.out.println("▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰ ĐĂNG NHẬP HỆ THỐNG ▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰ ");
        do {
            System.out.print("Username: ");
            String username = AppUtils.retryString("Username");
            System.out.print("Password: ");
            String password = AppUtils.retryString("Password");
            if (userService.memberLogin(username, password) == null) {
                System.out.println("Tài khoản không hợp lệ!");
                isRetry = isRetry();
            } else {
                System.out.println("▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰ CHÀO MỪNG BẠN ĐÃ ĐẾN VỚI CỬA HÀNG SOLAR LIGHTS ▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰\n");
                isRetry = false;
            }
        } while (isRetry);
        MemberView.menuMember();
    }

    private static boolean isRetry() {
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

    public static void renderMenuManager() {
        int choice;
        do {
            try {
                System.out.println("╔═══════════════════════════════════════════════╗");
                System.out.println("║                    ► ADMIN ◄                  ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║             1. Quản lý người dùng             ║");
                System.out.println("║             2. Quản lý sản phẩm               ║");
                System.out.println("║             3. Thống kê doanh thu             ║");
                System.out.println("║             0. Thoát                          ║");
                System.out.println("╚═══════════════════════════════════════════════╝");
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

    public static void userManagement() {
        int choice;
        do {
            try {
                System.out.println("╔═══════════════════════════════════════════════╗");
                System.out.println("║               ► ADMIN -> USER ◄               ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║       1. Hiển thị danh sách người dùng        ║");
                System.out.println("║       2. Thêm người dùng                      ║");
                System.out.println("║       3. Sửa thông tin người dùng             ║");
                System.out.println("║       4. Xóa người dùng                       ║");
                System.out.println("║       5. Quay lại                             ║");
                System.out.println("║       0. Thoát                                ║");
                System.out.println("╚═══════════════════════════════════════════════╝");
                System.out.println("Chọn chức năng: ");
                System.out.print("➥ ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        UserView.showUsers();
                        break;
                    case 2:
                        UserView.addUser();
                        break;
                    case 3:
                        UserView.showUsers();
                        MemberView.adminEdit();
                        break;
                    case 4:
                        UserView.showUsers();
                        UserView.remove();
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

    public static void productManagement() {
        ProductsView productsView = new ProductsView();
        int choice;
        do {
            try {
                System.out.println("╔═══════════════════════════════════════════════╗");
                System.out.println("║              ► ADMIN -> PRODUCTS ◄            ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║       1. Hiển thị danh sản phẩm               ║");
                System.out.println("║       2. Thêm sản phẩm                        ║");
                System.out.println("║       3. Sửa sản phẩm                         ║");
                System.out.println("║       4. Xóa sản phẩm                         ║");
                System.out.println("║       5. Tìm kiếm sản phẩm                    ║");
                System.out.println("║       6. Quay lại                             ║");
                System.out.println("║       0. Thoát                                ║");
                System.out.println("╚═══════════════════════════════════════════════╝");
                System.out.println("Chọn chức năng: ");
                System.out.print("➥ ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        productsView.showProducts();
                        productManagement();
                        break;
                    case 2:
                        productsView.addProducts();
                        break;
                    case 3:
                        productsView.updateProducts();
                        break;
                    case 4:
                        productsView.removeProduct();
                        break;
                    case 5:
                        productsView.findProductsName();
                        break;
                    case 6:
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

    public static void turnover() {
        int choice;
        do {
            try {
                System.out.println("╔═══════════════════════════════════════════════╗");
                System.out.println("║              ► ADMIN -> TURNOVER ◄            ║");
                System.out.println("╠═══════════════════════════════════════════════╣");
                System.out.println("║            1. Doanh thu theo ngày             ║");
                System.out.println("║            2. Tất cả doanh thu                ║");
                System.out.println("║            3. Quay lại                        ║");
                System.out.println("║            0. Thoát                           ║");
                System.out.println("╚═══════════════════════════════════════════════╝");
                System.out.println("Chọn chức năng: ");
                System.out.print("➥ ");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        DayTurnOver.sortByOrderDay();
                        break;
                    case 2:
                        OrderHistoryView.renderOrderHistory();
                        break;
                    case 3:
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
}
