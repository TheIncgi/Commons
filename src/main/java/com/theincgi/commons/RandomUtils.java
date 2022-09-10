package com.theincgi.commons;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomUtils {
	
	@SafeVarargs
	public static final <T> T pickRandomOfVarargs(Random random, T... t) {
		return t[ random.nextInt(t.length) ];
	}
	
	public static final <T> T pickRandom(Random random, List<T> t) {
		return t.get( random.nextInt(t.size()) ) ;
	}
	
	public static final <T> T pickRandom(Random random, Set<T> set) {
		if (set.size() == 0)
			return null;
		@SuppressWarnings("unchecked")
		var keys = (T[])set.toArray();
		return keys[random.nextInt(keys.length)];
	}
}
