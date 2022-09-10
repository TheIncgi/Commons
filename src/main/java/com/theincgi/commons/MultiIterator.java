package com.theincgi.commons;

import java.util.Iterator;

public class MultiIterator<T> implements Iterator<T>, Iterable<T> {
	private final Iterator<? extends T>[] iterators;
	private int current = 0;
	private Boolean hasNext = null;
	
	@SafeVarargs
	public MultiIterator( Iterator<? extends T>... iterators ) {
		this.iterators = iterators;
	}
	
	@Override
	public boolean hasNext() {
		if(hasNext != null)
			return hasNext;
		
		if( iterators.length == 0 || current >= iterators.length )
			return hasNext = false;
		while( true ) {
			if( iterators[current].hasNext() )
				return hasNext = true;
			if(++current >= iterators.length)
				return hasNext = false;
		}
	}
	
	@Override
	public T next() {
		hasNext();                        //make sure we're on a usable/last iterator
		return iterators[current].next(); //let the iterator throw an error 
	}
	
	@Override
	public void remove() {
		iterators[current].remove();
	}
	
	@Override
	public Iterator<T> iterator() {
		return this;
	}
}
