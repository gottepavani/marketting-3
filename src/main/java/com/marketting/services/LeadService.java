package com.marketting.services;

import java.util.List;

import com.marketting.entites.Lead;

public interface LeadService {
	public void saveLead(Lead lead);
	List<Lead> findAllLeads();
	public void deleteLead(long id);
	public Lead findLeadById(long id);

}
