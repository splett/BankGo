package com.main.model.account;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {
  
  private Long id;
  private BigDecimal balance;

  public Account() { }
  
  public Account(Long id) {
    this.id = id;
  }

  public Account(Long id, BigDecimal balance) {
    this.id = id;
    this.balance = balance;
  }
 
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public void toDeposit(BigDecimal amount) {
    this.setBalance(this.getBalance().add(amount));
  }

  public void toWithdraw(BigDecimal amount) {
    this.setBalance(this.getBalance().subtract(amount));
  }

  @Override
  public boolean equals(Object obj) {
    boolean result = false;

    if (this.getId().equals(((Account) obj).getId()))
      result = true;

    return result;
  }
}
