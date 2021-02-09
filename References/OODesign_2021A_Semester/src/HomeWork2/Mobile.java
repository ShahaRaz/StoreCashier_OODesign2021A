package HomeWork2;


public class Mobile implements Comparable<Mobile> {

	private String number;
	private Client client;
	private String info; // Defect description
	private int queueNumber; // changed from String

	public Mobile(String number, Client client, String info, int queueNumber) {
		this.number = number;
		this.client = client;
		this.info = info;
		this.queueNumber = queueNumber;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		if (info.equalsIgnoreCase("Repaired") || info.startsWith("Rep") || info.startsWith("rep")) {
			this.info = "Repaired"; // same Case
			this.setQueueNumber(-1); //
		}
		else
			this.info=info;
	}


	public String getQueueNumber() {
		return "A" +queueNumber;
	}

	public int getQueueNumAsINT(){
		return this.queueNumber;
	}

	public void setQueueNumber(int queueNumber) {
		this.queueNumber = queueNumber;
	}


	@Override
	public String toString() {
		return String.format("%-16s\t%-16s\t%-16s", client.getFullName(), number, this.getQueueNumber());
	}

	public String toStringStatus(String theStatus){
		return String.format("%-16s\t%-16s\t%-16s", client.getFullName(), number, theStatus);
	}




	@Override
	public int compareTo(Mobile other) {
		return other.getQueueNumAsINT() - this.getQueueNumAsINT(); // lower is better
		// NO CONVERSION TO INT ALL THE TIME, CHANGED TYPE TO INT(ALSO IN CLASS PROPERTY)
		//            try {
		//                int otherMobileNumInQue = Integer.parseInt(m1.getQueueNumber());
		//                int thisMobileNumInQue = Integer.parseInt(m0.getQueueNumber());
		//                return otherMobileNumInQue - thisMobileNumInQue; // smaller is better
		//            }
		//            catch (Exception e){
		//                System.err.println("___Note! QueueNumber must be numbers only___");
		//            }
		//            return 0; // don't throw
	}
}
