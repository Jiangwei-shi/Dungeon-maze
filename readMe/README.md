## 1. Overview
The world for our game consists of a dungeon, a network of tunnels, and caves that are 
interconnected so that players can explore the entire world by traveling from cave to cave 
through the tunnels that connect them. In the dungeon. the player will have a start point 
and end point. the aim is to let players walk from start point to end point.During the trip.
The player may need to fight with Otyughs. Player can pick up treasure and arrows. and use
arrow to shoot otyugh. The play can also restart game or set a new game.

## 2.Features
- Create a warp or non-wrap dungeon
- provide support for at least three types of treasure: diamonds, rubies, and sapphires
- treasure to be added to a specified percentage of caves.
- a player to enter the dungeon at the start
- provide a description after player shoot. 
- provide a description of the player's location that at the minimum includes a description 
of treasure in the room and the possible moves
- player to move from their current location
- player to pick up treasure or Arrow that is located in their same location
- player to use arrow to shoot Otyugh. each Otyugh needs two shoot until died.

## 3.How to run
(1) Open the cmd on Windows.

(2) Go to the place you store Project3.jar using cd or dir.

(3) Type this command and press Enter.
```
For Windos:
Jave -jar Project5.jar
//make sure the data file is in the same location of Project5.jar. Or you will not open the jar.

Java -jar Project5.jar 5 5 false 3 50 7
//create a row 5, columns 5, interconnectivity 3, nonWrapMaze, treasure percent is 50% maze, and 7 Otyugh in caves

Java -jar Project5.jar 5 5 true 3 50 7
//create a row 5, columns 5, interconnectivity 3, wrapMaze, treasure percent is 50% maze, and 7 Otyugh in caves

```

## 4. How to use the Program
(1) Follow step 3 above.

(2) you can move through the dungeon using a mouse click on the screen in addition 
to the keyboard arrow keys. A click on an invalid space in the game would not advance the player.

(3) you can restart game by click "Game -> restart game"

(4) you cane start a new game by "Game -> start New Game" after set. press apply to create a 
new dungeon.

(5) If you want to Pick up. press p to pick up.

(6) If you want to Shoot. press s to set shoot. use 1 or 2 to set the cave number. use direction 
key to set direction. then press shoot.

(7) while you move to the end or be killed. Game id over. 

## 5.Description of Examples

run1.pdf
1. enter the game. 
2. move by mouse and key.
3. restart game.
4. set a new game.
5. pick up treasure.
6. shoot arrow
7. update the shoot information.
8. killed by monster
9. kill a monster
10. win te game

run2.txt

1. Request input from the user.
2. create a 5X5 non-wrap dungeon.
3. player start walk from start point.
4. player move to another cave.
5. player shoot Otyugh.
6. player pick up arrow.
7. player pick up treasure.
8. player kill Otyugh.
9. player arrive the end point.

run3.txt

1. Request input from the user.
2. create a 5X5 non-wrap dungeon.
3. player start walk from start point.
4. player move to another cave.
5. player killed by Otyugh.

## 6. Model Changes
(1) modified Kruskal Algorithm to build a dungeon.

(2) use dfs to detect the Otyugh.

(3) use the swing to create a GUI.

## 7.Limitations
the user can not create a dungeon which row or column smaller than 5.
while open the jar. The user need download the data file.

## 8. Assumptions
In this project I learned how to user swing in the project, which helped me a lot.
I use the mouse listener key listener to reaction the user's input.

## 9.Citations
Kruskal’s Minimum Spanning Tree Algorithm: https://www.geeksforgeeks.org/kruskals-minimum-spanning-tree-algorithm-greedy-algo-2/

resizeImage: https://www.baeldung.com/java-resize-image