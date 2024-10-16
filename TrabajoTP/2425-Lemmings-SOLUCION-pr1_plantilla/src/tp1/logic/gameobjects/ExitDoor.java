package tp1.logic.gameobjects;

import tp1.logic.*;

import tp1.logic.Position;

public class ExitDoor {

	private Position pos; 
	
	public ExitDoor (Position pos){
		this.pos = pos;
	}

	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
	}
}
