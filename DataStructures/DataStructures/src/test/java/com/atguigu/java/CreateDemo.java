package com.atguigu.java;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreateDemo {
    
    @Test
    public void testBasicDataStructures() {
        System.out.println("=== Testing Basic Data Structures ===");
        
        // Test ArrayList
        java.util.ArrayList<String> list = new java.util.ArrayList<>();
        list.add("Hello");
        list.add("World");
        System.out.println("ArrayList created with elements: " + list);
        System.out.println("ArrayList size: " + list.size());
        assertEquals(2, list.size());
        assertEquals("Hello", list.get(0));
        
        // Test HashMap
        java.util.HashMap<String, Integer> map = new java.util.HashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        System.out.println("HashMap created: " + map);
        assertEquals(Integer.valueOf(1), map.get("one"));
        assertEquals(Integer.valueOf(2), map.get("two"));
        
        // Test Stack
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        stack.push(10);
        stack.push(20);
        System.out.println("Stack after pushing 10 and 20: " + stack);
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Popped element: " + stack.pop());
        System.out.println("Stack is empty: " + stack.isEmpty());
        assertTrue(stack.isEmpty());
    }
    
    @Test
    public void testStringOperations() {
        System.out.println("=== Testing String Operations ===");
        String str = "Data Structures";
        System.out.println("Original string: " + str);
        System.out.println("String length: " + str.length());
        System.out.println("Contains 'Structures': " + str.contains("Structures"));
        System.out.println("Uppercase: " + str.toUpperCase());
        assertEquals(15, str.length());
        assertTrue(str.contains("Structures"));
        assertEquals("DATA STRUCTURES", str.toUpperCase());
    }
    
    @Test
    public void testArrayOperations() {
        System.out.println("=== Testing Array Operations ===");
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("Array created: " + java.util.Arrays.toString(numbers));
        System.out.println("Array length: " + numbers.length);
        System.out.println("First element: " + numbers[0]);
        System.out.println("Last element: " + numbers[4]);
        assertEquals(5, numbers.length);
        assertEquals(1, numbers[0]);
        assertEquals(5, numbers[4]);
        
        // Sum calculation
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        System.out.println("Sum of all elements: " + sum);
        assertEquals(15, sum);
    }
    
    @Test
    public void testIntArrayForeach() {
        System.out.println("=== Testing Int Array Foreach ===");
        int[] numbers = {10, 20, 30, 40, 50};
        
        System.out.println("Array elements using foreach loop:");
        for (int num : numbers) {
            System.out.println("Element: " + num);
        }
        
        System.out.println("Array elements with index:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Index " + i + ": " + numbers[i]);
        }
        
        // Calculate and print even numbers
        System.out.println("Even numbers in array:");
        for (int num : numbers) {
            if (num % 2 == 0) {
                System.out.println("Even: " + num);
            }
        }
        
        assertEquals(5, numbers.length);
        assertEquals(10, numbers[0]);
        assertEquals(50, numbers[4]);
    }
}