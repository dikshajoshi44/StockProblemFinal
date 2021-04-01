package com.dikshaJoshi.StockProblem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class StockProblemApplicationTest {

	public static void main(String[] args) {

		SpringApplication.run(StockProblemApplicationTest.class, args);

		String input1 = "#1 09:45 BAC sell 240.12 100";
		String input2 = "#2 09:46 BAC sell 237.45  90";
		String input3 = "#3 09:47 BAC buy  238.10 110";
		String input4 = "#4 09:48 BAC buy  237.80  10";
		String input5 = "#5 09:49 BAC buy  237.80  40";
		String input6 = "#6 09:50 BAC sell 236.00  50";

		List<String> inputs = new ArrayList();
		inputs.add(input1);
		inputs.add(input2);
		inputs.add(input3);
		inputs.add(input4);
		inputs.add(input5);
		inputs.add(input6);

		for(int i = 0; i < inputs.size() ; i++){
			StockProblemUtil.processOrder(inputs.get(i));
		}
	}

}
