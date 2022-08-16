package com.kotlin.gateway

import com.lookup.gateway.headerFactory.GithubHeaderFactory
import com.lookup.util.GitHubListRepoResponse
import com.lookup.util.GitHubSearchResponse
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient
import org.jboss.resteasy.reactive.RestQuery
import java.util.concurrent.CompletionStage
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

@RegisterRestClient(configKey = "github-gateway")
@RegisterClientHeaders(GithubHeaderFactory::class)
interface GithubGateway {

    @GET
    @Path("/users/{username}/repos")
    fun listReposByUsername(@PathParam("username") username: String): CompletionStage<Set<GitHubListRepoResponse>>

    @GET
    @Path("/search/code")
    fun searchFile(@RestQuery q: String): CompletionStage<GitHubSearchResponse>
}