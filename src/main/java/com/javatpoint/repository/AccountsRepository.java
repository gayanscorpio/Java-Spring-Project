package com.javatpoint.repository;

import org.springframework.data.repository.CrudRepository;
//repository that extends CrudRepository
import com.javatpoint.model.Accounts;

public interface AccountsRepository extends CrudRepository<Accounts, String> {
}
