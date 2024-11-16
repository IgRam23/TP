package tp1.logic.gameobjects;
import tp1.logic.*;
import tp1.view.Messages;

public class Wall extends GameObject{ 
	//Constructor
	public Wall (GameWorld game, Position pos){
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
    
    @Override
    public boolean isLemming() {
    	return false;
    }
    
    public boolean interactWith(Wall wall) {
        // Los muros no interactúan con otros muros, por lo que devolveremos false.
        return false;
    }

    @Override
    public boolean interactWith(ExitDoor door) {
        // Definir la lógica de interacción con una puerta si es necesario
        return false;
    }

    @Override
    public boolean interactWith(Lemming lemming) {
        // Definir la lógica de interacción con un lemming si es necesario
        return false;
    
    }
    public boolean receiveInteraction(GameItem other) {
        // En el caso de la pared, generalmente no se realiza ninguna interacción
        // con otros objetos, así que simplemente devolvemos false.
        return false;
    }
    
    
}