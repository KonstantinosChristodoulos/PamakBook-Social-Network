package PamakBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class pamakbookState implements Serializable {
	
	 private ArrayList<User> users;
	 
	
	public pamakbookState (ArrayList<User> users) {
		this.users = users;	
		
	}
	
	public ArrayList<User> getUsers() {
        return users;
    }
	
	
	
	public void save (ArrayList<User> users) {
		try {
			File saved = new File ("saved.ser");
			FileOutputStream fileoutputStream = new FileOutputStream(saved);
			ObjectOutputStream outputStream = new ObjectOutputStream(fileoutputStream);
			pamakbookState state = new pamakbookState(users);
		
			outputStream.writeObject(state);
			outputStream.close();
			fileoutputStream.close();
			JOptionPane.showMessageDialog(null,"PamakBook saved successfully!");           		
		}catch(IOException ex) {
			JOptionPane.showMessageDialog(null,"Error saving PamakBook");
			ex.printStackTrace();
		
		}	
	
	}
	
	public static pamakbookState load() {
		
	    try {
	    	File saved = new File ("saved.ser");
	    	FileInputStream fInputStream = new FileInputStream(saved);
	    	ObjectInputStream inputStream = new ObjectInputStream(fInputStream);
	    
	    	pamakbookState state = (pamakbookState) inputStream.readObject();
	    	
	    	 inputStream.close();
	         fInputStream.close();

	         return state;


	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    }
	}
	    

}
