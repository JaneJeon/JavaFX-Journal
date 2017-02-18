// Based on the perceived intent of the user, acts on view or model accordingly
public class Action {
	private Launch terminal;
	private String intent;
	
	public Action(Launch terminal) {
		this.terminal = terminal;
	}
	
	// gets intent/actions/body from input?
	public void passOn(String s) {
		System.out.println("Received message "+s+" from Input");
		if (s.equalsIgnoreCase("quit")) quit();
	}
	
	private void quit() {
		DBAccess.close();
		terminal.quit();
	}
}