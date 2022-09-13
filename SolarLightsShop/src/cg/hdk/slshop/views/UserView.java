package cg.hdk.slshop.views;

import cg.hdk.slshop.model.Role;
import cg.hdk.slshop.model.User;
import cg.hdk.slshop.service.IUserService;
import cg.hdk.slshop.service.UserService;
import cg.hdk.slshop.utils.AppUtils;
import cg.hdk.slshop.utils.InputOption;
import cg.hdk.slshop.utils.InstantUtils;
import cg.hdk.slshop.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    public final String PATH = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\users.csv";
    private static IUserService userService;

    public UserView() {
        userService = UserService.getInstance();
    }

    public List<User> products;
    private static final Scanner scanner = new Scanner(System.in);

    public static void updateUser() {
        boolean isRetry = false;
        do {
            try {
                showUsers(InputOption.UPDATE);
                long id = inputId(InputOption.UPDATE);
                System.out.println("■■■■■■■■■■■■■■■■ UPDATE■■■■■■■■■■■■■■■■");
                System.out.println("■                                     ■");
                System.out.println("■       1. Sửa/đổi số điện thoại      ■");
                System.out.println("■       2. Sửa/đổi địa chỉ            ■");
                System.out.println("■       3. Sửa/đổi Email              ■");
                System.out.println("■       4. Quay lại                   ■");
                System.out.println("■       5. Thoát                      ■");
                System.out.println("■                                     ■");
                System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
                int option = AppUtils.retryChoose(1, 5);
                User newMember = new User();
                newMember.setIdUser(id);
                switch (option) {
                    case 1:
                        String phone = inputPhone(InputOption.UPDATE);
                        newMember.setPhoneNumber(phone);
                        userService.update(newMember);
                        System.out.println("Cập nhật số điện thoại thành công!");
                        break;
                    case 2:
                        String address = inputAddress(InputOption.UPDATE);
                        newMember.setAddress(address);
                        userService.update(newMember);
                        System.out.println("Cập nhật địa chỉ thành công! ");
                        break;
                    case 3:
                        String email = inputEmail();
                        newMember.setEmail(email);
                        userService.update(newMember);
                        System.out.println("Cập nhật Email thành công!");
                        break;
                    case 4:
                        MemberView.lauch();
                        break;
                    case 5:
                        System.exit(5);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Nhập sai rồi, vui lòng nhập lại!");
            }
        } while (isRetry);
    }

    public static void showUsers(InputOption inputOption) {
        System.out.println("────────────────────────────────────────────────────────────────────────────────────── DANH SACH NGUOI DUNG ────────────────────────────────────────────────────────────────────────");
        System.out.printf("\t%-18s %-17s %-31s %-27s %-20s %-18s %-22s %-13s \n", "Id", "Họ và tên", "Số điện thoại", "Email", "Địa chỉ", "Quyền", "Ngày tạo", "Ngày cập nhật");
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.printf(" %-13d |  %-19s |   %-15s |   %-28s   |  %-20s  |    %-8s  | %20s | %20s |\n",
                    user.getIdUser(),
                    user.getFullName(),
                    user.getPhoneNumber(),
                    user.getEmail(),
                    user.getAddress(),
                    user.getRole(),
                    InstantUtils.instantToString(user.getTimeCreate()),
                    user.getTimeUpdate() == null ? "" : InstantUtils.instantToString(user.getTimeUpdate())
            );
        }
        System.out.println("════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════\n");
        if (inputOption == InputOption.SHOW)
            AppUtils.isRetry(InputOption.SHOW);
    }

    static long inputId(InputOption option) {
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

//    public static void login() {
//        User user;
//        do {
//            try {
//                System.out.println("▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅ ĐĂNG NHẬP ▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅▅");
//                System.out.print("Username: \n");
//                String username = scanner.nextLine();
//                System.out.print("Password: ");
//                String password = scanner.nextLine();
//                user = userService.memberLogin(username, password);
//
//            }
//        }
//    }

    public static void addUser() {
        do {
            try {
                Long id = System.currentTimeMillis() / 1000;
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
        System.out.println("= = SET ROLE = =");
        System.out.println("∥   1. USER    ∥");
        System.out.println("∥   2. ADMIN   ∥");
        System.out.println("= = = =  = = = = ");
        System.out.println("Chọn Role: ");
        System.out.print(" ⭆ ");
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
    public static String inputUsername(){
        System.out.println("(Username in hoa ký tự đầu và không có ký tự đặc biệt)");
        System.out.print("Username: ");
        String username;
        do {
            if (!ValidateUtils.isUsernameValid(username = AppUtils.retryString("username"))){
                System.out.println( " Username" + username +" không đúng đinh dạng, vui lòng nhập lại!");
                System.out.print("Username: ");
                continue;
            }
            if (userService.existsByUsername(username)){
                System.out.println("Username đã tồn tại! Vui lòng chọn Username khác!");
                System.out.print("Username: ");
                continue;
            }
            break;
        }while (true);
        return username;
    }
    public static String inputPassword(){
        System.out.println("Mật khẩu từ 8 ký tự trở lên!");
        System.out.print("Password: ");
        String password;
        while (!ValidateUtils.isPasswordValid(password = scanner.nextLine())){
            if (password.equals("")){
                System.out.println("Mật khẩu không được để trống");
                continue;
            }
            System.out.println("Mật khẩu yếu! Vui lòng nhập lại.");
            System.out.print("Password: ");
        }
        return password;
    }
    public static String inputFullName(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Nhập họ và tên (ví dụ: Hoàng Đức Khanh)");
                System.out.print("Họ và tên: ");
        }
        System.out.print("➥ ");
        String fullName;
        while (!ValidateUtils.isNameValid(fullName = scanner.nextLine())){
            System.out.printf("Tên %s không đúng định dạng! vui lòng nhập lại");
            System.out.println("Nhập họ và tên (ví dụ: Hoàng Đức Khanh)");
            System.out.print("Họ và tên: ");
        }
        return fullName;
    }
    public static String inputPhone(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Nhập số điện thoại (ví dụ: 0964692858)");
                break;
            case UPDATE:
                System.out.print("Số điện thoại mới: ");
                break;
        }
        System.out.print("➥ ");
        String phone;
        do {
            if (!ValidateUtils.isPhoneValid(phone = scanner.nextLine())){
                System.out.printf("Số %d của bạn không đúng! vui lòng nhập lại.", phone);
                System.out.println("Nhập số điện thoại (ví dụ: 0964692858)");
                System.out.print("➥ ");
                continue;
            }
            if (userService.existByPhone(phone)){
                System.out.println("Số điện thoại đã được đăng ký! Vui lòng kiểm tra  và nhập lại");
                System.out.print("➥ ");
                continue;
            }
            break;
        }while (true);
        return phone;
    }
    public static String inputAddress(InputOption option){
        switch (option){
            case ADD:
                System.out.println("Nhập địa chỉ (ví dụ: Huế)");
                break;
            case UPDATE:
                System.out.println("Nhập địa chỉ muốn thay đổi");
                break;
        }
        System.out.print("➥ ");
        String address = scanner.nextLine();
        while (address.trim().equals("")){
            System.out.println("Địa chỉ không được để trống: ");
            address = scanner.nextLine();
        }
        return address;
    }
    public static String inputEmail(){
        System.out.println("Nhập Email (ví dụ: khanhhhoang197@gmail.com)");
        System.out.print("➥ ");
        String email;
        do {
            if (!ValidateUtils.isEmailValid(email = scanner.nextLine())){
                System.out.printf("Email %s không đúng định dạng! vui lòng nhập lại");
                System.out.println("Nhập Email (ví dụ: khanhhhoang197@gmail.com)");
                System.out.print("➥ ");
                continue;
            }
            if (userService.existsByEmail(email)){
                System.out.printf("Email %s đã tồn tại! vui lòng nhập lại");
                System.out.println("Nhập Email (ví dụ: khanhhhoang197@gmail.com)");
                System.out.print("➥ ");
                continue;
            }
            while (email.trim().equals("")){
                System.out.printf("Email không được để trống: ");
                email = scanner.nextLine();
            }
            break;
        }while (true);
        return email;
    }
    public void remove(){
        AdminView adminView = new AdminView();
        try {
            boolean flag = true;
            showUsers(InputOption.SHOW);
            Long id = inputId(InputOption.DELETE);
            System.out.println();
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║          ► Xóa User ◄          ║");
            System.out.println("╠════════════════════════════════╣");
            System.out.println("║       1.     Đồng ý            ║");
            System.out.println("║       2.     Quay lại          ║");
            System.out.println("╚════════════════════════════════╝");
            System.out.println("Chọn chức năng: ");
            System.out.print("=> ");
            do {
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        userService.revome(id);
                        System.out.println("Người dùng đã được xóa.");
                        showUsers(InputOption.SHOW);
                        break;
                    case "2":
                        adminView.renderMenuManager();
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại.");
                        System.out.print("=> ");
                        flag = false;
                }
            } while (!flag);
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    public static void main(String[] args) {
        UserView userView = new UserView();
        userView.showUsers(InputOption.SHOW);
    }
}
