/* Yusuf İslam Altunkaynak 
 * Furkan Eren Gülçay 
 * Enzel Ebrar Albayrak  
 * This program creates the level's end screen. Shows is level completed or failed and shows the earned money amount, new money amount, number of crashes and number of scores. */

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EndScreen extends BorderPane {
	
	/* Creates end screen with buttons and displays some informations. Buttons are able to switching
	   back to main screen, to the next or same level depends on win or lose. */
	public EndScreen(StackPane root, MainScreen ms, int levelIndex, int gain, int arrived, int win, int crashes, int lose) throws Exception {
		Level level = new Level();
		this.setCenter(level);
		ms.getMarket().setCash(ms.getMarket().getCash()+gain);
		Rectangle endScreen = new Rectangle((level.getLevelWidth()/level.getNumGridX())*(level.getNumGridX()-1), (level.getLevelHeight()/level.getNumGridY())*(level.getNumGridY()-1));
		endScreen.setX(level.getLevelWidth()/level.getNumGridX()/2);
		endScreen.setY(level.getLevelHeight()/level.getNumGridY()/2);
		endScreen.setArcWidth(16);
		endScreen.setArcHeight(16);
		endScreen.setFill(Color.ALICEBLUE);
		endScreen.setStroke(Color.DARKBLUE);
		getChildren().add(endScreen);
		
		Text levelText = new Text("LEVEL");
		levelText.setFill(Color.DARKBLUE);
		levelText.setFont(new Font("Times New Roman", 120));
		levelText.setX(endScreen.getX()+endScreen.getWidth()/2-190);
		levelText.setY(endScreen.getY()+150);
		getChildren().add(levelText);
		
		if(arrived==win) {
			Text winText = new Text("COMPLETED");
			winText.setFill(Color.DARKBLUE);
			winText.setFont(new Font("Times New Roman", 120));
			winText.setX(endScreen.getX()+endScreen.getWidth()/2-360);
			winText.setY(endScreen.getY()+285);
			getChildren().add(winText);
		}
		
		if(crashes==lose) {
			Text loseText = new Text("OVER");
			loseText.setFill(Color.DARKBLUE);
			loseText.setFont(new Font("Times New Roman", 120));
			loseText.setX(endScreen.getX()+endScreen.getWidth()/2-163);
			loseText.setY(endScreen.getY()+285);
			getChildren().add(loseText);
		}
	
		Circle money = new Circle(30);
		money.setCenterX(endScreen.getX()+300);
		money.setCenterY(endScreen.getY()+350);
		money.setFill(Color.LIGHTYELLOW);
		money.setStroke(Color.YELLOW);
		money.setStrokeWidth(5);
		getChildren().add(money);
		
		Text moneySign = new Text("$");
		moneySign.setFill(Color.DARKBLUE);
		moneySign.setFont(new Font("Times New Roman", 48));
		moneySign.setX(endScreen.getX()+290);
		moneySign.setY(endScreen.getY()+365);
		getChildren().add(moneySign); 
		
		Text moneyGained = new Text("Gained: +");
		moneyGained.setFill(Color.DARKBLUE);
		moneyGained.setFont(new Font("Times New Roman", 60));
		moneyGained.setX(endScreen.getX()+20);
		moneyGained.setY(endScreen.getY()+370);
		getChildren().add(moneyGained); 
		
		Text moneyGainedCounter = new Text("" + gain);
		moneyGainedCounter.setFill(Color.DARKBLUE);
		moneyGainedCounter.setFont(new Font("Times New Roman", 60));
		moneyGainedCounter.setX(endScreen.getX()+340);
		moneyGainedCounter.setY(endScreen.getY()+370);
		getChildren().add(moneyGainedCounter); 
		
		Circle money1 = new Circle(30);
		money1.setCenterX(endScreen.getX()+330);
		money1.setCenterY(endScreen.getY()+430);
		money1.setFill(Color.LIGHTYELLOW);
		money1.setStroke(Color.YELLOW);
		money1.setStrokeWidth(5);
		getChildren().add(money1);
		
		Text moneySign1 = new Text("$");
		moneySign1.setFill(Color.DARKBLUE);
		moneySign1.setFont(new Font("Times New Roman", 48));
		moneySign1.setX(endScreen.getX()+320);
		moneySign1.setY(endScreen.getY()+445);
		getChildren().add(moneySign1); 
		
		Text moneyTotal = new Text("New Total: ");
		moneyTotal.setFill(Color.DARKBLUE);
		moneyTotal.setFont(new Font("Times New Roman", 60));
		moneyTotal.setX(endScreen.getX()+20);
		moneyTotal.setY(endScreen.getY()+450);
		getChildren().add(moneyTotal); 
		
		Text moneyTotalCounter = new Text("" + ms.getMarket().getCash());
		moneyTotalCounter.setFill(Color.DARKBLUE);
		moneyTotalCounter.setFont(new Font("Times New Roman", 60));
		moneyTotalCounter.setX(endScreen.getX()+370);
		moneyTotalCounter.setY(endScreen.getY()+450);
		getChildren().add(moneyTotalCounter); 
		
		Text score = new Text("Score: " + arrived + "/" + win);
		score.setFill(Color.DARKBLUE);
		score.setFont(new Font("Times New Roman", 60));
		score.setX(endScreen.getX()+20);
		score.setY(endScreen.getY()+530);
		getChildren().add(score); 
		
		Text crash = new Text("Crashes: " + crashes + "/" + lose);
		crash.setFill(Color.DARKBLUE);
		crash.setFont(new Font("Times New Roman", 60));
		crash.setX(endScreen.getX()+20);
		crash.setY(endScreen.getY()+610);
		getChildren().add(crash); 
		
		Text back = new Text("BACK");
		back.setFont(new Font("Times New Roman", 48));
		back.setFill(Color.DARKBLUE);
		back.setX(60);
		back.setY(740);
		
		Rectangle backButton = new Rectangle(47, 690, 160, 60);
		backButton.setFill(Color.ALICEBLUE);
		backButton.setStroke(Color.DARKBLUE);
		backButton.setStrokeWidth(5);
		backButton.setArcWidth(16);
		backButton.setArcHeight(16);
		
		Group backToMain = new Group(backButton, back);
		getChildren().add(backToMain);
		
		backToMain.setOnMouseClicked(e -> {
			root.getChildren().clear();
			root.getChildren().add(ms);
		});
		
		if(arrived>=win) {
			ms.getSwitcher().update();
			Text next = new Text("NEXT");
			next.setFont(new Font("Times New Roman", 48));
			next.setFill(Color.DARKBLUE);
			next.setX(605);
			next.setY(740);
			
			Rectangle nextButton = new Rectangle(590, 690, 160, 60);
			nextButton.setFill(Color.ALICEBLUE);
			nextButton.setStroke(Color.DARKBLUE);
			nextButton.setStrokeWidth(5);
			nextButton.setArcWidth(16);
			nextButton.setArcHeight(16);
			
			Group nextGroup = new Group(nextButton, next);
			getChildren().add(nextGroup);
			
			nextGroup.setOnMouseClicked(e -> {
				if(levelIndex==5) {
					root.getChildren().clear();
					root.getChildren().add(ms);
				} else {
					try {
						root.getChildren().clear();
						root.getChildren().add(new Level("level" + (levelIndex+1) + ".txt", root, ms));
					} catch (Exception ex) {
					}
				}
			});
		} else {
			Text tryAgain = new Text("TRY AGAIN");
			tryAgain.setFont(new Font("Times New Roman", 48));
			tryAgain.setFill(Color.DARKBLUE);
			tryAgain.setX(480);
			tryAgain.setY(740);
			
			Rectangle tryAgainButton = new Rectangle(470, 690, 280, 60);
			tryAgainButton.setFill(Color.ALICEBLUE);
			tryAgainButton.setStroke(Color.DARKBLUE);
			tryAgainButton.setStrokeWidth(5);
			tryAgainButton.setArcWidth(16);
			tryAgainButton.setArcHeight(16);
			
			Group tryGroup = new Group(tryAgainButton, tryAgain);
			getChildren().add(tryGroup);
			
			tryGroup.setOnMouseClicked(e -> {
				try {
					root.getChildren().clear();
					root.getChildren().add(new Level("level" + levelIndex + ".txt", root, ms));
				} catch (Exception ex) {
				}
			});
		}
		
		root.getChildren().clear();
		root.getChildren().add(this);
		
	}
}
