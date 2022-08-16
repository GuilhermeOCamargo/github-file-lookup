package com.lookup

import com.lookup.service.GithubService
import com.lookup.util.CheckFileResponse
import org.jboss.resteasy.reactive.RestQuery
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("/git")
class GithubResource(@Inject var service: GithubService) {

    @GET
    @Path("/repo/validateFile")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun searchFileInRepo(@RestQuery username: String, @RestQuery file: String, @RestQuery key: String) : List<CheckFileResponse?> =
        service.checkFileInUserRepo(username, file, key)
}