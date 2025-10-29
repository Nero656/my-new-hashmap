import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MyCustomHashMap<K, V> {
    private Entry<K, V>[] table;
    private int size;
    private final My_Hash_Interface<K> strategy;

    public MyCustomHashMap(My_Hash_Interface<K> strategy, int capacity) {
        this.table = new Entry[capacity];
        this.strategy = strategy;
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        int index = strategy.hash(key, table.length);
        Entry<K, V> entry = table[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;

        if (size > table.length * 0.75) {
            resize();
        }
    }

    public V get(K key) {
        int index = strategy.hash(key, table.length);
        Entry<K, V> entry = table[index];

        if (entry == null) {
            System.out.print("\u001B[31m ERROR:  KEY IS INVALID");
        }

        while (entry != null) {
            if (entry.key.equals(key)) {
                System.out.printf("|%s -> %s|\n", entry.key, entry.value);
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    public void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            while (entry != null) {
                put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    public V remove(K key) {
        int index = strategy.hash(key, table.length);
        Entry<K, V> current = table[index];
        Entry<K, V> previous = null;

        if (current == null) {
            System.out.print("\u001B[31m ERROR:  KEY IS INVALID");
        }

        while (current != null) {
            if (current.key.equals(key)) {
                V removeValue = current.value;

                if (previous == null) {
                    table[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                size--;
                return removeValue;
            }
            previous = current;
            current = current.next;
        }

        return null;
    }

    public Stream<Entry<K, V>> stream() {
        List<Entry<K, V>> allEntries = new ArrayList<>();
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                allEntries.add(entry);
                entry = entry.next;
            }
        }
        return allEntries.stream();
    }

    public void display() {
        stream().forEach(entry -> System.out.println("| Key :" + entry.key + " -> " + entry.value + " |"));
    }
}
