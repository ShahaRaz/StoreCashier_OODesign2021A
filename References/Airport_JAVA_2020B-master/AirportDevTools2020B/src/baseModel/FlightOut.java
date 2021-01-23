package baseModel;

public class FlightOut extends Flight implements Cloneable {

	public FlightOut(String brand, String arrAirPort, MyDate date, String depTime, String arrTime, String flightId,
			int terminal, boolean isIncomingFlight) {
		super(brand, date, depTime, arrTime, flightId, terminal, isIncomingFlight);
		setArriveAirPort(arrAirPort);
		String[] airportSplit = splitAirport(arrAirPort);
		setCity(airportSplit[0]);
		setCountry(airportSplit[1]);
		setDepAirPort("Ben Gurion, TLV");

	}

	@Override
	public String toString() {
		return "Brand: " + brand + ", Arrival Air-Port: " + arriveAirPort + ", " + getDayInWeek() + ", Deptarture Date: " + getDate().toString()
				+ ", Departure Time: " + depTime + ", Arrival Time: " + arrTime + ", Flight Id:" + flightId
				+ ", Terminal: " + terminalNum;
	
	}
	
	public String toStringServer() {
		return("Airline: " + this.brand + ", Country: " +this.getCountry()+ ", City: " +this.getCity()+ ", Airport: " + this.getAirport() +
		", " + this.getDayInWeek().toString() +  ", Departure date: " +this.getDate().toString()+ ", Leave by: " + this.getDepTime()) ;

	}
	public String toHtmlTableServer() {
		return("<tr><td>" + this.brand + "</td><td>" +this.getCountry()+ "</td><td>" +this.getCity()+ "</td><td>" + this.getAirport() +
		"</td><td>" + this.getDayInWeek().toString() +  "</td><td>" +this.getDate().toString()+ "</td><td>" + this.getDepTime()+"</td></tr>") ;

	}

}
