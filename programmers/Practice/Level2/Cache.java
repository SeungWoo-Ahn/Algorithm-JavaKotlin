package programmers.Practice.Level2;

import java.util.*;

public class Cache {
    static class LRUCache {
        private final int capacity;
        private final Set<String> cache;
        private final LinkedList<String> order;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashSet<>();
            this.order = new LinkedList<>();
        }

        public boolean get(String key) {
            if (!cache.contains(key)) {
                return false;
            }
            order.remove(key);
            order.addFirst(key);
            return true;
        }

        public void put(String key) {
            if (cache.contains(key)) {
                order.remove(key);
            } else if (cache.size() == capacity) {
                String oldest = order.removeLast();
                cache.remove(oldest);
            }
            cache.add(key);
            order.addFirst(key);
        }
    }

    static int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        int time = 0;
        LRUCache cache = new LRUCache(cacheSize);
        for (String city: cities) {
            String key = city.toUpperCase();
            if (cache.get(key)) {
                time += 1;
            } else {
                cache.put(key);
                time += 5;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        int answer = solution(cacheSize, cities);
        System.out.print(answer);
    }
}
