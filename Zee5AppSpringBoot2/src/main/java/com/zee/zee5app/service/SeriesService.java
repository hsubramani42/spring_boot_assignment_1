package com.zee.zee5app.service;

import java.util.List;
import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.RecordExistsException;

public interface SeriesService {
	public String addSeries(Series series) throws RecordExistsException;

	public String updateSeriesById(String id, Series series) throws IdNotFoundException;

	public String deleteSeriesById(String id) throws IdNotFoundException;

	public Optional<Series> getSeriesById(String id) throws IdNotFoundException;

	public List<Series> getAllSeriesList();

	public Series[] getAllSeries();
}
