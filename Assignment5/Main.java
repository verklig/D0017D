import java.util.Scanner; // Importing the Scanner class
/**
* This program is a calculator that you can test different values with. It uses methods that it calls when needed.
* It loops until the user input is "q" or "Q", continuing to the next part before quitting.
*
* 1. Print text in the beginning of the program for testing of values.
* 2. Read the user input and ignore the invalid inputs.
* 3. Print the various calculations using the input until the input is "q" or "Q".
* 4. If it is then continue to the next part.
* 5. Exit the program once the input is "q" or "Q" again.
*
* @author Eric Blohm, boleri2
*/
public class Main
{
  static Scanner userInput = new Scanner(System.in);

  public static void main(String[] args)
  {
    // Scanner inputString = new Scanner(System.in);
    // String input = inputString.nextLine();
    // userInput = new Scanner(input);

    int r; // The value of the radius as an integer
    int h; // The value of the height as an integer
    int n; // The value of the numerator as an integer
    int d; // The value of the denominator as an integer

    System.out.println("----------------------------------");
    System.out.println("# Test of area and volume methods");
    System.out.println("----------------------------------");

    // Loops until user input is "q" or "Q"
    while (true)
    {
      r = input();
      if (r == -1)
      {
        break;
      }
      h = input();
      if (h == -1)
      {
        break;
      }

      System.out.printf("r = %d h = %d%n", r, h);
      
      System.out.print("Circle area: ");
      System.out.printf("%.2f", area(r));
      
      System.out.print("\nCone area: ");
      System.out.printf("%.2f", area(r, h));

      System.out.print("\nCone Volume: ");
      System.out.printf("%.2f", volume(r, h));

      System.out.print("\nPythagoras' theorem: ");
      System.out.printf("%.2f", pythagoras(r, h));

      System.out.println("\n");
    }

      System.out.println("----------------------------------");
      System.out.println("# Test of the fractional methods");
      System.out.println("----------------------------------");

    // Loops until user input is "q" or "Q"
    while (true)
    {
      n = input();
      if (n == -1)
      {
        break;
      }
      d = input();
      if (d == -1)
      {
        break;
      }

      printFraction(fraction(n, d));
    }
  }

  /**
  * This method reads the user input and returns the value, if the user input
  * is "q" or "Q" it returns -1 exiting the program.
  *
  * @return The user input.
  */
  public static int input()
  {
    int number = 0;
    boolean isAcceptedInput = false;

    while (!isAcceptedInput) // Loops until an accepted value is chosen (an int above 0 or q)
    {
      if (userInput.hasNextInt()) // Checks if the input is an integer
      {
        number = Math.abs(userInput.nextInt()); // Reads user input as an integer
        if (number > 0) // Checks if the user input is above 0
        {
          isAcceptedInput = true; // Accepts the input, exiting the loop
        }
        // else
        // {
        //  System.out.println("Sorry, that is an unaccepted entry. Try again.\n"); // Not accepting the input, looping again
        // }
      }
      else if (userInput.hasNext()) // Else if not an integer it checks for a string
      {
        String userStr = userInput.next(); // Reads the user input as a string
        if (userStr.equalsIgnoreCase("q")) // Ignoring the case so that either "q" or "Q" works
        {
          isAcceptedInput = true; // Accepts the input, exiting the loop
          number = -1; // Changing number to -1 to exit the program
        }
        // else
        // {
        //  System.out.println("Sorry, that is an unaccepted entry. Try again.\n"); // Not accepting the input, looping again
        // }
      }
    }
    
    return number;
  }

  /**
  * This method calculates the area of a circle using the given input.
  *
  * @param radius The radius of the circle.
  * @return The area of the circle.
  */
  public static double area(int radius)
  {
    return Math.PI * Math.pow(radius, 2);
  }

  /**
  * This method calculates the lateral surface area of a cone using the given input.
  *
  * @param radius The radius of the cone.
  * @param height The height of the cone.
  * @return The lateral surface area of the cone.
  */
  public static double area(int radius, int height)
  {
    double s = Math.sqrt(Math.pow(radius, 2) + Math.pow(height, 2));
    return Math.PI * radius * s;
  }

  /**
  * This method calculates the hypotenuse using the given input.
  *
  * @param sideA The length of side A.
  * @param sideB The length of side B.
  * @return The length of side C (the hypotenuse).
  */
  public static double pythagoras(int sideA, int sideB)
  {
    return Math.sqrt(Math.pow(sideA, 2) + Math.pow(sideB, 2));
  }

  public static double volume(int radius, int height)
  {
    return (Math.PI * Math.pow(radius, 2) * height) / 3;
  }

  /**
  * This method calculates the numerator and denominator of a given fraction, in simplified form.
  * If the denominator is zero it returns null.
  *
  * @param numerator The numerator of the fraction.
  * @param denominator The denominator of the fraction.
  * @return An array of two integers, containing the simplified numerator and denominator.
  */
  public static int[] fraction(int numerator, int denominator)
  {
    if (denominator == 0)
    {
      return null;
    }
    
    int gcd = gcd(numerator, denominator);
    int simplifiedNumerator = numerator / gcd;
    int simplifiedDenominator = denominator / gcd;
    
    return new int[] {simplifiedNumerator, simplifiedDenominator};
  }

  /**
  * This method finds the common divisor of two integers.
  *
  * @param a The first integer.
  * @param b The second integer.
  * @return The greatest common divisor of a and b.
  */
  public static int gcd(int a, int b)
  {
    if (b == 0)
    {
      return a;
    }
    else
    {
      return gcd(b, a % b);
    }
  }
  
  /**
  * This method prints the given fraction in a formatted way.
  *
  * @param fraction An array of two integers.
  */
  public static void printFraction(int[] fraction)
  {
    if (fraction[1] == 0)
    {
      System.out.println(fraction[0] + "/" + fraction[1] + " = \"Error\"");
    } 
    else if (fraction[0] == 0)
    {
      System.out.println(fraction[0] + "/" + fraction[1] + " = 0");
    } 
    else if (Math.abs(fraction[0]) < fraction[1])
    {
      System.out.println(fraction[0] + "/" + fraction[1] + " = " + fraction[0] + "/" + fraction[1]);
    }
    else
    {
      int whole = fraction[0] / fraction[1];
      int rest = Math.abs(fraction[0] % fraction[1]);
      
      System.out.println(fraction[0] + "/" + fraction[1] + " = " + whole + " " + rest + "/" + fraction[1]);
    }
  }
}
