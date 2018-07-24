package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.scene.paint.Color;

public class Neighbourhood {
	private int n=102,m=122;              //wymiay siatki
	private int i,j,k,l;
	private int tmpId;              
	private int neighbourhoodCase;        //które s¹siedztwo jest ustawione
	private Grain[][] grid;
	private Grain[] gridRow;
	private Grain[] gridColumn;
	private Color colors[][];
	private boolean states[][];
	private int grainsIds[][];
	private List<Color>colorsList=new ArrayList<Color>();
	private Color c;
	private int frequency;
	private Random generator = new Random();
	
	public Grain[][] getGrid() {
		return grid;
	}

	public void setGrid(Grain[][] grid) {
		this.grid = grid;
		gridRow=new Grain[n];
		gridColumn=new Grain[m];
		colors = new Color[n][m];
		states = new boolean[n][m];
		grainsIds = new int[n][m];
	}
	
	public void initTables(){
		for(i=1;i<n-1;i++){
			  for(j=1;j<m-1;j++){
				  colors[i][j]=grid[i][j].getColor();
				  states[i][j]=grid[i][j].getState();
				  grainsIds[i][j]=grid[i][j].getGrainId();
			  }
		 }
	}
	
	public void initRowColumn(){
		for(i=0;i<n;i++){
			  gridRow[i]=new Grain();
		 }
		for(j=0;j<m;j++){
			gridColumn[j]=new Grain();
		  }
	}
	
	public void nextStep(){
		
		for(i=1;i<n-1;i++){
			  for(j=1;j<m-1;j++){
				  if(!grid[i][j].getState()){
					  switch (neighbourhoodCase) {
					  case 1:
						  vonNeumann(i,j);
						  break;
					  case 2:
						  Moore(i,j);
						  break;
					  case 3:
						  heksagonalLeft(i,j);
						  break;
					  case 4:
						  heksagonalRight(i,j);
						  break;
					  case 5:
						  if(generator.nextBoolean())
                              heksagonalRight(i, j);
                          else
                              heksagonalLeft(i, j);
						  break;
					  case 6:
						  if(generator.nextInt(4)==0)
                              pentagonalLeft(i, j);
                          else if (generator.nextInt(4)==1)
                              pentagonalRight(i, j); 
                          else if (generator.nextInt(4)==2)
                              pentagonalBottom(i, j);
                          else if (generator.nextInt(4)==3)
                              pentagonalUp(i, j); 
						  break;
					  }
				  }
			  }
		  }
		for(i=1;i<n-1;i++){
			  for(j=1;j<m-1;j++){
				  if(!grid[i][j].getState()){
					  grid[i][j].setColor(colors[i][j]);
					  grid[i][j].setState(states[i][j]);
					  grid[i][j].setGrainId(grainsIds[i][j]);
				  }
			  }
		 }
	}
	
	public Color majorNeighbour(){
		frequency=0;
		for(Color el: colorsList){
        	if(frequency<Collections.frequency(colorsList, el)){
        		frequency=Collections.frequency(colorsList, el);
        		c=el;
        	}
        }
		colorsList.removeAll(colorsList);
		frequency=0;
		return c;
	}
	
	public int findIdByColor(Color c){
		for(k=1;k<n-1;k++){
			  for(l=1;l<m-1;l++){
				  if(grid[k][l].getColor()==c){
					  tmpId = grid[k][l].getGrainId();
				  }
			  }
		}
		return tmpId;
	}
	

	public void vonNeumann(int i, int j){
		if(grid[i-1][j].getState())
        {
            states[i][j]=true;
            colorsList.add(grid[i-1][j].getColor());
        }
        if(grid[i][j-1].getState())
        {
        	states[i][j]=true;
			colorsList.add(grid[i][j-1].getColor());
        }
        if(grid[i][j+1].getState())
        {
            states[i][j]=true;
            colorsList.add(grid[i][j+1].getColor());
        }
        if(grid[i+1][j].getState())
        {
            states[i][j]=true;
            colorsList.add(grid[i+1][j].getColor());
        }
        if(states[i][j]){
        	colors[i][j]=majorNeighbour();
        	grainsIds[i][j]=findIdByColor(colors[i][j]);
        	c=null;
        }
	}
	
	public void Moore(int i, int j){
		
		if(grid[i-1][j-1].getState())
        {
			states[i][j]=true;
			colorsList.add(grid[i-1][j-1].getColor());
        }
        
        if(grid[i-1][j].getState())
        {  
        	states[i][j]=true;
			colorsList.add(colors[i][j]=grid[i-1][j].getColor());
        }
        
        if(grid[i-1][j+1].getState())
        {   
        	states[i][j]=true; 
			colorsList.add(grid[i-1][j+1].getColor());
        }
        
        if(grid[i][j-1].getState())
        {
        	states[i][j]=true;
			colorsList.add(grid[i][j-1].getColor());
        }
        
        if(grid[i][j+1].getState())
        {
        	states[i][j]=true;
			colorsList.add(grid[i][j+1].getColor());
        }
        
        if(grid[i+1][j-1].getState())
        {
        	states[i][j]=true;
			colorsList.add(grid[i+1][j-1].getColor());
        }
        
        if(grid[i+1][j].getState())
        {
        	states[i][j]=true;
			colorsList.add(grid[i+1][j].getColor());
        }
        
        if(grid[i+1][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j+1].getColor());
        }
        if(states[i][j]){
        	colors[i][j]=majorNeighbour();
        	grainsIds[i][j]=findIdByColor(colors[i][j]);
        	c=null;
        }
	}
	
	
	public void heksagonalLeft(int i,int j){
		if(grid[i-1][j-1].getState())
        {
			states[i][j]=true;
			colorsList.add(grid[i-1][j-1].getColor());
        }
        if(grid[i-1][j].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i-1][j].getColor());  
        }
        if(grid[i][j-1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i][j-1].getColor());
        }
        if(grid[i][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i][j+1].getColor());
        }
        if(grid[i+1][j].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j].getColor());
        }
        if(grid[i+1][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j+1].getColor());
        }
		if(states[i][j]){
        	colors[i][j]=majorNeighbour();
        	grainsIds[i][j]=findIdByColor(colors[i][j]);
        	c=null;
        }
	}
	
	public void heksagonalRight(int i,int j){
		if(grid[i-1][j].getState())
        {
			states[i][j]=true;
			colorsList.add(grid[i-1][j].getColor());  
        }
        if(grid[i-1][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i-1][j+1].getColor());
        }
        if(grid[i][j-1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i][j-1].getColor());
        }
        if(grid[i][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i][j+1].getColor());
        }
        if(grid[i+1][j-1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j-1].getColor());
        }
        if(grid[i+1][j].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j].getColor());
        }
		if(states[i][j]){
        	colors[i][j]=majorNeighbour();
        	grainsIds[i][j]=findIdByColor(colors[i][j]);
        	c=null;
        }
	}
	
	public void pentagonalRight(int i,int j){
		if(grid[i-1][j].getState())
        {
			states[i][j]=true;
            colorsList.add(grid[i-1][j].getColor());  
        }
        if(grid[i-1][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i-1][j+1].getColor());
        }
        if(grid[i][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i][j+1].getColor());
        }
        if(grid[i+1][j].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j].getColor());
        }
        if(grid[i+1][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j+1].getColor());
        }
        if(states[i][j]){
        	colors[i][j]=majorNeighbour();
        	grainsIds[i][j]=findIdByColor(colors[i][j]);
        	c=null;
        }
	}
	
	public void pentagonalLeft(int i, int j){
		if(grid[i-1][j-1].getState())
        {
			states[i][j]=true;
			colorsList.add(grid[i-1][j-1].getColor());
        }
        if(grid[i-1][j].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i-1][j].getColor());  
        }
        if(grid[i][j-1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i][j-1].getColor());
        }
        if(grid[i+1][j-1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j-1].getColor());
        }
        if(grid[i+1][j].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j].getColor());
        }
        if(states[i][j]){
        	colors[i][j]=majorNeighbour();
        	grainsIds[i][j]=findIdByColor(colors[i][j]);
        	c=null;
        }
	}
	
	public void pentagonalBottom(int i, int j){
		if(grid[i][j-1].getState())
        {
			states[i][j]=true;
			colorsList.add(grid[i][j-1].getColor());
        }
        
        if(grid[i][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i][j+1].getColor());
        }
        
        if(grid[i+1][j-1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j-1].getColor());
        }
        
        if(grid[i+1][j].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j].getColor());
        }
        
        if(grid[i+1][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i+1][j+1].getColor());
        }
		if(states[i][j]){
        	colors[i][j]=majorNeighbour();
        	grainsIds[i][j]=findIdByColor(colors[i][j]);
        	c=null;
        }
	}
	
	public void pentagonalUp(int i, int j){
		if(grid[i-1][j-1].getState())
        {
			states[i][j]=true;
			colorsList.add(grid[i-1][j-1].getColor());
        }
        
        if(grid[i-1][j].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i-1][j].getColor());  
        }
        
        if(grid[i-1][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i-1][j+1].getColor());
        }
        
        if(grid[i][j-1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i][j-1].getColor());
        }
        
        if(grid[i][j+1].getState())
        {
        	states[i][j]=true;
        	colorsList.add(grid[i][j+1].getColor());
        }
        if(states[i][j]){
        	colors[i][j]=majorNeighbour();
        	grainsIds[i][j]=findIdByColor(colors[i][j]);
        	c=null;
        }
	}
	
	public void setN(int n) {
		this.n = n;
	}

	public void setM(int m) {
		this.m = m;
	}
	
	public void setNeighbourhoodCase(int neighbourhoodCase) {
		this.neighbourhoodCase = neighbourhoodCase;
	}
	
	void periodicBoundaries(){
			for(j = 1; j < m-1; j++)
	            grid[0][j] = grid[n-2][j];
	        
	        for(int j = 1; j < m-1; j++)
	            grid[n-1][j] = grid[1][j];
	        
	        for(int i = 1; i < n-1; i++)
	            grid[i][0] = grid[i][m-2];
	        
	        for(int i = 1; i < n-1; i++)
	        	grid[i][m-1] = grid[i][1];
	        
	        grid[0][0] = grid[n-2][m-2];
	        grid[n-1][m-1] = grid[1][1];
	        grid[n-1][0] = grid[1][m-2];
	        grid[0][m-1] = grid[n-2][1]; 
    }
	
	public void noPeriodicBoundaries(){
		for(j = 1; j < m-1; j++)
            grid[0][j] = gridColumn[j];
        
        for(int j = 1; j < m-1; j++)
            grid[n-1][j] = gridColumn[j];
        
        for(int i = 1; i < n-1; i++)
            grid[i][0] = gridRow[i];
        
        for(int i = 1; i < n-1; i++)
        	grid[i][m-1] = gridRow[i];
        
        grid[0][0] = gridRow[0];
        grid[n-1][m-1] = gridColumn[m-1];
        grid[n-1][0] = gridColumn[0];
        grid[0][m-1] = gridRow[n-1];  
	}
	
	public void showIDs(){
		for(int i=1;i<n-1;i++){
			  for(int j=1;j<m-1;j++){
				  System.out.print(grid[i][j].getGrainId()+" ");
			  }
			  System.out.println();
		 }
		System.out.println();
	}
}
