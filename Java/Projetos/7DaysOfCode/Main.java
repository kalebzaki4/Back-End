import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

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

        System.out.println("\nTítulos:");
        titles.forEach(System.out::println);

        System.out.println("\nImagens:");
        images.forEach(System.out::println);

        System.out.println("\nAnos:");
        years.forEach(System.out::println);

        System.out.println("\nNotas:");
        ratings.forEach(System.out::println);
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
