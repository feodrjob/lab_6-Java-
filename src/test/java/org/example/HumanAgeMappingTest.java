package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.changeKeysOnAge;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class HumanAgeMappingTest {

    private Map<Integer, Human> humanMap;
    private Map<Integer, Human> emptyMap;
    private Map<Integer, Human> singleEntryMap;
    private Map<Integer, Human> duplicatesAgeMap;

    @BeforeEach
    public void setUp() {
        // Основная тестовая мапа
        humanMap = new HashMap<>();
        humanMap.put(101, new Human("Smith", "John", "M", 30));
        humanMap.put(102, new Student("Johnson", "Alice", "P", 20, "CS"));
        humanMap.put(103, new Human("Williams", "Bob", "Q", 25));

        // Пустая мапа
        emptyMap = Collections.emptyMap();

        // Мапа с одним элементом
        singleEntryMap = new HashMap<>();
        singleEntryMap.put(1, new Human("Doe", "Jane", "R", 40));

        // Мапа с повторяющимися возрастами
        duplicatesAgeMap = new HashMap<>();
        duplicatesAgeMap.put(201, new Human("Brown", "Emma", "S", 22));
        duplicatesAgeMap.put(202, new Student("Davis", "Liam", "T", 22, "Math"));
    }

    @Test
    public void testChangeKeysOnAge_NormalCase() {
        Map<Integer, Integer> result = changeKeysOnAge(humanMap);

        assertEquals(3, result.size());
        assertEquals(30, result.get(101));
        assertEquals(20, result.get(102));
        assertEquals(25, result.get(103));
        assertNull(result.get(999)); // Несуществующий ключ
    }

    @Test
    public void testChangeKeysOnAge_SingleEntry() {
        Map<Integer, Integer> result = changeKeysOnAge(singleEntryMap);

        assertEquals(1, result.size());
        assertEquals(40, result.get(1));
    }

    @Test
    public void testChangeKeysOnAge_DuplicateAges() {
        Map<Integer, Integer> result = changeKeysOnAge(duplicatesAgeMap);

        assertEquals(2, result.size());
        assertEquals(22, result.get(201));
        assertEquals(22, result.get(202));
    }

    @Test
    public void testChangeKeysOnAge_EmptyMap() {
        assertThrows(IllegalArgumentException.class, () -> {
            changeKeysOnAge(emptyMap);
        });
    }


    @Test
    public void testChangeKeysOnAge_WithStudents() {
        Map<Integer, Human> studentMap = new HashMap<>();
        studentMap.put(301, new Student("Wilson", "Eva", "U", 19, "Physics"));

        Map<Integer, Integer> result = changeKeysOnAge(studentMap);

        assertEquals(1, result.size());
        assertEquals(19, result.get(301));
    }

    @Test
    public void testChangeKeysOnAge_CheckImmutableInput() {
        // Создаем копию для проверки, что исходная мапа не изменилась
        Map<Integer, Human> original = new HashMap<>(humanMap);
        changeKeysOnAge(humanMap);

        assertEquals(original, humanMap);
    }

    @Test
    public void testChangeKeysOnAge_ZeroAge() {
        Map<Integer, Human> zeroAgeMap = new HashMap<>();
        zeroAgeMap.put(401, new Human("Taylor", "Roy", "V", 0));

        Map<Integer, Integer> result = changeKeysOnAge(zeroAgeMap);

        assertEquals(1, result.size());
        assertEquals(0, result.get(401));
    }
}
