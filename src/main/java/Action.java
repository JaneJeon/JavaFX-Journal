public class Action {
	private Launch terminal;
	
	public Action(Launch terminal) {
		this.terminal = terminal;
	}
	
	public void passOn(String s) {
		System.out.println("Received message "+s+" from Input");
		if (s.toLowerCase().equals("quit")) quit();
	}
	
	private void quit() {
		DBAccess.close();
		terminal.quit();
	}
}