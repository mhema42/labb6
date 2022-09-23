package labb6;

import java.util.Scanner;

public class labb6 
{
	
	public static String menu() 
	{
		
		try (Scanner input = new Scanner(System.in))
		{
			System.out.println("Skriv ditt namn:");
			String name = input.nextLine();
			
			System.out.println("Välj alternativ:");
			System.out.print("1 - Hejsan   2 - Hello    3 - Priviet");
			
			String choice = input.nextLine();
			String text = null;
			
			switch (choice) 
			{
				case "1": text = "Hejsan " + name;
				break;
				case "2": text = "Hello " + name;
				break;
				case "3": text = "Priviet " + name;
				break;	
			}
			return text;
		}
	}
	
	public static void main(String[] args) 
	{
		String choice = menu();
		System.out.println(choice);
	}
}