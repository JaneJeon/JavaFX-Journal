import edu.stanford.nlp.simple.Document;
import edu.stanford.nlp.simple.Sentence;

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
		Document doc = new Document(s);
		for (Sentence sent : doc.sentences()) {
			System.out.println("Parts of sentence tag: "+sent.posTags());
			System.out.println("Entity Recognition: "+sent.nerTags());
			System.out.println("Constituency: "+sent.parse());
			System.out.println("Info Extraction: "+sent.openie());
			System.out.println("Dependency graph: "+sent.dependencyGraph());
			/*
			List<String> result = sent.posTags();
			for (int i=0; i<result.size(); i++) {
				if (result.get(i).equals("VB")) {
					System.out.println("Detected command: "+sent.lemma(i));
				}
				if (result.get(i).equals("NN")) {
					System.out.println("Here's what I want to do: "+sent.lemma(i));
				}
			}
			List<String> result2 = sent.nerTags();
			for (int i=0; i<result.size(); i++) {
				if (result2.get(i).equals("DATE")) {
					System.out.println("Recognized date: "+sent.lemma(i));
				}
				if (result2.get(i).equals("TIME")) {
					System.out.println("Recognized time: "+sent.lemma(i));
				}
			}*/
		}
		return s;
	}
}