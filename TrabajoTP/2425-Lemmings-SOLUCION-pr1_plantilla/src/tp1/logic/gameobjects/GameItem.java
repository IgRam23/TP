package tp1.logic.gameobjects;

public interface GameItem {
    boolean receiveInteraction(GameItem other);
    boolean interactWith(Wall wall);
    boolean interactWith(ExitDoor door);
	boolean interactWith(Lemming lemming);
}
