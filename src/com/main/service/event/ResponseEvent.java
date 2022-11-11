package com.main.service.event;

import javax.xml.bind.annotation.XmlRootElement;

import com.main.model.account.Account;
import com.main.model.account.AccountType;

@XmlRootElement
public class ResponseEvent {

  private Account origin;
  private Account destination;

  public ResponseEvent() { }
  
  public ResponseEvent(Account account, AccountType accountType) {
    if (accountType.equals(AccountType.DESTINATION))
      this.destination = account;
    else if (accountType.equals(AccountType.ORIGIN))
      this.origin = account;
  }

  public ResponseEvent(Account destination, Account origin) {
    this.destination = destination;
    this.origin = origin;
  }
  
  public Account getDestination() {
    return destination;
  }

  public void setDestination(Account destination) {
    this.destination = destination;
  }

  public Account getOrigin() {
    return origin;
  }

  public void setOrigin(Account origin) {
    this.origin = origin;
  }

}
