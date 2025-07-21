public class Movie implements Content, Comparable<Movie> {
    private String title;
    private String image;
    private String year;
    private String rating;

    public Movie(String title, String image, String year, String rating) {
        this.title = title;
        this.image = image;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String title() { return title; }

    @Override
    public String image() { return image; }

    @Override
    public String year() { return year; }

    @Override
    public String rating() { return rating; }

    @Override
    public String type() { return "Movie"; }

    @Override
    public int compareTo(Movie outro) {
        // Ordenar por rating (descendente)
        return Double.compare(Double.parseDouble(outro.rating), Double.parseDouble(this.rating));
    }

    @Override
    public String toString() {
        return title + " (" + year + ") - Rating: " + rating;
    }
}
