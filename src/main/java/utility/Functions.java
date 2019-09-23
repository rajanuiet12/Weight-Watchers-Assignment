package utility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Functions {

	public String toUpperCase(String str) {
		return str.toUpperCase();
	}

	public String toLowerCase(String str) {
		return str.toLowerCase();
	}

	public String countFrequenciesFromList(List<String> list) {

		Map<String, Integer> hm = new HashMap<String, Integer>();

		for (String i : list) {
			Integer j = hm.get(i);
			hm.put(i, (j == null) ? 1 : j + 1);
		}
		// displaying the occurrence of elements in the arraylist
//		for (Map.Entry<String, Integer> val : hm.entrySet()) {
//			System.out.println("Agent " + val.getKey() + " " + "has " + ": " + val.getValue() + " meeting(s)");
//		}
		
		return hm.toString().replaceAll("[{}]", " ");
	}
}
