package tp1.logic.gameobjects;

import tp1.logic.*;

import tp1.logic.Position;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	
	//Constructor
	public ExitDoor(Game game, Position pos, GameObjectContainer container){
		super(game, pos);
		
	}

    public boolean isExit() {
        return false; 
    }
   

    @Override
    public String getIcon() {
        return Messages.EXIT_DOOR; 
    }
}
