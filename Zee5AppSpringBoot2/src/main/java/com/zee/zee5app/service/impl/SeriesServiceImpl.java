package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.SeriesRepository;
import com.zee.zee5app.service.SeriesService;

@Service
public class SeriesServiceImpl implements SeriesService {
	@Autowired
	private SeriesRepository seriesRepository;

	@Override
	public String addSeries(Series series) {
		return (this.seriesRepository.save(series) != null) ? "success" : "fail";
	}

	@Override
	public String updateSeriesById(String id, Series series) throws IdNotFoundException {
		if (!this.seriesRepository.existsById(id))
			throw new IdNotFoundException("Invalid Id");
		return (this.seriesRepository.save(series) != null) ? "success" : "fail";

	}

	@Override
	public String deleteSeriesById(String id) throws IdNotFoundException {
		if (!this.seriesRepository.existsById(id))
			throw new IdNotFoundException("Invalid Id");
		this.seriesRepository.deleteById(id);
		return "success";
	}

	@Override
	public Optional<Series> getSeriesById(String id) throws IdNotFoundException {
		Optional<Series> series = this.seriesRepository.findById(id);
		if (series.isEmpty())
			throw new IdNotFoundException("Invalid Id");
		return series;
	}

	@Override
	public List<Series> getAllSeriesList() {
		return this.seriesRepository.findAll();
	}

	@Override
	public Series[] getAllSeries() {
		List<Series> seriess = this.seriesRepository.findAll();
		return seriess.toArray(new Series[seriess.size()]);
	}

}
