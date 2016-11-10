package response;

public class HasAccessResponse {
	
	private boolean result;
	private String message;
	

	/**
	 * Constructor for Response Object
	 * @param result	final Result to be passed
	 * @param message	message to be displayed
	 */
	public HasAccessResponse(boolean result, String message) {
		this.setResult(result);
		this.setMessage(message);
	}
	
	public HasAccessResponse(String message) {
		this(false, message);
	}

	/**
	 * Getters and Setters
	 */
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
