import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class SocClient {
	private Socket socket;

	/**
	 * Constructor to create Socket with HOST and PORT values mentioned in Utils
	 * class
	 * 
	 * @param host
	 * @param port
	 * @param file
	 */
	public SocClient(String host, int port, String file) {
		try {
			socket = new Socket(host, port);

			// Method to generate data of 300 bytes size
			String data = Utils.generateData(file);

			// Method to write data of 300 bytes size into file
			writeToFile(file, data);
			sendFile(file);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	/**
	 * Method to create new file with fileName(passed as parameter) and data
	 * populate data(passed as parameter)
	 * 
	 * @param file
	 * @param data
	 * @throws FileNotFoundException
	 */
	public void writeToFile(String file, String data) throws FileNotFoundException {

		System.out.println(file + " data: " + data);
		try {
			File newTextFile = new File(file);
			FileWriter fw = new FileWriter(newTextFile);

			fw.write(data);
			fw.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Method to accept data from Server (P2) and send data to Server (P2)
	 * 
	 * @param file
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void sendFile(String file) throws IOException, InterruptedException {

		// OutputStream to send data to Server (P2) from client (P1)
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

		// FileInputStream for reading data from file
		FileInputStream fis = new FileInputStream(file);

		byte[] serverByteArray = new byte[300];

		// DataInputStream to accept data sent from Server (P2) from client (P1)
		DataInputStream dis = new DataInputStream(socket.getInputStream());

		byte[] buffer = new byte[4096];
		fis.read(buffer);

		String clientFileData = new String(buffer);
//		System.out.println("Client File Data : " + clientFileData);

		// P1_Limit as 75 bytes to send data to Server (P2)
		int i = 0, j = Utils.P1_LIMIT;
		String temp = "";

		// Logic to send 75 bytes of data from the file of size 300 bytes
		while (i <= Utils.FILE_LENGTH) {
			while ((i < j)) {
				temp += String.valueOf((char) buffer[i]);
//				System.out.println("Buffer value" + String.valueOf((char) buffer[i]));
				i++;
			}
			i = j;
			j += Utils.P1_LIMIT;
			String message = new String(temp);
//			System.out.println(message);

			// Writing message of size 75 bytes to OutputStream to send it to Server (P2)
			dos.writeChars(message);
			temp = "";
		}

//		System.out.println("Waiting");

		// Reading data sent by Server (P2)
		dis.readFully(serverByteArray);
		String serverData = new String(serverByteArray);
		String clientServer = clientFileData + serverData;

//		System.out.println("Message from Server : " + serverData);
//		System.out.println("Client + Server in client : " + clientServer);

		// Writing final data to ClientData.txt file post receiving data from Server
		// (P2)
		writeToFile(file, clientServer);
		fis.close();
		dos.close();

	}

	public static void main(String[] args) {
		SocClient client = new SocClient(Utils.IP, Utils.PORT, Utils.CLIENT_FILE);
	}

}
