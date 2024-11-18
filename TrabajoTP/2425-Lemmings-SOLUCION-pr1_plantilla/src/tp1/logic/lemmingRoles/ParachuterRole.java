package tp1.logic.lemmingRoles;

import tp1.logic.Position;
import tp1.logic.gameobjects.*;  
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole {  
	
	Position pos;
	
	public ParachuterRole(Position pos) {
		this.pos = pos;
	}
	
	
    @Override
    public void start(Lemming lemming) {
        if (!lemming.isInAir()) {
            lemming.setRole(new WalkerRole(lemming.getPosition())); // Si no está en el aire, vuelve a ser caminante
        }	
    }

    @Override
    public void play(Lemming lemming) {
        if (lemming.isInAir()) {
            fallSlowly(); // Caída lenta sin morir
        } else {
            lemming.setRole(new WalkerRole(lemming.getPosition())); // Al tocar el suelo, vuelve a ser caminante
        }
    }

    @Override
    public String getIcon(Lemming lemming) {
        return Messages.LEMMING_PARACHUTE; // Icono de paracaidista
    }
    
    
    public void fallSlowly() {
    	
	}
	
    @Override
    public boolean canParse(String input) {
        return input.equalsIgnoreCase("Parachuter") || input.equalsIgnoreCase("P");
    }
	
	@Override
    public LemmingRole createInstance(Position position) {
        return new ParachuterRole(position); 
    }

    @Override 
    public void handleFall(Lemming lemming) {
    	//No muere por caer asi que no hace nada
    }

    
}
