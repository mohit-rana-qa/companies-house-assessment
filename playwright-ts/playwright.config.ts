import { defineConfig, devices } from '@playwright/test';
import * as dotenv from 'dotenv'; dotenv.config();
export default defineConfig({
  testDir: './tests',
  reporter: [['list'], ['junit', { outputFile: 'reports/results.xml' }], ['html']],
  use: { baseURL: process.env.BASE_URL || 'https://automationintesting.online', trace:'retain-on-failure', screenshot:'only-on-failure', video:'retain-on-failure' },
  projects: [
    { name:'chromium', use: { ...devices['Desktop Chrome'] } },
    { name:'firefox', use: { ...devices['Desktop Firefox'] } },
    { name:'webkit', use: { ...devices['Desktop Safari'] } }
  ]
});