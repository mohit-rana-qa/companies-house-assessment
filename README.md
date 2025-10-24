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
