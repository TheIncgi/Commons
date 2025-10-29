package com.theincgi.commons.console.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TextMenu<T> {
	
	private String title;
	private List<MenuOption<T>> options;
	private HashMap<Integer, MenuOption<T>> currentOptions = new HashMap<>();
	
	public TextMenu(String title) {
		this.title = title;
		options = new ArrayList<>();
	}
	
	public TextMenu(String title, List<MenuOption<T>> options) {
		this(title);
		this.options.addAll(options);
	}
	
	public void addOption(MenuOption<T> option) {
		options.add(option);
	}
	
	public void removeOption(MenuOption<T> option) {
		options.remove(option);
	}
	
	private int computeWidth() {
		int w = title.length();
		int x = Integer.toString(options.size()).length() + 2;
		for(var opt : options) {
			w = Math.max(w, x + opt.width(x));
		}
		return w;
	}
	
	public void print() {
		currentOptions.clear();
		final int w = computeWidth();
		int x = Integer.toString(options.size()).length() + 2;
		String horz = "+" + "=".repeat(w+2) + "+";
		
		String format  = "| %-"+w+"s |";
		String format2 = "| %"+(x-2)+"d) %-"+(w-x)+"s |";
		
		System.out.println(horz);
		System.out.println(format.formatted(title));
		System.out.println(horz);
		
		int i = 1;
		for(var opt : options) {
			if(!opt.isVisible()) continue;
			var lines = opt.getText();
			for(int j = 0; j<lines.length; j++) {
				var text = lines[j];
				if( j == 0 ) {
					System.out.println(format2.formatted(i, text));					
				} else {
					System.out.println(format.formatted(" ".repeat(x) + text));										
				}
			}
			currentOptions.put(i++, opt);
		}
		System.out.println(horz);
		System.out.print("> ");
	}
	
	public T show() {
		print();
		int choice;
		var in = new Scanner(System.in);
		while(true) {
			try {
				choice = Integer.parseInt(in.nextLine().trim());
			}catch(NumberFormatException nfe) {
				System.out.print("> ");
				continue;
			}
			if(currentOptions.containsKey(choice)) {
				break;
			}
			System.out.print("> ");
		}
		var opt = currentOptions.get(choice);
		return opt.select();
	}
	
}
