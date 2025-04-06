package org.example;

import java.lang.reflect.Array;
import java.util.*;

public class CollectionsDemo {
    public static int getCountDtringsForFirstSymbol(ArrayList<String> strings, char value) {
        int count = 0;
        for (String string : strings) {
            if (string.isEmpty()) throw new IllegalArgumentException("String is empty");
            if (string.startsWith(String.valueOf(value))) {
                count += 1;
            }
        }
        return count;
    }

    public static ArrayList<Human> getNamesakes(ArrayList<Human> humans, Human man) {
        ArrayList<Human> namesakes = new ArrayList<>();
        for (Human realman : humans) {
            if (realman == null) throw new IllegalArgumentException("Human is empty");
            if (man.getSecondname() == realman.getSecondname()) {

            }
        }
        return namesakes;
    }

    public static ArrayList<HashSet<Integer>> getListOfSets(ArrayList<HashSet<Integer>> setarrylist, HashSet<Integer> oneset) {
        if (setarrylist.isEmpty()) throw new IllegalArgumentException("Sets is empty");
        if (oneset.isEmpty()) throw new IllegalArgumentException("Set is empty");
        ArrayList<HashSet<Integer>> setWithoutIntersections = new ArrayList<>();
        boolean flag = true;
        for (HashSet<Integer> set : setarrylist){
            for (Integer num : oneset){
                if(set.contains(num)) {
                    flag = false;
                    break;
                }

            }
            if (flag){
                setWithoutIntersections.add(set);
            }
            flag = true;
        }
        return setWithoutIntersections;
    }

    public static ArrayList<?extends Human> getPeoplesByMaxAge(HashSet<Human> humans){
        if (humans == null|| humans.isEmpty()) throw new IllegalArgumentException("humans is empty");
        int maxAge = 0;
        ArrayList<Human> people = new ArrayList<>();

        for (Human human : humans){
            if (human.getAge() > maxAge) maxAge = human.getAge();
        }

        for (Human human : humans) {
            if(human.getAge() == maxAge) people.add(human);
        }
        return people;
    }

    public static ArrayList<Human> getSortedSet(HashSet<Human> humans){
        if (humans == null|| humans.isEmpty()) throw new IllegalArgumentException("humans is empty");
        TreeSet<Human> treeSet = new TreeSet<>();
        ArrayList<Human> result = new ArrayList<>();

        for(Human human : humans){
            treeSet.add(human);
        }

        for(Human human : treeSet){
            result.add(human);
        }
        return result;
    }

    public static HashSet<Human> getSetOfPeopleByIdentifier(Map<Integer,Human> humanMap, Set<Integer> nums){
        if (humanMap.isEmpty() || humanMap == null) throw new IllegalArgumentException("human is Empty");

        HashSet<Human> result = new HashSet<>();

        for (Integer num : nums){
            if(humanMap.containsKey(num)) result.add(humanMap.get(num));
        }
        return result;
    }

    public static HashSet<Human> getSetOfPeopleOlder18(Map<Integer,Human> humanMap){
        if (humanMap.isEmpty() || humanMap == null) throw new IllegalArgumentException("human is Empty");

        HashSet<Human> result = new HashSet<>();

        for (Map.Entry<Integer,Human> man : humanMap.entrySet()){
            if(man.getValue().getAge()>=18) result.add(humanMap.get(man.getKey()));
        }
        return result;
    }

    public static Map<Integer,Human> changeKeysOnAge(Map<Integer,Human> humanMap){
        if (humanMap.isEmpty() || humanMap == null) throw new IllegalArgumentException("human is Empty");
        Map<Integer,Human> result = new HashMap<>();

        for(Integer key : humanMap.keySet()){
            result.put(key,humanMap.get(humanMap.keySet()));
        }
        return result;

    }









}
