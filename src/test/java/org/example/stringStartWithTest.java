package org.example;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.example.CollectionsDemo.getCountDtringsForFirstSymbol;

public class stringStartWithTest {
    ArrayList<String> strings;


    @Before
    public void setUp() throws Exception {
        strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbc");
        //strings.add("");
        strings.add("aba");
        strings.add("bab");
    }

    @Test
    public void getCountDtringsForFirstSymboltest1(){
        Assert.assertEquals(2,getCountDtringsForFirstSymbol(strings,'a'));
    }
}
