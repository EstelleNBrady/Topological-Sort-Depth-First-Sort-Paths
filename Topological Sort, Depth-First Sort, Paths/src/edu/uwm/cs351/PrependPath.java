//Estelle Brady
//CS 351 - 401
//collaborated with Alex H, Al Frey, Anna, Miguel Garcia, got help at tutoring - library

package edu.uwm.cs351;

import java.util.Stack;

/**
 * A path created by adding a task before an existing path.
 */
public class PrependPath extends Path {
	private final Path rest;
	private final Task task;
	private final Task last;
	private final int size;
	
	/**
	 * Create a path that has a task before another path
	 * @param t task to put before
	 * @param p path to put the task before
	 * @exception NullPointerException
	 * @exception IllegalArgumentException
	 */
	public PrependPath(Task t, Path p) {
		if (t == null) throw new NullPointerException("task cannot be null");
		if (!p.getFirst().getDependencies().contains(t)) {
			throw new IllegalArgumentException("path is not connected to task");
		}
		task = t;
		rest = p;
		last = rest.getLast();
		size = p.size()+1;
	}

	@Override // required
	public int size() {
		return size;
	}

	@Override // required
	public Task getFirst() {
		return task;
	}

	@Override // required
	public Task getLast() {
		return last;
	}

	@Override // required
	protected void toArrayHelper(Stack<Work> worklist, Task[] array, int index) {
		array[index] = task;
		worklist.push(new Work(rest,index+1));
	}
	
	@Override // required
	public boolean containsHelper(Stack<Work>worklist, Task t) {
		   worklist.push(new Work(rest,0));
	        //we start where path1 ends, and we must subtract 1 to account the 0 index.
		   //comparison check if task looking for equal to the task of path currently looking at
		return last == t || t ==task;   
	}
	
	// TODO override helpers for contains and get
	@Override //required
	protected Task getHelper(Stack<Work> worklist, int index) {
		//if it is not 0, then we push the rest
		 if(index!=0) {
		 worklist.push(new Work(rest,index-1));
		 return last;
		 }
		 	return task;
	       //we start where path1 ends, and we must subtract 1 to account the 0 index.
		   //comparison check if task looking for equal to the task of path currently looking at
	}
}
