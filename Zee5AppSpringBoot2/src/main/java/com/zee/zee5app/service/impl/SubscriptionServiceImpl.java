package com.zee.zee5app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zee.zee5app.dto.Subscription;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.RecordExistsException;
import com.zee.zee5app.repository.SubscriptionRepository;
import com.zee.zee5app.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Override
	public String addSubscription(Subscription subscription) throws RecordExistsException {
		if(this.subscriptionRepository.existsById(subscription.getId()))
			throw new RecordExistsException("Subscription Id exists");
		return (this.subscriptionRepository.save(subscription) != null) ? "success" : "fail";
	}

	@Override
	public String updateSubscriptionById(String id, Subscription subscription) throws IdNotFoundException {
		if (!this.subscriptionRepository.existsById(id))
			throw new IdNotFoundException("Invalid Id");
		return (this.subscriptionRepository.save(subscription) != null) ? "success" : "fail";
	}

	@Override
	public String deleteSubscriptionById(String id) throws IdNotFoundException {
		if (!this.subscriptionRepository.existsById(id))
			throw new IdNotFoundException("Invalid Id");
		this.subscriptionRepository.deleteById(id);
		return "success";
	}

	@Override
	public Optional<Subscription> getSubscriptionById(String id) throws IdNotFoundException {
		Optional<Subscription> subscription = this.subscriptionRepository.findById(id);
		if (subscription.isEmpty())
			throw new IdNotFoundException("Invalid Id");
		return subscription;
	}

	@Override
	public List<Subscription> getAllSubscriptionsList() {
		return this.subscriptionRepository.findAll();
	}

	@Override
	public Subscription[] getAllSubscriptions() {
		List<Subscription> subscriptions = this.subscriptionRepository.findAll();
		return subscriptions.toArray(new Subscription[subscriptions.size()]);
	}

}