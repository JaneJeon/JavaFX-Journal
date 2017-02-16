import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.IOException;

public class Main {
	public static final int WIDTH = 960, HEIGHT = 600;
	private static final String imgPath = "background.jpg";
	
	public static void main(String[] args) throws IOException {
		// logic goes here...
		
		// setting up GUI
		final JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setUndecorated(true);
		final View textArea = new View(imgPath);
		final StyledDocument doc = textArea.getStyledDocument();
		final Style style = textArea.addStyle("Default style", null);
		StyleConstants.setForeground(style, Color.white);
		// adjust font size and margins so that the editor is at most 75 characters wide
		// Swing freaks the f*** out with the caret position in HiDPI with certain fonts (e.g. Lucida Sans)
		StyleConstants.setFontSize(style, 22);
		StyleConstants.setFontFamily(style, "Lucida Grande");
		final JScrollPane scrollPane = new JScrollPane(textArea);
		// this still doesn't fix the wrapping...
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// loading the window
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				scrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
				frame.getContentPane().add(scrollPane);
				//textArea.setText("Some sample text here ...");
				try {
					doc.insertString(doc.getLength(), "You can type here: ", style);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}