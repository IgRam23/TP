package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.logic.lemmingRoles.*; 

public interface LemmingRole {
    void start(Lemming lemming);
    void play(Lemming lemming);
    String getIcon(Lemming lemming);
    
    boolean receiveInteraction(GameItem other, Lemming owner);
    boolean interactWith(Lemming receiver, Lemming owner);
    boolean interactWith(Wall wall, Lemming owner);
    boolean interactWith(ExitDoor door, Lemming owner);
}

