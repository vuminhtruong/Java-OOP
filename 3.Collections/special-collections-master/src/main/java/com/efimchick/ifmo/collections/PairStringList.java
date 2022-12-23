package com.efimchick.ifmo.collections;

import java.util.*;

class PairStringList implements List <String>{
    private List<String> a = new ArrayList<>();

    @Override
    public boolean add(String s){
        a.add(s);
        return a.add(s);
    }

    @Override
    public void add(int i,String s){
        if((i&1)==1)
            i += 1;
        a.add(i,s);
        a.add(i,s);
    }

    @Override
    public boolean addAll(Collection<? extends String> collection){
        collection.stream().forEach(x->add(x));
        return true;
    }

    @Override
    public boolean addAll(int i,Collection<? extends String> collection){
        if(i<=a.size()&&i!=0){
            if(i%2==1)
                i+=1;}
        for(String s : collection){
            add(i,s);
            i += 2;
        }
        return true;
    }

    @Override
    public boolean remove(Object o){
        a.remove(o);
        return a.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> collection){
        return a.remove(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection){
        return a.retainAll(collection);
    }

    @Override
    public String set(int i,String e){
        a.set(i,e);
        return a.set(i^1,e);
    }

    @Override
    public int size(){
        return a.size();
    }

    @Override
    public void clear(){
        a.clear();
    }

    @Override
    public String remove(int i){
        a.remove(i);
        return a.remove(i^1);
    }

    @Override
    public boolean contains(Object o){
        return true;
    }

    @Override
    public boolean containsAll(Collection <?> c){
        return true;
    }

    @Override
    public boolean equals(Object e){
        return true;
    }
    
    @Override
    public boolean isEmpty(){
        return a.isEmpty();
    }

    @Override
    public String get(int index){
        return a.get(index);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] t) {
        return t;
    }

    @Override
    public ListIterator<String> listIterator(){
        return null;
    }

    @Override
    public int hashCode(){
        return 0;
    }

    @Override
    public int indexOf(Object o){
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public Iterator<String> iterator(){
        return a.iterator();
    }

    @Override
    public ListIterator<String> listIterator(int index){
        return null;
    }

    @Override
    public List<String> subList(int i, int i1) {
        return null;
    }
}