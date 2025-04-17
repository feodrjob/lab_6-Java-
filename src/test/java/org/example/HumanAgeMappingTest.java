package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getSetOfPeopleOlder18;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class HumanAgeMappingTest {
    private Map<Integer, Human> humanMap;
    private Human adult1;
    private Human adult2;
    private Human child;
    private Student adultStudent;

    @BeforeEach
    public void setUp() {
        // Инициализация тестовых данных
        adult1 = new Human("Ivanov", "Ivan", "Ivanovich", 25);
        adult2 = new Human("Petrov", "Petr", "Petrovich", 30);
        child = new Human("Sidorov", "Sidor", "Sidorovich", 17);
        adultStudent = new Student("Abramov", "Alex", "Sergeevich", 19, "CS");

        humanMap = new HashMap<>();
        humanMap.put(1, adult1);
        humanMap.put(2, adult2);
        humanMap.put(3, child);
        humanMap.put(4, adultStudent);
    }

    @Test
    public void testReturnsOnlyAdults() {
        HashSet<Human> expected = new HashSet<>();
        expected.add(adult1);
        expected.add(adult2);
        expected.add(adultStudent);

        HashSet<Human> result = getSetOfPeopleOlder18(humanMap);

        assertEquals(expected, result);
    }

    @Test
    public void testBoundaryAge18() {
        Human exactly18 = new Human("Kuznetsov", "Alex", "Sergeevich", 18);
        humanMap.put(5, exactly18);

        HashSet<Human> result = getSetOfPeopleOlder18(humanMap);
        assertTrue(result.contains(exactly18));
    }

    @Test
    public void testExcludesChildren() {
        HashSet<Human> result = getSetOfPeopleOlder18(humanMap);
        assertFalse(result.contains(child));
    }

    @Test
    public void testEmptyMap() {
        humanMap.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            getSetOfPeopleOlder18(humanMap);
        });
    }

    @Test
    public void testNullInput() {
        assertThrows(NullPointerException.class, () -> {
            getSetOfPeopleOlder18(null);
        });
    }

    @Test
    public void testAllAdults() {
        humanMap.remove(3); // Удаляем ребенка

        HashSet<Human> result = getSetOfPeopleOlder18(humanMap);
        assertEquals(3, result.size());
    }

    @Test
    public void testAllChildren() {
        humanMap.clear();
        humanMap.put(1, new Human("A", "B", "C", 16));
        humanMap.put(2, new Human("D", "E", "F", 17));

        HashSet<Human> result = getSetOfPeopleOlder18(humanMap);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDuplicateAdults() {
        humanMap.put(5, adult1); // Дубликат adult1 с другим ID

        HashSet<Human> result = getSetOfPeopleOlder18(humanMap);
        assertEquals(3, result.size()); // Дубликаты автоматически удаляются
    }
}
