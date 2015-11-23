import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class OptionsMenu {

	private static Texture backgroundTexture, keyboardTexture, phoneTexture;
	private static Button modeButton, backButton;
	private static ArrayList<Button> buttons;
	private static boolean clicked = false;
	
	public static void loadTextures(){
		backgroundTexture = Helpers.loadTexture("res/optionsBackground.png", "PNG");
		keyboardTexture = Helpers.loadTexture("res/keyboardTexture.png", "PNG");
		phoneTexture = Helpers.loadTexture("res/phoneTexture.png", "PNG");
		buttons = new ArrayList<Button>();
		modeButton  = new Button("Mode", 490, 420, 300, 60, "res/modeButtonNormal.png", "res/modeButtonHover.png", "res/modeButtonClicked.png");
		backButton = new Button("Back", 490, 100, 300, 60, "res/backButtonNormal.png", "res/backButtonHover.png", "res/backButtonClicked.png");
		buttons.add(modeButton);
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
					case "Mode":
						Main.keyboardInput = !Main.keyboardInput;
						Main.networkInput = !Main.networkInput;
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

		if(Main.keyboardInput)
			Helpers.drawTex(keyboardTexture, 460, 200);
		else
			Helpers.drawTex(phoneTexture, 460, 200);
		
	}
}
