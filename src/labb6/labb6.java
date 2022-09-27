package labb6;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

/**
 * @author mhema
 * User writes notes to text file, and can read all text from file or filter out text based on headlines 
 */
public class labb6 
{
	/**
	 * IOException error message 
	 */
	private static String error1 = "Unable to read file, begin with choice (1) and add text to file...";
	private static String error2 = "Unable to write to file, please enter correct and/or filename...";

	private static void jFrame(String text) {
		JFrame frame= new JFrame();  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        Container cp = frame.getContentPane();  
        JTextPane pane = new JTextPane();  
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();  
        
        Document doc = pane.getStyledDocument();  
        try {
			doc.insertString(doc.getLength(), text, attributeSet);
		} catch (BadLocationException e) {
			e.printStackTrace();
		} 
        
        JScrollPane scrollPane = new JScrollPane(pane);  
        cp.add(scrollPane, BorderLayout.CENTER);  
        
        frame.setLocationRelativeTo(null);
  
        frame.setSize(600, 400);  
        frame.setVisible(true);
	}
	
	//	 * Filters text on chosen headline and returns a list with filtered text
	
	private static void getFilteredText(String path) {
		
		String text = (fileReader(path));
		
//		System.out.println(text.substring(text.indexOf('-'), text.indexOf(':') + 1));
		
		if (text != error1) {
			String filter = "";
			Pattern p = Pattern.compile(Pattern.quote("Java -") + "(.*?)" + Pattern.quote(";"));
			Matcher m = p.matcher(text);
			while (m.find()) {
				filter = filter + m.group(0);
			}
			
			List<String> listText = Arrays.asList(filter.split(";", -1));
			String fText = String.join("\n", listText);
			
//			JOptionPane.showMessageDialog(null, fText, "Filtrerad text", JOptionPane.NO_OPTION);
			
			jFrame(fText);	     
		}
			
//			return listText;
//		} else {
//			System.out.println(error1);
//			return null;
//		}	
	}
	

	private static void getText(String path) {
		
		String text = fileReader(path);
		
		jFrame(text);
		
//		UIManager.put("OptionPane.maximumSize",new Dimension(300,300)); 
//		JOptionPane.showMessageDialog(null, text, "All text i filen", JOptionPane.NO_OPTION);
		
	}
	
	
	
//	/**
//	 * returns all text from saved text file, if text file exists 
//	 * @param path	where text file is saved (.\text.txt)
//	 * @return
//	 */
	
	
	private static String fileReader(String path) {
		
		String text;
		
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
	private static void fileWriter(String path, String text) {	
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
	
	private static void textInput(String path) {
		try (Scanner input = new Scanner(System.in)) {
						
			System.out.println("Ange Rubrik:");
			String rubrik = input.nextLine();
			
			System.out.println("Ange underrubrik:");
			String underrubrik = input.nextLine();
			
			System.out.println("Ange text");
			String newText = input.nextLine();
			
			input.close();
			
			String text = "\n" + rubrik + " - " + underrubrik + ": " + newText + ";";
			fileWriter(path, text);				
		}
	}	
	
	private static void menu() {
		try (Scanner input = new Scanner(System.in)) {
			
			String choice = JOptionPane.showInputDialog("1 - Lägg till text" + "\n" + "2 - Läs all text" + "\n" + "3 - Filtrera på rubrik");
			
			String path = ".\\text.txt";
			
			switch (choice) {
				case "1": 	
					textInput(path);
				break;
				case "2":
					getText(path); 
		        break;
				case "3":
					getFilteredText(path);
				break;
			}
		}	
	}
	
	public static void main(String[] args) {
		menu();
	}	
}