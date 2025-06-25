/* Yusuf İslam Altunkaynak 
 * Furkan Eren Gülçay 
 * Enzel Ebrar Albayrak  
 * This program creates the road tiles and adds to the level. */

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class RoadTile {

	private int type;
	private int rotationDegree;
	private int gridX;
	private int gridY;
	
	public RoadTile(int type, int rotationDegree, int gridX, int gridY, Level level) {
		this.type = type;
		this.rotationDegree = rotationDegree;
		this.gridX = gridX;
		this.gridY = gridY;
		create(level);
	}
	
	/* Creating road tiles according to type and rotation degrees. */
	private void create(Level level) {
		switch(type) {
			case 0:
				Rectangle roadTile0 = new Rectangle();
				if(rotationDegree == 0 || rotationDegree == 180) {
					roadTile0.setWidth(level.getLevelWidth()/level.getNumGridX());
					roadTile0.setHeight(level.getLevelHeight()/level.getNumGridY()-10);
					roadTile0.setX(gridX*level.getLevelWidth()/level.getNumGridX()-2.5);
					roadTile0.setY(gridY*level.getLevelHeight()/level.getNumGridY()+2.5);
				} else {
					roadTile0.setWidth(level.getLevelWidth()/level.getNumGridX()-10);
					roadTile0.setHeight(level.getLevelHeight()/level.getNumGridY());
					roadTile0.setX(gridX*level.getLevelWidth()/level.getNumGridX()+2.5);
					roadTile0.setY(gridY*level.getLevelHeight()/level.getNumGridY()-2.5);
				}
				roadTile0.setFill(Color.WHITE);
				level.getMap()[0][0].getChildren().add(roadTile0);
				break;
				
			case 1:
				Arc roadTile1 = new Arc();
				roadTile1.setLength(90);
				roadTile1.setFill(Color.WHITE);
				roadTile1.setType(ArcType.ROUND);
				roadTile1.setStartAngle(rotationDegree);
				Arc roadTile1inner = new Arc();
				roadTile1inner.setLength(90);
				roadTile1inner.setFill(Color.rgb(155, 198, 223));
				roadTile1inner.setType(ArcType.ROUND);
				roadTile1inner.setRadiusX(5);
				roadTile1inner.setRadiusY(5);
				roadTile1inner.setStartAngle(rotationDegree);
				switch (rotationDegree) {
				case 0:
					roadTile1.setCenterX(gridX*level.getLevelWidth()/level.getNumGridX()-2.5);
					roadTile1.setCenterY((gridY+1)*level.getLevelHeight()/level.getNumGridY()-2.5);
					break;
				case 90:
					roadTile1.setCenterX((gridX+1)*level.getLevelWidth()/level.getNumGridX()-2.5);
					roadTile1.setCenterY((gridY+1)*level.getLevelHeight()/level.getNumGridY()-2.5);
					break;
				case 180:
					roadTile1.setCenterX((gridX+1)*level.getLevelWidth()/level.getNumGridX()-2.5);
					roadTile1.setCenterY(gridY*level.getLevelHeight()/level.getNumGridY()-2.5);
					break;
				case 270:
					roadTile1.setCenterX(gridX*level.getLevelWidth()/level.getNumGridX()-2.5);
					roadTile1.setCenterY(gridY*level.getLevelHeight()/level.getNumGridY()-2.5);
					break;
				default:
				}
				roadTile1.setRadiusX(level.getLevelWidth()/level.getNumGridX()-5);
				roadTile1.setRadiusY(level.getLevelHeight()/level.getNumGridY()-5);
				roadTile1inner.setCenterX(roadTile1.getCenterX());
				roadTile1inner.setCenterY(roadTile1.getCenterY());
				level.getMap()[0][0].getChildren().addAll(roadTile1, roadTile1inner);
				break;
				
			case 2:
				Polygon roadTile2 = new Polygon();
				roadTile2.getPoints().add(gridX*(level.getLevelWidth()/level.getNumGridX())-2.5);
				roadTile2.getPoints().add(gridY*(level.getLevelHeight()/level.getNumGridY())+5-2.5);
				roadTile2.getPoints().add(gridX*(level.getLevelWidth()/level.getNumGridX())+5-2.5);
				roadTile2.getPoints().add(gridY*(level.getLevelHeight()/level.getNumGridY())+5-2.5);
				roadTile2.getPoints().add(gridX*(level.getLevelWidth()/level.getNumGridX())+5-2.5);
				roadTile2.getPoints().add(gridY*(level.getLevelHeight()/level.getNumGridY())-2.5);
				roadTile2.getPoints().add((gridX+1)*(level.getLevelWidth()/level.getNumGridX())-5-2.5);
				roadTile2.getPoints().add(gridY*(level.getLevelHeight()/level.getNumGridY())-2.5);
				roadTile2.getPoints().add((gridX+1)*(level.getLevelWidth()/level.getNumGridX())-5-2.5);
				roadTile2.getPoints().add(gridY*(level.getLevelHeight()/level.getNumGridY())+5-2.5);
				roadTile2.getPoints().add((gridX+1)*(level.getLevelWidth()/level.getNumGridX())-2.5);
				roadTile2.getPoints().add(gridY*(level.getLevelHeight()/level.getNumGridY())+5-2.5);
				roadTile2.getPoints().add((gridX+1)*(level.getLevelWidth()/level.getNumGridX())-2.5);
				roadTile2.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-5-2.5);
				roadTile2.getPoints().add((gridX+1)*(level.getLevelWidth()/level.getNumGridX())-5-2.5);
				roadTile2.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-5-2.5);
				roadTile2.getPoints().add((gridX+1)*(level.getLevelWidth()/level.getNumGridX())-5-2.5);
				roadTile2.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-2.5);
				roadTile2.getPoints().add(gridX*(level.getLevelWidth()/level.getNumGridX())+5-2.5);
				roadTile2.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-2.5);
				roadTile2.getPoints().add(gridX*(level.getLevelWidth()/level.getNumGridX())+5-2.5);
				roadTile2.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-5-2.5);
				roadTile2.getPoints().add(gridX*(level.getLevelWidth()/level.getNumGridX())-2.5);
				roadTile2.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-5-2.5);
				roadTile2.setFill(Color.WHITE);
				roadTile2.setStroke(Color.WHITE);
				level.getMap()[0][0].getChildren().add(roadTile2);
				break;
				
			case 3:
				Polygon roadTile3 = new Polygon();
				roadTile3.getPoints().add(gridX*(level.getLevelWidth()/level.getNumGridX())-2.5);
				roadTile3.getPoints().add(gridY*(level.getLevelHeight()/level.getNumGridY())+5-2.5);
				roadTile3.getPoints().add((gridX+1)*(level.getLevelWidth()/level.getNumGridX())-2.5);
				roadTile3.getPoints().add(gridY*(level.getLevelHeight()/level.getNumGridY())+5-2.5);
				roadTile3.getPoints().add((gridX+1)*(level.getLevelWidth()/level.getNumGridX())-2.5);
				roadTile3.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-5-2.5);
				roadTile3.getPoints().add((gridX+1)*(level.getLevelWidth()/level.getNumGridX())-5-2.5);
				roadTile3.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-5-2.5);
				roadTile3.getPoints().add((gridX+1)*(level.getLevelWidth()/level.getNumGridX())-5-2.5);
				roadTile3.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-2.5);
				roadTile3.getPoints().add(gridX*(level.getLevelWidth()/level.getNumGridX())+5-2.5);
				roadTile3.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-2.5);
				roadTile3.getPoints().add(gridX*(level.getLevelWidth()/level.getNumGridX())+5-2.5);
				roadTile3.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-5-2.5);
				roadTile3.getPoints().add(gridX*(level.getLevelWidth()/level.getNumGridX())-2.5);
				roadTile3.getPoints().add((gridY+1)*(level.getLevelHeight()/level.getNumGridY())-5-2.5);
				roadTile3.setRotate(rotationDegree);
				if(rotationDegree==90) {
					roadTile3.setTranslateX(roadTile3.getTranslateX()-2.5);
					roadTile3.setTranslateY(roadTile3.getTranslateY()-2.5);
				} else if(rotationDegree==180) {
					roadTile3.setTranslateY(roadTile3.getTranslateY()-5);
				} else if(rotationDegree==270) {
					roadTile3.setTranslateX(roadTile3.getTranslateX()+2.5);
					roadTile3.setTranslateY(roadTile3.getTranslateY()-2.5);
				}
				roadTile3.setFill(Color.WHITE);
				roadTile3.setStroke(Color.WHITE);
				level.getMap()[0][0].getChildren().add(roadTile3);
				break;
			default:
		}
	}

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
	
}
