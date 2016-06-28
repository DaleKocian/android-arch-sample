package com.remind101.archexample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import com.remind101.archexample.models.Counter;

import java.util.ArrayList;
import java.util.List;

public class CounterDatabase {
    private static CounterDatabase instance;

    private final SparseArray<Counter> counters;

    private int nextId = 1;

    private CounterDatabase() {
        counters = new SparseArray<>();
    }

    public static synchronized CounterDatabase getInstance() {
        if (instance == null) {
            instance = new CounterDatabase();
        }
        return instance;
    }

    public List<Counter> getAllCounters() {
        synchronized (counters) {
            List<Counter> countersList = new ArrayList<>();
            for (int i = 0; i < counters.size(); ++i) {
                countersList.add(counters.get(i));
            }
            return countersList;
        }
    }

    @Nullable
    public Counter getCounter(int id) {
        synchronized (counters) {
            return counters.get(id);
        }
    }

    public void saveCounter(@NonNull Counter counter) {
        synchronized (counters) {
            int id = nextId++;
            counter.setId(id);
            counters.put(id, counter);
        }
    }
}
