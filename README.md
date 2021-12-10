# QTrade

This is the monorepo of the QTrade stack.

<!-- TODO: describe functionalities -->

# Get Started

## Usage

The project will be available in OCI containers.

<!-- TODO: containerized -->

## Development

Install a JDK (Java 11 recommended) and [Bazel](https://docs.bazel.build/versions/main/install.html)

This project uses [Bazelisk](https://docs.bazel.build/versions/main/install-bazelisk.html)

```shell
bazel build //...

bazel test //...

# update dependencies
bazel run @unpinned_maven//:pin
```
