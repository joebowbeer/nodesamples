package joebowbeer;

public class Node<T> {

  public final T data;
  public Node<T> left;
  public Node<T> right;

  public Node(T data) {
    this.data = data;
  }

  /**
   * Converts binary tree to doubly-linked list.
   * @param <T> type of data
   * @param n root of tree
   * @return head of doubly-linked list
   */
  public static <T> Node<T> convert(Node<T> n) {
    Node<T> head;
    if (n.left != null) {
      // convert left subtree
      head = convert(n.left);
      // attach this node to rightmost in left subtree
      n.left = head.left;
      head.left.right = n;
    } else {
      head = n;
    }

    Node<T> tail;
    if (n.right != null) {
      // convert right subtree
      Node<T> next = convert(n.right);
      tail = next.left;
      // attach this node to leftmost in right subtree
      n.right = next;
      next.left = n;
    } else {
      tail = n;
    }

    // link head and tail
    head.left = tail;
    tail.right = head;
    
    return head;
  }
}
