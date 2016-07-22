package linkedlist;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Created by poorvank on 6/6/15.
 */

class RGLLNode {

  Integer info;
  RGLLNode link;

  public RGLLNode(int n) {

    this(n, null);
  }

  public RGLLNode(int n, RGLLNode node) {

    info = n;
    link = node;

  }

}


public class ReverseLinkListGroup {

  public static List<Integer> reverseLinkListEveryXNodes(List<Integer> inputList, int X) {
    int currentIndex = 0;
    int size = inputList.size();

    List<Integer> reverseList = new LinkedList<Integer>();
    while (currentIndex <= size - 1) {
      List<Integer> part = inputList.subList(currentIndex, size);
      if (part.size() < 3) {
        reverseList.addAll(part);
        break;
      }
      reverseList.addAll(reverseFirstPart(part, 3));
      currentIndex += 3;
    }
    return reverseList;
  }

  public static <T> List<T> reverseFirstPart(final List<T> input, final int reversedElementsNumber) {
    final Iterable<T> firstPart = Iterables.limit(input, reversedElementsNumber);
    final Iterable<T> secondPart = Iterables.skip(input, reversedElementsNumber);
    final List<T> reversedFirstPart = Lists.reverse(newArrayList(firstPart));
    return reversedFirstPart;
  }

  public static void main(String[] args) {
    LinkedList<Integer> inputList = new LinkedList<Integer>();
    inputList.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
    List<Integer> list = reverseLinkListEveryXNodes(inputList, 3);
    System.out.println(Arrays.toString(list.toArray()));
    //
    // RGLLNode head = new RGLLNode(1);
    // head.link = new RGLLNode(2);
    // head.link.link = new RGLLNode(3);
    // head.link.link.link = new RGLLNode(4);
    // head.link.link.link.link = new RGLLNode(5);
    // head.link.link.link.link.link = new RGLLNode(6);
    //
    // printList(head);
    //
    // head = reverseGroups(head, 2);
    //
    // printList(head);
    //
    // System.out.println("Reversing List via recursion!");
    //
    // head = reverseUsingRecursion(head);
    //
    // printList(head);
    //
  }

  private static RGLLNode reverseUsingRecursion(RGLLNode head) {


    if (head == null) {
      return null;
    }

    RGLLNode first = head;
    RGLLNode rest = head.link;

    if (rest == null) {
      return first;
    }

    rest = reverseUsingRecursion(rest);

    first.link.link = first;

    first.link = null;

    return rest;

  }

  private static RGLLNode reverseGroups(RGLLNode head, int k) {

    RGLLNode current = head, next = null, previous = null;

    int count = 0;

    while (current != null && count < k) {

      next = current.link;
      current.link = previous;
      previous = current;
      current = next;
      count++;

    }

    if (next != null) {
      head.link = reverseGroups(next, k);
    }

    return previous;
  }

  private static void printList(RGLLNode node) {

    RGLLNode temp = node;

    while (temp != null) {
      System.out.print(temp.info + " ");
      temp = temp.link;
    }

    System.out.println();

  }

}
