import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launch extends Application {
	private double width = 960, height = 600;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox();

		BackgroundImage bg = new BackgroundImage(new Image("background.jpg",960,600,false,true), 
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, 
				BackgroundSize.DEFAULT);

		TextArea textArea = new TextArea();
		textArea.setWrapText(true);
		textArea.setPrefSize(height, width);
		textArea.setFont(Font.font("Lucida Grande", 22));
		
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
