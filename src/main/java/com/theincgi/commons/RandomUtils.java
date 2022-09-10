package com.theincgi.commons;

import java.util.List;
import java.util.Random;

public class RandomUtils {
	
	@SafeVarargs
	public static final <T> T pickRandom(Random random, T... t) {
		return t[ random.nextInt(t.length) ];
	}
	
	public static final <T> T pickRandom(Random random, List<T> t) {
		return t.get( random.nextInt(t.size()) ) ;
	}
}
