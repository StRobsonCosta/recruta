package br.com.kavex.recruta.api;

import br.com.kavex.recruta.dto.InterviewQuestionDTO;
import br.com.kavex.recruta.service.InterviewQuestionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/questions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InterviewQuestionResource {

    @Inject
    InterviewQuestionService interviewQuestionService;

    @GET
    public List<InterviewQuestionDTO> getAllQuestions() {
        return interviewQuestionService.getAllQuestions();
    }

    @POST
    public Response createQuestion(InterviewQuestionDTO questionDTO) {
        interviewQuestionService.createQuestion(questionDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response getQuestionById(@PathParam("id") Long id) {
        return interviewQuestionService.getQuestionById(id)
                .map(q -> Response.ok(q).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deleteQuestion(@PathParam("id") Long id) {
        boolean deleted = interviewQuestionService.deleteQuestion(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
