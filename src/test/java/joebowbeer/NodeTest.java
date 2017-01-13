package joebowbeer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class NodeTest {

  @Test
  public void testConvert() {
    System.out.println("convert");
    verifyLinkedList(makeList(0), Node.convert(makeTree(0)));
    verifyLinkedList(makeList(1), Node.convert(makeTree(0, null, makeTree(1))));
    verifyLinkedList(makeList(1), Node.convert(makeTree(1, makeTree(0), null)));
    verifyLinkedList(makeList(2), Node.convert(makeTree(0, null, makeTree(1, null, makeTree(2)))));
    verifyLinkedList(makeList(2), Node.convert(makeTree(0, null, makeTree(2, makeTree(1), null))));
    verifyLinkedList(makeList(2), Node.convert(makeTree(1, makeTree(0), makeTree(2))));
    verifyLinkedList(makeList(2), Node.convert(makeTree(2, makeTree(1, makeTree(0), null), null)));
    verifyLinkedList(makeList(2), Node.convert(makeTree(2, makeTree(0, null, makeTree(1)), null)));
  }

  /* Helper methods */

  private static <T> void verifyLinkedList(List<T> expectedValues, Node<T> linkedList) {
    assertEquals(expectedValues, traverseLinkedList(linkedList, new ArrayList<>()));
  }

  /**
   * Copies data from the given linked list of nodes to the given list.
   * @param <T> type of data
   * @param head head of doubly-linked list
   * @param list list of data
   * @return list of data
   */
  private static <T> List<T> traverseLinkedList(Node<T> head, List<T> list) {
    if (head != null) {
      Node<T> n = head;
      do {
        list.add(n.data);
        n = n.right;
      } while (n != head);
    }
    return list;
  }

  private static List<Integer> makeList(int max) {
    return IntStream.rangeClosed(0, max).boxed().collect(Collectors.toList());
  }

  private static <T> Node<T> makeTree(T data, Node<T> left, Node<T> right) {
    Node<T> root = makeTree(data);
    root.left = left;
    root.right = right;
    return root;
  }

  private static <T> Node<T> makeTree(T data) {
    return new Node<>(data);
  }
}
