DIY project for lab 5 in my intro to CS course.

Project
One of the benefits of If statements is that it allows us to create simple “choice” games. To experiment with some Java functions, I made myself a game where the user has to escape from a prison of doors by answering a series of riddles. In this project, you will create a similar, but simpler, game, using nested for loops. You will also use some methods other than main to print large text blocks to help keep main clean and easy to digest.

Methods:

	Create your main() method in the usual manner, but leave it blank for a moment. We’re going to do what’s called “abstraction” for some large blocks of text, so that someone who looks at our code can see the function calls without needing to read through what is essentially just the “flavor” (atmosphere) of the game.

	Create a static method called giveIntro() that accepts no arguments and returns no value that introduces the player to the game. You may want to give them instructions on how to enter their answers, some hints on how to find the answers, or any other instructions. Or you might simply want to tell them how it came to pass that they’re trapped in a place where they need to answer riddles.

	Create at two static methods that accept no arguments and returns no values that will print any atmosphere or story you would like the player to experience before each the riddle is asked. You can name them something as simple as riddle1() and riddle2(), or you can choose to name them after the riddles that you choose, but the name should contain a clue to where they fall in the game’s progression, so that they can be located easily.

	Create one last static method called failed(), where you will put text that the player sees when they lose the game.

	Now we can go back to main.

	First, call your introduction method. Since this is a static method that accepts no arguments and returns no value, you can call it simply with giveIntro();.

	Now it’s time to construct your game. The logic of the game should be as follows:

	Call the function for the first riddle’s text block, so that the player can get into the moment. Then prompt them for an answer. Note that I chose to put the riddle itself here, in main, rather than in the large text block so that it was clear what was being asked of the user, but if you disagree with that choice, it’s your game. Just make sure that someone else looking at your code can figure out what’s going on.

	Since they can’t answer the second riddle without getting the first right, you will have to use nested if/else statements.

	If they get the question wrong once, you should give them another chance. The first time they get it wrong, give them a hint, and then prompt them for the answer again. If they fail, they’re stuck: they can’t escape. The program should call failed() and terminate.

	On the other hand, if they get the question correct, they should proceed to the second riddle. This inner if statement should resemble the outer statement: If they get the question wrong, they get a hint, if they fail, they see some sad text and the program terminates.

	However, if they get the question correct, they should see some congratulatory text before the program terminates.

	One thing you should watch out for is that the player should ONLY see the failed() text if they actual fail, and only see the congratulatory text if they win. The exact way you choose to ensure that this happens is up to you.
