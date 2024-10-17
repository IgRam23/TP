package tp1.logic.gameobjects;
import tp1.logic.*;

public class Wall {

	private Position pos;
	
	//Constructor
	public Wall (Position pos){
		this.pos = pos;
	}
	
	//Verifica si hay una pared en la posicion indicada
	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
	}
	
}