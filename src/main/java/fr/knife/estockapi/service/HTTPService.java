package fr.knife.estockapi.service;

import fr.knife.estockapi.exception.InternalServerException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * The service to manage HTTP operations
 */
@Service
public class HTTPService {
    /**
     * The HTTP client to send HTTP requests
     */
    private final HttpClient httpClient;

    /**
     * Create a new instance
     */
    public HTTPService() {
        this.httpClient = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
    }

    /**
     * Fetch the conteant at the specified URL
     *
     * @param url The URL
     *
     * @return A future that contains the response
     */
    @SuppressWarnings("java:S2142")
    public String fetch(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(String.format(url))).GET().build();

            return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new InternalServerException(e.getMessage());
        }
    }
}
