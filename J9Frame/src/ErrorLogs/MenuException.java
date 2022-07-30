package ErrorLogs;

/**
 *The menu exception.
 *
 */
public class MenuException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized.
	 */
	/**
	 * The menu exception.
	 */
	public MenuException() {
		super();
	}
	
	/**
	 * Return the setted string of exception.
	 * @param e - the exception.
	 */
	public MenuException(String e) {
		super(e);
	}

}
