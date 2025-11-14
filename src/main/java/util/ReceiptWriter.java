package util;

import models.Order;
import ui.UI;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// a writer method to print out the receipts into a new file
public class ReceiptWriter {
    public void saveReceipt(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");// formats the date time in desired format
        String time = LocalDateTime.now().format(formatter);
        String fileName = time + ".csv";
        File receipts = new File("src/main/resources/receipts/" + fileName); //creates new file any time the method is completed
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(receipts,true))) {
            bufferedWriter.write("\n ==== Drippy The Deli Receipt ==== \n");
            bufferedWriter.write("Date: " + LocalDateTime.now());
            // Includes customer name
            if (order.getCustomerName() != null) {
                bufferedWriter.write("\n Customer: " + order.getCustomerName() + "\n");
            }
            //includes coupon information
            if (order.getCoupon().equalsIgnoreCase("fanuelisgreat") || order.getCoupon().equalsIgnoreCase("ihategarlic")){
                bufferedWriter.write("\n Coupon used = " + order.getCoupon() + " 25% off");
            }else {
                System.out.println("No Coupon Used");
            }
            bufferedWriter.write(order.summaryFormatForReceipt());
            bufferedWriter.write("\n==========================================\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
