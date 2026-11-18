package PamakBook;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {
	private String name;
	private String description;
	protected ArrayList <User> members;


	public Group(String name, String description) {
		this.name = name;
		this.description = description;
		this.members = new ArrayList<>();
	}


	public String getName() { return name; }


	public boolean isMember(User u) {
		return members.contains(u);
	}


	public void addMember(User u) {
		if (!members.contains(u)) {
			members.add(u);
			u.addGroupReference(this);
			System.out.println(u.getName() + " has been successfully enrolled in group " + name);
		}
	}


	public void printMembers() {
		System.out.println("*******************************");
		System.out.println("Members of group " + name);
		System.out.println("*******************************");
		int i = 1;
		for (User u : members) System.out.println(i++ + ": " + u.getName());
			System.out.println("-----------------------------");
	}

}
