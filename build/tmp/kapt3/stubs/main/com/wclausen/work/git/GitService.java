package com.wclausen.work.git;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u000bJ\u001c\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H&\u00a8\u0006\f"}, d2 = {"Lcom/wclausen/work/git/GitService;", "", "checkout", "Lcom/github/michaelbull/result/Result;", "Lorg/eclipse/jgit/lib/Ref;", "Lcom/wclausen/work/git/GitService$GitError;", "branchName", "", "commitProgress", "Lorg/eclipse/jgit/revwalk/RevCommit;", "message", "GitError", "work-cli"})
public abstract interface GitService {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.github.michaelbull.result.Result<org.eclipse.jgit.lib.Ref, com.wclausen.work.git.GitService.GitError> checkout(@org.jetbrains.annotations.NotNull()
    java.lang.String branchName);
    
    @org.jetbrains.annotations.NotNull()
    public abstract org.eclipse.jgit.revwalk.RevCommit commitProgress(@org.jetbrains.annotations.NotNull()
    java.lang.String message);
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0006B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u00a2\u0006\u0002\u0010\u0005\u0082\u0001\u0001\u0007\u00a8\u0006\b"}, d2 = {"Lcom/wclausen/work/git/GitService$GitError;", "", "message", "", "cause", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "CheckoutFailedError", "Lcom/wclausen/work/git/GitService$GitError$CheckoutFailedError;", "work-cli"})
    public static abstract class GitError extends java.lang.Throwable {
        
        private GitError(java.lang.String message, java.lang.Throwable cause) {
            super(null);
        }
        
        @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/wclausen/work/git/GitService$GitError$CheckoutFailedError;", "Lcom/wclausen/work/git/GitService$GitError;", "cause", "", "(Ljava/lang/Throwable;)V", "work-cli"})
        public static final class CheckoutFailedError extends com.wclausen.work.git.GitService.GitError {
            
            public CheckoutFailedError(@org.jetbrains.annotations.NotNull()
            java.lang.Throwable cause) {
                super(null, null);
            }
        }
    }
}