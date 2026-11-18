package PamakBook;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserPageFrame extends JFrame {
	
	
    private User user;
    private JFrame loginFrame;
    private JPanel userInfoPanel;
    private JPanel createPostPanel;
    private JPanel fieldsPanel;
    private JPanel suggestPanel;
    private JPanel friendspostPanel;
    private JPanel postsPanel;
    private JPanel addPanel;
    private JPanel groupPanel;
    private JPanel friendPanel;
    private JPanel totalPanel;
    
  
    
    private JTextField nameField;
    private JTextField emailField;
    private JTextField friendField;
    
    
    private JTextArea postArea;
    
    private JButton backBtn;
    private JButton postBtn;
    private JButton addfriendBtn;
    private JButton joinGroupBtn;
    
    
    private JLabel recentpost;
    private JLabel suggest;
    
    private JList<String> suggestedList;
    
    private JScrollPane suggestedScroll;
    private JScrollPane groupsScroll;
    
    private JList<String> groupList;
    


    private JTextArea feedArea;
    private DefaultListModel<String> suggestedModel;

    public UserPageFrame(User user, JFrame loginFrame, ArrayList<User> users, ArrayList<Group> groups) {
        this.user = user;
        this.loginFrame = loginFrame;
        

        setTitle("Σελίδα Χρήστη");
        setSize(440,650 );
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        /* ================= PANEL 1: USER INFO ================= */
        userInfoPanel = new JPanel();
        

        nameField = new JTextField(user.getName());
        nameField.setEditable(false);

        emailField = new JTextField(user.getEmail());
        emailField.setEditable(false);
        
        nameField.setBackground(Color.WHITE);
        emailField.setBackground(Color.WHITE);

        fieldsPanel = new JPanel(new GridLayout(1,2,5,5));
        fieldsPanel.add(nameField);
        fieldsPanel.add(emailField);

        backBtn = new JButton("Back to Login Screen");

        userInfoPanel.add(fieldsPanel);
        userInfoPanel.add(backBtn);

        

        /* ================= PANEL 2: CREATE POST ================= */
        createPostPanel = new JPanel();
        

        postArea = new JTextArea(10,30);
        postArea.setLineWrap(true);
        postArea.setWrapStyleWord(true);

        postBtn = new JButton("Post");

        createPostPanel.add(new JScrollPane(postArea));
        createPostPanel.add(postBtn);

      
        

        /* ================= PANEL 3: FRIENDS POSTS ================= */

        friendspostPanel = new JPanel();
        
        feedArea = new JTextArea(8,20);
        feedArea.setEditable(false);
        feedArea.setLineWrap(true);
        feedArea.setWrapStyleWord(true);
        recentpost = new JLabel("Recent Posts by Friends");
        
        friendspostPanel.add(recentpost);

        friendspostPanel.add( new JScrollPane(feedArea));

        
       

        /* ================= PANEL 4: SUGGESTED FRIENDS ================= */

        suggestPanel = new JPanel();

        suggest = new JLabel("Suggested Friends");
        suggestPanel.add(suggest);

        suggestedModel = new DefaultListModel<>();
        suggestedList = new JList<>(suggestedModel);

        suggestedScroll = new JScrollPane(suggestedList);
        JPanel suggestedWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        suggestedScroll.setPreferredSize(new Dimension(55, 55));
        suggestedWrapper.add(suggestedScroll);

        suggestPanel.add(suggestedWrapper);
        
        
        
        postsPanel = new JPanel(new BorderLayout());
        postsPanel.add(createPostPanel, BorderLayout.NORTH);
        postsPanel.add(friendspostPanel, BorderLayout.CENTER);
        
        /* ================= PANEL 5: FRIENDS AND GROUPS PANEL ================= */
        
        addPanel = new JPanel(new BorderLayout());
        friendPanel = new JPanel();
        
        friendField = new JTextField("Enter Friend Name");
        addfriendBtn = new JButton("Add Friennd");
        
        
        
        addfriendBtn.addActionListener(e -> {
            String username;
            username = friendField.getText();
            User friend = null;
        	
        	for(User u:users) {
        		if (u.getName().equals(username)) {
        			friend = u;
        			break;
        		}
        	}
        	
        	
        	 if (friend == null) {
        	        JOptionPane.showMessageDialog(this,"User not found","Error",JOptionPane.ERROR_MESSAGE);
        	        return;
        	    }

        	    if (friend == user) {
        	        JOptionPane.showMessageDialog(this, "You cannot add yourself","Error", JOptionPane.ERROR_MESSAGE);
        	        return;
        	    }

        	    if (user.isFriend(friend)) {
        	        JOptionPane.showMessageDialog(this,"You are already friends","Info",  JOptionPane.INFORMATION_MESSAGE);
        	        return;
        	    }
        	    
        	    
        	    
        	    user.addFriend(friend);

        	    JOptionPane.showMessageDialog(this,"You are now friends with " + friend.getName(),"Success",JOptionPane.INFORMATION_MESSAGE);
        	    new FriendsGraphFrame(users);
        	    

        	    
        	    friendField.setText("");

        	    
        	    refreshSuggestedFriends();
        	    refreshFeed();

            
            
            
        });
        
        
        
        
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Group g : groups) {
            model.addElement(g.getName());
        }
        
        groupList = new JList<>(model);
        groupsScroll = new JScrollPane(groupList);
        groupsScroll.setPreferredSize(new Dimension(100, 55));
        
        
        
        joinGroupBtn = new JButton("Join Group");
        
        joinGroupBtn.addActionListener(e -> {
        	
        	String selectedGroupName = groupList.getSelectedValue();
        	Group selectedGroup = null;
        	
        	

            if (selectedGroupName == null) {
                JOptionPane.showMessageDialog(this,
                        "Please select a group",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            for (Group g : groups) {
            	if(g.getName().equals(selectedGroupName)) {
            		selectedGroup = g;
            		break;
            	}
            }
            
            if(selectedGroup.isMember(user)) {
            	JOptionPane.showMessageDialog(this,"You are already a member of this group","Info",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            selectedGroup.addMember(user);
            
            
            if(selectedGroup.isMember(user)) {
            	JOptionPane.showMessageDialog(this,"You have successfully joined the group","Info",JOptionPane.INFORMATION_MESSAGE);
                return;
            }else {
            	JOptionPane.showMessageDialog(this, "FAILED: You cannot join this Closed Group (Criteria not met).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        	
        });
        
        
        
        groupPanel = new JPanel();
        

        JLabel groupLabel = new JLabel("Available Groups");
        groupPanel.add(groupLabel);
        groupPanel.add(groupsScroll);
        groupPanel.add(joinGroupBtn);
        
        
        friendPanel.add(friendField);
        friendPanel.add(addfriendBtn);
        addPanel.add(suggestPanel, BorderLayout.NORTH);
        addPanel.add(friendPanel, BorderLayout.CENTER);
        addPanel.add(groupPanel, BorderLayout.SOUTH);
        
        
        /* ================= PANEL 6: TOTAL PANEL ================= */
        
        totalPanel = new JPanel();
        totalPanel.setLayout(new BorderLayout());
        
        totalPanel.add(userInfoPanel, BorderLayout.NORTH);
        totalPanel.add(postsPanel, BorderLayout.CENTER);
        totalPanel.add(addPanel, BorderLayout.SOUTH);
        
        add(totalPanel);

        /* ================= ACTIONS ================= */
        postBtn.addActionListener(e -> {
            if (!postArea.getText().isEmpty()) {
                user.addPost(postArea.getText());
                postArea.setText("");
                refreshFeed();
            }
        });

        backBtn.addActionListener(e -> {
            loginFrame.setVisible(true);
            dispose();
        });

        refreshFeed();
        refreshSuggestedFriends();

        setVisible(true);
    }

    private void refreshFeed() {
        feedArea.setText("");
        for (Post p : user.getFeedPosts()) {
            feedArea.append(
                p.getAuthor().getName() + ", " + p.getTimestamp() + ", " +
                p.getText() + "\n\n"
            );
        }
    }

    private void refreshSuggestedFriends() { 
        suggestedModel.clear();
        for (User u : user.SuggestedFriends()) {
            suggestedModel.addElement(u.getName());
        }
    }
	

}
