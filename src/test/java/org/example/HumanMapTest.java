package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getSetOfPeopleByIdentifier;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class HumanMapTest {
    private Map<Integer, Human> humanMap;
    private Human ivanov;
    private Human petrov;
    private Human sidorov;
    private Student student;

    @BeforeEach
    public void setUp() {
        // Инициализация тестовых данных
        ivanov = new Human("Ivanov", "Ivan", "Ivanovich", 20);
        petrov = new Human("Petrov", "Petr", "Petrovich", 25);
        sidorov = new Human("Sidorov", "Sidor", "Sidorovich", 25);
        student = new Student("Abramov", "Alex", "Sergeevich", 30, "Computer Science");

        humanMap = new HashMap<>();
        humanMap.put(1, ivanov);
        humanMap.put(2, petrov);
        humanMap.put(3, sidorov);
        humanMap.put(4, student);
    }

    @Test
    public void testBasicFunctionality() {
        Set<Integer> ids = new HashSet<>(Arrays.asList(1, 3));

        HashSet<Human> expected = new HashSet<>(Arrays.asList(ivanov, sidorov));
        HashSet<Human> result = getSetOfPeopleByIdentifier(humanMap, ids);

        assertEquals(expected, result);
    }

    @Test
    public void testWithStudent() {
        Set<Integer> ids = new HashSet<>(Collections.singletonList(4));

        HashSet<Human> expected = new HashSet<>(Collections.singletonList(student));
        HashSet<Human> result = getSetOfPeopleByIdentifier(humanMap, ids);

        assertEquals(expected, result);
    }

    @Test
    public void testWithNonExistingIds() {
        Set<Integer> ids = new HashSet<>(Arrays.asList(5, 6, 7));

        HashSet<Human> expected = new HashSet<>();
        HashSet<Human> result = getSetOfPeopleByIdentifier(humanMap, ids);

        assertEquals(expected, result);
    }

    @Test
    public void testMixedExistingAndNonExistingIds() {
        Set<Integer> ids = new HashSet<>(Arrays.asList(1, 5, 2, 6));

        HashSet<Human> expected = new HashSet<>(Arrays.asList(ivanov, petrov));
        HashSet<Human> result = getSetOfPeopleByIdentifier(humanMap, ids);

        assertEquals(expected, result);
    }

    @Test
    public void testEmptyIdsSet() {
        Set<Integer> ids = new HashSet<>();

        HashSet<Human> expected = new HashSet<>();
        HashSet<Human> result = getSetOfPeopleByIdentifier(humanMap, ids);

        assertEquals(expected, result);
    }

    @Test
    public void testEmptyHumanMap() {
        Map<Integer, Human> emptyMap = Collections.emptyMap();
        Set<Integer> ids = new HashSet<>(Arrays.asList(1, 2));

        assertThrows(IllegalArgumentException.class, () -> {
            getSetOfPeopleByIdentifier(emptyMap, ids);
        });
    }

    @Test
    public void testNullInput() {
        assertThrows(NullPointerException.class, () -> {
            getSetOfPeopleByIdentifier(null, new HashSet<>());
        });

        assertThrows(NullPointerException.class, () -> {
            getSetOfPeopleByIdentifier(humanMap, null);
        });
    }
}