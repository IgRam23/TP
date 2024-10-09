package tp1.logic.gameobjects;

import tp1.logic.*; //con el asterisco me mete las carpetas enteras

import tp1.view.Messages;

import tp1.logic.lemmingRoles.*;

public class Lemming {
	
	private Position pos; 
	private Direction dir;
	private boolean isAlive;
	private WalkerRole rol;
	private Game game;
	
    public Lemming(Game game, Position pos){
    	this.game = game;
        this.pos = pos;
        this.dir = Direction.RIGHT; // Inicialmente, no se mueve
        this.isAlive = true; // Inicialmente, el lemming está vivo
        this.rol = new WalkerRole();
	}
	
	public void update() {
		
		
		
		
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
		
		return this.pos.equals(pos);
	}
	public String getIcon() {
		if (this.dir == Direction.RIGHT)
			return Messages.LEMMING_RIGHT; 
		
		return Messages.LEMMING_LEFT;
	}
	public String toString() {
		//TODO fill your code
		return " ";
	}
	
}