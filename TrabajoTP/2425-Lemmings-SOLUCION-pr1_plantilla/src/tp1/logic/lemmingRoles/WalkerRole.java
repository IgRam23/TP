package tp1.logic.lemmingRoles;

import tp1.logic.Direction; 
import tp1.logic.Game;
import tp1.logic.Position;
import tp1.logic.gameobjects.*;
import tp1.view.Messages; 

public class WalkerRole extends AbstractRole {
	
	Position pos;
	
	private static final String NAME = Messages.WALKER_ROL_NAME;
	private static final String HELP = Messages.WALKER_ROL_HELP;
	private static final String ICON_RIGHT = Messages.LEMMING_RIGHT;
	private static final String ICON_LEFT = Messages.LEMMING_LEFT;
	
	public WalkerRole(Position pos) {
		this.pos = pos;
	}
	
	@Override
	public void start(Lemming lemming) {
		
	}
	  
	//Va manejando al lemming
	@Override
	public void play(Lemming lemming) {;	
		lemming.walkOrFall();
	}
	
	
	@Override
	public String getIcon(Lemming lemming) {
		if(lemming.getIcon() == Messages.LEMMING_RIGHT) {
			return ICON_RIGHT;
		} else if (lemming.getIcon() == Messages.LEMMING_LEFT) {
			return ICON_LEFT;
		} else return Messages.EMPTY;
	}

	private String getName() {
		return NAME;
	}

	public String getHelp() {
		return HELP;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	//estas dos ultimas funciones no se donde ponerlas asi que las he dejado aqui
	
	//Mueve al lemming
	public Position move(Direction dir) {       
		int newCol = pos.getCol() + dir.getX(); 
        int newRow = pos.getRow() + dir.getY(); 
        pos = new Position(newCol, newRow); 
        return pos;   
    }
	
	//Revisa la caida del lemming, y si es > MAX_FALL, el lemming muere
	public void handleFall(Lemming lemming) {
		int MAX_FALL = 2;
        if (lemming.getFallDistance() > MAX_FALL) {
            lemming.die(); 
        } 
	}
	
	
}
