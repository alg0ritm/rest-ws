package accounts.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import models.Games;
import models.LoginInfo;
import models.Users;

import org.springframework.orm.ObjectRetrievalFailureException;

import accounts.Account;
import accounts.AccountManager;
import accounts.Beneficiary;

import common.money.Percentage;

/**
 * A dummy implementation of an account manager storing all account data in memory. Useful for testing only.
 */
public class StubAccountManager implements AccountManager {

	private Map<Integer, Account> accountsById = new HashMap<Integer, Account>();
	
	private AtomicInteger nextEntityId = new AtomicInteger();

	public StubAccountManager() {
		Account account = new Account("123456789", "Keith and Keri Donald");
		account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
		account.addBeneficiary("Corgan", Percentage.valueOf("50%"));
		account.setEntityId(nextEntityId.getAndIncrement());
		accountsById.put(account.getEntityId(), account);
	}

	public List<Account> getAllAccounts() {
		return new ArrayList<Account>(accountsById.values());
	}

	public Account getAccount(Integer id) {
		Account account = accountsById.get(id);
		if (account == null) {
			throw new ObjectRetrievalFailureException(Account.class, id);
		}
		return account;
	}

	public void update(Account account) {
		accountsById.put(account.getEntityId(), account);
	}

	public void updateBeneficiaryAllocationPercentages(Integer accountId, Map<String, Percentage> allocationPercentages) {
		Account account = accountsById.get(accountId);
		for (Entry<String, Percentage> entry : allocationPercentages.entrySet()) {
			account.getBeneficiary(entry.getKey()).setAllocationPercentage(entry.getValue());
		}
	}

	public void addBeneficiary(Integer accountId, String beneficiaryName) {
		accountsById.get(accountId).addBeneficiary(beneficiaryName, Percentage.zero());
	}

	public void removeBeneficiary(Integer accountId, String beneficiaryName, Map<String, Percentage> allocationPercentages) {
		accountsById.get(accountId).removeBeneficiary(beneficiaryName);
		updateBeneficiaryAllocationPercentages(accountId, allocationPercentages);
	}

	@Override
	public void removeBeneficiary(Integer accountId, String beneficiaryName) {
		accountsById.get(accountId).removeBeneficiary(beneficiaryName);
		// should preserve invariant that total of percentages == 100, but never mind that... 
	}

	@Override
	public Account save(Account newAccount) {
		for (Beneficiary beneficiary : newAccount.getBeneficiaries()) {
			beneficiary.setEntityId(nextEntityId.getAndIncrement());
		}
		newAccount.setEntityId(nextEntityId.getAndIncrement());
		return newAccount;
	}

	@Override
	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Games> getAllGames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(LoginInfo loginInfo) {
		// TODO Auto-generated method stub
		return null;
	}


}
