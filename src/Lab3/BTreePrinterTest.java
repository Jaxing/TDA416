package Lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTreePrinterTest {

    private static SplayTreeSet<Integer>.Node<Integer> test1() {
        SplayTreeSet<Integer> tree = new SplayTreeSet<Integer>();
        tree.add(5);
        tree.add(4);
        return tree.root;
    }

    private static SplayTreeSet<Integer>.Node<Integer> test2() {
        SplayTreeSet<Integer> tree = new SplayTreeSet<Integer>();
        tree.add(5);
        tree.add(3);
        tree.add(4);
        return tree.root;
    }

    private static SplayTreeSet<Integer>.Node<Integer> test3() {
        SplayTreeSet<Integer> tree = new SplayTreeSet<Integer>();
        tree.add(3);
        tree.add(4);
        tree.add(5);
        return tree.root;
    }

    public static void main(String[] args) {
        {
            SplayTreeSet<Integer> tree = new SplayTreeSet<Integer>();
            System.out.println("---Test zag---");
            tree.add(3);
            BTreePrinter.printNode(tree.root);
            tree.add(4);
            BTreePrinter.printNode(tree.root);
        }
        {
            SplayTreeSet<Integer> tree = new SplayTreeSet<Integer>();
            System.out.println("---Test zig---");
            tree.add(4);
            BTreePrinter.printNode(tree.root);
            tree.add(3);
            BTreePrinter.printNode(tree.root);
        }
        {
            SplayTreeSet<Integer> tree = new SplayTreeSet<Integer>();
            System.out.println("---Test zigzig---");
            tree.add(5);
            BTreePrinter.printNode(tree.root);
            tree.add(4);
            BTreePrinter.printNode(tree.root);
            tree.add(6);
            BTreePrinter.printNode(tree.root);
            tree.add(3);
            BTreePrinter.printNode(tree.root);
        }
        {
            SplayTreeSet<Integer> tree = new SplayTreeSet<Integer>();
            System.out.println("---Test zagzag---");
            tree.add(4);
            BTreePrinter.printNode(tree.root);
            tree.add(6);
            BTreePrinter.printNode(tree.root);
            tree.add(2);
            BTreePrinter.printNode(tree.root);
            tree.add(7);
            BTreePrinter.printNode(tree.root);
            tree.add(5);
            BTreePrinter.printNode(tree.root);
        }
        {
            SplayTreeSet<Integer> tree = new SplayTreeSet<Integer>();
            System.out.println("---Test zagzig---");
            tree.add(3);
            BTreePrinter.printNode(tree.root);
            tree.add(6);
            BTreePrinter.printNode(tree.root);
            tree.add(4);
            BTreePrinter.printNode(tree.root);
            tree.add(5);
            BTreePrinter.printNode(tree.root);

        }
        {
            SplayTreeSet<Integer> tree = new SplayTreeSet<Integer>();
            System.out.println("---Test zigzag---");
            tree.add(5);
            BTreePrinter.printNode(tree.root);
            tree.add(2);
            BTreePrinter.printNode(tree.root);
            tree.add(4);
            BTreePrinter.printNode(tree.root);
            tree.add(3);
            BTreePrinter.printNode(tree.root);
        }
    }
}

class Node<T extends Comparable<?>> {
    Node<T> left, right;
    T data;

    public Node(T data) {
        this.data = data;
    }
}

class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(SplayTreeSet.Node root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<SplayTreeSet.Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<SplayTreeSet.Node> newNodes = new ArrayList<SplayTreeSet.Node>();
        for (SplayTreeSet.Node node : nodes) {
            if (node != null) {
                System.out.print(node.getData());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(SplayTreeSet.Node node) {
        if (node == null)
            return 0;
        return Math.max(BTreePrinter.maxLevel(node.getLeft()), BTreePrinter.maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}