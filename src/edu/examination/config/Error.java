package edu.examination.config;

public enum Error {

	EMAIL_BLANK(0, ConsoleColors.RED + "EMAIL CAN NOT BLANK" + ConsoleColors.RESET),
	EMAIL_NOT_VALID(1, ConsoleColors.RED + "THE EMAIL IS NOT VALID. PLEASE ENTER AGAIN!" + ConsoleColors.RESET),
	EMAIL_EXISTED(2, ConsoleColors.RED + "THE EMAIL HAVE EXISTED IN THE SYSTEM. PLEASE ENTER OTHER." + ConsoleColors.RESET),
	
	PASSWORD_BLANK(3, ConsoleColors.RED + "PASSWORD CAN NOT BLANK" + ConsoleColors.RESET),
	PASSWORD_NOT_VALID(4, ConsoleColors.RED + "THE PASSWORD IS NOT VALID. PASSWORD MUST BE AT LEAST 6 CHARACTERS LONG, ONE UPPERCASE, ONE LOWERCASE AND ONE NUMERIC CHARACTER." + ConsoleColors.RESET),
	
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
