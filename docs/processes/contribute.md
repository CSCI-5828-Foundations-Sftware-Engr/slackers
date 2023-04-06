# How we work on Goodboards

1. We work in pairs
2. We make branch from `main`
3. We work on the branch together
4. When we are ready to put that to main, we raise  PR to `main`, this will give us visual indication that it is compatible
5. The code should successfully compile.
6. Test the changes locally by deploying them.
7. Only `Rebase and Merge` or commands as below which achieve the same
   ```shell
    # Make sure local main is up to date
    git checkout main
    git pull
    # Make sure your local branch is up to date with remote
    git checkout <branch>
    git pull
    # Rebase local branch to be up-to-date w.r.t to main (everything in main is in the branch)
    git rebase main
    # If you get conflicts, resolve them.
    # Now put all the changes to main
    # It should always be --ff-only, if not check with team
    git checkout main
    git merge --ff-only <branch>
    # Now update remote main for everyone
    git push
    # PR should be automatically closed, if it doesn't discuss with team
   ```