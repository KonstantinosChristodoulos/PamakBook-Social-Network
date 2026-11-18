package PamakBook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginFrame extends JFrame {
	
	private ArrayList<User> users;
	
	private JPanel panel;
	private JTextField nameField, emailField;
	private JButton userPageBtn, newuserBtn, saveBtn;
	private JButton infectionBtn;

    public LoginFrame(ArrayList<User> users, ArrayList<Group> groups) {
        this.users = users;

        setTitle("User Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        nameField = new JTextField("user name");
        emailField = new JTextField("user email");
        userPageBtn = new JButton("Enter User Page");
        newuserBtn = new JButton("New User");
        infectionBtn = new JButton("Show Potential Infections");
        saveBtn = new JButton("Save PamakBook");

        panel.add(newuserBtn);
        panel.add(nameField);
        panel.add(emailField);
        panel.add(userPageBtn);
        panel.add(infectionBtn);
        panel.add(saveBtn);

        add(panel);
        
        newuserBtn.addActionListener(e -> {
        	String email = emailField.getText();
        	String name = nameField.getText();
        	boolean flag=true;
        	
        	for(User user:users) {
        		if (user.getName().equals(name)) {
        			flag=false;
        		}
        	}
        	if(flag == false) {
        		JOptionPane.showMessageDialog(null,"User " + name + " has not been created. Username already exists","Message",JOptionPane.INFORMATION_MESSAGE);
        	}
        	else {
        		if (!email.matches("(ics|iis|dai)\\d{3,5}@uom\\.edu\\.gr")) {
        			JOptionPane.showMessageDialog(null,"User " + name + " has not been created. Email format is not acceptable.","Message",JOptionPane.INFORMATION_MESSAGE);
        		}
        		else {
        			users.add(new User(name,email));
        			JOptionPane.showMessageDialog(null,"User " + name + " has been created.","Message",JOptionPane.INFORMATION_MESSAGE);
        		}
        	}
        	nameField.setText("user name");
        	emailField.setText("user email");
        	
        });

        userPageBtn.addActionListener(e -> {
            User u = findUser(nameField.getText());
            if (u == null) {
                JOptionPane.showMessageDialog(this, "User "+ nameField.getText() + " not found");
            } else {
                new UserPageFrame(u, this, users, groups);
                nameField.setText("user name");
            	emailField.setText("user email");
                setVisible(false);
            }
        });

        infectionBtn.addActionListener(e -> {
            User u = findUser(nameField.getText());
            if (u == null) {
                JOptionPane.showMessageDialog(this, "User not found");
            } else {
                new InfectionFrame(u, this);
                nameField.setText("user name");
            	emailField.setText("user email");
                setVisible(false);
            }
        });
        
        saveBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		pamakbookState saved = new pamakbookState (users);
        		saved.save(users);
        		
        	}
        });

        setVisible(true);
    }

    private User findUser(String name) {
        for (User u : users) {
            if (u.getName().equals(name)) {
                return u;
            }
        }
        return null;
    }
}
