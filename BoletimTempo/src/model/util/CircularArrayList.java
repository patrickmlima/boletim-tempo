package model.util;

import java.util.ArrayList;

/**
 * Class which can be treated as a circular array
 * 
 * @author Patrick M Lima
 * 
 * @param <E>
 *            data type of objects which will be added to array
 */
public class CircularArrayList<E> extends ArrayList<E> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index;

	/**
	 * Default class constructor
	 */
	public CircularArrayList() {
		super();
		index = -1;
	}
	
	/**
	 * Takes the next array element in sequential form. If is the last element,
	 * returns the first array element.
	 * 
	 * @return an array element
	 */
	public E getNext() {
		if (!super.isEmpty()) {
			if (index >= this.size() - 1)
				index = -1;
			return this.get(++index);
		}
		return null;
	}
	
	/**
	 * Takes the previous array element in sequential form. If is the first
	 * element, returns the last array element.
	 * 
	 * @return an array element
	 */
	public E getPrevious() {
		if (!super.isEmpty()) {
			if (index <= 0)
				index = this.size();
			return this.get(--index);
		}
		return null;
	}

	@Override
	public void clear() {
		super.clear();
		index = -1;
	}

	public int getIndex() {
		return index;
	}
}