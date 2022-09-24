package labb6;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class labb6 
{
	public static String fileReader(String path) {		
		String text = null;
		try {
			text = new String(Files.readAllBytes(Paths.get(path)));
		} 
		catch (IOException e) {
			System.out.println("\nUnable to read file, please enter correct and/or filename...");
		}
		return text;
	}
	
	public static void fileWriter(String path, String text) {	
		try {
	         FileWriter fw = new FileWriter(path, true);
	         fw.write(text + System.lineSeparator());
	         fw.close();
	         System.out.println("The content is successfully appended to the file.");
	    } 
		catch(IOException e) {
	         System.out.println("\nUnable to write to file, please enter correct and/or filename...");
	    }
	}
	
	public static String textInput() {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Ange Rubrik:");
			String rubrik = input.nextLine();
			
			System.out.println("Ange underrubrik:");
			String underrubrik = input.nextLine();
			
			System.out.println("Ange text");
			String text = input.nextLine();
			
			input.close();
			
			return "\n" + rubrik + " - " + underrubrik + "\n" + text;		
		}
	}
	
	public static void menu() {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Välj alternativ:");
			System.out.println("1 - Lägg till text   2 - Läs text   3 - Filtrera rubrik");
			
			String choice = input.nextLine();
			String text = null;
			String path = "c:\\labb6\\text.txt";
			
			switch (choice) {
				case "1": 	
					text = textInput();
					fileWriter(path, text);
				break;
				case "2":
					System.out.println(fileReader(path));
		        break;
				case "3": 	
					String s = (fileReader(path));
				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		menu();
	}	
}