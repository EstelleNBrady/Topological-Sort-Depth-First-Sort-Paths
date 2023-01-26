//Estelle Brady
//CS 351 - 401
//collaborated with Alex H, Al Frey, Anna, Miguel Garcia, got help at tutoring - library\

package edu.uwm.cs351;

import java.util.Stack;

/**
 * A path created by adding a new task to the end of an existing path.
 */
public class PostpendPath extends Path {
	private final Path most;
	private final Task task;
	private final Task start;
	private final int size;
	
	/**
	 * Create a path from a path and a task to put at the end
	 * @param p path to add to
	 * @param t task to add
	 */
	public PostpendPath(Path p, Task t) {
		if (t == null) throw new NullPointerException("task cannot be null");
		if (!t.getDependencies().contains(p.getLast())) {
			throw new IllegalArgumentException("path is not connected to task");
		}
		task = t;
		most = p;
		start = most.getFirst();
		size = p.size()+1;
	}

	@Override // required
	public int size() {
		return size;
	}

	@Override // required
	public Task getFirst() {
		return start;
	}

	@Override // required
	public Task getLast() {
		return task;
	}

	@Override // required
	protected void toArrayHelper(Stack<Work> worklist, Task[] array, int index) {
		array[index+size-1] = task;
		worklist.push(new Work(most,index));
	}
	
	@Override // required
	public boolean containsHelper(Stack<Work>worklist, Task t) {
		//we start where path1 ends, and we must subtract 1 to account the 0 index.
		//comparison check if task looking for equal to the task of path currently looking at
		worklist.push(new Work(most,0));
		return t == task || t == start;   
	}

	@Override // required
	protected Task getHelper(Stack<Work> worklist, int index) {
		//if it is equal to most's size return the task
		 if(index == most.size())
			 return task;
		 //if not, then we must push
		 worklist.push(new Work(most,index));
			 return start;	 
	}
}
