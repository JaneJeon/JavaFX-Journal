public class Input {
	public static void readTest(String s) {
		System.out.println(s);
	}

	public static String response(String s) {
		// chop off the first line, which is always the machine's response
		s = s.substring(s.indexOf('\n') + 1);
		System.out.println(s);
		
		return parser(s);
	}
	
	// TODO: match not just the exact words 'quit', but also match the intent to quit
	// TODO: respond to other commands as well
	private static String parser(String s) {
		if (s.toLowerCase().equals("quit")) return "quit";
		String result = "This is a response to your input!\n";
		return result;
	}
}
