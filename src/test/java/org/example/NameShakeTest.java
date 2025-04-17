package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.example.CollectionsDemo.getNamesakes;

public class NameShakeTest {
    ArrayList<Human> humans;
    ArrayList<Human> namesake1;
    ArrayList<Human> namesake2;
    Human human1 = new Human("Ivanov", "Ivan", "Ivanovich", 19);
    Human human2 = new Human("Kobzev", "Vyacheslav", "Yurevich", 43);
    Human human3 = new Human("Petrov", "Petr", "Petrovich", 52);
    Human human4 = new Human("Sidorov", "Sidr", "Sidorovich", 14);
    Human human5 = new Human("Petrov", "Igor", "Antonovich", 23);


    @Before
    public void setUp() {
        humans = new ArrayList<>();
        humans.add(human1);
        humans.add(human2);
        humans.add(human3);
        humans.add(human4);
        humans.add(human5);

        namesake1 = new ArrayList<>();
        namesake1.add(human3);
        namesake1.add(human5);

        namesake2 = new ArrayList<>();
        namesake2.add(human4);
    }


    @Test
    public void getNamesake1() {
        Assert.assertEquals(namesake1, getNamesakes(humans, human3));
    }

    @Test
    public void getNamesake2() {
        Assert.assertEquals(namesake2, getNamesakes(humans, human4));
    }
}
