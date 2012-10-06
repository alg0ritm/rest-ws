package accounts.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import models.Games;
import models.Users;

import org.hsqldb.User;
import org.springframework.orm.ObjectRetrievalFailureException;

import accounts.Account;
import accounts.Beneficiary;

import common.money.Percentage;

public class StubUsersManager {
private Map<Integer, Users> accountsById = new HashMap<Integer, Users>();
	
	private AtomicInteger nextEntityId = new AtomicInteger();

	public StubUsersManager() {
		Users user = new Users("skyzer@gmail.com", "skyzer", "Artur", "1234", 1, new Date(), 200);
		accountsById.put(user.getId(), user);
	}

	public ArrayList<Users> getAllUsers() {
		return new ArrayList<Users>(accountsById.values());
	}

	public Users getUser(Integer id) {
		Users user = accountsById.get(id);
		if (user == null) {
			throw new ObjectRetrievalFailureException(Account.class, id);
		}
		return user;
	}

	

	
	/*@Override
	public String login(Users user) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
