package tp1.logic.gameobjects;

import tp1.logic.*;

import tp1.logic.Position;

public class ExitDoor {

	private Position pos; 
	
	//Constructor
	public ExitDoor (Position pos){
		this.pos = pos;
	}
	
	//Comprueba si hay una puerta en una posicion especificada
	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
	}
}
