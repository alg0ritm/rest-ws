package accounts;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import common.money.Percentage;

/**
 * An account for a member of the reward network. An account has one or more beneficiaries whose allocations must add up
 * to 100%.
 * 
 * An account can make contributions to its beneficiaries. Each contribution is distributed among the beneficiaries
 * based on an allocation.
 * 
 * An entity. An aggregate.
 */
@Entity
@Table(name = "T_ACCOUNT")
public class Account {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Integer entityId;

	@Column(name = "number")
	private String number;

	@Column(name = "NAME")
	private String name;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "ACCOUNT_ID")
	private Set<Beneficiary> beneficiaries = new HashSet<Beneficiary>();

	protected Account() {
	}

	/**
	 * Create a new account.
	 * @param number the account number
	 * @param name the name on the account
	 */
	public Account(String number, String name) {
		this.number = number;
		this.name = name;
	}

	/**
	 * Returns the entity identifier used to internally distinguish this entity among other entities of the same type in
	 * the system. Should typically only be called by privileged data access infrastructure code such as an Object
	 * Relational Mapper (ORM) and not by application code.
	 * @return the internal entity identifier
	 */
	public Integer getEntityId() {
		return entityId;
	}

	/**
	 * Sets the internal entity identifier - should only be called by privileged data access code (repositories that
	 * work with an Object Relational Mapper (ORM)). Should never be set by application code explicitly.
	 * @param entityId the internal entity identifier
	 */
	public void setEntityId(Integer entityId) {
		this.entityId = entityId;
	}

	/**
	 * Returns the number used to uniquely identify this account.
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the number used to uniquely identify this account.
	 * @param number The number for this account
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Returns the name on file for this account.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name on file for this account.
	 * @param name The name for this account
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Add a single beneficiary with a 100% allocation percentage.
	 * @param beneficiaryName the name of the beneficiary (should be unique)
	 */
	public void addBeneficiary(String beneficiaryName) {
		addBeneficiary(beneficiaryName, Percentage.oneHundred());
	}

	/**
	 * Add a single beneficiary with the specified allocation percentage.
	 * @param beneficiaryName the name of the beneficiary (should be unique)
	 * @param allocationPercentage the beneficiary's allocation percentage within this account
	 */
	public void addBeneficiary(String beneficiaryName, Percentage allocationPercentage) {
		beneficiaries.add(new Beneficiary(beneficiaryName, allocationPercentage));
	}

	/**
	 * Returns the beneficiaries for this account.
	 * <p>
	 * Callers should not attempt to hold on or modify the returned set. This method should only be used transitively;
	 * for example, called to facilitate account reporting.
	 * @return the beneficiaries of this account
	 */
	public Set<Beneficiary> getBeneficiaries() {
		return beneficiaries;
	}

	/**
	 * Returns a single account beneficiary. Callers should not attempt to hold on or modify the returned object. This
	 * method should only be used transitively; for example, called to facilitate reporting or testing.
	 * @param name the name of the beneficiary e.g "Annabelle"
	 * @return the beneficiary object
	 */
	public Beneficiary getBeneficiary(String name) {
		for (Beneficiary b : beneficiaries) {
			if (b.getName().equals(name)) {
				return b;
			}
		}
		throw new IllegalArgumentException("No such beneficiary with name '" + name + "'");
	}

	/**
	 * Removes a single beneficiary from this account.
	 * @param beneficiaryName the name of the beneficiary (should be unique)
	 */
	public void removeBeneficiary(String beneficiaryName) {
		beneficiaries.remove(getBeneficiary(beneficiaryName));
	}

	public String toString() {
		return "Number = '" + number + "', name = '" + name + "', beneficiaries = " + beneficiaries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((beneficiaries == null) ? 0 : beneficiaries.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (beneficiaries == null) {
			if (other.beneficiaries != null)
				return false;
		} else if (!beneficiaries.equals(other.beneficiaries))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	
	
}