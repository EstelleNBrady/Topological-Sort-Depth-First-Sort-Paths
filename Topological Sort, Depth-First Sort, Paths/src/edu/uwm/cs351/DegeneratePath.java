//Estelle Brady
//CS 351 - 401
//collaborated with Alex H, Al Frey, Anna, Miguel Garcia, got help at tutoring - library\

package edu.uwm.cs351;

import java.util.Stack;
/**
 * A path without edges, just a single task.
 */
public class DegeneratePath extends Path {
	private final Task element; // never null
	
	public DegeneratePath(Task task) {
		if (task == null) throw new NullPointerException("path requires non-null task");
		element = task;
	}
	
	@Override // required
	public int size() {
		return 1;
	}
	
	@Override // required
	public Task getFirst() {
		return element;
	}

	@Override // required
	public Task getLast() {
		return element;
	}

	@Override // required
	protected void toArrayHelper(Stack<Work> worklist, Task[] array, int index) {
		array[index] = element;
		// don't add anything to worklist since there are no sub pahs here
	}
	
	@Override //required
	public boolean containsHelper(Stack<Work>worklist, Task t) {
		return element.equals(t);	   
	}
	
	@Override // required
	public Task getHelper(Stack<Work>worklist, int index) {
		 return element;		
	}
	// TODO Helpers for contains and get
}
