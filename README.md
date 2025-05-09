# DD1349-projekt
Our project is a word typing videogame. It's an endless highscore game where the goal is to survive as long as possible by killing the advancing monsters. To kill them, simply write the word before the monster gets to you.

## Features
- Real-time typing mechanics
- Different monsters
- Highscore
- Retro rpg animations

## How to test the game
1. Clone this repository with your command-line interface (git clone ...).
2. Make sure you have java downloaded on your computer (java -version).
3. Go to the 'src' directory (cd DD1349-projekt/src/).
4. Compile the 'Game.java' file (javac Game.java).
5. Run the game from the command-line interface (java Game.java).

## How to play
Type the words over the monsters and survive as long as possible.

## Extra information
The game is being created using java, following is some useful information:

If the highscore isn't working for you or fonts not loading, go to the GamePanel.java class and find the path for the font and remove 'src/'. Do the same thing for the highscore in GameLogic.java.

We use the JFrame class to create a window and then the JPanel class from the same javax.swing package to create the game panel. We use the KeyListener interface to read user inputs; we implemented this interface in our KeyHandler class where we handle different inputs and keep track of their states. Our GamePanel class also implements Runnable in java so we can run our program and create our game loop. Contact either Husein Hassan or Gustav Dyrcz for remaining questions about the code.
