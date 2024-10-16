package tp1.logic.gameobjects;
import tp1.logic.*;

public class Wall {

	private Position pos;
	
	public Wall (Position pos){
		this.pos = pos;
	}
	
	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
	}
	
}