package tp1.logic.lemmingRoles;

import tp1.logic.Direction;
import tp1.logic.Position;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class DownCaverRole extends AbstractRole {
	
	private static final String NAME = "DownCaver";
	private static final String HELP = "[D]own [C]aver: Lemming caves downwards";
	
	private boolean hasCaved;

	/*public DownCaverRole(Position pos) {
		this.pos = pos;
	}*/
	
    @Override
    public void start(Lemming lemming) {
        if(lemming.isInAir()) {
        	lemming.disableRole();
        }
    }    
    
    @Override
    public void play(Lemming lemming) {
    //	Position below = lemming.move(Direction.DOWN );

    	if(!lemming.isInAir() && hasCaved){
    		lemming.fall(); // Si la pared es dura, vuelve a ser WalkerRole}
            lemming.move(lemming.getDirection());
    		hasCaved = false;
    	}
    	else { 
    		lemming.disableRole();
    		lemming.update();
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
        return new DownCaverRole(); 
    }

	@Override
	public boolean interactWith(Wall wall, Lemming lemming) {
		
		if(lemming.isInPosition(wall.getPos().up())) {
			wall.killWall();
			hasCaved = true;
			//lemming.move(Direction.DOWN);
			return true;
		}
		
		return false;
		
	}

	@Override
	public String helpText() {
		return HELP;
	}
	
	
	
}
