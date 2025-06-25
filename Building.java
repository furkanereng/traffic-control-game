/* Yusuf İslam Altunkaynak 
   Furkan Eren Gülçay 
   Enzel Ebrar Albayrak 
   This program creates buildings and adds them to scene. */

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class Building extends Rectangle {
	
	private int type;
	private int rotationDegree;
	private Color color;
	private Color strokeColor;
	private int gridX;
	private int gridY;
	private double coordinateX;
	private double coordinateY;
	private Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.MAGENTA};
	private Color[] strokeColors = {Color.DARKRED, Color.DARKBLUE, Color.DARKGREEN, Color.DARKMAGENTA};
	
	/* Creating building's template. */
	public Building(int type, int rotationDegree, int color, int gridX, int gridY, Level level) {
		this.type = type;
		this.rotationDegree = rotationDegree;
		this.color = colors[color];
		this.strokeColor = strokeColors[color];
		this.gridX = gridX;
		this.gridY = gridY;
		coordinateX = gridX*(level.getLevelWidth()/level.getNumGridX());
		coordinateY = gridY*(level.getLevelHeight()/level.getNumGridY());
		setX(coordinateX-5);
		setY(coordinateY-5);
		setArcWidth(16);
		setArcHeight(16);
		setStrokeWidth(4);
		setFill(Color.DARKGREY);
		setStroke(Color.GREY);
		level.getMap()[0][0].getChildren().add(this);
		level.getBuildings().add(this);
		create(level);
	}
	
	/* Creating buildings with respect to its type. */
	private void create(Level level) {
		switch(type) {
			case 0:
				Rectangle rectangle1 = new Rectangle();
				rectangle1.setArcWidth(16);
				rectangle1.setArcHeight(16);
				rectangle1.setStrokeWidth(4);
				rectangle1.setFill(color);
				rectangle1.setStroke(strokeColor);
				rectangle1.setRotate(rotationDegree);
				rectangle1.setX(coordinateX+5);
				rectangle1.setY(coordinateY+5);
				rectangle1.setWidth(level.getLevelWidth()/level.getNumGridX()*2-10);
				rectangle1.setHeight(level.getLevelHeight()/level.getNumGridY()*2-10);
				Rectangle rectangle2 = new Rectangle();
				rectangle2.setArcWidth(16);
				rectangle2.setArcHeight(16);
				rectangle2.setStrokeWidth(4);
				rectangle2.setFill(color);
				rectangle2.setStroke(strokeColor);
				rectangle2.setRotate(rotationDegree);
				rectangle2.setX(coordinateX+15);
				rectangle2.setY(coordinateY+15);
				rectangle2.setWidth(level.getLevelWidth()/level.getNumGridX()*2-30);
				rectangle2.setHeight(level.getLevelHeight()/level.getNumGridY()*2-30);
				setWidth(level.getLevelWidth()/level.getNumGridX()*2+10);
				setHeight(level.getLevelHeight()/level.getNumGridY()*3+10);
				setRotate(rotationDegree);
				if(rotationDegree==90 || rotationDegree==270) {
					setX(getX()+level.getLevelWidth()/level.getNumGridX()/2);
					setY(getY()-level.getLevelHeight()/level.getNumGridY()/2);
				}
				if(rotationDegree==180) {
					rectangle1.setY(rectangle1.getY()+level.getLevelHeight()/level.getNumGridY());
					rectangle2.setY(rectangle2.getY()+level.getLevelHeight()/level.getNumGridY());
				} else if(rotationDegree==270) {
					rectangle1.setX(rectangle1.getX()+level.getLevelWidth()/level.getNumGridX());
					rectangle2.setX(rectangle2.getX()+level.getLevelWidth()/level.getNumGridX());
				}
				level.getMap()[0][0].getChildren().addAll(rectangle1, rectangle2);
				break;
				
			case 1:
				Ellipse ellipse1 = new Ellipse();
				ellipse1.setFill(color);
				ellipse1.setStroke(strokeColor);
				ellipse1.setStrokeWidth(4);
				ellipse1.setRadiusX((level.getLevelWidth()-20)/level.getNumGridX());
				ellipse1.setRadiusY((level.getLevelHeight()-20)/level.getNumGridY());
				ellipse1.setCenterX((getX()*2+getWidth())/2+level.getLevelWidth()/level.getNumGridX()+5);
				ellipse1.setCenterY((getY()*2+getHeight())/2+level.getLevelHeight()/level.getNumGridY()+5);
				Ellipse ellipse2 = new Ellipse();
				ellipse2.setFill(color);
				ellipse2.setStroke(strokeColor);
				ellipse2.setStrokeWidth(4);
				ellipse2.setRadiusX(ellipse1.getRadiusX()-10);
				ellipse2.setRadiusY(ellipse1.getRadiusY()-10);
				ellipse2.setCenterX(ellipse1.getCenterX());
				ellipse2.setCenterY(ellipse1.getCenterY());
				
				setWidth(level.getLevelWidth()/level.getNumGridX()*2+10);
				setHeight(level.getLevelHeight()/level.getNumGridY()*3+10);
				setRotate(rotationDegree);
				if(rotationDegree==90 || rotationDegree==270) {
					setX(getX()+level.getLevelWidth()/level.getNumGridX()/2);
					setY(getY()-level.getLevelHeight()/level.getNumGridY()/2);
				}
				if(rotationDegree==180) {
					ellipse1.setCenterY(ellipse1.getCenterY()+level.getLevelHeight()/level.getNumGridY());
					ellipse2.setCenterY(ellipse1.getCenterY());
				} else if(rotationDegree==270) {
					ellipse1.setCenterX(ellipse1.getCenterX()+level.getLevelWidth()/level.getNumGridX());
					ellipse2.setCenterX(ellipse1.getCenterX());
				}
				level.getMap()[0][0].getChildren().addAll(ellipse1, ellipse2);
				break;
				
			case 2:
				setWidth(level.getLevelWidth()/level.getNumGridX()+10);
				setHeight(level.getLevelWidth()/level.getNumGridY()+10);
				setX(getX()-2.5);
				setY(getY()-2.5);
				setFill(getColor());
				setStroke(getStrokeColor());
				break;
			default:
		}
	}

	/* The rest are getter/setter methods. */
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRotationDegree() {
		return rotationDegree;
	}

	public void setRotationDegree(int rotationDegree) {
		this.rotationDegree = rotationDegree;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getGridX() {
		return gridX;
	}

	public void setGridX(int gridX) {
		this.gridX = gridX;
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int gridY) {
		this.gridY = gridY;
	}

	public Color[] getColors() {
		return colors;
	}

	public void setColors(Color[] colors) {
		this.colors = colors;
	}

	public Color[] getStrokeColors() {
		return strokeColors;
	}

	public void setStrokeColors(Color[] strokeColors) {
		this.strokeColors = strokeColors;
	}

	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}
	
}
