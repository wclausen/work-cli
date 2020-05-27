# work-cli (WIP)
Repo for CLI to streamline workflows with jira + git by combining related actions into a single tool

Commands will include:
- `$ work init` (specify config info like jira username/api token, git directory info)
- `$ work start` (start a jira task and checkout a branch with the task key)
- `$ work comment` (add comment to current jira issue)
- `$ work commit` (commit in progress work)
- `$ work update` (combination of comment and commit)
- `$ work diff` (wrapper for arc diff, updates jira command with link to diff and changes issue status to "in review")
- `$ work done` (optionally provide final comment on issue, changes issue status to "done")

This tool is built in Kotlin using the Clikt library for command line parsing and Square's Workflow library for driving the underlying logic.
