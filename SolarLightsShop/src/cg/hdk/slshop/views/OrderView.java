package cg.hdk.slshop.views;

import cg.hdk.slshop.model.Order;
import cg.hdk.slshop.model.User;
import cg.hdk.slshop.utils.CSVUtils;
import cg.hdk.slshop.utils.InputOption;
import cg.hdk.slshop.utils.InstantUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static cg.hdk.slshop.views.OrderItemView.parseCsvLine;

public class OrderView {
    public static final String PATH_ORDER = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\Order.csv";
    Scanner scanner = new Scanner(System.in);
    public List<Order> orders;

    public OrderView() {
        List<Order> orderList = new ArrayList<>();
        this.orders = orderList;
    }
    public List<Order> findAllOrder() {
        List<Order> orders = new ArrayList<>();
        List<String> lines = CSVUtils.read(PATH_ORDER);
        for (String line : lines) {
            orders.add(Order.parseOder(line));
        }
        return orders;
    }
    public void addOrder() {
        List<Order> orderList = new ArrayList<>();
        ProductsView productsView = new ProductsView();
        productsView.showProducts();
        Long idOder = System.currentTimeMillis() / 1000;
        String fullName = UserView.inputFullName(InputOption.ADD);
        String mobile = UserView.inputPhone(InputOption.ADD);
        System.out.print("Địa chỉ: ");
        String address = scanner.nextLine();
        Order order = new Order(idOder, fullName, mobile, address);
        order.setTimeCreate(Instant.now());
        orderList.add(order);
        CSVUtils.write(PATH_ORDER, orderList);
    }
    public static void showMenu(List<String> Order) {
        System.out.println();
        System.out.println("     |          Số hóa đơn: " + Order.get(0));
        System.out.println("     |          Tên khách hàng: " + Order.get(1));
        System.out.println("     |          Số điện thoại: " + Order.get(2));
        System.out.println("     |          Địa chỉ: " + Order.get(3));
        System.out.println("     |          Ngày mua hàng: " + InstantUtils.instantToString(Instant.parse(Order.get(4))));
    }
    public static void showOrder() {
        System.out.println("\t⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸⫸   Bill   ⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷⫷");
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(PATH_ORDER));
            while ((line = br.readLine()) != null) {
                showMenu(parseCsvLine(line));
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
}
