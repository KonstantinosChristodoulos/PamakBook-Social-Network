package PamakBook;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<User> users = new ArrayList<>();		
		
		 // Δημιουργία groups
		ArrayList<Group> groups = new ArrayList<>();
		
		Group g1 = new Group("WebGurus", "A group for web passionates");
        ClosedGroup g2 = new ClosedGroup("ExamSolutions", "Solutions to common exam questions");
        groups.add(g1);
        groups.add(g2);
		
		pamakbookState saved = pamakbookState.load();
		if (saved != null) {
		    users = saved.getUsers();
		    
		} 
			
		
        
        new LoginFrame(users, groups);
		
		/*User u1 = new User("Makis", "iis2598@uom.edu.gr");
        User u2 = new User("Petros", "ics2524@uom.edu.gr");
        User u3 = new User("Maria", "iis2512@uom.edu.gr");
        User u4 = new User("Gianna", "iis25133@uom.edu.gr");
        User u5 = new User("Nikos", "dai1758@uom.edu.gr");
        User u6 = new User("Babis", "ics25104@uom.edu.gr");
        User u7 = new User("Stella", "dai1827@uom.edu.gr");
        User u8 = new User("Eleni", "ics2586@gmail.com");
        
        ArrayList<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        users.add(u4);
        users.add(u5);
        users.add(u6);
        users.add(u7);
        users.add(u8);*/


        /*// Φιλικές σχέσεις (στην σειρά ώστε να τυπώνονται τα μηνύματα όπως στο παράδειγμα)
        u1.addFriend(u2); // Makis - Petros
        u1.addFriend(u5); // Makis - Nikos
        u5.addFriend(u6); // Nikos - Babis
        u3.addFriend(u4); // Maria - Gianna
        u3.addFriend(u2); // Maria - Petros
        u4.addFriend(u6); // Gianna - Babis
        u5.addFriend(u3); // Nikos - Maria
        u1.addFriend(u6); // Makis - Babis
        u5.addFriend(u2); // Nikos - Petros
        u7.addFriend(u1); // Stella - Makis*/
        
        

    
    

	}

}
