package PamakBook;

public class ClosedGroup extends Group {

	public ClosedGroup(String name, String description) {
		super(name, description);
		
	}
	
	@Override
	public void addMember(User u) {
		// Αν η ομάδα είναι άδεια -> επιτρέπουμε το πρώτο μέλος
		if (members.isEmpty()) {
			members.add(u);
			u.addGroupReference(this);
			System.out.println(u.getName() + " has been successfully enrolled in group " + getName());
			return;
		}


		// Έλεγχος αν είναι φίλος με κάποιο ήδη μέλος
		boolean allowed = false;
		for (User m : members) {
			if (m.isFriend(u)) {
				allowed = true;
				break;
			}
		}


		if (!allowed) {
			System.out.println("FAILED: " + u.getName() + " cannot be enrolled in group " + getName());
			return;
		}


		// Αν επιτρέπεται -> πρόσθεσέ τον
		if (!members.contains(u)) {
			members.add(u);
			u.addGroupReference(this);
			System.out.println(u.getName() + " has been successfully enrolled in group " + getName());
		}
	}

}
