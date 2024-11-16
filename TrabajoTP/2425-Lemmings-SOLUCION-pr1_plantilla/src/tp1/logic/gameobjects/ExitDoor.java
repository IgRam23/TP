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
        return Messages.EXIT_DOOR; //bhb
    }
    
    @Override
    public void update() {
    }
    
    @Override
    public boolean isSolid() {
    	return false;
    }
    
    @Override
    public boolean isExit() {
    	return true;
    }
    
    @Override
    public boolean isLemming() {
    	return false;
    }
}
