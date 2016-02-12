import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
	
	private int size;
	private int top;
	private int bottom;
	private int openCount = 0;
	private boolean[] sites; //T=open, F=closed
	private WeightedQuickUnionUF uf;

	// create N-by-N grid, with all sites blocked
	Percolation(int N) {
		if(N <= 0){
			throw new IllegalArgumentException();
		}
		
		size = N;
		sites = new boolean[size*size + 2];
		top = size*size;
		bottom = size*size + 1;
		uf = new WeightedQuickUnionUF(size*size + 2);
	}
	
	private void checkIndexException(int i, int j){
		if( (i < 1) || (j < 1) || (i > size) || (j > size)){
			throw new IndexOutOfBoundsException();
		}
	}
	
	//Open i,j if not already open.
	public void open(int i, int j){
		checkIndexException(i, j);

		//hack to make 1 based indexing work.
		i--; j--;
		
		//2d to 1d conversion makes life easy.
		int pos = convert2dto1d(i, j);
		if(!sites[pos]) openCount++;
		
		sites[pos] = true;
		//virtual top row 
		if(i == 0) uf.union(pos, top);
		//virtual bottom row 
		if(i == size-1) uf.union(pos, bottom);
		//right side
		if((j != size-1) && sites[pos+1]) uf.union(pos, pos+1);
		//left side
		if((j != 0) && sites[pos-1]) uf.union(pos, pos-1);	
		//top side 
		if((i != 0) && sites[pos-size]) uf.union(pos, pos-size);
		//bottom side 
		if((i != size-1) && sites[pos+size]) uf.union(pos, pos+size);
	}
	
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j){
		checkIndexException(i, j);
		//hack to make 1 based indexing work.
		i--; j--;
		
		int pos = convert2dto1d(i, j);
		return sites[pos];
	}
	
	// is site (row i, column j) full?
	public boolean isFull(int i, int j){
		checkIndexException(i, j);
		//hack to make 1 based indexing work.
		i--; j--;
		int pos = convert2dto1d(i, j);
		return uf.connected(pos, top);
	}
	
	// does the system percolate?
	public boolean percolates(){
		return uf.connected(top, bottom);
	}
	
	
	
	
	private int convert2dto1d(int i, int j){
		return size*i + j;
	}
	
	//Get number of sites it took to percolate
	public int getOpenCount(){
		return openCount;
	}

}
