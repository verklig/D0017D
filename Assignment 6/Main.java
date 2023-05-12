import java.util.Date; // Importing the Date class
import java.util.Scanner; // Importing the Scanner class
import java.util.Arrays; // Importing the Arrays class
import java.util.Comparator; // Importing the Comparator class
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
  private static Scanner userInput = new Scanner(System.in);
  
  public static void main(String[] args)
  {
    int[][] articles = new int[10][3]; // The initial array to hold articles
    int[][] sales = new int[1000][3]; // The array to hold sales
    Date[] salesDate = new Date[1000]; // The array to hold dates
    int articleNumber = 1000; // Default article number starting at 1000
    int noOfArticles = 0; // Articles counter starting at 0
    int saleAndDateCount = 0; // Sale and date counter starting at 0

    articles = insertArticles(articles, articleNumber, 10); // Adding 10 articles to begin with

    int choice = 0;

    // This while loop continues until the user input is "q" or "Q", returning -1 exiting the program
    while (choice != -1) 
    {
      choice = menu();

      // This switch checks the user input and 
      switch (choice) 
      {
        case 1:
          System.out.print("How many articles do you want to add? ");

          do
          {
            noOfArticles = input();
            
            if (noOfArticles == -1)
            {
              System.out.print("Invalid entry, try again: ");
            }
          } 
          while (noOfArticles == -1);
          
          articles = checkFull(articles, noOfArticles);
          articles = insertArticles(articles, articleNumber, noOfArticles);
          System.out.println("Article(s) added successfully.");
          break;
        case 2:
          removeArticle(articles);
          break;
        case 3:
          printArticles(articles);
          break;
        case 4:
          boolean ok = sellArticles(sales, salesDate, articles, saleAndDateCount);

          if (ok)
          {
            saleAndDateCount++;
          }
          
          break;
        case 5:
          printSales(sales, salesDate);
          break;
        case 6:
          sortedTable(sales, salesDate);
          break;
        default:
          System.out.println("Input must be between 1-6 or q.");
      }
    }
  }

  /**
  * This method prints a menu and takes the user input, returning it.
  *
  * @return The user input.
  */
  public static int menu()
  {
    System.out.println("\n================= MENU =================");
    System.out.println("1. Insert articles");
    System.out.println("2. Remove an article");
    System.out.println("3. Display a list of articles");
    System.out.println("4. Register a sale");
    System.out.println("5. Display order history table");
    System.out.println("6. Sort and display order history table");
    System.out.println("q. Quit");
    System.out.print("Your choice: ");
    
    return input();
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
        number = Math.abs(userInput.nextInt()); // Reads user input as an integer and replaces negative numbers with positive
        if (number > 0) // Checks if the user input is above 0
        {
          isAcceptedInput = true; // Accepts the input, exiting the loop
        }
        else
        {
          System.out.print("Invalid entry, try again: "); // Not accepting the input, looping again
        }
      }
      else if (userInput.hasNext()) // Else if not an integer it checks for a string
      {
        String userStr = userInput.next(); // Reads the user input as a string
        if (userStr.equalsIgnoreCase("q")) // Ignoring the case so that either "q" or "Q" works
        {
          isAcceptedInput = true; // Accepts the input, exiting the loop
          number = -1; // Changing number to -1 to exit the program
        }
        else
        {
          System.out.print("Invalid entry, try again: "); // Not accepting the input, looping again
        }
      }
    }
    
    return number;
  }

  /**
  * This method checks if the articles array is full, returning an expanded one if needed.
  *
  * @param articles The articles array.
  * @param noOfArticles The number of articles as an integer.
  * @return The expanded array of articles if it's full.
  */
  public static int[][] checkFull(int[][] articles, int noOfArticles)
  {
    int emptySlots = 0;

    for (int i = 0; i < articles.length; i++)
    {
      if (articles[i][0] == 0)
      {
        emptySlots++;
      }
    }

    if (emptySlots < noOfArticles)
    {
      return expandArray(articles, noOfArticles - emptySlots);
    }

    return articles;
  }

  /**
  * This method checks if the articles array is full, returning an expanded one if needed.
  *
  * @param articles The articles array.
  * @param size The size of the array.
  * @return The expanded array of articles.
  */
  public static int[][] expandArray(int[][] articles, int size)
  {
    int[][] tempArticles = new int[articles.length + size][3];
    System.arraycopy(articles, 0, tempArticles, 0, articles.length);

    return tempArticles;
  }

  /**
  * This method adds articles to the articles array depending on the user input.
  *
  * @param articles The articles array.
  * @param articleNumber The article number as an integer.
  * @param noOfArticles The number of articles as an integer.
  * @return The inserted array of articles.
  */
  public static int[][] insertArticles(int[][] articles, int articleNumber, int noOfArticles)
  {
    for (int col = 0; col < noOfArticles; col++)
    {
      for (int row = 0; row < articles.length; row++)
      {
        // Only adds articles that haven't existed yet
        if (articles[row][0] != 0) // articles[row][1] == 0
        {
        }
        else
        {
          articles[row][0] = articleNumber + row;
          articles[row][1] = (int)(Math.random() * 9) + 1;
          articles[row][2] = (int)(Math.random() * 199) + 1;
          break;
        }
      }
    }
    
    return articles;
  }

  /**
  * This method removes articles from the articles array depending on the user input.
  * Removing in this case setting the stock to 0.
  *
  * @param articles The articles array.
  */
  public static void removeArticle(int[][] articles)
  {
    boolean validInput = false;
    boolean listEmpty = true;

    for (int i = 0; i < articles.length; i++)
    {
      if (articles[i][1] > 0)
      {
        listEmpty = false;
        break;
      }
    }

    while (true)
    {
      if (listEmpty)
      {
        System.out.println("Cannot remove an article, list is empty.");
        validInput = true;
        break;
      }
      
      System.out.print("Enter the article number to remove: ");
      break;
    }

    while (!validInput)
    {
      int articleNumber = input();

      for (int i = 0; i < articles.length; i++)
      {
        if (articles[i][0] == articleNumber && articles[i][1] != 0) 
        {
          // articles[i][0] = 0;
          articles[i][1] = 0; // Only changing the stock for easier checking
          // articles[i][2] = 0;
          System.out.println("Article removed successfully.");
          validInput = true;
          break;
        }
      }
      
      if (!validInput)
      {
        System.out.print("Invalid article number, try again: ");
      }
    }
  }

  /**
  * This method prints the articles in a table for the user to see.
  *
  * @param articles The articles array.
  */
  public static void printArticles(int[][] articles)
  {
    int[][] sortedArticles = Arrays.copyOf(articles, articles.length);
    Arrays.sort(sortedArticles, Comparator.comparingInt(a -> a[0])); // Sort in ascending order based on first index
    
    boolean articleFound = false;
    
    for (int i = 0; i < articles.length; i++)
    {
      if (articles[i][1] != 0)
      {
        System.out.println("\n++++++++++++ ARTICLE TABLE +++++++++++++");
        System.out.printf("%-17s %-16s %-16s\n", "Article", "Quantity", "Price");
        articleFound = true;
        break;
      }
    }

    if (!articleFound)
    {
      System.out.println("The article list is empty.");
    }
    
    for (int i = 0; i < articles.length; i++)
    {
      if (articles[i][0] >= 1000 && articles[i][1] > 0 && articles[i][2] > 0)
      {
        System.out.printf("%-17s %-16s %-16s\n", articles[i][0], articles[i][1], articles[i][2]);
      }
    }
  }

  /**
  * This method sells an article that the user chooses and the user also chooses how many are going to be sold.
  *
  * @param sales The sales array.
  * @param salesDate The salesDate array.
  * @param articles The articles array.
  * @param currSaleAndDate The sale and date counter.
  * @return A boolean that is true to add the currSaleAndDate counter.
  */
  public static boolean sellArticles(int[][] sales, Date[] salesDate, int[][] articles, int currSaleAndDate)
  {
    int articleNumber = 0;
    int quantity = 0;
    int price = 0;
    int stock = 0;
    boolean validInput = false;
    boolean listEmpty = true;

    for (int i = 0; i < articles.length; i++)
    {
      if (articles[i][1] > 0)
      {
        listEmpty = false;
        break;
      }
    }

    while (true)
    {
      if (listEmpty)
      {
        System.out.println("Cannot sell an article, list is empty.");
        validInput = true;
        break;
      }
      
      System.out.print("Enter the article number to sell: ");
      break;
    }
    
    while (!validInput)
    {
      articleNumber = input();

      // If input is less than 1000 or if the stock is 0 in the specified index in the articles array
      if (articleNumber < 1000 || articles[articleNumber - 1000][1] == 0) // || articleNumber > articles[articles.length - 1][0]
      {
        System.out.print("Invalid article number, try again: ");
        validInput = false;
      } 
      else 
      {
        for (int[] article : articles)
        {
          if (article[0] == articleNumber) 
          {
            stock = article[1];
            
            if (stock == 0) 
            {
              System.out.print("Article out of stock, try again: "); // Not needed anymore because of above check
              validInput = false;
            }
            else
            {
              System.out.printf("Stock: %d, enter quantity to sell: ", stock);
              quantity = input();
              
              if (quantity < 1 || quantity > stock)
              {
                System.out.println("Invalid quantity, try again.");
                validInput = false;
              }
              else
              {
                price = article[2];
                sales[currSaleAndDate][0] = articleNumber;
                sales[currSaleAndDate][1] = quantity;
                sales[currSaleAndDate][2] = quantity * price;
                salesDate[currSaleAndDate] = new Date();
                article[1] -= quantity;
                validInput = true;
                System.out.printf("Successfully sold %d article(s).\n", quantity);
              }
            }
            
            break;
          }
        }
      }
    }

    return true;
  }

  /**
  * This method prints the sales in a table with date added.
  *
  * @param sales The sales array.
  * @param salesDate The salesDate array.
  */
  public static void printSales(int[][] sales, Date[] salesDate)
  {
    boolean saleFound = false;
    
    for (int i = 0; i < sales.length; i++)
    {
      if (sales[i][1] != 0)
      {
        System.out.println("\n++++++++++++++ SALE TABLE ++++++++++++++");
        System.out.printf("%-17s %-16s %-16s\n", "Article", "Quantity", "Total");
        saleFound = true;
        break;
      }
    }

    if (!saleFound)
    {
      System.out.println("No sales has been registered yet.");
    }
    
    for (int i = 0; i < sales.length; i++)
    {
      if (sales[i][0] >= 1000 && sales[i][1] > 0 && sales[i][2] > 0)
      {
        System.out.printf("%-17s %-16s %-8s %-8s\n", sales[i][0], sales[i][1], sales[i][2], salesDate[i].toString());
      }
    }
  }

  /**
  * This method prints the sales in a table with date added, sorted by article number instead of date.
  *
  * @param sales The sales array.
  * @param salesDate The salesDate array.
  */
  public static void sortedTable(int[][] sales,  Date[] salesDate)
  {
    int[][] sortedSales = Arrays.copyOf(sales, sales.length);
    Arrays.sort(sortedSales, Comparator.comparingInt(a -> a[0])); // Sort in ascending order based on first index
    
    Date[] sortedSalesDate = new Date[salesDate.length];
    for (int i = 0; i < sales.length; i++) 
    {
      int originalIndex = findIndex(sales, sortedSales[i]);
      sortedSalesDate[i] = salesDate[originalIndex];
    }

    boolean saleFound = false;
    
    for (int i = 0; i < sales.length; i++)
    {
      if (sales[i][1] != 0)
      {
        System.out.println("\n+++++++++++ SORTED SALE TABLE ++++++++++");
        System.out.printf("%-17s %-16s %-16s\n", "Article", "Quantity", "Total");
        saleFound = true;
        break;
      }
    }

    if (!saleFound)
    {
      System.out.println("No sales has been registered yet.");
    }
    
    for (int i = 0; i < sales.length; i++)
    {
      if (sortedSales[i][0] >= 1000 && sortedSales[i][1] > 0 && sortedSales[i][2] > 0)
      {
        System.out.printf("%-17s %-16s %-8s %-8s\n", sortedSales[i][0], sortedSales[i][1], sortedSales[i][2], sortedSalesDate[i].toString());
      }
    }
  }

  /**
  * This method finds the index used to sort the date array.
  *
  * @param sales The sales array.
  * @param sortedSales The sorted sales array.
  * @return An integer of the original index.
  */
  private static int findIndex(int[][] sales, int[] sortedSales)
  {
    for (int i = 0; i < sales.length; i++)
    {
      if (Arrays.equals(sales[i], sortedSales))
      {
        return i;
      }
    }
    return -1;
  }
}
