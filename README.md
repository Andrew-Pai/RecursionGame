# RecursionGame
Simple games based off of recursion.

 Problem Definition:
  1. Create a boggle game. Generate a a board of random letters (5x5). Use recursion to locate the users word
  2. Create a battleship game. Create a 20x25 board containing 3 blobs. When the user selects a point on a blob, use recursion to delete only that one blob
 * Input - Any words the user finds on the board, and coordinates they choose to attack.
 * Output - Whether the word they chose is on the board, and a battleship board showing the coordinates the user has attacked
 * Process - Using recursion to search around a certain location until either the entire word is found or all the blobs have been replaced
