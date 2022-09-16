package cg.hdk.slshop.views;

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
    public void renderOrderHistory(){
        System.out.printf("\n\t%-16s %-36s %-26s %-15s %-26s %s\n\n", "ID", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng","Tổng Tiền","Ngày Xuất Đơn");
        BufferedReader br;
        try {
            String line;
            br = new BufferedReader(new FileReader(PATH_ORDER_HISTORY));
            while ((line = br.readLine()) != null){
                printMenu(parseCsvLine(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void printMenu(List<String> OrderHistory) {
            System.out.printf("\n\t%-16s %-36s %-26s %-16s %-26s %s\n\n", OrderHistory.get(0), OrderHistory.get(1), OrderHistory.get(2), OrderHistory.get(3),OrderHistory.get(4), InstantUtils.instantToString(Instant.parse(OrderHistory.get(5))));
    }
}
