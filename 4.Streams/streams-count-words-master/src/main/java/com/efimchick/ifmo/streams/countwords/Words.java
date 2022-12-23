package com.efimchick.ifmo.streams.countwords;


import java.util.*;
import java.util.stream.Collectors;

public class Words {

    private static String apply(String str) {
        str = str.replaceAll("[^\u0430-\u044f\u0410-\u042fa-zA-Z]", "+");
        str = str.replaceAll("[+\\d]+", " ");
        return str;
    }

    public String countWords(List<String> lines) {
        String s = "";
        s = lines.stream().map(Words::apply).collect(Collectors.joining(" "));
        List <String> list;
        list = Arrays.asList(s.split("\\s+"));
        List <String> l;
        l = list.stream().filter(cur -> !cur.isEmpty())
                .map(cur -> cur.toLowerCase())
                .collect(Collectors.toList());
        TreeMap <String,Integer> text = new TreeMap<>();
        l.stream().map(cur -> {
            text.put(cur,(text.getOrDefault(cur, 0)) + 1);
            return new words(cur,text.get(cur));
        }).collect(Collectors.toList());

        Set <String> set = text.keySet();
        List <words> a= set.stream().filter(cur -> (cur.length() >= 4 && text.get(cur) >= 10))
                .map(key -> new words(key,text.get(key)))
                .collect(Collectors.toList());
        Collections.sort(a, (words, t1) -> {
            int z = -Integer.compare(words.getValue(),t1.getValue());
            return z == 0 ? words.getS().compareTo(t1.getS()) : z;
        });
        String countword= a.stream()
                .map(cur -> cur.getS() + " - " + cur.getValue())
                .collect(Collectors.joining("\n"));
        return countword;
    }

    private class words{
        private String s;
        private int value;

        public words(String s,int value){
            this.s=s;
            this.value=value;
        }
        public String getS(){
            return s;
        }
        public int getValue(){
            return value;
        }
    }
}
