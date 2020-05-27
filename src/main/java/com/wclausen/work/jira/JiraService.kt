package com.wclausen.work.jira

import com.wclausen.work.jira.api.model.IssueComment
import com.wclausen.work.jira.api.model.IssueData
import com.wclausen.work.jira.api.model.IssueResponse
import com.wclausen.work.jira.api.model.JiraComment
import com.wclausen.work.jira.api.model.JiraUser
import com.wclausen.work.jira.api.model.JqlSearchResult
import retrofit2.http.*

interface JiraService {
    @POST("issue")
    suspend fun createIssue(@Body data: IssueData): IssueResponse

    @GET("myself")
    suspend fun getCurrentUser(): JiraUser

    // TODO: remove default value for jql when the assignee field is read from config
    @GET("search")
    suspend fun getTasksForCurrentUser(
        @Query("fields") fields: Array<String> = arrayOf("summary", "description", "project"),
        @Query("jql") jql: String = ""): JqlSearchResult

    @POST("issue/{id}/comment")
    suspend fun commentOnIssue(@Path("id") id: String, @Body comment: IssueComment): JiraComment
}