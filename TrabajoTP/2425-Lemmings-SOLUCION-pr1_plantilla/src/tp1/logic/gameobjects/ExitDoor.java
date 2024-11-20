package tp1.logic.gameobjects;

import tp1.logic.*;

import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	
	//Constructor
	public ExitDoor(GameWorld game, Position pos, GameObjectContainer container){
		super(game, pos);
		
	}

    @Override
    public String getIcon() {
        return Messages.EXIT_DOOR; 
    }
    
    @Override
    public void update() {
    }
    
    @Override
    public boolean isExit() {
    	return true;
    }
    
    @Override
    public boolean interactWith(Wall wall) {
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door) {
        // La puerta de salida no interactúa con otras puertas de salida
        return false;
    }

    @Override
    public boolean interactWith(Lemming lemming) {
        return lemming.interactWith(this); // No hubo interacción
    }
    
    @Override
    public boolean receiveInteraction(GameItem other) {
        return other.interactWith(this); 
    }
}
    
    
    
    

