public class MyHashMapStrategy<k> implements My_Hash_Interface<k>{
    @Override
    public int hash(k key, int capacity) {
        return Math.abs(key.hashCode()) % capacity;
    }
}
