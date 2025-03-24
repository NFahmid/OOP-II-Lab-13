import java.util.List;

public class FlightDisplay implements DisplayClass {
    private Flight flight;

    public FlightDisplay(Flight flight) {
        this.flight = flight;
    }

    @Override
    public void displayRegisteredUsersForAllFlight() {
        System.out.println();
        System.out.printf("%50s %s Here is the list of all the Registered Users in All Flights %s\n", " ", "++++++++++++++", "++++++++++++++");
        for (Flight flight : this.flight.getFlightList()) {
            List<Customer> c = flight.getListOfRegisteredCustomersInAFlight();
            if (!c.isEmpty()) {
                displayHeaderForUsers(flight, c);
            }
        }
    }

    @Override
    public void displayRegisteredUsersForASpecificFlight(String flightNum) {
        System.out.println();
        boolean found = false;
        for (Flight flight : this.flight.getFlightList()) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNum)) {
                found = true;
                List<Customer> c = flight.getListOfRegisteredCustomersInAFlight();
                if (!c.isEmpty()) {
                    System.out.printf("%50s %s Here is the list of all the Registered Users in Flight %s %s\n", " ", "++++++++++++++", flightNum.toUpperCase(), "++++++++++++++");
                    displayHeaderForUsers(flight, c);
                } else {
                    System.out.println("No users registered for flight " + flightNum.toUpperCase());
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Flight " + flightNum.toUpperCase() + " not found.");
        }
    }

    @Override
    public void displayHeaderForUsers(Flight flight, List<Customer> c) {
        System.out.println("\nFlight Details:");
        System.out.printf("%10s| %-10s | %-32s | %-7s | %-27s | %-35s | %-23s | %-12s |\n", "", "Serial No.", "User ID", "Name", "Age", "Email", "Address", "Phone", "Tickets");
        System.out.println("-".repeat(170));
        
        for (int i = 0; i < c.size(); i++) {
            Customer customer = c.get(i);
            System.out.printf("%10s| %-10d | %-32s | %-7s | %-27s | %-35s | %-23s | %-12d |\n", "",
                    (i + 1),
                    customer.getUserID(),
                    customer.getName(),
                    customer.getAge(),
                    customer.getEmail(),
                    customer.getAddress(),
                    customer.getPhone(),
                    customer.getNumOfTicketsBookedByUser().get(i));
        }
        System.out.println("-".repeat(170));
        System.out.println();
    }

    @Override
    public void displayFlightsRegisteredByOneUser(String userID) {
        boolean found = false;
        for (Customer customer : Customer.customerCollection) {
            if (customer.getUserID().equals(userID)) {
                found = true;
                List<Flight> flights = customer.getFlightsRegisteredByUser();
                if (!flights.isEmpty()) {
                    System.out.println("\nFlight Details:");
                    System.out.println("-".repeat(170));
                    System.out.printf("| %-5s| %-41s | %-9s | %-11s | %-21s | %-22s | %-12s | %-11s | %-6s | %-10s |\n",
                            "Sr.No", "Schedule", "Flight No", "Tickets", "From", "To", "Arrival", "Duration", "Gate", "Status");
                    System.out.println("-".repeat(170));
                    
                    for (int i = 0; i < flights.size(); i++) {
                        Flight flight = flights.get(i);
                        System.out.printf("| %-5d| %-41s | %-9s | \t%-9d | %-21s | %-22s | %-12s | %-11s | %-6s | %-10s |\n",
                                (i + 1),
                                flight.getFlightSchedule(),
                                flight.getFlightNumber(),
                                customer.getNumOfTicketsBookedByUser().get(i),
                                flight.getFromWhichCity(),
                                flight.getToWhichCity(),
                                flight.fetchArrivalTime(),
                                flight.getFlightTime(),
                                flight.getGate(),
                                "As Per Schedule");
                    }
                    System.out.println("-".repeat(170));
                } else {
                    System.out.println("No flights registered for user " + userID);
                }
                break;
            }
        }
        if (!found) {
            System.out.println("User " + userID + " not found.");
        }
    }
}