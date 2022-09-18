package cg.hdk.slshop.views;

import cg.hdk.slshop.model.ProductsManager;
import cg.hdk.slshop.service.*;
import cg.hdk.slshop.utils.AppUtils;
import cg.hdk.slshop.utils.InstantUtils;

import java.util.List;
import java.util.Scanner;

public class SortView {
    public static ProductsService productService = new ProductsService();
    static Scanner scanner = new Scanner(System.in);

    public static void menuSortByAdmin() {
        try {
            String choice;
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║                  ► Sắp Xếp Sản Phẩm ◄                  ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║       1.     Sắp xếp theo tên sản phẩm                 ║");
            System.out.println("║       2.     Sắp xếp theo giá sản phẩm                 ║");
            System.out.println("║       0.     Quay lại quản lý sản phẩm                 ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println("Chọn chức năng: ");
            System.out.print("➥ ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    sortName();
                    break;
                case "2":
                    sortPrice();
                    break;
                case "0":
                    AdminView.productManagement();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại.");
                    System.out.print("➥ ");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void menuSort() {
        try {
            String choice;
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║                  ► Sắp Xếp Sản Phẩm ◄                  ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║       1.     Sắp xếp theo tên sản phẩm                 ║");
            System.out.println("║       2.     Sắp xếp theo giá sản phẩm                 ║");
            System.out.println("║       0.     Quay lại                                  ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println("Chọn chức năng: ");
            System.out.print("➥ ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    sortName();
                    break;
                case "2":
                    sortPrice();
                    break;
                case "0":
                   MemberView.menuMember();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại.");
                    System.out.print("➥ ");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public static void sortName() {
        try {
            String choice;
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║                  ► Sắp Xếp Theo Tên ◄                  ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║       1.     Tên sản phẩm A -> Z                       ║");
            System.out.println("║       2.     Tên sản phẩm Z -> A                       ║");
            System.out.println("║       0.     Quay lại xắp xếp sản phẩm                 ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println("Chọn chức năng: ");
            System.out.print("➥ ");
            choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    sortNameDESC();
                    break;
                case "2":
                    sortNameASC();
                    break;
                case "0":
                    menuSort();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại.");
                    System.out.print("➥ ");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void sortPrice() {
        try {
            String choice;
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════╗");
            System.out.println("║                  ► Sắp Xếp Theo Giá ◄                  ║");
            System.out.println("╠════════════════════════════════════════════════════════╣");
            System.out.println("║       1.     Giá sản phẩm tăng dần                     ║");
            System.out.println("║       2.     Giảm sản phẩm giảm dần                    ║");
            System.out.println("║       0.     Quay lại xắp xếp sản phẩm                 ║");
            System.out.println("╚════════════════════════════════════════════════════════╝");
            System.out.println("Chọn chức năng: ");
            System.out.print("➥ ");
                choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        sortPriceASC();
                        break;
                    case "2":
                        sortPriceDESC();
                        break;
                    case "0":
                        menuSort();
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại.");
                        System.out.print("➥ ");
                }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void sortNameASC() {
        List<ProductsManager> products = productService.findAllProducts();
        SortByNameASC sortByNameASC = new SortByNameASC();
        products.sort(sortByNameASC);
        showSortList(products);
        sortName();
    }

    public static void sortNameDESC() {
        List<ProductsManager> products = productService.findAllProducts();
        SortByNameDESC sortByNameDESC = new SortByNameDESC();
        products.sort(sortByNameDESC);
        showSortList(products);
        sortName();
    }

    public static void sortPriceASC() {
        List<ProductsManager> products = productService.findAllProducts();
        SortByPriceASC sortByPriceASC = new SortByPriceASC();
        products.sort(sortByPriceASC);
        showSortList(products);
        sortPrice();
    }

    public static void sortPriceDESC() {
        List<ProductsManager> products = productService.findAllProducts();
        SortByPriceDESC sortByPriceDESC = new SortByPriceDESC();
        products.sort(sortByPriceDESC);
        showSortList(products);
        sortPrice();
    }

    public static void showSortList(List<ProductsManager> products) {
        System.out.println();
        System.out.println("══════════════════════════════════════ Danh Sách Sản Phẩm ═════════════════════════════════════════");
        System.out.printf("%-25s %-20s %-20s %-20s\n", "ID", "Tên Sản Phẩm", "Số lượng", "Giá");
        System.out.println("───────────────────────────────────────────────────────────────────────────────────────────────────");
        for (ProductsManager product : products) {
            System.out.printf("%-25s %-20s %-20s %-20s\n",
                    product.getIdProduct(),
                    product.getName(),
                    InstantUtils.quantityProducts(product.getQuantity()),
                    InstantUtils.doubleToVND(product.getPrice()));
        }
        System.out.println("═══════════════════════════════════════════════════════════════════════════════════════════════════");
        int choice;
        do {
            System.out.println("Nhấn 0 để quay lại sắp xếp sản phẩm.");
            System.out.print("➥ ");
            choice = AppUtils.retryParseInt();
        } while (choice != 0);
    }

    public static void main(String[] args) {
        menuSort();
    }
}
