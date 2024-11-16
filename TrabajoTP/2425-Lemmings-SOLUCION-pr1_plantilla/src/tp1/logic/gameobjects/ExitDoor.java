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
    
    @Override
    public boolean interactWith(Wall wall) {
        // Define la interacción con un muro si es necesario (por ejemplo, un muro bloqueando la puerta)
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door) {
        // La puerta de salida no interactúa con otras puertas de salida
        return false;
    }

    @Override
    public boolean interactWith(Lemming lemming) {
        // Definir la lógica de interacción con un lemming si es necesario
        return false;
    }

    @Override
    public boolean receiveInteraction(GameItem other) {
        // La puerta de salida no realiza ninguna acción al recibir una interacción
        return false;
    }
}
    
    
    
    

