package tp1.logic.lemmingRoles;

import tp1.logic.Position;
import tp1.logic.gameobjects.*;  
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole {  
	
	
	private static final String NAME = "Parachuter";
	private static final String HELP = "[P]arachuter: Lemming falls with a parachute";
		
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
            lemming.update();
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
	public String getName() {
		return NAME;
	}

    @Override
	public String getHelp() {
		return HELP;
	}
	
    @Override
    public boolean canParse(String input) {
        return input.equalsIgnoreCase("Parachuter") || input.equalsIgnoreCase("P");
    }

	@Override
    public LemmingRole createInstance(Position position) {
        return new ParachuterRole(); 
    }

	@Override
	public String helpText() {
		return HELP ;
	}


    
}
