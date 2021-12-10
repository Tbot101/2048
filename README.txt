=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: tahaboty
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays
     A standard 2048 board is 4 by 4. To represent the board, I will use a 2D array of Tiles that
     store the current state of each Tile.  When the game starts, all tiles on the board (i.e., all
     entries in the 2D array) will be null, representing a blank tile, except two tile will have a
     value of 2 or 4. As the game progresses a new tile will be filled at the end of each turn with
     90% chance of the tile value being 2 and 10% 4. The board will be updated by accessing the 2D
     array and manipulating the tiles within it.

  2. Collections
     I will store each of the moves that occur during a game in a collection, so that a user can undo
     a move. Specifically, I will store them in a list since order is important. I will be using a
     Stack because I will only ever need to add/remove from the end and LIFO principles satisfy this
     design requirement. When a user needs to undo, it will pop the last move off of the list and return
     the 2d array representative of the previous state of the game. The list will store entries of type
     Tile[][] 2D arrays so the last board position can be displayed, as each tile value changes between
     moves, and therefore, so will the entire 2d array.

  3. File I/O
     My 2048 implementation will use I/O to store game state, so that the user can pause a game.
     Specifically, the state of the 2D array will be saved in a text file when the save button is pressed.
     Whenever a player wants to load the saved game, my game will read this text file and parse the data,
     so it can be displayed.

  4. JUnit Testable Component
     The main state of my game will be the board (2D array). I will create methods that take in key presses
     as a parameter, and update the game accordingly. I will design this functionality such that I can test
      it with JUnit. I will test that the 2D array is updated correctly, as well as if the 2048 score is
      reached or if the user has lost. I will test edge cases, such as:
     -	Columns of equal value in the direction of swipe are concatenated
     -	Swipes in a direction are invalid if it is not possible to move cells in that direction
     -	Numbers are added only once and not twice (for example given 2,2,2,2 the result is 4,4 and not 8)
     -	Orientation of summation is correct
        (for example given 2,2,2 in a leftward swipe we are left with 4,2 and not 2,4)
     - Will test going back and making sure that the Stack collection works in storing game moves

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

    _2048: Handles main logic and the internal state of the game. Includes File I/O
    Colors: Class to easily update colors of all tiles in a systematic manner
    Direction: Contains main logic to help move the tiles up, down, left, right
    GameBoard:
    Run2048:
    Tile: Tile class in order to make the 2D array more clean instead of just
    Game: Runs the main game

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

    1. Connecting GUI to logic

    2. Implementing move function
        This required significant planning and tinkering with for loops as the array
        manipulation required to correctly implement movement and update scores and
        tiles was very involved.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

    1. I would move the movement functions inside the direction class itself, instead of having
       right, left, up and down within _2048.
    2. I would improve the look and feel of the game so that it is more user-friendly.
    3. Improve the File I/O mechanisms as the code seems a bit clunky and could be refactored
       to be cleaner and easier to understand

========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
