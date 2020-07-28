package edu.examination.config;

public enum Message {

	EXIT_APP(0, "THANK FOR USING MY APP. BYE AND SEE YOU AGAIN!"),
	REGISTER_SUCCESSFUL(1, "REGISTRATION SUCCESSFUL"),
	LOGIN_SUCCESSFUL(2, "LOGIN SUCCESSFUL!"),
	CONTACT_ADMIN(3, "PLEASE CONTACT WITH ADMIN TO FIX"),
	TOTAL_MARK_NOT_ENOUGH(4, "DUE TO TOTAL MARKS OF YOUR EXAM IS NOT ENOUGH 100. YOU ONLY CAN SAVE AS DRAFT OR CANCEL"),
	EXAM_SUBMIT_SUCCESSFUL(5, "SUBMIT SUCCESSFUL"),
	EXAM_SAVE_AS_DRAFT_SUCCESSFUL(6, "SAVE AS DRAFT SUCCESSFUL"),
	UPDATE_SUCCESSFUL(7, "UPDATE SUCCESSFUL"),
	UPDATE_FAIL(8, "UPDATE FAIL"),
	NO_DRAFT_EXAM(9, "YOU DON'T HAVE ANY DRAFT EXAMS TO EDIT");
	
	private final int code;
	private final String description;

	private Message(int code, String description) {
		this.code = code;
		this.description = ConsoleColors.BLUE_BOLD + description + ConsoleColors.RESET;
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
