package tp1.logic;

import tp1.logic.gameobjects.Lemming; // ns si esta bien 
import java.util.ArrayList; // ns si esta bien 
import java.util.List;// ns si esta bien 


public class GameObjectContainer {

	private List<Lemming> lemmings;
	 
	public GameObjectContainer() {
		
		this.lemmings = new ArrayList<>();
	}
	public void add(Lemming l) {
		lemmings.add(l);
	}
    public void update() {
		
	}
    public String positionToString(Position p) {
    	p.getCol();
    	p.getRow();
    	return " ";
    }
    private void removeDead() {
    	lemmings.removeIf(lemming -> !lemming.isAlive()); 
    }
    public boolean isSolid(Position p) {
    	
    	return false;
    }
    public boolean isExit(Position p) {
    	
    	return false;
    }
	
	
}
