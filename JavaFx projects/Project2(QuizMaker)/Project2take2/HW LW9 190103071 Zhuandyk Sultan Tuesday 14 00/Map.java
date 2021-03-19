package com.company;

import java.util.ArrayList;
import java.util.List;

public class Map<K, V> {
    private ArrayList<K> keys;
    private ArrayList<V> values;

    public Map() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    public void set(K key, V value) {
        if (containsKey(key)) {
            int index = keys.indexOf(key);
            values.set(index, value);
        } else {
            this.keys.add(key);
            this.values.add(value);
        }
    }

    public V get(K key) {
        if (containsKey(key)) {
            int index = keys.indexOf(key);
            return values.get(index);
        }
        return null;
    }


    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    public int size() {
        return keys.size();
    }

    public void clear() {
        this.keys.clear();
        this.values.clear();
    }

    public boolean isEmpty() {
        return keys.isEmpty();
    }

    public ArrayList<K> getKeys() {
        return keys;
    }

    public ArrayList<V> getValues() {
        return values;
    }

    @Override
    public String toString() {
        String string = "[";
        for (int i = 0; i < keys.size(); i++) {
            if (i == keys.size() - 1)
                string += keys.get(i) + ": " + values.get(i) + " ";
            else
                string += keys.get(i) + ": " + values.get(i) + ", ";
        }
        string += "]";
        return string;

    }
}
