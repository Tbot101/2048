=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: _______
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
     entries in the 2D array) will be null, representing a blank cell, except two cells will have a value
     of 2 or 4. As the game progresses a new cell will be filled at the end of each turn with 90% chance of
     the cell value being 2 and 10% 4. The board will be updated by accessing the 2D array and manipulating
     cells within it.

  2.

  3.

  4.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

    _2048:
    Colors:
    Direction:
    GameBoard:
    Run2048:
    Tile:
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
