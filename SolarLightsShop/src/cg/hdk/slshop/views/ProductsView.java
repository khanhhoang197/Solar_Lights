package cg.hdk.slshop.views;

import cg.hdk.slshop.model.ProductsManager;
import cg.hdk.slshop.service.IProductService;
import cg.hdk.slshop.service.ProductsService;
import cg.hdk.slshop.utils.AppUtils;
import cg.hdk.slshop.utils.CSVUtils;
import cg.hdk.slshop.utils.InputOption;
import cg.hdk.slshop.utils.InstantUtils;

import java.util.*;

public class ProductsView {

    public final static String PATH = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\products.csv";
    static Scanner scanner = new Scanner(System.in);
    private final IProductService productService;
    public List<ProductsManager> productsManager;

    public ProductsView() {
        this.productService = new ProductsService();
    }

    public void showProducts() {
        System.out.println("\t▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ DANH SÁCH SẢN PHẨM ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.printf("\t│\t\t%-6s   ││           %-35s││       %-20s   ││   %-10s        │\n", "ID", "Tên sản phẩm", "Giá sản phẩm", "Số lượng");
        System.out.println("\tㅑ二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二=二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二ㅕ");
        List<ProductsManager> products = productService.findAllProducts();
        for (ProductsManager product : products) {
            System.out.printf("\t│\t%-10s   ││   %-40s   ││    %-20s      ││     %-13s   │\n",
                    product.getIdProduct(),
                    product.getName(),
                    InstantUtils.doubleToVND(product.getPrice()),
                    InstantUtils.quantityProducts(product.getQuantity())
            );
        }
        System.out.println("\t▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n");
    }

    public void addProducts() {
        do {
            try {
                List<ProductsManager> products = new ArrayList<>();
                Long id = System.currentTimeMillis() / 1000;
                String productName = inputName(InputOption.ADD);
                double Price = inputPrice(InputOption.ADD);
                int productQuantity = inputQuantity(InputOption.ADD);
                String newProductName = scanner.nextLine();
                for (ProductsManager product : products) {
                    if (newProductName.equals(product.getName())) {
                        updateProducts();
                    }
                }
                ProductsManager newProducts = new ProductsManager(id, productName, productQuantity, Price);
                productService.addProducts(newProducts);
                System.out.println("\n Thêm sản phẩm thành công!");
            } catch (Exception e) {
                System.out.println("Nhập sai! Vui lòng nhập lại.");
            }
        } while (AppUtils.isRetry(InputOption.ADD));
    }

    public void updateProducts() {
        showProducts();
        List<ProductsManager> productsManagers = productService.findAllProducts();
        Scanner input = new Scanner(System.in);
        System.out.println("Nhập y nếu muốn chỉnh sửa hoặc nhập b để quay lại menu chính: ");
        String choice = input.nextLine();
        switch (choice) {
            case "y":
                System.out.println("Nhập ID Muốn chỉnh sửa:  ");
                Long id = Long.parseLong(input.nextLine());
                int count = 0;
                for (ProductsManager productsManager : productsManagers) {
                    Long tamp = productsManager.getIdProduct();
                    if (tamp.equals(id)) {
                        System.out.println("Nhập tên sản phẩm: ");
                        String name = input.nextLine();
                        System.out.println("Nhập giá sản phẩm muốn chỉnh sửa: ");
                        double price = Double.parseDouble(input.nextLine());
                        System.out.println("Nhập số lượng muốn chỉnh sửa");
                        int quatity = Integer.parseInt(input.nextLine());
                        productsManager.setName(name);
                        productsManager.setPrice(price);
                        productsManager.setQuantity(quatity);
                        productsManager.setIdProduct(id);
                        count++;
                        CSVUtils.write(PATH, productsManagers);
                        showProducts();
                        break;
                    }
                }
                if (count == 0) {
                    System.out.println("ID không tồn tại vui lòng nhập lại!");
                    updateProducts();
                }
                break;
            case "b":
                break;
            default:
                System.out.println("Vui Lòng Nhập Lại!");
                updateProducts();
        }
    }

    public void removeProduct() {
        showProducts();
        int count = 0;
        System.out.println("Nhập y để tiếp tục hoặc nhập b để quay lại menu chính: ");
        String confirm = scanner.nextLine();
        switch (confirm) {
            case "y":
                checkRemoveProducts();
                break;
            case "b":
                AdminView.productManagement();
                break;
            default:
                System.out.println("chọn không họp lệ");
                removeProduct();
                return;
        }
        if (count == 0) {
            System.out.println("ID không tồn tại! Bạn có muốn tiếp tục không?");
            removeProduct();
        }
    }

    public void checkRemoveProducts() {
        List<ProductsManager> productsManagers = productService.findAllProducts();
        try {
            System.out.println("Nhập ID muốn xóa:  ");
            Long id = Long.parseLong(scanner.nextLine());
            for (ProductsManager productsManager : productsManagers) {
                Long tamp = productsManager.getIdProduct();
                if (tamp.equals(id)) {
                    System.out.println("╔════════════════════════════════╗");
                    System.out.println("║            ►  Xóa  ◄           ║");
                    System.out.println("╠════════════════════════════════╣");
                    System.out.println("║       1.     Đồng ý            ║");
                    System.out.println("║       2.     Quay lại          ║");
                    System.out.println("╚════════════════════════════════╝");
                    System.out.print("➥ ");
                    String option = scanner.nextLine();
                    switch (option) {
                        case "1":
                            productsManagers.remove(productsManager);
                            CSVUtils.write(PATH, productsManagers);
                            System.out.println("Xóa thành công!");
                            removeProduct();
                            return;
                        case "2":
                            break;
                        default:
                            System.out.println("Nhập vào không hợp lệ! vui lòng nhập lại.");
                            removeProduct();
                            break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Nhập không hợp lệ.");
            e.printStackTrace();
        }
    }


    public void findProductsName() {
        List<ProductsManager> productsManagers = productService.findAllProducts();
        System.out.println("Nhập tên sản phẩm muốn tìm kiếm: ");
        String name = scanner.nextLine();
        int count = 0;
        System.out.println("\t▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ DANH SÁCH SẢN PHẨM ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.printf("\t│\t\t%-6s   ││           %-35s││       %-20s   ││   %-10s        │\n", "ID", "Tên sản phẩm", "Giá sản phẩm", "Số lượng");
        System.out.println("\t▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
        System.out.println("\tㅑ二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二二ㅕ");
        for (ProductsManager productsManager : productsManagers) {
            if (productsManager.getName().toUpperCase().contains(name.toUpperCase())) {
                System.out.printf("\t│\t%-10s   ││   %-40s   ││    %-20s      ││     %-13s   │\n",
                        productsManager.getIdProduct(), productsManager.getName(),
                        productsManager.getPrice(), productsManager.getQuantity());
                count++;
            }
        }
        System.out.println("\t▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n");
        if (count == 0) {
            System.out.println("Sản phẩm hiện tại đang hết hàng!!");
        }
    }

    private String inputName(InputOption option) {
        String name;
        switch (option) {
            case ADD:
                System.out.println("Nhập tên sản phẩm");
                break;
            case UPDATE:
                System.out.println("Nhập tên muốn thay đổi");
                break;
        }
        System.out.print("⭆ ");
        boolean flagInputName = true;
        do {
            name = scanner.nextLine().trim();
            boolean exits = (!name.isEmpty());
            switch (option) {
                case ADD:
                case UPDATE:
                    if (!exits) {
                        System.out.println("Bạn nhập sai định dạng, vui lòng nhập lại:");
                        System.out.print("=> ");
                    }
                    flagInputName = !exits;
                    break;
            }
        } while (flagInputName);
        return name;
    }

    private int inputQuantity(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập số lượng");
                break;
            case UPDATE:
                System.out.println("Nhập số lượng cần thay đổi");
                break;
        }
        int quantity;
        do {
            quantity = AppUtils.retryParseInt();
            if (quantity <= 0)
                System.out.println("Số lượng phải lớn hơn 0");
            System.out.println("Nhập số lượng: ");
        } while (quantity <= 0);
        return quantity;
    }

    private double inputPrice(InputOption option) {
        switch (option) {
            case ADD:
                System.out.println("Nhập giá sản phẩm");
                break;
            case UPDATE:
                System.out.println("Nhập giá của sản phẩm");
                break;
        }
        double price;
        do {
            price = AppUtils.retryParseDouble();
            if (price < 10000)
                System.out.println("Giá phải lớn hơn 10.000đ");
                System.out.print("Nhập giá sản phẩm: ");
        } while (price <10000);
        return price;
    }
}
