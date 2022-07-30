package ErrorLogs;

public class StyleException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1669186428752820882L;

	public StyleException() {
		// TODO Auto-generated constructor stub
		super("Error occoured in the style code");
	}
	
	public StyleException(String se) {
		// TODO Auto-generated constructor stub
		super(se);
	}
}
