/* Yusuf İslam Altunkaynak 
 * Furkan Eren Gülçay 
 * Enzel Ebrar Albayrak  
 * This program is used for running the game. Creates the main screen. */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class RunGame extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		MainScreen ms = new MainScreen(primaryStage, root);
		ms.setMarket(new Market(root, ms)); 
		ms.setSwitcher(new LevelSwitcher(root, ms.getMarket(), ms));
		root.getChildren().add(ms);
		Scene scene = new Scene(root, 800, 800);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Traffic Simulator"); 
		primaryStage.setScene(scene); 
		primaryStage.show(); 
	}
	
	/* Used for running the program on IDE. */
	public static void main(String[] args) {
		launch(args);
	}

}
