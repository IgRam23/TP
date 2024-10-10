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
	private boolean doExit;
	private boolean finished;

	public Game() {

		initGame2();

	}
    
	private void initGame2() {

		container = new GameObjectContainer();
		numLemmings=0;
		container.add(new Lemming(this, new Position(4,0)));
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

		remaining = numLemmings - 1;

	}

        
	public Game(int nLevel) {
		// TODO Auto-generated constructor stu
		
		
	}

	public void Update() {
		nextCycle();
		container.update();
		
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
		return this.numLemmings;
	}

	public int numLemmingsDead() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numLemmingsExit() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numLemmingsToWin() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String positionToString(int col, int row) {
		
		Position pos = new Position(row,col);
		return container.positionToString(pos);
		
	}

	public boolean playerWins() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean playerLooses() {
		// TODO Auto-generated method stub
		return false;
	}

	public String help() {
		// TODO Auto-generated method stub
		return "";
	}
	
	public boolean isFinished() {
		return this.finished;
	}
	// Método para indicar que el juego ha terminado (usado por exit)
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

}