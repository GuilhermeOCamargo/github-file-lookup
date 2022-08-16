package com.kotlin.gateway.headerFactory

import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MultivaluedHashMap

val GITHUB_TOKEN: String = "token ${System.getenv("GITHUB_API_TOKEN")}"

infix fun MultivaluedHashMap<String, String>.withAuthorization(value: String): MultivaluedHashMap<String, String> {
    this.add(HttpHeaders.AUTHORIZATION, value)
    return this
}
infix fun MultivaluedHashMap<String, String>.withAccept(value: String): MultivaluedHashMap<String, String> {
    this.add(HttpHeaders.ACCEPT, value)
    return this
}
infix fun MultivaluedHashMap<String, String>.withContentType(value: String): MultivaluedHashMap<String, String> {
    this.add(HttpHeaders.CONTENT_TYPE, value)
    return this
}