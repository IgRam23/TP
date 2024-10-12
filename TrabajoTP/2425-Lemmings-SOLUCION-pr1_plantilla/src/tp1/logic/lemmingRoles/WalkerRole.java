package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.gameobjects.*; 

public class WalkerRole {

	private static final int MAX_FALL = 2;
	private Position position;
	
	 public WalkerRole(Position initialPosition) {
	        this.position = initialPosition;
	   }
	
	 
	//Realiza el movimiento del lemming
	public void play(Lemming lemming) { 
		lemming.walkOrFall();          
	}
	

	public Position move(Direction dir) {
        
		int newCol = position.getCol() + dir.getY(); // Sumar el valor de Y
        int newRow = position.getRow() + dir.getX(); // Sumar el valor de X
        position = new Position(newCol, newRow); // Actualiza la posición
        return position; // Devuelve la nueva posición
    }
	
	//Revisa la caida del lemming, y si es > MAX_FALL, el lemming muere
	public void handleFall(Lemming lemming) {
	        if (lemming.getFallDistance() > MAX_FALL) {
	            lemming.dead(); // Mata al lemming si cae demasiado
	        }
	      
	}
	
}
