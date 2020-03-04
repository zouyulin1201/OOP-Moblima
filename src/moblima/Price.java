package moblima;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Price {
    public static Map<String, Double> priceMap = new HashMap<String, Double>();
    private static ArrayList<LocalDate> holiday = new ArrayList<LocalDate>();

    public static ArrayList<LocalDate> getHoliday() {
        return holiday;
    }

    public static void setHoliday(ArrayList<LocalDate> holiday) {
        Price.holiday = holiday;
    }

    public static void addHoliday(LocalDate addHoliday) {
        Price.holiday.add(addHoliday);
    }

    public static void editPriceMap(String type, double price) {
        priceMap.replace(type, price);
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("price.dat");
            out = new ObjectOutputStream(fos);
            out.writeObject(priceMap);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(priceMap);
    }

    public static void setPriceMap() {
        priceMap.put("WeekdayRegularStandard", 9.00);
        priceMap.put("WeekendRegularStandard", 11.00);
        priceMap.put("WeekdayThreeDimensionStandard", 11.00);
        priceMap.put("WeekendThreeDimensionStandard", 15.00);
        priceMap.put("WeekdayRegularPlatinum", 15.00);
        priceMap.put("WeekendRegularPlatinum", 20.00);
        priceMap.put("WeekdayThreeDimensionPlatinum", 20.00);
        priceMap.put("WeekendThreeDimensionPlatinum", 25.00);
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("price.dat");
            out = new ObjectOutputStream(fos);
            out.writeObject(priceMap);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //System.out.println(priceMap);
    }

    public static void readPriceMap() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        priceMap = new HashMap<String, Double>();
        try {
            fis = new FileInputStream("price.dat");
            in = new ObjectInputStream(fis);
            priceMap = (Map<String, Double>) in.readObject();
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        for (String s : priceMap.keySet()) {
            System.out.println(s);
        }
    }

    public static double calPrice(Ticket ticket) {
        double discount = 1;
        if (holiday.contains(ticket.getDate())) {
            discount = 1.5;
        }
        if (ticket.getCinema().getCinemaclass() == CinemaClass.STANDARD) {
            //Senior Citizen and Students are not finish
            DayOfWeek day = ticket.getDate().getDayOfWeek();
            if (ticket.getMovieChosen().getMovieType() == MovieType.Regular) {
                switch (day) {
                    case MONDAY:
                    case TUESDAY:
                    case WEDNESDAY:
                    case THURSDAY:
                        return priceMap.get("WeekdayRegularStandard") * discount;
                    case FRIDAY: {
                        if (Integer.parseInt(ticket.getSlot().getTime().substring(0, 2)) < 18) {
                            System.out.println("Before Friday Evening");//should be time of movie
                            return priceMap.get("WeekdayRegularStandard") * discount;
                        } else
                            return priceMap.get("WeekendRegularStandard") * discount;
                    }
                    case SATURDAY:
                    case SUNDAY:
                        return priceMap.get("WeekendRegularStandard") * discount;
                }
            }
            if (ticket.getMovieChosen().getMovieType() == MovieType.ThreeDimension) {

                switch (day) {
                    case MONDAY:
                    case TUESDAY:
                    case WEDNESDAY:
                    case THURSDAY:
                        return priceMap.get("WeekdayThreeDimensionStandard") * discount;
                    case FRIDAY: {


                        if (Integer.parseInt(ticket.getSlot().getTime().substring(0, 2)) < 18)//should be time of movie
                            return priceMap.get("WeekdayThreeDimensionStandard") * discount;
                        else
                            return priceMap.get("WeekendThreeDimensionStandard") * discount;
                    }
                    case SATURDAY:
                    case SUNDAY:
                        return priceMap.get("WeekendThreeDimensionStandard") * discount;
                }
            }

        } else {
            DayOfWeek day = ticket.getDate().getDayOfWeek();
            if (ticket.getMovieChosen().getMovieType() == MovieType.Regular) {
                switch (day) {
                    case MONDAY:
                    case TUESDAY:
                    case WEDNESDAY:
                    case THURSDAY:
                        return priceMap.get("WeekdayRegularPlatinum") * discount;
                    case FRIDAY: {
                        //System.out.println(Integer.parseInt(ticket.getSlot().getTime().substring(0,2)));
                        if (Integer.parseInt(ticket.getSlot().getTime().substring(0, 2)) < 18)//should be time of movie
                            return priceMap.get("WeekdayRegularPlatinum") * discount;
                        else
                            return priceMap.get("WeekendRegularPlatinum") * discount;
                    }
                    case SATURDAY:
                    case SUNDAY:
                        return priceMap.get("WeekendRegularPlatinum") * discount;
                }
            }
            if (ticket.getMovieChosen().getMovieType() == MovieType.ThreeDimension) {

                switch (day) {
                    case MONDAY:
                    case TUESDAY:
                    case WEDNESDAY:
                    case THURSDAY:
                        return priceMap.get("WeekdayThreeDimensionPlatinum") * discount;
                    case FRIDAY: {
                        if (Integer.parseInt(ticket.getSlot().getTime().substring(0, 2)) < 18)//should be time of movie
                            return priceMap.get("WeekdayThreeDimensionPlatinum") * discount;
                        else
                            return priceMap.get("WeekendThreeDimensionPlatinum") * discount;
                    }
                    case SATURDAY:
                    case SUNDAY:
                        return priceMap.get("WeekendThreeDimensionPlatinum") * discount;
                }
            }


        }
        return 0;
    }
}
