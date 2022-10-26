package org.example;

import org.example.interfaces.URLFetcherInterface;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLFetcher implements URLFetcherInterface {
    URL url;

    public URLFetcher(URL url) {
        this.url = url;
    }

    public String fetch() throws IOException {
		URLConnection connection = url.openConnection();
		
		StringBuilder contentBuffer = new StringBuilder();

        try (InputStream input = connection.getInputStream()) {
            int c;
            while ((c = input.read()) >= 0) {
                contentBuffer.append((char) c);
            }
        }
	
		return contentBuffer.toString();
	}
}