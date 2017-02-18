import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

// TODO: change text color based on background's color
public class Launch extends Application {
	private double width = 960, height = 600;
	private TextArea textArea;
	private Input input;

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
		textArea.appendText("Hi, there!\n");
		
		textArea.setOnKeyPressed(event -> {
			if (event.isMetaDown()) {
				if (event.getCode() == KeyCode.W) {
					// cmd W (close window)
					DBAccess.close();
					quit();
				} else if (event.getCode() == KeyCode.S) {
					// cmd S (take a screenshot and save it using JFileChooser)
					save(primaryStage.getScene().snapshot(null), primaryStage);
				} else if (event.getCode() == KeyCode.EQUALS && textArea.getFont().getSize() < 150) {
					// cmd + (increase font)
					textArea.setStyle("-fx-font-size: "+(textArea.getFont().getSize() * 1.1)+"px;");
				} else if (event.getCode() == KeyCode.MINUS && textArea.getFont().getSize() > 10) {
					// cmd - (decrease font)
					textArea.setStyle("-fx-font-size: "+(textArea.getFont().getSize() / 1.1)+"px;");
				}
			} else {
				if (event.getCode() == KeyCode.ENTER) {
					// bare Enter press is end of input - pass onto Input class
					textArea.setText(input.response(textArea.getText()));
					textArea.positionCaret(textArea.getText().length());
				} else if (event.getCode() == KeyCode.BACK_SPACE) {
					// delete single character
					textArea.deletePreviousChar();
				}
			}
			
			event.consume();
		});
		
		root.getChildren().add(textArea);

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX((screenBounds.getWidth() - width) / 2);
		primaryStage.setY((screenBounds.getHeight() - height) / 2);
		
		Scene scene = new Scene(root, width, height, Color.TRANSPARENT);
		scene.getStylesheets().add("text-area-bg.css");
		primaryStage.setScene(scene);

		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	// to be accessed only be Action class
	public void quit() {
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
