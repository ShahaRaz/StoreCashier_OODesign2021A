package baseModel;

public class FlightIn extends Flight implements Cloneable {
	private String depCity;

	public FlightIn(String brand, String depAirPort, MyDate date, String depTime, String arrTime, String flightId,
			int terminal, boolean isIncomingFlight) {
		super(brand, date, depTime, arrTime, flightId, terminal, isIncomingFlight);
		setDepAirPort(depAirPort);
		String[] airportSplit = splitAirport(depAirPort);
		setCity(airportSplit[0]);
		setCountry(airportSplit[1]);
		setArriveAirPort("Ben-Gurion, TLV");
		
	}

	@Override
	public String toString() {
		return "Brand: " + brand + ", Departure Air-Port: " + depAirPort + ", " + getDayInWeek() + ", Deptarture Date: "
				+ getDate().toString() + ", Departure Time: " + depTime + ", Arrival Time: " + arrTime + ", Flight Id:"
				+ flightId + ", Terminal: " + terminalNum;
	}

	public String getDepCity() {
		return depCity;  
	}

	public String toStringServer() {
		return("Airline: " + this.brand + ", Country: " +this.getCountry()+ ", City: " +this.getCity()+ ", Airport:" +this.getAirport() +
		", " + this.getDayInWeek().toString() +  ", Departure date:" +this.getDate().toString()+ ", Arrives at: " +this.getArrTime()) ;
	}
	public String toHtmlTableServer() {
		return("<tr><td> " + this.brand + "</td><td>" +this.getCountry()+ "</td><td>" +this.getCity()+ "</td><td>" +this.getAirport() +
		"</td><td>" + this.getDayInWeek().toString() +  "</td><td>" +this.getDate().toString()+ "</td><td>" +this.getArrTime()+ "</td></tr>") ;
	}

}
