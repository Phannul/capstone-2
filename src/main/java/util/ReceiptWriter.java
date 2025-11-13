package util;

import models.Order;
import ui.UI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    public void saveReceipt(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String time = LocalDateTime.now().format(formatter);
        String fileName = time + ".csv";
        File receipts = new File("src/main/resources/receipts/" + fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(receipts,true))) {
            bufferedWriter.write("\n ==== DELIcious Receipt ==== \n");
            bufferedWriter.write("Date: " + LocalDateTime.now());
            if (order.getCustomerName() != null) {
                bufferedWriter.write("\n Customer: " + order.getCustomerName() + "\n");
            }
            if (order.getCoupon() != null){
                bufferedWriter.write("\n Coupon used = " + order.getCoupon() + " 25% off");
            }
            bufferedWriter.write(order.summaryFormatForReceipt());
            bufferedWriter.write("\n==========================================\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
