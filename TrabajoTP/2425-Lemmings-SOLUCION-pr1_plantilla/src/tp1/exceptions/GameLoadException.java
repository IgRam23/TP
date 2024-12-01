package tp1.exceptions;

public class GameLoadException extends GameModelException {
	private static final long serialVersionUID = 1L; 

	public GameLoadException() {
		super();
	}
	
	public GameLoadException(String message) {
		super(message);
	}
	
	public GameLoadException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GameLoadException(Throwable cause) {
		super(cause);
	}
	
	GameLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		 super(message, cause, enableSuppression, writableStackTrace);
	}
}
