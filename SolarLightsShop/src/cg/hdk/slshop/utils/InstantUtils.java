package cg.hdk.slshop.utils;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InstantUtils {
    private static final String PATTERN_FORMAT = "dd-MM-yyyy";
    public static String instantToString(Instant instant){
        return instantToString(instant, null);
    }
    public static String instantToString(Instant instant, String patternFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat != null ? patternFormat :
                PATTERN_FORMAT).withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
    public static String doubleToVND(double value){
        String patternVND = ",### VNĐ";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }
    public static String quantityProducts(int value){
        String patternVND = "###";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }

}
