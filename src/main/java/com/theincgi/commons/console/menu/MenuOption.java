package com.theincgi.commons.console.menu;

import java.util.function.Supplier;

public class MenuOption<T> {
	private String[] text;
	private Supplier<T> onSelect;
	private boolean visible = true;
	
	public MenuOption(String text, Supplier<T> onSelect) {
		this.text = text.split("\n");
		this.onSelect = onSelect;
	}
	
	public String[] getText() {
		return text;
	}
	
	public T select() {
		return onSelect.get();
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public int width(int offset) {
		var w = 0;
		var first = true;
		for(var line : text) {
			w = Math.max(w, line.length() + (first?0:offset));
			if(first)
				first = false;
		}
		return w;
	}
	
}
