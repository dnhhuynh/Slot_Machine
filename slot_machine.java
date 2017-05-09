import java.util.*;
import java.lang.Math;

public class Assign2
{

   public static void main(String[] args)
   {
      int toBet = 1;
      int multiplier;
      int winnings;
      TripleString pullString = new TripleString();

      System.out.print("Welcome to the Hunger Games Slot Machine!\n");

      while (toBet != 0 && pullString.continuePlay())
      {
         toBet = getBet();
         if(toBet == 0) break;
        
            pullString = pull();
            multiplier = getPayMultiplier(pullString);
            winnings = multiplier*toBet;
            display(pullString, winnings);
            pullString.saveWinnings(winnings);
         
      } 

      System.out.print(pullString.displayWinnings());
      System.out.print("Thank you for playing.");

   }

   public static int getBet()
   {
      //Scanner will allow for user input
      Scanner enterBet = new Scanner(System.in);
      //set input to int 
      int bet;
      // A while loop will keep the program running when true
      while (true) 
      {
         //Output welcome message
         System.out.print("Place a bet from 1 to 100 or enter 0 to quit: ");

         //nextInt scans the next token of the input as an int. 
         bet = enterBet.nextInt();

         //If statement returns bet range from 1 to 100
         if (bet <= 100 && bet >= 0) 
         {
            return bet;
         }
      }
   }

   public static TripleString pull()
   {
      //Use reel to set TripleString default constructor to initialize string members 
      TripleString reel = new TripleString();

      //Output result message
      System.out.print("Thank you, and may the odds be ever in your favor!\n");
      System.out.print("***YOUR PULL IS***\n");

      //Set mutators that intake private helper method randString()
      reel.setFirst(randString());
      reel.setSecond(randString());
      reel.setThird(randString());

      return reel;
   }
   
   //Randomly generates value for slot machine based on defined probabilities
   static String randString()
   {

      String randStr;

      //generate a random integer in the range from 0 to 999
      Random randomGenerator = new Random(); 
      int randInt = randomGenerator.nextInt(1000);    

      //assign string value to random number according to probabilities: 
      //50% "BAR", 25% "cherries", 12.5% "space", 12.5% "7"
      if (randInt < 500)
      {
         randStr = "BAR";
      }
      else if (randInt >= 500 && randInt < 750)
      {
         randStr = "cherries";
      }
      else if (randInt >= 750 && randInt < 875)
      {
         randStr = "(space)";
      }
      else
      {
         randStr = "7";
      }

      return randStr;  

   }

   public static int getPayMultiplier(TripleString pullString)
   {
      // Sort the first 3rd of the TripleString
      switch (pullString.getFirst()) {
      // If cherries is first
      case "cherries":
         // If the second 3rd of the TripleString does not equal cherries
         if ("cherries".equals(pullString.getSecond())) {
            return 5;
         }
         else if ("cherries".equals(pullString.getSecond())) {
            if ("cherries".equals(pullString.getThird())) {
               return 15;
            }
            else if ("cherries".equals(pullString.getThird())) {
               return 30;
            }
         }  break;
         // If there are three BARs in a row
      case "BAR":
         if ("BAR".equals(pullString.getSecond())) {
            if ("BAR".equals(pullString.getThird())) {
               return 50;
            }
         }  break;
         // If there are three 7s in a row
      case "7":
         if ("7".equals(pullString.getSecond())) {
            if ("7".equals(pullString.getThird())) {
               return 100;
            }
         }  break;
      }
      // If the TripleString does not match, then return 0
      return 0;
   }


   // This method takes the winnings (a dollar amount) and thePull as parameters and displays the three strings inside thePull along with " losing statement" or "winning statement & dollar amount".
   public static void display (TripleString thePull, int winnings) {
      System.out.print (thePull.getFirst() + " " + thePull.getSecond() + " " + thePull.getThird() + "\n");

      if (winnings == 0)
         System.out.println ("Losing statement… ");
      else
         System.out.println ("Winning statement: " + winnings);
   }
}

class TripleString
{ 
   private String string1, string2, string3;
   public static final int MAX_LEN = 20;
   public static final int MAX_PULLS = 40;
   public static int pullWinnings[] = new int[MAX_PULLS];
   public static int index = 0;

   //Default Constructor
   public TripleString()
   {
      string1 = string2 = string3 = "";
   }

   //Mutators
   public boolean setFirst(String firstSymbol)
   {
      if (validString(firstSymbol))
      {
         string1 = firstSymbol;
         return true;
      }
      else 
         return false;
   }

   public boolean setSecond(String secondSymbol)
   {
      if (validString(secondSymbol))
      {
         string2 = secondSymbol;
         return true;
      }
      else 
         return false;
   }

   public boolean setThird(String thirdSymbol)
   {
      if (validString(thirdSymbol))
      {
         string3 = thirdSymbol;
         return true;
      }
      else 
         return false;
   }

   //Accessors
   public String getFirst()
   {
      return string1;
   }

   public String getSecond()
   {
      return string2;
   }

   public String getThird()
   {
      return string3;
   }

   public String toString()
   {
      return getFirst() + getSecond() + getThird();
   }
   
   public boolean continuePlay()
   {
      if (index == MAX_PULLS)
      {
         return false;
      }
      else
         return true;
   }
   
   private boolean validString(String str)
   {
      if (str.length() <= MAX_LEN)
      {
         return true;
      }
      else
         return false;
   }

   //Winning Methods
   public boolean saveWinnings(int winnings)
   {
      if (index < MAX_PULLS)
      {
         pullWinnings[index] = winnings;
         ++index;
         return true;
      }
      else
         return false;
   }

   public String displayWinnings()
   {
      String allWinnings = "";

      for (int i = 0; i < index; i++)
      {
         allWinnings += Integer.toString(pullWinnings[i]) + " ";
      }

      return allWinnings;
   }

} 

/***********************************OUTPUT******************************************


*************************************************************************************/

