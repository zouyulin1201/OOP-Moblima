package moblima;

import java.util.ArrayList;
import java.util.Collections;

public class Sort {

    public static ArrayList<Movie> sortByRate(ArrayList<Movie> givenMovies) {
        // TODO Auto-generated constructor stub
        ArrayList<Movie> movies = new ArrayList<Movie>(givenMovies);

        ///bubble sort is used to save on swapping operation
        Collections.sort(movies, new SortbyScore());
        Collections.reverse(movies);

        return movies;
    }

    public static ArrayList<Movie> sortBySale(ArrayList<Movie> givenMovies) {
        // TODO Auto-generated constructor stub
        ArrayList<Movie> movies = new ArrayList<Movie>(givenMovies);

        ///bubble sort is used to save on swapping operation
        Collections.sort(movies, new SortbySales());
        Collections.reverse(movies);

        return movies;

    }
}