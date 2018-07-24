package model;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Rectangle{

	private boolean state;
	private int stateNumber;
	private int aliveNeighbours;
	
	public int getAliveNeighbours() {
		return aliveNeighbours;
	}

	public void setAliveNeighbours(int aliveNeighbours) {
		this.aliveNeighbours = aliveNeighbours;
	}

	public Cell(){
		this.state=false;
		this.stateNumber=0;
		setWidth(15); 
	    setHeight(15);  
	    setFill(Color.WHITE);
	    
	    this.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                setFill(Color.BLACK);
                setState(true);
                stateNumber=1;
            }
        });
	}
	
	public void setState(boolean status){
		this.state=status;
		if(this.state==true){
			setFill(Color.BLACK);
			stateNumber=1;
		}else{
			setFill(Color.WHITE);
			stateNumber=0;
		}
		
	}
	
	public boolean getState(){
		return this.state;
	}
	
	public int getStateNumber(){
		return this.stateNumber;
	}
}
