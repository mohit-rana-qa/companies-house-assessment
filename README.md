# Companies House – Practical Automation Assessment
[Playwright+TS] and [Selenium 4 + Java 21] suites targeting https://automationintesting.online with UI, API, a11y, visual and tiny perf smoke. See /docs for plan & bugs.

## Playwright
```bash
cd playwright-ts
npm ci
npx playwright install --with-deps
npm test
```
## Selenium
```bash
cd selenium-java
mvn -q -DskipTests=false test
```

## Test Plan Summary
See [docs/TEST_PLAN.md](docs/TEST_PLAN.md) for the complete plan.

**Scope:**
- Verify booking flow, contact form, admin login, and room creation.
- Validate core REST APIs (`/room`, `/booking`, `/actuator/health`).
- Include non-functional checks: accessibility (Axe), visual snapshots, performance smoke.

**Test Types:**
- `@smoke` – Happy-path UI checks
- `@api` – API contract/smoke tests
- `@a11y` – Accessibility scans
- `@visual` – Visual regression baselines

---

## Sample Bug Reports
See [docs/BUG_REPORTS.md](docs/BUG_REPORTS.md) for full details.

### Example 1 – Invalid email accepted in Contact Form
- **Severity:** Medium  
- **Steps:** Enter `johndoe` (no `@`) in the email field and submit.  
- **Expected:** Validation error message.  
- **Actual:** Form submits successfully.

### Example 2 – Admin room name accepts >256 characters
- **Severity:** Low  
- **Impact:** Layout breaks in the room list.  
- **Recommendation:** Add `maxlength` attribute and server-side check.

---

## Framework Highlights
- Dual-stack: **Playwright + TypeScript** and **Selenium 4 + Java 21**
- CI-ready via GitHub Actions
- Deterministic waits, page objects, data-driven setup
- Lightweight performance smoke using `k6`

---

💬 *For detailed reasoning, architecture notes, and CI setup, see the `/docs` folder.*
