/* Yusuf İslam Altunkaynak 
 * Furkan Eren Gülçay 
 * Enzel Ebrar Albayrak  
 * This program creates the car's path and used for calculating the length of the path. */

import javafx.scene.shape.Path;

public class CarPath extends Path {
	
	private double pathLength;
	private double x;
	private double y;
	
	public double setPathLength(double x, double y) {
		return pathLength += Math.sqrt(Math.pow(getX()-x, 2)+Math.pow(getY()-y, 2));
	}
	
	public double getPathLength() {
		return pathLength;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
}
