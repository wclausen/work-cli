# work-cli (WIP)
Repo for CLI to streamline workflows with jira + git by combining related actions into a single tool

Commands will include:
- init (specify config info like jira username/api token, git directory info)
- start (start a jira task and checkout a branch with the task key)
- comment (add comment to current jira issue)
- commit (commit in progress work)
- update (combination of comment and commit)
- diff (wrapper for arc diff, updates jira command with link to diff and changes issue status to "in review")
- done (optionally provide final comment on issue, changes issue status to "done")

This tool is built in Kotlin using the Clikt library for command line parsing and Square's Workflow library for driving the underlying logic.
