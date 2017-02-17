import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launch extends Application {
	private double width = 960, height = 600;
	private String imgPath = "background.jpg";
	private TextArea textArea;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		VBox root = new VBox();

		BackgroundImage bg = new BackgroundImage(new Image(imgPath, width, height, false, true), 
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, 
				BackgroundSize.DEFAULT);
		
		textArea = new TextArea();
		textArea.setWrapText(true);
		textArea.setPrefSize(height, width);
		textArea.setFont(Font.font("Lucida Grande", 22)); // try to keep width at most 75 characters
		
		// default dialog
		textArea.appendText("Hello, human!\n");
		
		textArea.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				String response = Input.response(textArea.getText());
				if (response.equals("quit")) System.exit(-1);
				
				textArea.setText(response);
				textArea.positionCaret(response.length());
			}
			// consume event to prevent another line from being added
			event.consume();
		});
		
		root.setBackground(new Background(bg));
		root.getChildren().add(textArea);

		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX((screenBounds.getWidth() - width) / 2);
		primaryStage.setY((screenBounds.getHeight() - height) / 2);
		
		Scene scene = new Scene(root, width, height);
		scene.getStylesheets().add("text-area-bg.css");
		primaryStage.setScene(scene);

		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
}