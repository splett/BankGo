package com.main.service.event;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.main.authorizer.Singleton;
import com.main.model.event.Event;
import com.main.model.event.EventType;
import com.main.model.util.Status;

@Path("/event")
public class EventService {

  
  /**
   * Performs a deposit, withdraw or transfer according to the {@linkplain EventType} parameter 
   * @param event {@link Event}
   * @return {@link Response} in the {@link ResponseEvent} format or NOT FOUND (404) if the informed parameters are invalid and the event was not performed
   */
  @POST
  @Consumes("application/json")
  @Produces({ MediaType.APPLICATION_JSON })
  public Response doEvent(Event event) {
    ResponseEvent responseEvent = (ResponseEvent) Singleton.getInstance().doEvent(event);
    return responseEvent != null ? Response.status(Status.CREATED.getStatusCode()).entity(responseEvent).build() : Response.status(Status.NOT_FOUND.getStatusCode()).entity("0").build();
  }

}
