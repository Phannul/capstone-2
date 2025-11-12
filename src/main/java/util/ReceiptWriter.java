package util;

import models.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ReceiptWriter {
    String path = "src/main/resources/Reciepts.csv";

    public void saveReceipt(Order order) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path,true))) {
            bufferedWriter.write("==== DELIcious Receipt ==== \n");
            bufferedWriter.write("Date: " + LocalDateTime.now());
            bufferedWriter.write(order.summaryFormatForReceipt());
            bufferedWriter.write("\n==========================================\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
