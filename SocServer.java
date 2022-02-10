
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

// import Utils;

public class SocServer {
	public static void main(String[] args) throws Exception {
		System.out.println("Server has started");
		ServerSocket server = new ServerSocket(Utils.PORT);
		System.out.println("Server is waiting for client");

		// Method to generate data of 300 bytes size
		String data = Utils.generateData(Utils.SERVER_FILE);

		// Method to write data of 300 bytes size into file
		writeToFile(Utils.SERVER_FILE, data);

//		System.out.println("Server File Data     : " + data);

		// Accepting request/data from Client (P1)
		Socket s1 = server.accept();
		System.out.println("Client Connected");

		// BufferedReader to accept data sent from client (P1) to Server (P2)
		BufferedReader br = new BufferedReader(new InputStreamReader(s1.getInputStream()));

		// OutputStream to send data to client (P1) from Server (P2)
		DataOutputStream dos = new DataOutputStream(s1.getOutputStream());

		// FileInputStream to read data from file
		FileInputStream fis = new FileInputStream(Utils.SERVER_FILE);
		byte[] buffer = new byte[400];
		fis.read(buffer);

		// Logic to send 300 bytes of data in chunks of 100 bytes each
		int i = 0, j = Utils.P2_LIMIT;
		while (i < Utils.FILE_LENGTH && (i < j)) {
			dos.write(buffer, i, Utils.P2_LIMIT);
			i = j;
			j += Utils.P2_LIMIT;
		}

		// Reading message from Client (P1)
		String messageFromClient = br.readLine();
		String serverFileData = new String(buffer);

		String temp = messageFromClient + serverFileData;

//		System.out.println("Client File Data     : " + messageFromClient);
//		System.out.println("Client + Server in server : " + temp);
//		System.out.println("Server Data after    : " + serverFileData);

//		Writing final data to ClientData.txt file post receiving data from Server
		writeToFile(Utils.SERVER_FILE, temp);
		fis.close();
		server.close();
	}

	/**
	 * Method to create new file with fileName(passed as parameter) and data
	 * populate data(passed as parameter)
	 * 
	 * @param file
	 * @param data
	 * @throws FileNotFoundException
	 */
	public static void writeToFile(String file, String data) throws FileNotFoundException {
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
}
