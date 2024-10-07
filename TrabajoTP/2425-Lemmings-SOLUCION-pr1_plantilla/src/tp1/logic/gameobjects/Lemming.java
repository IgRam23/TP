package tp1.logic.gameobjects;

import tp1.logic.*; //con el asterisco me mete las carpetas enteras
import tp1.logic.lemmingRoles.WalkerRole;

public class Lemming {
	
	//TODO fill your code
	
	private Position pos; 
	private Direction dir;
	private boolean isAlive;
	private WalkerRole rol;
	private Game game;
	
    public Lemming(Game game, Position pos){
    	this.game = game;
        this.pos = pos;
        this.dir = Direction.NONE; // Inicialmente, no se mueve
        this.isAlive = true; // Inicialmente, el lemming está vivo
        this.rol = new WalkerRole();
	}
	/**
	 *  Implements the automatic update	
	 */
	public void update() {
		//TODO fill your code
		
		
	}
	public void dead() {
		isAlive = false;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public boolean isInAir() {
		//TODO fill your code
		return false;
	}
	public void walkOrFall() {
		//TODO fill your code
	}
	public boolean isInPosition(Position pos) {
		
		return this.pos == pos;
	}
	public String getIcon() {
		//TODO fill your code
		return " ";
	}
	public String toString() {
		//TODO fill your code
		return " ";
	}
	
}
