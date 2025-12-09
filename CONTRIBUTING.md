# Contributing to HAPI-FHIR

Thank you for your interest in contributing to HAPI-FHIR! As the leading open-source Java implementation of the HL7 FHIR specification, HAPI-FHIR serves healthcare organizations worldwide. Your contributions help advance healthcare interoperability.

---

## Table of Contents

- [Code of Conduct](#code-of-conduct)
- [Getting Started](#getting-started)
- [Development Environment](#development-environment)
- [How to Contribute](#how-to-contribute)
- [Coding Standards](#coding-standards)
- [Testing Requirements](#testing-requirements)
- [Documentation](#documentation)
- [Pull Request Process](#pull-request-process)
- [FHIR-Specific Guidelines](#fhir-specific-guidelines)
- [Community](#community)

---

## Code of Conduct

We are committed to providing a welcoming and inclusive environment. All participants are expected to:

- Be respectful and considerate
- Welcome newcomers and help them learn
- Focus on what is best for the community and healthcare interoperability
- Show empathy towards other community members

---

## Getting Started

### Prerequisites

| Requirement | Version | Purpose |
|-------------|---------|---------|
| **JDK** | 17+ | Build and test |
| **Maven** | 3.5.4+ | Build system |
| **Git** | Latest | Version control |
| **IDE** | IntelliJ IDEA recommended | Development |

### Quick Setup

```bash
# Clone the repository
git clone https://github.com/hapifhir/hapi-fhir.git
cd hapi-fhir

# Build without tests (faster initial build)
mvn install -DskipTests -P FASTINSTALL

# Run full build with tests
mvn install
```

### First-Time Setup Verification

```bash
# Verify the build completed successfully
mvn -version
java -version

# Run a quick sanity check
mvn test -pl hapi-fhir-base -Dtest=FhirContextTest
```

---

## Development Environment

### IDE Configuration

#### IntelliJ IDEA (Recommended)

1. **Import Project**
   - File → Open → Select `pom.xml`
   - Import as Maven project

2. **Configure Code Style**
   - File → Settings → Editor → Code Style → Java
   - Import scheme from: `.idea/codeStyles/` (if available)
   - Or configure manually:
     - Tab size: 4 (using tabs, not spaces)
     - Import order: Default, then `java.*`, `javax.*`, then static

3. **Configure Checkstyle**
   - Install Checkstyle-IDEA plugin
   - Configure to use `hapi-fhir-checkstyle/src/main/resources/checkstyle.xml`

4. **Enable Annotation Processing**
   - File → Settings → Build → Compiler → Annotation Processors
   - Enable annotation processing

#### Eclipse

1. Import as existing Maven project
2. Install Checkstyle plugin
3. Configure workspace encoding to UTF-8

### EditorConfig

The project uses `.editorconfig` for consistent formatting:

```ini
# .editorconfig
root = true

[*]
charset = utf-8
end_of_line = lf
insert_final_newline = true
trim_trailing_whitespace = true

[*.java]
indent_style = tab
indent_size = 4

[*.xml]
indent_style = tab
indent_size = 4

[*.md]
indent_style = space
indent_size = 2
```

---

## How to Contribute

### Types of Contributions

| Type | Description | Label |
|------|-------------|-------|
| 🐛 **Bug Fix** | Fix incorrect behavior | `bug` |
| ✨ **Feature** | New functionality | `enhancement` |
| 📚 **Documentation** | Improve docs | `documentation` |
| 🧪 **Tests** | Add/improve tests | `testing` |
| ♻️ **Refactor** | Code improvement | `refactor` |
| 🔧 **Tooling** | Build/CI improvements | `tooling` |

### Contribution Workflow

```
1. Find or Create Issue
        ↓
2. Fork Repository
        ↓
3. Create Feature Branch
        ↓
4. Make Changes
        ↓
5. Write Tests
        ↓
6. Update Documentation
        ↓
7. Submit Pull Request
        ↓
8. Address Review Feedback
        ↓
9. Merge! 🎉
```

### Step-by-Step Guide

#### 1. Find or Create an Issue

- Check [existing issues](https://github.com/hapifhir/hapi-fhir/issues)
- Look for `good first issue` or `help wanted` labels
- Create a new issue if one doesn't exist
- Wait for confirmation before starting major work

#### 2. Fork and Clone

```bash
# Fork via GitHub UI, then:
git clone https://github.com/YOUR-USERNAME/hapi-fhir.git
cd hapi-fhir
git remote add upstream https://github.com/hapifhir/hapi-fhir.git
```

#### 3. Create a Branch

```bash
# Sync with upstream
git fetch upstream
git checkout master
git merge upstream/master

# Create feature branch
git checkout -b feature/issue-1234-description
```

**Branch Naming Convention:**
- `feature/issue-XXX-short-description` - New features
- `bugfix/issue-XXX-short-description` - Bug fixes
- `docs/issue-XXX-short-description` - Documentation
- `refactor/issue-XXX-short-description` - Refactoring

#### 4. Make Changes

- Follow coding standards (see below)
- Keep commits focused and atomic
- Write meaningful commit messages

**Commit Message Format:**
```
type(scope): Short description (max 72 chars)

Longer description if needed. Explain the what and why,
not the how (the code shows that).

Fixes #1234
```

**Types:** `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`

**Scopes:** `base`, `client`, `server`, `jpa`, `validation`, `structures-r4`, etc.

#### 5. Submit Pull Request

- Push your branch to your fork
- Open PR against `master` branch
- Fill out the PR template completely
- Link related issues

---

## Coding Standards

### Code Style

HAPI-FHIR uses **Spotless** with **Palantir Java Format** for code formatting:

```bash
# Format code before committing
mvn spotless:apply

# Check formatting
mvn spotless:check
```

### Style Guidelines

| Rule | Standard |
|------|----------|
| **Indentation** | Tabs (4 spaces width) |
| **Line Length** | 120 characters max |
| **Braces** | Same line (K&R style) |
| **Imports** | No wildcards, organized |
| **Naming** | camelCase methods, PascalCase classes |

### Java Best Practices

```java
// ✅ Good: Clear, documented public API
/**
 * Parses a FHIR resource from JSON.
 *
 * @param theJson the JSON string to parse
 * @return the parsed resource
 * @throws DataFormatException if parsing fails
 */
public IBaseResource parseResource(String theJson) {
    Validate.notBlank(theJson, "theJson must not be blank");
    // Implementation
}

// ❌ Bad: Unclear, undocumented
public IBaseResource parse(String s) {
    // Implementation
}
```

### Null Safety

```java
// Use JSR-305 annotations
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MyService {
    
    @Nonnull
    public String processResource(@Nonnull IBaseResource resource, 
                                   @Nullable String optionalParam) {
        // Implementation
    }
}
```

### Exception Handling

```java
// Use HAPI error codes for consistent messaging
import ca.uhn.fhir.i18n.Msg;

throw new InvalidRequestException(
    Msg.code(1234) + "Resource ID is required for update operation"
);
```

### Logging

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClass {
    private static final Logger ourLog = LoggerFactory.getLogger(MyClass.class);
    
    public void processResource(IBaseResource resource) {
        ourLog.debug("Processing resource of type: {}", resource.fhirType());
        // Never log PHI!
        // ❌ ourLog.info("Patient name: {}", patient.getName());
    }
}
```

---

## Testing Requirements

### Test Coverage

| Area | Minimum Coverage |
|------|-----------------|
| New code | 85% |
| Bug fixes | Test that reproduces bug |
| Core modules | 80%+ |

### Test Structure

```
src/
├── main/java/       # Production code
└── test/java/       # Unit tests

# Integration tests in separate modules:
hapi-fhir-jpaserver-test-r4/
hapi-fhir-jpaserver-test-dstu3/
```

### Writing Tests

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class MyResourceProviderTest {

    private MyResourceProvider myProvider;

    @BeforeEach
    void setUp() {
        myProvider = new MyResourceProvider();
    }

    @Test
    void testReadResource_ValidId_ReturnsResource() {
        // Given
        String resourceId = "123";
        
        // When
        IBaseResource result = myProvider.read(new IdType(resourceId));
        
        // Then
        assertNotNull(result);
        assertEquals(resourceId, result.getIdElement().getIdPart());
    }

    @Test
    void testReadResource_InvalidId_ThrowsException() {
        // Given
        String invalidId = "";
        
        // When/Then
        assertThrows(InvalidRequestException.class, () -> {
            myProvider.read(new IdType(invalidId));
        });
    }
}
```

### Running Tests

```bash
# Run all tests
mvn test

# Run tests for a specific module
mvn test -pl hapi-fhir-base

# Run a specific test class
mvn test -pl hapi-fhir-base -Dtest=FhirContextTest

# Run a specific test method
mvn test -pl hapi-fhir-base -Dtest=FhirContextTest#testForR4

# Run with coverage
mvn test -P JACOCO
```

---

## Documentation

### Types of Documentation

| Type | Location | When to Update |
|------|----------|---------------|
| **Javadoc** | Source files | API changes |
| **User Docs** | `hapi-fhir-docs/` | Feature changes |
| **ADRs** | `docs/adr/` | Architecture decisions |
| **Changelog** | Release notes | Every PR |

### Javadoc Standards

```java
/**
 * Processes a FHIR Bundle containing multiple resources.
 * 
 * <p>This method handles both transaction and batch bundles according to
 * the FHIR specification. Transaction bundles are processed atomically,
 * while batch bundles allow partial success.</p>
 *
 * <h3>Example Usage:</h3>
 * <pre>
 * Bundle bundle = new Bundle();
 * bundle.setType(Bundle.BundleType.TRANSACTION);
 * // Add entries...
 * Bundle response = processor.processBundle(bundle);
 * </pre>
 *
 * @param theBundle the bundle to process, must not be null
 * @param theRequestDetails the request details for this operation
 * @return the response bundle containing operation outcomes
 * @throws InvalidRequestException if the bundle type is not supported
 * @throws TransactionFailedException if a transaction bundle fails
 * @since 6.0.0
 * @see <a href="https://www.hl7.org/fhir/http.html#transaction">FHIR Transaction</a>
 */
public Bundle processBundle(@Nonnull Bundle theBundle, 
                            @Nonnull RequestDetails theRequestDetails) {
    // Implementation
}
```

### Changelog Entry

Add a changelog entry for your PR in the appropriate file:

```markdown
## [Unreleased]

### Added
- Added support for X feature (#1234)

### Fixed
- Fixed issue with Y when Z (#1235)

### Changed
- Improved performance of A by B% (#1236)
```

---

## Pull Request Process

### PR Checklist

Before submitting, ensure:

- [ ] Code compiles without errors
- [ ] All tests pass locally
- [ ] New tests added for new functionality
- [ ] Javadoc added/updated for public APIs
- [ ] Code follows style guidelines (`mvn spotless:check`)
- [ ] Changelog entry added
- [ ] No unrelated changes included
- [ ] Commits are clean and well-described
- [ ] PR description is complete

### PR Template

```markdown
## Description
Brief description of changes.

## Related Issue
Fixes #(issue number)

## Type of Change
- [ ] Bug fix (non-breaking change fixing an issue)
- [ ] New feature (non-breaking change adding functionality)
- [ ] Breaking change (fix or feature causing existing functionality to change)
- [ ] Documentation update

## FHIR Version Impact
- [ ] All versions
- [ ] R5
- [ ] R4/R4B
- [ ] DSTU3
- [ ] DSTU2

## Testing
Describe tests added or modified.

## Checklist
- [ ] Tests pass locally
- [ ] Code follows style guidelines
- [ ] Javadoc updated
- [ ] Changelog updated
```

### Review Process

1. **Automated Checks** - CI must pass
2. **Code Review** - At least 1 approval required (2 for core modules)
3. **Testing Verification** - Reviewer may request additional tests
4. **Documentation Review** - Ensure docs are adequate

### After Merge

- Delete your feature branch
- Update any related issues
- Celebrate! 🎉

---

## FHIR-Specific Guidelines

### Working with FHIR Resources

```java
// Always use the appropriate FhirContext
FhirContext ctx = FhirContext.forR4();

// Use the generic IBaseResource when possible for version independence
public void processResource(IBaseResource resource) {
    String resourceType = resource.fhirType();
    // ...
}

// Use specific types when version-specific behavior is needed
public void processPatient(org.hl7.fhir.r4.model.Patient patient) {
    // R4-specific code
}
```

### FHIR Version Compatibility

| Scenario | Approach |
|----------|----------|
| New feature for all versions | Implement in `hapi-fhir-base` or `hapi-fhir-server` |
| Version-specific feature | Implement in appropriate `hapi-fhir-structures-*` |
| JPA feature | Implement in `hapi-fhir-jpaserver-base` |

### Search Parameter Development

```java
@Search
public List<Patient> searchByName(
    @RequiredParam(name = Patient.SP_NAME) StringParam theName,
    @OptionalParam(name = Patient.SP_BIRTHDATE) DateRangeParam theBirthdate
) {
    // Implementation
}
```

### Operation Development

```java
@Operation(name = "$my-operation", idempotent = true)
public Parameters myOperation(
    @OperationParam(name = "input") StringType theInput,
    RequestDetails theRequestDetails
) {
    // Implementation
}
```

---

## Community

### Getting Help

| Channel | Purpose | Response Time |
|---------|---------|---------------|
| [GitHub Discussions](https://github.com/hapifhir/hapi-fhir/discussions) | Questions, ideas | Community-driven |
| [Stack Overflow](https://stackoverflow.com/questions/tagged/hapi-fhir) | Technical Q&A | Community-driven |
| [Chat/Zulip](https://chat.fhir.org) | Real-time discussion | Varies |

### Maintainers

- **Smile CDR Team** - Primary maintainers
- **Community Contributors** - Various areas

### Recognition

Contributors are recognized in:
- Release notes
- GitHub contributor graph
- Project documentation

---

## License

By contributing to HAPI-FHIR, you agree that your contributions will be licensed under the [Apache License 2.0](LICENSE.txt).

---

## Questions?

If you have questions about contributing:

1. Check existing [documentation](https://hapifhir.io)
2. Search [GitHub Discussions](https://github.com/hapifhir/hapi-fhir/discussions)
3. Ask in [GitHub Discussions](https://github.com/hapifhir/hapi-fhir/discussions/new)

---

Thank you for contributing to HAPI-FHIR and advancing healthcare interoperability! 🏥💙

