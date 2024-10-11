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
        this.rol = new WalkerRole(pos);
	}
	
	public void update() {
		//walkOrFall(); he comentado esto porque nse si esta bien
		
		//esto tmpoco se si esta bien pero bueno aqui lo dejo
	    if (!isAlive) {
	        return; // Si el lemming está muerto, no se actualiza
	    }
	    
		// Delegar al rol para que ejecute la acción correspondiente
	    rol.play(this);       
		
	}
	
	//Muere el lemming :(
	public void dead() {
		isAlive = false;
	}
	
	// Verifica si el lemming esta vivo
	public boolean isAlive() {
		return isAlive;
	}
	
	// Verifica si el lemming esta en el aire
	public boolean isInAir() {
		//TODO fill your code
		Position pos_abajo = new Position(pos.getCol(), pos.getRow() + 1);
		//hay q verificar que debajo esta vacio
		
		
		return false;
	}
	
	public void walkOrFall() {
		
		if (dir == Direction.RIGHT) {
            // Actualiza la posición llamando a andaDerch() de WalkerRole
            pos = rol.andaDerch();
        } else if (dir == Direction.LEFT) {
            // Actualiza la posición llamando a andaIzq() de WalkerRole
            pos = rol.andaIzq();
        } else if (dir == Direction.DOWN) {
        	// Actualiza la posicion llamando a seCae() de WalkerRole
        	pos = rol.seCae();
        }
	}
	
	//Verifica si el lemming esta en pos 
	public boolean isInPosition(Position pos) {
		return this.pos.equals(pos);
	}
	
	//Dibuja el lemming
	public String getIcon() {
		if (this.dir == Direction.RIGHT)
			return Messages.LEMMING_RIGHT; 
		
		return Messages.LEMMING_LEFT;
	}
	
	public String toString() {
		
		
		return " ";
	}
	
}