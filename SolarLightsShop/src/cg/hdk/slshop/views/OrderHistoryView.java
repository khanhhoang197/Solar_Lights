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

import static cg.hdk.slshop.views.OrderItemView.getStrings;

public class OrderHistoryView {
    public final static String PATH_ORDER_HISTORY = "F:\\CodeGym\\Solar_Lights\\SolarLightsShop\\data\\salesHistory.csv";

    public static List<OrderHistory> findAllOrderHistory() {
        List<OrderHistory> orderHistories = new ArrayList<>();
        List<String> lines = CSVUtils.read(PATH_ORDER_HISTORY);
        for (String line : lines) {
            orderHistories.add(OrderHistory.parseOrderHistory(line));
        }
        return orderHistories;
    }

    public static List<String> parseCsvLine(String csvLine){
        return getStrings(csvLine);
    }
    public static void renderOrderHistory(){
        System.out.print("\t▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓ TOÀN BỘ DOANH THU ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.printf("\n\t|       %-7s |           %-25s |      %-26s |      %-15s |      %-26s |      %-16s | \n", "ID", "Tên Sản Phẩm", "Giá Tiền", "Số Lượng", "Thành Tiền", "Ngày Xuất Đơn");
        System.out.print("\t▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔");
        BufferedReader br;
        try {
            String line;
            br = new BufferedReader(new FileReader(PATH_ORDER_HISTORY));
            while ((line = br.readLine()) != null){
                printMenu(parseCsvLine(line));
            }
            renderTotalHistory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void printMenu(List<String> OrderHistory) {
        System.out.printf("\n\t|   %-5s  | %-32s    |     %-26s  |         %-10s   |      %-25s  |      %-13s    |\n",
                OrderHistory.get(0), OrderHistory.get(1), InstantUtils.doubleToVND(Double.parseDouble(OrderHistory.get(2))), OrderHistory.get(3),
                InstantUtils.doubleToVND(Double.parseDouble(OrderHistory.get(4))),
                InstantUtils.instantToString(Instant.parse(OrderHistory.get(5))));
    }
    public static Double totalOrderHistory() {
        List<OrderHistory> orderHistories = findAllOrderHistory();
        Double totalAllPrice = Double.valueOf(0);
        for (OrderHistory orderHistory : orderHistories) {
            totalAllPrice += orderHistory.getTotal();
        }
        return totalAllPrice;
    }
    public static void renderTotalHistory() {
        System.out.print("\t▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔▔\n");
        System.out.printf("    |                                                                              Tổng tiền:                              %s           \n", InstantUtils.doubleToVND(totalOrderHistory()));
        System.out.println("\t▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓\n");
    }
}
