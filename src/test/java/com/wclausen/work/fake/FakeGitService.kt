package com.wclausen.work.fake

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.wclausen.work.git.GitService
import org.eclipse.jgit.lib.AnyObjectId
import org.eclipse.jgit.lib.ObjectId
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.revwalk.RevCommit

class FakeGitService : GitService {

    var throws = false
    var lastBranchCheckedOut: String? = null
    override suspend fun checkout(branchName: String): Result<Ref, GitService.GitError> {
        lastBranchCheckedOut = branchName
        return if (!throws) Ok(FakeRef()) else Err(GitService.GitError.CheckoutFailedError(Exception()))
    }

    override fun commitProgress(message: String): RevCommit {
        return FakeRevCommit()
    }

}

class FakeRevCommit : RevCommit(object : AnyObjectId() {
    override fun toObjectId(): ObjectId {
        TODO("Not yet implemented")
    }

})

class FakeRef : Ref {
    override fun getPeeledObjectId(): ObjectId {
        TODO("Not yet implemented")
    }

    override fun getTarget(): Ref {
        TODO("Not yet implemented")
    }

    override fun getName(): String {
        return ""
    }

    override fun getStorage(): Ref.Storage {
        TODO("Not yet implemented")
    }

    override fun getLeaf(): Ref {
        TODO("Not yet implemented")
    }

    override fun getObjectId(): ObjectId {
        TODO("Not yet implemented")
    }

    override fun isPeeled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSymbolic(): Boolean {
        TODO("Not yet implemented")
    }

}

