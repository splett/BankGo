package com.main.service.balance;

import java.math.BigDecimal;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.main.authorizer.Singleton;
import com.main.model.util.Status;

@Path("/balance")
public class BalanceService {

  /**
   * Get balance of an account. Return NOT FOUND (404) if the account does not exist
   * @param accountId {@link Long}
   * @return {@link BigDecimal} Balance
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response getBalance(@QueryParam("account_id") Long accountId) {
    BigDecimal balance = Singleton.getInstance().getBalance(Long.valueOf(accountId));
    return (balance != null ? Response.ok(balance.toString()).build() : Response.status(Status.NOT_FOUND.getStatusCode()).entity("0").build());
  }
  
}
