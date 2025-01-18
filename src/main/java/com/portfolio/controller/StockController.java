package com.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.entity.Stock;
import com.portfolio.service.StockPriceService;
import com.portfolio.service.StockService;

@RestController
@RequestMapping("/api/stocks")
@CrossOrigin(origins = "http://localhost:3001") // Allow React app origin

public class StockController {
	@Autowired
	private StockService stockService;

	@Autowired
	private StockPriceService stockPriceService;

	@GetMapping("/price/{ticker}")
	public double getStockPrice(@PathVariable String ticker) {
		return stockPriceService.getStockPrice(ticker);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
		Stock stock = stockService.getStockById(id);
		return ResponseEntity.ok(stock);
	}

	@GetMapping
	public List<Stock> getAllStocks() {
		return stockService.getAllStocks();
	}

	@PostMapping
	public Stock addStock(@RequestBody Stock stock) {
		return stockService.addStock(stock);
	}

	@PutMapping("/{id}") // Removed `/u` for clarity
	public Stock updateStock(@PathVariable Long id, @RequestBody Stock stock) {
		return stockService.updateStock(id, stock);
	}

	@DeleteMapping("/{id}") // Removed `/d` for clarity
	public ResponseEntity<String> deleteStock(@PathVariable Long id) {
		stockService.deleteStock(id);
		return ResponseEntity.ok("Stock deleted successfully.");
	}
}
