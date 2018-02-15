package com.SDP.Controllers;

import com.SDP.Models.Domains;
import com.SDP.Models.Employees;
import com.SDP.Models.Scores;
import com.SDP.Repositories.DomainsRepository;
import com.SDP.Repositories.ScoresRepository;
import com.SDP.Repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/req") // This means URL's start with /XXX (after Application path)
public class MainController {
    @Autowired
	private EmployeesRepository userRepository;
    @Autowired
    private DomainsRepository domainRepository;
    @Autowired
    private ScoresRepository scoreRepository;



	//@GetMapping(path="/add") // Map ONLY GET Requests
	//public @ResponseBody String addNewUser (@RequestParam String name
	//		, @RequestParam String email) {
	//	// @ResponseBody means the returned String is the response, not a view name
	//	// @RequestParam means it is a parameter from the GET or POST request
//
	//	Employees n = new Employees();
	//	n.setName(name);
	//	n.setLastname(email);
	//	userRepository.save(n);
	//	return "Saved";
	//}

	@GetMapping(path="/allusers")
	public @ResponseBody Iterable<Employees> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}

	@GetMapping(path="/alldomains")
    public @ResponseBody Iterable<Domains> getAllDomains(){
	    return domainRepository.findAll();
    }

    @GetMapping(path="/allscores")
    public @ResponseBody Iterable<Scores> getAllScores(){
        return scoreRepository.findAll();
    }

    @GetMapping(path="/userbyid")
	public @ResponseBody Iterable<Employees> getUsersById(@RequestParam int id){
    	return userRepository.findById(id);

	}

	@GetMapping(path="/domainsbyid")
	public @ResponseBody Iterable<Domains> getDomainsById(@RequestParam int id){
		return domainRepository.findById(id);
	}



}