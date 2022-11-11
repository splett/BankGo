package com.main.model.event;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Event {

  private EventType type;
  private Long origin;
  private BigDecimal amount;
  private Long destination;

  public EventType getType() {
    return type;
  }

  public void setType(EventType type) {
    this.type = type;
  }

  public Long getOrigin() {
    return origin;
  }

  public void setOrigin(Long origin) {
    this.origin = origin;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public Long getDestination() {
    return destination;
  }

  public void setDestination(Long destination) {
    this.destination = destination;
  }

}
