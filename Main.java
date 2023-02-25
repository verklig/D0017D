/**
* The program takes input from the user to calculate the amount of sun hours during a day, and also calculate the amount of 
* electricity produced + the value of the electricity produced and printing it to the console
*
* 1. Create a constants and variables
* 2. Get user inputs, loop if value is not correct
* 3. Calculate user inputs
* 4. Print out date, production and value to the console
*
* @author Eric Blohm, boleri2
*/
import java.util.Scanner; // Importing the Scanner class

class Main 
{
  public static void main(String[] args) 
  {
    Scanner userInput = new Scanner(System.in); // Setting the Scanner class to be used as "userInput"

    // All constants that are being used
    final int JUNE = 6;
    final int JULY = 7;
    final int DAYS_IN_JUNE_MAX = 30;
    final int DAYS_IN_JULY_MAX = 31;
    final int DAYS_IN_MONTH_MIN = 1;
    final int HOURS_MAX = 23;
    final int MINUTES_MAX = 59;
    final int TIME_MIN = 0;
    final int CONVERT_WH_TO_KWH = 1000;
    final int NUM_OF_PANELS = 26;
    final int PANEL_HEIGHT = 1;
    final int SOLAR_RADIATION = 166;
    final double PANEL_WIDTH = 1.7;
    final double EFFICIENCY = 0.2;
    final double ELECTRIC_PRICE = 0.9;
    final double PANEL_AREA = PANEL_WIDTH * PANEL_HEIGHT;

    double hours = 0; // Hours set by the user
    double minutes = 0; // Minutes set by the user
    int month = 0; // Month set by the user
    int day = 0; // Day set by the user
    
    double finalSunriseHours = 0.0; // Sunrise hours set after calculating user input
    double finalSunsetHours = 0.0; // Sunset hours set after calculating user input
    
    boolean doneGettingDateInfo = false; // Used to repeat while loop until it = true
    boolean isSunriseBeforeSunset = false; // Used to repeat while loop until it = true

    // Getting date info from user, the while statement loops
    // as long as the user doesn't give info between given parameters
    while (!doneGettingDateInfo)
    {
        System.out.print("Enter today's date [mm-dd]> ");

        // Reads user input as a string set as "time"
        String time = userInput.nextLine();

        // Splits the time string with "-" for month and day values
        // then converts the first and second "part" of the split into integers
        String[] part = time.split("-");
        month = Integer.parseInt(part[0]);
        day = Integer.parseInt(part[1]);

      // Checks if the month chosen by the user is either June or July
      // otherwise prints an error message and loops
      // It also checks in between June and July so that the day input by the user
      // is a correct value, also printing an error message if not and loops
      if (month == JUNE || month == JULY)
      {
        if (month == JUNE)
        {
          if (day >= DAYS_IN_MONTH_MIN && day <= DAYS_IN_JUNE_MAX)
          {
            doneGettingDateInfo = true;
          }
          else
          {
            System.out.println("Day must be between 1 and 30 in June!\n");
          }
        }
        if (month == JULY)
        {
          if (day >= DAYS_IN_MONTH_MIN && day <= DAYS_IN_JULY_MAX)
          {
            doneGettingDateInfo = true;
          }
          else
          {
            System.out.println("Day must be between 1 and 31 in July!\n");
          }
        }
      }
      else
      {
        System.out.println("Month must be June or July!\n");
      }
    }

    // This while statement loops as long as the sunrise value input by the user is
    // smaller than the sunset value input by the user
    // This is to have a correct calculation at the end
    while (!isSunriseBeforeSunset)
    {
      // Getting sunrise info from user, the while statement loops
      // as long as the user doesn't give info between given parameters
      // Very close to the if statements above
      while (true)
      {
        System.out.print("Enter the time of sunrise [hh:mm]> ");
        String time = userInput.nextLine();
        
        String[] part = time.split(":");
        hours = Integer.parseInt(part[0]);
        minutes = Integer.parseInt(part[1]);
        
        if (hours >= TIME_MIN && hours <= HOURS_MAX)
        {
          if (minutes >= TIME_MIN && minutes <= MINUTES_MAX)
          {
            finalSunriseHours = hours + minutes / 60; // Calculating "finalSunriseHours"
            break; // Breaks out of the while loop
          }
          else
          {
            System.out.println("Minutes must be between 0 and 59!\n");
          }
        }
        else
        {
          System.out.println("Hours must be between 0 and 23!\n");
        }
      }
  
      // Getting sunset info from user, the while statement loops
      // as long as the user doesn't give info between given parameters
      // Very close to the if statements above
      while (true)
      {
        System.out.print("Enter the time of sunset [hh:mm]> ");
        String time = userInput.nextLine();
        
        String[] part = time.split(":");
        hours = Integer.parseInt(part[0]);
        minutes = Integer.parseInt(part[1]);
        
        if (hours >= TIME_MIN && hours <= HOURS_MAX)
        {
          if (minutes >= TIME_MIN && minutes <= MINUTES_MAX)
          {
            finalSunsetHours = hours + minutes / 60; // Calculating "finalSunsetHours"
            break; // Breaks out of the while loop
          }
          else
          {
            System.out.println("Minutes must be between 0 and 59!\n");
          }
        }
        else
        {
          System.out.println("Hours must be between 0 and 23!\n");
        }
      }

      // This keeps the user in the while loop if the sunrise value is bigger
      // than the sunset value, and gives an error message if so
      if (finalSunriseHours > finalSunsetHours)
      {
        System.out.println("Sunset cannot be before sunrise!\n");
      }
      else
      {
        isSunriseBeforeSunset = true;
      }
    }
    
    // Calculations with many constants that has been set before
    // to get the right values in the print at the end
    double finalHours = finalSunsetHours - finalSunriseHours;
    double production = SOLAR_RADIATION * EFFICIENCY * PANEL_AREA * finalHours * NUM_OF_PANELS / CONVERT_WH_TO_KWH;
    double value = production * ELECTRIC_PRICE;
    
    System.out.println("==========================================");
    
    // Prints the sun hours to the console with the hours formatted
    System.out.printf("Sun hours: %.2f hours\n", finalHours);
    
    // Prints the date, production and value of the electricity in a formatted fashion to the the console
    System.out.printf("The production on %d/%d is: %.2f kWh to a value of: SEK %.2f", month, day, production, value);
  }
}
