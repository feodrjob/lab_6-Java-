package org.example;

import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getListOfSets;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class SetOperationsTest {

    @Test
    public void testNoIntersection() {
        List<Set<Integer>> list = Arrays.asList(
                new HashSet<>(Arrays.asList(1, 2)),
                new HashSet<>(Arrays.asList(3, 4)),
                new HashSet<>(Collections.singletonList(5))
        );

        Set<Integer> testSet = new HashSet<>(Arrays.asList(9, 3));

        List<Set<Integer>> result = getListOfSets(list, testSet);

        List<Set<Integer>> expected = Arrays.asList(
                new HashSet<>(Arrays.asList(1, 2)),
                new HashSet<>(Collections.singletonList(5))
        );
        assertEquals(expected, result);
    }

    @Test
    public void testSomeIntersection() {
        List<Set<Integer>> list = Arrays.asList(
                new HashSet<>(Arrays.asList(1, 2)),
                new HashSet<>(Arrays.asList(3, 4)),
                new HashSet<>(Collections.singletonList(5))
        );

        Set<Integer> testSet = new HashSet<>(Arrays.asList(2, 6));

        List<Set<Integer>> result = getListOfSets(list, testSet);

        assertEquals(2, result.size());
        assertTrue(result.contains(list.get(1)));
        assertTrue(result.contains(list.get(2)));
        assertFalse(result.contains(list.get(0)));
    }

    @Test
    public void testAllIntersect() {
        List<Set<Integer>> list = Arrays.asList(
                new HashSet<>(Arrays.asList(1, 2)),
                new HashSet<>(Arrays.asList(2, 3))
        );

        Set<Integer> testSet = new HashSet<>(Collections.singletonList(2));

        List<Set<Integer>> result = getListOfSets(list, testSet);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyInputList() {
        List<Set<Integer>> list = Collections.emptyList();
        Set<Integer> testSet = new HashSet<>(Collections.singletonList(1));

        assertThrows(IllegalArgumentException.class, () -> {
            getListOfSets(list, testSet);
        });
    }

    @Test
    public void testEmptyTestSet() {
        List<Set<Integer>> list = Collections.singletonList(
                new HashSet<>(Collections.singletonList(1))
        );

        Set<Integer> testSet = Collections.emptySet();

        assertThrows(IllegalArgumentException.class, () -> {
            getListOfSets(list, testSet);
        });
    }

    @Test
    public void testEmptySetsInList() {
        List<Set<Integer>> list = Arrays.asList(
                new HashSet<>(Collections.singletonList(1)),
                new HashSet<>()
        );

        Set<Integer> testSet = new HashSet<>(Collections.singletonList(2));

        List<Set<Integer>> result = getListOfSets(list, testSet);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(list));
    }
}