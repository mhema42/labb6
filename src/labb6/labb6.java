package labb6;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mhema
 * User writes notes to text file, and can read all text from file or filter out text based on headlines 
 */
public class labb6 
{
	/**
	 * IOException error message 
	 */
	static String error1 = "Unable to read file, begin with choice (1) and add text to file...";
	static String error2 = "Unable to write to file, please enter correct and/or filename...";

//	 * Filters text on chosen headline and returns a list with filtered text
	
	public static Object textFilter(String path, String text) {
		
		text = (textReader(path, text));
		
//		System.out.println(text.substring(text.indexOf('-'), text.indexOf(':') + 1));
		
		if (text != error1) {
			String filter = "";
			Pattern p = Pattern.compile(Pattern.quote("Java -") + "(.*?)" + Pattern.quote(";"));
			Matcher m = p.matcher(text);
			while (m.find()) {
				filter = filter + m.group(0);
			}
			
			List<String> listText = Arrays.asList(filter.split(";", -1));
			listText.forEach(t -> System.out.println(t));
			
			return listText;
		} else {
			System.out.println(error1);
			return null;
		}	
	}
	
//	/**
//	 * returns all text from saved text file, if text file exists 
//	 * @param path	where text file is saved (.\text.txt)
//	 * @return
//	 */
	
	
	public static String textReader(String path, String text) {
		try {
			text = new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			text = error1;
		}
		
		return text;
	}
	
	/**
	 * after textInput, this method will save the text to file 
	 * @param path	where text file will be saved (.\text.txt)
	 * @param text	text from input
	 */
	public static void fileWriter(String path, String text) {	
		try {
	         FileWriter fw = new FileWriter(path, true);
	         fw.write(text + System.lineSeparator());
	         fw.close();
	         System.out.println("The content is successfully appended to the file.");
	    } 
		catch(IOException e) {
	         System.out.println(error2);
	    }
	}
	
	public static void textInput(String path, String text) {
		try (Scanner input = new Scanner(System.in)) {
						
			System.out.println("Ange Rubrik:");
			String rubrik = input.nextLine();
			
			System.out.println("Ange underrubrik:");
			String underrubrik = input.nextLine();
			
			System.out.println("Ange text");
			String newText = input.nextLine();
			
			input.close();
			
			text = "\n" + rubrik + " - " + underrubrik + ": " + newText + ";";
			fileWriter(path, text);				
		}
	}
	
	public static void menu() {
		try (Scanner input = new Scanner(System.in)) {
			
			System.out.println("Välj alternativ:");
			System.out.println("1 - Lägg till text   2 - Läs all text   3 - Filtrera på rubrik");
			
			String choice = input.nextLine();
			String path = ".\\text.txt";
			String text = null;
			
			switch (choice) {
				case "1": 	
					textInput(path, text);
				break;
				case "2":
					System.out.println(textReader(path, text)); 
		        break;
				case "3":
					textFilter(path, text);
				break;
			}
		}	
	}
	
	public static void main(String[] args) {
		menu();
	}	
}