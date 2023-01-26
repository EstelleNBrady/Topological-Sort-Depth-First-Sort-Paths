//Estelle Brady
//CS 351 - 401
//collaborated with Alex H, Al Frey, Anna, Miguel Garcia, got help at tutoring - library\

package edu.uwm.cs351;

import java.util.Stack;

/**
 * A path built up by concatenating two paths.
 * It doesn't add any edges.  The lengths are added, but the size of the result is
 * one less that the size of the two parts.
 */

//it is a path by connecting two paths
//we do not add edges and the lengths are added. The size are the length added but one less than the size of the two partss.
public class ConcatPath extends Path {
	private final Task Ftask;
	private final Task Ltask;
	private final int size;
	private final Path path1;
	private final Path path2;
	// TODO: Data Structure (no wellFormed needed)
	// Remember: all fields should be final
	
	/**
	 * Connect two paths
	 * @param p1 non-degenerate path that ends in one task
	 * @param p2 non-degenerate path start starts with that same task
	 * @exception NullPointerException
	 */
	public ConcatPath(Path p1, Path p2) {
		if(p1 == null || p2 == null) {
			throw new NullPointerException("paths cannot be null");
		}
		path1 = p1;
		path2 = p2;
		Ftask = p1.getFirst();
		Ltask = p2.getLast();
		
		if(p1.getLast() != p2.getFirst())
			throw new IllegalArgumentException("the paths are not connected");
		if(!(p1.size() > 1) || !(p2.size() > 1)) {
			throw new IllegalArgumentException("the path is degenerate");
		}
		size = p1.size() + p2.size() -1;
	}
	
	@Override // required
	public int size() {
		return size; // TODO
	}

	@Override // required
	public Task getFirst() {
		return Ftask; // TODO
	}

	@Override // required
	public Task getLast() {
		return Ltask; // TODO
	}
	
	/**
	 * Copy all the tasks into the given array at the given index.
	 * @param worklist add any work that needs to be (instead of recursion).
	 * @param array array to write into, must not be null
	 * @param index index into which to copy tasks, must be legal
	 */
	@Override // required
	protected void toArrayHelper(Stack<Work> worklist, Task[] array, int index) {
        worklist.push(new Work(path1,index));
        //we start where path1 ends, and we must subtract 1 to account the 0 index.
        worklist.push(new Work(path2,path1.size()-1+index));
	}
	
	@Override // required
	public boolean containsHelper(Stack<Work>worklist, Task t) {
        worklist.push(new Work(path1,0));
        worklist.push(new Work(path2,path1.size()-1));
	    //we start where path1 ends, and we must subtract 1 to account the 0 index.
		//comparison check if task lookig for equal to the task of path currently looking at
		//task,start,last,
		return t == Ftask || t == Ltask;
	}
	
	@Override // required
	public Task getHelper(Stack<Work>worklist, int index) {
		//we get the last element in path 1
		if(index<path1.size()-1) {
			worklist.push(new Work(path1,index));
			//in this case, return the first task
			return Ftask;
		}
		 worklist.push(new Work(path2,index-path1.size()+1));
	        return Ltask;
	}
}
