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

	public Game() {

		initGame2();
	}
	
	
    
	private void initGame2() {

		container = new GameObjectContainer();
		numLemmings=0;
		container.add(new Lemming(this, new Position(4,1)));
		container.add(new Lemming(this, new Position(5,0)));
		container.add(new Lemming(this, new Position(6,0)));
		container.add(new Lemming(this, new Position(4,9)));

		numLemmings = 4;

		//container.add(new Wall(new Position(2,1)));
		//container.add(new Wall(new Position(3,1)));
		//container.add(new Wall(new Position(4,1)));
		container.add(new Wall(new Position(5,1)));
		container.add(new Wall(new Position(5,2)));
		container.add(new Wall(new Position(7,1)));
		container.add(new Wall(new Position(5,3)));
		container.add(new Wall(new Position(7,2)));
		container.add(new Wall(new Position(7,3)));
		container.add(new Wall(new Position(7,0)));
		container.add(new Wall(new Position(6,3)));
		container.add(new Wall(new Position(5,9)));
		container.add(new Wall(new Position(9,4)));
		container.add(new Wall(new Position(7,7)));
		container.add(new Wall(new Position(7,6)));
		container.add(new Wall(new Position(7,5)));
		container.add(new Wall(new Position(7,8)));
		container.add(new ExitDoor(new Position(6,6)));

		remaining = numLemmings; //inicializar remaining a los lemmings con los que empiezas
		lemmingsToWin = 2;

	}

        
	public Game(int nLevel) {
		
	}
	
	
	//resetea el juego
	public void reset() {
		container = new GameObjectContainer();
	    currentCycle = 0;
	    lemmingsDead = 0;
	    numLemmings = 0;
	    finished = false;
	    initGame2();  
    }

	
	//Actualiza el juego
	public void Update() {
		if (!isFinished()) {
	        container.update(); 
	        nextCycle(); 
	        if (playerWins() || playerLooses()) {
	            finished = true; 
	        }
	    }
	}
	
	public boolean isSolid(Position pos) {
	    return container.isSolid(pos);
	}

	public boolean isExit(Position pos) {
	    return container.isExit(pos);
	}
	
	
	public void addLemming(Lemming lemming) {
	    container.add(lemming); // Permite añadir lemmings sin exponer el contenedor
	}

	public Lemming getLemmingAt(Position pos) { 
	    return container.getLemmingAt(pos); // Método que interactúa con el contenedor    
	}
	
	
	//Devuelve el ciclo actual
	public int getCycle() {
		return this.currentCycle;
	}
	
	//Incrementa el ciclo 
	public void nextCycle() {
		this.currentCycle++; 
	}

	//Devuelve el num de lemmings
	public int numLemmingsInBoard() {
		return this.remaining;
	}

	//Devuelve el num de lemmings muertos
	public int numLemmingsDead() {
		return this.lemmingsDead; 
	}
	//Incrementa el num de lemmings muertos
	public void incrementDeadLemmings() {
	    this.lemmingsDead++;  // Incrementa el número de lemmings muertos
	    this.remaining--; //quita lemmings remainings
	}

    //
	public int numLemmingsExit() {
		return lemmingsExit; 
	}
	public void incrementLemmingsExit() {
		this.lemmingsExit++;
		this.remaining--;
	}
	
    //Devuelve el num de lemmings necarios para ganar
	public int numLemmingsToWin() {
		return this.lemmingsToWin;
	}

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
	
	public void lemmingHasDied() {
        incrementDeadLemmings();  // Incrementa el contador de lemmings muertos
    }


	public String help() {
		// TODO Auto-generated method stub
		return "";
	}
	
	//Devuelve true si el juego ha terminado
	public boolean isFinished() {
		return this.finished;
	}
	// Método para indicar que el juego ha terminado (usado por exit)
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}