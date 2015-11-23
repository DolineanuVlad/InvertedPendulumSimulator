import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.joints.RevoluteJointDef;

public class LevelSelector {

	public static int currentLevel;

	public static void loadLevel1(){
		//Definition of the pendulum body
		BodyDef boxDef = new BodyDef();
		boxDef.position.set(new Vec2(6.4f, 1.4f));
		boxDef.type = BodyType.DYNAMIC;
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(0.25f, 1.0f);
		Main.box = Main.world.createBody(boxDef);
		FixtureDef boxFixture = new FixtureDef();
		boxFixture.density = 0.5f;
		boxFixture.shape = boxShape;
		Main.box.createFixture(boxFixture);
		Main.box.setAngularDamping(0.5f);
		Main.box.setLinearDamping(0.5f);

		//Definition of the floor
		BodyDef floorDef = new BodyDef();
		floorDef.position.set(new Vec2(0, 0));
		floorDef.type = BodyType.STATIC;
		PolygonShape floorShape = new PolygonShape();
		floorShape.setAsBox(100, 0.6f);
		Main.floor = Main.world.createBody(floorDef);
		FixtureDef floorFixture = new FixtureDef();
		floorFixture.density = 0.5f;
		floorFixture.friction = 3.0f;
		floorFixture.shape = floorShape;
		Main.floor.createFixture(floorFixture);

		//Definition of the wheel
		BodyDef wheelDef = new BodyDef();
		wheelDef.position.set(new Vec2(6.4f, 1f));
		wheelDef.type = BodyType.DYNAMIC;
		CircleShape wheelShape = new CircleShape();
		wheelShape.m_radius = Main.circleRadius;
		Main.wheel = Main.world.createBody(wheelDef);
		FixtureDef wheelFixture = new FixtureDef();
		wheelFixture.density = 3.0f;
		wheelFixture.friction = 3.0f;
		wheelFixture.shape = wheelShape;
		Main.wheel.createFixture(wheelFixture);
		Main.wheel.setAngularDamping(1.0f);
		Main.wheel.setLinearDamping(0.5f);

		//Definition of the joint
		RevoluteJointDef jointDef = new RevoluteJointDef();
		jointDef.bodyA = Main.box;
		jointDef.bodyB = Main.wheel;
		jointDef.localAnchorB = new Vec2(0, 0);
		jointDef.localAnchorA = new Vec2(0, -0.95f);

		Main.world.createJoint(jointDef);

		//Definition of the hill
		BodyDef hillDef = new BodyDef();
		hillDef.position.set(new Vec2(6.4f, -8.5f));
		hillDef.type = BodyType.STATIC;
		CircleShape hillShape = new CircleShape();
		hillShape.m_radius = 10f;
		Main.hill = Main.world.createBody(hillDef);
		FixtureDef hillFixture = new FixtureDef();
		hillFixture.density = 3.0f;
		hillFixture.friction = 3.0f;
		hillFixture.shape = hillShape;
		Main.hill.createFixture(hillFixture);
		Main.hill.setAngularDamping(1.0f);
		Main.hill.setLinearDamping(0.5f);

	}

	public static void loadLevel2(){
		//Definition of the pendulum body
		BodyDef boxDef = new BodyDef();
		boxDef.position.set(new Vec2(6.4f, 1.4f));
		boxDef.type = BodyType.DYNAMIC;
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(0.25f, 1.0f);
		Main.box = Main.world.createBody(boxDef);
		FixtureDef boxFixture = new FixtureDef();
		boxFixture.density = 0.5f;
		boxFixture.shape = boxShape;
		Main.box.createFixture(boxFixture);
		Main.box.setAngularDamping(0.5f);
		Main.box.setLinearDamping(0.5f);

		//Definition of the floor
		BodyDef floorDef = new BodyDef();
		floorDef.position.set(new Vec2(0, 0));
		floorDef.type = BodyType.STATIC;
		PolygonShape floorShape = new PolygonShape();
		floorShape.setAsBox(100, 0.6f);
		Main.floor = Main.world.createBody(floorDef);
		FixtureDef floorFixture = new FixtureDef();
		floorFixture.density = 0.5f;
		floorFixture.friction = 3.0f;
		floorFixture.shape = floorShape;
		Main.floor.createFixture(floorFixture);

		//Definition of the wheel
		BodyDef wheelDef = new BodyDef();
		wheelDef.position.set(new Vec2(6.4f, 1f));
		wheelDef.type = BodyType.DYNAMIC;
		CircleShape wheelShape = new CircleShape();
		wheelShape.m_radius = Main.circleRadius;
		Main.wheel = Main.world.createBody(wheelDef);
		FixtureDef wheelFixture = new FixtureDef();
		wheelFixture.density = 3.0f;
		wheelFixture.friction = 3.0f;
		wheelFixture.shape = wheelShape;
		Main.wheel.createFixture(wheelFixture);
		Main.wheel.setAngularDamping(1.0f);
		Main.wheel.setLinearDamping(0.5f);

		//Definition of the joint
		RevoluteJointDef jointDef = new RevoluteJointDef();
		jointDef.bodyA = Main.box;
		jointDef.bodyB = Main.wheel;
		jointDef.localAnchorB = new Vec2(0, 0);
		jointDef.localAnchorA = new Vec2(0, -0.95f);

		Main.world.createJoint(jointDef);

		//Definition of the wall
		BodyDef wallDef = new BodyDef();
		wallDef.position.set(new Vec2(10.0f, 1.5f));
		wallDef.type = BodyType.STATIC;
		PolygonShape wallShape = new PolygonShape();
		wallShape.setAsBox(0.5f, 2f);
		Main.wall = Main.world.createBody(wallDef);
		FixtureDef wallFixture = new FixtureDef();
		wallFixture.density = 0.2f;
		wallFixture.friction = 0.2f;
		wallFixture.restitution = 0.3f;
		wallFixture.shape = wallShape;
		Main.wall.createFixture(wallFixture);

	}

	public static void loadLevel3(){
		//Definition of the pendulum body
		BodyDef boxDef = new BodyDef();
		boxDef.position.set(new Vec2(6.4f, 1.4f));
		boxDef.type = BodyType.DYNAMIC;
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(0.25f, 1.0f);
		Main.box = Main.world.createBody(boxDef);
		FixtureDef boxFixture = new FixtureDef();
		boxFixture.density = 0.5f;
		boxFixture.shape = boxShape;
		Main.box.createFixture(boxFixture);
		Main.box.setAngularDamping(0.5f);
		Main.box.setLinearDamping(0.5f);

		//Definition of the floor
		BodyDef floorDef = new BodyDef();
		floorDef.position.set(new Vec2(0, 0));
		floorDef.type = BodyType.STATIC;
		PolygonShape floorShape = new PolygonShape();
		floorShape.setAsBox(100, 0.6f);
		Main.floor = Main.world.createBody(floorDef);
		FixtureDef floorFixture = new FixtureDef();
		floorFixture.density = 0.5f;
		floorFixture.friction = 3.0f;
		floorFixture.shape = floorShape;
		Main.floor.createFixture(floorFixture);

		//Definition of the wheel
		BodyDef wheelDef = new BodyDef();
		wheelDef.position.set(new Vec2(6.4f, 1f));
		wheelDef.type = BodyType.DYNAMIC;
		CircleShape wheelShape = new CircleShape();
		wheelShape.m_radius = Main.circleRadius;
		Main.wheel = Main.world.createBody(wheelDef);
		FixtureDef wheelFixture = new FixtureDef();
		wheelFixture.density = 3.0f;
		wheelFixture.friction = 3.0f;
		wheelFixture.shape = wheelShape;
		Main.wheel.createFixture(wheelFixture);
		Main.wheel.setAngularDamping(1.0f);
		Main.wheel.setLinearDamping(0.5f);

		//Definition of the joint
		RevoluteJointDef jointDef = new RevoluteJointDef();
		jointDef.bodyA = Main.box;
		jointDef.bodyB = Main.wheel;
		jointDef.localAnchorB = new Vec2(0, 0);
		jointDef.localAnchorA = new Vec2(0, -0.95f);

		Main.world.createJoint(jointDef);

		//Definition of the ledge
		BodyDef ledgeDef = new BodyDef();
		ledgeDef.position.set(new Vec2(10.5f, 3.0f));
		ledgeDef.type = BodyType.STATIC;
		PolygonShape ledgeShape = new PolygonShape();
		ledgeShape.setAsBox(2f, 0.5f);
		Main.ledge = Main.world.createBody(ledgeDef);
		FixtureDef ledgeFixture = new FixtureDef();
		ledgeFixture.density = 0.2f;
		ledgeFixture.friction = 0.2f;
		ledgeFixture.restitution = 0.3f;
		ledgeFixture.shape = ledgeShape;
		Main.ledge.createFixture(ledgeFixture);

	}

	public static void loadLevel4(){
		//Definition of the pendulum body
		BodyDef boxDef = new BodyDef();
		boxDef.position.set(new Vec2(3.2f, 1.4f));
		boxDef.type = BodyType.DYNAMIC;
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(0.25f, 1.0f);
		Main.box = Main.world.createBody(boxDef);
		FixtureDef boxFixture = new FixtureDef();
		boxFixture.density = 0.5f;
		boxFixture.shape = boxShape;
		Main.box.createFixture(boxFixture);
		Main.box.setAngularDamping(0.5f);
		Main.box.setLinearDamping(0.5f);

		//Definition of the floor
		BodyDef floorDef = new BodyDef();
		floorDef.position.set(new Vec2(0, 0));
		floorDef.type = BodyType.STATIC;
		PolygonShape floorShape = new PolygonShape();
		floorShape.setAsBox(100, 0.6f);
		Main.floor = Main.world.createBody(floorDef);
		FixtureDef floorFixture = new FixtureDef();
		floorFixture.density = 0.5f;
		floorFixture.friction = 3.0f;
		floorFixture.shape = floorShape;
		Main.floor.createFixture(floorFixture);

		//Definition of the wheel
		BodyDef wheelDef = new BodyDef();
		wheelDef.position.set(new Vec2(3.2f, 1f));
		wheelDef.type = BodyType.DYNAMIC;
		CircleShape wheelShape = new CircleShape();
		wheelShape.m_radius = Main.circleRadius;
		Main.wheel = Main.world.createBody(wheelDef);
		FixtureDef wheelFixture = new FixtureDef();
		wheelFixture.density = 3.0f;
		wheelFixture.friction = 3.0f;
		wheelFixture.shape = wheelShape;
		Main.wheel.createFixture(wheelFixture);
		Main.wheel.setAngularDamping(1.0f);
		Main.wheel.setLinearDamping(0.5f);

		//Definition of the joint
		RevoluteJointDef jointDef = new RevoluteJointDef();
		jointDef.bodyA = Main.box;
		jointDef.bodyB = Main.wheel;
		jointDef.localAnchorB = new Vec2(0, 0);
		jointDef.localAnchorA = new Vec2(0, -0.95f);

		Main.world.createJoint(jointDef);

		//Definition of the pendulum body for player 2
		BodyDef boxDef2 = new BodyDef();
		boxDef2.position.set(new Vec2(9.6f, 1.4f));
		boxDef2.type = BodyType.DYNAMIC;
		PolygonShape boxShape2 = new PolygonShape();
		boxShape2.setAsBox(0.25f, 1.0f);
		Main.boxPlayer2 = Main.world.createBody(boxDef2);
		FixtureDef boxFixture2 = new FixtureDef();
		boxFixture2.density = 0.5f;
		boxFixture2.shape = boxShape2;
		Main.boxPlayer2.createFixture(boxFixture2);
		Main.boxPlayer2.setAngularDamping(0.5f);
		Main.boxPlayer2.setLinearDamping(0.5f);

		//Definition of the wheel for player 2
		BodyDef wheelDef2 = new BodyDef();
		wheelDef2.position.set(new Vec2(9.6f, 1f));
		wheelDef2.type = BodyType.DYNAMIC;
		CircleShape wheelShape2 = new CircleShape();
		wheelShape2.m_radius = Main.circleRadius;
		Main.wheelPlayer2 = Main.world.createBody(wheelDef2);
		FixtureDef wheelFixture2 = new FixtureDef();
		wheelFixture2.density = 3.0f;
		wheelFixture2.friction = 3.0f;
		wheelFixture2.shape = wheelShape2;
		Main.wheelPlayer2.createFixture(wheelFixture2);
		Main.wheelPlayer2.setAngularDamping(1.0f);
		Main.wheelPlayer2.setLinearDamping(0.5f);

		//Definition of the joint
		RevoluteJointDef jointDef2 = new RevoluteJointDef();
		jointDef2.bodyA = Main.boxPlayer2;
		jointDef2.bodyB = Main.wheelPlayer2;
		jointDef2.localAnchorB = new Vec2(0, 0);
		jointDef2.localAnchorA = new Vec2(0, -0.95f);

		Main.world.createJoint(jointDef2);
	}

}
