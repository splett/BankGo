package com.main.authorizer;

import com.main.model.account.Account;
import com.main.model.event.Event;

public class Validator {

  private Authorizer authorizer;

  public Validator(Authorizer authorizer) {
    this.authorizer = authorizer;
  }
  
  /**
   * If the origin account exists it returns the account, if it doesn't exist it returns null
   * @param event {@link Event}
   * @return {@link Account} account
   */
  public Account toValidateWithdraw(Event event) {
    return toGetAccount(event.getOrigin());
  }
  
  /**
   * If the destination account exists it returns the account, if it doesn't exist it returns null
   * @param event {@link Event}
   * @return {@link Account} account
   */
  public Account toValidateDeposit(Event event) {
    return toGetAccount(event.getDestination());
  }
 
  /**
   * If the origin account exists it returns the account, if it doesn't exist it returns null
   * @param event {@link Event}
   * @return {@link Account} account
   */
 
  public Account toValidateTransferAccountOrigin(Event event) {
    
    Account account = toGetAccount(event.getOrigin());
    
    if(account != null && account.getBalance().compareTo(event.getAmount()) >= 0)
      return account;
    else 
      return null;
  }
  
  /**
   * If the destination account exists it returns the account, if it doesn't exist it returns null
   * @param event {@link Event}
   * @return {@link Account} account
   */
  public Account toValidateTransferAccountDestination(Event event) {
    return toGetAccount(event.getDestination());
  }
  
  /**
   * If the account exists it returns the account, if it doesn't exist it returns null
   * @param accountId {@link Long}
   * @return {@link Account} account
   */
  public Account toValidateBalance(Long accountId) {
    return toGetAccount(accountId);
  }
  
  /**
   * Find for an account according to the ID, if it doesn't exist it returns null
   * @param accountId {@link Long}
   * @return {@link Account} account
   */
  public Account toGetAccount(Long accountId) {
    if (hasAccounts())
      for (int i = 0; i < authorizer.getAccounts().size(); i++)
        if (authorizer.getAccounts().get(i).getId().equals(accountId))
          return authorizer.getAccounts().get(i);
    
    return null;
  }
  
  /**
   * Verify if has account
   * @return {@link Boolean} true if have account
   */
  private Boolean hasAccounts() {
    return authorizer.getAccounts() != null;
  }

}
