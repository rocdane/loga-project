package vendor.http;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServlet;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class HttpRequestHelper {

    private String uri;
    private String method;
    private Map<String, Object> parameters;
    private HttpURLConnection httpURLConnection;

    public HttpRequestHelper(String uri, String method, Map<String, Object> parameters) throws IOException {
        this.uri = uri;
        this.method = method;
        this.parameters = new HashMap<>();

        URL url = new URL(uri);
        this.httpURLConnection = (HttpURLConnection) url.openConnection();
        this.httpURLConnection.setRequestMethod(method);
        this.httpURLConnection.setRequestProperty("Content-Type", "application/json");
        this.httpURLConnection.setConnectTimeout(5000);
        this.httpURLConnection.setReadTimeout(5000);
        this.buildRequestParameter(parameters);
    }

    private String buildRequestParameter(Map<String, Object> params) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;

    }

    public String process() throws IOException {

        StringBuilder fullResponseBuilder = new StringBuilder();

        StringBuffer content = new StringBuffer();

        BufferedReader bufferedReader = null;

        Reader streamReader = null;

        String inputLine;

        int status = httpURLConnection.getResponseCode();

        if(status==200){
            bufferedReader = new BufferedReader( new InputStreamReader(httpURLConnection.getInputStream()));
            while ((inputLine = bufferedReader.readLine()) != null) {
                content.append(inputLine);
            }
            bufferedReader.close();
        }else if (status > 299) {
            streamReader = new InputStreamReader(httpURLConnection.getErrorStream());

            while ((inputLine = streamReader.toString()) != null) {
                content.append(inputLine);
            }

            streamReader.close();
        } else {
            streamReader = new InputStreamReader(httpURLConnection.getInputStream());

            while ((inputLine = streamReader.toString()) != null) {
                content.append(inputLine);
            }
        }

        fullResponseBuilder.append(httpURLConnection.getResponseCode())
                .append(" ")
                .append(httpURLConnection.getResponseMessage())
                .append("\n");

        httpURLConnection.getHeaderFields().entrySet().stream()
                .filter(entry -> entry.getKey() != null)
                .forEach(entry -> {
                    fullResponseBuilder.append(entry.getKey()).append(": ");
                    List<String> headerValues = entry.getValue();
                    Iterator<String> it = headerValues.iterator();
                    if (it.hasNext()) {
                        fullResponseBuilder.append(it.next());
                        while (it.hasNext()) {
                            fullResponseBuilder.append(", ").append(it.next());
                        }
                    }
                    fullResponseBuilder.append("\n");
                });

        httpURLConnection.disconnect();

        return fullResponseBuilder.toString();
    }
}
