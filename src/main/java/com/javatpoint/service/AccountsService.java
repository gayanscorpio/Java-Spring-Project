package com.javatpoint.service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.javatpoint.model.Accounts;
import com.javatpoint.repository.AccountsRepository;

//defining the business logic
@Service
public class AccountsService {
	@Autowired
	AccountsRepository accountsRepository;

	/*
	 * getting all accounts record by using the method findaAll() of CrudRepository
	 */
	public ResponseEntity<List<Accounts>> getAllAccounts() {
		try {

			List<Accounts> accounts = new ArrayList<Accounts>();
			accountsRepository.findAll().forEach(account -> accounts.add(account));

			if (accounts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(accounts, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	/*
	 * getting a specific record by using the method findById() of CrudRepository
	 */
	public Accounts getAccountsById(String id) {
		return accountsRepository.findById(id).get();
	}

	/*
	 * saving a specific record by using the method save() of CrudRepository
	 */
	public ResponseEntity<List<Accounts>> saveOrUpdateAccounts(Date balanceDateReq) {
		ArrayList<Accounts> accounts = new ArrayList<Accounts>();
		List<Accounts> updatedAccounts = new ArrayList<Accounts>();

		OffsetDateTime balanceDate = balanceDateReq.toInstant().atOffset(ZoneOffset.UTC);
		accountsRepository.findAll().forEach(account -> accounts.add(account));

		if (accounts.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {

			for (Accounts account : accounts) {

				updatedAccounts.add(calculateInterests(balanceDate, account));
			}
		}
		return new ResponseEntity<>(updatedAccounts, HttpStatus.OK);

	}

	/**
	 * update account based on request account identification and date
	 * 
	 * @param identification
	 * @param dateAfterString
	 * @return
	 */
	public ResponseEntity<Accounts> saveOrUpdateAccount(String identification, Date balanceDateReq) {

		OffsetDateTime balanceDate = balanceDateReq.toInstant().atOffset(ZoneOffset.UTC);

		Optional<Accounts> accountData = accountsRepository.findById(identification);

		if (accountData.isPresent()) {

			Accounts account = calculateInterests(balanceDate, accountData.get());

			return new ResponseEntity<>(accountsRepository.save(account), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	private Accounts calculateInterests(OffsetDateTime balanceDate, Accounts accountData) {

		OffsetDateTime dateBeforeString = accountData.getOpeningDate();

		// calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(dateBeforeString, balanceDate);
		double interestCalculate = 0.6 * noOfDaysBetween;

		accountData.setBalance(interestCalculate + accountData.getBalance());
		accountData.setBalanceDate(balanceDate);
		return accountData;
	}

	/*
	 * deleting a specific record by using the method deleteById() of CrudRepository
	 */
	public void delete(String id) {
		accountsRepository.deleteById(id);
	}

	/*
	 * updating a record
	 */
	public void saveAccount(Accounts accounts) {
		accountsRepository.save(accounts);
	}
}