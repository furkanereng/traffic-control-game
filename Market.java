/* Yusuf İslam Altunkaynak 
 * Furkan Eren Gülçay 
 * Enzel Ebrar Albayrak  
 * This program is holding information about total money amount, selected car and which cars are bought. */

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Market extends BorderPane {
	
	private int cash;
	private int selectedCar;
	private boolean[] isBought = new boolean[6];
	private Group[] carButtons = new Group[6];
	private Group[] purchaseButtons = new Group[6];
	private Rectangle[] selected = new Rectangle[6];
	private Text amount;
	
	/* Creates the market scene with buttons. */
	public Market(StackPane root, MainScreen ms) {
		//this.setCenter(new Level());
		Image image = new Image("market.png");
		ImageView background = new ImageView(image);
		this.setCenter(background);
		
		isBought[0] = true;
		
		Circle money = new Circle(30);
		money.setCenterX(40);
		money.setCenterY(40);
		money.setFill(Color.LIGHTYELLOW);
		money.setStroke(Color.YELLOW);
		money.setStrokeWidth(5);
		this.getChildren().add(money); 
		
		Text coin = new Text("$");
		coin.setFont(new Font("Times New Roman", 48));
		coin.setX(29);
		coin.setY(55);
		this.getChildren().add(coin);
		
		amount = new Text(getCash() + "");
		amount.setFont(new Font("Times New Roman", 48));
		amount.setX(80);
		amount.setY(55);
		this.getChildren().add(amount);
		
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
		
		/* From this line, choosing car system is implemented for each button. Only bought cars
		   can be selected. */
		Rectangle car0Rectangle = new Rectangle(53,20);
		car0Rectangle.setFill(Color.BLACK);
		car0Rectangle.setX(106);
		car0Rectangle.setY(200);
		Rectangle car0Button = new Rectangle(42, 155, 181, 114);
		car0Button.setFill(Color.ALICEBLUE);
		car0Button.setStroke(Color.DARKBLUE);
		car0Button.setStrokeWidth(5);
		car0Button.setArcWidth(16);
		car0Button.setArcHeight(16);
		selected[0] = car0Button;
		Group car0 = new Group(car0Button, car0Rectangle);
		this.getChildren().add(car0);
		carButtons[0] = car0;
		car0.setOnMouseClicked(e -> {
			selectedCar = 0;
			selected[0].setStroke(Color.GREEN);
			for(Rectangle s: selected)
				if(s!=selected[0])
					s.setStroke(Color.DARKBLUE);
		});
		
		Rectangle car1Rectangle = new Rectangle(53, 20);
		car1Rectangle.setFill(Color.DEEPSKYBLUE);
		car1Rectangle.setX(371);
		car1Rectangle.setY(200);
		Rectangle car1Button = new Rectangle(307, 155, 181, 114);
		car1Button.setFill(Color.ALICEBLUE);
		car1Button.setStroke(Color.DARKBLUE);
		car1Button.setStrokeWidth(5);
		car1Button.setArcWidth(16);
		car1Button.setArcHeight(16);
		selected[1] = car1Button;
		Group car1 = new Group(car1Button, car1Rectangle);
		car1.setOpacity(0.5);
		this.getChildren().add(car1);
		carButtons[1] = car1;
		
		Rectangle car2Rectangle = new Rectangle(53, 20);
		car2Rectangle.setFill(Color.CRIMSON);
		car2Rectangle.setX(639);
		car2Rectangle.setY(200);
		Rectangle car2Button = new Rectangle(574, 155, 181, 114);
		car2Button.setFill(Color.ALICEBLUE);
		car2Button.setStroke(Color.DARKBLUE);
		car2Button.setStrokeWidth(5);
		car2Button.setArcWidth(16);
		car2Button.setArcHeight(16);
		selected[2] = car2Button;
		Group car2 = new Group(car2Button, car2Rectangle);
		car2.setOpacity(0.5);
		this.getChildren().add(car2);
		carButtons[2] = car2;
		
		Rectangle car3Rectangle = new Rectangle(53, 20);
		car3Rectangle.setFill(Color.GREEN);
		car3Rectangle.setX(106);
		car3Rectangle.setY(466);
		Rectangle car3Button = new Rectangle(42, 420, 181, 114);
		car3Button.setFill(Color.ALICEBLUE);
		car3Button.setStroke(Color.DARKBLUE);
		car3Button.setStrokeWidth(5);
		car3Button.setArcWidth(16);
		car3Button.setArcHeight(16);
		selected[3] = car3Button;
		Group car3 = new Group(car3Button, car3Rectangle);
		car3.setOpacity(0.5);
		this.getChildren().add(car3);
		carButtons[3] = car3;
		
		Rectangle car4Rectangle = new Rectangle(53, 20);
		car4Rectangle.setFill(Color.BLUEVIOLET);
		car4Rectangle.setX(371);
		car4Rectangle.setY(466);
		Rectangle car4Button = new Rectangle(307, 420, 181, 114);
		car4Button.setFill(Color.ALICEBLUE);
		car4Button.setStroke(Color.DARKBLUE);
		car4Button.setStrokeWidth(5);
		car4Button.setArcWidth(16);
		car4Button.setArcHeight(16);
		selected[4] = car4Button;
		Group car4 = new Group(car4Button, car4Rectangle);
		car4.setOpacity(0.5);
		this.getChildren().add(car4);
		carButtons[4] = car4;
		
		Rectangle car5Rectangle = new Rectangle(53, 20);
		car5Rectangle.setFill(Color.BLUE);
		car5Rectangle.setX(639);
		car5Rectangle.setY(466);
		Rectangle car5Button = new Rectangle(574, 420, 181, 114);
		car5Button.setFill(Color.ALICEBLUE);
		car5Button.setStroke(Color.DARKBLUE);
		car5Button.setStrokeWidth(5);
		car5Button.setArcWidth(16);
		car5Button.setArcHeight(16);
		selected[5] = car5Button;
		Group car5 = new Group(car5Button, car5Rectangle);
		car5.setOpacity(0.5);
		this.getChildren().add(car5);
		carButtons[5] = car5;
		
		/* From this line, buying car system is implemented. Checks the money amount and allows to
		   buying if and only if have enough money. Each car can only be bought just for once. */
		Rectangle purchaseCar0 = new Rectangle(106, 275, 53, 40);
		purchaseCar0.setFill(Color.ALICEBLUE);
		purchaseCar0.setStroke(Color.DARKBLUE);
		purchaseCar0.setArcWidth(16);
		purchaseCar0.setArcHeight(16);
		Text amountCar0 = new Text("$0");
		amountCar0.setFont(new Font("Times New Roman", 24));
		amountCar0.setFill(Color.DARKBLUE);
		amountCar0.setX(120);
		amountCar0.setY(305);
		Group labelCar0 = new Group(purchaseCar0, amountCar0);
		labelCar0.setOpacity(0.5);
		this.getChildren().add(labelCar0);
		purchaseButtons[0] = labelCar0;
		
		Rectangle purchaseCar1 = new Rectangle(371, 275, 53, 40);
		purchaseCar1.setFill(Color.ALICEBLUE);
		purchaseCar1.setStroke(Color.DARKBLUE);
		purchaseCar1.setArcWidth(16);
		purchaseCar1.setArcHeight(16);
		Text amountCar1 = new Text("$200");
		amountCar1.setFont(new Font("Times New Roman", 24));
		amountCar1.setFill(Color.DARKBLUE);
		amountCar1.setX(374);
		amountCar1.setY(305);
		Group labelCar1 = new Group(purchaseCar1, amountCar1);
		labelCar1.setOpacity(0.5);
		this.getChildren().add(labelCar1);
		purchaseButtons[1] = labelCar1;
		
		Rectangle purchaseCar2 = new Rectangle(639, 275, 53, 40);
		purchaseCar2.setFill(Color.ALICEBLUE);
		purchaseCar2.setStroke(Color.DARKBLUE);
		purchaseCar2.setArcWidth(16);
		purchaseCar2.setArcHeight(16);
		Text amountCar2 = new Text("$300");
		amountCar2.setFont(new Font("Times New Roman", 24));
		amountCar2.setFill(Color.DARKBLUE);
		amountCar2.setX(643);
		amountCar2.setY(305);
		Group labelCar2 = new Group(purchaseCar2, amountCar2);
		labelCar2.setOpacity(0.5);
		this.getChildren().add(labelCar2);
		purchaseButtons[2] = labelCar2;
		
		Rectangle purchaseCar3 = new Rectangle(106, 540, 53, 40);
		purchaseCar3.setFill(Color.ALICEBLUE);
		purchaseCar3.setStroke(Color.DARKBLUE);
		purchaseCar3.setArcWidth(16);
		purchaseCar3.setArcHeight(16);
		Text amountCar3 = new Text("$400");
		amountCar3.setFont(new Font("Times New Roman", 24));
		amountCar3.setFill(Color.DARKBLUE);
		amountCar3.setX(110);
		amountCar3.setY(570);
		Group labelCar3 = new Group(purchaseCar3, amountCar3);
		labelCar3.setOpacity(0.5);
		this.getChildren().add(labelCar3);
		purchaseButtons[3] = labelCar3;
		
		Rectangle purchaseCar4 = new Rectangle(371, 540, 53, 40);
		purchaseCar4.setFill(Color.ALICEBLUE);
		purchaseCar4.setStroke(Color.DARKBLUE);
		purchaseCar4.setArcWidth(16);
		purchaseCar4.setArcHeight(16);
		Text amountCar4 = new Text("$600");
		amountCar4.setFont(new Font("Times New Roman", 24));
		amountCar4.setFill(Color.DARKBLUE);
		amountCar4.setX(374);
		amountCar4.setY(570);
		Group labelCar4 = new Group(purchaseCar4, amountCar4);
		labelCar4.setOpacity(0.5);
		this.getChildren().add(labelCar4);
		purchaseButtons[4] = labelCar4;
		
		Rectangle purchaseCar5 = new Rectangle(639, 540, 53, 40);
		purchaseCar5.setFill(Color.ALICEBLUE);
		purchaseCar5.setStroke(Color.DARKBLUE);
		purchaseCar5.setArcWidth(16);
		purchaseCar5.setArcHeight(16);
		Text amountCar5 = new Text("$800");
		amountCar5.setFont(new Font("Times New Roman", 24));
		amountCar5.setFill(Color.DARKBLUE);
		amountCar5.setX(643);
		amountCar5.setY(570);
		Group labelCar5 = new Group(purchaseCar5, amountCar5);
		labelCar5.setOpacity(0.5);
		this.getChildren().add(labelCar5);
		purchaseButtons[5] = labelCar5;
		
	}
	
	/* Displaying this market pane in the screen. */
	public void displayMarket(StackPane root) {
		root.getChildren().clear();
		root.getChildren().add(this);
	}

	/* Updating market display after any purchase or scene switching. */
	public void update() {
		amount.setText(getCash() + "");
		if(!isBought[1] && cash>=200) {
			purchaseButtons[1].setOpacity(1);
			purchaseButtons[1].setOnMouseClicked(e -> {
				purchaseButtons[1].setOpacity(0.5);
				cash-=200;
				purchaseButtons[1].setDisable(true);
				purchaseButtons[1].setOnMouseClicked(null);
				carButtons[1].setOpacity(1);
				carButtons[1].setOnMouseClicked(ev -> {
					selectedCar = 1;
					selected[1].setStroke(Color.GREEN);
					for(Rectangle s: selected)
						if(s!=selected[1])
							s.setStroke(Color.DARKBLUE);
				});
				amount.setText(getCash() + "");
				isBought[1] = true;
				update();
			});
		} else {
			purchaseButtons[1].setOpacity(0.5);
			purchaseButtons[1].setOnMouseClicked(null);
		}
		
		if(!isBought[2] && cash>=300) {
			purchaseButtons[2].setOpacity(1);
			purchaseButtons[2].setOnMouseClicked(e -> {
				purchaseButtons[2].setOpacity(0.5);
				cash-=300;
				purchaseButtons[2].setDisable(true);
				purchaseButtons[2].setOnMouseClicked(null);
				carButtons[2].setOpacity(1);
				carButtons[2].setOnMouseClicked(ev -> {
					selectedCar = 2;
					selected[2].setStroke(Color.GREEN);
					for(Rectangle s: selected)
						if(s!=selected[2])
							s.setStroke(Color.DARKBLUE);
				});
				amount.setText(getCash() + "");
				isBought[2] = true;
				update();
			});
		} else {
			purchaseButtons[2].setOpacity(0.5);
			purchaseButtons[2].setOnMouseClicked(null);
		}
		
		if(!isBought[3] && cash>=400) {
			purchaseButtons[3].setOpacity(1);
			purchaseButtons[3].setOnMouseClicked(e -> {
				purchaseButtons[3].setOpacity(0.5);
				cash-=400;
				purchaseButtons[3].setDisable(true);
				purchaseButtons[3].setOnMouseClicked(null);
				carButtons[3].setOpacity(1);
				carButtons[3].setOnMouseClicked(ev -> {
					selectedCar = 3;
					selected[3].setStroke(Color.GREEN);
					for(Rectangle s: selected)
						if(s!=selected[3])
							s.setStroke(Color.DARKBLUE);
				});
				amount.setText(getCash() + "");
				isBought[3] = true;
				update();
			});
		} else {
			purchaseButtons[3].setOpacity(0.5);
			purchaseButtons[3].setOnMouseClicked(null);
		}
		
		if(!isBought[4] && cash>=600) {
			purchaseButtons[4].setOpacity(1);
			purchaseButtons[4].setOnMouseClicked(e -> {
				purchaseButtons[4].setOpacity(0.5);
				cash-=600;
				purchaseButtons[4].setDisable(true);
				purchaseButtons[4].setOnMouseClicked(null);
				carButtons[4].setOpacity(1);
				carButtons[4].setOnMouseClicked(ev -> {
					selectedCar = 4;
					selected[4].setStroke(Color.GREEN);
					for(Rectangle s: selected)
						if(s!=selected[4])
							s.setStroke(Color.DARKBLUE);
				});
				amount.setText(getCash() + "");
				isBought[4] = true;
				update();
			});
		} else {
			purchaseButtons[4].setOpacity(0.5);
			purchaseButtons[4].setOnMouseClicked(null);
		}
		
		if(!isBought[5] && cash>=800) {
			purchaseButtons[5].setOpacity(1);
			purchaseButtons[5].setOnMouseClicked(e -> {
				purchaseButtons[5].setOpacity(0.5);
				cash-=800;
				purchaseButtons[5].setDisable(true);
				purchaseButtons[5].setOnMouseClicked(null);
				carButtons[5].setOpacity(1);
				carButtons[5].setOnMouseClicked(ev -> {
					selectedCar = 5;
					selected[5].setStroke(Color.GREEN);
					for(Rectangle s: selected)
						if(s!=selected[5])
							s.setStroke(Color.DARKBLUE);
				});
				amount.setText(getCash() + "");
				isBought[5] = true;
				update();
			});
		} else {
			purchaseButtons[5].setOpacity(0.5);
			purchaseButtons[5].setOnMouseClicked(null);
		}
	}
	
	/* The rest are getter/setter methods. */
	public int getCash() {
		return cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(int selectedCar) {
		this.selectedCar = selectedCar;
	}

	public Text getAmount() {
		return amount;
	}

	public void setAmount(Text amount) {
		this.amount = amount;
	}

	public boolean[] getIsBought() {
		return isBought;
	}

	public void setIsBought(boolean[] isBought) {
		this.isBought = isBought;
	}

	public Group[] getCarButtons() {
		return carButtons;
	}

	public void setCarButtons(Group[] carButtons) {
		this.carButtons = carButtons;
	}

	public Group[] getPurchaseButtons() {
		return purchaseButtons;
	}

	public void setPurchaseButtons(Group[] purchaseButtons) {
		this.purchaseButtons = purchaseButtons;
	}
	
}
