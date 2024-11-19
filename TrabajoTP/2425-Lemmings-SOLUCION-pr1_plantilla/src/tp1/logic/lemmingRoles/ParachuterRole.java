package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.Position;
import tp1.logic.gameobjects.*;  
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole {  
	
	public ParachuterRole(Position pos) {
		this.pos = pos;
	}
	
	
    @Override
    public void start(Lemming lemming) {
        if (!lemming.isInAir()) {
            lemming.disableRole(); // Si no está en el aire, vuelve a ser caminante
        }	
    }

    @Override
    public void play(Lemming lemming) {
        if (lemming.isInAir()) {
            lemming.fall(); 
            lemming.changeFall(0);
            lemming.move(lemming.getDirection());
        } else {
            lemming.disableRole(); // Al tocar el suelo, vuelve a ser caminante
        }
    }

    @Override
    public String getIcon(Lemming lemming) {
        return Messages.LEMMING_PARACHUTE; // Icono de paracaidista
    }
    
    
    @Override
    public String getRoleType() {
    	return "Parachuter";
    }
    

	
    @Override
    public boolean canParse(String input) {
        return input.equalsIgnoreCase("Parachuter") || input.equalsIgnoreCase("P");
    }
    
    
	//reviasr esto
	@Override
    public LemmingRole createInstance(Position position) {
        return new ParachuterRole(position); 
    }

 
   
    
    
}
