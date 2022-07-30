package ErrorLogs;

public class CodeException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6639205339290256329L;
	
	public CodeException() {
		// TODO Auto-generated constructor stub
		super("Code contains errors!");
	}
	
	public CodeException(String err) {
		super(err);
	}

}
