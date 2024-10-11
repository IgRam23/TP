package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.Position;
import tp1.logic.gameobjects.*; 

public class WalkerRole {

	private static final int MAX_FALL = 2;
	private Position position;
	
	 public WalkerRole(Position initialPosition) {
	        this.position = initialPosition;
	   }
	
	public void play(Lemming lemming) { 
		/*if (lemming.isFalling()) {
	        lemming.handleFall(); // Método para manejar la caída
	    } else if (lemming.isInAir()) {
	        lemming.fall(); // Método para caer
	    } else {
	        lemming.step(); // Método para avanzar o cambiar dirección
	    }*/ 
		lemming.walkOrFall();          
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
