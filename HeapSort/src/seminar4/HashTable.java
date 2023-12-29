package seminar4;

public class HashTable<K, V> {
    private static final int INIT_BASKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.75;

    private int size = 0;

    private void recalculate(){
        Bucket[] old = buckets;
        buckets = (Bucket[]) new Object[old.length*2];
        for( int i = 0; i<old.length; i++){
            Bucket bucket = old[i];
            Bucket.Node node = bucket.head;
            while (node != null){
                put(node.value.key, node.value.value);
                node = node.next;
            }
            old[i] = null;
        }
    }

    public HashTable(int initSize) {
        this.buckets = (Bucket[]) new Object[initSize];
    }

    public HashTable() {
        this(INIT_BASKET_COUNT);
    }

    private int calculateBucketIndex(K key) {
        return key.hashCode() % buckets.length;
    }

    private Bucket[] buckets;

    private class Entity {
        private K key;
        private V value;

        public Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private class Bucket {
        private Node head;

        private class Node {
            private Node next;
            private Entity value;
        }

        public V get(K key) {
            Node node = head;
            while (node != null) {
                if (node.value.key.equals(key)) {
                    return node.value.value;
                }
                node = node.next;
            }
            return null;
        }

        public boolean add(Entity entity) {

            Node node = new Node();
            node.value = entity;

            if (head != null) {
                if (head.value.key.equals(entity.key))
                    return false;
                Node current = head;
                while (current.next != null) {
                    if (current.value.key.equals(entity.key))
                        return false;
                    current = current.next;
                }
                current.next = node;
                return true;
            } else {
                head = node;
                return true;
            }
        }

        public boolean remove(K key) {
            if (head != null) {
                if (head.value.key.equals(key))
                    head = head.next;
                else {
                    Node node = head;
                    while (node != null) {
                        if (node.value.key.equals(key)) {
                            node.next = node.next.next;
                        }
                        node = node.next;
                    }
                }
            }
            return false;
        }
    }

    public V get(K key) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket != null)
            return bucket.get(key);
        return null;
    }

    public boolean put(K key, V value) {
        if(buckets.length * LOAD_FACTOR < size) {
            recalculate();
        };

        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        if (bucket == null) {
            bucket = new Bucket();
            buckets[index] = bucket;
        }
        boolean addOk = bucket.add(new Entity(key, value));
        if (addOk)
            size++;
        return addOk;
    }

    public boolean remove(K key) {
        int index = calculateBucketIndex(key);
        Bucket bucket = buckets[index];
        boolean removeOk =  bucket.remove(key);
        if (removeOk)
            size --;
        return removeOk;
    }
}
