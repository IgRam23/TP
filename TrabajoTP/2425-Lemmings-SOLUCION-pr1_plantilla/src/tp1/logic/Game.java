package tp1.logic;

public class Game {

	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;
	private int cycle; //ns si esta bien
    private int lemmingsInBoard; //ns si esta bien
    private int lemmingsDead; //ns si esta bien
    private int lemmingsExit; //ns si esta bien
    private int lemmingsToWin; //ns si esta bien

	public Game(int nLevel) {
		// TODO Auto-generated constructor stub
		this.lemmingsInBoard = 0;
		this.lemmingsDead = 0;
		this.cycle = 0;
		
	}

	public int getCycle() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numLemmingsInBoard() {
		
		return lemmingsInBoard;
	}

	public int numLemmingsDead() {
		// TODO Auto-generated method stub
		return lemmingsDead;
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
		// TODO Auto-generated method stub
		
		return "";
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

}
