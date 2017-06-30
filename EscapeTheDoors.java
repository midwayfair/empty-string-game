/*
 COSC 236
 Jon S. Patton
 Description: Play a riddle game prompting the user for input, with a bit of a chutes and ladders structure.
 Filename: EscapeTheDoors.java
 Date started: 6/2/17
 Modification history:
 Classes and their methods:
 Lab5_Problem4: main, giveIntro.
 EscapeTheDoors, Doors (most of the "game"): escapeAttempt, firstDoor, secondDoor, thirdDoor, wakeUp, door2Text, door3Text, and failed.
 UserInput (a singleton class to avoid instantiating a new scanner when a method recurses).

 * The actual game structure is as follows:
 * if user beats first door
 *   if user beats second door
 *     if user beats third door
 *       user escapes
 *       else go back
 *     else go back
 *  else start over
 */

import java.util.*;

public class EscapeTheDoors
{
  public static void main(String args[])
  {
    //DECLARATIONS
    int iAttempts;

    //Initialize variables ...
    iAttempts = 0;

    //Instantiations
    Doors playGame = new Doors();
    /*Have to initialize a new instance of the class that performs the heavy lifting.
     * There's no truly compelling reason to use a class instead of more methods in something this short
     * except that it's a little cleaner looking.
     */

    //Play!
    giveIntro();
    iAttempts = playGame.escapeAttempt(iAttempts);

    //Give a score when the game is over.
    if (iAttempts == 1)
    {
      System.out.println("It took you " + iAttempts + " attempt to escape.");
    }
    else
    {
      System.out.println("It took you " + iAttempts + " attempts to escape.");
    }
  }

  //introduce them to the conceit of the game and give a clue how to escape.
  private static void giveIntro()
  {
    System.out.println("Oh no! You're trapped in this program!");
    System.out.println("You will have to figure out how to escape.");
    System.out.println("Your only clue is that this game is a bit like thermonuclear war. Or tic-tac-toe, maybe. Or maybe Zen?");
    System.out.println("Ready to start? No? Too bad, here we go!");
    System.out.println("(Press Enter to continue.)");
    UserInput.get();
    System.out.println();
  }
}

/* The basic logic is described in the header, but here are some further details.
 * Each time the player enters a room, they're confronted with a door that presents them with a little brain
 * teaser that can potentially be interpreted as "nothing."
 *
 * If they just hit return (saying, essentially, nothing), they will go to the next room. Otherwise they'll either go
 * back to a previous room or get another attempt at the one they're in.
 *
 * This looks like a lot of code, but it's really just a lot of print statements, and then some user input and a couple
 * if statements each time. It uses recursion, but in what is, I think, a fairly obvious way. (Oh, we just went back to
 * a different "level.")
 *
 * The only other thing to keep in mind that the methods return the current number of attempts to escape, but they only
 * add one if the person fails to pass the door.
 */
class Doors
{
  public static int escapeAttempt(int iAttempts)
  {
    String sAnswer;

    wakeUp(iAttempts);
    iAttempts = firstDoor(iAttempts);

    if (iAttempts > 0){
      return iAttempts;
    } else {
      return 1; //Perfect score.
    }
  }

  public static int firstDoor(int iAttempts)
  {
    String sAnswer;
    System.out.println("When you try the door handle, a voice says, 'What is 2 - 2?'");
    System.out.print("How do you answer? ->");
    sAnswer = UserInput.get();
    System.out.println();

    //Check if they typed nothing. (This is true of all doors.)
    if (sAnswer.isEmpty())
    {
      return secondDoor(iAttempts);
    }
    else if (sAnswer.charAt(0) >= 48 && sAnswer.charAt(0) <= 57)
    {
      failed();
      return escapeAttempt(iAttempts) + 1;
    }
    else
    {
      failed();
      return escapeAttempt(iAttempts) + 1;
    }
  }

  public static int secondDoor(int iAttempts){
    String sAnswer;
    door2Text();
    System.out.print("There is a console. It reads, 'Enter the worst possible password.'");
    System.out.print("What do you type in? ->");
    sAnswer = UserInput.get();
    System.out.println();

    //Check if they typed nothing. (This is true of all doors.)
    if (sAnswer.isEmpty())
    {
      return thirdDoor(iAttempts);
    }
    else if (sAnswer.equalsIgnoreCase("password"))
    {
      failed();
      return secondDoor(iAttempts) + 1;
    }
    else
    {
      failed();
      return escapeAttempt(iAttempts) + 1;
    }
  }

  public static int thirdDoor(int iAttempts)
  {
    String sAnswer;
    System.out.print("Someone asks, 'What did you just say?' How do you answer? ->");
    sAnswer = UserInput.get();
    System.out.println();

    //Check if they typed nothing. (This is true of all doors.)
    if (sAnswer.isEmpty())
    {
      if (iAttempts == 0)
      {
        System.out.println("Your surroundings fade, and you realize that you're home in bed ...");
        System.out.println("Be honest. You knew it was a dream all along, didn't you?");
        sAnswer = UserInput.get();
        if (sAnswer.equalsIgnoreCase("yes") || sAnswer.equalsIgnoreCase("y"))
        {
          System.out.println("Fool! Hahahaaaa ....");
          return escapeAttempt(iAttempts) + 1;
        }
      }
      else
      {
        System.out.println("Your surroundings fade, and you realize that you're home in bed ...");
      }
    return iAttempts;
    }
  return iAttempts; //fallthrough case
  }

  //Large text blocks have been abstracted out to clean up the gameplay code.
  public static void wakeUp(int iAttempts)
  {
    if (iAttempts > 0)
    {
      System.out.println("Oh no! You're back at the beginning!");
    }
    else
    {
      System.out.println("You find yourself in a dark hallway with no memory of how you got there.");
      System.out.println("There is a faint light coming from under a door ahead. Maybe that's the way out?");
    }
    System.out.print("You stumble down the corridor for a few minutes and you come to a blue door.");
    System.out.println("You can faintly smell pancakes and bacon somewhere in the building.");
  }

  public static void door2Text()
  {
    System.out.println("There's daylight streaming in from a nearby window.");
    System.out.println("Unfortunately, the window doesn't have any openings.");
    System.out.println("You stare out at buildings you don't recognize.");
    System.out.println("'Where am I?' you wonder.");
    System.out.println("Realizing you won't get any answers here, you turn to a second door");
    System.out.println("Leading from this room.");
  }

  public static void door3Text(){
    System.out.println("The smell of breakfast is getting much closer.");
    System.out.println("There's one final door in front of you. It looks strangely familiar.");
    System.out.println("You ponder the door for a moment and suddenly it dawns on you:");
    System.out.println("It's the door to your house from when you were a child!");
    System.out.println("'What on earth is going on here?' you wonder.");
    System.out.println("You walk up to the door.");
  }

  //We can randomize the "failure" message to give a slightly less repetitive experience.
  //(Within reason -- it's a repetitive game!)
  public static void failed()
  {
    //Instantiation of psuedorandom number
    int iRandom;
    Random randomNumber = new Random();
    iRandom = randomNumber.nextInt(1);

    if (iRandom == 0)
    {
    System.out.println("Although you were sure you answered correctly, the door won't budge.");
    System.out.println("Suddenly you feel very lightheaded ...");
    }
    else
    {
    System.out.println("That was clearly not the right answer.");
    System.out.println("Everything goes swimmy for a second ...");
    }
    System.out.println();
  }
}

/*This is a Singleton, which is a Java-ish way of handling a global In-Out.
 * https://en.wikipedia.org/wiki/Singleton_pattern
 * Because escapeAttempt() is potentially recursive, initializing the Scanner every time would create bunches of instances
 * and eat up a ton of memory if someone was stuck for, say, 100 turns (horrible, I know).
 *
 * Hopefully this reasoning will justify this particular diversion.

 * The "nextLine" is because java's "next()" function will loop until it gets input of some sort. nextLine() combined with
 * isEmpty() is how you break out of it.
 */

final class UserInput
{

  private static final Scanner CONSOLE = new Scanner(System.in);

  public static String get()
  {
    return CONSOLE.nextLine();
  }
}
