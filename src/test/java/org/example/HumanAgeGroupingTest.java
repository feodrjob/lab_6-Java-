package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.changeKeysOnAge;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class HumanAgeGroupingTest {
    private Map<Integer, Human> humanMap;
    private Human person1;
    private Human person2;
    private Student student1;

    @BeforeEach
    public void setUp() {
        // Инициализация тестовых данных
        person1 = new Human("Ivanov", "Ivan", "Ivanovich", 25);
        person2 = new Human("Petrov", "Petr", "Petrovich", 30);
        student1 = new Student("Sidorov", "Sidor", "Sidorovich", 20, "CS");

        humanMap = new HashMap<>();
        humanMap.put(1, person1);
        humanMap.put(2, person2);
        humanMap.put(3, student1);
    }

    @Test
    public void testBasicAgeMapping() {
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 25);
        expected.put(2, 30);
        expected.put(3, 20);

        Map<Integer, Integer> result = changeKeysOnAge(humanMap);

        assertEquals(expected, result);
    }

    @Test
    public void testWithDuplicateAges() {
        humanMap.put(4, new Human("Kuznetsov", "Alex", "Sergeevich", 25)); // Дубликат возраста

        Map<Integer, Integer> result = changeKeysOnAge(humanMap);

        assertEquals(25, result.get(1));
        assertEquals(25, result.get(4));
        assertEquals(4, result.size());
    }

    @Test
    public void testSingleEntry() {
        humanMap.clear();
        humanMap.put(1, person1);

        Map<Integer, Integer> expected = Collections.singletonMap(1, 25);
        Map<Integer, Integer> result = changeKeysOnAge(humanMap);

        assertEquals(expected, result);
    }

    @Test
    public void testEmptyInput() {
        humanMap.clear();
        assertThrows(IllegalArgumentException.class, () -> {
            changeKeysOnAge(humanMap);
        });
    }

    @Test
    public void testNullInput() {
        assertThrows(NullPointerException.class, () -> {
            changeKeysOnAge(null);
        });
    }

    @Test
    public void testWithStudentAge() {
        Map<Integer, Integer> result = changeKeysOnAge(humanMap);
        assertEquals(20, result.get(3));
    }

    @Test
    public void testAgeZero() {
        humanMap.put(4, new Human("Smith", "John", "Doe", 0));

        Map<Integer, Integer> result = changeKeysOnAge(humanMap);
        assertEquals(0, result.get(4));
    }

    @Test
    public void testNegativeAge() {
        humanMap.put(5, new Human("Doe", "Jane", "Smith", -1));

        Map<Integer, Integer> result = changeKeysOnAge(humanMap);
        assertEquals(-1, result.get(5));
    }
}