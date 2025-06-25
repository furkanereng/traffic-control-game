/* Yusuf İslam Altunkaynak 
 * Furkan Eren Gülçay 
 * Enzel Ebrar Albayrak  
 * This program creates the levels depending on level files and runs the level. */

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.PathTransition.OrientationType;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Level extends GridPane {

	private double levelWidth;
	private double levelHeight;
	private int numGridX;
	private int numGridY;
	private int numPaths;
	private int win;
	private int lose;
	private double time; 
	private MapTile[][] map; // Holds the panes which are inside the Level (grid pane).
	private ArrayList<CarPath> paths = new ArrayList<>();
	private ArrayList<Car> cars = new ArrayList<>();
	private ArrayList<TrafficLight> lights = new ArrayList<>();
	private ArrayList<Building> buildings = new ArrayList<>();
	private StackPane root; // Used for switching between scenes.
	private MainScreen ms; // Used for switching between scenes.
	private AnimationTimer timer;
	private int crashes;
	private int arrived;
	private int gain;
	private int levelIndex;
	private Text scoreCounter;
	private Text crashCounter;

	/* Since we need multiple panes for GridPane, we did it easier with an inner class. */
	public class MapTile extends Pane {
		private MapTile() {
			setGridLinesVisible(true);
			this.setPrefSize(2000, 2000);
		}
	}
	
	/* No-arguments constructor for creating the grid background. */
	public Level() {
		setLevelWidth(800);
		setLevelHeight(800);
		setNumGridX(15);
		setNumGridY(15);
		setMap(new MapTile[numGridX][numGridY]);
		setBackground(new Background(
				new BackgroundFill(Color.rgb(155, 198, 223), new CornerRadii(0), new Insets(0))));
		for (int i = 0; i < numGridX; i++)
			for (int j = 0; j < numGridY; j++)
				this.add(map[i][j] = new MapTile(), i, j);
		setGridLinesVisible(true);
	} 

	/* Calls the method which'll read content of level file and it'll generate the level. */
	public Level(String level, StackPane root, MainScreen ms) throws Exception {
		this.root = root;
		this.ms = ms;
		readLevel(level);
		scoreCounter = new Text("Score: " + arrived + "/" + win);
		scoreCounter.setFont(new Font("", 24));
		scoreCounter.setX(10);
		scoreCounter.setY(50);
		getMap()[0][0].getChildren().add(scoreCounter);
		crashCounter = new Text("Crashes: " + crashes + "/" + lose);
		crashCounter.setFont(new Font("", 24));
		crashCounter.setX(10);
		crashCounter.setY(75);
		getMap()[0][0].getChildren().add(crashCounter);
		root.getChildren().removeAll();
		root.getChildren().add(this); // Changing scene to this level.
		createTraffic();
	}

	/* Reads the content of input file and creates the game components, gets win/lose conditions etc. */
	private void readLevel(String level) throws Exception {
		File lvl = new File(level);
		Scanner input = new Scanner(lvl);
		String levelIndexString = level.charAt(5) + "";
		this.levelIndex = Integer.parseInt(levelIndexString);

		/* Reads each line of input file and gets them into an array list. */
		ArrayList<String> lines = new ArrayList<>();
		while (input.hasNext())
			lines.add(input.nextLine());
		input.close();

		/* Gets each line from the array list and splits each line's words and returns them in an array. */
		ArrayList<String[]> words = new ArrayList<>();
		for (String wordsOfLine : lines)
			words.add(wordsOfLine.split(" "));

		/* Creates the game components and sets level. */
		for (String[] word : words) {
			switch (word[0]) {
			case "Metadata":
				setLevelWidth(Double.parseDouble(word[1]));
				setLevelHeight(Double.parseDouble(word[2]));
				setNumGridX(Integer.parseInt(word[3]));
				setNumGridY(Integer.parseInt(word[4]));
				setNumPaths(Integer.parseInt(word[5]));
				setWin(Integer.parseInt(word[6]));
				setLose(Integer.parseInt(word[7]));
				setMap(new MapTile[numGridX][numGridY]);
				setBackground(new Background(
						new BackgroundFill(Color.rgb(155, 198, 223), new CornerRadii(0), new Insets(0))));
				for (int i = 0; i < numGridX; i++)
					for (int j = 0; j < numGridY; j++)
						this.add(map[i][j] = new MapTile(), i, j);
				setGridLinesVisible(true);
				break;

			case "Building":
				int type = Integer.parseInt(word[1]);
				int rotationDegree = Integer.parseInt(word[2]);
				int color = Integer.parseInt(word[3]);
				int gridX = Integer.parseInt(word[4]);
				int gridY = Integer.parseInt(word[5]);
				new Building(type, rotationDegree, color, gridX, gridY, this);
				break;

			case "TrafficLight":
				double x1 = Double.parseDouble(word[1]);
				double y1 = Double.parseDouble(word[2]);
				double x2 = Double.parseDouble(word[3]);
				double y2 = Double.parseDouble(word[4]);
				lights.add(new TrafficLight(x1, y1, x2, y2, this));
				break;

			case "RoadTile":
				type = Integer.parseInt(word[1]);
				rotationDegree = Integer.parseInt(word[2]);
				gridX = Integer.parseInt(word[3]);
				gridY = Integer.parseInt(word[4]);
				new RoadTile(type, rotationDegree, gridX, gridY, this);
				break;

			case "Path":
				int pathIndex = Integer.parseInt(word[1]);
				switch (word[2]) {
				case "MoveTo":
					paths.add(new CarPath());
					paths.get(pathIndex).getElements()
							.add(new MoveTo(Double.parseDouble(word[3]), Double.parseDouble(word[4])));
					break;
				case "LineTo":
					paths.get(pathIndex).getElements()
							.add(new LineTo(Double.parseDouble(word[3]), Double.parseDouble(word[4])));
					paths.get(pathIndex).setPathLength(Double.parseDouble(word[3]), Double.parseDouble(word[4]));
					break;
				default:
				}
				paths.get(pathIndex).setX(Double.parseDouble(word[3]));
				paths.get(pathIndex).setY(Double.parseDouble(word[4]));
				paths.get(pathIndex).setOpacity(0);
				break;

			default:
			}
		}

	}

	/* Creates the AnimationTimer which will check each frame of the animation. */
	private void createTraffic() {
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if(crashes>=lose || arrived>=win) {
					timer.stop();
					for(Car car: cars)
						car.getPathTransition().stop();
				}
				try {
					update();
				} catch (Exception e) {
				}
			}
		};
		timer.start();
	}

	/* This method is called for each frames of animation. Handles car movements and traffic. */
	private void update() throws Exception {
		time += 0.16; 
		
		/* Checking traffic lights and cars will stop if light is red. */
		for (TrafficLight light : lights)
			for (Car car : cars)
				if (car.isAtLight(light))
					if (light.isRed()) {
						car.getPathTransition().pause();
						car.setStopped(true);
						car.setStoppedAtLight(true);
					} else {
						car.setStopped(false);
						car.setStoppedAtLight(false);
						car.getPathTransition().play();
					}
		
		/* Checking is there any car in front of the car and if there is not, checks that is car stopped at light or not.*/
		for (Car car1 : cars) {
			boolean isThereCar = false;
			for (Car car2 : cars)
				if (car1 != car2 && !car1.isWillStopAtSpawn())
					if (car1.willCarPause(car2)) {
						if (car2.isStopped()) {
							car1.setStopped(true);
							car1.getPathTransition().pause();
						} else if (car2.isStopped() || car2.isStoppedAtLight()) {
							car1.setStopped(false);
							car1.getPathTransition().play();
						}
						isThereCar=true;
					} else if (!car1.isStopped() && !car1.isStoppedAtLight()) {
						car1.setStopped(false);
						car1.getPathTransition().play();
					} 
			if(!isThereCar && !car1.isStoppedAtLight()) {
				car1.setStopped(false);
				car1.getPathTransition().play();
			}
		}
		
		/* When cars crashed, removes the car after 0.5s delay. Updates number of crashes. */
		for (Car car1 : cars)
			for (Car car2 : cars)
				/* Checking car1 and car2 are crashed or not for calling this just for once. */
				if (car1 != car2 && car1.areCarsCrashed(car2) && !car1.isCrashed() && !car2.isCrashed()) {
					car1.setStopped(true);
					car2.setStopped(true);
					car1.setCrashed(true);
					car2.setCrashed(true);
					car1.getPathTransition().stop();
					car2.getPathTransition().stop();
					PauseTransition pauseTransition = new PauseTransition(Duration.millis(500));
					pauseTransition.setOnFinished(event -> {
						car1.setStopped(false);
						car2.setStopped(false);
						car1.setCrashed(false);
						car2.setCrashed(false);
						cars.remove(car1);
						cars.remove(car2);
						getMap()[0][0].getChildren().removeAll(car1, car2);
						car1.setVisible(false);
						car2.setVisible(false);
						gain--;
						crashes++;
					});
					pauseTransition.play();
				}
		
		/* Spawns car at random time. */
		if (time > 2) {
			if (Math.random() < 0.3) {
				spawnCar();
			}
			time = 0;
		}
		
		/* Updating Score and Crash counters. */
		scoreCounter.setText("Score: " + arrived + "/" + win);
		crashCounter.setText("Crashes: " + crashes + "/" + lose);
		
		/* Checking level's ending conditions and shows end screen if level is over. */
		if(arrived==win || crashes==lose) {
			if(gain<0)
				gain=0;
			if(arrived==win && levelIndex!=5)
				ms.getSwitcher().getIsUnlocked()[levelIndex] = true;
			new EndScreen(root, ms, levelIndex, gain, arrived, win, crashes, lose);
		}
		
	}

	/* Creating the car and spawning it at random path. Setting car's transition and updating arrived cars. */
	private void spawnCar() {
		Car car = new Car(ms.getMarket().getSelectedCar());
		PathTransition pt = new PathTransition();
		car.setPathTransition(pt);
		pt.setPath(paths.get((int) (Math.random() * numPaths)));
		pt.setDuration(new Duration(((CarPath) (pt.getPath())).getPathLength() / car.getSpeed() * 1000));
		pt.setNode(car);
		pt.setOrientation(OrientationType.ORTHOGONAL_TO_TANGENT);
		pt.setInterpolator(Interpolator.LINEAR);
		Group g = new Group(pt.getPath(), car);
		car.setVisible(true);
		
		/* Finding spawning buildings. May also contain ending building too. */
		ArrayList<Building> spawns = new ArrayList<>();
		for(Building spawnArea: buildings) 
			if(((Shape)car.getPathTransition().getPath()).intersects(spawnArea.getBoundsInParent())) 
				spawns.add(spawnArea);
		
		/* Checking is there any car at buildings. */
		boolean isThereCar=false;
		for(Car car1: cars) 
			for(Building spawn: spawns)
				if(car1.atSpawnArea(spawn)) 
					isThereCar=true;
		
		/* Preventing spawning of cars at spawning areas if there is any car. */
		if(!isThereCar) {
			cars.add(car);
			this.getMap()[0][0].getChildren().add(g);
			car.setVisible(true);
			pt.play();
		}
		
		/* Removes car from scene when ended and updates it as arrived. */
		pt.setOnFinished(e -> {
			pt.stop();
			cars.remove(car);
			this.getMap()[0][0].getChildren().remove(g);
			arrived++;
			gain += 2;
		});
	}

	/* The rest are getter/setter methods. */
	public double getLevelWidth() {
		return levelWidth;
	}

	public void setLevelWidth(double levelWidth) {
		this.levelWidth = levelWidth;
	}

	public double getLevelHeight() {
		return levelHeight;
	}

	public void setLevelHeight(double levelHeight) {
		this.levelHeight = levelHeight;
	}

	public int getNumGridX() {
		return numGridX;
	}

	public void setNumGridX(int numGridX) {
		this.numGridX = numGridX;
	}

	public int getNumGridY() {
		return numGridY;
	}

	public void setNumGridY(int numGridY) {
		this.numGridY = numGridY;
	}

	public int getNumPaths() {
		return numPaths;
	}

	public void setNumPaths(int numPaths) {
		this.numPaths = numPaths;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public MapTile[][] getMap() {
		return map;
	}

	public void setMap(MapTile[][] map) {
		this.map = map;
	}

	public int getArrived() {
		return arrived;
	}

	public void setArrived(int arrived) {
		this.arrived = arrived;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public ArrayList<CarPath> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<CarPath> paths) {
		this.paths = paths;
	}

	public ArrayList<Car> getCars() {
		return cars;
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public ArrayList<TrafficLight> getLights() {
		return lights;
	}

	public void setLights(ArrayList<TrafficLight> lights) {
		this.lights = lights;
	}

	public int getCrashes() {
		return crashes;
	}

	public void setCrashes(int crashes) {
		this.crashes = crashes;
	}

	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	public void setSpawnAreas(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}

}
