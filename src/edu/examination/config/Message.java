package edu.examination.config;

public enum Message {

	EXIT_APP(0, "THANK FOR USING MY APP. BYE AND SEE YOU AGAIN!"),
	
	REGISTER_SUCCESSFUL(1, "REGISTRATION SUCCESSFUL"),
	LOGIN_SUCCESSFUL(2, "LOGIN SUCCESSFUL!"),
	
	INCORRECT_OPTION(3,"INCORRECT OPTION. PLEASE ENTER AGAIN!");
	
	
	private final int code;
	private final String description;

	private Message(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return code + ": " + description;
	}
}
