package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {

	private GameLogic gm;
	private static Scene scene;

	@Override
	public void start(Stage primaryStage) {
		
	
		gm = new GameLogic();
		scene = new Scene(gm.gameBoard);
		
		
		primaryStage.setScene(scene);
		
		
		primaryStage.show();
		gm.initializeGameLogic();
		
	}
	public static Scene getScene() {
	    return scene;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
