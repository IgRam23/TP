package tp1.logic.gameobjects;
	
	import java.util.Arrays; 
	import java.util.List;
	import tp1.exceptions.*;
	import tp1.logic.GameObjectContainer;
	import tp1.logic.GameWorld;
	import tp1.logic.Position;
	import tp1.logic.lemmingRoles.LemmingRole;
	
	
	public class GameObjectFactory {
		
		private static final Position pos = null;
		private static final GameWorld game = null;
		private static final LemmingRole role = null;
		
		
		private static final List<GameObject> availableObjects = Arrays.asList(
				new Lemming(game, pos, role),
				new Wall(game, pos),
				new ExitDoor(game, pos),
				new MetalWall(game, pos) 
		);
		
	    public static GameObject parse(String line, GameWorld game)  throws ObjectParseException, OffBoardException {	
			for(GameObject object : availableObjects) {
				 try {
		                GameObject parsedObject = object.parse(line, game);
		                if (parsedObject != null) {
		                    return parsedObject; 
		                }
		            } catch (ObjectParseException | OffBoardException e) { 
		            //no lanzamos nada porque hay que seguir con el bucle hasta hacer un return
		            }
			}
			throw new ObjectParseException("No matching GameObject found for input line: " + line);
		}
	}
