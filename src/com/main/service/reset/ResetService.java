package com.main.service.reset;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.main.authorizer.Authorizer;
import com.main.authorizer.Singleton;

@Path("/reset")
public class ResetService {
  
  /**
   * Create a new instance of {@link Authorizer}
   * @return {@link Response} Status 200 OK
   */
  @POST
  public Response toReset() {
    Singleton.getInstance(true);
    return Response.ok("OK").build();
  }
}
