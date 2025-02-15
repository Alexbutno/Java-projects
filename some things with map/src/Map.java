import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
    public class Map<T,T1> extends ArrayList<T> {
        public static void main(String[] args) {
            Map<String, Integer> map = new Map<>();
            map.put("42",3);
            map.put("23",8);
            map.put("42",324);
            System.out.println(map);
            System.out.println("---------------------");
            Map<String, Integer> map2 = new Map<>();
            map2.put("42",4432);
            map2.put("234",324);
            map2.put("43444",234);
            System.out.println(map2);
            System.out.println("---------------------");
            System.out.println(map.equals(map2));
            System.out.println("---------------------");
            map.putAll(map2);
            System.out.println(map);
            System.out.println("---------------------");
        }
        private final List<T> mapKeys = new ArrayList<>();
        private final List<T1> mapValues = new ArrayList<>();
        public int size(){
            return mapKeys.size();
        }
        public boolean isEmpty(){
            return mapKeys.isEmpty();
        }
        public void clear(){
            mapValues.clear();
            mapKeys.clear();
        }
        public boolean equals(Map<T,T1> obj){
            return ((mapKeys.equals(obj.mapKeys))&&(mapValues.equals(obj.mapValues)));
        }

        public void put(T key,T1 value){
            if (mapKeys.isEmpty()){
                mapKeys.add(key);
                mapValues.add(value);
            }
            if (mapKeys.contains(key)){
                mapValues.set(mapKeys.indexOf(key),value);
            }else{
                mapKeys.add(key);
                mapValues.add(value);
            }
        }
        public void put(Pair<T,T1> pair){
            this.put(pair.getFirst(), pair.getSecond());
        }
        public T getKey(Pair<T,T1> pair){
            return pair.getFirst();
        }
        public T1 geValue(Pair<T,T1> pair){
            return pair.getSecond();
        }
        public T1 qet(T key) {
            if (mapKeys.contains(key)) {
                return mapValues.get(mapKeys.indexOf(key));
            } else return null;
        }
        public void putAll(Map<T,T1> map2){
            MapIterator<T,T1> iterator =map2.getIterator();
            while (iterator.hasNext()){
                this.put(iterator.current());
                iterator.next();
            }
        }
        public DefaultListModel<Pair<T,T1>> getListModel() {
            DefaultListModel<Pair<T,T1>> model = new DefaultListModel<>();
            for (T keys : mapKeys ) {
                model.addElement(new Pair<>(keys,mapValues.get(mapKeys.indexOf(keys))));
            }
            return model;
        }

        public MapIterator<T,T1> getIterator() {
            return new MapIterator<>();
        }
        public String toString(){
            MapIterator<T,T1> iterator =this.getIterator();
            StringBuilder string = new StringBuilder("map:"+"\n");
            while (iterator.hasNext()){
                string.append(iterator.current()).append("\n");
                iterator.next();
            }
            return string.toString();
        }
        public class MapIterator<T3,T4> {
            private int index;

            public boolean hasNext() {
                return index <= mapKeys.size() - 1;
            }

            public void next() throws ArrayIndexOutOfBoundsException {
                if (hasNext()) index++;
                else throw new ArrayIndexOutOfBoundsException();
            }

            public Pair<T,T1> current() {
                return new Pair<T, T1>(mapKeys.get(index),mapValues.get(index));
            }
        }
    }
