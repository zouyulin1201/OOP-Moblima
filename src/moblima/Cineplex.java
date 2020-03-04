package moblima;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable {
    private String Name;
    private int ID;
    private ArrayList<Cinema> cinemas;
    private ArrayList<MovieSlot> slots;

    public Cineplex(String name, int iD) {
        super();
        Name = name;
        ID = iD;
        this.cinemas = new ArrayList<Cinema>();
        this.slots = new ArrayList<MovieSlot>();
    }

    public Cineplex() {
        // TODO Auto-generated constructor stub
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(ArrayList<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public void addCinemas(Cinema cinema) {
        this.cinemas.add(cinema);
        for (MovieSlot mv : cinema.getMovieSlots()) {
            this.slots.add(mv);
        }
    }

    public ArrayList<MovieSlot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<MovieSlot> slots) {
        this.slots = slots;
    }

    public void addSlots(MovieSlot slot) {
        this.slots.add(slot);
    }


}
