package com.pack.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Product {
	int id;
	String name;
	float price;

	public Product(int id, String name, float price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	 public int getId() {  
	        return id;  
	    }  
	    public String getName() {  
	        return name;  
	    }  
	    public float getPrice() {  
	        return price;  
	    }  
}

interface Function {
	int operation(int a, int b);
}

public class Sample {
	public static void main(String[] args) {
		List<Integer> arr = new ArrayList<Integer>();
		arr.add(1);
		arr.add(2);
		arr.add(3);
		arr.add(4);

		// Using lambda expression to print all elements of arr
		arr.forEach(n -> System.out.println(n));

		// Add 2 numbers
		Function add = (a, b) -> a + b;
		System.out.println("Additon: " + add.operation(6, 7));

		// Filter function

		List<Product> list = new ArrayList<Product>();
		list.add(new Product(1, "Samsung ", 17000f));
		list.add(new Product(3, "Iphone ", 65000f));
		list.add(new Product(2, "Sony ", 25000f));
		list.add(new Product(4, "Nokia ", 15000f));
		list.add(new Product(5, "Redmi ", 26000f));
		list.add(new Product(6, "Lenevo ", 19000f));

		// Filter based on price
		Stream<Product> s1 = list.stream().filter(p -> p.price > 20000f);
		s1.forEach(p -> System.out.println(p.price));

		// Filter based on name
		Stream<Product> s2 = list.stream().filter(n -> n.name.startsWith("S"));
		s2.forEach(n -> System.out.println(n.name));

		// Collect intermediate operation by stream
		List<Product> result = list.stream().filter(s -> s.name.startsWith("S")).collect(Collectors.toList());
		result.forEach(n -> System.out.println("Collect Result: " + n.name));

		// Square of the number
		List<Integer> number = Arrays.asList(2, 3, 4, 5);

		// demonstration of map method
		List<Integer> square = number.stream().map(x -> x * x).collect(Collectors.toList());
		System.out.println(square);

		int even = number.stream().filter(x -> x % 2 == 0).reduce(0, (ans, i) -> ans + i);
		System.out.println(even);

		Float totalPrice = list.stream().map(product -> product.price).reduce(0.0f, (sum, price) -> sum + price); // accumulating price
		System.out.println(totalPrice);
		// More precise code
		float totalPrice2 = list.stream().map(product -> product.price).reduce(0.0f, Float::sum); // accumulating price, by referring
																									// method of Float class
		System.out.println(totalPrice2);

		double totalPrice3 = list.stream().collect(Collectors.summingDouble(product -> product.price));
		System.out.println(totalPrice3);
		
		//Max and Min
		Product productA = list.stream().max((product1, product2) -> product1.price > product2.price ? 1 : -1).get();
		System.out.println(productA.price);
		Product productB = list.stream().min((product1, product2) -> product1.price > product2.price ? 1 : -1).get();
		System.out.println(productB.price);
		
		//Count 
		long count = list.stream().filter(p -> p.price > 20000f).count();
		System.out.println("Count: "+count);
		
		//Method Reference add getter method to the properties
		 List<Float> productPriceList =  list.stream().filter(p -> p.price > 30000) // filtering data  
	                            .map(Product::getPrice)         // fetching price by referring getPrice method  
	                            .collect(Collectors.toList());  // collecting as list  
	        System.out.println(productPriceList);  
	}

}
