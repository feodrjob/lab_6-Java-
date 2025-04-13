package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getSetOfPeopleOlder18;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class HumanAgeFilterTest {

    private Map<Integer, Human> humanMap;
    private Map<Integer, Human> emptyMap;
    private Map<Integer, Human> allAdultsMap;
    private Map<Integer, Human> mixedAgesMap;

    @BeforeEach
    public void setUp() {
        // Основная мапа с разными возрастами
        humanMap = new HashMap<>();
        humanMap.put(1, new Human("Smith", "John", "Michael", 30));  // взрослый
        humanMap.put(2, new Human("Johnson", "Alice", "Marie", 17));  // не взрослый
        humanMap.put(3, new Student("Williams", "Bob", "James", 18, "CS")); // взрослый (ровно 18)
        humanMap.put(4, new Human("Brown", "Emma", "Grace", 15));    // не взрослый

        // Пустая мапа
        emptyMap = Collections.emptyMap();

        // Все взрослые
        allAdultsMap = new HashMap<>();
        allAdultsMap.put(1, new Human("Doe", "John", "A", 25));
        allAdultsMap.put(2, new Human("Doe", "Jane", "B", 30));

        // Смешанные возраста (только дети)
        mixedAgesMap = new HashMap<>();
        mixedAgesMap.put(1, new Student("Junior", "Mike", "C", 16, "Math"));
        mixedAgesMap.put(2, new Student("Junior", "Anna", "D", 17, "Physics"));
    }

    @Test
    public void testGetSetOfPeopleOlder18_NormalCase() {
        Set<Human> result = getSetOfPeopleOlder18(humanMap);

        assertEquals(2, result.size());
        assertTrue(result.contains(humanMap.get(1))); // 30 лет
        assertTrue(result.contains(humanMap.get(3))); // 18 лет
        assertFalse(result.contains(humanMap.get(2))); // 17 лет
        assertFalse(result.contains(humanMap.get(4))); // 15 лет
    }

    @Test
    public void testGetSetOfPeopleOlder18_AllAdults() {
        Set<Human> result = getSetOfPeopleOlder18(allAdultsMap);

        assertEquals(2, result.size());
        assertTrue(result.containsAll(allAdultsMap.values()));
    }

    @Test
    public void testGetSetOfPeopleOlder18_NoAdults() {
        Set<Human> result = getSetOfPeopleOlder18(mixedAgesMap);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetSetOfPeopleOlder18_EmptyMap() {
        assertThrows(IllegalArgumentException.class, () -> {
            getSetOfPeopleOlder18(emptyMap);
        });
    }


    @Test
    public void testGetSetOfPeopleOlder18_BorderlineAge18() {
        Map<Integer, Human> borderlineMap = new HashMap<>();
        borderlineMap.put(1, new Human("Border", "Case", "Test", 18));

        Set<Human> result = getSetOfPeopleOlder18(borderlineMap);

        assertEquals(1, result.size());
        assertTrue(result.contains(borderlineMap.get(1)));
    }

    @Test
    public void testGetSetOfPeopleOlder18_BorderlineAge17() {
        Map<Integer, Human> borderlineMap = new HashMap<>();
        borderlineMap.put(1, new Human("Border", "Case", "Test", 17));

        Set<Human> result = getSetOfPeopleOlder18(borderlineMap);

        assertTrue(result.isEmpty());
    }


}