import com.google.gson.Gson;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
//Invoca a la API ExangeRate para convertir una moneda base en otra
public class CurrencyConverterService {

    private static final String API_KEY = "ee8481bd11fcd60534813319";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final HttpClient client;
    private final Gson gson;

    public CurrencyConverterService() {
        this.client = createUnsafeHttpClient();
        this.gson = new Gson();
    }

    private HttpClient createUnsafeHttpClient() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new SecureRandom());

            return HttpClient.newBuilder()
                    .sslContext(sslContext)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("No se pudo crear el cliente HTTP inseguro", e);
        }
    }

    public List<CurrencyCode> getSupportedCurrencies() throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "/codes";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        SupportedCodesResponse supported = gson.fromJson(response.body(), SupportedCodesResponse.class);

        List<CurrencyCode> currencyList = new ArrayList<>();
        for (List<String> codePair : supported.supported_codes()) {
            currencyList.add(new CurrencyCode(codePair.get(0), codePair.get(1)));
        }
        return currencyList;
    }

    public ConversionResponse convert(String from, String to, double amount) throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "/pair/" + from + "/" + to + "/" + amount;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return gson.fromJson(response.body(), ConversionResponse.class);
    }
}