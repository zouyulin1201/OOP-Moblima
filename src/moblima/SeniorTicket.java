package moblima;

import java.time.LocalDate;

public class SeniorTicket extends Ticket {
    private double discountRatio;
    private double discountedPrice;

    public SeniorTicket() {
        // TODO Auto-generated constructor stub
        super();
        discountRatio = 0.8;
        discountedPrice = -1; //sentinal value
    }

    public SeniorTicket(Movie movieChosen, Seat seat, Cineplex cineplex, Cinema cinema, LocalDate date, MovieSlot slot) {
        super(movieChosen, seat, cineplex, cinema, date, slot);
        // TODO Auto-generated constructor stub
        //this.price = discountRatio*Price.calPrice(this);
        discountRatio = 0.6;
        discountedPrice = -1;
    }

    @Override
    public double getPrice() {
        if (discountedPrice == -1) {
            discountedPrice = super.getPrice() * discountRatio;
            discountedPrice = Math.round(discountedPrice * 100);
            discountedPrice /= 100;
        }
        //super.setPrice(basicPrice*discountRatio);
        return discountedPrice;

    }

    public double getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(double discountRatio) {
        this.discountRatio = discountRatio;
    }

    @Override
    public void printTicket() {
        System.out.println("Senior Ticket");
        //super.printTicket();
        System.out.println("--------------RECEIPT--------------");
        System.out.println(this.getCineplex().getName());
        System.out.println("Projection Date: " + this.getDate().toString() + "	" + this.getDate().getDayOfWeek().toString());
        System.out.println("Projection Time: " + this.getSlot().getTime());
        System.out.println(this.getMovieChosen().getTitle());
        System.out.println(this.getMovieChosen().getMovieType());
        System.out.println("Cinema Class: " + this.getCinema().getCinemaclass().toString());
        System.out.println("Cinema:	" + (this.getCinema().getCinemaID() + 1) +
                "	Seat: " +
                (char) (this.getSeat().getRow() + 65) + " " +
                (this.getSeat().getCol() + 1));
        System.out.println();
        System.out.println("Price: " + this.getPrice());
        System.out.println("Paid by:         UOB CREDIT CARD");
        System.out.println();

        System.out.println("Date" + "	" + LocalDate.now());
        System.out.println("Transaction Number :  " + this.getTransactionID());

        System.out.println();
        System.out.println("THANK YOU, PLEASE COME AGAIN\n" +
                "Prices Inclusive of 7% GST\n");
    }


}
