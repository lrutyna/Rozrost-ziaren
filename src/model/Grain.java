package model;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grain extends Rectangle{
	private Program program;
	private boolean state;
	private int grainId;
	private Color color;
	private int i,j;
	
	public Grain(int i, int j){
		this.i=i;
		this.j=j;
		setWidth(5); 
	    setHeight(5);  
	    setColor(Color.WHITE);
	}
	
	public Grain(){
		this.state=false;
	}
	
	public int getGrainId() {
		return grainId;
	}

	public void setGrainId(int grainId) {
		this.grainId = grainId;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		setFill(color);
	}
	
	public void setState(boolean status){
		this.state=status;
		if(state){
			this.setOnMouseReleased(new EventHandler<MouseEvent>()
	        {
	            @Override
	            public void handle(MouseEvent t) {
	            }
	        });
		}else{
			this.setOnMouseReleased(new EventHandler<MouseEvent>()
	        {
	            @Override
	            public void handle(MouseEvent t) {
	                program.setGrainPosition(i, j);
	            }
	        });
		}
	}
	
	public boolean getState(){
		return this.state;
	}
	
	public void setProgram(Program program){
		this.program=program;
	}
	
}
