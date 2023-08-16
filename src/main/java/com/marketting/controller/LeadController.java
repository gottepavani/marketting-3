package com.marketting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marketting.dto.LeadDto;
import com.marketting.entites.Lead;
import com.marketting.services.LeadService;
import com.marketting.util.EmailService;

@Controller
public class LeadController {
	@Autowired
	private LeadService leadService;
	
	@Autowired
    private EmailService emailService;
	
	@RequestMapping("/viewCreateLead")
	public String viewCreateLeadForm()
	{
		return "create_registration";
	}

//@RequestMapping("/saveLead")
//public String saveOneLead(@ModelAttribute("lead")Lead lead,ModelMap model)
//{
//	leadService.saveLead(lead);
//	model.addAttribute("msg"," Record is saved");
//	return "create_registration";
//}
//	@RequestMapping("/saveLead")
//	public String saveOneLead(@RequestParam("firstName") String firstName,@RequestParam("lastName")String lastName,@RequestParam("email")String email,@RequestParam("mobile")int mobile)
//	
//	{
//		Lead l=new Lead();
//		l.setFirstName(firstName);
//		l.setLastName(lastName);
//		l.setEmail(email);
//		l.setMobile(mobile);
//		leadService.saveLead(l);  
//     return "create_registration";
//	}

	@RequestMapping("/saveLead")
	public String saveOneLead(LeadDto leadDto,ModelMap model)
	{
	    Lead l=new Lead();
		l.setFirstName(leadDto.getFirstName());
	    l.setLastName(leadDto.getLastName());
	    l.setEmail(leadDto.getEmail());
	    l.setMobile(leadDto.getMobile());
	    emailService.sendEmail(leadDto.getEmail(),"Test","welcome");
	    leadService.saveLead(l);
		model.addAttribute("msg"," Record is saved");
		return "create_registration";
	}
	@RequestMapping("/listLeads")
	public String getAllLeads(Model model)
	{
		List<Lead> leads = leadService.findAllLeads();
		model.addAttribute("leads", leads);
		return"list_leads";
	}
	@RequestMapping("/delete")
	public String deleteOneLead(@RequestParam("id")long id,Model model)
	{
	leadService.deleteLead(id);
	List<Lead> leads = leadService.findAllLeads();
	model.addAttribute("leads", leads);
	return"list_leads";
	}
	@RequestMapping("/update")
	public String updateOneLead(@RequestParam("id")long id,Model model)
	{
	Lead lead = leadService.findLeadById(id);
	model.addAttribute("lead", lead);
	return"update_leads";
	}
	
}
