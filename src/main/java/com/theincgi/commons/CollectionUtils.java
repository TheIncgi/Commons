package com.theincgi.commons;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {
	

	@SuppressWarnings("unchecked")
	public static <T,U extends T, Z extends List<U>> Z addAllWhereExtends( Z into, List<? extends T> from, Class<U> t ) {
		for( var f : from )
			if( f.getClass().isInstance(t) )
				into.add((U) f);
		
		return into;
	} 
}
