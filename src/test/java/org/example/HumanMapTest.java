package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getSetOfPeopleByIdentifier;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class HumanMapTest {

    private Map<Integer, Human> humanMap;
    private Set<Integer> idsToFind;

    @BeforeEach
    public void setUp() {
        // Инициализация тестовых данных
        humanMap = new HashMap<>();
        humanMap.put(1, new Human("Smith", "John", "Michael", 30));
        humanMap.put(2, new Human("Johnson", "Alice", "Marie", 25));
        humanMap.put(3, new Student("Williams", "Bob", "James", 20, "Computer Science"));
        humanMap.put(4, new Human("Brown", "Emma", "Grace", 35));

        idsToFind = new HashSet<>(Arrays.asList(1, 3, 5)); // 5 нет в мапе
    }

    @Test
    public void testGetSetOfPeopleByIdentifier_NormalCase() {
        Set<Human> result = getSetOfPeopleByIdentifier(humanMap, idsToFind);

        assertEquals(2, result.size());
        assertTrue(result.contains(humanMap.get(1)));
        assertTrue(result.contains(humanMap.get(3)));
        assertFalse(result.contains(humanMap.get(2)));
    }

    @Test
    public void testGetSetOfPeopleByIdentifier_EmptyIdsSet() {
        Set<Integer> emptyIds = Collections.emptySet();
        Set<Human> result = getSetOfPeopleByIdentifier(humanMap, emptyIds);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetSetOfPeopleByIdentifier_NoMatches() {
        Set<Integer> nonExistingIds = new HashSet<>(Arrays.asList(5, 6, 7));
        Set<Human> result = getSetOfPeopleByIdentifier(humanMap, nonExistingIds);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetSetOfPeopleByIdentifier_AllMatches() {
        Set<Integer> allExistingIds = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Human> result = getSetOfPeopleByIdentifier(humanMap, allExistingIds);

        assertEquals(4, result.size());
        assertTrue(result.containsAll(humanMap.values()));
    }

    @Test
    public void testGetSetOfPeopleByIdentifier_WithStudentObjects() {
        Set<Integer> studentId = new HashSet<>(Collections.singletonList(3));
        Set<Human> result = getSetOfPeopleByIdentifier(humanMap, studentId);

        assertEquals(1, result.size());
        Human student = result.iterator().next();
        assertTrue(student instanceof Student);
        assertEquals("Williams", student.getSurname());
    }


    @Test
    public void testGetSetOfPeopleByIdentifier_EmptyMap() {
        assertThrows(IllegalArgumentException.class, () -> {
            getSetOfPeopleByIdentifier(Collections.emptyMap(), idsToFind);
        });
    }
}