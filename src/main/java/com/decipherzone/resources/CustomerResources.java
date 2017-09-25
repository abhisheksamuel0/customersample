package com.decipherzone.resources;

import com.codahale.metrics.annotation.Timed;
import com.decipherzone.config.Response;
import com.decipherzone.service.CustomerService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.eclipse.jetty.http.HttpStatus;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by decipher on 22/9/17 2:34 PM
 * Abhishek Samuel (Software Engineer)
 * abhisheks@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */
@Path("/customer")
@Api(value = "/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResources {

    private CustomerService customerService;

    @Inject
    public CustomerResources(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    @Timed
    @ApiOperation(value = "Find all customers", response = Response.class, notes = "Returns list of all customers present in customer collection")
    public Response getCustomer() {
        Response response = new Response();
        String message = StringUtils.EMPTY;
        response.setHttpStatus(HttpStatus.FORBIDDEN_403);

        List<Document> documentList = customerService.findAll();
        if (documentList != null) {
            response.setResult(documentList);
            response.setHttpStatus(HttpStatus.OK_200);
        } else {
            message = "Error while getting customer list";
        }
        response.setMessage(message);

        return response;
    }

}
