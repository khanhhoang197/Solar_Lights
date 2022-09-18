package cg.hdk.slshop.views;

import cg.hdk.slshop.model.OrderHistory;
import cg.hdk.slshop.model.OrderItem;
import cg.hdk.slshop.model.ProductsManager;
import cg.hdk.slshop.utils.CSVUtils;
import cg.hdk.slshop.utils.InstantUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderItemView {
    public static final String PATH_ORDERITEM = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\OrderItem.csv";
    public final static String PATH_ORDER_HISTORY = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\salesHistory.csv";
    public final static String PATH_PRODUCT = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\products.csv";
    static Scanner scanner = new Scanner(System.in);
    OrderView orderView = new OrderView();
    List<OrderItem> orderItems;

    public OrderItemView() {
        this.orderItems = new ArrayList<>();
    }

    public static List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        List<String> lines = CSVUtils.read(PATH_ORDERITEM);
        for (String line : lines) {
            orderItems.add(OrderItem.parserOderItem(line));
        }
        return orderItems;
    }

    public static List<ProductsManager> findAllProducts() {
        List<ProductsManager> products = new ArrayList<>();
        List<String> records = CSVUtils.read(PATH_PRODUCT);
        for (String record : records) {
            products.add(ProductsManager.parseProduct(record));
        }
        return products;
    }

    public void addOrderItem() {
        List<OrderItem> orderItems = new ArrayList<>();
        List<ProductsManager> productsManagers = findAllProducts();
        ProductsView productsView = new ProductsView();
        productsView.showProducts();
        System.out.print("Nhập ID sản phẩm muốn mua: ");
        Long id = Long.parseLong(scanner.nextLine());
        double price = 0;
        double total = 0;
        String name = "";
        int count = 0;
        for (ProductsManager product : productsManagers) {
            Long temp = product.getIdProduct();
            if (temp.equals(id)) {
                price = product.getPrice();
                name = product.getName();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("ID Không tồn tại vui lòng nhập lại!!");
            addOrderItem();
            return;
        }
        System.out.println("Nhập số lượng sản phẩm muốn mua!");
        System.out.print("➥ ");
        int quantity = Integer.parseInt(scanner.nextLine());
        if (quantity <= 0) {
            System.out.println("Không hợp lệ, Vui lòng nhập lại.");
            addOrderItem();
            return;
        } else {
            int countQuantity = 0;
            for (ProductsManager product : productsManagers) {
                Long tempId = product.getIdProduct();
                int tempQuantity = product.getQuantity();
                if (tempId.equals(id) && tempQuantity >= quantity) {
                    total = product.getPrice() * quantity;
                    product.setQuantity(product.getQuantity() - quantity);
                    CSVUtils.write(PATH_PRODUCT, productsManagers);
                    countQuantity++;
                }
            }
            if (countQuantity == 0) {
                System.out.println("Số lượng không hợp lệ. Mời nhập lại!");
                addOrderItem();
                return;
            }
        }
        doubleSource(orderItems, id, price, total, name, quantity);
        showOrderItem();
        selectOderItem();
    }

    public static void updateOrderItem() {
        List<OrderItem> orderItems = findAll();
        List<ProductsManager> productsManagers = findAllProducts();
        ProductsView render = new ProductsView();
        render.showProducts();
        System.out.print("Nhập ID sản phẩm cần mua: ");
        Long id = Long.parseLong(scanner.nextLine());
        double price = 0;
        double total = 0;
        String name = "";
        int count = 0;
        for (OrderItem orderItem : orderItems) {
            Long tampSame = orderItem.getId();
            if (tampSame.equals(id)) {
                price = orderItem.getPrice();
                name = orderItem.getName();
                count++;
                int quantityUpdate = CSVUtils.inputQuantity();
                int countQuantity = 0;
                if (orderItem.getId().equals(id))
                    orderItem.setQuantity(orderItem.getQuantity() + quantityUpdate);
                total = orderItem.getPrice() * orderItem.getQuantity();
                for (ProductsManager product : productsManagers) {
                    int tampQuantity = product.getQuantity();
                    Long tempId = product.getIdProduct();
                    if (tempId.equals(id) && tampQuantity >= quantityUpdate) {
                        product.setQuantity(product.getQuantity() - quantityUpdate);
                        CSVUtils.write(PATH_PRODUCT, productsManagers);
                        countQuantity++;
                    }
                }
                if (countQuantity == 0) {
                    System.out.println("Số lượng không đủ vui lòng nhập lại!!");
                    updateOrderItem();
                    return;
                }
                orderItem.setQuantity(orderItem.getQuantity());
                orderItem.setName(name);
                orderItem.setPrice(price);
                orderItem.setTotal(total);
                CSVUtils.write(PATH_ORDERITEM, orderItems);
                showOrderItem();
                System.out.println("Order thành công!");
                return;
            }
        }
        for (ProductsManager product : productsManagers) {
            Long temp = product.getIdProduct();
            if (temp.equals(id)) {
                price = product.getPrice();
                name = product.getName();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("ID không tồn tại vui lòng nhập lại!!");
            updateOrderItem();
            return;
        }
        System.out.print("Nhập số lượng sản phẩm muốn mua: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        if (quantity <= 0) {
            System.out.println("Không hợp lệ, vui lòng nhập lại.");
            updateOrderItem();
        } else {
            int countQuantity = 0;
            for (ProductsManager product : productsManagers) {
                Long tempId = product.getIdProduct();
                int tempQuantity = product.getQuantity();
                if (tempId.equals(id) && tempQuantity >= quantity) {
                    total = product.getPrice() * quantity;
                    product.setQuantity(product.getQuantity() - quantity);
                    CSVUtils.write(PATH_ORDERITEM, productsManagers);
                    countQuantity++;
                }
            }

            if (countQuantity <= 0) {
                System.out.println("Số lượng không hợp lệ! Vui lòng nhập lại!!");
                updateOrderItem();
                return;
            }
        }
        doubleSource(orderItems, id, price, total, name, quantity);
    }
    public static void editOrderItem() {
        List<OrderItem> orderItems = findAll();
        List<ProductsManager> productsManagers = findAllProducts();
        ProductsView render = new ProductsView();
        showOrderItem();
        System.out.println("Nhập ID của vật phẩm muốn đổi: ");
        Long id = Long.parseLong(scanner.nextLine());
        int count = 0;
        for (OrderItem orderItem : orderItems) {
            Long tamp = orderItem.getId();
            String name = "";
            double price = 0;
            double total = 0;
            long idEdit = 0;
            int quantity = 0;
            if (tamp.equals(id)) {
                render.showProducts();
                System.out.println("Nhập ID của vật phẩm mới: ");
                idEdit = Long.parseLong(scanner.nextLine());
                for (ProductsManager product : productsManagers) {
                    Long tempProduct = product.getIdProduct();
                    if (tempProduct.equals(idEdit)) {
                        price = product.getPrice();
                        name = product.getName();
                    }
                }
                for (ProductsManager product : productsManagers) {
                    Long tempQuantity = product.getIdProduct();
                    if (tempQuantity.equals(idEdit)) {
                        total = product.getPrice() * quantity;
                    }
                }
            }
            orderItem.setId(idEdit);
            orderItem.setName(name);
            orderItem.setPrice(price);
            orderItem.setQuantity(quantity);
            orderItem.setTotal(total);
            count++;
            CSVUtils.write(PATH_ORDERITEM, orderItems);
            showOrderItem();
            break;
        }
        if (count == 0) {
            System.out.println("ID không tồn tại vui lòng nhập lại!");
            editOrderItem();
        }
    }

    public static void deleteOrderItem() {
        List<ProductsManager> products = findAllProducts();
        Scanner input = new Scanner(System.in);
        List<OrderItem> orderItemList = findAll();
        showOrderItem();
        System.out.println("\nNhập ID của sản phẩm muốn xóa. ");
        System.out.print("➥ ");
        Long id = Long.parseLong(input.nextLine());
        int count = 0;
        for (OrderItem orderItem : orderItemList) {
            Long tamp = orderItem.getId();
            if (tamp.equals(id)) {
                count++;
                System.out.println("----------------------------------------------------------");
                System.out.printf("|        Bạn có chắc chắn muốn xóa '%s' ?      |\n", orderItem.getName());
                System.out.println("----------------------------------------------------------");
                System.out.println("Nhấn Y để xóa hoặc N để quay lại!");
                System.out.print("➥ ");
                String choice = input.nextLine();
                switch (choice) {
                    case "y":
                        for (ProductsManager product : products) {
                            Long temp = product.getIdProduct();
                            if (temp.equals(id)) {
                                product.setQuantity(product.getQuantity() + orderItem.getQuantity());
                                CSVUtils.write(PATH_PRODUCT, products);
                            }
                        }
                        orderItemList.remove(orderItem);
                        CSVUtils.write(PATH_ORDERITEM, orderItemList);
                        showOrderItem();
                        selectOderItem();
                        updateOrderItem();
                        break;
                    case "N":
                        return;
                    default:
                        throw new IllegalStateException("Unexpected value: " + choice);
                }
            }
        }
        if (count == 0) {
            System.out.println("ID không tồn tại vui lòng nhập lại !!");
            deleteOrderItem();
        }
    }

    private static void doubleSource(List<OrderItem> orderItems, Long id, double price, double total, String name, int quantity) {
        OrderItem orderItem = new OrderItem(id, name, price, quantity, total);
        orderItems.add(orderItem);
        Instant timeCreate = Instant.now();
        List<OrderHistory> orderHistories = OrderHistoryView.findAllOrderHistory();
        OrderHistory orderHistory = new OrderHistory(id, name, price, quantity, total, timeCreate);
        orderHistories.add(orderHistory);
        CSVUtils.write(PATH_ORDER_HISTORY, orderHistories);
        CSVUtils.write(PATH_ORDERITEM, orderItems);
        System.out.println("Order thành công!");
    }

    public static void showOrderItem() {
        System.out.printf("\n    |       %-10s |            %-30s |       %-15s |    %-10s |     %-23s|\n", "ID ", "Tên Vật Phẩm", "Giá Tiền", "Số lượng", "Thành Tiền");
        System.out.println("\t▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(PATH_ORDERITEM));
            while ((line = br.readLine()) != null) {
                printMenuOrderItem(parseCsvLine(line));
            }
            renderTotal();
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
    }

    public static void printMenuOrderItem(List<String> OrderItem) {
        System.out.printf("    |       %-8s |    %-36s   |       %-15s |       %-8s |     %-19s   |\n",
                OrderItem.get(0), OrderItem.get(1), InstantUtils.doubleToVND(Double.parseDouble(OrderItem.get(2))), OrderItem.get(3), InstantUtils.doubleToVND(Double.parseDouble(OrderItem.get(4))));
    }

    public static List<String> parseCsvLine(String csvLine) {
        return getStrings(csvLine);
    }

    static List<String> getStrings(String csvLine) {
        List<String> result = new ArrayList<>();
        if (csvLine != null) {
            String[] splitData = csvLine.split(",");
            for (int i = 0; i < splitData.length; i++) {
                result.add(splitData[i]);
            }
        }
        return result;
    }

    public static Double totalPrice() {
        List<OrderItem> orderItemList = findAll();
        double totalPrice = 0;
        for (OrderItem orderItem : orderItemList) {
            totalPrice += orderItem.getTotal();
        }
        return totalPrice;
    }

    public static void renderTotal() {
        System.out.println("\t▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
        System.out.printf("    |                                                                                         Tổng tiền:          %s           |\n", InstantUtils.doubleToVND(totalPrice()));
        System.out.println("\t▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\n");
    }

    public static void selectOderItem() {
        System.out.println("☰☰☰☰☰☰☰☰☰   SELECT   ☰☰☰☰☰☰☰☰☰");
        System.out.println("☰                                   ☰");
        System.out.println("☰       1. Tiếp tục mua hàng        ☰");
        System.out.println("☰       2. Sửa giỏ hàng             ☰");
        System.out.println("☰       3. Xóa mặt hàng đã chọn     ☰");
        System.out.println("☰       4. Xuất hóa đơn             ☰");
        System.out.println("☰                                   ☰");
        System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰.☰☰☰☰☰☰☰☰☰☰☰☰☰");
        System.out.print("➥ ");
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                updateOrderItem();
                showOrderItem();
                totalPrice();
                selectOderItem();
                break;
            case "2":
                editOrderItem();
                showOrderItem();
                selectOderItem();
                break;
            case "3":
                showOrderItem();
                deleteOrderItem();
                selectOderItem();
                break;
            case "4":
                OrderView.showOrder();
                showOrderItem();
                MemberView.menuMember();
                break;
            default:
                System.out.println("Không hợp lệ, vui lòng nhập lại.");
                selectOderItem();
                break;
        }
    }
}
