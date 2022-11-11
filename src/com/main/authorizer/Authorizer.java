package com.main.authorizer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.main.model.account.Account;
import com.main.model.account.AccountType;
import com.main.model.event.Event;
import com.main.model.event.EventType;
import com.main.service.event.ResponseEvent;

public class Authorizer {

  private List<Account> accounts;
  private Validator validator;

  public Authorizer() {
    validator = new Validator(this);
    accounts = new ArrayList<Account>();
  }

  /**
   * Performs a deposit, withdraw or transfer according to the {@linkplain EventType} parameter 
   * @param event {@link Event}
   * @return {@link Account} Updated account or null if the account does not exist or is not valid
   */
  public ResponseEvent doEvent(Event event) {
    
    if (event.getType().equals(EventType.deposit))
      return toDeposit(event);
    
    else if (event.getType().equals(EventType.withdraw))
      return toWithdraw(event);
    
    else if (event.getType().equals(EventType.transfer))
      return toTransfer(event);
    
    return null;
  }

  /**
   * Do a deposit
   * @param event {@link Event}
   * @return {@link ResponseEvent} Updated account destination or null if the account does not exist
   */
  private ResponseEvent toDeposit(Event event) {
    Account account = validator.toValidateDeposit(event);
    if (account == null) {
      account = toCreateAccount(event);
      accounts.add(toCreateAccount(event));
    } 
    else
      toGetAccountOnList(account).toDeposit(event.getAmount());

    return toCreateResponseEvent(account, AccountType.DESTINATION);
  }

  /**
   * Do a withdrawal
   * @param event {@link Event}
   * @return {@link ResponseEvent} Updated account origin or null if the account does not exist
   */
  private ResponseEvent toWithdraw(Event event) {
    Account account = validator.toValidateWithdraw(event);

    if (account == null)
      return null;
    else
      toGetAccountOnList(account).toWithdraw(event.getAmount());

    return toCreateResponseEvent(account, AccountType.ORIGIN);
  }

  /**
   * Do a transfer
   * @param event {@link Event}
   * @return {@link ResponseEvent} Updated accounts (origin and destination) or null if the account does not exist
   */
  private ResponseEvent toTransfer(Event event) {
    Account accountOrigin = validator.toValidateTransferAccountOrigin(event);
    Account accountDestination = validator.toValidateTransferAccountDestination(event);

    if (accountOrigin == null)
      return null;
    else {
      
      toGetAccountOnList(accountOrigin).toWithdraw(event.getAmount());
      
      /* If the destination account exists for the bank, it withdraws the money */
      if (accountDestination != null)
        toGetAccountOnList(accountDestination).toDeposit(event.getAmount());
    }

    return toCreateResponseEventTransfer(event, accountOrigin, accountDestination);
  }

  /**
   * Get balance of the account
   * @param accountId {@link Long}
   * @return {@link BigDecimal} Balance or null if the account does not exist
   */
  public BigDecimal getBalance(Long accountId) {
    Account account = validator.toValidateBalance(accountId);

    if (account != null)
      return account.getBalance();

    return null;
  }

  /**
   * Get the account on list
   * @param account {@link Account}
   * @return {@link Account} account
   */
  public Account toGetAccountOnList(Account account) {
    return accounts.get(accounts.indexOf(account));
  }

  /**
   * Create a new account
   * @param event {@link Event}
   * @return {@link Account} new Account
   */
  private Account toCreateAccount(Event event) {
    return new Account(event.getDestination(), event.getAmount());
  }
  
  /**
   * Create the event response
   * @param account {@link Account}
   * @param accountType {@link AccountType}
   * @return {@link ResponseEvent} Event response
   */
  private ResponseEvent toCreateResponseEvent(Account account, AccountType accountType) {
    return new ResponseEvent(toGetAccountOnList(account), accountType);
  } 
  
  /**
   * Create the event response with origin and destination account
   * @param event {@link Event}
   * @param origin {@link Account}
   * @param destination {@link Account}
   * @return {@link ResponseEvent} Event response
   */
  private ResponseEvent toCreateResponseEventTransfer(Event event, Account origin, Account destination) {
    return new ResponseEvent((destination != null ? destination : new Account(event.getDestination(), event.getAmount())), origin);
  } 

  public List<Account> getAccounts() {
    return accounts;
  }

}
