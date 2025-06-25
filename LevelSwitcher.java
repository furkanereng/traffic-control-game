/* Yusuf İslam Altunkaynak 
 * Furkan Eren Gülçay 
 * Enzel Ebrar Albayrak  
 * This program creates the pane used for switching between levels. A level becomes unlocked if and only if the previous level is completed. */

import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelSwitcher extends BorderPane {
	
	private ArrayList<Group> levelButtons = new ArrayList<>();
	private StackPane root;
	private MainScreen ms;
	private boolean[] isUnlocked = new boolean[5];

	/* Creating the pane which is allowing switching levels. */
	public LevelSwitcher(StackPane root, Market market, MainScreen ms) {
		
		Image image = new Image("levels.png");
		ImageView background = new ImageView(image);
		this.setCenter(background);
		
		this.root = root;
		this.ms = ms;
		isUnlocked[0] = true;
		
		Text back = new Text("BACK");
		back.setFont(new Font("Times New Roman", 48));
		back.setFill(Color.DARKBLUE);
		back.setX(28);
		back.setY(748);
		
		Rectangle backButton = new Rectangle(15, 700, 160, 60);
		backButton.setFill(Color.ALICEBLUE);
		backButton.setStroke(Color.DARKBLUE);
		backButton.setStrokeWidth(5);
		backButton.setArcWidth(16);
		backButton.setArcHeight(16);
		
		Group backToMain = new Group(backButton, back);
		
		this.getChildren().addAll(backToMain);
		backToMain.setOnMouseClicked(e -> {
			ms.displayMain(root);
		});
		
		Text level1Text = new Text("1");
		level1Text.setFont(new Font("Times New Roman", 100));
		level1Text.setFill(Color.DARKBLUE);
		level1Text.setX(110);
		level1Text.setY(250);
		Rectangle level1Button = new Rectangle(42, 155, 181, 114);
		level1Button.setFill(Color.ALICEBLUE);
		level1Button.setStroke(Color.DARKBLUE);
		level1Button.setStrokeWidth(5);
		level1Button.setArcWidth(16);
		level1Button.setArcHeight(16);
		Group level1 = new Group(level1Button, level1Text);
		this.getChildren().add(level1);
		level1.setOnMouseClicked(e -> {
			try {
				new Level("level1.txt", root, ms);
			} catch(Exception ex) {
				System.out.println("level1.txt is not found");
			}
		});
		levelButtons.add(level1);
		
		Text level2Text = new Text("2");
		level2Text.setFont(new Font("Times New Roman", 100));
		level2Text.setFill(Color.DARKBLUE);
		level2Text.setX(375);
		level2Text.setY(250);
		Rectangle level2Button = new Rectangle(307, 155, 181, 114);
		level2Button.setFill(Color.ALICEBLUE);
		level2Button.setStroke(Color.DARKBLUE);
		level2Button.setStrokeWidth(5);
		level2Button.setArcWidth(16);
		level2Button.setArcHeight(16);
		Group level2 = new Group(level2Button, level2Text);
		level2.setOpacity(0.5);
		this.getChildren().add(level2);
		levelButtons.add(level2);
		
		Text level3Text = new Text("3");
		level3Text.setFont(new Font("Times New Roman", 100));
		level3Text.setFill(Color.DARKBLUE);
		level3Text.setX(640);
		level3Text.setY(250);
		Rectangle level3Button = new Rectangle(574, 155, 181, 114);
		level3Button.setFill(Color.ALICEBLUE);
		level3Button.setStroke(Color.DARKBLUE);
		level3Button.setStrokeWidth(5);
		level3Button.setArcWidth(16);
		level3Button.setArcHeight(16);
		Group level3 = new Group(level3Button, level3Text);
		level3.setOpacity(0.5);
		this.getChildren().add(level3);
		levelButtons.add(level3);
		
		Text level4Text = new Text("4");
		level4Text.setFont(new Font("Times New Roman", 100));
		level4Text.setFill(Color.DARKBLUE);
		level4Text.setX(242);
		level4Text.setY(421);
		Rectangle level4Button = new Rectangle(174, 326, 181, 114);
		level4Button.setFill(Color.ALICEBLUE);
		level4Button.setStroke(Color.DARKBLUE);
		level4Button.setStrokeWidth(5);
		level4Button.setArcWidth(16);
		level4Button.setArcHeight(16);
		Group level4 = new Group(level4Button, level4Text);
		level4.setOpacity(0.5);
		this.getChildren().add(level4);
		levelButtons.add(level4);
		
		Text level5Text = new Text("5");
		level5Text.setFont(new Font("Times New Roman", 100));
		level5Text.setFill(Color.DARKBLUE);
		level5Text.setX(510);
		level5Text.setY(421);
		Rectangle level5Button = new Rectangle(442, 326, 181, 114);
		level5Button.setFill(Color.ALICEBLUE);
		level5Button.setStroke(Color.DARKBLUE);
		level5Button.setStrokeWidth(5);
		level5Button.setArcWidth(16);
		level5Button.setArcHeight(16);
		Group level5 = new Group(level5Button, level5Text);
		level5.setOpacity(0.5);
		this.getChildren().add(level5);
		levelButtons.add(level5);
		
	}
	
	/* Updating the pane depending on levels are completed or not. */
	public void update() {
		if(isUnlocked[1]) {
			levelButtons.get(1).setOpacity(1);
			levelButtons.get(1).setOnMouseClicked(e -> {
				try {
					new Level("level2.txt", root, ms);
				} catch(Exception ex) {
					System.out.println("level2.txt is not found");
				}
			});
		}
		if(isUnlocked[2]) {
			levelButtons.get(2).setOpacity(1);
			levelButtons.get(2).setOnMouseClicked(e -> {
				try {
					new Level("level3.txt", root, ms);
				} catch(Exception ex) {
					System.out.println("level3.txt is not found");
				}
			});
		}
		if(isUnlocked[3]) {
			levelButtons.get(3).setOpacity(1);
			levelButtons.get(3).setOnMouseClicked(e -> {
				try {
					new Level("level4.txt", root, ms);
				} catch(Exception ex) {
					System.out.println("level4.txt is not found");
				}
			});
		}
		if(isUnlocked[4]) {
			levelButtons.get(4).setOpacity(1);
			levelButtons.get(4).setOnMouseClicked(e -> {
				try {
					new Level("level5.txt", root, ms);
				} catch(Exception ex) {
					System.out.println("level5.txt is not found");
				}
			});
		}
	}
	
	/* Displays this pane. */
	public void displaySwitcher(StackPane root) {
		root.getChildren().clear();
		root.getChildren().add(this);
	}

	/* The rest are getter/setter methods. */
	public ArrayList<Group> getLevelButtons() {
		return levelButtons;
	}

	public void setLevelButtons(ArrayList<Group> levelButtons) {
		this.levelButtons = levelButtons;
	}

	public boolean[] getIsUnlocked() {
		return isUnlocked;
	}

	public void setIsUnlocked(boolean[] isUnlocked) {
		this.isUnlocked = isUnlocked;
	}
	
}
