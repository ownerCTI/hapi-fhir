# HAPI FHIR

**HAPI FHIR - Java API for HL7 FHIR Clients and Servers**

[![License][Badge-License]][Link-License]
[![Maven Central][Badge-MavenCentral]][Link-MavenCentral]

The leading open-source implementation of the HL7 FHIR specification for Java.

---

## CI/CD Status

| CI Status (master) | SNAPSHOT Pipeline | Current Release |
| :---: | :---: | :---: |
| [![Build Status][Badge-AzurePipelineMaster]][Link-AzurePipelinesMaster] | [![Build Status][Badge-AzureReleaseSnapshot]][Link-AzurePipelinesSnapshot] | [![Release Artifacts][Badge-MavenCentral]][Link-MavenCentral] |

## Code Quality

[![codecov][Badge-CodeCov]][Link-CodeCov]

---

## 🚀 Quick Start

### Maven

```xml
<dependency>
    <groupId>ca.uhn.hapi.fhir</groupId>
    <artifactId>hapi-fhir-base</artifactId>
    <version>6.10.0</version>
</dependency>

<!-- Choose your FHIR version -->
<dependency>
    <groupId>ca.uhn.hapi.fhir</groupId>
    <artifactId>hapi-fhir-structures-r4</artifactId>
    <version>6.10.0</version>
</dependency>
```

### Basic Usage

```java
// Create a FHIR context for R4
FhirContext ctx = FhirContext.forR4();

// Parse a resource
Patient patient = ctx.newJsonParser().parseResource(Patient.class, jsonString);

// Create a client
IGenericClient client = ctx.newRestfulGenericClient("http://hapi.fhir.org/baseR4");

// Read a resource
Patient result = client.read().resource(Patient.class).withId("123").execute();
```

---

## 📚 Documentation

| Resource | Link |
|----------|------|
| **Complete Documentation** | [hapifhir.io](http://hapifhir.io) |
| **Live Demo Server** | [hapi.fhir.org](http://hapi.fhir.org/) |
| **Javadoc** | [hapifhir.io/hapi-fhir/apidocs/](https://hapifhir.io/hapi-fhir/apidocs/) |
| **Getting Help** | [Wiki - Getting Help][Link-wiki] |

---

## ✨ Features

### FHIR Version Support

| Version | Module | Status |
|---------|--------|--------|
| **R5** | `hapi-fhir-structures-r5` | ✅ Supported |
| **R4B** | `hapi-fhir-structures-r4b` | ✅ Supported |
| **R4** | `hapi-fhir-structures-r4` | ✅ Supported |
| **DSTU3** | `hapi-fhir-structures-dstu3` | ✅ Supported |
| **DSTU2** | `hapi-fhir-structures-dstu2` | ⚠️ Maintenance |

### Core Capabilities

- 🔄 **Client** - Fluent REST client for any FHIR server
- 🖥️ **Server** - Build your own FHIR server
- 💾 **JPA Server** - Full FHIR server with database persistence
- ✅ **Validation** - Profile and resource validation
- 🔐 **Security** - SMART on FHIR, OAuth 2.0 support
- 📊 **Subscriptions** - Real-time notifications
- 🔗 **MDM** - Master Data Management

---

## 📦 Project Structure

```
hapi-fhir/
├── hapi-fhir-base/              # Core library
├── hapi-fhir-client/            # REST client
├── hapi-fhir-server/            # Server framework
├── hapi-fhir-jpaserver-*/       # JPA server modules
├── hapi-fhir-structures-*/      # FHIR version structures
├── hapi-fhir-validation/        # Validation support
└── docs/                        # Project documentation
    ├── adr/                     # Architecture Decision Records
    └── prd/                     # Product Requirements
```

---

## 🤝 Contributing

We welcome contributions! Please see our [Contributing Guide](CONTRIBUTING.md) for details on:

- Setting up your development environment
- Coding standards and testing requirements
- Pull request process

Looking to help? Check out issues labeled [`good first issue`](https://github.com/hapifhir/hapi-fhir/labels/good%20first%20issue) or [`help wanted`](https://github.com/hapifhir/hapi-fhir/labels/help%20wanted).

---

## 🔒 Security

For security vulnerabilities, please see our [Security Policy](SECURITY.md).

**Do not report security vulnerabilities through public GitHub issues.**

---

## 📄 License

This project is Open Source, licensed under the [Apache Software License 2.0](LICENSE.txt).

---

## 💼 Commercial Support

For commercial support and enterprise features, please see [Smile CDR][Link-SmileCDR].

---

## 🙏 Acknowledgments

HAPI FHIR is made possible by contributions from:
- [Smile CDR](https://smilecdr.com)
- [University Health Network](https://www.uhn.ca/)
- [Community contributors](https://github.com/hapifhir/hapi-fhir/graphs/contributors)

---

<!-- Links -->
[Link-AzurePipelines]: https://dev.azure.com/hapifhir/HAPI%20FHIR/_build
[Link-AzurePipelinesMaster]: https://dev.azure.com/hapifhir/HAPI%20FHIR/_build?definitionId=2
[Link-AzurePipelinesSnapshot]: https://dev.azure.com/hapifhir/HAPI%20FHIR/_build?definitionId=3
[Link-MavenCentral]: https://search.maven.org/search?q=g:ca.uhn.hapi.fhir
[Link-CodeCov]: https://codecov.io/gh/hapifhir/hapi-fhir
[Link-wiki]: https://github.com/hapifhir/hapi-fhir/wiki/Getting-Help
[Link-SmileCDR]: https://smilecdr.com
[Link-License]: https://hapifhir.io/hapi-fhir/license.html

<!-- Badges -->
[Badge-AzurePipelineMaster]: https://dev.azure.com/hapifhir/HAPI%20FHIR/_apis/build/status/hapifhir.hapi-fhir?branchName=master
[Badge-AzureReleaseSnapshot]: https://dev.azure.com/hapifhir/HAPI%20FHIR/_apis/build/status/SNAPSHOT%20pipeline?branchName=master
[Badge-MavenCentral]: https://img.shields.io/maven-central/v/ca.uhn.hapi.fhir/hapi-fhir-base.svg?label=Maven%20Central
[Badge-CodeCov]: https://codecov.io/gh/hapifhir/hapi-fhir/branch/master/graph/badge.svg?token=zHfnKfQB9X
[Badge-License]: https://img.shields.io/badge/license-apache%202.0-60C060.svg
