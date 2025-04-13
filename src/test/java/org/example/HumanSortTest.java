package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.CollectionsDemo.getSortedSet;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;

public class HumanSortTest {

    private HashSet<Human> humans;
    private HashSet<Human> humansWithSameSurnames;
    private HashSet<Human> emptySet;
    private HashSet<Human> studentsSet;

    @BeforeEach
    public void setUp() {
        // Initialize test data for different humans
        humans = new HashSet<>();
        humans.add(new Human("Smith", "John", "Michael", 30));
        humans.add(new Human("Johnson", "Robert", "William", 25));
        humans.add(new Human("Williams", "David", "James", 35));
        humans.add(new Human("Brown", "Richard", "Joseph", 40));

        // Initialize test data for humans with same surnames
        humansWithSameSurnames = new HashSet<>();
        humansWithSameSurnames.add(new Human("Smith", "William", "Thomas", 30));
        humansWithSameSurnames.add(new Human("Smith", "John", "Edward", 25));
        humansWithSameSurnames.add(new Human("Smith", "John", "Henry", 35));

        // Initialize empty set
        emptySet = new HashSet<>();

        // Initialize set with students
        studentsSet = new HashSet<>();
        studentsSet.add(new Student("Davis", "Charles", "George", 23, "Physics"));
        studentsSet.add(new Student("Anderson", "Thomas", "Paul", 21, "Mathematics"));
        studentsSet.add(new Student("Davis", "Daniel", "Mark", 22, "Computer Scienc"));
    }

    @Test
    public void testGetSortedSetWithDifferentHumans() {
        ArrayList<Human> sorted = getSortedSet(humans);

        assertEquals("Brown", sorted.get(0).getSurname());
        assertEquals("Johnson", sorted.get(1).getSurname());
        assertEquals("Smith", sorted.get(2).getSurname());
        assertEquals("Williams", sorted.get(3).getSurname());
    }

    @Test
    public void testGetSortedSetWithSameSurnames() {
        ArrayList<Human> sorted = getSortedSet(humansWithSameSurnames);

        // Проверяем порядок при одинаковых фамилиях
        assertEquals("John", sorted.get(0).getName());
        assertEquals("Edward", sorted.get(0).getSecondname());  // Edward идет первым

        assertEquals("John", sorted.get(1).getName());
        assertEquals("Henry", sorted.get(1).getSecondname());  // Henry идет вторым

        assertEquals("William", sorted.get(2).getName());
        assertEquals("Thomas", sorted.get(2).getSecondname());
    }

    @Test
    public void testGetSortedSetWithStudents() {
        ArrayList<Human> sorted = getSortedSet(studentsSet);

        // Проверяем порядок сортировки
        assertEquals("Anderson", sorted.get(0).getSurname());

        assertEquals("Davis", sorted.get(1).getSurname());
        assertEquals("Charles", sorted.get(1).getName());  // Charles идет перед Daniel

        assertEquals("Davis", sorted.get(2).getSurname());
        assertEquals("Daniel", sorted.get(2).getName());
    }
    @Test
    public void testGetSortedSetWithEmptySet() {
        assertThrows(IllegalArgumentException.class, () -> {
            getSortedSet(emptySet);
        });
    }

    @Test
    public void testGetSortedSetWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            getSortedSet(null);
        });
    }
}