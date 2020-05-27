package com.wclausen.work.jira;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J%\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0011\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J+\u0010\u0011\u001a\u00020\u00122\u000e\b\u0003\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\b\b\u0003\u0010\u0015\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/wclausen/work/jira/JiraService;", "", "commentOnIssue", "Lcom/wclausen/work/jira/api/model/JiraComment;", "id", "", "comment", "Lcom/wclausen/work/jira/api/model/IssueComment;", "(Ljava/lang/String;Lcom/wclausen/work/jira/api/model/IssueComment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createIssue", "Lcom/wclausen/work/jira/api/model/IssueResponse;", "data", "Lcom/wclausen/work/jira/api/model/IssueData;", "(Lcom/wclausen/work/jira/api/model/IssueData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentUser", "Lcom/wclausen/work/jira/api/model/JiraUser;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTasksForCurrentUser", "Lcom/wclausen/work/jira/api/model/JqlSearchResult;", "fields", "", "jql", "([Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "work-cli"})
public abstract interface JiraService {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "issue")
    public abstract java.lang.Object createIssue(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.wclausen.work.jira.api.model.IssueData data, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wclausen.work.jira.api.model.IssueResponse> p1);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "myself")
    public abstract java.lang.Object getCurrentUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wclausen.work.jira.api.model.JiraUser> p0);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "search")
    public abstract java.lang.Object getTasksForCurrentUser(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "fields")
    java.lang.String[] fields, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "jql")
    java.lang.String jql, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wclausen.work.jira.api.model.JqlSearchResult> p2);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.POST(value = "issue/{id}/comment")
    public abstract java.lang.Object commentOnIssue(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "id")
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Body()
    com.wclausen.work.jira.api.model.IssueComment comment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.wclausen.work.jira.api.model.JiraComment> p2);
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 3)
    public final class DefaultImpls {
    }
}