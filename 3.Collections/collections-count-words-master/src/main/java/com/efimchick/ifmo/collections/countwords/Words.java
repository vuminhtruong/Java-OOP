package com.efimchick.ifmo.collections.countwords;

import java.util.*;

public class Words {
    public String countWords(List<String> lines) {
        TreeMap <String,Integer> text = word(lines);
        ArrayList<words> list= new ArrayList<words>();
        for(Map.Entry<String,Integer> entry : text.entrySet() ){
            if(entry.getKey().length()<4||entry.getValue()<10)
                continue;
            list.add(new words(entry.getKey(),entry.getValue()));
        }
        Collections.sort(list, new Comparator<words>() {
            @Override
            public int compare(words words, words t1) {
                int z=-Integer.compare(words.getValue(),t1.getValue());
                if(z==0)
                    z=words.getS().compareTo(t1.getS());
                return z;
            }
        });
        String countWords = "";
        for(int i = 0;i < list.size();++i){
            String t = list.get(i).getS() + " - " + list.get(i).getValue();
            countWords += t;
            if(i < list.size()-1)
                countWords += "\n";
        }
        return countWords;
    }
    private TreeMap<String,Integer> word(List<String> lines){
        TreeMap <String,Integer> wordMap = new TreeMap<String, Integer>();
        for(int i = 0;i < lines.size();i++){
            String m = lines.get(i);
            StringTokenizer tokenizer = new StringTokenizer(m," ");
            ArrayList <String> a = new ArrayList <String> ();
            while(tokenizer.hasMoreTokens()){
                String s = tokenizer.nextToken();
                ArrayList <String> lis = new ArrayList<>();
                lis = addword(s);
                for(int j = 0;j < lis.size();j++){
                    a.add(lis.get(j));
                }
            }
            for(int j = 0;j < a.size();j++){
                int counts = 0;
                if(!wordMap.isEmpty()){
                    if(wordMap.containsKey(a.get(j))){
                        counts = wordMap.get(a.get(j));
                        wordMap.remove(a.get(j));
                    }
                }
                wordMap.put(a.get(j),++counts);
            }
        }
        return wordMap;
    }
    private ArrayList <String> addword(String s){
        ArrayList <String> list = new ArrayList<>();
        for(int i = 0;i < s.length();i++){
            if(Character.isLetter(s.charAt(i))){
                int j = i;
                String x = "";
                while(j < s.length() && Character.isLetter(s.charAt(j))){
                    x += s.charAt(j);
                    j++;
                }
                if(!x.isEmpty()){
                    x = x.toLowerCase();
                    list.add(x);
                }
                i = j;
            }
        }
        return list;
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




