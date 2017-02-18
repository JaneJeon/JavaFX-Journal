// Breaks down input in a way that is understandable by machines
// and passes along the results (intent/actions/body) to actor
public class Input {
	private Action action;
	
	public Input(Action action) {
		this.action = action;
	}

	// generates a response for the terminal and the actor
	public String response(String s) {
		// chop off the first line, which is always the machine's response
		s = s.substring(s.indexOf('\n') + 1);
		
		// parse first to get actionable info, then pass on
		action.passOn(s);
		return parser(s);
	}
	
	// TODO: plug NLP into this - need to get the intent
	private String parser(String s) {
		String result = "This is a response to your input!\n";
		return result;
	}
}