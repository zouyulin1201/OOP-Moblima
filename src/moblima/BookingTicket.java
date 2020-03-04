package moblima;

import java.util.ArrayList;
import java.util.Scanner;

public class BookingTicket {
    public static Ticket BookTicket(ArrayList<Movie> movies, ArrayList<MovieSlot> slots, int cat) {

        String sel = ""; //Selected movie
        Cineplex selectedCineplex = null;
        boolean available = false;
        Movie chosen = new Movie();
        System.out.println("Welcome to Ticket Booking");
        //--------------------------Select Movie------------------------------
        System.out.println("Available movies are listed below");
        for (Movie m : movies) {
            if (m.getShowingStatus().equals(Status.NOW_SHOWING) || m.getShowingStatus().equals(Status.PREVIEW)) {
                System.out.println(m.getTitle());
                available = true;
            }
        }
        if (!available) {
            System.out.println("Nothing available");
            return null;
        }
        Scanner sc = new Scanner(System.in);
        boolean find = false;
        while (find != true) {
            System.out.println("Enter Movie title: ");
            String movieName = sc.nextLine();
            for (Movie m : movies) {
                if (m.getTitle().equals(movieName)) {
                    chosen = m;
                    sel = m.getTitle();
                    find = true;
                    break;
                }
            }
            if (find)
                break;
            System.out.println("Cannot match movie");
        }
        //-------------------------Select Cineplex----------------------------
        ArrayList<MovieSlot> ava = new ArrayList<MovieSlot>();
        ArrayList<Cineplex> avaCpx = new ArrayList<Cineplex>();
        for (MovieSlot ms : slots) {
            if (ms.getMovie().getTitle().equals(sel)) {
                ava.add(ms);
            }
        }
        if (ava.isEmpty()) {
            System.out.println("Nothing available");
            return null;
        }

        for (MovieSlot ms : ava) {
            if (!avaCpx.contains(ms.getCineplex()))
                avaCpx.add(ms.getCineplex());
        }
        System.out.println("Available Cineplexes are listed below");
        for (Cineplex s : avaCpx) {
            System.out.println(s.getName());
        }
        find = false;
        while (find != true) {
            System.out.println("Enter Cineplex name: ");
            String cineplexName = sc.nextLine();
            for (Cineplex s : avaCpx) {
                if (s.getName().equals(cineplexName)) {
                    selectedCineplex = s;
                    find = true;
                    break;
                }
            }
            if (find)
                break;
            System.out.println("Cannot match Cineplex");
        }
        //----------------------Select Time Slot--------------------------------
        ArrayList<MovieSlot> avaTS = new ArrayList<MovieSlot>();
        System.out.println("Available Time Slots:");
        System.out.println("Select according to the choice number:");
        for (MovieSlot ms : ava) {
            if (ms.getCineplex().equals(selectedCineplex)) {
                avaTS.add(ms);
            }
        }
        for (MovieSlot ms : avaTS) {
            System.out.println("Choice " + (avaTS.indexOf(ms) + 1));
            System.out.println("Date: " + ms.getDate());
            System.out.println("Time: " + ms.getTime());
            System.out.println("Cinema: " + ms.getCinema().getCinemaID());
            System.out.println("Cinema Class: " + ms.getCinemaClass());
            System.out.println();
        }
        if (avaTS.isEmpty()) {
            System.out.println("Nothing available");
            return null;
        }
        int ch;
        System.out.println("Enter your selection: ");
        while (true) {
            try {
                ch = Integer.parseInt(sc.nextLine());
                if (1 <= ch && ch <= avaTS.size())
                    break;
                else
                    System.out.println("Bad input! Enter your selection again!");
            } catch (NumberFormatException e) {
                System.out.println("Bad input! Enter your selection again!");
            }
        }

        MovieSlot msFound = avaTS.get(ch - 1);
        System.out.println("Your Choice");
        System.out.println("Date: " + msFound.getDate());
        System.out.println("Time: " + msFound.getTime());
        System.out.println("Cinema: " + msFound.getCinema().getCinemaID());
        System.out.println("Cinema Class: " + msFound.getCinemaClass());
        System.out.println();
        for (MovieSlot ms : slots) {
            System.out.println("Looking slot in Library");
            if (ms.getMovie().getMovieID() == msFound.getMovie().getMovieID() &&
                    ms.getCineplex().getID() == msFound.getCineplex().getID() &&
                    ms.getTime().equals(msFound.getTime()) &&
                    ms.getCinema().getCinemaID() == msFound.getCinema().getCinemaID() &&
                    ms.getDate().equals(msFound.getDate())) {
                System.out.println("found slot!");
                System.out.println("Date: " + ms.getDate());
                System.out.println("Time: " + ms.getTime());
                System.out.println("Cinema: " + ms.getCinema().getCinemaID());
                System.out.println("Cinema Class: " + ms.getCinemaClass());
                while (true) {
                    System.out.println("Choose your seat");
                    Seatprinter.printSeat(ms.getCinemaSeats());
                    System.out.println("Input the row number you selected (Capital Letter)");
                    char row = sc.nextLine().charAt(0);
                    System.out.println("Input the column number you selected (integer)");
                    int col = Integer.parseInt(sc.nextLine());
                    int rowNum = (int) row - 65;
                    System.out.println((col - 1) + " " + rowNum);
                    if (!ms.getCinemaSeats()[rowNum][col - 1].isBooked()) {
                        //System.out.println(ms.getCinemaSeats()[rowNum][col-1]);
                        ms.bookSeat(rowNum, col - 1);
                        ms.getMovie().saleOneMore();
                        if (cat == 2) {
                            SeniorTicket ts = new SeniorTicket(chosen, ms.getCinemaSeats()[rowNum][col - 1], selectedCineplex, ms.getCinema(), ms.getDate(), ms);
                            System.out.println("The price is: " + ts.getPrice());
                            System.out.println("Would you like to complete the payment? Input Y/N");
                            char input = sc.nextLine().charAt(0);
                            if (input == 'Y') {
                                ts.printTicket();
                                return ts;
                            } else
                                return null;
                        } else if (cat == 3) {
                            ChildTicket tc = new ChildTicket(chosen, ms.getCinemaSeats()[rowNum][col - 1], selectedCineplex, ms.getCinema(), ms.getDate(), ms);
                            System.out.println("The price is: " + tc.getPrice());
                            System.out.println("Would you like to complete the payment? Input Y/N");
                            char input = sc.nextLine().charAt(0);
                            if (input == 'Y') {
                                tc.printTicket();
                                return tc;
                            } else
                                return null;
                        } else {
                            Ticket ta = new Ticket(chosen, ms.getCinemaSeats()[rowNum][col - 1], selectedCineplex, ms.getCinema(), ms.getDate(), ms);
                            System.out.println("The price is: " + ta.getPrice());
                            System.out.println("Would you like to complete the payment? Input Y/N");
                            char input = sc.nextLine().charAt(0);
                            if (input == 'Y') {
                                ta.printTicket();
                                return ta;
                            } else
                                return null;
                        }
                    } else
                        System.out.println("The seat you have chosen is occupied!");
                }
            }
        }
        return null;

    }

}
