# Companies House – Practical Automation Assessment

This submission demonstrates a dual-stack test automation approach aligned with Companies House’s migration roadmap:
- Playwright + TypeScript (for customer-facing web flows)
- Selenium 4 + Java 21 (JUnit 5) for legacy internal systems

Target site: [https://automationintesting.online](https://automationintesting.online)

---

## Framework Overview

| Stack | Purpose | Key Features |
|--------|----------|--------------|
| Playwright (TypeScript) | Modern front-end automation | Page Object Model, tagging (`@smoke`, `@api`, `@a11y`, `@visual`), CI-ready |
| Selenium (Java 21) | Legacy UI smoke | Cross-browser setup, explicit waits, headless mode for CI |
| k6 (Performance) | Lightweight non-functional check | GET `/room` response time baseline |
| Axe (Accessibility) | Accessibility smoke testing | Automated WCAG validation on key pages |

---

## How to Run

### Playwright
```bash
cd playwright-ts
npm ci
npx playwright install --with-deps
npm test
Selenium
bash
Copy code
cd selenium-java
mvn -q -DskipTests=false test
Reports:

Playwright HTML report: playwright-ts/playwright-report

JUnit XML results for CI: reports/results.xml

Test Plan Summary
See docs/TEST_PLAN.md for full details.

Scope

UI: Home → Rooms → Booking, Contact Form, Admin login, Room creation

API: Health, Rooms, Booking create/delete

Non-functional: Accessibility (Axe), Visual regression, Performance (k6)

Test Types

@smoke – happy-path UI tests

@api – REST contract validation

@a11y – accessibility checks

@visual – snapshot comparisons

Sample Bug Reports
See docs/BUG_REPORTS.md for the detailed list.

**BUG-001 – Contact form accepts invalid email
**
Severity: Medium

Expected: Validation prevents submission.

Actual: Form submits successfully.

**BUG-002 – Admin room name accepts over 256 characters
**
Severity: Low

Impact: Layout breaks; no input validation present.

**BUG-003 – Accessibility: Missing ARIA landmarks
**
Severity: Low

Impact: Affects screen reader usability; lacks semantic HTML.

**BUG-004 – Booking modal intermittently fails to close
**
Severity: Medium

Impact: Occasional UI inconsistency after success message.

BUG-005 – Logout button visible on login screen

Severity: Medium

Priority: High

Page: /admin (Admin Login page)

Steps:

Navigate to https://automationintesting.online/admin

Observe the Logout button in the top-right corner.

Expected: Logout should only be visible after login.

Actual: Logout button is displayed on the login page.

Impact: Causes UI inconsistency and may confuse users.

Evidence: docs/screens/logout-visible.png

Framework Highlights
Dual automation stacks to reflect Companies House migration projects.

Consistent structure with Page Objects, environment variables, and modular design.

Deterministic waits to avoid flaky UI interactions.

API, accessibility, and visual checks integrated into CI workflows.

Lightweight performance check using k6.

Repository Structure
bash
Copy code
companies-house-assessment/
├── playwright-ts/        # Playwright + TypeScript suite
├── selenium-java/        # Selenium + Java 21 smoke tests
├── perf/                 # k6 performance smoke test
├── docs/
│   ├── TEST_PLAN.md
│   ├── BUG_REPORTS.md
│   └── ARCHITECTURE.md
└── .github/workflows/    # CI pipelines
