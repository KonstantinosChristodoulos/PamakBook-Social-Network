package PamakBook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class User implements Serializable{
	
	private String name;
	private String email;
	private ArrayList<User> friends;
	private ArrayList<Group> groups;
	private ArrayList<Post> posts;
	


	public User(String name, String email) {
		if (!email.matches("(ics|iis|dai)\\d{3,5}@uom\\.edu\\.gr")) {
			System.out.println("User " + name + " has not been created. Email format is not acceptable.");
		}
		this.name = name;
		this.email = email;
		this.friends = new ArrayList<>();
		this.groups = new ArrayList<>();
		this.posts = new ArrayList<>();
	}


	public String getName() { return name; }
	public String getEmail() { return email; }


	public boolean isFriend(User other) {
		return friends.contains(other);
	}


	public void addFriend(User other) {
		if (other == null || other == this) return;
		if(!this.friends.contains(other)) {
			other.friends.add(this);
			this.friends.add(other);
			System.out.println(this.name + " and " + other.name + " are now friends!");
		}
	}
	
	

	//common friends
	public void printcommonfriends(User p1 )
	{
		System.out.println("**************************************");
        System.out.println("Common friends of " +p1.name+ " and " + this.name);
        System.out.println("**************************************");
		printUserList(p1.commonFriends(this));
		System.out.println("--------------------------------------");
	}
	
	public List<User> commonFriends(User other) {
		List<User> list = new ArrayList<>();
		for (User u : friends) {
			if (other.friends.contains(u)) list.add(u);
		}
		return list;
	}


	public void printFriends() {
		System.out.println("************************");
		System.out.println("Friends of " + name);
		System.out.println("************************");
		int i = 1;
		for (User u : friends) System.out.println(i++ + ": " + u.name);
			System.out.println("-----------------------");
	}


	public void printGroups() {
		System.out.println("**************************************");
		System.out.println("Groups that " + name + " has been enrolled in");
		System.out.println("**************************************");
		int i = 1;
		for (Group g : groups) System.out.println(i++ + ": " + g.getName());
			System.out.println("--------------------------------------");
	}
	
	
	// Βοηθητική μέθοδος για εκτύπωση λίστας χρηστών με αρίθμηση
    private static void printUserList(java.util.List<User> list) {
        int i = 1;
        for (User u : list) {
            System.out.println(i++ + ": " + u.getName());
        }
    }

	protected void addGroupReference(Group g) {
		groups.add(g);
	}

	//infected
	public void printinfected()
	{
		System.out.println("*******************************");
        System.out.println(this.name + " has been infected. The following users have to be tested");
        System.out.println("*******************************");
        // potentialTransmissions επιστρέφει Set<User>
        for (User u : this.potentialTransmissions()) {
            System.out.println(u.getName());
        }
        System.out.println("-----------------------------");
    }
	
	public ArrayList<User> potentialTransmissions() {
		ArrayList<User> result = new ArrayList<>();
		for (User f : friends) {
			result.add(f);
		}
		for (User f : friends) {
			for (User f1 : f.friends) {
				if(f1!=this && !result.contains(f1)) {
					result.add(f1);
				}
			}
		}
		return result;
	}
	
	//posts
	public void addPost(String text) {
	    Post p = new Post(this, text);
	    posts.add(p);
	}
	
	public List<Post> getFeedPosts() {
	    ArrayList<Post> feed = new ArrayList<>();

	    // δικά του posts
	    feed.addAll(this.posts);

	    // posts φίλων
	    for (User f : friends) {
	        feed.addAll(f.posts);
	    }

	    // ταξινόμηση: πιο πρόσφατο πρώτο
	    Collections.sort(feed, new PostTimestampComparator() {
	        @Override
	        public int compare(Post p1, Post p2) {
	            return p2.getTimestamp().compareTo(p1.getTimestamp());
	        }
	    });

	    return feed;
	}
	
	
	// προτεινόμενοι φίλοι
	public ArrayList<User> SuggestedFriends() {
	    ArrayList<User> suggested = new ArrayList<>();

	    for (User f : friends) {
	        for (User fof : f.friends) {
	            if (fof != this &&
	                !this.friends.contains(fof) &&
	                !suggested.contains(fof)) {
	                suggested.add(fof);
	            }
	        }
	    }

	    return suggested;
	}
	
	
	
	//εσωτερική κλάση για σύγκριση
	private static class PostTimestampComparator implements Comparator<Post> {

	    @Override
	    public int compare(Post p1, Post p2) {
	        // πιο πρόσφατο πρώτο
	        return p2.getTimestamp().compareTo(p1.getTimestamp());
	    }
	}
	
	public ArrayList<User> getFriends() {
	    return friends;
	}



}
