import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

// TODO: hot reloading
// TODO: make console output not hard on the eyes
// TODO: select parts of text by mouse to delete and copy
// http://stackoverflow.com/questions/5255466/deleting-only-a-selected-text-in-a-text-area
// TODO: add support for ctrl+C, ctrl+V, ctrl+A, option+del (delete last word)
// TODO: how to make textArea actually fill up the entire canvas (instead of leaving some space in borders)??
public class Launch extends Application {
	private double width = 960, height = 600;
	private TextArea textArea;
	private Input input;
	private String consoleOutput;

	// passes the instance of this class to Action instance, then passes Action instance to Input
	public Launch() {
		Action actor = new Action(this);
		input = new Input(actor);
	}

	public static void main(String[] args) {
		new Launch();
		launch(args);
	}

	public void start(Stage primaryStage) {
		VBox root = new VBox();
		
		textArea = new TextArea();
		textArea.setWrapText(true);
		textArea.setPrefSize(height, width);
		
		// default dialog
		consoleOutput = "Hi, there!\n";
		textArea.appendText(consoleOutput);
		
		textArea.setOnKeyPressed(event -> {
			// check special keys first for shortcuts
			if (event.isMetaDown()) {
				if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.Q) {
					// cmd W/Q (quit safely)
					quit();
				} else if (event.getCode() == KeyCode.S) {
					// cmd S (take a screenshot and save it using JFileChooser)
					save(primaryStage.getScene().snapshot(null), primaryStage);
				} else if (event.getCode() == KeyCode.EQUALS && textArea.getFont().getSize() < 150) {
					// cmd + (increase font)
					textArea.setStyle("-fx-font-size: "+(textArea.getFont().getSize() * 1.1)+"px;");
				} else if (event.getCode() == KeyCode.MINUS && textArea.getFont().getSize() > 11) {
					// cmd - (decrease font)
					textArea.setStyle("-fx-font-size: "+(textArea.getFont().getSize() / 1.1)+"px;");
				} else if (event.getCode() == KeyCode.BACK_SPACE) {
					// TODO: cmd + delete should delete a whole line
				}
			} else if (event.isShiftDown() && event.getCode() == KeyCode.ENTER) {
				// shift enter (puts new line without submitting the input)
				textArea.appendText("\n");
			} else if (!(textArea.getText().length() == consoleOutput.length())) {
				// check if user is just mashing Return without entering anything
				if (event.getCode() == KeyCode.ENTER) {
					// bare Enter press is end of input - pass onto Input class
					consoleOutput = input.response(textArea.getText());
					textArea.setText(consoleOutput);
					textArea.positionCaret(consoleOutput.length());
					textArea.setScrollTop(Double.MAX_VALUE);
				} else if (event.getCode() == KeyCode.BACK_SPACE) {
					// delete single character, unless if the user is trying to delete the console output
					textArea.deletePreviousChar();
				}
			}
			
			event.consume();
		});
		
		root.getChildren().add(textArea);

//		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
//		primaryStage.setX((screenBounds.getWidth() - width) / 2);
//		primaryStage.setY((screenBounds.getHeight() - height) / 2);
		
		Scene scene = new Scene(root, width, height, Color.TRANSPARENT);
		scene.getStylesheets().add("text-area-bg.css");
		primaryStage.setScene(scene);

		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	// accessed by this and Action. Close db connection properly.
	public void quit() {
		DBAccess.close();
		System.exit(0);
	}
	
	// save image
	private void save(WritableImage screenshot, Stage stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", "*.png"));
		File file = fileChooser.showSaveDialog(stage);
		if (file != null) {
			if (!file.getAbsolutePath().endsWith(".png")) {
				file = new File(file.toString() + ".png");
			}

			RenderedImage renderedImage = SwingFXUtils.fromFXImage(screenshot, null);
			try {
				ImageIO.write(renderedImage, "png", file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}