# HAPI-FHIR Technical Debt Inventory

**Document Version:** 1.0  
**Date:** 2025-12-09  
**Status:** Active Tracking  
**Related:** ADR-001, PRD-001

---

## Executive Summary

This document catalogs technical debt items identified in the HAPI-FHIR codebase, categorized by module, priority, and type. Each item is tracked with a reference to enable GitHub issue creation.

**Total Items:** 46 TODO/FIXME/HACK instances across 30 files

---

## Priority Classification

| Priority | Definition | Response Time |
|----------|------------|---------------|
| 🔴 **Critical** | Security risk, data integrity, blocking | Immediate |
| 🟠 **High** | Significant functionality gap | Next sprint |
| 🟡 **Medium** | Code quality, maintainability | Backlog |
| 🟢 **Low** | Nice-to-have, minor improvement | Community |

---

## Technical Debt by Module

### 1. Validation Module (`hapi-fhir-validation`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-VAL-001 | `FHIRPathResourceGeneratorR4.java` | 146 | Unary expression not implemented | 🟠 High | Implementation |
| TD-VAL-002 | `FHIRPathResourceGeneratorR4.java` | 195 | UNDECL_EXT not implemented | 🟠 High | Implementation |
| TD-VAL-003 | `FHIRPathResourceGeneratorR4.java` | 346 | Upper function unimplemented | 🟡 Medium | Implementation |
| TD-VAL-004 | `FHIRPathResourceGeneratorR4.java` | 427 | UNDECL_EXT in switch not implemented | 🟠 High | Implementation |
| TD-VAL-005 | `FHIRPathResourceGeneratorR4.java` | 488 | Xor operation unimplemented | 🟡 Medium | Implementation |
| TD-VAL-006 | `FHIRPathResourceGeneratorR4.java` | 552 | Xor needs prior implementation | 🟡 Medium | Implementation |
| TD-VAL-007 | `FHIRPathResourceGeneratorR4.java` | 632 | Xor needs prior implementation | 🟡 Medium | Implementation |
| TD-VAL-008 | `ValidationSupportChain.java` | 177 | Code system support check needed | 🟡 Medium | Enhancement |
| TD-VAL-009 | `ValidatorWrapper.java` | 199 | Legacy message filtering - review needed | 🟢 Low | Cleanup |
| TD-VAL-010 | `CommonCodeSystemsTerminologyService.java` | 601 | R5 version handling for core lib bump | 🟠 High | Compatibility |

**Test Files:**
| ID | File | Description | Priority |
|----|------|-------------|----------|
| TD-VAL-T01 | `FhirInstanceValidatorR4Test.java` | Questionnaire validation comment | 🟢 Low |
| TD-VAL-T02 | `FhirInstanceValidatorR4BTest.java` | Waiting for core lib PR merge | 🟡 Medium |
| TD-VAL-T03 | `FhirInstanceValidatorR5Test.java` | Waiting for core lib PR merge | 🟡 Medium |
| TD-VAL-T04 | `ResourceValidatorDstu3Test.java` | Test needs re-enabling | 🟡 Medium |
| TD-VAL-T05 | `StructureMapTest.java` | TypeParam.Type should be Enum | 🟡 Medium |

---

### 2. Structures R4 Module (`hapi-fhir-structures-r4`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-R4-001 | `ClientR4Test.java` | 535 | Remove read annotation error handling | 🟢 Low | Documentation |
| TD-R4-002 | `ClientR4Test.java` | 997 | Document this behavior | 🟢 Low | Documentation |
| TD-R4-003 | `RDFParserTest.java` | 8 | Share FHIR JSON examples consideration | 🟢 Low | Enhancement |

---

### 3. Structures DSTU3 Module (`hapi-fhir-structures-dstu3`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-D3-001 | `ServerCapabilityStatementProvider.java` | 248 | Make acceptUnknown configurable | 🟡 Medium | Enhancement |
| TD-D3-002 | `XmlParserDstu3Test.java` | 3000 | Implement Bundle with Binary test | 🟡 Medium | Test |
| TD-D3-003 | `XmlParserDstu3Test.java` | 3314 | Test should work - investigate | 🟡 Medium | Test |
| TD-D3-004 | `ModelScannerDstu3Test.java` | 148 | Re-enable when Claim compartments fixed | 🟡 Medium | Test |
| TD-D3-005 | `ReferenceParamTest.java` | 113, 140 | Verify behavior correctness | 🟡 Medium | Test |

---

### 4. Structures DSTU2 Module (`hapi-fhir-structures-dstu2`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-D2-001 | `ServerConformanceProvider.java` | 196 | Make acceptUnknown configurable | 🟡 Medium | Enhancement |
| TD-D2-002 | `BoundCodeableConceptDt.java` | 139 | Throw special exception type | 🟡 Medium | Error Handling |
| TD-D2-003 | `XmlParserDstu2Test.java` | 2508 | Implement Bundle with Binary test | 🟢 Low | Test |
| TD-D2-004 | `JsonParserDstu2Test.java` | 1519 | Preserve comments feature | 🟢 Low | Enhancement |
| TD-D2-005 | `GenericClientDstu2Test.java` | 2723 | Implement getEncoding stub | 🟢 Low | Test |

---

### 5. Structures DSTU2.1 Module (`hapi-fhir-structures-dstu2.1`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-D21-001 | `ServerConformanceProvider.java` | 192 | Make acceptUnknown configurable | 🟡 Medium | Enhancement |
| TD-D21-002 | `XmlParserDstu2_1Test.java` | 2143 | Implement Bundle with Binary test | 🟢 Low | Test |
| TD-D21-003 | `XmlParserDstu2_1Test.java` | 2451 | Test should work - investigate | 🟢 Low | Test |

---

### 6. Structures HL7Org DSTU2 Module (`hapi-fhir-structures-hl7org-dstu2`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-HL7D2-001 | `ServerConformanceProvider.java` | 188 | Make acceptUnknown configurable | 🟡 Medium | Enhancement |
| TD-HL7D2-002 | `XmlParserHl7OrgDstu2Test.java` | 403 | Uncomment with model updates | 🟢 Low | Test |
| TD-HL7D2-003 | `ResourceWithExtensionsA.java` | Multiple | Auto-generated stubs need implementation | 🟢 Low | Implementation |

---

### 7. Structures R5 Module (`hapi-fhir-structures-r5`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-R5-001 | `HapiWorkerContext.java` | 263 | Verify noInactive handling | 🟡 Medium | Verification |

---

### 8. Storage Module (`hapi-fhir-storage`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-STO-001 | `MemoryCacheService.java` | 48 | Extract interface for caching | 🟡 Medium | Refactoring |
| TD-STO-002 | `ResponseBundleBuilderTest.java` | 220 | Add PARAM_OFFSET test | 🟢 Low | Test |

---

### 9. Test Utilities (`hapi-fhir-test-utilities`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-TU-001 | `HttpClientExtension.java` | 34 | Merge with HttpClientHelper | 🟡 Medium | Refactoring |
| TD-TU-002 | `RestServerR4Helper.java` | 236 | Add data to observation | 🟢 Low | Enhancement |

---

### 10. Testpage Overlay (`hapi-fhir-testpage-overlay`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-TO-001 | `Controller.java` | 461 | Remove unused queries parameter | 🟢 Low | Cleanup |

---

### 11. Tinder Plugin (`hapi-tinder-plugin`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-TP-001 | `BaseStructureSpreadsheetParser.java` | 412 | Change back to exception for composite params | 🟡 Medium | Error Handling |
| TD-TP-002 | `BaseElement.java` | 143 | Implement isExtensionLocal | 🟡 Medium | Implementation |
| TD-TP-003 | `BaseElement.java` | 147 | Implement isExtensionModifier | 🟡 Medium | Implementation |
| TD-TP-004 | `BoundCodeableConceptDt.java` | 139 | Throw special exception type | 🟡 Medium | Error Handling |

---

### 12. OSGi Integration Tests (`osgi/`)

| ID | File | Line | Description | Priority | Type |
|----|------|------|-------------|----------|------|
| TD-OSGI-001 | `Dstu3XmlParserTest.java` | 2721 | Implement Bundle with Binary test | 🟢 Low | Test |
| TD-OSGI-002 | `XmlParserDstu2_1Test.java` | 2061 | Implement Bundle with Binary test | 🟢 Low | Test |
| TD-OSGI-003 | `ResourceWithExtensionsA.java` | Multiple | Auto-generated stubs | 🟢 Low | Implementation |

---

## Summary by Priority

| Priority | Count | Percentage |
|----------|-------|------------|
| 🔴 Critical | 0 | 0% |
| 🟠 High | 5 | 11% |
| 🟡 Medium | 23 | 50% |
| 🟢 Low | 18 | 39% |
| **Total** | **46** | **100%** |

---

## Summary by Type

| Type | Count | Description |
|------|-------|-------------|
| Implementation | 12 | Incomplete feature implementation |
| Test | 12 | Missing or disabled tests |
| Enhancement | 8 | Feature improvements |
| Refactoring | 3 | Code restructuring needed |
| Error Handling | 3 | Exception handling improvements |
| Cleanup | 3 | Dead code, unused parameters |
| Documentation | 2 | Missing documentation |
| Verification | 1 | Behavior confirmation needed |
| Compatibility | 2 | Version compatibility issues |

---

## Recommended Approach

### Phase 1: High Priority Items (Sprint 1-2)

1. **TD-VAL-001, 002, 004** - FHIRPath expression implementation gaps
2. **TD-VAL-010** - R5 compatibility fix for core lib

### Phase 2: Medium Priority Items (Sprint 3-6)

1. **Configuration Items** - Make `acceptUnknown` configurable across modules
2. **Refactoring** - `MemoryCacheService` interface extraction
3. **Test Utilities** - Merge `HttpClientExtension` with `HttpClientHelper`

### Phase 3: Low Priority Items (Community/Backlog)

1. Auto-generated stubs in test fixtures
2. Documentation improvements
3. Legacy test implementations

---

## GitHub Issue Creation

Use the templates in `.github/ISSUE_TEMPLATE/` to create issues for these items.

Recommended labels:
- `tech-debt`
- `priority:high`, `priority:medium`, `priority:low`
- `type:implementation`, `type:test`, `type:enhancement`, etc.
- Module-specific: `module:validation`, `module:structures-r4`, etc.

---

## Tracking Updates

| Date | Action | By |
|------|--------|----|
| 2025-12-09 | Initial inventory created | System |

---

*This document should be updated as items are resolved or new items are discovered.*

