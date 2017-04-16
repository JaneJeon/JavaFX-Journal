// Based on the perceived intent of the user, acts on view or model accordingly
public class Action {
	private Launch terminal;
	private String intent;
	
	public Action(Launch terminal) {
		this.terminal = terminal;
	}
	
	// gets intent/actions/body from input?
	public void passOn(String s) {
		System.out.println("Received message '"+s+"' from Input");
		if (s.equalsIgnoreCase("quit")) terminal.quit();
	}
	
	// don't forget the newline at the end
	public String output() {
		String s = "";
		for (int i=0; i<700; i++) {
			s += Integer.toString(i);
		}
		return "Here is your output.\n" + s + "\n";
	}
}