import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	public int T;
	public int N;
	private int [] openCount;
	private Percolation[] perc;
	
	// perform T independent experiments on an N-by-N grid
	public PercolationStats(int N, int T) {
		if((N <= 0) || (T <= 0)){
			throw new IllegalArgumentException();
		}
		this.T = T;
		this.N = N;
		
		perc = new Percolation[T];
		for(int i = 0; i < T; i++){
			perc[i] = new Percolation(N);
		}
		
		boolean doesItPerc = false;
		int x; int y;
		openCount = new int [T];
		
		for(int i = 0; i < T; i++){
			while(!doesItPerc){
				x = StdRandom.uniform(N)+1;
				y = StdRandom.uniform(N)+1;
				perc[i].open(x,  y);
				doesItPerc = perc[i].percolates();
			}
			openCount[i] = perc[i].getOpenCount();
			doesItPerc = false;
		}
		

		
	}

	// sample mean of percolation threshold
	public double mean(){
		return StdStats.mean(openCount);
	}
	
	// sample standard deviation of percolation threshold
	public double stddev(){
		return StdStats.stddev(openCount);
	}
//	
//	// low  endpoint of 95% confidence interval
//	public double confidenceLo(){
//		
//	}
//	
//	// high endpoint of 95% confidence interval
//	public double confidenceHi(){
//		
//	}
	
	public static void main(String[] args) {
		
		//java PercolationStats size tests
		//java PercolationStats 10 100
		PercolationStats PS = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		//PercolationVisualizer.draw(PS.perc[0], Integer.parseInt(args[0]));
		
		double threshold = PS.mean()/(PS.N*PS.N);
		System.out.print("Threshold is: " + threshold + "\n");
		
		
	}

}
