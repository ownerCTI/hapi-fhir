# ADR-002: Architecture Improvements Roadmap

**Status:** Proposed  
**Date:** 2025-12-09  
**Decision Makers:** Development Team  
**Technical Area:** Architecture Evolution, Technical Debt Reduction  
**Supersedes:** None  
**Related:** ADR-001 (Repository Assessment)

---

## Context

Following the comprehensive assessment documented in ADR-001, this ADR outlines a structured roadmap for architectural improvements to the HAPI-FHIR project. The improvements are organized into phases to ensure minimal disruption to existing functionality while systematically addressing technical debt and modernizing the codebase.

---

## Decision Drivers

1. **Maintainability**: Reduce complexity and improve code quality
2. **Security**: Address potential vulnerabilities proactively
3. **Performance**: Optimize critical paths and resource usage
4. **Modernization**: Keep pace with Java ecosystem evolution
5. **Community**: Lower barriers for new contributors

---

## Decision

We propose a phased approach to architectural improvements organized into four phases over 12-18 months.

---

## Phase 1: Foundation (Months 1-3)

### 1.1 Documentation Infrastructure

| Task | Priority | Effort | Owner |
|------|----------|--------|-------|
| Establish ADR practice and templates | High | 1 week | Architecture |
| Create SECURITY.md | High | 2 days | Security |
| Create CONTRIBUTING.md | High | 3 days | Community |
| Update README.md (remove deprecated badges) | Medium | 1 day | Any |
| Create developer onboarding guide | Medium | 1 week | Documentation |

**ADR Template to Adopt:**

```markdown
# ADR-XXX: [Title]

**Status:** [Proposed | Accepted | Deprecated | Superseded]
**Date:** YYYY-MM-DD
**Decision Makers:** [Team/Individuals]
**Technical Area:** [Area]

## Context
[Why is this decision needed?]

## Decision
[What is the change?]

## Consequences
[What are the results?]
```

### 1.2 Technical Debt Triage

| Task | Priority | Effort |
|------|----------|--------|
| Audit all TODO/FIXME comments | High | 3 days |
| Create GitHub issues for actionable items | High | 2 days |
| Remove outdated TODO.txt | High | 1 hour |
| Categorize by module and severity | Medium | 2 days |

**Proposed TODO Categories:**

| Category | Label | Action |
|----------|-------|--------|
| Security | `tech-debt:security` | Immediate attention |
| Performance | `tech-debt:performance` | Schedule in sprint |
| Code Quality | `tech-debt:quality` | Backlog |
| Documentation | `tech-debt:docs` | Community contribution |
| Deprecated | `tech-debt:deprecated` | Remove or update |

### 1.3 CI/CD Enhancement

| Task | Priority | Effort |
|------|----------|--------|
| Add TODO/FIXME count to CI reporting | Medium | 1 day |
| Implement dependency vulnerability scanning | High | 2 days |
| Add code coverage gates | Medium | 1 day |
| Create build time tracking dashboard | Low | 1 day |

---

## Phase 2: Modernization (Months 4-6)

### 2.1 Dependency Updates

#### Spring Boot Migration Strategy

```
Current: Spring Boot 2.7.12
Target:  Spring Boot 3.x (LTS)

Migration Path:
1. Audit javax.* → jakarta.* namespace changes
2. Update Hibernate to 6.x compatible
3. Update Spring Framework to 6.x
4. Test all modules for compatibility
5. Update documentation
```

**Dependency Update Priority Matrix:**

| Dependency | Current | Target | Risk | Phase |
|------------|---------|--------|------|-------|
| Spring Boot | 2.7.12 | 3.2.x | High | 2.1 |
| Hibernate | 5.6.15 | 6.4.x | High | 2.1 |
| Jetty | 10.0.16 | 12.x | Medium | 2.2 |
| Jackson | 2.15.2 | 2.17.x | Low | 2.1 |
| Mockito | 4.8.1 | 5.x | Low | 2.2 |
| JUnit | 5.9.1 | 5.10.x | Low | 2.2 |

### 2.2 Java Version Strategy

```
Current Build: JDK 17 (compile target: 11)
Current Tests: JDK 17

Proposed Evolution:
Phase 2: Maintain JDK 11 target, JDK 17 build
Phase 3: JDK 17 target (when Spring Boot 3 adopted)
Phase 4: JDK 21 LTS evaluation
```

### 2.3 Code Quality Improvements

| Improvement | Tool/Approach | Benefit |
|-------------|---------------|---------|
| Null safety annotations | @Nullable/@Nonnull | Reduce NPEs |
| Record classes | Java 17+ | Reduce boilerplate |
| Sealed classes | Java 17+ | Type safety |
| Pattern matching | Java 21+ | Code clarity |

---

## Phase 3: Optimization (Months 7-9)

### 3.1 Performance Improvements

#### Critical Path Analysis

| Component | Current State | Optimization Target |
|-----------|--------------|---------------------|
| FhirContext initialization | Expensive (full scan) | Lazy loading |
| Parser instantiation | Per-request | Pooling consideration |
| Validation | Full chain | Caching improvements |
| Search | Full index scan possible | Query optimization |

#### Caching Strategy Review

```
Current Caching Architecture:
- Caffeine (default)
- Guava (alternative)
- Custom implementations via SPI

Optimization Areas:
1. FhirContext caching per version
2. Validation result caching
3. Search parameter definition caching
4. Terminology lookup caching
```

### 3.2 Build Optimization

| Optimization | Expected Improvement |
|--------------|---------------------|
| Parallel module builds | 20-30% time reduction |
| Incremental compilation | Faster dev cycles |
| Test parallelization tuning | 15-25% time reduction |
| Dependency caching | Network I/O reduction |

### 3.3 Memory Optimization

| Area | Current Issue | Proposed Solution |
|------|---------------|-------------------|
| Resource scanning | High heap usage | Streaming/pagination |
| Batch operations | Memory spikes | Chunked processing |
| Validation contexts | Multiple instances | Shared pools |

---

## Phase 4: Evolution (Months 10-12)

### 4.1 Legacy Support Transition

#### DSTU2/DSTU2.1 Deprecation Timeline

```
Month 10: Announce deprecation
Month 12: Deprecation warnings in logs
Month 18: Move to separate repository/branch
Month 24: End of maintenance
```

#### Migration Support

| From | To | Migration Guide |
|------|-----|-----------------|
| DSTU2 | R4 | Required |
| DSTU2.1 | R4 | Required |
| DSTU3 | R4 | Recommended |
| R4 | R5 | In progress |

### 4.2 API Evolution

#### Proposed API Improvements

| Area | Current | Proposed |
|------|---------|----------|
| Client fluent API | Good | Enhanced builder pattern |
| Server resource providers | Annotation-based | Keep + add programmatic |
| Validation API | Callback-based | Reactive option |
| Batch API | Sync-first | Async-first option |

### 4.3 Observability Enhancement

| Feature | Current | Target |
|---------|---------|--------|
| Metrics | Basic | Micrometer integration |
| Tracing | Manual | OpenTelemetry support |
| Logging | SLF4J | Structured logging (JSON) |
| Health | Custom | Actuator alignment |

---

## Implementation Guidelines

### Change Management Process

```
1. Create GitHub Issue
   └── Link to relevant ADR
   └── Define acceptance criteria
   
2. Create Feature Branch
   └── Follow naming: feature/ADR-XXX-description
   
3. Implementation
   └── Follow existing code style
   └── Add/update tests
   └── Update documentation
   
4. Pull Request
   └── Link issue
   └── Require 2 approvals for core changes
   └── Pass all CI checks
   
5. Release
   └── Include in changelog
   └── Update version appropriately
```

### Breaking Change Policy

| Change Type | Handling |
|-------------|----------|
| API removal | Deprecate for 2 major versions |
| Behavior change | Feature flag, then default |
| Dependency bump | Document migration path |
| Configuration change | Backward-compatible first |

### Testing Requirements

| Phase | Coverage Requirement |
|-------|---------------------|
| Phase 1 | Maintain existing |
| Phase 2 | 80% for changed code |
| Phase 3 | 85% for new optimizations |
| Phase 4 | 90% for new APIs |

---

## Risks and Mitigations

### Technical Risks

| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| Spring Boot 3 incompatibility | Medium | High | Thorough testing, feature flags |
| Performance regression | Low | High | Benchmark suite, A/B testing |
| Breaking existing integrations | Medium | High | Semantic versioning, changelog |

### Process Risks

| Risk | Probability | Impact | Mitigation |
|------|-------------|--------|------------|
| Resource constraints | High | Medium | Prioritization, community involvement |
| Scope creep | Medium | Medium | ADR discipline, phase gates |
| Community resistance | Low | Medium | Communication, migration support |

---

## Success Metrics

### Phase 1 Success Criteria
- [ ] ADR practice established
- [ ] All high-priority documentation created
- [ ] Technical debt inventory complete
- [ ] CI improvements deployed

### Phase 2 Success Criteria
- [ ] Spring Boot 3.x migration complete
- [ ] All dependencies updated to latest stable
- [ ] No critical vulnerabilities

### Phase 3 Success Criteria
- [ ] 20% build time improvement
- [ ] No memory regression
- [ ] Performance benchmarks established

### Phase 4 Success Criteria
- [ ] Legacy deprecation announced
- [ ] Migration guides published
- [ ] Observability features available

---

## Consequences

### Positive
- Modernized, maintainable codebase
- Improved security posture
- Better developer experience
- Clearer governance

### Negative
- Significant effort investment
- Potential short-term disruption
- Learning curve for new patterns

### Neutral
- Some existing patterns will change
- Documentation will require ongoing maintenance

---

## References

| Reference | Link |
|-----------|------|
| ADR-001 | [Repository Assessment](./ADR-001-repository-assessment.md) |
| Spring Boot 3 Migration | https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide |
| Jakarta EE Migration | https://jakarta.ee/resources/jakarta-ee-9-migration/ |
| HAPI-FHIR Documentation | https://hapifhir.io |

---

**Document History:**
- 2025-12-09: Initial version proposed

