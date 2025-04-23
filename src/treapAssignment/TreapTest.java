package treapAssignment;

import interfaces.Entry;
import org.junit.jupiter.api.Test;
import tree.AVLTreeMap;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreapTest  {

    @Test
    void testGet() throws IOException {
        Treap<Integer, String> map = new Treap<>();
        Integer[] arr = new Integer[] {15, 35, 55, 5, 45, 25, 65, 1, 20, 50, 60};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals("25", map.get(25));
        assertEquals("50", map.get(50));
    }

    @Test
    void testPut() throws IOException {
        Treap<Integer, String> map = new Treap<>();
        Integer[] arr = new Integer[] {40, 15, 65, 8, 55, 22, 75, 3, 18, 60, 70};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        Iterator<Integer> keys = map.keySet().iterator();
        List<Integer> list = new ArrayList<>();
        keys.forEachRemaining(list::add);

        assertEquals("[3, 8, 15, 18, 22, 40, 55, 60, 65, 70, 75]", list.toString());
        assertEquals("[3, 8, 15, 18, 22, 40, 55, 60, 65, 70, 75]", map.tree.inorder().toString());

        map.put(50, "50");
        assertEquals("[3, 8, 15, 18, 22, 40, 50, 55, 60, 65, 70, 75]", map.tree.inorder().toString());
    }

    @Test
    void testRemoveK() throws IOException {
        Treap<Integer, String> map = new Treap<>();

        Integer[] arr = new Integer[]{25, 18, 30, 12, 20, 45, 10, 14, 22};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        System.out.println("Before removing: ");
        System.out.println(map.tree.toBinaryTreeString());
        System.out.println(map.toBinaryTreeStringWithPriority());
        assertEquals("[10, 12, 14, 18, 20, 22, 25, 30, 45]", map.toString());

        map.remove(45);
        System.out.println("After removing " + "45: ");
        System.out.println(map.tree.toBinaryTreeString());
        System.out.println(map.toBinaryTreeStringWithPriority());

        assertEquals("[10, 12, 14, 18, 20, 22, 25, 30]", map.toString());
    }

    @Test
    void testRemove2() throws IOException {
        Treap<Integer, String> map = new Treap<>();

        Integer[] arr = new Integer[]{28, 21, 35, 16, 22, 60, 13, 18, 24};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        System.out.println("Before removing: ");
        System.out.println(map.tree.toBinaryTreeString());
        System.out.println(map.toBinaryTreeStringWithPriority());
        assertEquals("[13, 16, 18, 21, 22, 24, 28, 35, 60]", map.toString());

        map.remove(60);
        System.out.println("After removing " + "60: ");
        System.out.println(map.tree.toBinaryTreeString());
        System.out.println(map.toBinaryTreeStringWithPriority());

        assertEquals("[13, 16, 18, 21, 22, 24, 28, 35]", map.toString());

        map.remove(16);
        System.out.println("After removing " + "16: ");
        System.out.println(map.tree.toBinaryTreeString());
        System.out.println(map.toBinaryTreeStringWithPriority());

        assertEquals("[13, 18, 21, 22, 24, 28, 35]", map.toString());
    }

    @Test
    void testRemoveKOld() throws IOException {
        Treap<Integer, String> map = new Treap<>();
        Integer[] arr = new Integer[] {42, 33, 19, 27, 38, 5, 14, 3, 25, 22, 4, 6};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(12, map.size());
        assertEquals("33", map.remove(33));
        assertEquals(11, map.size());
    }

    @Test
    void testSplit() throws IOException {
        Treap<Integer, String> map = new Treap<>();
        Integer[] arr = new Integer[] {55, 25, 75, 15, 35, 45, 65};

        for (Integer i : arr) {
            map.put(i, i.toString());
        }

        System.out.println("Initial: ");
        System.out.println(map.toBinaryTreeString());
        System.out.println(map.toBinaryTreeStringWithPriority());

        Treap[] splitTreaps = map.split(35);
        System.out.println("Split at 35:\n");
        System.out.println("Left treap");
        System.out.println(splitTreaps[0].toBinaryTreeString());
        System.out.println("Right treap");
        System.out.println(splitTreaps[1].toBinaryTreeString());

        assertEquals("[15, 25]", splitTreaps[0].toString());
        assertEquals("[45, 55, 65, 75]", splitTreaps[1].toString());
    }

    @Test
    void testJoin() throws IOException {
        Treap<Integer, String> left = new Treap<>();
        Treap<Integer, String> right = new Treap<>();

        Integer[] leftArr = new Integer[] {5, 15, 25};
        Integer[] rightArr = new Integer[] {35, 45, 55};

        for (Integer i : leftArr) {
            left.put(i, i.toString());
        }

        for (Integer i : rightArr) {
            right.put(i, i.toString());
        }

        System.out.println("Left treap before join:");
        System.out.println(left.toBinaryTreeString());
        System.out.println(left.toBinaryTreeStringWithPriority());

        System.out.println("Right treap before join:");
        System.out.println(right.toBinaryTreeString());
        System.out.println(right.toBinaryTreeStringWithPriority());

        Treap<Integer, String> newTreap = new Treap<>();
        Treap<Integer, String> joined = newTreap.join(left, right);

        System.out.println("Joined treap:");
        System.out.println(joined.toBinaryTreeString());
        System.out.println(joined.toBinaryTreeStringWithPriority());

        assertEquals("[5, 15, 25, 35, 45, 55]", joined.toString());
    }

    @Test
    void testCeilingEntry() throws IOException {
        Treap<Integer, String> map = new Treap<>();
        Integer[] arr = new Integer[] {50, 35, 25, 45, 20, 10, 5, 30, 40, 15, 60};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(15, map.ceilingEntry(12).getKey());
        assertEquals(5, map.ceilingEntry(5).getKey());
    }

    @Test
    void testFloorEntry() throws IOException {
        Treap<Integer, String> map = new Treap<>();
        Integer[] arr = new Integer[] {42, 33, 19, 27, 38, 5, 14, 3, 25, 22, 4, 6};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals(6, map.floorEntry(11).getKey());
        assertEquals(5, map.floorEntry(5).getKey());
    }

    @Test
    void testLowerEntry() throws IOException {
        Treap<Integer, String> map = new Treap<>();
        Integer[] arr = new Integer[] {50, 32, 18, 30, 45, 6, 14, 2, 28, 26, 3, 7};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }


        assertEquals(28, map.lowerEntry(30).getKey());
        assertEquals(32, map.lowerEntry(35).getKey());
    }

    @Test
    void testHigherEntry() throws IOException {
        Treap<Integer, String> map = new Treap<>();
        Integer[] arr = new Integer[] {48, 29, 17, 27, 46, 6, 13, 3, 26, 24, 4, 7};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }
        assertEquals(13, map.higherEntry(11).getKey());
    }

    @Test
    void testEntrySet() throws IOException {
        Treap<Integer, String> map = new Treap<>();
        Integer[] arr = new Integer[] {50, 37, 22, 35, 45, 6, 18, 3, 33, 30, 4, 7};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }
        List<Entry<Integer, String>> esStr = new ArrayList<>();
        for (var e : map.entrySet()) {
            esStr.add(e);
        }
        assertEquals("[3, 4, 6, 7, 18, 22, 30, 33, 35, 37, 45, 50]", esStr.toString());
    }

    @Test
    void testToString() throws IOException {
        AVLTreeMap<Integer, String> map = new AVLTreeMap<>();
        Integer[] arr = new Integer[] {48, 30, 18, 28, 46, 6, 14, 3, 27, 25, 4, 7};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals("[3, 4, 6, 7, 14, 18, 25, 27, 28, 30, 46, 48]", map.toString());
    }

    @Test
    void testSubMap() throws IOException {
        AVLTreeMap<Integer, String> map = new AVLTreeMap<>();
        Integer[] arr = new Integer[] {50, 37, 22, 35, 45, 6, 18, 3, 33, 30, 4, 7};

        for (Integer i : arr) {
            map.put(i, Integer.toString(i));
        }

        assertEquals("[18, 22, 30, 33]", map.subMap(18, 34).toString());
    }
}
