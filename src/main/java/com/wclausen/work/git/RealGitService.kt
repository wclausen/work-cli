package com.wclausen.work.git

import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapError
import com.github.michaelbull.result.runCatching
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.revwalk.RevCommit

class RealGitService(private val gitClient: Git) : GitService {

    override suspend fun checkout(branchName: String): Result<Ref, GitService.GitError> =
        runCatching {
            val branchAlreadyExists =
                gitClient.branchList().call().any { it.name.contains(branchName) }
            gitClient.checkout().setName(branchName).setCreateBranch(!branchAlreadyExists).call()
        }.mapError { GitService.GitError.CheckoutFailedError(it) }

    override fun commitProgress(message: String): Result<RevCommit, GitService.GitError> =
        runCatching {
            gitClient.commit().setAll(true).setMessage(message).call()
        }.mapError { GitService.GitError.CommitFailedError(it) }
}