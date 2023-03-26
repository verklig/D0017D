import java.util.Scanner;
import java.util.Random;
import java.util.InputMismatchException;
/**
* The program asks the user for a number that it will use for the length of the arrays that will generate random numbers
* between 0 - 999. It prints the numbers in the order that they were randomized and then prints the even numbers in an
* ascending order and the odd numbers in a decending order. At last it prints the number of random numbers, even numbers
* and odd numbers.
*
* 1. Create constants and variables.
* 2. Get user inputs, loop if value is not correct.
* 3. Exit the program if the input is "q" or "Q".
* 4. Calculate random numbers and sort numbers accordingly.
* 5. Print the values to the console.
*
* @author Eric Blohm, boleri2
*/
class Main 
{
  public static void main(String[] args) 
  {
    Scanner userInput = new Scanner(System.in); // Setting the scanner method to be used as "userInput"
    Random rand = new Random(); // Setting the random method to be used as "rand"

    // The max value as 1000 because the first number in an array is 0
    // meaning that the max is actually 999
    final int MAX_VALUE = 1000;
    
    // Used to read the user input as a string
    String userStr = "";

    while (!userStr.equalsIgnoreCase("q"))
    {
      // Initializing the array and count variables and setting them to 0
      int[] randomArray = null;
      int randomCount = 0;
  
      int[] evenArray = null;
      int evenCount = 0;
  
      int[] oddArray = null;
      int oddCount = 0;
      
      while (true)
      {
        try
        {
        System.out.print("\n\nHow many random numbers in the range 0 - 999 are desired? (exit with \"q\") ");
        userStr = userInput.nextLine(); // Read user input as a string

        // If userStr = "q" or "Q", exit the program
        if (userStr.equalsIgnoreCase("q"))
        {
          System.exit(0);
        }
          
        randomCount = Integer.parseInt(userStr); // Parse string to integer

        // Setting the length of the arrays to the user input
        randomArray = new int[randomCount];
        evenArray = new int[randomCount];
        oddArray = new int[randomCount];

        // If the number given is accepted, print this and break out from the while loop
        System.out.println("Number Accepted!\n\n");
        break;
        }
        catch (OutOfMemoryError e) // Printing error if the input is larger than the amount of ram is avalible
        {
          System.out.println("Number too large!");
        }
        catch (InputMismatchException e) // Printing error if the input is anything else than an integer
        {
          System.out.println("Can only read integers!");
        }
        catch (NumberFormatException e) // Printing error if the input is anything else than an integer
        {
          System.out.println("Can only read integers!");
        }
        catch (NegativeArraySizeException e) // Printing error if the input is less than 0
        {
          System.out.println("Number bust be positive!");
        }
      }
      
      // Generate random numbers
      for (int i = 0; i < randomCount; i++)
      {
       randomArray[i] = rand.nextInt(MAX_VALUE); // Storing random integers in the array up to MAX_VALUE

        // Set the even and odd array with even and odd numbers
        if (randomArray[i] % 2 == 0)
        {
          evenArray[evenCount] = randomArray[i];
          evenCount++; // Plus one for each iteration
        }
        else
        {
          oddArray[oddCount] = randomArray[i];
          oddCount++; // Plus one for each iteration
        }
      }

      // Print the random numbers in the order they were randomized
      System.out.println("Here are the random numbers:");
      for (int i = 0; i < randomCount; i++)
      {
          System.out.print(randomArray[i] + " ");
      }
      System.out.println("\n"); // New lines for asthetics
      
      System.out.println("Here are the random numbers arranged:");

      // Swap places of the numbers in the array and sort them in an ascending order
      for (int i = 0; i < evenCount; i++) 
      {
        for (int j = 0; j < evenCount-1; j++) 
        {
          if (evenArray[j] > evenArray[j+1]) 
          {
            int temp = evenArray[j];
            evenArray[j] = evenArray[j+1];
            evenArray[j+1] = temp;
          }
        }
      }

      // Check if there is any even numbers, if not, print this
      if (evenCount == 0)
      {
        System.out.print("No Even Numbers ");
      }
      else
      {
        // Print the even numbers
        for (int i = 0; i < evenCount; i++)
        {
          System.out.print(evenArray[i] + " ");
        }
      }
      
      System.out.print("- "); // Print to differentiate even and odd numbers

      // Swap places of the numbers in the array and sort them in an decending order
      for (int i = 0; i < oddCount; i++) 
      {
        for (int j = 0; j < oddCount-1; j++) 
        {
          if (oddArray[j] < oddArray[j+1]) 
          {
            int temp = oddArray[j];
            oddArray[j] = oddArray[j+1];
            oddArray[j+1] = temp;
          }
        }
      }

      // Check if there is any odd numbers, if not, print this
      if (oddCount == 0)
      {
        System.out.print("No Odd Numbers ");
      }
      else
      {
        // Print the odd numbers
        for (int i = 0; i < oddCount; i++)
        {
          System.out.print(oddArray[i] + " ");
        }
      }

      // Print how many numbers the array has and the number of even and odd numbers
      System.out.println("\n\nOf the above " + randomCount + " numbers, " + evenCount + " were even and " + oddCount + " odd.");
    }
  }
}
