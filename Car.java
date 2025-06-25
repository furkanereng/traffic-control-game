/* Yusuf İslam Altunkaynak 
 * Furkan Eren Gülçay 
 * Enzel Ebrar Albayrak  
 * This program creates cars and contains methods related to controlling car traffic.
 */

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Car extends Group {

	private boolean isStopped;
	private boolean isStoppedAtLight;
	private boolean isCrashed;
	private boolean willStopAtSpawn;
	private double speed = 150;
	private Rectangle safeArea;
	private Rectangle car;
	private PathTransition pathTransition;
	private int type;
	
	/* Creating car with a safe distance to control traffic. */
	public Car(int type) {
		Rectangle car = new Rectangle();
		car.setWidth(15);
		car.setHeight(6);
		this.car = car;
		this.type = type;
		changeCar(type);
		Rectangle safeArea = new Rectangle();
		safeArea.setWidth(22);
		safeArea.setHeight(6);
		safeArea.setOpacity(0); 
		this.safeArea = safeArea;
		getChildren().addAll(car, safeArea);
	}
	
	/* Changing car to selected one. */
	private void changeCar(int type) {
		switch(type) {
			case 0:
				car.setFill(Color.BLACK);
				speed = 150;
				break;
			case 1:
				car.setFill(Color.DEEPSKYBLUE);
				speed = 140;
				break;
			case 2:
				car.setFill(Color.CRIMSON);
				speed = 130;
				break;
			case 3:
				car.setFill(Color.GREEN);
				speed = 120;
				break;
			case 4:
				car.setFill(Color.BLUEVIOLET);
				speed = 110;
				break;
			case 5:
				car.setFill(Color.BLUE);
				speed = 100;
				break;
			default:
		}
	}
	
	/* Shape.intersect(Shape, Shape) method returns to an empty Path list when there is
	   no intersection between nodes and we used this property to detect crashes. */
	public boolean areCarsCrashed(Car car) {
		if(!((Path)Shape.intersect(this.getCar(), car.getCar())).getElements().isEmpty())
			return true;
		else return false;
	}

	/* Controls is car at a light or not. */
	public boolean isAtLight(TrafficLight light) {
		if(!((Path)Shape.intersect(this.getCar(), light.getLight())).getElements().isEmpty())
			return true;
		else return false;
	} 
	
	/* Controls the distance between cars. */
	public boolean willCarPause(Car car) {
		if(!((Path)Shape.intersect(this.getSafeArea(), car.getSafeArea())).getElements().isEmpty() && 
				!((Path)Shape.intersect(this.getSafeArea(), car.getCar())).getElements().isEmpty())
			return true;
		else return false;
	}
	
	/* Controls is car at a spawn area. */
	public boolean atSpawnArea(Building spawn) {
		if(!((Path)Shape.intersect(this.getSafeArea(), spawn)).getElements().isEmpty())
			return true;
		else return false;
	}
	
	/* The rest are getter/setter methods. */
	public boolean isStopped() {
		return isStopped;
	}

	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
	}

	public PathTransition getPathTransition() {
		return pathTransition;
	}

	public void setPathTransition(PathTransition pathTransition) {
		this.pathTransition = pathTransition;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Rectangle getSafeArea() {
		return safeArea;
	}

	public void setSafeArea(Rectangle safeArea) {
		this.safeArea = safeArea;
	}

	public Rectangle getCar() {
		return car;
	}

	public void setCar(Rectangle car) {
		this.car = car;
	}

	public boolean isStoppedAtLight() {
		return isStoppedAtLight;
	}

	public void setStoppedAtLight(boolean isStoppedAtLight) {
		this.isStoppedAtLight = isStoppedAtLight;
	}

	public boolean isCrashed() {
		return isCrashed;
	}

	public void setCrashed(boolean isCrashed) {
		this.isCrashed = isCrashed;
	}

	public boolean isWillStopAtSpawn() {
		return willStopAtSpawn;
	}

	public void setWillStopAtSpawn(boolean willStopAtSpawn) {
		this.willStopAtSpawn = willStopAtSpawn;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

} 
