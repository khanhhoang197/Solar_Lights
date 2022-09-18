package cg.hdk.slshop.views;

import cg.hdk.slshop.model.Role;
import cg.hdk.slshop.model.User;
import cg.hdk.slshop.service.IUserService;
import cg.hdk.slshop.service.UserService;
import cg.hdk.slshop.utils.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static cg.hdk.slshop.views.OrderItemView.parseCsvLine;

public class UserView {
    private static IUserService userService;

    public UserView() {
        userService = UserService.getInstance();
    }

    static final String PATH_USER = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\users.csv";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<User> findAll() {
        List<User> users = new ArrayList<>();
        List<String> lines = CSVUtils.read(PATH_USER);
        for (String line : lines) {
            users.add(User.parseUser(line));
        }
        return users;
    }

    public static void showUsers() {
        System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────────────── DANH SÁCH KHÁCH HÀNG ──────────────────────────────────────────────────────────────────────────────────────────────");
        System.out.printf("\t%-18s %-22s %-20s %-26s %-33s %-27s %-20s %-25s %-25s \n", "Id", "Username", "Họ và tên", "Số điện thoại", "Địa chỉ", "Email", "Quyền", "Ngày tạo", "Ngày cập nhật");
        System.out.println("═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(PATH_USER));
            while ((line = br.readLine()) != null) {
                printMenu(parseCsvLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
    }

    public static void printMenu(List<String> userList) {
        System.out.printf(" %-13s |  %-19s |   %-20s |   %-14s   |  %-25s  |    %-30s  | %10s | %30s |\n",
                userList.get(0), userList.get(1), userList.get(3),
                userList.get(4), userList.get(5), userList.get(6), userList.get(7), userList.get(8)

        );
    }

    public static long inputId(InputOption option) {
        long id;
        switch (option) {
            case UPDATE:
            case DELETE:
                System.out.print("Nhập id người dùng cần xóa: ");
                break;
        }
        System.out.print("➥ ");
        boolean flagInputId = true;
        do {
            id = AppUtils.retryParseLong();
            boolean exits = userService.existsUserId(id);
            switch (option) {
                case UPDATE:
                case DELETE:
                    if (!exits) {
                        System.out.println("Id không tồn tại, vui lòng nhập lại! ");
                        System.out.print("➥ ");
                    }
                    flagInputId = !exits;
                    break;
            }
        }
        while (flagInputId);
        return id;
    }

    public static void createAddUser() {
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                String username = inputUsername();
                String password = inputPassword();
                String fullName = inputFullName(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail();
                User user = new User(id, username.trim(), password, fullName.trim(), phone.trim(), address.trim(), email.trim(), Role.USER);
                userService.add(user);
                System.out.println("Tạo tài khoản thành công!");
                AdminView.loginUser(Role.USER);
            } catch (Exception e) {
                System.out.println("Sai định dạng.");
                e.printStackTrace();
            }
        } while (AppUtils.isRetry(InputOption.ADD));
    }

    public static void addUser() {
        do {
            try {
                long id = System.currentTimeMillis() / 1000;
                String username = inputUsername();
                String password = inputPassword();
                String fullName = inputFullName(InputOption.ADD);
                String phone = inputPhone(InputOption.ADD);
                String address = inputAddress(InputOption.ADD);
                String email = inputEmail();
                User user = new User(id, username.trim(), password, fullName.trim(), phone.trim(), address.trim(), email.trim(), Role.USER);
                setRole(user);
                userService.add(user);
                System.out.println("Tạo tài khoản thành công!");
            } catch (Exception e) {
                System.out.println("Sai định dạng, vui lòng nhập lại!");
            }
        } while (AppUtils.isRetry(InputOption.ADD));
    }

    public static void setRole(User user) {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║          ► SET ROLE ◄          ║");
        System.out.println("╠════════════════════════════════╣");
        System.out.println("║       1.     USER              ║");
        System.out.println("║       2.     ADMIN             ║");
        System.out.println("╚════════════════════════════════╝");
        System.out.println("Chọn Role: ");
        System.out.print("➥ ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option) {
            case 1:
                user.setRole(Role.USER);
                break;
            case 2:
                user.setRole(Role.ADMIN);
                break;
            default:
                System.out.println("Nhập không đúng! Vui lòng nhập lại");
                setRole(user);
        }
    }

    public static String inputUsername() {
        System.out.println("(Username không có ký tự đặc biệt)");
        System.out.print("Username: ");
        String username;
        do {
            if (!ValidateUtils.isUsernameValid(username = AppUtils.retryString("username"))) {
                System.out.println(" Username " + username + " không đúng đinh dạng, vui lòng nhập lại!");
                System.out.print("Username: ");
                continue;

            } else if (userService.existsByUsername(username)) {
                System.out.println("Username đã tồn tại! Vui lòng chọn Username khác!");
                System.out.print("Username: ");
                UserView.createAddUser();
                continue;
            }
            break;
        } while (true);
        return username;
    }

    public static String inputPassword() {
        System.out.println("Mật khẩu từ 8 ký tự trở lên và ");
        System.out.println("    + Không chứa ký tự ngắt dòng");
        System.out.println("    + Phải chứa ít nhất 1 ký tự số [0-9]");
        System.out.println("    + Phải chứa ít nhất 1 ký tự chữ hoa");
        System.out.println("    + Phải chứa ít nhất 1 ký tự chữ thường");
        System.out.println("    + Phải chứa ít nhất 1 ký tự đặc biệt [! @ # $ % ^ & + = _ ]]");
        System.out.print("Password: ");
        String password;
        while (!ValidateUtils.isPasswordValid(password = scanner.nextLine())) {
            if (password == null) {
                System.out.println("Mật khẩu không được để trống");
                System.out.print("Password: ");
                inputPassword();
                break;
            }
            System.out.println("Mật khẩu không đúng định dạng! Vui lòng nhập lại.");
            System.out.print("Password: ");
        }
        return password;
    }

    public static String inputFullName(InputOption option) {
        if (option == InputOption.ADD) {
            System.out.println("Nhập họ và tên (ví dụ: Hoàng Đức Khanh)");
            System.out.print("Họ và tên: ");
        }
        System.out.print("➥ ");
        String fullName;
        while (!ValidateUtils.isNameValid(fullName = scanner.nextLine())) {
            System.out.printf("Tên %s không đúng định dạng! vui lòng nhập lại", fullName);
            System.out.println("Nhập họ và tên (ví dụ: Nguyễn Văn A)");
            System.out.print("Họ và tên: ");
        }
        return fullName;
    }

    public static String inputPhone(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số điện thoại di động (ví dụ: 0987654321)");
                break;
            case UPDATE:
                System.out.println("Số điện thoại mới:  ");
                break;
        }
        System.out.print("➥ ");
        String phone;
        do {
            if (!ValidateUtils.isPhoneValid(phone = scanner.nextLine())) {
                System.out.printf("Số %s của bạn không đúng! vui lòng nhập lại.", phone);
                System.out.println("Nhập số điện thoại (ví dụ: 0987654321)");
                System.out.print("➥ ");
                continue;
            }
            break;
        } while (true);
        return phone;
    }

    public static String inputAddress(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập địa chỉ (ví dụ: Huế)");
                break;
            case UPDATE:
                System.out.println("Nhập địa chỉ muốn thay đổi");
                break;
        }
        System.out.print("➥ ");
        String address = scanner.nextLine();
        while (!ValidateUtils.isAddressValid(address)) {
            if (address == null) {
                System.out.println("Địa chỉ không được để trống");
                break;
            }
            System.out.println("Mật khẩu không đúng định dạng! Vui lòng nhập lại.");
            System.out.print("Password: ");
        }
        return address;
    }

    public static String inputEmail() {
        System.out.println("Nhập Email (ví dụ: nguyenvana@gmail.com)");
        System.out.print("➥ ");
        String email;
        do {
            if (!ValidateUtils.isEmailValid(email = scanner.nextLine())) {
                System.out.printf("Email %s không đúng định dạng! vui lòng nhập lại", email);
                System.out.println("Nhập Email (ví dụ: khanhhhoang197@gmail.com)");
                System.out.print("➥ ");
                continue;
            }
            if (userService.existsByEmail(email)) {
                System.out.printf("Email %s đã tồn tại! vui lòng nhập lại", email);
                System.out.println("Nhập Email (ví dụ: khanhhhoang197@gmail.com)");
                System.out.print("➥ ");
                continue;
            }
            while (email.trim().equals("")) {
                System.out.printf("Email %s không được để trống: ", email);
                email = scanner.nextLine();
            }
            break;
        } while (true);
        return email;
    }

    public static void remove() {
        boolean isTrue = true;
        do {
            try {
                UserService userService1 = new UserService();
                List<User> userList = userService1.findAll();
                System.out.print("Nhập ID User cần xóa: ");
                Long id = Long.parseLong(scanner.nextLine());
                for (User user : userList) {
                    Long temp = user.getIdUser();
                    if (temp.equals(id)) {
                        System.out.println();
                        System.out.println("╔════════════════════════════════╗");
                        System.out.println("║          ► Xóa User ◄          ║");
                        System.out.println("╠════════════════════════════════╣");
                        System.out.println("║       1.     Đồng ý            ║");
                        System.out.println("║       2.     Quay lại          ║");
                        System.out.println("╚════════════════════════════════╝");
                        System.out.println("Chọn chức năng: ");
                        System.out.print("=> ");
                        String choice = scanner.nextLine();
                        switch (choice) {
                            case "1":
                                userList.remove(user);
                                CSVUtils.write(PATH_USER, userList);
                                System.out.println("Xóa thành công.");
                                showUsers();
                                break;
                            case "2":
                                AdminView.userManagement();
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại.");
                                System.out.print("=> ");
                                isTrue = false;
                        }
                    }
                }
            } catch (Exception e) {
                e.getStackTrace();
            }
        } while (!isTrue);
    }

    public static void main(String[] args) {
        createAddUser();

    }
}
