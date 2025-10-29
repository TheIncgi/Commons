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
	public static final <Z, T extends Z, U extends Z> Z pickRandom(Random random, T a, List<U> orB) {
		float aChance = 1 / (1+orB.size());
		return random.nextFloat() < aChance ? a : pickRandom(random, orB);
	}
	
	public static final <T> T pickRandom(Random random, Set<T> set) {
		if (set.size() == 0)
			return null;
		@SuppressWarnings("unchecked")
		var keys = (T[])set.toArray();
		return keys[random.nextInt(keys.length)];
	}
	
	@SafeVarargs
	public static final <T> boolean isAnyOf(T t, T... any) {
		for(T a : any) {
			if(t==null && a==null)
				return true;
			if(t==null || a==null)
				continue;
			if(t.equals(a))
				return true;
		}
		return false;
	}
	
}
