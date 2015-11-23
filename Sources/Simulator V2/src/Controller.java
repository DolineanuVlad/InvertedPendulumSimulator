
public class Controller {

	private static float angle, anglePlayer2;
	private static float targetAngle = 0, targetAnglePlayer2 = 0;
	private static float desiredTargetAngle = 0, desiredTargetAnglePlayer2 = 0;	
	private final static float MAX_ANGULAR_IMPULSE = 0.3f;
	private final static float MIN_ANGULAR_IMPULSE = -0.3f;
	
	public static void update(){

		//We get the angle of the robot(the body of the robot)
		angle = (float)Math.toDegrees(Main.box.getAngle());

		//We calculate the force that will be applied to the wheels
		float calculatedAngularImpulse = (angle-targetAngle) * 0.03f;

		//We cap the force
		if(calculatedAngularImpulse > MAX_ANGULAR_IMPULSE)
			calculatedAngularImpulse = MAX_ANGULAR_IMPULSE;
		if(calculatedAngularImpulse < MIN_ANGULAR_IMPULSE)
			calculatedAngularImpulse = MIN_ANGULAR_IMPULSE;

		//We apply the force
		if(angle >= -50 && angle <= 50)
			Main.wheel.applyAngularImpulse(calculatedAngularImpulse);
		

		//desiredTargetAngle : what is received by commands, while targetAngle is the actual angle
		//this bit is used to get a smoother transition between commands
		if(desiredTargetAngle > targetAngle){
			targetAngle += 0.4f;
		}
		else if(desiredTargetAngle < targetAngle){
			targetAngle -= 0.4f;
		}

	}
	
	public static void updatePlayer2(){
		//We get the angle of the robot(the body of the robot)
		anglePlayer2 = (float)Math.toDegrees(Main.boxPlayer2.getAngle());

		//We calculate the force that will be applied to the wheels
		float calculatedAngularImpulse = (anglePlayer2-targetAnglePlayer2) * 0.03f;

		//We cap the force
		if(calculatedAngularImpulse > MAX_ANGULAR_IMPULSE)
			calculatedAngularImpulse = MAX_ANGULAR_IMPULSE;
		if(calculatedAngularImpulse < MIN_ANGULAR_IMPULSE)
			calculatedAngularImpulse = MIN_ANGULAR_IMPULSE;

		//We apply the force
		if(anglePlayer2 >= -50 && anglePlayer2 <= 50)
		Main.wheelPlayer2.applyAngularImpulse(calculatedAngularImpulse);


		//desiredTargetAngle : what is received by commands, while targetAngle is the actual angle
		//this bit is used to get a smoother transition between commands
		if(desiredTargetAnglePlayer2 > targetAnglePlayer2){
			targetAnglePlayer2 += 0.4f;
		}
		else if(desiredTargetAnglePlayer2 < targetAnglePlayer2){
			targetAnglePlayer2 -= 0.4f;
		}

	}
	
	

	public static void setDesiredAngle(float angle){
		desiredTargetAngle = angle;
	}
	
	public static void setDesiredAnglePlayer2(float angle){
		desiredTargetAnglePlayer2 = angle;
	}

}
