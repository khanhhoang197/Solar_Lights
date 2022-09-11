package cg.hdk.slshop.render;

public class RenderList {
    public void option(){
        System.out.println("            ▂▃▄▅▆▇█▇▆▅▄▃▂          ");
        System.out.println("▂▃▄▅▆▇████████████████████████▇▆▅▄▃▂");
        System.out.println("▐                                  ▐");
        System.out.println("▐            1.Đăng nhập           ▐");
        System.out.println("▐            0.Thoát               ▐");
        System.out.println("▐▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▂▐");
        System.out.println("Chọn chức năng: ");
        System.out.print("➥ ");
    }
    public void renderMenuManager(){

        System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
        System.out.println("▓                                                  ▓");
        System.out.println("▓               1. Quản lý người dùng              ▓");
        System.out.println("▓               2. Quản lý sản phẩm                ▓");
        System.out.println("▓               3. Thống kê doanh thu              ▓");
        System.out.println("▓               4. Thoát                           ▓");
        System.out.println("▓                                                  ▓");
        System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
        System.out.println("Chọn chức năng: ");
        System.out.print("➥ ");
    }
    public void userManagement(){
        System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
        System.out.println("▓                                                  ▓");
        System.out.println("▓               1. Thêm người dùng                 ▓");
        System.out.println("▓               2. Sửa người dùng                  ▓");
        System.out.println("▓               3. Xóa người dùng                  ▓");
        System.out.println("▓               4. Tìm kiếm người dùng             ▓");
        System.out.println("▓               5. Quay lại                        ▓");
        System.out.println("▓               0. Thoát                           ▓");
        System.out.println("▓                                                  ▓");
        System.out.println("▓░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░▓");
        System.out.println("Chọn chức năng: ");
        System.out.print("➥ ");
    }
    public void productManagement(){
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
    }
    public void turnover(){
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
    }

    public static void main(String[] args) {
        RenderList renderList = new RenderList();
        renderList.option();
        renderList.renderMenuManager();
    }
}
