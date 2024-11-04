package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;

public abstract class GameObject {

	protected Position pos;
	protected boolean isAlive;
	protected GameWorld game;
	
	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	public boolean isInPosition(Position p) {
        return this.pos.equals(p);
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	

	public abstract String getIcon();

	public abstract void update();
	
	public abstract boolean isSolid();
	
	public abstract boolean isExit();
	
	public abstract boolean isLemming();

}