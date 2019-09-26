package com.jcaseydev;

class BSTSort<T extends Comparable<T>> {

  Node rootNode;
  StringBuilder sortedList = new StringBuilder();

  BSTSort(T value) {
    rootNode = new Node(value);
    sortedList.append(" ");
  }

  Node insert(Node node, T value) {
    if (rootNode == null) {
      rootNode = new Node(value);
    }

    if (value.compareTo(node.value) <= 0) {
      if (node.left != null) {
        node.left = insert(node.left, value);
      } else {
        node.left = new Node(value);
      }
    } else if (value.compareTo(node.value) > 0) {
      if (node.right != null) {
        node.right = insert(node.right, value);
      } else {
        node.right = new Node(value);
      }
    }
    return node;
  }

  String inOrder(Node node) {
    if (node != null) {
      inOrder(node.left);
      sortedList.append(node.value.toString()).append(" ");
      inOrder(node.right);
    }
    return sortedList.toString();
  }

  String inOrderDesc(Node node) {
    if (node != null) {
      inOrderDesc(node.right);
      sortedList.append(node.value.toString()).append(" ");
      inOrderDesc(node.left);
    }
    return sortedList.toString();
  }

  class Node {

    private T value;
    private Node left;
    private Node right;

    Node(T value) {
      this.value = value;
      left = null;
      right = null;
    }
  }
}
