import java.util.Scanner; // Importing the Scanner class
/**
* This program is a game in which you want to get a sum of 12 from rolling 3 dice. The user can choose to roll them in any order
* but only once each, once all the dice are rolled the program calculates if the user won, lost, or neither of them.
*
* 1. Read input from user (1,2,3 or q) and check so that it's a correct input.
* 2. Take the input and calculate a random number between 1-6 and set that as the number for that chosen die.
* 3. Print the value of the die and the sum each instance to the console.
* 4. Once all 3 dice are chosen, display the win/loss and a message saying the outcome.
* 5. Loop to the next round until the user input is "q".
*
* @author Eric Blohm, boleri2
*/
class Main
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in); // Setting the Scanner class to be used as "input"

    // All constants that are being used
    final int DICE_1 = 1;
    final int DICE_2 = 2;
    final int DICE_3 = 3;
    final int DICE_ROLL_MAX = 6;
    final int DICE_ROLL_MIN = 1;
    final int SUM_WIN = 12;

    String userStr = ""; // User input used in the verifivation while loop below
    int userNum = 0; // User input used in the verifivation while loop below
    int dice1Value = 0; // The value of die 1
    int dice2Value = 0; // The value of die 2
    int dice3Value = 0; // The value of die 3
    int wins = 0; // Counts the wins the user got
    int losses = 0; // Counts the losses the user got
    boolean isDice1Rolled = false; // Verification if die 1 has been rolled or not
    boolean isDice2Rolled = false; // Verification if die 2 has been rolled or not
    boolean isDice3Rolled = false; // Verification if die 3 has been rolled or not
    boolean isAcceptedInput = false; // Verification if the input from the user is accepted
    boolean isQuittingGame = false; // Checks if the user input is "q" and exits the game if so

    System.out.println("\nWelcome to dice game 12. You must roll dice 1-3 and try to get the sum of 12...\n");
    
    while(!isQuittingGame) // Loops the game until the user input is "q"
    {
      while (!isAcceptedInput) // Loops until an accepted value is chosen (1,2,3 or q)
      {
        System.out.print("Enter which dice you want to roll [1,2,3] (exit with q): ");
        
        if (input.hasNextInt()) // Checks if the input is an integer
        {
          userNum = input.nextInt(); // Reads user input as an integer
          if (userNum >= DICE_1 && userNum <= DICE_3) // Checks if the user input is between 1-3
          {
            isAcceptedInput = true; // Accepts the input, exiting the loop
          }
          else
          {
            System.out.println("Sorry, that is an unaccepted entry. Try again.\n"); // Not accepting the input, looping again
          }
        }
        else if (input.hasNext()) // Else if not an integer it checks for a string
        {
          userStr = input.next(); // Reads the user input as a string
          if (userStr.equalsIgnoreCase("q")) // Ignoring the case so that either "q" or "Q" works
          {
            isAcceptedInput = true; // Accepts the input, exiting the loop
            isQuittingGame = true; // Exiting the first while loop and quitting the program
          }
          else
          {
            System.out.println("Sorry, that is an unaccepted entry. Try again.\n"); // Not accepting the input, looping again
          }
        }
      }

      if (userStr.equalsIgnoreCase("q")) // Checks if the user input is "q" or "Q"
      {
        break; // If above is true, breaks out from the if statement
      }
      else if (userNum == DICE_1)
      {
        if (!isDice1Rolled) // If die 1 is not rolled
        {
          // dice1Value becomes a random number between 1-6 and printing the numbers to the console, as well as the sum
          dice1Value = DICE_ROLL_MIN + (int)(Math.random() * ((DICE_ROLL_MAX - DICE_ROLL_MIN) + DICE_ROLL_MIN));
          System.out.printf("%d %d %d sum: %d \n\n", dice1Value, dice2Value, dice3Value, dice1Value + dice2Value + dice3Value);
          isDice1Rolled = true; // Setting the die 1 as rolled so that you can't roll the same again
          isAcceptedInput = false; // This is to go back to the loop to check for the user input again
        }
        else
        {
          System.out.println("You have already rolled dice 1!\n");
          isAcceptedInput = false; // Not accepting the input, looping again
        }
      }
      else if (userNum == DICE_2)
      {
        if (!isDice2Rolled) // If die 2 is not rolled
        {
          // dice2Value becomes a random number between 1-6 and printing the numbers to the console, as well as the sum
          dice2Value = DICE_ROLL_MIN + (int)(Math.random() * ((DICE_ROLL_MAX - DICE_ROLL_MIN) + DICE_ROLL_MIN));
          System.out.printf("%d %d %d sum: %d \n\n", dice1Value, dice2Value, dice3Value, dice1Value + dice2Value + dice3Value);
          isDice2Rolled = true; // Setting the die 2 as rolled so that you can't roll the same again
          isAcceptedInput = false; // This is to go back to the loop to check for the user input again
        }
        else
        {
          System.out.println("You have already rolled dice 2!\n");
          isAcceptedInput = false; // Not accepting the input, looping again
        }
      }
      else if (userNum == DICE_3)
      {
        if (!isDice3Rolled) // If die 3 is not rolled
        {
          // dice3Value becomes a random number between 1-6 and printing the numbers to the console, as well as the sum
          dice3Value = DICE_ROLL_MIN + (int)(Math.random() * ((DICE_ROLL_MAX - DICE_ROLL_MIN) + DICE_ROLL_MIN));
          System.out.printf("%d %d %d sum: %d \n\n", dice1Value, dice2Value, dice3Value, dice1Value + dice2Value + dice3Value);
          isDice3Rolled = true; // Setting the die 3 as rolled so that you can't roll the same again
          isAcceptedInput = false; // This is to go back to the loop to check for the user input again
        }
        else
        {
          System.out.println("You have already rolled dice 3!\n");
          isAcceptedInput = false; // Not accepting the input, looping again
        }
      }
      
      if (isDice1Rolled && isDice2Rolled && isDice3Rolled) // When all the dice are rolled
      {
        if (dice1Value + dice2Value + dice3Value == SUM_WIN) // If the sum equals to 12
        {
          wins++; // Adds +1 for each time the sum equals to 12
          System.out.printf("#wins: %d #losses: %d\n", wins, losses);
          System.out.println("You won!!\n\n");
        }
        else if (dice1Value + dice2Value + dice3Value > SUM_WIN) // If the sum is above 12
        {
          losses++; // Adds +1 for each time the sum is above 12
          System.out.printf("#wins: %d #losses: %d\n", wins, losses);
          System.out.println("You lost!!\n\n");
        }
        else // If the sum is less than 12
        {
          System.out.printf("#wins: %d #losses: %d\n", wins, losses);
          System.out.println("You neither won nor lost the game.\n\n");
        }
        // Printing next round to the console and resetting the values
        System.out.println("Next round!\n\n");
        dice1Value = 0;
        dice2Value = 0;
        dice3Value = 0;
        isDice1Rolled = false;
        isDice2Rolled = false;
        isDice3Rolled = false;
        isQuittingGame = false;
      }
    }
    System.out.printf("\n#wins: %d #losses: %d\n\n", wins, losses);
    System.out.println("Exiting game...");
  }
}
