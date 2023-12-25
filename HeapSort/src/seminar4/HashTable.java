package seminar4;

public class HashTable<K, V> {
    private static final int INIT_BASKET_COUNT = 16;

    public HashTable(int initSize) {
        this.baskets = (Basket[]) new Object[initSize];
    }

    public HashTable() {
        this(INIT_BASKET_COUNT);
    }

    private int calculateBasketIndex(K key) {
        return key.hashCode() % baskets.length;
    }

    private Basket[] baskets;

    private class Entity {
        private K key;
        private V value;

        public Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private class Basket {
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
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index];
        if (basket != null)
            return basket.get(key);
        return null;
    }

    public boolean put(K key, V value){
        int index = calculateBasketIndex(key);
        Basket basket = baskets[index];
        if (basket == null) {
            basket = new Basket();
            baskets[index] = basket;
        }
        return basket.add(new Entity(key, value));

    }
}
