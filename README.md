# DD1349-projekt

### Description

This is the repository for our word typing videogame, it'll be a level-based game where you make your way through by killing monsters with words written over their heads. To kill them, simply write the word before the get to you. The idea isn't fully complete, we're still figuring out where we want to go with how monsters should damage the player. When that is decided it will be updated in here.

### How to install and test

Game is still in development so there is no set way to test the game other than manually cloning the repo and running the main game class.

### Extra information

The game is being created using java, following is some information regarding what packages we've decided to use and such.

We use the JFrame class to create a window and then the JPanel class from the same javax.swing package to create the game panel. We use the KeyListener interface to read user inputs; we implemented this interface in our KeyHandler class where we handle different inputs and keep track of their states. Our GamePanel class also implements Runnable in java so we can run our program and create our game loop.

Will add more features in issues at a later time.
