import java.util.Scanner;

public class SecretMessages {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter a message to encode and decode: ");

		String message = scan.nextLine();

		while (message.length() > 0) {

			String out = "";

			System.out.print("Enter a secret a key (-26 to 26): ");

			int intKey = Integer.parseInt(scan.nextLine());

			char key = (char) intKey;

			for (int x = 0; x < message.length(); x++) {

				char in = message.charAt(x);

				if (in >= 'A' && in <= 'Z') {

					in += key;
					if (in > 'Z')
						in -= 26;
					if (in < 'A')
						in += 26;
				}
				if (in >= 'a' && in <= 'z') {

					in += key;
					if (in > 'z')
						in -= 26;
					if (in < 'a')
						in += 26;
				}

				out += in;

			}

			System.out.println(out);

			System.out.println("Enter another message, or press ENTER to end: ");

			message = scan.nextLine();
		}

		System.out.println("You have ended the program. Thanks for using...");

	}

}
