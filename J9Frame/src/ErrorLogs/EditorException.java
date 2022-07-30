package ErrorLogs;

public class EditorException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2885666564783730005L;
	
	public EditorException() {
		super("Notic : Editor found error !");
	}

	public EditorException(String e) {
		super(e);
	}
}
