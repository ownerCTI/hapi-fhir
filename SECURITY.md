# Security Policy

## HAPI-FHIR Security

HAPI-FHIR is a critical healthcare infrastructure component used by healthcare organizations worldwide to handle Protected Health Information (PHI) and other sensitive medical data. We take security extremely seriously and appreciate the community's efforts in responsibly disclosing vulnerabilities.

---

## 🔒 Supported Versions

We provide security updates for the following versions:

| Version | Supported | End of Support |
|---------|-----------|----------------|
| 6.x.x   | ✅ Yes    | Current        |
| 5.x.x   | ✅ Yes    | TBD            |
| 4.x.x   | ⚠️ Critical only | 2024-12-31 |
| < 4.0   | ❌ No     | Ended          |

**Recommendation:** Always use the latest stable release for production healthcare systems.

---

## 🛡️ Security Considerations for Healthcare Deployments

### HIPAA/HITECH Compliance

HAPI-FHIR is designed to support HIPAA-compliant deployments when properly configured:

| Feature | Security Consideration |
|---------|----------------------|
| **Transport Security** | Always use TLS 1.2+ in production |
| **Authentication** | Implement OAuth 2.0/SMART on FHIR |
| **Authorization** | Use consent interceptors and access controls |
| **Audit Logging** | Enable and monitor audit events |
| **Data at Rest** | Use encrypted database storage |
| **PHI in Logs** | Configure log sanitization |

### GDPR/Privacy Considerations

| Requirement | HAPI-FHIR Support |
|-------------|-------------------|
| Right to Erasure | `$expunge` operation |
| Data Portability | Native FHIR export |
| Consent Management | Consent resource support |
| Audit Trail | AuditEvent resource |

---

## 🚨 Reporting a Vulnerability

### How to Report

**DO NOT** report security vulnerabilities through public GitHub issues.

#### Option 1: GitHub Security Advisories (Preferred)

1. Go to [Security Advisories](https://github.com/hapifhir/hapi-fhir/security/advisories)
2. Click "Report a vulnerability"
3. Provide detailed information (see template below)

#### Option 2: Email

Send reports to: **security@smilecdr.com**

Use this PGP key for sensitive communications:
```
[PGP key to be published by Smile CDR security team]
```

### What to Include

Please provide as much of the following information as possible:

```markdown
## Vulnerability Report

### Summary
[Brief description of the vulnerability]

### Affected Versions
- [ ] 6.x
- [ ] 5.x
- [ ] 4.x
- [ ] Other: ___

### Affected Components
- [ ] hapi-fhir-base (Core library)
- [ ] hapi-fhir-client (Client)
- [ ] hapi-fhir-server (Server)
- [ ] hapi-fhir-jpaserver-* (JPA Server)
- [ ] hapi-fhir-validation (Validation)
- [ ] hapi-fhir-structures-* (FHIR structures)
- [ ] Other: ___

### Vulnerability Type
- [ ] Remote Code Execution (RCE)
- [ ] SQL Injection
- [ ] Cross-Site Scripting (XSS)
- [ ] Authentication Bypass
- [ ] Authorization Bypass
- [ ] Information Disclosure
- [ ] Denial of Service (DoS)
- [ ] XML External Entity (XXE)
- [ ] Deserialization
- [ ] Path Traversal
- [ ] Other: ___

### CVSS Score (if known)
[e.g., 7.5 High]

### Steps to Reproduce
1. 
2. 
3. 

### Proof of Concept
[Code, screenshots, or other evidence]

### Impact
[Description of potential impact, especially regarding PHI/healthcare data]

### Suggested Fix (if any)
[Your recommendations]

### Discovery Information
- Discovered by: [Name/Handle]
- Discovery date: [Date]
- Organization (optional): [Org name]
```

---

## ⏱️ Response Timeline

| Stage | Timeline | Description |
|-------|----------|-------------|
| **Acknowledgment** | 24-48 hours | Initial response confirming receipt |
| **Triage** | 3-5 business days | Severity assessment and validation |
| **Investigation** | 1-2 weeks | Root cause analysis |
| **Fix Development** | 2-4 weeks | Patch development and testing |
| **Disclosure** | Coordinated | Joint disclosure with reporter |

**Note:** Critical vulnerabilities affecting PHI may receive expedited handling.

---

## 🏆 Recognition

We appreciate security researchers who help keep HAPI-FHIR and the healthcare community safe.

### Hall of Fame

Security researchers who have responsibly disclosed vulnerabilities:

| Researcher | Year | Recognition |
|------------|------|-------------|
| *Your name here* | | |

### Acknowledgment Options

When reporting, please indicate your preference:
- [ ] Public acknowledgment in release notes
- [ ] Hall of Fame listing
- [ ] Anonymous acknowledgment
- [ ] No public acknowledgment

---

## 🔐 Security Best Practices for HAPI-FHIR Deployments

### Minimum Security Configuration

```yaml
# Example Spring Boot security configuration
hapi:
  fhir:
    # Require HTTPS in production
    server:
      address: https://your-fhir-server.com/fhir
    
    # Enable SMART on FHIR
    security:
      oauth:
        enabled: true
        
    # Configure consent
    consent:
      enabled: true
      
    # Audit logging
    audit:
      enabled: true
      log_to_resource: true
```

### Security Checklist

#### Authentication & Authorization
- [ ] OAuth 2.0 / SMART on FHIR implemented
- [ ] Client credentials validated
- [ ] Scope-based access control configured
- [ ] Session management secured

#### Transport Security
- [ ] TLS 1.2+ enforced
- [ ] HSTS headers configured
- [ ] Certificate pinning (for mobile clients)
- [ ] Secure cookie flags set

#### Data Protection
- [ ] Database encryption enabled
- [ ] Backup encryption configured
- [ ] PHI redaction in logs
- [ ] Secure key management

#### Monitoring & Audit
- [ ] AuditEvent resources generated
- [ ] Security event monitoring
- [ ] Intrusion detection configured
- [ ] Regular log review process

#### Infrastructure
- [ ] Firewall rules configured
- [ ] Network segmentation
- [ ] Regular security updates
- [ ] Penetration testing scheduled

---

## 📋 Known Security Considerations

### Parser Security

HAPI-FHIR parsers are configured with security defaults:

| Parser | Default Setting | Risk Mitigated |
|--------|----------------|----------------|
| XML Parser | External entities disabled | XXE attacks |
| JSON Parser | Safe defaults | Deserialization attacks |
| RDF Parser | Sandboxed | Injection attacks |

### Validation Security

| Feature | Security Benefit |
|---------|-----------------|
| Profile validation | Prevents malformed data |
| Terminology validation | Ensures code integrity |
| Reference validation | Prevents dangling references |

### Interceptor Security

Custom interceptors should:
- Validate all inputs
- Use parameterized queries
- Log security events
- Handle exceptions safely

---

## 📚 Security Resources

### Documentation
- [HAPI-FHIR Security Documentation](https://hapifhir.io/hapi-fhir/docs/security/introduction.html)
- [SMART on FHIR](https://docs.smarthealthit.org/)
- [HL7 FHIR Security](https://www.hl7.org/fhir/security.html)

### Standards
- [HIPAA Security Rule](https://www.hhs.gov/hipaa/for-professionals/security/index.html)
- [NIST Cybersecurity Framework](https://www.nist.gov/cyberframework)
- [OWASP Healthcare](https://owasp.org/www-project-web-security-testing-guide/)

### Training
- [FHIR Security Fundamentals](https://www.hl7.org/fhir/security.html)
- [Healthcare Cybersecurity Best Practices](https://www.hhs.gov/hipaa/for-professionals/security/guidance/cybersecurity/index.html)

---

## 📞 Contact

| Purpose | Contact |
|---------|---------|
| Security vulnerabilities | security@smilecdr.com |
| General security questions | [GitHub Discussions](https://github.com/hapifhir/hapi-fhir/discussions) |
| Commercial support | [Smile CDR](https://smilecdr.com) |

---

## 📝 Policy Updates

This security policy is reviewed quarterly and updated as needed.

| Version | Date | Changes |
|---------|------|---------|
| 1.0 | 2025-12-09 | Initial version |

---

*Thank you for helping keep HAPI-FHIR and the healthcare community secure.*

