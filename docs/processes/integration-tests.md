# Integration Tests

We have taken a ktor-client + Jsoup based approach to automatically **integration test** our application.
Since the sole user interface are HTML Views, we will use ktor-client to retrieve the views using API and then use Jsoup Parse combinded with junit asserts to test for the expectations.

We are locating this Junit based suite in `integration` and is separate from the whole project build and can be run in the integration-test directory.

`../gradlew test` which is also part of the `:build` task as of now.
We are using integration tests as the gate-keeper for staging promotion to deployment, it is currently less flexible.

It has been added as a Github Action Workflow to run on staging as `integration-test-gradle` which runs the job on our staging environment.