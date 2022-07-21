
// Libraries :D
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

// CODE :D
public class Main {

	private static HttpURLConnection connection;
	private static URL requrl;
	private static URL ipurl;
	private static StringBuffer ResponseContent_IP;
	private static StringBuffer ResponseContent_req;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		BufferedReader reader;
		String line;
		
		// MAIN MENU :D
		String ASCII_TITLE = "\r\n"
				+ "       _    __      __       _    _ _______ _______ _____  \r\n"
				+ "      | |  /\\ \\    / /\\     | |  | |__   __|__   __|  __ \\ \r\n"
				+ "      | | /  \\ \\  / /  \\    | |__| |  | |     | |  | |__) |\r\n"
				+ "  _   | |/ /\\ \\ \\/ / /\\ \\   |  __  |  | |     | |  |  ___/ \r\n"
				+ " | |__| / ____ \\  / ____ \\  | |  | |  | |     | |  | |     \r\n"
				+ "  \\____/_/    \\_\\/_/    \\_\\ |_|  |_|  |_|     |_|  |_|     \r\n"
				+ "                                                           \r\n"
				+ "                                           By Ghost | GEFF helped me <3";
				
		String MENU = "\n\n                  ----------------- Menu -----------------\r\n"
				+ "\n[0] HTTP REQUEST   | Receipt of the STATUS CODE and RESPONSE TEXT of a site.\r\n"
				+ "[1] IP JSON LOOKUP | IP analyzer showing information in JSON about the provided IP.\r\n"
				+ "[2] EXIT";
		while(true) {
			cls();
			System.out.print(ASCII_TITLE);
			System.out.print(MENU);
			System.out.print("\n\nINPUT: ");
			String MAIN_INPUT = scanner.nextLine();
			switch(MAIN_INPUT) {
			case "0":
				cls();
				System.out.print(ASCII_TITLE);
				System.out.print("\n\n                  ----------------- HTTP REQUEST -----------------");
				System.out.println("\n\n[?] Enter a URL to receive STATUS CODE and RESPONSE TEXT.");
				System.out.print("[+] URL: ");
				String URL_input = scanner.nextLine();
				ResponseContent_req = new StringBuffer();
				
				try {
					
					requrl = new URL(URL_input);
					connection = (HttpURLConnection) requrl.openConnection();
					
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(2000);
					connection.setReadTimeout(2000);
					
					
					int status_req = connection.getResponseCode();
					
					if (status_req > 299) {
						reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
						while((line = reader.readLine()) != null) {
							ResponseContent_req.append(line);
						}
						reader.close();
					} else {
						reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						while((line = reader.readLine()) != null) {
							ResponseContent_req.append(line);
						}
						reader.close();
					}
					System.out.println("\n[#] STATUS CODE: " + status_req);
					System.out.println("[#] RESPONSE TEXT: \n" + ResponseContent_req.toString());
					
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.print("\n\n[*] Press enter to return to the menu.. ");
				scanner.nextLine();
				break;
			case "1": 
				cls();
				System.out.print(ASCII_TITLE);
				System.out.print("\n\n                  ----------------- IP JSON LOOKUP -----------------");
				System.out.println("\n\n[?] Enter an IP to receive information in JSON about.");
				System.out.print("[+] IP: ");
				String IP_INPUT = scanner.nextLine();
				ResponseContent_IP = new StringBuffer();

				try {
					ipurl = new URL("http://ip-api.com/json/" + IP_INPUT);
					connection = (HttpURLConnection) ipurl.openConnection();
					
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(2000);
					connection.setReadTimeout(2000);
					int status_IP = connection.getResponseCode();
					
					if (status_IP > 299) {
						reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
						while((line = reader.readLine()) != null) {
							ResponseContent_IP.append(line);
						}
						reader.close();
					} else {
						reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
						while((line = reader.readLine()) != null) {
							ResponseContent_IP.append(line);
						}
						reader.close();
				}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("\n[*] IP INFO: \n" + ResponseContent_IP.toString());
				System.out.print("\n\n[*] Press enter to return to the menu.. ");
				scanner.nextLine();
				break;
			case "2": 
				System.exit(0);
				break;
			default:
				System.out.print("\n[!] The option doesn't exist. Press enter to continue..");
				scanner.nextLine();
				break;
			}
		
		
	}
}
	public static void cls()
	{
		try {
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		} catch(Exception E) {
			System.out.print("");
		}
	}
} 