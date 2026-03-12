public interface List<T>
{
	/**
	 * Adds item to the end of a list
	 * 
	 * @param item
	 */
	public void addLast(T item);

	/**
	 * Adds item to the start of a list
	 * 
	 * @param item
	 */
	public void addFirst(T item);

	/**
	 * Retrieves item from List
	 * 
	 * @param position where 0 is the first position in the list
	 * @return item - the item at the given position on the list 
	 * (or null if no item exists at that position)
	 */
	public T get (int position);

	/**
	 * Prints list to screen
	 */
	public void print();

	/**
	 * Prints list backwards to screen
	 */
	public void printBackwards();
	
	/**
	 * Removes first item from List
	 * 
	 * @param item
	 * @return true if item was removed, false otherwise
	 */
	public boolean remove (T item);

	/**
	 * Determines if the List is empty.
	 * 
	 * @return True if the List is empty, False otherwise.
	 */
	public boolean isEmpty();

	/**
	 * Determines the number of items in the List
	 * 
	 * @return int - The length of the list
	 */
	public int getLength();
}
