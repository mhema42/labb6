package labb6;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;

public class jframe {
	
	public static void jFrame(String text) {
		
		
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
	
	public static void main(String[] args) {
		jFrame(null);
	}
}
