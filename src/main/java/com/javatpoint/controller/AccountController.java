package com.javatpoint.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.javatpoint.model.Accounts;
import com.javatpoint.service.AccountsService;

/*
 * mark class as Controller
 */
@RestController
public class AccountController {

	@Autowired
	AccountsService accountService;

	/*
	 * creating a get mapping that retrieves all the accounts detail from the
	 * database
	 */
	@GetMapping("/account")
	private ResponseEntity<List<Accounts>> getAllAccounts() {
		return (ResponseEntity<List<Accounts>>) accountService.getAllAccounts();
	}

	/*
	 * creating a get mapping that retrieves the detail of a specific account
	 */
	@GetMapping("/account/{accoountid}")
	private Accounts getAccounts(@PathVariable("accoountid") String accoountid) {
		return accountService.getAccountsById(accoountid);
	}

	/*
	 * creating a delete mapping that deletes a specified account
	 */
	@DeleteMapping("/account/{accoountid}")
	private void deleteAccount(@PathVariable("accoountid") String accoountid) {
		accountService.delete(accoountid);
	}

	/*
	 * creating post mapping that post the account detail in the database
	 */
	@PostMapping("/accounts")
	private String processAccountOpening(@RequestBody Accounts accounts) {
		accountService.saveAccount(accounts);
		return accounts.getIdentification();
	}

	/*
	 * creating put mapping that updates the account detail
	 */
	@PutMapping("/accounts/{monthdate}")
	private ResponseEntity<List<Accounts>> processAccountEndOfDayBalances(@PathVariable("monthdate") String monthdate) {

		Date date = dateParser(monthdate);

		return accountService.saveOrUpdateAccounts(date);
	}

	/**
	 * return the account with calculation of monthly interest based on number of
	 * days from the account opened.
	 * 
	 * @param identification
	 * @param monthdate
	 * @return
	 */
	@PutMapping("/account/{identification}/{monthdate}")
	private ResponseEntity<Accounts> calculateMonthlyInterest(@PathVariable("identification") String identification,
			@PathVariable("monthdate") String monthdate) {

		Date date = dateParser(monthdate);

		// return accounts;
		return accountService.saveOrUpdateAccount(identification, date);
	}

	private Date dateParser(String monthdate) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

		Date date = null;
		try {
			date = formatter.parse(monthdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
