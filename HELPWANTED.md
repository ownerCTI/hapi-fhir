# Help Wanted 🙋

Welcome to HAPI-FHIR! We're thrilled you're interested in contributing to healthcare interoperability.

This page lists opportunities for community contributions organized by skill level and area.

---

## 🚀 Getting Started

1. **Read our [Contributing Guide](CONTRIBUTING.md)** - Development setup and coding standards
2. **Review the [Security Policy](SECURITY.md)** - Important for healthcare software
3. **Check [Documentation](docs/)** - ADRs and technical debt inventory

---

## 🏷️ Issues by Label

| Label | Description |
|-------|-------------|
| [`good first issue`](https://github.com/hapifhir/hapi-fhir/labels/good%20first%20issue) | Great for newcomers |
| [`help wanted`](https://github.com/hapifhir/hapi-fhir/labels/help%20wanted) | Community help appreciated |
| [`tech-debt`](https://github.com/hapifhir/hapi-fhir/labels/tech-debt) | Code improvement tasks |
| [`documentation`](https://github.com/hapifhir/hapi-fhir/labels/documentation) | Docs improvements |

---

## 🟢 Beginner-Friendly Tasks

### Documentation Improvements
- [ ] Improve Javadoc for public APIs
- [ ] Add code examples to documentation
- [ ] Fix typos and broken links
- [ ] Translate documentation

### Test Improvements
- [ ] Add tests for uncovered code paths
- [ ] Improve test assertions and error messages
- [ ] Add test data fixtures

### Code Cleanup
- [ ] Resolve TODO comments (see [Technical Debt Inventory](docs/TECHNICAL_DEBT_INVENTORY.md))
- [ ] Remove deprecated code
- [ ] Improve log messages

---

## 🟡 Intermediate Tasks

### Feature Enhancements
- [ ] Investigate adding support for FHIR's RDF (Turtle) encoding to HAPI
- [ ] Improve error messages for common mistakes
- [ ] Add support for additional FHIR operations

### Technical Debt (from [Inventory](docs/TECHNICAL_DEBT_INVENTORY.md))

| ID | Description | Module |
|----|-------------|--------|
| TD-VAL-008 | Code system support check in ValidationSupportChain | validation |
| TD-STO-001 | Extract interface for MemoryCacheService | storage |
| TD-TU-001 | Merge HttpClientExtension with HttpClientHelper | test-utilities |

### Performance
- [ ] Profile and optimize hot paths
- [ ] Improve caching effectiveness
- [ ] Reduce memory allocations

---

## 🟠 Advanced Tasks

### Architecture Improvements
- [ ] Spring Boot 3.x migration support
- [ ] Hibernate 6.x compatibility
- [ ] OpenTelemetry integration

### FHIR Specification
- [ ] R5 feature parity
- [ ] New search parameter types
- [ ] Bulk data improvements

### Security
- [ ] Security audit assistance
- [ ] Penetration testing
- [ ] SMART on FHIR enhancements

---

## 🔴 Expert Tasks

### Core Library
- [ ] FHIRPath engine optimizations (see TD-VAL-001 through TD-VAL-007)
- [ ] Parser performance improvements
- [ ] Validation engine enhancements

### Integration
- [ ] Cloud-native deployment guides
- [ ] Kubernetes operators
- [ ] Database optimization for specific vendors

---

## 📊 Technical Debt Summary

From our [Technical Debt Inventory](docs/TECHNICAL_DEBT_INVENTORY.md):

| Priority | Count | Good for |
|----------|-------|----------|
| 🟠 High | 5 | Experienced contributors |
| 🟡 Medium | 23 | Intermediate contributors |
| 🟢 Low | 18 | Beginners welcome |

---

## 🤝 How to Claim a Task

1. **Check existing issues** - Someone may already be working on it
2. **Comment on the issue** - Say "I'd like to work on this"
3. **Wait for confirmation** - A maintainer will assign you
4. **Submit a PR** - Follow the [Contributing Guide](CONTRIBUTING.md)

---

## 💬 Getting Help

- **Questions**: [GitHub Discussions](https://github.com/hapifhir/hapi-fhir/discussions)
- **Real-time chat**: [FHIR Zulip](https://chat.fhir.org)
- **Stack Overflow**: [hapi-fhir tag](https://stackoverflow.com/questions/tagged/hapi-fhir)

---

## 🏆 Recognition

Contributors are recognized in:
- Release notes
- GitHub contributor graph
- This page (for significant contributions)

---

Thank you for helping advance healthcare interoperability! 🏥💙
