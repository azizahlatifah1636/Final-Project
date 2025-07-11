# Automation Test Framework

This project is a comprehensive automation test framework that demonstrates testing both Web UI and API using a combination of modern tools and libraries.

## Tech Stack

- **Java 11**: Programming language
- **Gradle**: Build tool and task automation
- **Cucumber**: BDD framework
- **Selenium WebDriver**: Web UI automation
- **REST Assured**: API testing
- **JUnit**: Testing framework
- **AssertJ**: Fluent assertions
- **WebDriverManager**: Browser driver management
- **GitHub Actions**: CI/CD

## Project Structure

```
.
├── src
│   └── test
│       ├── java
│       │   └── com
│       │       └── finalproject
│       │           ├── api
│       │           │   ├── models       # API data models
│       │           │   └── steps        # API step definitions
│       │           ├── common           # Common utilities
│       │           ├── web
│       │           │   ├── pages        # Page Object Models
│       │           │   └── steps        # Web UI step definitions
│       │           ├── ApiTestRunner.java
│       │           └── WebTestRunner.java
│       └── resources
│           └── features
│               ├── api                  # API feature files
│               └── web                  # Web UI feature files
├── .github
│   └── workflows                        # GitHub Actions workflows
├── build.gradle                         # Gradle build script
└── README.md                            # This file
```

## Features

- **BDD Approach**: All tests are written in Gherkin syntax for better collaboration
- **Page Object Model**: Web UI tests follow POM design pattern for maintainability
- **API Testing**: Complete API testing framework with models and validations
- **HTML and JSON Reports**: Generated for each test run
- **Parallel Test Execution**: Both API and Web tests can run in parallel
- **CI/CD Integration**: GitHub Actions workflow for automated testing
- **Cross-browser Support**: Selenium setup for multi-browser testing

## Target Applications

- **Web UI**: [DemoBlaze](https://www.demoblaze.com/) - E-commerce demo site
- **API**: [Dummy API](https://dummyapi.io/) - Test API for practice

## Test Scenarios

### Web UI Tests

- Product browsing by category
- Shopping cart management (add/remove products)
- User account management (signup/login)

### API Tests

- User management (create, read, update, delete)
- Tag management (list tags)

## Running the Tests

### Prerequisites

- Java JDK 11 or higher
- Gradle (or use the included Gradle wrapper)
- Chrome browser installed (for Web UI tests)

### Using Gradle Tasks

Run API tests:
```bash
./gradlew runApiTests
```

Run Web UI tests:
```bash
./gradlew runWebTests
```

Run all tests:
```bash
./gradlew runAllTests
```

### Test Reports

After running the tests, reports are generated in:
- API Test HTML Report: `target/cucumber-reports/api-report.html`
- Web Test HTML Report: `target/cucumber-reports/web-report.html`
- API Test JSON Report: `target/cucumber-reports/api-report.json`
- Web Test JSON Report: `target/cucumber-reports/web-report.json`

## GitHub Actions

The project includes GitHub Actions workflows that will automatically run the tests:
- On every Pull Request to the main branch
- Manually when triggered from the Actions tab

The workflow uploads test reports as artifacts that can be downloaded and viewed after the workflow completes.

## Development and Contribution

### Adding New Tests

1. Create a new feature file in the appropriate directory (`api` or `web`)
2. Implement step definitions in the corresponding package
3. For Web UI tests, add Page Objects if needed
4. For API tests, add models for new endpoints if needed

### Best Practices

- Keep feature files focused on business value
- Make step definitions reusable
- Follow Page Object Model pattern for UI tests
- Include proper validation for API responses
- Add meaningful assertions
- Use tags to categorize and filter tests
