
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;

public class Credits {
	/**
	 * Displays the credits menu
	 */

	private static Texture backgroundTexture;
	private static Button backButton;
	private static boolean clicked = true;
	
	public static void loadTextures(){
		backgroundTexture = Helpers.loadTexture("res/creditsBackground.png", "PNG");
		backButton = new Button("Back", 490, 100, 300, 60, "res/backButtonNormal.png", "res/backButtonHover.png", "res/backButtonClicked.png");
	}
	
	public static void render(){
		Helpers.drawTex(backgroundTexture, 0, 0);
		boolean changed = false;
		if(backButton.isCollision(Mouse.getX(), Mouse.getY())){
			
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
				backButton.setMode(2);
				Main.GAME_STATE = "MainMenu";
			} else {
				backButton.setMode(1);
			}
			
		}else{
			backButton.setMode(0);
		}
		
		
		backButton.render();
	}
}
