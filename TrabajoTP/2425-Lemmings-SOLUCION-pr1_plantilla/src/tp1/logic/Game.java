package tp1.logic;

import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.logic.gameobjects.ExitDoor;

public class Game {

	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;

    private GameObjectContainer container;
	private int currentCycle;					
	private int numLemmings;
	private int remaining;
	private int lemmingsToWin;
	private int lemmingsDead;
	private int lemmingsExit;
	private boolean doExit;
	private boolean finished;
	private int nivel;

	public Game() {

		initGame2();
	}
	
	private void initGame1() {

		container = new GameObjectContainer();
		numLemmings=0;

		container.add(new Lemming(this, new Position(3,3)));
		container.add(new Lemming(this, new Position(2,3)));
		numLemmings = 2;

		container.add(new Wall(new Position(2,4)));
		container.add(new Wall(new Position(3,4)));
		container.add(new Wall(new Position(4,4)));
		container.add(new Wall(new Position(4,6)));
		container.add(new Wall(new Position(5,6)));
		container.add(new Wall(new Position(6,6)));
		container.add(new Wall(new Position(7,6)));
		container.add(new Wall(new Position(7,5)));
		container.add(new ExitDoor(new Position(4,5)));

		lemmingsToWin = 1;
		remaining = numLemmings;

	}
	
	
	private void initGame2() {

		container = new GameObjectContainer();
		numLemmings=0;

		container.add(new Lemming(this, new Position(4,8)));
		container.add(new Lemming(this, new Position(5,0)));
		container.add(new Lemming(this, new Position(6,0)));
		container.add(new Lemming(this, new Position(7,0)));
		numLemmings = 4;

		container.add(new Wall(new Position(2,1)));
		container.add(new Wall(new Position(3,1)));
		container.add(new Wall(new Position(4,1)));
		container.add(new Wall(new Position(5,1)));
		container.add(new Wall(new Position(6,1)));
		container.add(new Wall(new Position(7,1)));
		container.add(new Wall(new Position(5,3)));
		container.add(new Wall(new Position(6,3)));
		container.add(new Wall(new Position(7,3)));
		container.add(new Wall(new Position(3,9)));
		container.add(new Wall(new Position(4,9)));
		container.add(new Wall(new Position(5,9)));
		container.add(new Wall(new Position(6,9)));
		container.add(new Wall(new Position(7,9)));
		container.add(new Wall(new Position(3,8)));
		container.add(new ExitDoor(new Position(7,8)));

		lemmingsToWin = 2;
		remaining = numLemmings;
	}
	
	private void initGame3() {

		container = new GameObjectContainer();
		container.add(new Lemming(this, new Position(4,1)));
		container.add(new Lemming(this, new Position(5,0)));
		container.add(new Lemming(this, new Position(5,5)));
		container.add(new Lemming(this, new Position(4,9)));

		numLemmings = 4;

		container.add(new Wall(new Position(5,1)));
		container.add(new Wall(new Position(5,2)));
		container.add(new Wall(new Position(7,1)));
		container.add(new Wall(new Position(5,3)));
		container.add(new Wall(new Position(7,2)));
		container.add(new Wall(new Position(7,3)));
		container.add(new Wall(new Position(7,0)));
		container.add(new Wall(new Position(6,3)));
		container.add(new Wall(new Position(6,4)));
		container.add(new Wall(new Position(5,9)));
		container.add(new Wall(new Position(9,4)));
		container.add(new Wall(new Position(7,7)));
		container.add(new Wall(new Position(7,6)));
		container.add(new Wall(new Position(7,5)));
		container.add(new Wall(new Position(7,8)));
		container.add(new ExitDoor(new Position(6,6)));

		lemmingsToWin = 3;
		remaining = numLemmings; 

	}
	
	//Gestiona los niveles
	public Game(int nLevel) {
		nivel = nLevel;
		if(nivel == 1) {
	    	initGame1();
	    } else if(nivel == 2) {
		    initGame2();  
	    } else if(nivel == 3){
	    	initGame3(); 
	    } else {
	    	initGame1();
	    }
	}
	
	//Resetea el juego
	public void reset() { 
	    currentCycle = 0;
	    lemmingsDead = 0;
	    lemmingsExit = 0;
	    numLemmings = 0;
	    finished = false;
	    
	    if(nivel == 1) {
	    	initGame1();
	    } else if(nivel == 2) {
		    initGame2(); 
	    } else if(nivel == 3){
	    	initGame3();
	    }else {
	    	initGame1(); //vamos a dejar este por defecto
	    }
    }

	//Actualiza el juego
	public void Update() { 
		if (!isFinished()) {
	        container.update(); 
	    }
	}
	
	//Comprueba y devuelve un booleano segun si es una pared 
	public boolean isSolid(Position pos) {
	    return container.isSolid(pos);
	}
	
	//Comprueba y devuelve un booleano segun si es una puerta de salida
	public boolean isExit(Position pos) {
	    return container.isExit(pos);
	}
	
	//Añade un lemming
	public void addLemming(Lemming lemming) {
	    container.add(lemming); 
	}
	
	//Devuelve el ciclo actual
	public int getCycle() {
		return this.currentCycle;
	}
	
	//Aumenta el ciclo del juego
	public void nextCycle() {
		this.currentCycle++; 
	}

	//Devuelve el numero de lemmings que siguen en la partida
	public int numLemmingsInBoard() {
		return this.remaining;
	}

	//Devuelve el numero de lemmings muertos
	public int numLemmingsDead() {
		return this.lemmingsDead; 
	}
	
	//Aumenta el numero de lemmings muertos
	public void incrementDeadLemmings() {
	    this.lemmingsDead++;  // Incrementa el número de lemmings muertos
	    this.remaining--; //quita lemmings remainings
	}

    //Devuelve el numero de lemmings que ha salido
	public int numLemmingsExit() {
		return lemmingsExit; 
	}
	
	//Aumenta el numero de lemmings que salen por la puerta y resta uno al numero de lemmings que quedan en la partida
	public void incrementLemmingsExit() {
		this.lemmingsExit++;
		this.remaining--;
	}
	
    //Devuelve el numero de lemmings necesarios para ganar
	public int numLemmingsToWin() {
		return this.lemmingsToWin;
	}
	
	//Llama a la funcion que devuelve los elementos como string
	public String positionToString(int col, int row) { 
		Position pos = new Position(row,col);
		return container.positionToString(pos);
	}
	
    //Devuelve true si el jugador ha ganado
	public boolean playerWins() {
		return numLemmingsExit() >= numLemmingsToWin(); 
	}
	
    //Devuelve true si el jugador ha perdido
	public boolean playerLooses() {
		return remaining == 0 && numLemmingsExit() < numLemmingsToWin(); 
	}
	
	//Aumenta el numero de lemmings muertos
	public void lemmingHasDied() {
        incrementDeadLemmings();  
    }
	
	//Devuelve true si el juego ha terminado
	public boolean isFinished() {
		return this.finished;
	}
	
	//Pone el estado del juego a -> terminado (usado por exit)
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    
    public String help() {
		return "";
	}

}