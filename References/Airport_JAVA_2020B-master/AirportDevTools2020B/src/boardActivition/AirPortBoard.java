package boardActivition;
import java.util.ArrayList;
import java.util.List;

import baseModel.FilterFlights;
import baseModel.Flight;
import baseModel.MyDate;
import baseModel.Program;
import interfaces.Massageable;
import interfaces.SilentUi;
import interfaces.ConsoleUI;
import interfaces.htmlUI;

public class AirPortBoard {

	public static final String FILE_NAME = "Input.txt";

	private List<Flight> allFlights = new ArrayList<>();
	private List<Flight> flightsInBoard = new ArrayList<>();
	private List<Flight> flightsOutBoard = new ArrayList<>();
	public Massageable ui = new ConsoleUI();
	private Massageable devUi = new SilentUi();
	private FilterFlights filtered;
	private MyDate LAST_DAY_IN_BOARD = new MyDate(1, 1, 2100); 
	private MyDate FIRST_DAY_IN_BOARD = new MyDate(1,1,2020);
	private boolean isHtmlFormat;
	// Args:
	// 0 - User Interface kind
	// 1 - departures or Arrivials
	// 2 - airline brand
	// 3 - country
	// 4 - City
	// 5 - Airport
	// 6 - starting date
	// 7 - ending date
	// 8 - week days [ 1= Sunday , 2=Monday... 7=Saturday]
	// EX : "html" "departures" "Arkia" "USA" "New-York" "JFK" "23/04/2020"
	// "01/05/2020" "435"

	
	
	public AirPortBoard(String[] args) {
		AirportActivition(args);
	}

	

	void AirportActivition(String[] args) {
		if (args.length == 0) {
			devUi.showErrMassage("Expected values: ui kind + dep/arr + airline brand + country + airport + Take off date "
					+ "landing date + weekDays[1sunday->7]");
			ui.showErrMassage("no args, Exiting");
			exit();
		}
		
		devUi.showErrMassage("entered JAVA program!");
		int i = 0;
		for (String s : args) {
			i++;
			devUi.showMassage((s));
		}
		devUi.showMassage(args.toString());
		devUi.showErrMassage("java recived :" + args.length + " && arguments" + " Number of strings: " + i);
		
		
		
		if (args[0].contains("html")) {
			ui = new htmlUI(); // overWrites the console ui
			isHtmlFormat=true;
			devUi.showErrMassage("html it is");
		} else if (args[0].contains("console")) {
			devUi.showErrMassage("console it is");
			isHtmlFormat=false;
		}
			
		else {
			devUi.showErrMassage("first arg must be ui type (html or console)");
			exit();
		}
		
		try {
			Program.loadFromFile(this.allFlights, this.flightsInBoard, this.flightsOutBoard,FILE_NAME); // load all flights from file

		} catch (Exception e) {
			ui.showErrMassage("Error! can't load flights from data base. Try Again!");
			e.printStackTrace();
			exit();
		}

		// 1 Departure / Arriviall__________________________________________________
		if (args[1].contains("dep")) {
		//	Program.simpleMiniShowFlights(flightsOutBoard);
			filtered = new FilterFlights(flightsOutBoard, null);
		}
		if (args[1].contains("arr")) {
		//	Program.simpleMiniShowFlights(flightsInBoard);
			filtered = new FilterFlights(flightsInBoard, null);
		}

		
		// 2 - airline brand__________________________________________________
		devUi.showMassage("args[2]: " + args[2]);
		if (args[2].length() != 0) {
			filtered.filterByAirlineBrand(args[2]);
		}
		devUi.showMassage("filtered stage 2");
		devUi.showErrMassage(filtered.toStringServer(ui.dropLineChar()));
		
		// 3 - country__________________________________________________ 
		devUi.showMassage("args[3]: " + args[3]);
		if (args[3].length() != 0) {
//			String[] splitCountry = args[2].split(",");
//			for (String c:splitCountry) {
//				filtered.filterByAirlineCountry(c);	
//			}
			filtered.filterByAirlineCountry(args[3]);

		}
		devUi.showMassage("filtered stage 3");
		devUi.showErrMassage(filtered.toStringServer(ui.dropLineChar()));
		
		
		// 4 - City__________________________________________________
		devUi.showMassage("args[4]: " + args[4]);
		if (args[4].length() != 0) {
			filtered.filterByAirlineCity(args[4]);
		}
		devUi.showMassage("filtered stage 4");
		devUi.showErrMassage(filtered.toStringServer(ui.dropLineChar()));
		
		

		// 5 - Airport__________________________________________________
		devUi.showMassage("args[5]: " + args[5]);
		if (args[5].length() != 0) {
			filtered.filterByAirlineAirport(args[5]);
		}
		devUi.showMassage("filtered stage 5");
		devUi.showErrMassage(filtered.toStringServer(ui.dropLineChar()));
		
		
		// 6 - Starting Date__________________________________________________
		devUi.showMassage("args[6]: " + args[6]);
		if (args[6].length() != 0) {
			filtered.filterByDateRange(MyDate.ParseFromString(args[6]), LAST_DAY_IN_BOARD);
		}
		devUi.showMassage("filtered stage 6");
		devUi.showErrMassage(filtered.toStringServer(ui.dropLineChar()));
		
		
		// 7 - Ending Date__________________________________________________
		devUi.showMassage("args[7]: " + args[7]);
		if (args[7].length() != 0) {
			filtered.filterByDateRange(FIRST_DAY_IN_BOARD,MyDate.ParseFromString(args[7]));
		}
		
		devUi.showErrMassage("filtered stage 7");
		devUi.showErrMassage(filtered.toStringServer(ui.dropLineChar()));

		// 8 - week Days__________________________________________________
		if (args[8].length() != 0) {
			devUi.showMassage("args[8]: " + args[8]);
			filtered.toggleIntDaysInWeekFromStr(args[8]);
			filtered.filterByDateWeekDay();
		}

		// printOut

		/// Print Style: 
		if (isHtmlFormat)
			ui.showMassage(filtered.toHtmlTableServer(ui.dropLineChar()));
		else
			ui.showMassage(filtered.toStringServer(ui.dropLineChar()));
		

	}


	private void exit() {
		ui.showMassage("Goodbye");
	}
	
	

}

