package com.raimundo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class APIDriver {

      public static CurrencyConversionInfoDTO convert(String currency1, String currency2) {
            Gson gson = new Gson();

            URI uriString = URI.create("https://v6.exchangerate-api.com/v6/316652e3a2469899b709b0e0/pair/" + currency1
                        + "/" + currency2);
            HttpRequest request = HttpRequest.newBuilder()
                        .uri(uriString)
                        .build();

            HttpClient client = HttpClient.newHttpClient();

            HttpResponse<String> response = client.sendAsync(request, BodyHandlers.ofString()).join();
            CurrencyConversionInfoDTO currencyInfo = gson.fromJson(response.body(), CurrencyConversionInfoDTO.class);

            return currencyInfo;
      }

}
