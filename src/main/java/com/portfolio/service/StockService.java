package com.portfolio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.entity.Stock;
import com.portfolio.repository.StockRepository;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;

	public List<Stock> getAllStocks() {
		return stockRepository.findAll();
	}

	public Stock getStockById(Long id) {
		return stockRepository.findById(id).orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));
	}

	public Stock addStock(Stock stock) {
		return stockRepository.save(stock);
	}

	public Stock updateStock(Long id, Stock stockDetails) {
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));

		stock.setStockName(stockDetails.getStockName());
		stock.setTicker(stockDetails.getTicker());
		stock.setQuantity(stockDetails.getQuantity());
		stock.setBuyPrice(stockDetails.getBuyPrice());

		return stockRepository.save(stock);
	}

	public void deleteStock(Long id) {
		Stock stock = stockRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Stock not found with id: " + id));
		stockRepository.delete(stock);
	}
}
