package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.view.Messages;

public class MetalWall extends Wall {

	public MetalWall (GameWorld game, Position pos){
		super(game, pos);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

    @Override
    public String getIcon() {
        return Messages.METALWALL; // Representación de la pared
    }

    @Override 
    public void update() {
    	
    }
    
    public boolean receiveInteraction(GameItem other) {
        return false; 
    }
	
    @Override
    public boolean interactWith(ExitDoor door) {
        // Definir la lógica de interacción con una puerta si es necesario
        return false;
    }
    @Override
    public boolean interactWith(Wall wall) {
        // Definir la lógica de interacción con una puerta si es necesario
        return false;
    }
    
    public boolean interactWith(Lemming lemming) {
        // Definir la lógica de interacción con una puerta si es necesario
        return false;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
}
