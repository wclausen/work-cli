package com.wclausen.work.git;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/wclausen/work/git/RealGitService;", "Lcom/wclausen/work/git/GitService;", "gitClient", "Lorg/eclipse/jgit/api/Git;", "(Lorg/eclipse/jgit/api/Git;)V", "checkout", "Lcom/github/michaelbull/result/Result;", "Lorg/eclipse/jgit/lib/Ref;", "Lcom/wclausen/work/git/GitService$GitError;", "branchName", "", "commitProgress", "Lorg/eclipse/jgit/revwalk/RevCommit;", "message", "work-cli"})
public final class RealGitService implements com.wclausen.work.git.GitService {
    private final org.eclipse.jgit.api.Git gitClient = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.github.michaelbull.result.Result<org.eclipse.jgit.lib.Ref, com.wclausen.work.git.GitService.GitError> checkout(@org.jetbrains.annotations.NotNull()
    java.lang.String branchName) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public org.eclipse.jgit.revwalk.RevCommit commitProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
        return null;
    }
    
    public RealGitService(@org.jetbrains.annotations.NotNull()
    org.eclipse.jgit.api.Git gitClient) {
        super();
    }
}