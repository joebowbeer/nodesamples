package joebowbeer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class NodeTest {

  @Test
  public void testConvert() {
    System.out.println("convert");
    verifyLinkedList(makeList(0), makeTree(0).convert());
    verifyLinkedList(makeList(1), makeTree(0, null, makeTree(1)).convert());
    verifyLinkedList(makeList(1), makeTree(1, makeTree(0), null).convert());
    verifyLinkedList(makeList(2), makeTree(0, null, makeTree(1, null, makeTree(2))).convert());
    verifyLinkedList(makeList(2), makeTree(0, null, makeTree(2, makeTree(1), null)).convert());
    verifyLinkedList(makeList(2), makeTree(1, makeTree(0), makeTree(2)).convert());
    verifyLinkedList(makeList(2), makeTree(2, makeTree(1, makeTree(0), null), null).convert());
    verifyLinkedList(makeList(2), makeTree(2, makeTree(0, null, makeTree(1)), null).convert());
  }

  /* Helper methods */

  private static <T> void verifyLinkedList(List<T> expectedValues, Node<T> linkedList) {
    assertThat(collectValues(linkedList), is(expectedValues));
  }

  /**
   * Returns list of data from the given doubly-linked list of nodes.
   * @param <T> type of node data
   * @param head head of doubly-linked list
   * @return list of data
   */
  private static <T> List<T> collectValues(Node<T> head) {
    List<T> list = new ArrayList<>();
    Node<T> n = head;
    do {
      list.add(n.data);
      n = n.right;
    } while (n != head);
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
