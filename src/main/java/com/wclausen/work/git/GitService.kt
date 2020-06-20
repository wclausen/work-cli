package com.wclausen.work.git

import com.github.michaelbull.result.Result
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.revwalk.RevCommit

interface GitService {

    suspend fun checkout(branchName: String): Result<Ref, GitError>

    fun commitProgress(message: String): RevCommit

    sealed class GitError(message: String, cause: Throwable) : Throwable(cause){
        class CheckoutFailedError(cause: Throwable) : GitError("Failed to checkout branch", cause)
    }

}