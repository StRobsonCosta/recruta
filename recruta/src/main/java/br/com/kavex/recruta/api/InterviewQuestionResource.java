package br.com.kavex.recruta.api;

import br.com.kavex.recruta.dto.InterviewQuestionDTO;
import br.com.kavex.recruta.service.InterviewQuestionService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    public Response getAllQuestions() {
        List<InterviewQuestionDTO> questions = interviewQuestionService.getAllQuestions();
        return Response.ok(questions).build();
    }

    @POST
    @Transactional
    public Response createQuestion(InterviewQuestionDTO questionDTO) {
        if (questionDTO == null || questionDTO.getQuestion() == null || questionDTO.getCategory() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Dados inválidos").build();
        }
        interviewQuestionService.createQuestion(questionDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response getQuestionById(@PathParam("id") Long id) {
        if (id == null || id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("ID inválido").build();
        }
        return interviewQuestionService.getQuestionById(id)
                .map(q -> Response.ok(q).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteQuestion(@PathParam("id") Long id) {
        if (id == null || id <= 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity("ID inválido").build();
        }
        boolean deleted = interviewQuestionService.deleteQuestion(id);
        return deleted ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
