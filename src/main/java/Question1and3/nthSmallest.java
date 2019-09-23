package Question1and3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class nthSmallest {

	public static void main(String[] args) {
		Set<Integer> numbers_Set = new TreeSet();

		while (numbers_Set.size() != 500) {
			numbers_Set.add(generateRandomNumber());
		}

		System.out.println("500 Random Numbers are: " + numbers_Set);

		getNthSmallestNumberFromCollection(numbers_Set);
	}

	/*function to print nth smallest number from a collection.*/
	private static void getNthSmallestNumberFromCollection(Set<Integer> numbers_Set) {
		ArrayList<Integer> numbers_List = new ArrayList(numbers_Set);
		Scanner s = new Scanner(System.in);
		System.out.println("Enter the nth smallest number you want to find");
		int nth_Smallest = s.nextInt();
		System.out.println(nth_Smallest + " smallest number is: " + numbers_List.get(nth_Smallest - 1));
	}

	/*function to generate a random number*/
	public static int generateRandomNumber() {
		return (int) (Math.random() * 9000);
	}
}
