package cg.hdk.slshop.views;

import cg.hdk.slshop.model.OrderDay;
import cg.hdk.slshop.model.OrderHistory;
import cg.hdk.slshop.utils.CSVUtils;
import cg.hdk.slshop.utils.InstantUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static cg.hdk.slshop.views.OrderHistoryView.parseCsvLine;

public class DayTurnOver {
    static Scanner scanner = new Scanner(System.in);
    private final static String PATH_ORDER_DAY = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\OrderDay";

    public static List<OrderDay> findAll() {
        List<OrderDay> orderDays = new ArrayList<>();
        List<String> lines = CSVUtils.read(PATH_ORDER_DAY);
        for (String line : lines) {
            orderDays.add(OrderDay.parserOrderDay(line));
        }
        return orderDays;
    }

    public static void renderOrderDay() {
        System.out.print("\t▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ DOANH THU NGÀY ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.printf("\n\t|       %-7s |           %-25s |      %-26s |      %-15s |      %-26s |      %-16s | \n", "ID", "Tên Sản Phẩm", "Giá Tiền", "Số Lượng", "Thành Tiền", "Ngày Xuất Đơn");
        System.out.print("\t▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(PATH_ORDER_DAY));
            while ((line = br.readLine()) != null) {
                printMenu(parseCsvLine(line));
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

    public static void printMenu(List<String> dayOrder) {
        System.out.printf("\n\t|   %-5s  | %-32s    |     %-26s  |         %-10s   |      %-25s  |      %-13s    |\n", dayOrder.get(0), dayOrder.get(1),
                InstantUtils.doubleToVND(Double.parseDouble(dayOrder.get(2))), dayOrder.get(3),
                InstantUtils.doubleToVND(Double.parseDouble(dayOrder.get(4))), InstantUtils.instantToString(Instant.parse(dayOrder.get(5))));
    }

    public static void sortByOrderDay() {
        List<OrderHistory> orderHistories = OrderHistoryView.findAllOrderHistory();
        List<OrderDay> orderDays = new ArrayList<>();
        System.out.println("Nhập ngày muốn tìm kiếm (Ví dụ: 01-01-2022)");
        System.out.print("➥ ");
        String day = scanner.nextLine();
        Long id;
        String name;
        double price;
        int quantity;
        Double total;
        Instant timeCreate;
        int count = 0;
        for (OrderHistory allOrder : orderHistories) {
            if (InstantUtils.instantToString((allOrder.getTimeCreate())).equals(day)) {
                id = allOrder.getId();
                name = allOrder.getName();
                price = allOrder.getPrice();
                quantity = allOrder.getQuantity();
                total = allOrder.getTotal();
                timeCreate = allOrder.getTimeCreate();
                OrderDay orderDay = new OrderDay(id, name, price, quantity, total, timeCreate);
                orderDays.add(orderDay);
                count++;
                CSVUtils.write(PATH_ORDER_DAY, orderDays);
            }
        }
        if (count == 0) {
            System.out.println("Ngày không đúng , vui lòng nhập lại!!");
            sortByOrderDay();
        }
        renderOrderDay();
    }

    public static Double totalDayPrice() {
        List<OrderDay> orderDayList = findAll();
        Double totalAllPrice = Double.valueOf(0);
        for (OrderDay orderDay : orderDayList) {
            totalAllPrice += orderDay.getTotal();
        }
        return totalAllPrice;
    }

    public static void renderTotal() {
        System.out.print("\t▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\n");
        System.out.printf("    |                                                                              Tổng tiền:                              %s           \n", InstantUtils.doubleToVND(totalDayPrice()));
        System.out.println("\t▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n");
    }
}
