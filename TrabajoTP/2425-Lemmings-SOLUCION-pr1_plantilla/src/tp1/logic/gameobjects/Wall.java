package tp1.logic.gameobjects;
import tp1.logic.*;
import tp1.view.Messages;

public class Wall extends GameObject{ 
	//Constructor
	public Wall (Game game, Position pos){
		super(game, pos);
	}

    @Override
    public String getIcon() {
        return Messages.WALL; // Representación de la pared
    }
    
    @Override
    public void update() {
    }
    
    @Override
    public boolean isSolid() {
    	return true;
    }
    
    @Override
    public boolean isExit() {
    	return false;
    }
}