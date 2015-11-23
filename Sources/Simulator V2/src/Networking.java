import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Networking implements Runnable{

	private DatagramSocket dsocket;
	private InetAddress playerOne, playerTwo;
	private boolean playerOneConnected = false, playerTwoConnected = false;

	public void run(){
		try{
			System.out.println("Starting network listening");
			work();
		} catch(Exception e){}
	}

	public void start(){
		run();
	}

	public void work() {
		try {
			int port = 8008;

			dsocket = new DatagramSocket(port);

			// Create a buffer to read packet into

			byte[] buffer = new byte[2048];
			// Create a packet to receive data into the buffer
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			while (true) {
				if(Main.networkReset == true){
					playerOneConnected = false;
					playerTwoConnected = false;
					Main.networkReset = false;
					System.out.println("Network reset!");
				}
				// Wait to receive a packet

				dsocket.receive(packet);
				buffer = packet.getData();

				// Convert the contents to a string, and display them
				String msg = new String(buffer, 0, packet.getLength());

				//Splits the received string into pieces using "," as a separator
				//This is done because sensorUDP(Android application) sends the positional data as a whole, the actual value that interests us being the 5th in the array
				String[] stringList;
				stringList = msg.split(",");

				//Checks to see if the string has the correct length
				if(stringList.length < 5)
					System.err.println("Incorrect string");
				else{
					
					//If level 4 is not currently active only do the logic for player one
					if(Main.level != 4){
						if(playerOneConnected == false){
							System.err.println("player one connected from: " + packet.getAddress().toString());
							playerOne = packet.getAddress();
							playerOneConnected = true;
						}
						if(playerOneConnected && packet.getAddress().equals(playerOne)){
							float angle = Float.parseFloat(stringList[4]);
							if(Main.networkInput == true){
								if(angle > 2 || angle <-2)
									Controller.setDesiredAngle(angle/5);
								else
									Controller.setDesiredAngle(0);
							}
						}
					}
					//If level 4 is active then first wait for 2 devices to start sending packets
					//Store their ip's as different players
					//Then for each received packet check if the ip corresponds to one of the two players
					//If so, update that specific player's target angle
					else{
						float angle = Float.parseFloat(stringList[4]);
						if(playerOneConnected == false){
							System.err.println("player one connected from: " + packet.getAddress().toString());
							playerOne = packet.getAddress();
							playerOneConnected = true;
						}
						if(playerTwoConnected == false && !packet.getAddress().equals(playerOne)){
							playerTwo = packet.getAddress();
							System.err.println("player two connected from: " + packet.getAddress().toString());

							playerTwoConnected = true;
						}
						if(playerOneConnected && playerTwoConnected && Main.networkInput){
							System.err.println("packetAddress=="+packet.getAddress());
							System.err.println(playerOne);
							System.err.println(playerTwo);
							if(packet.getAddress().equals(playerOne)){
								//System.err.println("###############################");
								if(angle > 2 || angle <-2)
									Controller.setDesiredAngle(angle/5);
								else
									Controller.setDesiredAngle(0);
							}
							else if(packet.getAddress().equals(playerTwo)){
								//System.err.println("-------------------------------");
								if(angle > 2 || angle <-2)
									Controller.setDesiredAnglePlayer2(angle/5);
								else
									Controller.setDesiredAnglePlayer2(0);
							}
						}
					}
				}
				// Reset the length of the packet
				packet.setLength(buffer.length);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public void resetPlayers(){
		playerOneConnected = false;
		playerTwoConnected = false;
	}
}
