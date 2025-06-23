package br.com.bank.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    public static List<String> readEachLine() {
        List<String> listData = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            if (data.isEmpty()) {
                break;
            }
            listData.add(data);
        }

        return listData;
    }
}
