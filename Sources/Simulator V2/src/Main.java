
import static org.lwjgl.opengl.GL11.*;


import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;

public class Main implements Runnable{

	private static final String TITLE = "Inverted Pendulum Simulator";
	private static final int[] WINDOW_DIMENSIONS = {1280,720};
	
	public static String GAME_STATE = "MainMenu";
	
	public static boolean keyboardInput = true,
						  networkInput = false,
						  paused = false,
						  mousePressed = false;
	
	public static int level, mouseGrabX, mouseGrabY;

	public static World world = new World(new Vec2(0f, -9.8f), false);

	public static Body box, wheel, boxPlayer2, wheelPlayer2, hill, wall, ledge, floor;
	
	public static float circleRadius = 0.4f;
	
	public static boolean networkReset = true;
	
	public static Texture wheelTexture, floorTexture, boxTexture, boxTexturePlayer2, hillTexture, wallTexture, ledgeTexture, background;

	private static void render(){
		glClear(GL_COLOR_BUFFER_BIT);
		
		//Depending on the current level the method only displays what it has to
		//It also checks for the menus for GAME_STATE
		if(GAME_STATE == "Running" && level == 1){
			Helpers.drawTex(background, 0, 0);
			Helpers.drawTheHill();
			Helpers.drawTheFloor();
			Helpers.drawTheBox();
			Helpers.drawTheWheel();
		} else if(GAME_STATE == "Running" && level == 2){
			Helpers.drawTex(background, 0, 0);
			Helpers.drawTheWall();
			Helpers.drawTheFloor();
			Helpers.drawTheBox();
			Helpers.drawTheWheel();
		}else if(GAME_STATE == "Running" && level == 3){
			Helpers.drawTex(background, 0, 0);
			Helpers.drawTheLedge();
			Helpers.drawTheFloor();
			Helpers.drawTheBox();
			Helpers.drawTheWheel();
		}else if(GAME_STATE == "Running" && level == 4){
			Helpers.drawTex(background, 0, 0);
			Helpers.drawTheFloor();
			Helpers.drawTheBox();
			Helpers.drawTheWheel();
			Helpers.drawTheBox2();
			Helpers.drawTheWheel2();
		} else if(GAME_STATE == "MainMenu"){
			MainMenu.render();
		} else if(GAME_STATE == "OptionsMenu"){
			OptionsMenu.render();
		} else if(GAME_STATE == "Credits"){
			Credits.render();
		} else if(GAME_STATE == "Levels"){
			LevelMenu.render();
		}
		
	}
	private static void outOfScreenLeft(){
		//Checks to see if the robots get out of the screen
		//If they go too far to the left they will be placed just outside of the screen on the right side
		Vec2 newWheelPos = new Vec2(14.0f, wheel.getPosition().y);
		Vec2 newBoxPos = new Vec2(14.0f + (wheel.getPosition().x - box.getPosition().x), box.getPosition().y);
		if(wheel.getPosition().x <= -2.0f){
			wheel.setTransform(newWheelPos, wheel.getAngle());
			box.setTransform(newBoxPos,  box.getAngle());
		}
		if(level==4){
			Vec2 newWheelPos2 = new Vec2(14.0f, wheelPlayer2.getPosition().y);
			Vec2 newBoxPos2 = new Vec2(14.0f + (wheelPlayer2.getPosition().x - boxPlayer2.getPosition().x), boxPlayer2.getPosition().y);
			if(wheelPlayer2.getPosition().x <= -2.0f){
				wheelPlayer2.setTransform(newWheelPos2, wheelPlayer2.getAngle());
				boxPlayer2.setTransform(newBoxPos2,  boxPlayer2.getAngle());
			}
		}
	}
	
	private static void outOfScreenRight(){
		//Checks to see if the robots get out of the screen
		//If they go too far to the right they will be placed just outside of the screen on the left side
		Vec2 newWheelPos = new Vec2(-1.5f, wheel.getPosition().y);
		Vec2 newBoxPos = new Vec2(-1.5f + (wheel.getPosition().x - box.getPosition().x), box.getPosition().y);
		if(wheel.getPosition().x >= 14.8f){
			wheel.setTransform(newWheelPos, wheel.getAngle());
			box.setTransform(newBoxPos,  box.getAngle());
		}
		if(level==4){
			Vec2 newWheelPos2 = new Vec2(-1.5f, wheelPlayer2.getPosition().y);
			Vec2 newBoxPos2 = new Vec2(-1.5f + (wheelPlayer2.getPosition().x - boxPlayer2.getPosition().x), boxPlayer2.getPosition().y);
			if(wheelPlayer2.getPosition().x >= 14.8f){
				wheelPlayer2.setTransform(newWheelPos2, wheelPlayer2.getAngle());
				boxPlayer2.setTransform(newBoxPos2,  boxPlayer2.getAngle());
			}
		}
	}

	private static void logic(){
		//Updates the world and controllers if the game is not paused
		if(GAME_STATE == "Running"){
			world.step(1/60f, 8, 3);
			if(wheel.getPosition().x < 0)
				outOfScreenLeft();
			else if(wheel.getPosition().x > 12.8f)
				outOfScreenRight();
			if(level == 4){
				if(wheelPlayer2.getPosition().x < 0)
					outOfScreenLeft();
				else if(wheelPlayer2.getPosition().x > 12.8f)
					outOfScreenRight();
			}
			Controller.update();
			if(level == 4)
				Controller.updatePlayer2();
		}
	}

	private static void input(){
		//If the mode of the game is set to keyboard input A, D will control player one and if the level is "VERSUS" the left and right arrows will control player two
		if(GAME_STATE == "Running" && keyboardInput == true){
			if(Keyboard.isKeyDown(Keyboard.KEY_D) && !Keyboard.isKeyDown(Keyboard.KEY_A)){
				Controller.setDesiredAngle(-10);
			}else if(Keyboard.isKeyDown(Keyboard.KEY_A) && !Keyboard.isKeyDown(Keyboard.KEY_D)){
				Controller.setDesiredAngle(10);
			}else{
				Controller.setDesiredAngle(0);
			}
			
			if(level == 4){
				if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
					Controller.setDesiredAnglePlayer2(-10);
				}else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
					Controller.setDesiredAnglePlayer2(10);
				}else{
					Controller.setDesiredAnglePlayer2(0);
				}
			}
		} 
		
		//Check if ESC key is pressed in order to show the menu and pause the game
		if(GAME_STATE == "Running"){
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
			{
				GAME_STATE = "MainMenu";
				paused = true;
			}		
		}
	}

	private static void cleanUp(boolean crash){
		//Frees up the memory
		Display.destroy();
		System.exit(crash ? 1 : 0);
	}

	private static void setupMatrices(){
		//Sets up openGl in projection mode and enables texture drawing capabilities
		glMatrixMode(GL_PROJECTION);
		glOrtho(0, 1280, 0, 720, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void setupMenus(){
		//Loads all the textures and buttons for the menus
		MainMenu.loadTextures();
		OptionsMenu.loadTextures();
		Credits.loadTextures();
		LevelMenu.loadTextures();
	}
	public static void setupObjects(){
		//Loads the textures for the objects and depending on the level creates whichever objects are needed
		
		wheelTexture = Helpers.loadTexture("res/wheelTexture.png", "PNG");
		floorTexture = Helpers.loadTexture("res/floorTexture.png", "PNG");
		boxTexture = Helpers.loadTexture("res/boxTexture.png", "PNG");
		boxTexturePlayer2 = Helpers.loadTexture("res/boxTexturePlayer2.png", "PNG");
		hillTexture = Helpers.loadTexture("res/hillTexture.png", "PNG");
		wallTexture = Helpers.loadTexture("res/wallTexture.png", "PNG");
		ledgeTexture = Helpers.loadTexture("res/ledgeTexture.png", "PNG");
		background = Helpers.loadTexture("res/background.png", "PNG");
		
		if(level == 1)
			LevelSelector.loadLevel1();
		else if(level == 2)
			LevelSelector.loadLevel2();
		else if(level == 3)
			LevelSelector.loadLevel3();
		else if(level == 4)
			LevelSelector.loadLevel4();
	}

	private static void update(){
		//Updates the display and syncs it to 60 fps
		Display.update();
		Display.sync(60);
	}

	private static void enterGameLoop(){
		//Keeps repeating the game loop until the close button is pressed
		while(!Display.isCloseRequested()){
			render();
			logic();
			input();
			update();
		}
	}

	private static void setupDisplay(){
		//Creates a new display with the desired title and dimensions
		try{
			Display.setDisplayMode(new DisplayMode(WINDOW_DIMENSIONS[0], WINDOW_DIMENSIONS[1]));
			Display.setTitle(TITLE);
			Display.create();
		} catch(LWJGLException e){
			e.printStackTrace();
			cleanUp(true);
		}	
	}

	public static void main(String[] args){
		Main main = new Main(); //Used for the main thread
		Thread networkThread = new Thread(new Networking()); //Defining the network thread
		networkThread.start(); //Starting the network thread
		Thread thread = new Thread(main); //Defining the main thread
		thread.start(); //Starting the main thread

	}

	@Override
	public void run() {
		//When the thread starts it will go through loading everything once and then starting the main game loop 
		setupDisplay();
		setupMenus();
		setupObjects();
		setupMatrices();
		enterGameLoop();
		cleanUp(false);
	}

	public void start(){
		run();
	}
}
