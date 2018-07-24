package model;


public class Game{

	private int n=45,m=35;
	private Cell[][] grid;
	
	public Game(){
	}

	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}


	public void nextStep(){
		neighbours();
		
        for (int k = 1; k < n-1; k++)
        {
            for (int l = 1; l < m-1; l++)
            {
                //komorka umiera z samotnosci
                if ((grid[k][l].getState()) && (grid[k][l].getAliveNeighbours() < 2))
                    grid[k][l].setState(false);
 
                //komorka umiera z nat³oku
                else if ((grid[k][l].getState()) && (grid[k][l].getAliveNeighbours() > 3))
                	grid[k][l].setState(false);
                
                //komorka rodzi sie
                else if (!(grid[k][l].getState()) && (grid[k][l].getAliveNeighbours() == 3))
                	grid[k][l].setState(true);
    
            }
        }
	}
	
	public void neighbours(){
		int aliveNeighbours;
		
		for (int k = 1; k < n-1; k++)
        {
            for (int l = 1; l < m-1; l++)
            {
                aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        aliveNeighbours += grid[k + i][l + j].getStateNumber();
 
                aliveNeighbours -= grid[k][l].getStateNumber();
                grid[k][l].setAliveNeighbours(aliveNeighbours);
            }
        }
	}
	
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}
}
