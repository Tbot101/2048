_2048: Handles main logic and the internal state of the game. Includes File I/O

Colors: Class to easily update colors of all tiles in a systematic manner

Direction: Contains main logic to help move the tiles up, down, left, right

GameBoard: Implementation is based on a Model-View-Controller framework. The GameBoard stores the model
               as a field and listens for the Keyboard presses and then accordingly repaints the GUI.

Run2048: This manages the top-level frame and widgets for the GUI. It stores the buttons which direct
             the user to instructions or playing the game

Tile: Tile class in order to make the 2D array more clean instead of just

Game: Runs the main game
