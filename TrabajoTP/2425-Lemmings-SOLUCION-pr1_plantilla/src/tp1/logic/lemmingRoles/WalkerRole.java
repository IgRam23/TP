package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.Position;

public class WalkerRole {

	private static final int MAX_FALL = 2;
	private Position position;
	
	 public WalkerRole(Position initialPosition) {
	        this.position = initialPosition;
	   }
	
	public void play() {
		// lemming.walkOrFall();
	}
	
	
	//El lemming se mueve hacia la izq
	public Position andaDerch() {
		Position newPosition = new Position(position.getCol() + 1, position.getRow());
		return newPosition;
    }

    //El lemming se mueve hacia la derecha 
    public Position andaIzq() {
    	Position newPosition = new Position(position.getCol() - 1, position.getRow());
		return newPosition;
    }
    
    //El lemming se cae
    public Position seCae() {
    	Position newPosition = new Position(position.getCol(), position.getRow() - 1);
		return newPosition;
    }

	
}
