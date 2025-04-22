package treapAssignment;
import tree.TreeMap;
import interfaces.Entry;
import interfaces.Position;
import utils.MapEntry;
import java.io.IOException;
import java.util.Comparator;
import java.util.Random;

public class Treap<K extends Comparable <K>, V> extends TreeMap<K, V> {
    private Random rand = new Random();

    public Treap() {
        super();
    }

    public Treap(Comparator<K> comp) {
        super(comp);
    }

    public int priority(Position<Entry<K, V>> p) {
        return tree.getAux(p);
    }


    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        int priority = rand.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE - 1);
        tree.setAux(p, priority);
        upheap(p);
    }

    private void upheap(Position<Entry<K, V>> p) {
        while (!tree.isRoot(p)) {
            Position<Entry<K, V>> parent = tree.parent(p);
            if (priority(p) <= priority(parent)) {
                break;
            }
            rotate(p);
        }
    }

    @Override
    public V remove(K key) throws IllegalArgumentException, IOException {
        Position<Entry<K, V>> p = treeSearch(tree.root(), key);
        //Key not found
        if (tree.isExternal(p)) {
            return null;
        }
        tree.setAux(p, Integer.MIN_VALUE + 1);
        downHeap(p);
        return super.remove(p.getElement().getKey());
    }

    protected void downHeap(Position<Entry<K, V>> p) {
        while (tree.isExternal(tree.left(p)) || tree.isExternal(tree.right(p))) {
            int leftPriority = (tree.left(p).getElement() == null) ? Integer.MIN_VALUE : tree.getAux(tree.left(p));
            int rightPriority = (tree.right(p).getElement() == null) ? Integer.MIN_VALUE : tree.getAux(tree.right(p));

            if (priority(p) > leftPriority && priority(p) > rightPriority) break;

            if (rightPriority > leftPriority) {
                rotate(tree.right(p));
            } else {
                rotate(tree.left(p));
            }
        }
    }


    public Treap<K, V>[] split(K key) throws IllegalArgumentException, IOException {
        insertWithPriority(key, null, Integer.MAX_VALUE);
        Treap<K, V> leftTreap = new Treap<>();
        Treap<K, V> rightTreap = new Treap<>();

        Position<Entry<K, V>> root = tree.root();
        Position<Entry<K, V>> leftSubtree = tree.left(root);
        Position<Entry<K, V>> rightSubtree = tree.right(root);

        insertSubtree(leftSubtree, leftTreap);
        insertSubtree(rightSubtree, rightTreap);

        Treap<K, V>[] result = new Treap[2];
        result[0] = leftTreap;
        result[1] = rightTreap;

        return result;
    }

    private void insertSubtree(Position<Entry<K, V>> node, Treap<K, V> treap) throws IOException {
        if (tree.isExternal(node)) return;

        K key = node.getElement().getKey();
        V value = node.getElement().getValue();
        int priority = tree.getAux(node);

        treap.insertWithPriority(key, value, priority);

        insertSubtree(tree.left(node), treap);
        insertSubtree(tree.right(node), treap);
    }

    private V insertWithPriority(K key, V value, int priority) throws IllegalArgumentException {
        Entry<K, V> entry = new MapEntry<>(key, value);
        Position<Entry<K, V>> p = treeSearch(tree.root(), key);

        if (tree.isExternal(p)) {
            expandExternal(p, entry);
            tree.setAux(p, priority);
            upheap(p);
            return null;
        } else {
            V oldValue = p.getElement().getValue();
            tree.set(p, entry);
            tree.setAux(p, priority);
            upheap(p);
            return oldValue;
        }
    }

    public Treap<K, V> join(Treap<K, V> leftTreap, Treap<K, V> rightTreap) throws IOException {
        Treap<K, V> newTreap = new Treap<>();

        insertSubtree(leftTreap.tree.root(), newTreap);
        insertSubtree(rightTreap.tree.root(), newTreap);

        return newTreap;
    }


    public static void main(String[] args) throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        Integer[] keys = {10, 20, 30};

        for (Integer key : keys) {
            treap.put(key, "val" + key);
        }


        System.out.println("Initial: ");
        System.out.println(treap.toBinaryTreeString());
        System.out.println(treap.toBinaryTreeStringWithPriority());

        Treap[] splitTreaps = treap.split(20);
        System.out.println("After 20 gets moved to top");
        System.out.println(treap.toBinaryTreeString());
        System.out.println(treap.toBinaryTreeStringWithPriority());

        System.out.println("After splitting from 20:\n ");
        System.out.println("Left treap");
        System.out.println(splitTreaps[0].toBinaryTreeString());
        System.out.println(splitTreaps[0].toBinaryTreeStringWithPriority());
        System.out.println("Right Treap");
        System.out.println(splitTreaps[1].toBinaryTreeString());
        System.out.println(splitTreaps[1].toBinaryTreeStringWithPriority());
        System.out.println(treap.tree.inorder());

    }

    public String toBinaryTreeStringWithPriority() {
        return buildToString(tree.root());
    }

    private String buildToString(Position<Entry<K, V>> node) {
        StringBuilder sb = new StringBuilder();
        buildToStringHelper(node, sb);
        return sb.toString();
    }

    private void buildToStringHelper(Position<Entry<K, V>> node, StringBuilder sb) {
        if (tree.isExternal(node)) return;

        sb.append("Key = ")
                .append(node.getElement().getKey())
                .append(": Value = ")
                .append(node.getElement().getValue())
                .append(": priority = ")
                .append(priority(node))
                .append("\n");

        Position<Entry<K, V>> left = tree.left(node);
        Position<Entry<K, V>> right = tree.right(node);

        if (tree.isInternal(left)) {
            buildToStringHelper(left, sb);
        }
        if (tree.isInternal(right)) {
            buildToStringHelper(right, sb);
        }
    }
}

