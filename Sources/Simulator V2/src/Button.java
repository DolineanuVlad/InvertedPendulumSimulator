import org.newdawn.slick.opengl.Texture;

public class Button {
	/*
	 * The button class contains the coordinates of the button, its dimensions as well as a label which is used to identify the button
	 * It has 3 different textures for the modes : normal, hovered, clicked  
	 */
	private int x, y, w, h, mode;
	private String label;
	private Texture normalTex, hoverTex, clickedTex;
	
	public Button(String label, int x, int y, int w, int h, String normal, String hover, String clicked){
		this.label = label;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		normalTex = Helpers.loadTexture(normal, "PNG");
		hoverTex = Helpers.loadTexture(hover, "PNG");
		clickedTex = Helpers.loadTexture(clicked, "PNG");
		mode = 0;
	}
	
	public boolean isCollision(int mouseX, int mouseY){
		/**
		 * Checks to see if the coordinates passed as arguments are on the button
		 * Returns true if they do, false if they don't
		 */
		if(mouseX > x && mouseX <x+w){
			if(mouseY > y && mouseY <y+h){
				return true;
			}
		}
		return false;
	}
	
	public void setMode(int value){
		/**
		 * Sets the current mode of the button
		 */
		mode = value;
	}
	
	public String getLabel(){
		return label;
	}
	
	public void render(){
		/**
		 * Depending on the mode, it displays the corresponding texture
		 */
		if(mode == 0)
			Helpers.drawTex(normalTex, x, y);
		else if(mode == 1)
			Helpers.drawTex(hoverTex, x, y);
		else if(mode == 2)
			Helpers.drawTex(clickedTex, x, y);
	}
}
