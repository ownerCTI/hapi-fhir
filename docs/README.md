# HAPI-FHIR Documentation

This directory contains project governance, planning, and technical documentation for the HAPI-FHIR project.

---

## 📁 Directory Structure

```
docs/
├── README.md                           # This file
├── TECHNICAL_DEBT_INVENTORY.md         # Tracked technical debt items
├── adr/                                # Architecture Decision Records
│   ├── ADR-001-repository-assessment.md
│   └── ADR-002-architecture-improvements.md
└── prd/                                # Product Requirements Documents
    └── PRD-001-enhancement-plan.md
```

---

## 📋 Architecture Decision Records (ADRs)

ADRs document significant architectural decisions made during the project's evolution.

| ADR | Title | Status | Date |
|-----|-------|--------|------|
| [ADR-001](adr/ADR-001-repository-assessment.md) | Repository Assessment and Technical Debt Inventory | Accepted | 2025-12-09 |
| [ADR-002](adr/ADR-002-architecture-improvements.md) | Architecture Improvements Roadmap | Proposed | 2025-12-09 |

### Creating New ADRs

Use the following naming convention: `ADR-XXX-short-title.md`

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

---

## 📑 Product Requirements Documents (PRDs)

PRDs outline feature requirements, enhancement plans, and product roadmaps.

| PRD | Title | Status | Date |
|-----|-------|--------|------|
| [PRD-001](prd/PRD-001-enhancement-plan.md) | HAPI-FHIR Enhancement Plan | Draft | 2025-12-09 |

---

## 🔧 Technical Debt Tracking

| Document | Description |
|----------|-------------|
| [TECHNICAL_DEBT_INVENTORY.md](TECHNICAL_DEBT_INVENTORY.md) | Categorized inventory of 46 TODO/FIXME items |

### Summary by Priority

| Priority | Count |
|----------|-------|
| 🔴 Critical | 0 |
| 🟠 High | 5 |
| 🟡 Medium | 23 |
| 🟢 Low | 18 |

---

## 🔗 Related Documentation

| Document | Location | Description |
|----------|----------|-------------|
| Main README | [/README.md](../README.md) | Project overview |
| Security Policy | [/SECURITY.md](../SECURITY.md) | Vulnerability reporting |
| Contributing | [/CONTRIBUTING.md](../CONTRIBUTING.md) | Contribution guidelines |
| HAPI-FHIR Docs | [/hapi-fhir-docs](../hapi-fhir-docs/) | User documentation module |
| Help Wanted | [/HELPWANTED.md](../HELPWANTED.md) | Contribution opportunities |

---

## 🎫 GitHub Issue Templates

Issue templates are available in `/.github/ISSUE_TEMPLATE/`:

| Template | Purpose |
|----------|---------|
| `bug_report.yml` | Report bugs and unexpected behavior |
| `feature_request.yml` | Suggest new features |
| `technical_debt.yml` | Track technical debt items |

---

## 📝 Contributing to Documentation

1. **ADRs**: Required for any significant architectural changes
2. **PRDs**: Required for major feature additions or enhancement plans
3. **Technical Debt**: Update inventory when resolving or discovering items
4. Follow existing document formats and templates
5. Update index tables when adding new documents

---

## 📅 Document History

| Date | Action | Documents |
|------|--------|-----------|
| 2025-12-09 | Initial creation | ADR-001, ADR-002, PRD-001, TECHNICAL_DEBT_INVENTORY |
| 2025-12-09 | Added GitHub templates | Issue templates, PR template |
