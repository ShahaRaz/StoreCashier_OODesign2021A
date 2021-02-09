package HomeWork2;

import java.util.*;

public class ManagerLab implements Lab, Iterable<Mobile>{

    private ArrayList<Mobile> mobilesList;
    private HashMap<Mobile, String> statusMap;
    private Comparator<Mobile> comparator; // TODO: 27/12/2020 see how to utilize me (not started at constructor)



    private Scanner scn = new Scanner(System.in);
    private QueueGenerator queueGenerator;
    public ManagerLab() {

        mobilesList =  new ArrayList<>();
        statusMap = new HashMap<Mobile, String>();


        queueGenerator = QueueGenerator.getInstance();
        getUserChoose();
    }


    private  void getUserChoose() {
        int flagExit =0;
        do {
            printMainMenu();
            String userInput = scn.nextLine();
            switch (userInput) {
                case "1":
                    addMobileToLab();
                    break;
                case "2":
                    removeMobileFromLab();
                    break;
                case "3":
                    changeStatusByNumber();
                    break;
                case "4":
                    getStatusByNumber();
                    break;
                case "5":
                    printAllMobiles();
                    break;
                case "6":
                    printRepaired();
                    break;
                case "7":
                    printInRepair();
                    break;
                case "8":
                    printClientsSet();
                    break;
                case "9":
                    automaticallyAddMoblies();
                    break;
                case "10":
                    sort();
                    break;
                default:
                    System.out.println("Program Closed!");
                    flagExit=1;
//
            }
        }while(flagExit==0);

    }

    private void automaticallyAddMoblies() {
        int i = queueGenerator.getNumber();
        int add20mobiles = i+20;
        for (; i <= add20mobiles ; i++) {
            String clientId = "2000" + i;
            String firstName = "FName_ " + i;
            String lastName = "LName_" + i;
            String mobileNumber = "050" + i;
            String mobileInfo = "broken part is: " +i;
            int enqueueNum = this.queueGenerator.getNext(); // todo Why is the A always there>?
            String repairStatus = "In Repair";
            Client client = new Client(clientId, firstName, lastName);
            //adding the phone:
            if(findMobileByNumber(mobileNumber)==null) {
                this.addMobile(new Mobile(mobileNumber, client, mobileInfo, enqueueNum));
                setStatus(mobileNumber, repairStatus); // since addMobile can't receive the status string
            }
            else{
                System.err.println(mobileNumber + " is already in database");
            }


        }
    }

    private void getStatusByNumber() {
        System.out.println("============= Get Status By Number =============");
        System.out.println("Enter Mobile Number:");
        String mobileNumber = scn.nextLine();
        System.out.println(getStatus(mobileNumber));

    }

    private void changeStatusByNumber() {
        System.out.println("============= Change Status By Number =============");
        System.out.printf("Enter Mobile Number:");
        String mobileNumber = scn.nextLine();
        System.out.printf("Enter Mobile Status:");
        String newStatus = scn.nextLine();
        if(setStatus(mobileNumber,newStatus)==true)
            System.out.println("Status Changed!");
        else{
            System.out.println("Mobile not found");
        }
    }

    private void removeMobileFromLab() {
        System.out.println("Please insert the mobile number");
        String mobileToDelete = scn.nextLine();
        if (removeMobile(mobileToDelete)==true)
            System.out.println(mobileToDelete + " deleted successfully");
        else{
            System.out.println("Phone not found in our lab");
        }
    }

    private void addMobileToLab() {
        System.out.println("============= Add Mobile In Lab ============= ");
        System.out.println("Enter Client Id:");
        String clientId = scn.nextLine();
        System.out.println("Enter Client First Name");
        String firstName = scn.nextLine();
        System.out.println("Enter Client Last Name");
        String lastName = scn.nextLine();
        System.out.println("Enter Mobile Number");
        String mobileNumber = scn.nextLine();
        System.out.println("Enter Mobile Info"); // what's the problem
        String mobileInfo = scn.nextLine();
        int enqueueNum = this.queueGenerator.getNext(); // todo Why is the A always there>?
        String repairStatus = "In Repair";
        Client client = new Client(clientId,firstName,lastName);
        //adding the phone:
        if(findMobileByNumber(mobileNumber)==null) {
            this.addMobile(new Mobile(mobileNumber, client, mobileInfo, enqueueNum));
            setStatus(mobileNumber, repairStatus); // since addMobile can't receive the status string
        }
        else{
            System.err.println(mobileNumber + " is already in database");
        }
    }

    private void printMainMenu() {
        System.out.println("==================== Lab ====================");
        System.out.println("1. Add Mobile To Lab");
        System.out.println("2. Remove Mobile From Lab");
        System.out.println("3. Change Status By Number");
        System.out.println("4. Get Status By Number");
        System.out.println("5. Print All Mobiles in Lab");
        System.out.println("6. Print Repaired Mobiles");
        System.out.println("7. Print Mobiles In Repair");
        System.out.println("8. Print Set Of Clients");
        System.out.println("9. [[[ automatically add 20 phones ]]]");
        System.out.println("10. Sort arr by  ??? ___ ???  Uncomment wanted ");

        System.out.println("Enter your Option or Press any other key to Exit.");
        System.out.println("=============================================");
    }


    @Override
    public void addMobile(Mobile m) {
        mobilesList.add(m);
        //Note! this doesn't add the mobile to the hashMap, since it can't get the repairStatus String (Bounded by interface)
        // add the mobile to _____HashMap____ using SetStatus
        // ___ call method below ___  after calling addMobile() _____
//       setStatus(mobileNumber,repairStatus); // since addMobile can't receive the status string
    }

    @Override
    public boolean setStatus(String mobileNumber, String mStatus) {
        Mobile changeMe = findMobileByNumber(mobileNumber);
        if (changeMe == null) // didn't find
            return false;
        //set status in the hashMap

        if(mStatus.equalsIgnoreCase("Repaired") || mStatus.startsWith("Rep") || mStatus.startsWith("rep")) {
            statusMap.put(changeMe,"Repaired");
        }
        else {
            statusMap.put(changeMe,mStatus);
        }
        return true;

    }

    @Override
    public String getStatus(String mobileNumber) {
        Mobile findMe = findMobileByNumber(mobileNumber);
        if (findMe == null) // didn't find
            return "no phone with such number";
        return statusMap.get(findMe); // will return the status
    }

    @Override
    public Mobile getMobile(int index) {
        return this.mobilesList.get(index);
    }

    @Override
    public boolean removeMobile(Mobile m) {
        boolean fromArrList = this.mobilesList.remove(m); // returns true if deleted
        String fromHashMap = this.statusMap.remove(m); // removes from hashMap
        if (fromHashMap!=null && fromArrList==true)
            return true;
        else
            return false;
    }

    @Override
    public boolean removeMobile(String mobileNumber) {
        // this method only locate this number, and sends to other to actually delete
        Mobile deleteMe = findMobileByNumber(mobileNumber);
        if (deleteMe == null)
            return false; // didn't find phone
        return this.removeMobile(deleteMe); // true if found phone in [ list AND hashMap ]
    }

    @Override
    public Mobile removeMobile(int index) {
        Mobile deleteMe = getMobile(index);
        if(deleteMe==null)
            return null;
        if(removeMobile(deleteMe)==false) // the removal
            return null;
        return deleteMe;
    }

    @Override
    public int size() {
        return mobilesList.size(); // returns number of mobiles in lab...
    }

    @Override
    public Comparator<Mobile> getComparator() {
        return this.comparator; // test me
    }

    @Override
    public void setComparator(Comparator<Mobile> c) {
        this.comparator=c; // test me
    }

    @Override
    public void sort() {
        int flagExit=1;
        do {
            System.out.println("sort by:\n1. by Queue\n2. by client name\n3. by mobile's compareTo");
            String userInput = scn.nextLine();
            switch (userInput) {
                case "1":
                    Collections.sort(mobilesList,CompareMobilesByQueue);
                    break;
                case "2":
                    Collections.sort(mobilesList,CompareMobilesByClientName); // just for test todo deleteme
                    break;
                case "3":
                    Collections.sort(mobilesList,Mobile::compareTo);
                    break;
                default:
                    System.out.println("bad input");
                    flagExit=0;
//
            }
        }while(flagExit==0);

    }

    @Override
    public Mobile findMobileByNumber(String mobileNumber) {
        for (Mobile m  : mobilesList){
            if (m.getNumber().equals(mobileNumber)){
                return m; // false if couldn't find in list AND hashMap
            }
        }
        return null;
    }

    @Override
    public void printRepaired() { // 12
        System.out.println("============= Print Repaired Mobiles =============");
        String tableHeader = String.format("%-16s\t%-16s\t%-16s", "Client Full Name ", "Mobile No.", "Status");
        System.out.println(tableHeader);
        for (Mobile m : mobilesList){
            if(statusMap.get(m).equalsIgnoreCase("Repaired")) {
                System.out.println(m.toStringStatus(statusMap.get(m)));
            }
        }
    }

    @Override
    public void printInRepair() { //13
        System.out.println("============= Print Repaired Mobiles =============");
        String tableHeader = String.format("%-16s\t%-16s\t%-16s", "Client Full Name ", "Mobile No.", "Num in Queue");
        System.out.println(tableHeader);
        for (Mobile m : mobilesList){
            if(!statusMap.get(m).equalsIgnoreCase("Repaired"))
                System.out.println(m.toString());
        }
    }

    @Override
    public void printAllMobiles() { // 11
        System.out.println("============= Print All Mobiles =============");
        String tableHeader = String.format("%-16s\t%-16s\t%-16s", "Client Full Name ", "Mobile No.", "Status");
        System.out.println(tableHeader);
        for (Mobile m : mobilesList){
            System.out.println(m.toStringStatus(statusMap.get(m)));
        }
    }

    @Override
    public void printClientsSet() {
        System.out.println("============= Print Set Of Clients =============");
        String tableHeader = String.format("%-13s\t%-13s\t%-15s", "Client ID ", "First Name", "Last Name");
        System.out.println(tableHeader);
        for (Mobile m : mobilesList){
            System.out.println((m.getClient().toString()));
        }
    }



    public static Comparator<Mobile> CompareMobilesByQueue = new Comparator<Mobile>(){
        @Override
        public int compare(Mobile m0, Mobile m1) {
            return m1.getQueueNumAsINT() - m0.getQueueNumAsINT();
        }
    };


    public static Comparator<Mobile> CompareMobilesByClientName = new Comparator<Mobile>(){
        @Override
        public int compare(Mobile m0, Mobile m1) {
            return m1.getClient().getFullName().compareTo(m0.getClient().getFullName());
        }
    };



    // Costume Iterator
    @Override
    public Iterator<Mobile> iterator() { // prac 89 1:28:00
        return new MyMobileIterator();
    }

    public class MyMobileIterator implements Iterator<Mobile>{
        private int index=0;

        @Override
        public boolean hasNext() {
            return (index<size());
        }

        @Override
        public Mobile next() {
            if(hasNext())
                return mobilesList.get(index++);
            return null;
        }
    }
}
