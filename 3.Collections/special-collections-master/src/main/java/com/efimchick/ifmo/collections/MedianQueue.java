package com.efimchick.ifmo.collections;

import java.util.*;

public class MedianQueue implements Queue <Integer> {
    Queue <Integer> medique = new LinkedList<>();

    void toMedian(){
        List <Integer> list;
        list = new ArrayList<>(medique);
        Collections.sort(list);
        medique.clear();
        while(!list.isEmpty()){
            int mid = (list.size() >> 1)-((list.size() & 1) ^ 1);
            medique.add(list.get(mid));
            List <Integer> newList = new ArrayList<>();
            if(mid >= 1)
                newList.addAll(list.subList(0,mid));
            if(mid + 2 <= list.size())
                newList.addAll(list.subList(mid + 1,list.size()));
            list.clear();;
            list.addAll(newList);
        }
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(Integer integer) {
        return medique.add(integer);
    }


    @Override
    public void clear() {
        medique.clear();
    }

    @Override
    public boolean offer(Integer integer) {
        medique.offer(integer);
        toMedian();
        return true;
    }

    @Override
    public Integer remove() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public Integer poll() {
        int x = medique.poll();
        toMedian();
        return x;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> collection) {
        return false;
    }

    @Override
    public Integer element() {
        return null;
    }

    @Override
    public Integer peek() {
        return medique.peek();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return medique.iterator();
    }

    @Override
    public int size() {
        return medique.size();
    }

    @Override
    public boolean isEmpty() {
        return medique.isEmpty();
    }

}

