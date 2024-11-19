package tp1.logic.lemmingRoles;

import tp1.logic.Position;
import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class DownCaverRole extends AbstractRole {
	
	public DownCaverRole(Position pos) {
		this.pos = pos;
	}
	
	
    @Override
    public void start(Lemming lemming) {
        if (!lemming.isInAir()) { //si hay pared dura paasa a ser walker role
            lemming.disableRole();
        }	
    }

    @Override
    public void play(Lemming lemming) {
        if (lemming.isInAir()) { //si no es dura
            lemming.fall(); 
            lemming.changeFall(0);
            lemming.move(lemming.getDirection());
        } else {
            lemming.disableRole(); // Al tocar el suelo, vuelve a ser caminante
        }
    }

    @Override
    public String getIcon(Lemming lemming) {
        return Messages.LEMMING_DOWN_CAVER; // Icono de minero
    }
    
    
    @Override
    public String getRoleType() {
    	return "DownCaver";
    }
    
    @Override
    public boolean canParse(String input) {
        return input.equalsIgnoreCase("DownCaver") || input.equalsIgnoreCase("DC");
    }
	
	@Override
    public LemmingRole createInstance(Position position) {
        return new DownCaverRole(position); 
    }

}
