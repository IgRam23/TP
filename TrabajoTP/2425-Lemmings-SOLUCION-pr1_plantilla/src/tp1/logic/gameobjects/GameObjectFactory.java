package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;
import tp1.exceptions.*;
import tp1.logic.GameWorld;


public class GameObjectFactory {
	private static final List<GameObject> availableObjects = Arrays.asList(
			//new Lemming(),
			//new Wall(),
			//new ExitDoor(),
			//new MetalWall()
	);
	
	 public static GameObject parse(String line, GameWorld game)  throws ObjectParseException, OffBoardException {
	
		
		 for(GameObject object : availableObjects) {
			 try {
	                GameObject parsedObject = object.parse(line, game);
	                if (parsedObject != null) {
	                    return parsedObject; // Retornamos el objeto si el parse fue exitoso
	                }
	            } catch (ObjectParseException | IllegalArgumentException e) {
	                // Ignoramos y seguimos con el siguiente objeto
	            }
		 }
		 throw new ObjectParseException("No matching GameObject found for input line: " + line);
	 }
}
