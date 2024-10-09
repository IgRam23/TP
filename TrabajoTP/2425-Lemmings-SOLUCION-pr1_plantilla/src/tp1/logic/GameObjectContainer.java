package tp1.logic;

import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;
import tp1.logic.gameobjects.*;

import java.util.ArrayList; 


public class GameObjectContainer {

	private ArrayList<Lemming> lemmings;
	private ArrayList<Wall> walls;
	private ArrayList<ExitDoor> doors;; 
	 
	public GameObjectContainer() {
	
		this.lemmings = new ArrayList<>();
		this.walls = new ArrayList<>();
		this.doors = new ArrayList<>();
	}
	public void add(Lemming l) {
		lemmings.add(l);
	}
	public void add(Wall w) { //ns si esta bien
		walls.add(w);
	}
	public void add(ExitDoor d) { //ns si esta bien
		doors.add(d);
	}
	
    public void update() {
		
	}
    public String positionToString(Position p) {
    	if(isSolid(p)) {
    		return Messages.WALL;
    	} else if(isExit(p)) {
    		return Messages.EXIT_DOOR;
    	} else if(isLemming(p) != -1) {
    		Lemming l = Lem(p,isLemming(p));
    		return l.getIcon();
    	}else return " ";
    	
    }
    private void removeDead() {
    	lemmings.removeIf(lemming -> !lemming.isAlive()); 
    }
    public boolean isSolid(Position p) {
    	for(int i = 0; i < walls.size(); i++){
    		if(walls.get(i).isInPosition(p))
    			return true;
    	}
    	return false;
    	
    }
    public boolean isExit(Position p) {
    	for(int i = 0; i < doors.size(); i++){
    		if(doors.get(i).isInPosition(p))
    			return true;
    	}
    	
    	return false;
    }
    
    public int isLemming(Position p) {

    	for(int i = 0; i < lemmings.size(); i++) {
    		if(lemmings.get(i).isInPosition(p)) {
    			return i;
    		}
    	}
    	return -1;
 
    }
    public Lemming Lem(Position p,int i) {
    		return lemmings.get(i);
   
    }
    
   
    
	
	
}