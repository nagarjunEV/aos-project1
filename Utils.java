import java.io.FileNotFoundException;
import java.util.Random;

public class Utils {

	private static final Random random = new Random();

	public static final String IP = "127.0.0.1";
	public static final int PORT = 8888;
	public static final int P1_LIMIT = 75;
	public static final int P2_LIMIT = 100;
	public static final int FILE_LENGTH = 300;
	public static final String SERVER_FILE = "serverData.txt";
	public static final String CLIENT_FILE = "clientData.txt";
	public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	/**
	 * Generate random data of length 300 bytes from the above constant CHARACTERS
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String generateData(String file) throws FileNotFoundException {
		StringBuilder sb = new StringBuilder(Utils.FILE_LENGTH);
		for (int i = 0; i < Utils.FILE_LENGTH; i++) {
			sb.append(Utils.CHARACTERS.charAt(random.nextInt(Utils.CHARACTERS.length())));
		}

// 		Length of Data
//		String data = sb.toString();
//		System.out.println(data);
//		byte[] utf8Bytes = data.getBytes(StandardCharsets.UTF_8);
//		System.out.println(utf8Bytes.length);

		return sb.toString();
	}

}
