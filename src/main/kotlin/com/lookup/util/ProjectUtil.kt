package com.lookup.util

typealias GitHubListRepoResponse = MutableMap<String, Any?>
typealias GitHubSearchResponse = MutableMap<String, Any?>
typealias CheckFileResponse = Map<String, Any?>

inline fun <T> Any.cast(): T = this as T

inline fun GitHubSearchResponse.getTotalCount(): Int =
    this.get("total_count")!!.cast<Int>()
inline fun GitHubSearchResponse.getOwner(): String =
    this.get("owner")!!.cast<GitHubListRepoResponse>().get("login").toString()
inline fun GitHubSearchResponse.getRepositoryName(): String =
    this.get("name").toString()

inline fun GitHubSearchResponse.hasFile(): Boolean = this.getTotalCount() > 0