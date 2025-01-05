package hu.strahl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrawlerUtil {

    private static HashMap<String, String> requestHeaders;

    private static void initRequestHeaders() {
        requestHeaders = new HashMap<>();
        requestHeaders.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
        requestHeaders.put("Accept-Language", "hu-HU,hu;q=0.8,en-US;q=0.5,en;q=0.3");
        requestHeaders.put("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/115.0");
    }

    private static Integer randInt(Integer max) {
        return (int) Math.round(Math.random() * max);
    }

    public static InputStream readWebpageInputStream(String urlString) throws IOException {
        initRequestHeaders();

        @SuppressWarnings("deprecation")
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        requestHeaders.forEach(conn::setRequestProperty);

        int status = conn.getResponseCode();
        System.out.printf("%s Connection status: %d - %s\n", urlString, status, conn.getResponseMessage());

        return conn.getInputStream();
    }

    public static String readWebpage(String urlString)  {
        StringBuilder builder = new StringBuilder();
        InputStream inputStream;

        try {
            inputStream = readWebpageInputStream(urlString);
            if (inputStream != null ) { // OK
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String inputLine = reader.readLine();
                while (inputLine != null) {
                    builder.append(inputLine);
                    inputLine = reader.readLine();
                }
            }
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }

        return builder.toString();
    }

    public static List<String> findAllEbayUrls(String html, String linkType) {
        String regex = String.format("\"http[^\"]+ebay.com/%s/[^\"]+\"", linkType);
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(html);
        List<String> urlList = new ArrayList<>();
        while (matcher.find()) {
            String url = matcher.group().replace("\"","");
            urlList.add(url);
        }
        return urlList;
    }

    public static String findRandomEbayItem() {
        String url = "https://ebay.com";
        String html = readWebpage(url);
        List<String> categoryUrls = findAllEbayUrls(html, "b");
        List<String> itemUrls = findAllEbayUrls(html, "itm");
        int cycles = 0;
        while (itemUrls.isEmpty() && cycles < 1000) {
            if (!categoryUrls.isEmpty()) {
                int r = randInt(categoryUrls.size());
                url = categoryUrls.get(r);
                html = readWebpage(url);
                categoryUrls = findAllEbayUrls(html, "b");
                itemUrls = findAllEbayUrls(html, "itm");
            }
            else {
                return null; // no link found
            }
            ++cycles; // make sure to avoid infinite loops!
        }
        // found some items
        int r = randInt(itemUrls.size());
        url = itemUrls.get(r);
        //html = readWebpage(url);

        return url;
    }

}
