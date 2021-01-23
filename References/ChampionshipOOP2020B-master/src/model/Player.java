package model;
public class Player {
	private String name;
	private int winsCounter;
	
	public Player(String name) throws Exception {
		checkNameValid(name);
		this.name=name;
		winsCounter=0;
	}
	
	private void checkNameValid(String name2) throws Exception {
		if(name2.length()==0) 
			throw new Exception("cannot insert empty name");
//		if(name.charAt(0)<65||name.charAt(0)>90)
//			throw new Exception("first letter must be Upper-Case");
		////// Naming logic will be here With proper throw
	}

	public void uppWins() {
		winsCounter++;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWinsCounter() {
		return winsCounter;
	}

	public boolean equals(Player other) {
		return other.getName().equalsIgnoreCase(this.getName());
	}

	@Override
	public String toString() {
		return "Player [name=" + name + " wins = "+winsCounter+"]";
	}
	
}
