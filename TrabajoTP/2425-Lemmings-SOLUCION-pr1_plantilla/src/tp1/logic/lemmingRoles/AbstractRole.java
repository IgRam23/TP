 package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.Position;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;

public abstract class AbstractRole implements LemmingRole {
	
	protected Position pos;

    public boolean receiveInteraction(GameItem other, Lemming lemming) {
        return other.interactWith(lemming);     
    }

    public boolean interactWith(Lemming receiver, Lemming owner) {
        return false; // Por defecto, no hay interacción
    }

    public boolean interactWith(Wall wall, Lemming owner) {
        return false; // Por defecto, no interactúa con paredes
    }

    public boolean interactWith(ExitDoor door, Lemming owner) {
        return false; // Por defecto, no interactúa con puertas
    }
    
    
    @Override
    //Mueve al lemming
	public Position move(Direction dir) {       
		int newCol = pos.getCol() + dir.getX(); 
        int newRow = pos.getRow() + dir.getY(); 
        pos = new Position(newCol, newRow); 
        return pos; 
    }
	
 
    
    @Override
	//Revisa la caida del lemming, y si es > MAX_FALL, el lemming muere
	public void handleFall(Lemming lemming) {
		int MAX_FALL = 2;
        if (lemming.getFallDistance() > MAX_FALL) {
            lemming.die(); 
        } 
	}
    
    @Override
    public String getRoleType() {
    	return "WalkerRole";
    }
}

