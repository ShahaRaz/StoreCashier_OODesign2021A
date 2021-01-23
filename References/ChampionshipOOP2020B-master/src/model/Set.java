package model;
import java.util.Vector;


public class Set {

    private Vector<Player> list;

    public void upWins(int index){
 	   list.elementAt(index).uppWins(); 
    }

    public Set() {
        list = new Vector<Player>();
    }
    public void printList() {
    	System.out.println(list);
    }

    // you can add an object
    public Boolean add(Player p) {
    	if(list.size()==Model.NUMBER_OF_PLAYERS) {
    		System.out.println("Teams bracket is full!");
    		return false;
    	}
        for (Player t : list) {
            if (t.equals(p)) {
            	System.out.println("the participant already exists");
            	return false;
            }
        }
        list.add(p);
        return true;
    }
   
	public void reset() {
    	for (int i = 0; i < list.size(); i++) {
			list.remove(i);
		}
    }
    

    public Vector<Player> getList() {
		return list;
	}
	public void setList(Vector<Player> list) {
		this.list = list;
	}
	public Player get(int index) {
        return list.get(index);
    }
    public void showList() {
    	for (int i = 0; i < list.size(); i++) 
    		System.out.println(list.elementAt(i));	
    }

	public int size() {
		return list.size();
	}
/*	public void showListUI() {
		MassageAble ui = new GraphicUI();
		StringBuffer SB = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			SB.append(list.elementAt(i).toString());
			SB.append("\n");
		}
		String showCitizens = SB.substring(0, SB.length() - 1);
		ui.showMassage("Presenting all citizens: \n" + showCitizens);

	}*/
	

}