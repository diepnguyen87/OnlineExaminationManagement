package edu.examination.config;

public enum Error {

	EMAIL_BLANK(0, "EMAIL CAN NOT BLANK"),
	EMAIL_NOT_VALID(1, "THE EMAIL IS NOT VALID. PLEASE ENTER AGAIN!" ),
	EMAIL_EXISTED(2, "THE EMAIL HAVE EXISTED IN THE SYSTEM. PLEASE ENTER OTHER."),
	
	PASSWORD_BLANK(3, "PASSWORD CAN NOT BLANK"),
	PASSWORD_NOT_VALID(4, "THE PASSWORD IS NOT VALID. PASSWORD MUST BE AT LEAST 6 CHARACTERS LONG, ONE UPPERCASE, ONE LOWERCASE AND ONE NUMERIC CHARACTER."),
	
	INCORRECT_AUTHENICATION(5, "USER NAME OR PASSWORD IS INCORRECT.");
	
	private final int code;
	private final String description;

	private Error(int code, String description) {
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
