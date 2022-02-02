package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Episode;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.repository.EpisodeRepository;
import com.zee.zee5app.service.EpisodeService;

@Service
public class EpisodeServiceImpl implements EpisodeService {

	@Autowired
	private EpisodeRepository episodeRepository;

	@Override
	public String addEpisode(Episode episode) {
		return (this.episodeRepository.save(episode) != null) ? "success" : "fail";
	}

	@Override
	public String updateEpisodeById(String id, Episode episode) throws IdNotFoundException {
		if (!this.episodeRepository.existsById(id))
			throw new IdNotFoundException("Invalid Id");
		return (this.episodeRepository.save(episode) != null) ? "success" : "fail";
	}

	@Override
	public String deleteEpisodeById(String id) throws IdNotFoundException {
		if (!this.episodeRepository.existsById(id))
			throw new IdNotFoundException("Invalid Id");
		this.episodeRepository.deleteById(id);
		return "success";
	}

	@Override
	public Optional<Episode> getEpisodeById(String id) throws IdNotFoundException {
		Optional<Episode> episode = this.episodeRepository.findById(id);
		if (episode.isEmpty())
			throw new IdNotFoundException("Invalid Id");
		return episode;
	}

	@Override
	public List<Episode> getAllEpisodeList() {
		return this.episodeRepository.findAll();
	}

	@Override
	public Episode[] getAllEpisode() {
		List<Episode> episodes = this.episodeRepository.findAll();
		return episodes.toArray(new Episode[episodes.size()]);
	}

}
