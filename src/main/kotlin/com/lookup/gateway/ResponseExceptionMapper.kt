package com.lookup.gateway

import javax.ws.rs.core.Response
import javax.ws.rs.ext.Provider

@Provider
class ResponseExceptionMapper: org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper<RuntimeException> {
    override fun toThrowable(response: Response?): RuntimeException {
        throw RuntimeException("ERRO ${response?.status}")
    }
}