package cg.hdk.slshop.views;

import cg.hdk.slshop.model.OrderHistory;
import cg.hdk.slshop.model.OrderItem;
import cg.hdk.slshop.model.ProductsManager;
import cg.hdk.slshop.service.ProductsService;
import cg.hdk.slshop.utils.CSVUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderItemView {
    private final String PATH_ORDERITEM = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\OrderItem.csv";
    Scanner scanner = new Scanner(System.in);
    List<OrderItem> orderItems = new ArrayList<>();

    public OrderItemView() {
        List<OrderItem> orderItemList = new ArrayList<>();
        this.orderItems = orderItemList;
    }

    public List<OrderItem> findAll() {
        List<OrderItem> orderItems = new ArrayList<>();
        List<String> lines = CSVUtils.read(PATH_ORDERITEM);
        for (String line : lines) {
            orderItems.add(OrderItem.parserOderItem(line));
        }
        return orderItems;
    }

    public final static String PATH_PRODUCT = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\products.csv";

    public List<ProductsManager> findAllProducts() {
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
        double price = Double.valueOf(0);
        double total = Double.valueOf(0);
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
            }
        }
        OrderItem orderItem = new OrderItem(id, name, price, quantity, total);
        orderItems.add(orderItem);
        Instant timeCreate = Instant.now();
        List<OrderHistory> orderHistories = OrderHistoryView.findAllOrderHistory();
        OrderHistory orderHistory = new OrderHistory(id, name, price, quantity, total, timeCreate);
        orderHistories.add(orderHistory);
        CSVUtils.write("F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\salesHistory.csv", orderHistories);
        CSVUtils.write(PATH_ORDERITEM, orderItems);
        System.out.println("Order thành công!");
        showOrderItem();
        System.out.println("Bạn có muốn tiếp tục mua hàng không? ");
        confirm();
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice) {
            case 1:
                boolean isTrue = true;
                while (isTrue) {
                    updateOrderItem();
                    confirm();
                    switch (choice) {
                        case 1:
                            isTrue = true;
                            break;
                        case 2:
                            isTrue = false;
                            break;
                        default:
                            System.out.println("Không hợp lệ, vui lòng nhập lại.");
                            updateOrderItem();
                    }
                }
                break;
            case 2:
                break;
        }
    }

    public void confirm() {
        System.out.println("☰☰☰☰☰☰☰☰☰   CONFIRM  ☰☰☰☰☰☰☰☰☰");
        System.out.println("☰                                    ☰");
        System.out.println("☰       1. Tiếp tục mua hàng         ☰");
        System.out.println("☰       2. Xuất hóa đơn              ☰");
        System.out.println("☰                                    ☰");
        System.out.println("☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰☰");
        int choice = Integer.parseInt(scanner.nextLine());
        System.out.print("➥ ");
    }

    private void showOrderItem() {
        System.out.printf("\n%-25s %-25s %-25s %-25s %s\n", "ID Product", "Tên Vật Phẩm", "Giá Tiền", "Số lượng", "Thành Tiền");
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\products.csv"));
            while ((line = br.readLine()) != null) {
                printMenuOrderItem(parseCsvLine(line));
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
    }

    public static void printMenuOrderItem(List<String> OrderItem) {
        System.out.printf("\n%-25s %-25s %-25s %-25s %s\n", OrderItem.get(0), OrderItem.get(1), OrderItem.get(2), OrderItem.get(3), OrderItem.get(4));
    }

    public static List<String> parseCsvLine(String csvLine) {
        List<String> result = new ArrayList<>();
        if (csvLine != null) {
            String[] splitData = csvLine.split(",");
            for (int i = 0; i < splitData.length; i++) {
                result.add(splitData[i]);
            }
        }
        return result;
    }

    public void updateOrderItem() {
        List<OrderItem> orderItems = findAll();
        List<ProductsManager> productsManagers = findAllProducts();
        ProductsView render = new ProductsView();
        render.showProducts();
        System.out.print("Nhập ID sản phẩm cần mua: ");
        Long id = Long.parseLong(scanner.nextLine());
        Double price = Double.valueOf(0);
        Double total = Double.valueOf(0);
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
                    CSVUtils.write("F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\products.csv", productsManagers);
                    countQuantity++;
                }
            }
            if (countQuantity == 0) {
                System.out.println("Số lượng không hợp lệ! Vui lòng nhập lại!!");
                addOrderItem();
                return;
            }
        }
        OrderItem orderItem = new OrderItem(id,name,price,quantity,total);
        orderItems.add(orderItem);
        Instant timeCreate = Instant.now();
        List<OrderHistory> orderHistories = OrderHistoryView.findAllOrderHistory();
        OrderHistory orderHistory = new OrderHistory(id, name, price, quantity, total, timeCreate);
        orderHistories.add(orderHistory);
        CSVUtils.write("F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\salesHistory.csv", orderHistories);
        CSVUtils.write(PATH_ORDERITEM, orderItems);
        System.out.println("Order thành công!");
    }
    public Double totalPrice(){
        List<OrderItem> orderItemList = findAll();
        Double  totalPirce = Double.valueOf(0);
        for (OrderItem orderItem: orderItemList) {
            totalPirce += orderItem.getTotal();
        }
        return totalPirce;
    }
    public void editOrderItem(){
        List<OrderItem> orderItems = findAll();
        List<ProductsManager> productsManagers = findAllProducts();
        ProductsView render = new ProductsView();

    }

}
