# CONTRIBUTING *and a code of conduct somehow*

## Your work is welcome and must make you feel grantly rewarded once it is done

### Report anything first

Do you want to edit or add some features to `earthdistances`? Are there any technical issue you want to fight with?

This file indicates you how to proceed in your willing to enhance this great open-source software !

First of all, create a new issue [here](https://github.com/Krikaliov/earthdistances/issues) and describe your suggestion on how to enhance `earthdistances` as both accurate and clear as possible. In case of fixing a bug encountered while using `earthdistances`, see [README.md](https://github.com/Krikaliov/earthdistances) file (*Disclaimer* section).

### Debates and approvals (or not)

This new issue must involve discussions and debates about your ideas and suggestions about how to enhance `earthdistances`. Following these fructuous discussions, your suggestions can be approved and marked as an "issue" assigned to you on the project board by the organization or devoted volunteers, otherwise fun takes end here :( .

### Cloning, working and pushing

A ticket number must be attached to this new "issue" (assigned to you) from the project board. Use it as branch name for work once you cloned the git repo on your machine:

```
git clone git@github.com:Krikaliov/earthdistances.git
git switch -m -c "#<ticket_number>"
# Homeworks ...
git add --all
git commit -m "Several words describing your work...
> 
> A paragraph with details about your work...
> ...
> "
git push -u origin #<ticket_number>
```

Then you can create a new pull request to branch `dev` (`main` **ONLY** when `dev` is not available).

### Thank you !

Thank you for submitting your work !
