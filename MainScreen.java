/* Yusuf İslam Altunkaynak 
 * Furkan Eren Gülçay 
 * Enzel Ebrar Albayrak  
 * This program creates the main screen. We created the Market and LevelSwitcher instances just once and assigned to MainScreen. Otherwise; when we create more than once, we can't control them. */

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreen extends BorderPane {
	
	private Market m;
	private LevelSwitcher switcher;
	
	/* Creates the main screen with buttons. */
	public MainScreen(Stage stage, StackPane root) {
		Image image = new Image("traffic.png");
		ImageView background = new ImageView(image);
		this.setCenter(background);
		
		Rectangle playButton = new Rectangle(315, 400, 165, 75);
		playButton.setFill(Color.ALICEBLUE);
		playButton.setStroke(Color.DARKBLUE);
		playButton.setStrokeWidth(5);
		playButton.setArcWidth(16);
		playButton.setArcHeight(16);
		Text playText = new Text("PLAY");
		playText.setFill(Color.DARKBLUE);
		playText.setFont(new Font("Times New Roman", 48));
		playText.setX(337);
		playText.setY(455);
		Group play = new Group(playButton);
		play.getChildren().add(playText);
		this.getChildren().add(play);
		play.setOnMouseClicked(e -> {
			switcher.update();
			switcher.displaySwitcher(root);
		});
		
		Rectangle marketButton = new Rectangle(277, 500, 242, 75);
		marketButton.setFill(Color.ALICEBLUE);
		marketButton.setStroke(Color.DARKBLUE);
		marketButton.setStrokeWidth(5);
		marketButton.setArcWidth(16);
		marketButton.setArcHeight(16);
		Text marketText = new Text("MARKET");
		marketText.setFill(Color.DARKBLUE);
		marketText.setFont(new Font("Times New Roman", 48));
		marketText.setX(299);
		marketText.setY(556);
		Group marketGroup = new Group(marketButton, marketText);
		this.getChildren().add(marketGroup);
		
		marketGroup.setOnMouseClicked(e -> {
			m.update();
			m.displayMarket(root);
		});
	}
	
	/* Displays this pane in scene. */
	public void displayMain(StackPane root) {
		root.getChildren().clear();
		root.getChildren().add(this);
	}

	/* The rest are getter/setter methods. */
	public Market getMarket() {
		return m;
	}

	public void setMarket(Market m) {
		this.m = m;
	}

	public LevelSwitcher getSwitcher() {
		return switcher;
	}

	public void setSwitcher(LevelSwitcher switcher) {
		this.switcher = switcher;
	}

}
