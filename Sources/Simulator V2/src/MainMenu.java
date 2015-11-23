import java.util.ArrayList;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class MainMenu {

	private static Texture backgroundTexture;
	private static Button levelsButton, optionsButton, creditsButton, resumeButton, resetButton;
	private static ArrayList<Button> buttons;
	private static boolean clicked = false, resumeButtonActive = false;
	
	public static void loadTextures(){
		backgroundTexture = Helpers.loadTexture("res/menuBackground.png", "PNG");
		buttons = new ArrayList<Button>();
		
		levelsButton = new Button("Levels", 490, 260, 300, 60, "res/levelsButtonNormal.png", "res/levelsButtonHover.png", "res/levelsButtonClicked.png");
		buttons.add(levelsButton);
		
		optionsButton = new Button("Options", 490, 180, 300, 60, "res/optionsButtonNormal.png", "res/optionsButtonHover.png", "res/optionsButtonClicked.png");
		buttons.add(optionsButton);
		
		creditsButton = new Button("Credits", 490, 100, 300, 60, "res/creditsButtonNormal.png", "res/creditsButtonHover.png", "res/creditsButtonClicked.png");
		buttons.add(creditsButton);
	}
	
	public static void render(){
		if(resumeButtonActive == false && Main.paused == true){
			resumeButton  = new Button("Resume", 490, 420, 300, 60, "res/resumeButtonNormal.png", "res/resumeButtonHover.png", "res/resumeButtonClicked.png");
			buttons.add(resumeButton);
			
			resetButton = new Button("Reset", 490, 340, 300, 60, "res/resetButtonNormal.png", "res/resetButtonHover.png", "res/resetButtonClicked.png");
			buttons.add(resetButton);
			resumeButtonActive = true;
			
			
		}
		
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
					case "Options":
						Main.GAME_STATE = "OptionsMenu";
						break;
					case "Credits":
						Main.GAME_STATE = "Credits";
						break;
					case "Resume":
						Main.GAME_STATE = "Running";
						Main.paused = false;
						break;
					case "Reset":
						Main.world = new World(new Vec2(0f, -9.8f), false);
						Main.setupObjects();
						Main.GAME_STATE = "Running";
						Main.paused = false;
						break;
					case "Levels":
						Main.GAME_STATE = "Levels";
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
