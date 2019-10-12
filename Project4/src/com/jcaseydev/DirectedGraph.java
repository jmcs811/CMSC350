package com.jcaseydev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DirectedGraph<T> {

  private Map<T, Integer> classMap;
  private ArrayList<LinkedList<Integer>> edgeList;
  private List<Integer> neighbors;
  private StringBuilder result;
  private int counter = 0;

  DirectedGraph() {
    classMap = new HashMap<>();
    edgeList = new ArrayList<>();
  }

  void buildGraph(ArrayList<T[]> tokens) {
    for (T[] token : tokens) {
      for (int i = 0; i < token.length; i++) {
        addNode(token[i]);
        if (i != 0) {
          addEdge(token[0], token[i]);
        }
      }
    }
  }

  private void addEdge(T t, T t1) {
    int node1 = classMap.get(t);
    int node2 = classMap.get(t1);
    edgeList.get(node1).add(node2);
  }

  private void addNode(T t) {
    if (!classMap.containsKey(t)) {
      classMap.put(t, counter);
      LinkedList<Integer> edge = new LinkedList<>();
      edgeList.add(counter, edge);
      counter++;
    }
  }

  ArrayList<String[]> tokenize(String file) throws IOException {
    String path = new File("Tests/" + file).getAbsolutePath();
    BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
    ArrayList<String[]> fileArray = new ArrayList<>();
    String line;
    int index = 0;

    while ((line = bufferedReader.readLine()) != null) {
      String[] lineArray = line.split("\\s");
      fileArray.add(index, lineArray);
      index++;
    }
    return fileArray;
  }

  String orderGenerator(T t) throws ContainsCycleException, InvalidClassNameException {
    if (classMap.get(t) == null) {
      throw new InvalidClassNameException();
    }

    result = new StringBuilder();
    neighbors = new ArrayList<>();
    depthFirstSearch(classMap.get(t));

    return result.toString();
  }

  private void depthFirstSearch(Integer integer) throws ContainsCycleException {
    for (T k : classMap.keySet()) {
      if (classMap.get(k).equals(integer)) {
        result.append(k.toString()).append(" ");
      }
    }

    for (Integer i : edgeList.get(integer)) {
      if (neighbors.contains(i)) {
        throw new ContainsCycleException();
      }
      neighbors.add(i);
      depthFirstSearch(i);
    }
  }

}
