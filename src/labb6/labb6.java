package labb6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class labb6 
{
	
	public static void fileWriter(String path, String text) {	
		try
	      {
	         FileWriter fw = new FileWriter(path, true);
	         fw.write(text + System.lineSeparator());
	         fw.close();
	         System.out.println("The content is successfully appended to the file.");
	      }
	      catch(IOException ioe)
	      {
	         System.out.println("\nSomething went wrong!");
	      }
	}
	
	public static String textInput() {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Ange Rubrik:");
			String rubrik = input.nextLine();
			
			System.out.println("Ange text");
			String text = input.nextLine();
			
			return "\n" + rubrik + "\n" + text;		
		}
	}
	
	public static void menu() throws IOException {
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Välj alternativ:");
			System.out.println("1 - Lägg till text   2 - Läs text");
			
			String choice = input.nextLine();
			String text = null;
			String path = "c:\\labb6\\text.txt";
			
			switch (choice) {
				case "1": 	
					text = textInput();
					fileWriter(path, text);
				break;
				case "2":
					String st;
					BufferedReader br = new BufferedReader(new FileReader(path));
					while ((st = br.readLine()) != null) {
						System.out.println(st);
					}
		        break;	
			}
			input.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		menu();
	}
}



//public static String menu() {
//try (Scanner input = new Scanner(System.in)) {
//	System.out.println("Skriv ditt namn:");
//	String name = input.nextLine();
//	
//	System.out.println("");
//	System.out.println("Välj alternativ:");
//	System.out.println("1 - Svenska   2 - Engelska    3 - Ryska");
//	
//	String choice = input.nextLine();
//	String text = null;
//	
//	switch (choice) {
//		case "1": text = "Hejsan " + name;
//		break;
//		case "2": text = "Hello " + name;
//		break;
//		case "3": text = "Priviet " + name;
//		break;	
//	}
//	return text;		
//}
//}


//String text = textInput();
//String path = "c:\\labb6\\text.txt";
//
//fileWriter(path, text);
