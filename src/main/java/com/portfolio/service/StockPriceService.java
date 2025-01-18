package com.portfolio.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockPriceService {

	@Value("${stock.api.key}")
	private String apiKey;

	@Value("${stock.api.url}")
	private String apiUrl;

	private final RestTemplate restTemplate = new RestTemplate();

	public double getStockPrice(String ticker) {
		try {
			String url = apiUrl + "?function=GLOBAL_QUOTE&symbol=" + ticker + "&apikey=" + apiKey;
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode root = objectMapper.readTree(response.getBody());
			JsonNode globalQuote = root.path("Global Quote");

			if (globalQuote.isMissingNode() || globalQuote.path("05. price").asText().isEmpty()) {
				throw new RuntimeException("Failed to fetch stock price for ticker: " + ticker);
			}

			return globalQuote.path("05. price").asDouble();
		} catch (Exception e) {
			throw new RuntimeException("Error fetching stock price: " + e.getMessage());
		}
	}
}
