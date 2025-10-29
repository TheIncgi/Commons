package com.theincgi.commons.console;

import java.util.Scanner;
import java.util.function.Function;

public class ConsoleUtils {

	private static Scanner scanner = new Scanner(System.in);
	private static final String FORMAT = "%s: [def: %s] ";
	
	public static <T> T prompt(String msg, T defaultValue, Function<String, T> parser) {
		while(true) {
			System.out.printf(FORMAT, msg, defaultValue);
			var line = scanner.nextLine();
			if(line.isBlank()) {
				return defaultValue;
			}
			try {
				return parser.apply(line);
			}catch (Exception e) {
				// retry
			}
		}
	}
	
	public static String prompt(String msg, String defaultValue) {
		return prompt( msg, defaultValue, s->s);
	}
	
	public static int prompt(String msg, int defaultValue) {
		return prompt( msg, defaultValue, Integer::parseInt);
	}
	
	public static double prompt(String msg, double defaultValue) {
		return prompt( msg, defaultValue, Double::parseDouble);
	}
}
