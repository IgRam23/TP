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
	
	//Va manejando al lemming
	public void play(Lemming lemming) { 
		lemming.walkOrFall();          
	}
	
	//Mueve al lemming
	public Position move(Direction dir) {
		int newCol = position.getCol() + dir.getY(); 
        int newRow = position.getRow() + dir.getX(); 
        position = new Position(newCol, newRow); 
        return position; 
    }
	
	//Revisa la caida del lemming, y si es > MAX_FALL, el lemming muere
	public void handleFall(Lemming lemming) {
	        if (lemming.getFallDistance() > MAX_FALL) {
	            lemming.die(); 
	        } 
	}
	
}
