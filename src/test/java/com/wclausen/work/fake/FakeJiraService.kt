package com.wclausen.work.fake

import com.wclausen.work.jira.JiraService
import com.wclausen.work.jira.api.model.IssueBean
import com.wclausen.work.jira.api.model.IssueComment
import com.wclausen.work.jira.api.model.IssueData
import com.wclausen.work.jira.api.model.IssueResponse
import com.wclausen.work.jira.api.model.JiraComment
import com.wclausen.work.jira.api.model.JiraUser
import com.wclausen.work.jira.api.model.JqlSearchResult
import com.wclausen.work.jira.api.model.JqlSearchResultIssueFields

class FakeJiraService : JiraService {

    var lastCreateIssueData: IssueData? = null
    var createIssueResponse: ((IssueData) -> IssueResponse) = {
        lastCreateIssueData = it
       IssueResponse("10003", "MF-775")
    }

    var getTasksForCurrentUserResponse: (() -> JqlSearchResult) = {
        JqlSearchResult(
            listOf(IssueBean(
                "10001",
                "user_self",
                "WORK-1",
                JqlSearchResultIssueFields("Some task summary", "some task description"))))
    }

    override suspend fun createIssue(data: IssueData) = createIssueResponse.invoke(data)

    override suspend fun getCurrentUser(): JiraUser {
        return JiraUser(
            "user_self",
            "user_key",
            "1234567",
            "some_email@fake.com")
    }

    override suspend fun getTasksForCurrentUser(fields: Array<String>, jql: String): JqlSearchResult {
        return getTasksForCurrentUserResponse.invoke()
    }

    override suspend fun commentOnIssue(id: String, comment: IssueComment): JiraComment {
        return JiraComment("some_url", "some_comment_id")
    }

}