package com.epam.speed_comparison;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Speed comparison:
* 1.	Lists (ArrayList vs LinkedList);
* 2.	Sets (HashSet vs TreeSet);
* 3.	Maps (HashMap vs TreeMap).
 */
public class Runner {
	static int collectionSize = 15000;
	static Integer key = 20000;
	static Integer element = 100000;
	static int firstElementIndex = 0;
	static int middleElementIndex = 7500;
	static long start, elapsedTime;
	static List<Integer> arrayList, linkedList;
	static Set<Integer> hashSet, treeSet;
	static Map<Integer, Integer> hashMap, treeMap;
	
	public static void main(String[] args) {
		
		initializeCollections(); 
		checkListPerformance();
		checkSetPerformance();
		checkMapPerformance();
	}
	
	private static void initializeCollections() {
		arrayList = new ArrayList<Integer>(collectionSize);
		hashMap = new HashMap<Integer, Integer>(collectionSize);
		for (int i = 0; i < collectionSize; i++) {
			arrayList.add(Integer.valueOf(i));
			hashMap.put(Integer.valueOf(i), new Random().nextInt());
		}
		linkedList = new LinkedList<Integer>(arrayList);
		hashSet = new HashSet<Integer>(arrayList);
		treeSet = new TreeSet<Integer>(arrayList);
		treeMap = new TreeMap<Integer, Integer>(hashMap);
	}
	
	private static void outputResult(String s, Class c, long elapsedTime) {
		System.out.println(String.format("%s, %s, time spent: %s ns.", s, c, elapsedTime));
	}
	
	private static void checkMapPerformance() {
		measureElementAdditionToMap(hashMap, element);
		measureElementAdditionToMap(treeMap, element);
		System.out.println();
		
		measureElementSearchInMap(hashMap, element);
		measureElementSearchInMap(treeMap, element);
		System.out.println();
				
		measureElementRemovalFromMap(hashMap, key, element);
		measureElementRemovalFromMap(treeMap, key, element);
		System.out.println();
	}

	private static void measureElementRemovalFromMap(Map<Integer, Integer> map, Integer key, Integer element) {
		start = System.nanoTime();
		map.remove(key, element);
		elapsedTime = System.nanoTime() - start;
		outputResult("Element removal", map.getClass(), elapsedTime);
	}

	private static void measureElementSearchInMap(Map<Integer, Integer> map, Integer element) {
		start = System.nanoTime();
		map.containsValue(element);
		elapsedTime = System.nanoTime() - start;
		outputResult("Element search", map.getClass(), elapsedTime);
	}

	private static void measureElementAdditionToMap(Map<Integer, Integer> map, Integer element) {
		start = System.nanoTime();
		map.put(key, element);
		elapsedTime = System.nanoTime() - start;
		outputResult("Element addition", map.getClass(), elapsedTime);
	}

	private static void checkSetPerformance() {
		measureElementAdditionToSetCollection(hashSet, element);
		measureElementAdditionToSetCollection(treeSet, element);
		System.out.println();
		
		measureElementSearchInCollection(hashSet, element);
		measureElementSearchInCollection(treeSet, element);
		System.out.println();
		
		measureElementRemovalFromCollection(hashSet, element);
		measureElementRemovalFromCollection(treeSet, element);
		System.out.println();
	}

	private static void measureElementAdditionToSetCollection(Set<Integer> set, Integer element) {
		start = System.nanoTime();
		set.add(element);
		elapsedTime = System.nanoTime() - start;
		outputResult("Element addition", set.getClass(), elapsedTime);
	}

	private static void checkListPerformance() {
		// working with the beginning of a list collection
		measureElementAdditionToListCollection(arrayList, element, firstElementIndex);
		measureElementAdditionToListCollection(linkedList, element, firstElementIndex);
		System.out.println();
				
		measureElementSearchInCollection(arrayList, element);
		measureElementSearchInCollection(linkedList, element);
		System.out.println();
		
		measureElementRemovalFromCollection(arrayList, element);
		measureElementRemovalFromCollection(linkedList, element);
		System.out.println();
				
		// working with the middle of a list collection
		measureElementAdditionToListCollection(arrayList, element, middleElementIndex);
		measureElementAdditionToListCollection(linkedList, element, middleElementIndex);
		System.out.println();
		
		measureElementSearchInCollection(arrayList, element);
		measureElementSearchInCollection(linkedList, element);
		System.out.println();
		
		measureElementRemovalFromCollection(arrayList, element);
		measureElementRemovalFromCollection(linkedList, element);
		System.out.println();
				
		// working with the end of a list collection
		measureElementAdditionToListCollection(arrayList, element, arrayList.size());
		measureElementAdditionToListCollection(linkedList, element, linkedList.size());
		System.out.println();
		
		measureElementSearchInCollection(arrayList, element);
		measureElementSearchInCollection(linkedList, element);
		System.out.println();
		
		measureElementRemovalFromCollection(arrayList, element);
		measureElementRemovalFromCollection(linkedList, element);
		System.out.println();
	}

	private static void measureElementAdditionToListCollection(List<Integer> list, Integer element,
			int elementIndex) {
		start = System.nanoTime();
		list.add(elementIndex, element);
		elapsedTime = System.nanoTime() - start;
		outputResult("Element addition to index " + elementIndex, list.getClass(), elapsedTime);
	}
	
	private static void measureElementRemovalFromCollection(Collection<Integer> collection, Integer element) {
		start = System.nanoTime();
		collection.remove(element);
		elapsedTime = System.nanoTime() - start;
		outputResult("Element removal", collection.getClass(), elapsedTime);
	}

	private static void measureElementSearchInCollection(Collection<Integer> collection, Integer element) {
		start = System.nanoTime();
		collection.contains(element);
		elapsedTime = System.nanoTime() - start;
		outputResult("Element search", collection.getClass(), elapsedTime);
	}
}
