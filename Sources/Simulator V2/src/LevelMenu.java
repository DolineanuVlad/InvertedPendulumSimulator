import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class LevelMenu {
	
	private static Texture backgroundTexture;
	private static Button hillLevelButton, wallLevelButton, ledgeLevelButton, vsLevelButton, backButton;
	private static ArrayList<Button> buttons;
	private static boolean clicked = true;
	
	public static void loadTextures(){
		backgroundTexture = Helpers.loadTexture("res/levelsBackground.png", "PNG");
		
		buttons = new ArrayList<Button>();
		hillLevelButton  = new Button("HillLevel", 490, 420, 300, 60, "res/hillButtonNormal.png", "res/hillButtonHover.png", "res/hillButtonClicked.png");
		buttons.add(hillLevelButton);
		
		wallLevelButton = new Button("WallLevel", 490, 340, 300, 60, "res/wallButtonNormal.png", "res/wallButtonHover.png", "res/wallButtonClicked.png");
		buttons.add(wallLevelButton);
		
		ledgeLevelButton = new Button("LedgeLevel", 490, 260, 300, 60, "res/ledgeButtonNormal.png", "res/ledgeButtonHover.png", "res/ledgeButtonClicked.png");
		buttons.add(ledgeLevelButton);
		
		vsLevelButton = new Button("VsLevel", 490, 180, 300, 60, "res/vsButtonNormal.png", "res/vsButtonHover.png", "res/vsButtonClicked.png");
		buttons.add(vsLevelButton);
		
		backButton = new Button("Back", 490, 100, 300, 60, "res/backButtonNormal.png", "res/backButtonHover.png", "res/backButtonClicked.png");
		buttons.add(backButton);
	}
	
	public static void render(){
		
		Helpers.drawTex(backgroundTexture, 0, 0);
		Boolean changed = false;
		
		for(Button b : buttons){
			
			int mouseX = Mouse.getX();
			int mouseY = Mouse.getY();
			if(b.isCollision(mouseX, mouseY)){
				
				if(Mouse.isButtonDown(0) && clicked == false){
					clicked = true;
					changed = true;
				}
				else if(Mouse.isButtonDown(0) == false)
				{
					clicked = false;
					changed = true;
				}
				if(clicked && changed){
					b.setMode(2);
					switch(b.getLabel()){
					case "HillLevel":
						Main.world = new World(new Vec2(0f, -9.8f), false);
						Main.level = 1;
						Main.setupObjects();
						Main.networkReset = true;
						Main.GAME_STATE = "Running";
						break;
					case "WallLevel":
						Main.world = new World(new Vec2(0f, -9.8f), false);
						Main.level = 2;
						Main.setupObjects();
						Main.networkReset = true;
						Main.GAME_STATE = "Running";
						break;
					case "LedgeLevel":
						Main.world = new World(new Vec2(0f, -9.8f), false);
						Main.level = 3;
						Main.setupObjects();
						Main.networkReset = true;
						Main.GAME_STATE = "Running";
						break;
					case "VsLevel":
						Main.world = new World(new Vec2(0f, -9.8f), false);
						Main.level = 4;
						Main.setupObjects();
						Main.networkReset = true;
						Main.GAME_STATE = "Running";
						break;
					case "Back":
						Main.GAME_STATE = "MainMenu";
						break;
					}
				} else {
					b.setMode(1);
				}
				
			}else{
				b.setMode(0);
			}
		}
		
		for(Button b : buttons){
			b.render();
		}
		
	}
}
