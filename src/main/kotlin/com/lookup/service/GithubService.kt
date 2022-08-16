package com.lookup.service

import com.kotlin.gateway.GithubGateway
import com.lookup.util.*
import kotlinx.coroutines.future.await
import org.eclipse.microprofile.rest.client.inject.RestClient
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import java.util.concurrent.Flow
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GithubService(@RestClient val gateway: GithubGateway) {

    private fun listUserRepo(username: String): CompletableFuture<Set<GitHubListRepoResponse>> = gateway.listReposByUsername(username).toCompletableFuture()

    private fun findFileInRepo(fileName: String, keyword: String, repoOwner: String, repoName: String): CompletableFuture<GitHubSearchResponse> =
        buildGithubQuery(fileName, keyword, repoOwner, repoName)
            .let { gateway.searchFile(it).toCompletableFuture() }

    private fun buildGithubQuery(fileName: String, keyword: String, repoOwner: String, repoName: String): String =
        "$keyword repo:$repoOwner/$repoName filename:$fileName"

    private suspend fun buildResponse(gitHubListRepoResponse: GitHubListRepoResponse, fileName: String, keyword: String): CheckFileResponse? =
        findFileInRepo(fileName, keyword, gitHubListRepoResponse.getOwner(), gitHubListRepoResponse.getRepositoryName())
            .await()
            .let {
                if(!it.hasFile()){
                    mapOf<String, Any?>("repoName" to gitHubListRepoResponse.getRepositoryName())
                } else {
                    null
                }
            }

    suspend fun checkFileInUserRepo(username: String, fileName: String, keyword: String): List<CheckFileResponse?> =
        listUserRepo(username)
            .await()
            .mapNotNull { buildResponse(it, fileName, keyword) }

}