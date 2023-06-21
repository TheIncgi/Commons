package com.theincgi.commons;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionUtils {
	

	@SuppressWarnings("unchecked")
	public static <T,U extends T, Z extends List<U>> Z addAllWhereExtends( Z into, List<? extends T> from, Class<U> t ) {
		for( var f : from )
			if( t.isInstance(f) ) //class isInstance obj
				into.add((U) f);
		
		return into;
	} 
	
	public static <T> Pair<Integer, T> bestMatch( List<T> list, BiFunction<T, T, Boolean> bIsBetter ) {
		if( list.size() == 0 )
			return null;
		var it = list.iterator();
		int bestIndex = 0;
		T bestValue = it.next();
		for( int i = 1; it.hasNext(); i++  ) {
			T b = it.next();
			if( bIsBetter.apply(bestValue, b) ) {
				bestIndex = i;
				bestValue = b;
			}
		}
		return new Pair<>( bestIndex, bestValue );
	}
	
	public static BiFunction<? extends Number, ? extends Number, Boolean> GREATEST = (a,b) -> {
		return a.doubleValue() < b.doubleValue();
	};
	
	public static BiFunction<? extends Number, ? extends Number, Boolean> LEAST = (a,b) -> {
		return a.doubleValue() > b.doubleValue();
	};
	
	public static <T, Z extends List<T>> Z collect( Stream<T> stream, Supplier<Z> list ) {
		return stream.collect(Collectors.toCollection(list));
	}
}
