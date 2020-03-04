package moblima;

import java.time.LocalDate;

public class ChildTicket extends Ticket {
    private double discountRatio;
    private double discountedPrice;

    public ChildTicket() {
        // TODO Auto-generated constructor stub
        super();
        discountRatio = 0.9;
        discountedPrice = -1; //sentinal value
    }

    public ChildTicket(Movie movieChosen, Seat seat, Cineplex cineplex, Cinema cinema, LocalDate date, MovieSlot slot) {
        super(movieChosen, seat, cineplex, cinema, date, slot);
        //this.price = discountRatio*Price.calPrice(this);
        // TODO Auto-generated constructor stub
        discountRatio = 0.8;
        discountedPrice = -1;
    }

    @Override
    public double getPrice() {
        //double discountedPrice;
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
        System.out.println("Child Ticket");
        super.printTicket();
    }


}
