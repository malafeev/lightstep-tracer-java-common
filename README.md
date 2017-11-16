# Lightstep Tracer Common

[![MIT license](http://img.shields.io/badge/license-MIT-blue.svg)](http://opensource.org/licenses/MIT)

The core LightStep distributed tracing library for the Java runtime environment. For specific documentation
see [lightstep-tracer-java](https://github.com/lightstep/lightstep-tracer-java) or
[lightstep-tracer-android](https://github.com/lightstep/lightstep-tracer-android).

## common

Contains the `com.lightstep.tracer.shared` (shared logic) and `com.lightstep.tracer.grpc` (shared compiled proto files) source files and assets for the JRE and Android libraries.

## example

Contains an example implementation of the tracer (used for integration testing).

## grpc

Contains the transport layer specific to the grpc flavor of the lightstep tracer.

## okhttp

Contains the transport layer specific to the okhttp flavor of the lightstep tracer.

_N.B. http is an experimental feature for the lightstep tracer and collector. Please reach out before attempting to enable it._

## Development info

See [DEV.md](DEV.md) for information on contributing to this instrumentation library.
