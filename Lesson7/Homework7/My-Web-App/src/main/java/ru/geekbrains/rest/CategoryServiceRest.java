package ru.geekbrains.rest;

import ru.geekbrains.services.CategoryRepr;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/cat")
public interface CategoryServiceRest {
  @GET
  @Path("/count")
  @Produces(MediaType.APPLICATION_JSON)
  Long countAll();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  List<CategoryRepr> findAll();

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  CategoryRepr findById(@PathParam("id") Long id);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  void insert(CategoryRepr category);

  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  void update(CategoryRepr category);

  @DELETE
  @Path("/{id}")
  void deleteById(@PathParam("id") Long id);
}
