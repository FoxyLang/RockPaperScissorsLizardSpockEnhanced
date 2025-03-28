import java.util.*;
/*
 * RockPaperScissorsLizardSpockEnhanced.java
 * Author: FoxyLang
 * 
 * This game will allow you to play against the computer with a potentially 
 * infinite set of hand types for a game of "Rock Paper Scissors."
 * 
 */


public class RockPaperScissorLizardSpockEnhanced {
	public static void main(String[] args) {
		//---Declare Variables---
		//Inputs
		String hand;
		int handVal = 0;
		String repeat = "";
		
		//Outputs
		String message;
		
		//Other
		String gameType;
		ArrayList<String> hands = new ArrayList<String>();
		double winTrigger;	//A value used for random generation, triggering a win/loss scenario.
		int winLoss = 0;		//A value store for the cases of a win, loss, or tie (0-2)
		int compVal;		//The index for the value that the computer chose		
		int wins = 0;
		int losses = 0;
		int ties = 0;
		boolean loop = true; //Set true whenever you want to loop
		int count = 0;
		
		//Instantiate Objects
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		
		
		//---Prompt user for hand types
		System.out.println("What kinds of hands are we playing? ");
		System.out.println("(Input hands seperated by a space, please)");
		gameType = scan.nextLine().toLowerCase();
		
		//---Fill the hands array with all hand types
		Scanner scanTypes = new Scanner(gameType);
		while(scanTypes.hasNext()) {
			//Gets the inputs and converts them to Title Case
			String toAdd = scanTypes.next();
			toAdd = Character.toUpperCase(toAdd.charAt(0)) + toAdd.substring(1, toAdd.length());
			
			//Adds the converted input to the hands array
			hands.add(0, toAdd);
		}
		scanTypes.close();
		
		//We need an odd number of objects inside of the array, so we add one if it's not odd
		if(hands.size() % 2 == 0) {
			hands.add(0, "Default");
		}
		
		
		do {
			//---Process---
			
			if(!repeat.equals("auto")) {	//runs once, then later the user can autoplay
				do {
					//Ask user for a hand type from the hands list
					message = "Pick: ";
					for(int i = 0; i < hands.size(); i++) {
						message = message + "\"" + hands.get(i) + ",\" ";
						if(i % 6 == 5) {
							message = message + "\n\t";
						}
					}
					System.out.print(message);
					
					hand = scan.nextLine().toLowerCase();						//Get user hand, lower case
					hand = Character.toUpperCase(hand.charAt(0)) + hand.substring(1, hand.length());	//Convert to Title Case
					
					//Check if the hand inputed by the user exists within the array
					for(int i = 0; i < hands.size(); i++) {
						loop = !hand.equals(hands.get(i));
						if(loop == false) {
							handVal = i;
							break;
						}
					}
					if(loop) {
						System.out.println("Invalid hand."); //If the hand doesn't exist, tell the user it is invalid
					}
					
				}while(loop);
			}
			else {
				handVal = rand.nextInt(hands.size());	//Runs only if autoplay is activated
			}
			
			
			
			//Rolls either win, loss, or tie
			winTrigger = rand.nextDouble();
			
			double toTry = (double) ((hands.size() / 2) / (double) hands.size());
			if(winTrigger <= toTry) {
				do {
				compVal = rand.nextInt(1 + hands.size() / 2) * 2;	//Sets the computer's hand to a range of even values
				} while(compVal == 0);							    //not = 0
				winLoss = 1;
			}
			else if(winTrigger <= 2.0 * toTry) {
				compVal = 1 + rand.nextInt(hands.size() / 2) * 2;	//Sets the computer's hand to a range of odd values
				winLoss = 2;
			}
			else {
				compVal = handVal;
				winLoss = 0;
			}
			
			switch(winLoss) {
				case 1:
					message = "You Chose " + hands.get(handVal) + ".\n"
							+ "The Computer chose " + hands.get((handVal + compVal) % hands.size()) + ".\n"
							+ "You lose. Better luck next time.";
					losses++;
					break;
				case 2: 
					message = "You Chose " + hands.get(handVal) + ".\n"
							+ "The Computer chose " + hands.get((handVal + compVal) % hands.size()) + ".\n"
							+ "You Won!";
					wins++;
					break;
				default:
					message = "You Chose " + hands.get(handVal) + ".\n"
							+ "The Computer chose " + hands.get(handVal) + ".\n"
							+ "It was a tie!";
					ties++;
			}		
			
			System.out.println(message);
			
			System.out.print("Again (Y/N)? ");
			if(!repeat.equalsIgnoreCase("auto")) {
				repeat = scan.nextLine();
			}
			else {
				count++;
			}
		}while((!repeat.equalsIgnoreCase("n") && !repeat.equals("no")) && count < 100);
		
		System.out.println("\nWins: Player	|  Computer\n"
						 + "      " + wins + " \t|  " + losses + "\n"
						 + "________________|__________\n"
						 + "Ties: " + ties);
		scan.close();
	}
}
