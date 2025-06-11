import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String apiKey = "API_KEY_AQUI";
        String url = "https://imdb-api.com/en/API/Top250Movies/" + apiKey;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();

        String jsonMovies = json.substring(json.indexOf("[") + 1, json.lastIndexOf("]"));
        String[] moviesArray = jsonMovies.split("},\\{");

        List<String> titles = parseAttribute(moviesArray, "\"title\":\"");
        List<String> images = parseAttribute(moviesArray, "\"image\":\"");
        List<String> years = parseAttribute(moviesArray, "\"year\":\"");
        List<String> ratings = parseAttribute(moviesArray, "\"imDbRating\":\"");

        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            movies.add(new Movie(titles.get(i), images.get(i), years.get(i), ratings.get(i)));
        }

        Collections.sort(movies);
        System.out.println("\nOrdenados por nota:");
        movies.forEach(System.out::println);

        movies.sort(Comparator.comparing(Movie::title));
        System.out.println("\nOrdenados por título:");
        movies.forEach(System.out::println);

        movies.sort(Comparator.comparing(Movie::year));
        System.out.println("\nOrdenados por ano:");
        movies.forEach(System.out::println);
    }

    private static List<String> parseAttribute(String[] moviesArray, String attributeKey) {
        List<String> result = new ArrayList<>();
        for (String movie : moviesArray) {
            int start = movie.indexOf(attributeKey);
            if (start != -1) {
                start += attributeKey.length();
                int end = movie.indexOf("\"", start);
                if (end != -1) {
                    String value = movie.substring(start, end);
                    result.add(value);
                }
            }
        }
        return result;
    }
}
