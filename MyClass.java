package less02;

import java.io.Console;

public class MyClass {

	public static void main(String[] args) {
		Console console = System.console();
		if(console==null) {
			System.out.println("Message");
			System.exit(66613);
		} 
		
		
			System.out.println("Console ok...");
			String login = console.readLine("Login: ");
			char[] pwd = console.readPassword("Password: ");
			if(login.equals("admin")&&((new String(pwd)).equals("123"))) {
				System.out.println("Access granted");
			} else {
				System.out.println("Access denied");
			}

	}

}
