package joebowbeer;

public class Node<T> {

  public final T data;
  public Node<T> left;
  public Node<T> right;

  public Node(T data) {
    this.data = data;
  }

  /**
   * Converts binary tree rooted at this node to a doubly-linked list of nodes in infix order.
   * @return head of doubly-linked list
   */
  public Node<T> convert() {
    Node<T> head;
    if (left != null) {
      // convert left subtree
      head = left.convert();
      // attach this node to rightmost in left subtree
      attach(head.left, this);
    } else {
      head = this;
    }

    Node<T> tail;
    if (right != null) {
      // convert right subtree
      Node<T> next = right.convert();
      tail = next.left;
      // attach this node to leftmost in right subtree
      attach(this, next);
    } else {
      tail = this;
    }

    // attach tail to head
    attach(tail, head);
    
    return head;
  }

  private static <T> void attach(Node<T> prev, Node<T> next) {
    prev.right = next;
    next.left = prev;
  }
}
