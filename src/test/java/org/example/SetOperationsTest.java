package org.example;

import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getListOfSets;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashSet;

public class SetOperationsTest {

    @Test
    public void testNoIntersection() {
        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        list.add(new HashSet<Integer>() {{ add(1); add(2); }});
        list.add(new HashSet<Integer>() {{ add(3); add(4); }});
        list.add(new HashSet<Integer>() {{ add(5); }});

        HashSet<Integer> testSet = new HashSet<Integer>() {{ add(6); add(7); }};

        ArrayList<HashSet<Integer>> result = getListOfSets(list, testSet);

        assertEquals(3, result.size());
        assertTrue(result.containsAll(list));
    }

    @Test
    public void testSomeIntersection() {
        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        list.add(new HashSet<Integer>() {{ add(1); add(2); }});
        list.add(new HashSet<Integer>() {{ add(3); add(4); }});
        list.add(new HashSet<Integer>() {{ add(5); }});

        HashSet<Integer> testSet = new HashSet<Integer>() {{ add(2); add(6); }};

        ArrayList<HashSet<Integer>> result = getListOfSets(list, testSet);

        assertEquals(2, result.size());
        assertTrue(result.contains(list.get(1)));
        assertTrue(result.contains(list.get(2)));
        assertFalse(result.contains(list.get(0)));
    }

    @Test
    public void testAllIntersect() {
        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        list.add(new HashSet<Integer>() {{ add(1); add(2); }});
        list.add(new HashSet<Integer>() {{ add(2); add(3); }});

        HashSet<Integer> testSet = new HashSet<Integer>() {{ add(2); }};

        ArrayList<HashSet<Integer>> result = getListOfSets(list, testSet);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyInputList() {
        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        HashSet<Integer> testSet = new HashSet<Integer>() {{ add(1); }};

        assertThrows(IllegalArgumentException.class, () -> {
            getListOfSets(list, testSet);
        });
    }

    @Test
    public void testEmptyTestSet() {
        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        list.add(new HashSet<Integer>() {{ add(1); }});

        HashSet<Integer> testSet = new HashSet<>();

        assertThrows(IllegalArgumentException.class, () -> {
            getListOfSets(list, testSet);
        });
    }

    @Test
    public void testEmptySetsInList() {
        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        list.add(new HashSet<Integer>() {{ add(1); }});
        list.add(new HashSet<Integer>());

        HashSet<Integer> testSet = new HashSet<Integer>() {{ add(2); }};

        // Пустое множество не пересекается ни с каким другим множеством
        ArrayList<HashSet<Integer>> result = getListOfSets(list, testSet);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(list));
    }
}

