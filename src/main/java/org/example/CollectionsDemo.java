package org.example;

import java.lang.reflect.Array;
import java.util.*;

public class CollectionsDemo {
    public static int getCountDtringsForFirstSymbol(ArrayList<String> strings, char value) {
        int count = 0;
        if (strings.isEmpty()) throw new IllegalArgumentException("String is empty");

        for (String string : strings) {
            if (string.startsWith(String.valueOf(value))) {
                count += 1;
            }
        }
        return count;
    }

    public static ArrayList<Human> getNamesakes(ArrayList<Human> humans, Human man) {
        ArrayList<Human> namesakes = new ArrayList<>();
        if (humans.isEmpty()) throw new IllegalArgumentException("Human is empty");
        for (Human realman : humans) {

            if (Objects.equals(man.getSurname(), realman.getSurname())) {
                namesakes.add(realman);
            }
        }
        return namesakes;
    }

    public static List<Set<Integer>> getListOfSets(List<Set<Integer>> setarrylist, Set<Integer> oneset) {
        if (setarrylist.isEmpty()) throw new IllegalArgumentException("Sets is empty");
        if (oneset.isEmpty()) throw new IllegalArgumentException("Set is empty");
        List<Set<Integer>> setWithoutIntersections = new ArrayList<>();
        boolean flag = true;
        for (Set<Integer> set : setarrylist){
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

    public static ArrayList<?extends Human> getPeoplesByMaxAge(Set<Human> humans){
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

    public static ArrayList<Human> getSortedSet(Set<Human> humans){
        if (humans == null|| humans.isEmpty()) throw new IllegalArgumentException("humans is empty");

        TreeSet<Human> treeSet = new TreeSet<>(humans);

        return new ArrayList<>(treeSet);
    }

    public static HashSet<Human> getSetOfPeopleByIdentifier(Map<Integer,Human> humanMap, Set<Integer> nums){
        if (humanMap.isEmpty()) throw new IllegalArgumentException("human is Empty");

        HashSet<Human> result = new HashSet<>();

        for (Integer num : nums){
            if(humanMap.containsKey(num)) {
                result.add(humanMap.get(num));
            }
        }
        return result;
    }

    public static HashSet<Human> getSetOfPeopleOlder18(Map<Integer,Human> humanMap){
        if (humanMap.isEmpty()) throw new IllegalArgumentException("human is Empty");

        HashSet<Human> result = new HashSet<>();

        for (Map.Entry<Integer,Human> man : humanMap.entrySet()){
            if(man.getValue().getAge()>=18) result.add(humanMap.get(man.getKey()));
        }
        return result;
    }

    public static Map<Integer,Integer> changeKeysOnAge(Map<Integer,Human> humanMap){
        if (humanMap.isEmpty()) throw new IllegalArgumentException("human is Empty");
        Map<Integer,Integer> result = new HashMap<>();

        Set<Integer> buf = new HashSet<>();
        buf = humanMap.keySet();

        for(Integer key : buf){
            result.put(key,humanMap.get(key).getAge());
        }
        return result;

    }

    public static Map<Integer,ArrayList<Human>> getMapAge(Set<Human> humans){
        if (humans.isEmpty()) throw new IllegalArgumentException("human is Empty");

        HashMap<Integer,ArrayList<Human>> result = new HashMap<>();
        ArrayList<Integer> ageList = new ArrayList<>();

        for (Human human : humans){
            ageList.add(human.getAge());
        }

        for (Integer age : ageList){
            ArrayList<Human> oneAge = new ArrayList<>();
            for (Human human : humans){
                if(age == human.getAge()) oneAge.add(human);
            }
            result.put(age,oneAge);
        }
        return result;
    }

    public static Map<Integer,Map<Character,ArrayList<Human>>> getAgeHumanToLetter(Set<Human> humans){
        if (humans.isEmpty()) throw new IllegalArgumentException("human is Empty");

        HashMap<Integer,HashMap<Character,ArrayList<Human>>> result = new HashMap<>();
        ArrayList<Integer> ages = new ArrayList<>();

        for (Human human : humans){
            ages.add(human.getAge());
        }

        for (Integer age : ages){
            HashMap<Character,Human> secondmap = new HashMap<>();
            for (Human human : humans){
                //if (age == human.getAge() && )
            }
        }
        return null;

    }













}
