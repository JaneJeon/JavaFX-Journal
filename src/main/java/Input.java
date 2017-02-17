public class Input {
	private Action action;
	
	public Input(Action action) {
		this.action = action;
	}
	
	public void readTest(String s) {
		System.out.println(s);
	}

	public String response(String s) {
		// chop off the first line, which is always the machine's response
		s = s.substring(s.indexOf('\n') + 1);
		action.passOn(s);
		return parser(s);
	}
	
	// TODO: plug NLP into this
	private String parser(String s) {
		String result = "This is a response to your input!\n";
		return result;
	}
}