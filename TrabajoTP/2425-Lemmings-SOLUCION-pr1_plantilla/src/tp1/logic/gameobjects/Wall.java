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
    public boolean interactWith(Wall wall){
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
    	
    	
    	if (lemming.isInPosition(this.pos)) {
           
    		this.isAlive = false;
    		/*lemming.changePreviousDir(lemming.getDirection());
            lemming.changeDir(lemming.getDirection() == Direction.RIGHT ? Direction.LEFT : Direction.RIGHT);
            return true; // Interacción procesada*/return true;
        }
        return false; // No hubo interacción
    
    }
    
    public boolean receiveInteraction(GameItem other) {
        return other.interactWith(this); 
    }
    
    
    public void killWall(){
    	
    	this.isAlive = false;
    }
    
    public Position getPos() {
    	return this.pos;
    }
    
    
    
}