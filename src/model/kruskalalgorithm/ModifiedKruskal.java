package model.kruskalalgorithm;

import model.dungeon.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;



/**
 * Modified Kruskal Algorithm to construct Maze.
 */
public class ModifiedKruskal {

  private final HashMap<Pair<Integer, Integer>, Pair<Integer, Integer>> root = new HashMap<>();
  private final HashMap<Pair<Integer, Integer>, Integer> level = new HashMap<>();

  private Pair<Integer, Integer> find(Pair<Integer, Integer> i) {
    if (root.get(i) == i) {
      return i;
    }
    return find(root.get(i));
  }

  private void union(Pair<Integer, Integer> x, Pair<Integer, Integer> y) {
    Pair<Integer, Integer> xCoordinate = find(x);
    Pair<Integer, Integer> yCoordinate = find(y);

    if (level.get(xCoordinate) < level.get(yCoordinate)) {
      root.put(xCoordinate, yCoordinate);
    } else if (level.get(xCoordinate) > level.get(yCoordinate)) {
      root.put(yCoordinate, xCoordinate);
    } else {
      root.put(yCoordinate, xCoordinate);
      level.put(xCoordinate, level.get(xCoordinate) + 1);
    }
  }


  /**
   * Modified Kruskal Algo to create a maze from a mazemaze.
   *
   * @param maze              a basic maze which you want to convert into a modified kruskal maze.
   * @param interconnectivity how many edges that you want to add.
   * @throws IllegalArgumentException if number of interconnectivity not possible
   *                                  with the given maze.
   */
  public void createMazeWithKruskal(BasicMaze maze, int interconnectivity)
      throws IllegalArgumentException {
    ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> result = new ArrayList<>();
    List<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> adjList = maze.getEdges();

    int row = maze.getRows();
    int column = maze.getColumns();
    boolean isWrap = maze.isWrapped();

    if (isWrap) {
      interconnectivity =
          row * column * 2 - (row * column) + 1
              - interconnectivity;
    } else {
      interconnectivity = row * column * 2 - row - column - (row * column) + 1
          - interconnectivity;
    }

    for (int i = 0; i < adjList.size(); i++) {
      root.put(adjList.get(i).fst, adjList.get(i).fst);
      level.put(adjList.get(i).fst, 0);
      root.put(adjList.get(i).snd, adjList.get(i).snd);
      level.put(adjList.get(i).snd, 0);
    }

    int numberOfNode = 0;
    int theFlag = 0;
    while (numberOfNode < maze.getNumberOfNodes() - 1) {
      Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> temp = adjList.get(theFlag++);
      Pair<Integer, Integer> key = temp.fst;
      Pair<Integer, Integer> value = temp.snd;
      Pair<Integer, Integer> xOfRoot = find(key);
      Pair<Integer, Integer> yOfRoot = find(value);

      if (xOfRoot != yOfRoot) {
        numberOfNode += 1;
        result.add(temp);
        union(xOfRoot, yOfRoot);
      }
    }

    ArrayList<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> edges = new ArrayList<>();
    for (Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pair : adjList) {
      if (!result.contains(pair)) {
        edges.add(pair);
      }
    }

    if (edges.size() < interconnectivity) {
      throw new IllegalArgumentException(
          "Number of interconnectivity not possible with the input.");
    }

    int numberOfSavedEdges = edges.size();
    Iterator<Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>> iterator = edges.iterator();
    while (numberOfSavedEdges != interconnectivity && iterator.hasNext()) {
      result.add(iterator.next());
      numberOfSavedEdges--;
    }
    maze.createBasicMazeFromEdges(result);
  }
}
