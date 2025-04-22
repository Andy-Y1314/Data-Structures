package treapAssignment;

import tree.AVLTreeMap;

import java.io.IOException;
import java.util.*;

public class Benchmark {
    public static final int NUM_TIMES = 100000000;
    public static final int WARMUP_TIMES = 100;

    public static void main(String[] args) throws IOException {
        int[] arraySize = {1000, 2000, 3000, 4000 ,5000, 6000, 7000, 8000 ,9000, 10000, 20000, 40000, 80000, 160000};

        for (int n : arraySize) {
            System.out.println("Testing with n = " + n);
            Integer[] randomData = generateRandomData(n);
            Integer[] sortedData = generateSortedData(n);
            Integer[] reverseSortedData = generateReverseSortedData(n);
            Integer[] partiallySortedData = generatePartiallySortedData(n);


            //benchmarkTreapInorder(randomData, sortedData, reverseSortedData, partiallySortedData);
            //benchmarkAVLInorder(randomData, sortedData, reverseSortedData, partiallySortedData);
            //benchmarkTreeMapInorder(randomData, sortedData, reverseSortedData, partiallySortedData);

            /*benchmarkTreapSearch(randomData, sortedData, reverseSortedData, partiallySortedData);
            benchmarkAVLTreeMapSearch(randomData, sortedData, reverseSortedData, partiallySortedData);
            benchmarkJavaTreeMapSearch(randomData, sortedData, reverseSortedData, partiallySortedData);*/

            //benchmarkTreapDelete(randomData, sortedData, reverseSortedData, partiallySortedData);
            //benchmarkAVLDelete(randomData, sortedData, reverseSortedData, partiallySortedData);
            //benchmarkTreeMapDelete(randomData, sortedData, reverseSortedData, partiallySortedData);


            //benchmarkTreapInsert(randomData, sortedData, reverseSortedData, partiallySortedData);
            //benchmarkAVLTreeMapInsert(randomData, sortedData, reverseSortedData, partiallySortedData);
            benchmarkJavaTreeMapInsert(randomData, sortedData, reverseSortedData, partiallySortedData);

            /*benchmarkTreapInsertSingle(randomData, sortedData, reverseSortedData, partiallySortedData);
            benchmarkAVLInsertSingle(randomData, sortedData, reverseSortedData, partiallySortedData);
            benchmarkTreeMapInsertSingle(randomData, sortedData, reverseSortedData, partiallySortedData);*/
        }
    }



    /**********************************************************************************************************************
     *INORDER***************************************************************************************************************
     **********************************************************************************************************************/

    private static void benchmarkTreapInorder(Integer[] random, Integer[] sorted, Integer[] reverse, Integer[] partial) throws IOException {
        System.out.println("\nBenchmarking Treap in-order traversal");
        System.out.println("Treap (Random): " + runTreapInorderBenchmark(random) + " ns");
        System.out.println("Treap (Sorted): " + runTreapInorderBenchmark(sorted) + " ns");
        System.out.println("Treap (Reverse Sorted): " + runTreapInorderBenchmark(reverse) + " ns");
        System.out.println("Treap (Partially Sorted): " + runTreapInorderBenchmark(partial) + " ns");
    }

    private static long runTreapInorderBenchmark(Integer[] data) throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        for (Integer key : data) {
            treap.put(key, Integer.toString(key));
        }

        for (int i = 0; i < WARMUP_TIMES; i++) {
            treap.tree.inorder();
        }

        long totalTime = 0;

        for (int i = 0; i < NUM_TIMES; i++) {
            long start = System.nanoTime();
            treap.tree.inorder();
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime / NUM_TIMES;
    }

    private static void benchmarkAVLInorder(Integer[] random, Integer[] sorted, Integer[] reverse, Integer[] partial) throws IOException {
        System.out.println("\nBenchmarking AVLTreeMap in-order traversal");
        System.out.println("AVLTreeMap (Random): " + runAVLInorderBenchmark(random) + " ns");
        System.out.println("AVLTreeMap (Sorted): " + runAVLInorderBenchmark(sorted) + " ns");
        System.out.println("AVLTreeMap (Reverse Sorted): " + runAVLInorderBenchmark(reverse) + " ns");
        System.out.println("AVLTreeMap (Partially Sorted): " + runAVLInorderBenchmark(partial) + " ns");
    }

    private static long runAVLInorderBenchmark(Integer[] data) throws IOException {
        AVLTreeMap<Integer, String> avl = new AVLTreeMap<>();
        for (Integer key : data) {
            avl.put(key, Integer.toString(key));
        }
        long totalTime = 0;

        for (int i = 0; i < WARMUP_TIMES; i++) {
            avl.tree.inorder();
        }

        for (int i = 0; i < NUM_TIMES; i++) {
            long start = System.nanoTime();
            avl.tree.inorder();
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime / NUM_TIMES;
    }

    private static void benchmarkTreeMapInorder(Integer[] random, Integer[] sorted, Integer[] reverse, Integer[] partial) {
        System.out.println("\nBenchmarking TreeMap in-order traversal");
        System.out.println("TreeMap (Random): " + runTreeMapInorderBenchmark(random) + " ns");
        System.out.println("TreeMap (Sorted): " + runTreeMapInorderBenchmark(sorted) + " ns");
        System.out.println("TreeMap (Reverse Sorted): " + runTreeMapInorderBenchmark(reverse) + " ns");
        System.out.println("TreeMap (Partially Sorted): " + runTreeMapInorderBenchmark(partial) + " ns");
        System.out.println();
    }

    private static long runTreeMapInorderBenchmark(Integer[] data) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (Integer key : data) {
            treeMap.put(key, Integer.toString(key));
        }
        long totalTime = 0;

        for (int i = 0; i < WARMUP_TIMES; i++) {
            for (Integer key : treeMap.keySet()) {
            }
        }

        for (int i = 0; i < NUM_TIMES; i++) {
            long start = System.nanoTime();
            for (Integer key : treeMap.keySet()) {
            }
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime / NUM_TIMES;
    }


    /**********************************************************************************************************************
     *SEARCH***************************************************************************************************************
     **********************************************************************************************************************/


    private static void benchmarkTreapSearch(Integer[] randomData, Integer[] sortedData, Integer[] reverseSortedData, Integer[] partiallySortedData) throws IOException {
        System.out.println("\nBenchmarking Treap search");

        System.out.println("Treap (Random) - Successful search: " + runTreapSuccessfulSearchBenchmark(randomData) + " ns");
        //System.out.println("Treap (Random) - Unsuccessful search: " + runTreapUnsuccessfulSearchBenchmark(randomData) + " ns");

        System.out.println("Treap (Sorted) - Successful search: " + runTreapSuccessfulSearchBenchmark(sortedData) + " ns");
        //System.out.println("Treap (Sorted) - Unsuccessful search: " + runTreapUnsuccessfulSearchBenchmark(sortedData) + " ns");

        System.out.println("Treap (Reverse Sorted) - Successful search: " + runTreapSuccessfulSearchBenchmark(reverseSortedData) + " ns");
        //System.out.println("Treap (Reverse Sorted) - Unsuccessful search: " + runTreapUnsuccessfulSearchBenchmark(reverseSortedData) + " ns");

        System.out.println("Treap (Partially Sorted) - Successful search: " + runTreapSuccessfulSearchBenchmark(partiallySortedData) + " ns");
        //System.out.println("Treap (Partially Sorted) - Unsuccessful search: " + runTreapUnsuccessfulSearchBenchmark(partiallySortedData) + " ns");
    }

    private static long runTreapSuccessfulSearchBenchmark(Integer[] data) throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        for (Integer key : data) {
            treap.put(key, Integer.toString(key));
        }

        Random rand = new Random();
        for (int i = 0; i < WARMUP_TIMES; i++) {
            treap.get(data[rand.nextInt(data.length)]);
        }
        long totalTime = 0;

        for (int i = 0; i < NUM_TIMES; i++) {
            Integer key = data[rand.nextInt(data.length)];
            long start = System.nanoTime();
            treap.get(key);
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime / NUM_TIMES;
    }

    private static long runTreapUnsuccessfulSearchBenchmark(Integer[] data) throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        for (Integer key : data) {
            treap.put(key, Integer.toString(key));
        }

        Random rand = new Random();
        for (int i = 0; i < WARMUP_TIMES; i++) {
            treap.get(data[rand.nextInt(data.length)]);
        }
        long totalTime = 0;

        for (int i = 0; i < NUM_TIMES; i++) {
            Integer key = data[rand.nextInt(data.length)] + data.length;
            long start = System.nanoTime();
            treap.get(key);
            long end = System.nanoTime();
            totalTime += (end - start);
        }

        return totalTime / NUM_TIMES;
    }

    private static void benchmarkAVLTreeMapSearch(Integer[] randomData, Integer[] sortedData, Integer[] reverseSortedData, Integer[] partiallySortedData) throws IOException {
        System.out.println("\nBenchmarking AVLTreeMap search");

        System.out.println("AVLTreeMap (Random) - Successful search: " + runAVLSuccessfulSearchBenchmark(randomData) + " ns");
        //System.out.println("AVLTreeMap (Random) - Unsuccessful search: " + runAVLUnsuccessfulSearchBenchmark(randomData) + " ns");

        System.out.println("AVLTreeMap (Sorted) - Successful search: " + runAVLSuccessfulSearchBenchmark(sortedData) + " ns");
        //System.out.println("AVLTreeMap (Sorted) - Unsuccessful search: " + runAVLUnsuccessfulSearchBenchmark(sortedData) + " ns");

        System.out.println("AVLTreeMap (Reverse Sorted) - Successful search: " + runAVLSuccessfulSearchBenchmark(reverseSortedData) + " ns");
        //System.out.println("AVLTreeMap (Reverse Sorted) - Unsuccessful search: " + runAVLUnsuccessfulSearchBenchmark(reverseSortedData) + " ns");

        System.out.println("AVLTreeMap (Partially Sorted) - Successful search: " + runAVLSuccessfulSearchBenchmark(partiallySortedData) + " ns");
        //System.out.println("AVLTreeMap (Partially Sorted) - Unsuccessful search: " + runAVLUnsuccessfulSearchBenchmark(partiallySortedData) + " ns");
    }

    private static long runAVLSuccessfulSearchBenchmark(Integer[] data) throws IOException {
        AVLTreeMap<Integer, String> avl = new AVLTreeMap<>();
        for (Integer key : data) {
            avl.put(key, Integer.toString(key));
        }

        Random rand = new Random();
        for (int i = 0; i < WARMUP_TIMES; i++) {
            avl.get(data[rand.nextInt(data.length)]);
        }
        long totalTime = 0;

        for (int i = 0; i < NUM_TIMES; i++) {
            Integer key = data[rand.nextInt(data.length)];
            long start = System.nanoTime();
            avl.get(key);
            long end = System.nanoTime();
            totalTime += (end - start);
        }
        return totalTime / NUM_TIMES;
    }

    private static long runAVLUnsuccessfulSearchBenchmark(Integer[] data) throws IOException {
        AVLTreeMap<Integer, String> avl = new AVLTreeMap<>();
        for (Integer key : data) {
            avl.put(key, Integer.toString(key));
        }

        Random rand = new Random();
        for (int i = 0; i < WARMUP_TIMES; i++) {
            avl.get(data[rand.nextInt(data.length)]);
        }
        long totalTime = 0;

        for (int i = 0; i < NUM_TIMES; i++) {
            Integer key = data[rand.nextInt(data.length)] + data.length;
            long start = System.nanoTime();
            avl.get(key);
            long end = System.nanoTime();
            totalTime += (end - start);
        }

        return totalTime / NUM_TIMES;
    }

    private static void benchmarkJavaTreeMapSearch(Integer[] randomData, Integer[] sortedData, Integer[] reverseSortedData, Integer[] partiallySortedData) {
        System.out.println("\nBenchmarking java.util.TreeMap search");

        System.out.println("TreeMap (Random) - Successful search: " + runTreeMapSuccessfulSearchBenchmark(randomData) + " ns");
        //System.out.println("TreeMap (Random) - Unsuccessful search: " + runTreeMapUnsuccessfulSearchBenchmark(randomData) + " ns");

        System.out.println("TreeMap (Sorted) - Successful search: " + runTreeMapSuccessfulSearchBenchmark(sortedData) + " ns");
        //System.out.println("TreeMap (Sorted) - Unsuccessful search: " + runTreeMapUnsuccessfulSearchBenchmark(sortedData) + " ns");

        System.out.println("TreeMap (Reverse Sorted) - Successful search: " + runTreeMapSuccessfulSearchBenchmark(reverseSortedData) + " ns");
        //System.out.println("TreeMap (Reverse Sorted) - Unsuccessful search: " + runTreeMapUnsuccessfulSearchBenchmark(reverseSortedData) + " ns");

        System.out.println("TreeMap (Partially Sorted) - Successful search: " + runTreeMapSuccessfulSearchBenchmark(partiallySortedData) + " ns");
        //System.out.println("TreeMap (Partially Sorted) - Unsuccessful search: " + runTreeMapUnsuccessfulSearchBenchmark(partiallySortedData) + " ns");
        System.out.println();
    }

    private static long runTreeMapSuccessfulSearchBenchmark(Integer[] data) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (Integer key : data) {
            treeMap.put(key, Integer.toString(key));
        }

        Random rand = new Random();
        for (int i = 0; i < WARMUP_TIMES; i++) {
            treeMap.get(data[rand.nextInt(data.length)]);
        }
        long totalTime = 0;

        for (int i = 0; i < NUM_TIMES; i++) {
            Integer key = data[rand.nextInt(data.length)];
            long start = System.nanoTime();
            treeMap.get(key);
            long end = System.nanoTime();
            totalTime += (end - start);
        }

        return totalTime / NUM_TIMES;
    }

    private static long runTreeMapUnsuccessfulSearchBenchmark(Integer[] data) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (Integer key : data) {
            treeMap.put(key, Integer.toString(key));
        }

        Random rand = new Random();
        for (int i = 0; i < WARMUP_TIMES; i++) {
            treeMap.get(data[rand.nextInt(data.length)]);
        }
        long totalTime = 0;

        for (int i = 0; i < NUM_TIMES; i++) {
            Integer key = data[rand.nextInt(data.length)] + data.length;
            long start = System.nanoTime();
            treeMap.get(key);
            long end = System.nanoTime();
            totalTime += (end - start);
        }

        return totalTime / NUM_TIMES;
    }


    /************************************************************************************************************
     *DELETE*****************************************************************************************************
     ************************************************************************************************************/


    private static void benchmarkTreapDelete(Integer[] random, Integer[] sorted, Integer[] reverse, Integer[] partial) throws IOException {
        System.out.println("\nBenchmarking Treap delete");
        System.out.println("Treap (Random): " + runTreapDeleteBenchmark(random) + " ns");
        //System.out.println("Treap (Sorted): " + runTreapDeleteBenchmark(sorted) + " ns");
        //System.out.println("Treap (Reverse Sorted): " + runTreapDeleteBenchmark(reverse) + " ns");
        //System.out.println("Treap (Partially Sorted): " + runTreapDeleteBenchmark(partial) + " ns");
    }

    private static long runTreapDeleteBenchmark(Integer[] data) throws IOException {
        Treap<Integer, String> treap = new Treap<>();
        for (Integer key : data) {
            treap.put(key, Integer.toString(key));
        }

        Random rand = new Random();
        Integer keyToRemove = data[rand.nextInt(data.length)];

        for (int i = 0; i < WARMUP_TIMES; i++) {
            treap.remove(keyToRemove);
            treap.put(keyToRemove, Integer.toString(keyToRemove));
        }

        long totalTime = 0;
        for (int i = 0; i < NUM_TIMES; i++) {
            long start = System.nanoTime();
            treap.remove(keyToRemove);
            long end = System.nanoTime();
            totalTime += (end - start);
            treap.put(keyToRemove, Integer.toString(keyToRemove));
        }
        return totalTime / NUM_TIMES;
    }

    private static void benchmarkAVLDelete(Integer[] random, Integer[] sorted, Integer[] reverse, Integer[] partial) throws IOException {
        System.out.println("\nBenchmarking AVLTreeMap delete");
        System.out.println("AVLTreeMap (Random): " + runAVLDeleteBenchmark(random) + " ns");
        System.out.println("AVLTreeMap (Sorted): " + runAVLDeleteBenchmark(sorted) + " ns");
        System.out.println("AVLTreeMap (Reverse Sorted): " + runAVLDeleteBenchmark(reverse) + " ns");
        System.out.println("AVLTreeMap (Partially Sorted): " + runAVLDeleteBenchmark(partial) + " ns");
    }

    private static long runAVLDeleteBenchmark(Integer[] data) throws IOException {
        AVLTreeMap<Integer, String> avl = new AVLTreeMap<>();
        for (Integer key : data) {
            avl.put(key, Integer.toString(key));
        }

        Random rand = new Random();
        Integer keyToRemove = data[rand.nextInt(data.length)];

        for (int i = 0; i < WARMUP_TIMES; i++) {
            avl.remove(keyToRemove);
            avl.put(keyToRemove, Integer.toString(keyToRemove));
        }

        long totalTime = 0;
        for (int i = 0; i < NUM_TIMES; i++) {
            long start = System.nanoTime();
            avl.remove(keyToRemove);
            long end = System.nanoTime();
            totalTime += (end - start);
            avl.put(keyToRemove, Integer.toString(keyToRemove));
        }
        return totalTime / NUM_TIMES;
    }

    private static void benchmarkTreeMapDelete(Integer[] random, Integer[] sorted, Integer[] reverse, Integer[] partial) {
        System.out.println("\nBenchmarking java.util.TreeMap delete");
        System.out.println("TreeMap (Random): " + runTreeMapDeleteBenchmark(random) + " ns");
        System.out.println("TreeMap (Sorted): " + runTreeMapDeleteBenchmark(sorted) + " ns");
        System.out.println("TreeMap (Reverse Sorted): " + runTreeMapDeleteBenchmark(reverse) + " ns");
        System.out.println("TreeMap (Partially Sorted): " + runTreeMapDeleteBenchmark(partial) + " ns");
        System.out.println();
    }

    private static long runTreeMapDeleteBenchmark(Integer[] data) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (Integer key : data) {
            treeMap.put(key, Integer.toString(key));
        }

        Random rand = new Random();
        Integer keyToRemove = data[rand.nextInt(data.length)];

        for (int i = 0; i < WARMUP_TIMES; i++) {
            treeMap.remove(keyToRemove);
            treeMap.put(keyToRemove, Integer.toString(keyToRemove));
        }

        long totalTime = 0;
        for (int i = 0; i < NUM_TIMES; i++) {
            long start = System.nanoTime();
            treeMap.remove(keyToRemove);
            long end = System.nanoTime();
            totalTime += (end - start);
            treeMap.put(keyToRemove, Integer.toString(keyToRemove));
        }
        return totalTime / NUM_TIMES;
    }

    /***************************************************************************************************************************
     * INSERT*******************************************************************************************************************
     * **************************************************************************************************************************/

    private static void benchmarkTreapInsert(Integer[] randomData, Integer[] sortedData, Integer[] reverseSortedData, Integer[] partiallySortedData) throws IOException {
        System.out.println();
        System.out.println("************Benchmarking Treap insert**************");

        System.out.println("Treap (Random): " + runTreapBenchmark(randomData) + " ns");
        System.out.println("Treap (Sorted): " + runTreapBenchmark(sortedData) + " ns");
        System.out.println("Treap (Reverse Sorted): " + runTreapBenchmark(reverseSortedData) + " ns");
        System.out.println("Treap (Partially Sorted): " + runTreapBenchmark(partiallySortedData) + " ns");
        System.out.println();
    }

    private static long runTreapBenchmark(Integer[] data) throws IOException {
        for (int i = 0; i < WARMUP_TIMES; i++) {
            Treap<Integer, String> treap = new Treap<>();
            for (Integer key : data) {
                treap.put(key, Integer.toString(key));
            }
        }

        long totalTime = 0;
        for (int i = 0; i < NUM_TIMES; i++) {
            Treap<Integer, String> treap = new Treap<>();
            long startTime = System.nanoTime();
            for (Integer key : data) {
                treap.put(key, Integer.toString(key));
            }
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / NUM_TIMES;
    }

    private static void benchmarkAVLTreeMapInsert(Integer[] randomData, Integer[] sortedData, Integer[] reverseSortedData, Integer[] partiallySortedData) throws IOException {
        System.out.println();
        System.out.println("*********Benchmarking AVLTreeMap insert*****");

        System.out.println("AVLTreeMap (Random): " + runAVLBenchmark(randomData) + " ns");
        System.out.println("AVLTreeMap (Sorted): " + runAVLBenchmark(sortedData) + " ns");
        System.out.println("AVLTreeMap (Reverse Sorted): " + runAVLBenchmark(reverseSortedData) + " ns");
        System.out.println("AVLTreeMap (Partially Sorted): " + runAVLBenchmark(partiallySortedData) + " ns");
        System.out.println();
    }

    private static long runAVLBenchmark(Integer[] data) throws IOException {
        for (int i = 0; i < WARMUP_TIMES; i++) {
            AVLTreeMap<Integer, String> avlTreeMap = new AVLTreeMap<>();
            for (Integer key : data) {
                avlTreeMap.put(key, Integer.toString(key));
            }
        }

        long totalTime = 0;
        for (int i = 0; i < NUM_TIMES; i++) {
            AVLTreeMap<Integer, String> avlTreeMap = new AVLTreeMap<>();
            long startTime = System.nanoTime();
            for (Integer key : data) {
                avlTreeMap.put(key, Integer.toString(key));
            }
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / NUM_TIMES;
    }

    private static void benchmarkJavaTreeMapInsert(Integer[] randomData, Integer[] sortedData, Integer[] reverseSortedData, Integer[] partiallySortedData) {
        System.out.println();
        System.out.println("********Benchmarking java.util.TreeMap insert************");

        System.out.println("TreeMap (Random): " + runTreeMapBenchmark(randomData) + " ns");
        System.out.println("TreeMap (Sorted): " + runTreeMapBenchmark(sortedData) + " ns");
        System.out.println("TreeMap (Reverse Sorted): " + runTreeMapBenchmark(reverseSortedData) + " ns");
        System.out.println("TreeMap (Partially Sorted): " + runTreeMapBenchmark(partiallySortedData) + " ns");
        System.out.println();
    }

    private static long runTreeMapBenchmark(Integer[] data) {
        for (int i = 0; i < WARMUP_TIMES; i++) {
            TreeMap<Integer, String> treeMap = new TreeMap<>();
            for (Integer key : data) {
                treeMap.put(key, Integer.toString(key));
            }
        }

        long totalTime = 0;
        for (int i = 0; i < NUM_TIMES; i++) {
            TreeMap<Integer, String> treeMap = new TreeMap<>();
            long startTime = System.nanoTime();
            for (Integer key : data) {
                treeMap.put(key, Integer.toString(key));
            }
            long endTime = System.nanoTime();
            totalTime += (endTime - startTime);
        }
        return totalTime / NUM_TIMES;
    }

    private static void benchmarkTreapInsertSingle(Integer[] random, Integer[] sorted, Integer[] reverse, Integer[] partial) throws IOException {
        System.out.println("\nBenchmarking Treap insertSingle");
        System.out.println("Treap (Random): " + runTreapInsertSingle(random) + " ns");
        System.out.println("Treap (Sorted): " + runTreapInsertSingle(sorted) + " ns");
        System.out.println("Treap (Reverse Sorted): " + runTreapInsertSingle(reverse) + " ns");
        System.out.println("Treap (Partially Sorted): " + runTreapInsertSingle(partial) + " ns");
    }

    private static long runTreapInsertSingle(Integer[] data) throws IOException {
        Treap<Integer, String> treap = new Treap<>();

        Random rand = new Random();
        Integer keyToInsert = data[rand.nextInt(data.length)];

        for (int i = 0; i < WARMUP_TIMES; i++) {
            treap.put(keyToInsert, Integer.toString(keyToInsert));
            treap.remove(keyToInsert);
        }

        long totalTime = 0;
        for (int i = 0; i < NUM_TIMES; i++) {
            long start = System.nanoTime();
            treap.put(keyToInsert, Integer.toString(keyToInsert));
            long end = System.nanoTime();
            totalTime += (end - start);
            treap.remove(keyToInsert);
        }
        return totalTime / NUM_TIMES;
    }

    private static void benchmarkAVLInsertSingle(Integer[] random, Integer[] sorted, Integer[] reverse, Integer[] partial) throws IOException {
        System.out.println("\nBenchmarking AVLTreeMap insertSingle");
        System.out.println("AVLTreeMap (Random): " + runAVLInsertSingle(random) + " ns");
        System.out.println("AVLTreeMap (Sorted): " + runAVLInsertSingle(sorted) + " ns");
        System.out.println("AVLTreeMap (Reverse Sorted): " + runAVLInsertSingle(reverse) + " ns");
        System.out.println("AVLTreeMap (Partially Sorted): " + runAVLInsertSingle(partial) + " ns");
    }

    private static long runAVLInsertSingle(Integer[] data) throws IOException {
        AVLTreeMap<Integer, String> avl = new AVLTreeMap<>();

        Random rand = new Random();
        Integer keyToInsert = data[rand.nextInt(data.length)];

        for (int i = 0; i < WARMUP_TIMES; i++) {
            avl.put(keyToInsert, Integer.toString(keyToInsert));
            avl.remove(keyToInsert);
        }

        long totalTime = 0;
        for (int i = 0; i < NUM_TIMES; i++) {
            long start = System.nanoTime();
            avl.put(keyToInsert, Integer.toString(keyToInsert));
            long end = System.nanoTime();
            totalTime += (end - start);
            avl.remove(keyToInsert);
        }
        return totalTime / NUM_TIMES;
    }

    private static void benchmarkTreeMapInsertSingle(Integer[] random, Integer[] sorted, Integer[] reverse, Integer[] partial) {
        System.out.println("\nBenchmarking java.util.TreeMap insertSingle");
        System.out.println("TreeMap (Random): " + runTreeMapInsertSingle(random) + " ns");
        System.out.println("TreeMap (Sorted): " + runTreeMapInsertSingle(sorted) + " ns");
        System.out.println("TreeMap (Reverse Sorted): " + runTreeMapInsertSingle(reverse) + " ns");
        System.out.println("TreeMap (Partially Sorted): " + runTreeMapInsertSingle(partial) + " ns");
    }

    private static long runTreeMapInsertSingle(Integer[] data) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        Random rand = new Random();
        Integer keyToInsert = data[rand.nextInt(data.length)];

        for (int i = 0; i < WARMUP_TIMES; i++) {
            treeMap.put(keyToInsert, Integer.toString(keyToInsert));
            treeMap.remove(keyToInsert);
        }

        long totalTime = 0;
        for (int i = 0; i < NUM_TIMES; i++) {
            long start = System.nanoTime();
            treeMap.put(keyToInsert, Integer.toString(keyToInsert));
            long end = System.nanoTime();
            totalTime += (end - start);
            treeMap.remove(keyToInsert);
        }
        return totalTime / NUM_TIMES;
    }


    /*********************************************************************************
     *Generator Methods***************************************************************
     * *******************************************************************************/
    private static Integer[] generateRandomData(int size) {
        Integer[] data = new Random().ints(size).distinct().limit(size).boxed().toArray(Integer[]::new);
        return data;
    }

    private static Integer[] generateSortedData(int size) {
        Integer[] data = new Integer[size];
        for (int i = 0; i < size; i++) {
            data[i] = i;
        }
        return data;
    }

    private static Integer[] generateReverseSortedData(int size) {
        Integer[] data = new Integer[size];
        for (int i = 0; i < size; i++) {
            data[i] = size - i - 1;
        }
        return data;
    }

    private static Integer[] generatePartiallySortedData(int size) {
        Integer[] data = new Integer[size];
        for (int i = 0; i < size / 2; i++) {
            data[i] = i;
        }
        List<Integer> secondHalf = new ArrayList<>();
        for (int i = size / 2; i < size; i++) {
            secondHalf.add(i);
        }
        Collections.shuffle(secondHalf);
        for (int i = size / 2; i < size; i++) {
            data[i] = secondHalf.get(i - size / 2);
        }
        return data;
    }
}