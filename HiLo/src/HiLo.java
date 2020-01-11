import java.util.Scanner;

public class HiLo {

	public static void main(String[] args) {
		
		//get ready for the player to use the keyboard
		Scanner scan = new Scanner(System.in);
		
		//play again string
		String playAgain = "";
		
		//game loop
		do {
			//create a random number from 1-100
			int theNumber = (int)(Math.random()*100 + 1);
			
			int guess = 0;
			
			//user's tracking steps
			int trackStep = 0;
			
			while ( guess != theNumber ) {
				System.out.println("Guess a number between 1 to 100: ");
				
				//get the user's guess
				guess = scan.nextInt();
				
				//update user's tracking step
				trackStep = trackStep + 1;
				
				if (guess < theNumber)
					System.out.println(guess + " is too low. Try again.");
				else if (guess > theNumber)
					System.out.println(guess + " is too high. Try again.");
				else
					System.out.println(guess + " is correct. You win...");
			} //end of the while loop for guessing
			
			
			//final point show of the user
			System.out.println("You tried to"
					+ " find the destination " + trackStep + " steps only.");
			
			//ask for 'y' to play again
			System.out.println("Would you like to play again(y/n)?");
			playAgain = scan.next();
			
		}while (playAgain.equalsIgnoreCase("y"));
		
		//thank you user for playing
		System.out.println("Thank you for playing! Goodbye.");
	}

}
