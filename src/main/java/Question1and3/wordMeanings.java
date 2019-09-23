package Question1and3;

import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class wordMeanings {

	public static void main(String[] args) throws Exception {

		String file_Path = "src\\main\\java\\Question1and3\\word-meaning.json";

		if (doesFileExist(file_Path)) {

			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(new FileReader(file_Path));

			Set<Map.Entry<String, String>> entries = obj.entrySet();
			for (Map.Entry<String, String> entry : entries) {
				System.out.println("Word: " + entry.getKey());
				String[] meanings = entry.getValue().split(", ");
				for (int i = 0; i < meanings.length; i++) {
					System.out.println("Meaning " + (i + 1) + " : " + meanings[i]);
				}
			}
		}
	}

	/* Function that returns weather a file exists or not. */
	private static boolean doesFileExist(String file_Path) {

		boolean exists = false;
		try {
			new FileReader(file_Path);
			exists = true;
			System.out.println("File exists at the specified path: " + file_Path);
		} catch (Exception e) {
			exists = false;
			System.out.println("File does not exist at the specified path: " + file_Path);
			System.out.println(e);
		}
		return exists;
	}

}
