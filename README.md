# DD1349-projekt
Our project is a word typing videogame. It's a level-based game where you make your way through by killing monsters with words written over their heads. To kill them, simply write the word before the monster gets to you. The idea isn't fully complete, we're still figuring out where we want to go with how monsters should damage the player. When that is decided it will be updated in here.

## Features
- Real-time word typing mechanics
- Progressive difficulty
- Retro rpg animations

## How to test the game
1. Clone this repository with your your command-line interface (git clone ...).
2. Open the project in an IDE that suppors java, ex. IntelliJ.
3. Go into the 'src' directory (cd DD1349-projekt/src/).
4. Compile the 'Game.java' file (javac Game.java).
5. Run the game from the command-line interface (java Game.java).

## How to play
Type the words over the monsters.

## Extra information
The game is being created using java, following is some information regarding what packages we've decided to use.

We use the JFrame class to create a window and then the JPanel class from the same javax.swing package to create the game panel. We use the KeyListener interface to read user inputs; we implemented this interface in our KeyHandler class where we handle different inputs and keep track of their states. Our GamePanel class also implements Runnable in java so we can run our program and create our game loop.
