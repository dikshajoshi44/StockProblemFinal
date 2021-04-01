package com.dikshaJoshi.StockProblem;

import java.io.*;
import java.util.Scanner;

public class StockProblemApplication {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter complete path of the input file: e.g. /usr/src/StockProblem/input.txt");
        String inputFile = sc.nextLine();
        File file = new File(inputFile);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine())!=null) {
                StockProblemUtil.processOrder(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + file + " Not Found");
        } catch (IOException e) {
            System.out.println("Error while reading file " + file + " " + e.getMessage());
        }
    }
}
