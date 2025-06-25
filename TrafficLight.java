/* Yusuf İslam Altunkaynak 
 * Furkan Eren Gülçay 
 * Enzel Ebrar Albayrak  
 * This program is creating traffic lights and adds to scene. */

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class TrafficLight extends Group {

	private double x1;
	private double y1;
	private double x2;
	private double y2;
	private boolean isRed;
	private Circle light;
	
	public TrafficLight(double x1, double y1, double x2, double y2, Level level) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.isRed = false;
		
		/* We explained how did we implement and handle the problems in the report. */
		int gridX = (int)(((x1+x2)/2)/(level.getLevelWidth()/level.getNumGridX()));
		int gridY = (int)(((y1+y2)/2)/(level.getLevelHeight()/level.getNumGridY()));
		Line line = new Line(x1%(level.getLevelWidth()/level.getNumGridX()), y1%(level.getLevelHeight()/level.getNumGridY()),
					x2%(level.getLevelWidth()/level.getNumGridX()), y2%(level.getLevelHeight()/level.getNumGridY()));
		
		line.setStrokeWidth(0.5);
		line.setFill(Color.BLACK);
		line.setStroke(Color.BLACK);
		level.getMap()[gridX][gridY].getChildren().add(line);
		
		Circle circle = new Circle();
		circle.setRadius(5);
		circle.setTranslateX((line.getStartX()+line.getEndX())/2);
		circle.setTranslateY((line.getStartY()+line.getEndY())/2);
		circle.setFill(Color.GREEN);
		circle.setStroke(Color.BLACK);	
		circle.setStrokeWidth(0.7);
		setLight(circle);
		this.getChildren().addAll(line, circle);
		level.getMap()[gridX][gridY].getChildren().add(this);

		setOnMouseClicked(e -> {
			if(isRed) 
				light.setFill(Color.GREEN);
			else 
				light.setFill(Color.RED);
			isRed = !isRed;
		});
	}

	/* The rest are getter/setter methods. */
	public double getX1() {
		return x1;
	}

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	public boolean isRed() {
		return isRed;
	}

	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}

	public Circle getLight() {
		return light;
	}

	public void setLight(Circle light) {
		this.light = light;
	}
	
}
