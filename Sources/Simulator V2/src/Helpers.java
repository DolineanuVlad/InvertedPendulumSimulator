import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;

import org.jbox2d.common.Vec2;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Helpers {

	public static Texture loadTexture(String path, String fileType){
		//Loads a texture from the disk and returns a Texture object
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}
	
	public static void drawTex(Texture tex, float x, float y){
		//Draws a texture on the coordinates x, y
		tex.bind();
		glBegin(GL_QUADS);
	    glTexCoord2f(0, 0);
	    glVertex2f(x, y);
	 
	    glTexCoord2f(tex.getWidth(), 0);
	    glVertex2f(x+tex.getImageWidth(), y);
	 
	    glTexCoord2f(tex.getWidth(), tex.getHeight());
	    glVertex2f(x+tex.getImageWidth(),y+tex.getImageHeight());
	 
	    glTexCoord2f(0, tex.getHeight());
	    glVertex2f(x,y+tex.getImageHeight());
	    glEnd();
	    glLoadIdentity();
	}
	
	public static void drawTheBox(){
		//Draws the robot body for player 1
		glPushMatrix();
		Vec2 boxPosition = Main.box.getPosition().mul(100);
		glTranslatef(boxPosition.x, boxPosition.y, 0);
		glRotated(Math.toDegrees(Main.box.getAngle()), 0f, 0f, 1f);
		drawTex(Main.boxTexture, -0.25f * 100f, -1.0f * 100f);
		glPopMatrix();
	}
	
	public static void drawTheBox2(){
		//Draws the robot body for player 2
		glPushMatrix();
		Vec2 boxPosition = Main.boxPlayer2.getPosition().mul(100);
		glTranslatef(boxPosition.x, boxPosition.y, 0);
		glRotated(Math.toDegrees(Main.boxPlayer2.getAngle()), 0f, 0f, 1f);
		drawTex(Main.boxTexturePlayer2, -0.25f * 100f, -1.0f * 100f);
		glPopMatrix();
	}
	
	public static void drawTheWall(){
		//Draws the wall
		glPushMatrix();
		Vec2 wallPosition = Main.wall.getPosition().mul(100);
		glTranslatef(wallPosition.x-50, wallPosition.y-40, 0);
		glRotated(Math.toDegrees(Main.wall.getAngle()), 0f, 0f, 1f);
		drawTex(Main.wallTexture, -0.5f * 100f, -2.0f * 100f);
		glPopMatrix();
	}
	
	public static void drawTheLedge(){
		//Draws the ledge
		glPushMatrix();
		Vec2 ledgePosition = Main.ledge.getPosition().mul(100);
		glTranslatef(ledgePosition.x-10, ledgePosition.y-150, 0);
		glRotated(Math.toDegrees(Main.ledge.getAngle()), 0f, 0f, 1f);
		drawTex(Main.ledgeTexture, -2.0f * 100f, -0.5f * 100f);
		glPopMatrix();
	}
	
	public static void drawTheFloor(){
		//Draws the floor
		glPushMatrix();
		Vec2 floorPosition = Main.floor.getPosition().mul(100);
		glTranslatef(floorPosition.x, floorPosition.y, 0);
		glRotated(Math.toDegrees(Main.floor.getAngle()), 0f, 0f, 1f);
		Helpers.drawTex(Main.floorTexture, 0, 0);
		glPopMatrix();
	}
	
	public static void drawTheWheel(){
		//Draws the wheel for player 1
		glPushMatrix();
		Vec2 wheelPosition = Main.wheel.getPosition().mul(100);
		glTranslatef(wheelPosition.x, wheelPosition.y, 0);
		glRotated(Math.toDegrees(Main.wheel.getAngle()), 0f, 0f, 1f);
		Helpers.drawQuadTex(Main.wheelTexture, -Main.circleRadius * 100, -Main.circleRadius * 100, Main.circleRadius * 200, Main.circleRadius * 200);
		glPopMatrix();
	}
	
	public static void drawTheWheel2(){
		//Draws the wheel for player 2
		glPushMatrix();
		Vec2 wheelPosition = Main.wheelPlayer2.getPosition().mul(100);
		glTranslatef(wheelPosition.x, wheelPosition.y, 0);
		glRotated(Math.toDegrees(Main.wheelPlayer2.getAngle()), 0f, 0f, 1f);
		Helpers.drawQuadTex(Main.wheelTexture, -Main.circleRadius * 100, -Main.circleRadius * 100, Main.circleRadius * 200, Main.circleRadius * 200);
		glPopMatrix();
	}
	
	public static void drawTheHill(){
		//Draws the hill
		glPushMatrix();
		Vec2 hillPosition = Main.hill.getPosition().mul(100);
		glTranslatef(hillPosition.x, hillPosition.y, 0);
		glRotated(Math.toDegrees(Main.hill.getAngle()), 0f, 0f, 1f);
		Helpers.drawQuadTex(Main.hillTexture, -10f * 100, -10f * 100, 10f * 200, 10f * 200);
		glPopMatrix();
	}
	
	public static void drawQuadTex(Texture tex, float x, float y, float w, float h){
		//Draws a texture on the x and y coordinates and with the dimensions specified by w and h
		//But it only works with square textures which also have the dimensions as powers of 2(ex: 32x32, 64x64, 128x128, ...)
		
		tex.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(w, 0);
		glTexCoord2f(1, 1);
		glVertex2f(w, h);
		glTexCoord2f(0, 1);
		glVertex2f(0, h);
		glEnd();
		glLoadIdentity();
	}
	
}
