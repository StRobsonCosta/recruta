package br.com.kavex.recruta.controller;

import br.com.kavex.recruta.dto.UserMsgRequest;
import br.com.kavex.recruta.service.WhatsAppService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/whatsapp")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ZapController {

    @Inject
    WhatsAppService whatsappService;

    @POST
    @Path("/message")
    public Response receiveMessage(UserMsgRequest request) {
        whatsappService.processMessage(request);
        return Response.ok().build();
    }
}
