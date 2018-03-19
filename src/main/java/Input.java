// TODO: warm up parser

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
		// TODO: rethink this for terminal inputs
		s = s.substring(s.indexOf('\n') + 1);
		
		// TODO: think about what data type I'm going to pass on
		// parse first to get actionable info, then pass on
		action.passOn(parser(s));
		return action.output();
	}
	
	// TODO: plug NLU into this - need to get the intent
	// TODO: take care of log4j being retarded
	// main intent - verb. Main object receiving action - noun.
	// Then pass on the entire text. need to check if existing intent is diary mode
	private String parser(String s) {
		
		return s;
	}
}