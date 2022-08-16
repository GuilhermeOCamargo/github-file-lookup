package com.lookup.gateway.headerFactory

import com.kotlin.gateway.headerFactory.GITHUB_TOKEN
import com.kotlin.gateway.headerFactory.withAccept
import com.kotlin.gateway.headerFactory.withAuthorization
import com.kotlin.gateway.headerFactory.withContentType
import io.quarkus.rest.client.reactive.ReactiveClientHeadersFactory
import io.smallrye.mutiny.Uni
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.MultivaluedHashMap
import javax.ws.rs.core.MultivaluedMap
@ApplicationScoped
class GithubHeaderFactory: ReactiveClientHeadersFactory() {
    override fun getHeaders(
        incomingHeaders: MultivaluedMap<String, String>?,
        clientOutgoingHeaders: MultivaluedMap<String, String>?
    ): Uni<MultivaluedMap<String, String>> =
        Uni.createFrom()
            .item(MultivaluedHashMap<String, String>()
                    withAuthorization GITHUB_TOKEN
                    withAccept "application/vnd.github+json"
                    withContentType MediaType.APPLICATION_JSON
            )

}