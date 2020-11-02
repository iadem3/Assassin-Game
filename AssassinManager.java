import java.util.*;
public class AssassinManager {
	LinkedList<AssassinNode> killRing = new LinkedList<>();
	LinkedList<AssassinNode> Graveyard = new LinkedList<>();
	Iterator<AssassinNode> k = killRing.iterator();
	Iterator<AssassinNode> j = Graveyard.iterator();
	AssassinNode front = null;
	AssassinNode prev1 = null;
	
	public AssassinManager(List<String> names) {
		if(names.isEmpty() == true) {
			throw new IllegalArgumentException();
		}
		else {
			
			for(String i : names) {
				AssassinNode k = new AssassinNode(i);
				killRing.add(k);
				if(killRing.size() == 1) {
					front = k;
				}
				else if(killRing.size() == names.size()) {
					prev1 = k;
				}
			}
			
			k = killRing.iterator();
			AssassinNode current = k.next();
			AssassinNode temp = null;
			
			while(k.hasNext()) {
				temp = k.next();
				current.next = temp;
				System.out.println(current.name + " " + temp.name);
				current = temp;
			}
			temp.next = front;
		}
		
	}
	
	public void kill(String name) {
		k = killRing.iterator();
		AssassinNode prev = null;
		while(k.hasNext()) {
			AssassinNode current = k.next();
			if(name.equals(current.name)) {
				
				killRing.remove(current);
				Graveyard.add(current);
				
				if(prev == null) {
					current.killer = prev1.name;
					front = current.next;
					prev1.next = front;
					
				}
				else {

					prev.next = current.next;
					current.killer = prev.name;
					
				}
				break;
			}
			
			else {
				prev = current;
			}
			
		}
	}
	
	public void printKillRing() {
		k = killRing.iterator();
		while(k.hasNext()) {
			AssassinNode temp = k.next();
			System.out.println(temp.name + " is stalking " + temp.next.name);
		}
	}
	
	public void printGraveyard() {
		j = Graveyard.iterator();
		while(j.hasNext()) {
			AssassinNode temp = j.next();
			System.out.println(temp.name + " was killed by " + temp.killer);
		}
	}
	
	public boolean killRingContains(String name) {
		k = killRing.iterator();
		while(k.hasNext()) {
			AssassinNode current = k.next();
			if(name.equals(current.name)) {
				return true;
				
			}
		}
		return false;
	}
	
	public boolean graveyardContains(String name) {
		j = Graveyard.iterator();
		while(j.hasNext()) {
			AssassinNode temp = j.next();
			if(temp.name == name) {
				return true;
			}
		}
		return false;
	}
	
	public boolean gameOver() {
		if(killRing.size() == 1) {
			return true;
		}
		return false;
	}
	
	public String winner() {
		if(killRing.size() == 1) {
			return front.name;
		}
			return null;
		
	}
	
}
