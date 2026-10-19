# OrangHRM UI Test Automation

Automated UI tests for the public **OrangeHRM 5 demo site** (<https://opensource-demo.orangehrmlive.com>).

You do not need any testing experience to use this project. This guide takes you from zero to a finished test run and a visual report, one step at a time.

## Table of contents

1. [Project overview](#1-project-overview)
2. [Demo video](#2-demo-video)
3. [Glossary](#3-glossary)
4. [Prerequisites](#4-prerequisites)
5. [Getting the project](#5-getting-the-project)
6. [Installing dependencies](#6-installing-dependencies)
7. [Project structure](#7-project-structure)
8. [Configuration](#8-configuration)
9. [Running the tests](#9-running-the-tests)
10. [running.xml explained](#10-runningxml-explained)
11. [Reading the logs](#11-reading-the-logs)
12. [Allure reporting](#12-allure-reporting)
13. [Troubleshooting](#13-troubleshooting)
14. [Adding a new test or page object](#14-adding-a-new-test-or-page-object)
15. [FAQ](#15-faq)

> **A note about spelling.** Some names in this project contain spelling mistakes from the original code, for example `uttils`, `JSONFileManger`, `cong.properties`, `fristName`, `getSucceflyMessage`, and `LoginTestsMoudul`. They are kept on purpose. Type them exactly as written, or Java and Maven will not find them.

---

## 1. Project overview

This project opens a real browser and clicks through the OrangeHRM demo site like a person would. It checks that important screens and messages appear as expected.

The project has **five tests**. They run in this order:

| Test | What it does |
|------|--------------|
| **TC1** | Logs in as the admin. Adds a new employee with a profile picture and login details (username and password). Checks the "Success" message. |
| **TC2** | Logs in as the new employee. Edits personal details and contact details. Adds an emergency contact (from Excel), then removes it. Logs out. Logs in as the admin and checks the employee's full name in PIM. |
| **TC3** | Logs in as the admin. Opens the first employee in the PIM list. Adds an emergency contact, removes it, then adds a dependent. |
| **TC4** | Logs in as the new employee. Fills in and submits a timesheet. Logs in as the admin, finds the employee in the Time module, and approves the timesheet with a comment. |
| **TC5** | Logs in as the admin. Opens a PIM report, edits it, opens it in full screen, and saves it as a PDF. Checks that the PDF is valid. |

**Important:** the tests run against a **live public website**. Other people use it too, and it can change or reset. See [Troubleshooting](#13-troubleshooting) if a test passes one day and fails the next.

**Technologies used**

| Tool | Version | Used for |
|------|---------|----------|
| Java | 25 (see the note in [Prerequisites](#4-prerequisites)) | The programming language |
| Maven | any recent 3.x | Builds the project and downloads libraries |
| Selenium | 4.43.0 | Controls the browser |
| TestNG | 7.12.0 | Runs the tests and checks results |
| Allure (allure-testng) | 2.35.5 | Creates a visual test report |
| Log4j2 (log4j-core, log4j-api) | 2.26.1 | Writes log messages |
| Gson | 2.14.0 | Reads the JSON data files |
| Apache POI (poi, poi-ooxml) | 5.5.1 | Reads the Excel data file |

---

## 2. Demo video

Do you want to see the whole project in action before you install anything? Watch the full test run here:

**Demo:** <DEMO_LINK>

You will see the complete run in order, from the first login through adding the employee, editing information, the timesheet, and the final PDF report. You can watch it first to know what "success" looks like.

---

## 3. Glossary

| Term | Meaning |
|------|---------|
| **Command line** | A text window where you type commands. On Windows it is called Command Prompt (`cmd`). |
| **Selenium** | A library that lets Java code control a web browser: open pages, click buttons, type text. |
| **WebDriver** | The part of Selenium that talks to one browser (Chrome, Edge, or Firefox). In the code it is the `driver` object. |
| **Selenium Manager** | A helper built into Selenium. It downloads the right browser driver (for example ChromeDriver) for you automatically. |
| **TestNG** | A Java testing framework. It finds methods marked `@Test`, runs them, and records pass or fail. |
| **Maven** | A build tool. It reads `pom.xml`, downloads the libraries, compiles the code, and starts the tests. |
| **Surefire** | The Maven plugin that runs the tests. In this project it reads the list of tests from `running.xml`. |
| **Allure** | A tool that turns test results into a clear web report with charts, errors, and screenshots. |
| **Log4j** | A library that writes log messages (what the program is doing) to the console and to a file. |
| **Locator** | A description of where an element is on a web page, for example "the button with type submit". |
| **XPath** | A locator language. Most locators in this project are written as XPath, such as `//input[@name="username"]`. |
| **Page object** | A Java class that represents one page of the website. It holds the locators and the actions for that page. |
| **Singleton** | A pattern where only one object of a kind exists at a time. Here: one browser is created, reused, and then quit. |
| **Soft assertion** | A check that records a failure but lets the test keep going. The failures are reported at the end with `assertAll()`. |
| **Hard assertion** | A check that stops the test immediately when it fails. |
| **Incognito / private mode** | A browser window that does not keep history or cookies. Chrome calls it incognito. Edge and Firefox call it private. |
| **Apache POI** | A Java library that reads and writes Microsoft Office files, such as `.xlsx`. |
| **Chrome DevTools Protocol (CDP)** | A set of commands for controlling Chrome from outside. TC5 uses it to print a page to PDF. |
| **Properties file** | A simple text file with `key=value` lines. `cong.properties` is one. |

---

## 4. Prerequisites

Install these four things first. The Allure tool is needed only later, for reports.

### 4.1 JDK 25

The JDK (Java Development Kit) lets you compile and run Java.

> **Why 25?** `pom.xml` sets the compiler to Java 25 in the `maven-compiler-plugin` settings. (A separate property in the same file says 23, but the plugin setting is the one that is used.) IntelliJ is also set to `openjdk-25`. Install JDK 25 or newer.

1. Download JDK 25 from a provider such as [Adoptium Temurin](https://adoptium.net) or [Oracle](https://www.oracle.com/java/technologies/downloads/).
2. Run the installer. Choose the option to set `JAVA_HOME` and add Java to `PATH` if it is offered.
3. Open a **new** Command Prompt and check:

```cmd
java -version
```

Typical output:

```text
openjdk version "25.0.1" 2025-10-21 LTS
OpenJDK Runtime Environment Temurin-25.0.1+8 (build 25.0.1+8-LTS)
OpenJDK 64-Bit Server VM Temurin-25.0.1+8 (build 25.0.1+8-LTS, mixed mode, sharing)
```

The exact numbers on your computer will be different. The first number must be **25** or higher.

### 4.2 Apache Maven

1. Download the binary zip from <https://maven.apache.org/download.cgi>.
2. Extract it, for example to `C:\Tools\apache-maven-3.9.x`.
3. Add its `bin` folder (for example `C:\Tools\apache-maven-3.9.x\bin`) to the Windows `PATH` variable.
4. Make sure `JAVA_HOME` points to your JDK folder.
5. Open a **new** Command Prompt and check:

```cmd
mvn -version
```

Typical output:

```text
Apache Maven 3.9.x
Maven home: C:\Tools\apache-maven-3.9.x
Java version: 25.0.1, vendor: Eclipse Adoptium, runtime: C:\Program Files\Eclipse Adoptium\jdk-25.0.1.8-hotspot
```

The `Java version` line must show 25 or higher.

### 4.3 Google Chrome

1. Download Chrome from <https://www.google.com/chrome/> and install it.
2. To check the version, type `chrome://version` in the Chrome address bar.

Chrome is the default browser for this project. Selenium Manager downloads the matching ChromeDriver the first time you run a test, so you do not install it yourself. (This step needs an internet connection.)

### 4.4 IntelliJ IDEA (optional)

IntelliJ IDEA Community Edition is free: <https://www.jetbrains.com/idea/download/>. You can run everything from the command line without it. The author uses it on Windows 11.

### 4.5 Allure command-line tool (only for reports)

See [section 12](#12-allure-reporting).

---

## 5. Getting the project

**Option A: from GitHub**

```cmd
git clone https://github.com/Omda66/OrangHRM.git
```

**Option B: from the archive**

1. Extract the archive you received (for example with right-click, **Extract All**).
2. Open the extracted **`OrangHRM`** folder. It must contain `pom.xml` and `running.xml`.

**Set the Project SDK in IntelliJ**

1. Start IntelliJ and choose **Open**. Select the `OrangHRM` folder (the one with `pom.xml`).
2. Choose **File > Project Structure > Project**.
3. Set **SDK** to JDK 25. The saved project setting is `openjdk-25`.
4. Wait until IntelliJ finishes loading the Maven project. If it asks, click **Load Maven Changes**.

---

## 6. Installing dependencies

Open Command Prompt and go to the project folder. `/d` also switches the drive letter if needed:

```cmd
cd /d C:\path\to\OrangHRM
```

Then run:

```cmd
mvn clean install -DskipTests
```

**What Maven does:**

1. `clean` deletes the old `target` folder.
2. It reads `pom.xml` and downloads every library listed there from the internet. The first time can take a few minutes.
3. It compiles the code in `src/main/java` and `src/test/java`.
4. `-DskipTests` means "build everything, but do not run the tests yet".

Typical successful output (the end of it):

```text
[INFO] BUILD SUCCESS
[INFO] Total time:  45.123 s
```

If you see `BUILD FAILURE`, go to [Troubleshooting](#13-troubleshooting).

**What each dependency is for**

| Dependency | Purpose in this project |
|------------|-------------------------|
| `selenium-java` | Opens and controls Chrome, Edge, or Firefox |
| `testng` | `@Test`, `@BeforeMethod`, `@AfterMethod`, assertions, and the test runner |
| `log4j-core`, `log4j-api` | The logging system configured in `log4j2.properties` |
| `allure-testng` | Writes the Allure result files while the tests run |
| `gson` | Reads `EmployeeInfo.json` and `newEmployee.json` |
| `poi`, `poi-ooxml` | Reads `EmployeeEmergency.xlsx` |

> **Why are page objects inside `src/main/java`?** Selenium and TestNG are declared in `pom.xml` without a `test` scope. That means both `src/main` and `src/test` can use them, so the page objects (the code that knows the pages) live in `src/main/java/pages`, and the tests live in `src/test/java`.

---

## 7. Project structure

```text
OrangHRM/
├── pom.xml                      Maven settings: libraries, plugins, Java version
├── running.xml                  TestNG suite file: which tests run, and in what order
├── .gitignore                   Files and folders that Git ignores
├── .idea/                       IntelliJ settings (misc.xml = SDK, workspace.xml = run configurations)
├── logs/                        Created on the first run: application.log
├── allure-results/              Created on the first run: raw Allure data
├── target/                      Created by Maven: compiled code and target/downloads (PDF)
└── src/
    ├── main/
    │   ├── java/pages/
    │   │   ├── BasePage.java            Shared helpers: findElement, click, sendText, getText, select..., getResourcePath
    │   │   ├── LoginPage/LoginPage.java Login screen
    │   │   ├── AdminPage/               Screens the admin sees
    │   │   │   ├── Dashboard/Dashboard.java
    │   │   │   ├── PimPage/
    │   │   │   │   ├── PimPage.java                PIM (employee list) page
    │   │   │   │   ├── AddEmployee/AddEmployee.java
    │   │   │   │   ├── Dependents/Dependents.java
    │   │   │   │   ├── EditEmployeePage/EditEmployeePage.java
    │   │   │   │   ├── EmergencyContacts/EmergencyContacts.java
    │   │   │   │   ├── PersonalDetails/PersonalDetails.java
    │   │   │   │   └── ReportsPage/
    │   │   │   │       ├── ReportsPage.java
    │   │   │   │       ├── EditReportPage/EditReportPage.java
    │   │   │   │       └── InfoReportPage/InfoReportPage.java   Full-screen report and PDF saving
    │   │   │   └── TimePage/TimePage.java      Admin timesheet approval
    │   │   └── EmployeePage/            Screens the new employee sees (E_ prefix)
    │   │       ├── E_Dashboard/E_Dashboard.java
    │   │       ├── E_PimPage/
    │   │       │   ├── E_PimPage.java
    │   │       │   ├── E_ContactDetails/E_ContactDetails.java
    │   │       │   ├── E_EmergencyContacts/E_EmergencyContacts.java
    │   │       │   └── E_PersonalDetails/E_PersonalDetails.java
    │   │       └── E_TimePage/E_TimePage.java   Employee timesheet
    │   └── resources/
    │       ├── cong.properties          url, username, password, browser
    │       ├── EmployeeInfo.json        Contact details for TC2
    │       ├── newEmployee.json         The employee created by TC1
    │       ├── EmployeeEmergency.xlsx   Emergency contact data for TC2
    │       ├── profile.jpeg             Profile picture uploaded by TC1
    │       └── log4j2.properties        Logging settings
    └── test/java/
        ├── BaseTest/BaseTest.java       Opens and closes the browser around every test
        ├── driverFactory/               DriverFactory, GetChromeDriver, GetEdgeDriver, GetFireFoxDriver
        ├── uttils/                      ConfigHandler, ExcelFile, JSONFileManger, ScreenShot
        └── TC1 ... TC5/                 One test class per folder
```

---

## 8. Configuration

All settings live in `src/main/resources`. Open them with any text editor.

### 8.1 `cong.properties`

A `key=value` file with four keys:

| Key | Meaning |
|-----|---------|
| `url` | The login page of the demo site. `BaseTest` opens this address before every test. (It is **not** written inside `BaseTest.java`.) |
| `username` | The admin user name: `<ADMIN_USERNAME>` |
| `password` | The admin password: `<ADMIN_PASSWORD>` |
| `browser` | `chrome`, `edge`, or `firefox` |

**To switch browser,** change the `browser` line. The value is not case-sensitive. Any other value stops the run with `Invalid browser name`.

| Browser | Started with |
|---------|--------------|
| `chrome` | `--incognito` |
| `edge` | `--private` |
| `firefox` | `--private` |

> TC5 saves the report as PDF through Chrome's DevTools commands. That step works **only with Chrome**.

### 8.2 `newEmployee.json`

One flat JSON object. It describes the employee that TC1 creates.

| Key | Meaning |
|-----|---------|
| `fristName` | First name (spelled this way in the file and in the code) |
| `LastName` | Last name |
| `fullName` | First and last name together. TC2 uses it to search for the employee. |
| `ID` | The employee ID typed into the Add Employee form |
| `username` | The new employee's login name: `<EMPLOYEE_USERNAME>` |
| `password` | The new employee's password: `<EMPLOYEE_PASSWORD>` |

TC2 and TC4 log in with this username and password.

### 8.3 `EmployeeInfo.json`

One flat JSON object with contact details. TC2 types them into the employee's **Contact Details** page.

| Key | Meaning |
|-----|---------|
| `street_1`, `street_2` | Address lines |
| `city` | City |
| `state_province` | State or province |
| `zip_postal_code` | ZIP or postal code |
| `home`, `mobile`, `work` | Telephone numbers |
| `work_email`, `other_email` | Email addresses |

### 8.4 `EmployeeEmergency.xlsx`

An Excel file with three sheets (`Sheet1`, `Sheet2`, `Sheet3`). **Only the first sheet is used.** It has **one column (A) and five rows**. TC2 reads it by position:

| Row (Excel) | Index in code | Meaning |
|-------------|---------------|---------|
| 1 | 0 | Emergency contact name |
| 2 | 1 | Relationship |
| 3 | 2 | Home telephone |
| 4 | 3 | Mobile |
| 5 | 4 | Work telephone |

The three phone cells are stored as numbers, and Excel drops a leading zero. That is why TC2 adds `"0"` in front of them in the code.

### 8.5 `profile.jpeg`

The picture that TC1 uploads. `BasePage.getResourcePath` finds it on the classpath, so Maven must have copied it into `target/classes` first. Running through Maven or IntelliJ does this for you.

### 8.6 Values written inside the tests and page objects

Some values are **not** in the data files. They are typed directly in the code:

| Where | What |
|-------|------|
| `PimPage.java` and `TimePage.java` | The autocomplete suggestion locator is fixed to **one employee full name**. |
| `TC4.java` | Types the same fixed employee name in the Time module. |
| `TC3.java` | Fixed sample text for the emergency contact and the dependent. |
| `TC4.java` | Fixed project text, hours, and an approval comment. |
| `TC5.java` | A short search text for the report, and the PDF file name (`pdfName`, which is `Employee report.pdf`). |

> **If you change the employee in `newEmployee.json`, also change the fixed name in `PimPage.java`, `TimePage.java`, and `TC4.java`.** Otherwise the tests will pick the wrong person from the suggestion list.

### 8.7 Keep real credentials out of Git

`cong.properties` and `newEmployee.json` hold usernames and passwords. For this public demo the values are not secret, but the habit matters. If you ever point the tests at a real system:

1. Use placeholders or fake values in anything you commit.
2. Add these lines to `.gitignore` (they are **not** ignored today):

```text
src/main/resources/cong.properties
src/main/resources/newEmployee.json
logs/
```

3. Keep a copy of the files with fake values, for example `cong.properties.example`, so others know the format.

The code does not read environment variables today, so a switch to environment variables would need a small code change in `ConfigHandler`.

---

## 9. Running the tests

> **Always run from the folder that contains `pom.xml`.** All data files and the log file use relative paths such as `src/main/resources/cong.properties` and `logs/application.log`.

### 9.1 From IntelliJ

**Run the whole suite**

1. In the Project panel, open `running.xml`.
2. Right-click `running.xml`.
3. Choose **Run '...\running.xml'**.

**Run one test class**

1. Open `src/test/java/TC1/TC1.java` (or TC2 to TC5).
2. Click the green arrow next to the class name or the `@Test` method.

IntelliJ also keeps saved run configurations named `TC1.TC1` to `TC5.TC5`.

### 9.2 From Windows Command Prompt

1. Open Command Prompt.
2. Go to the project folder:

```cmd
cd /d C:\path\to\OrangHRM
```

3. Run all five tests:

```cmd
mvn clean test
```

Chrome opens (in incognito mode), runs through the screens, and closes. It happens five times, once per test. Do not click inside the browser while it runs.

Typical output (the end of it):

```text
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] BUILD SUCCESS
```

> `clean` deletes the `target` folder, including an old `target/downloads/Employee report.pdf`. It does **not** delete `logs/` or `allure-results/`.

### 9.3 Running a single class or method

The Surefire documentation says that the `-Dtest` option overrides `suiteXmlFiles`. So `running.xml` is ignored when you use it.

Run one class:

```cmd
mvn test -Dtest=TC5
```

Run one method (each class has one `@Test` method with the same name as the class):

```cmd
mvn test -Dtest=TC5#TC5
```

Typical output:

```text
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
```

> TC2 and TC4 log in as the employee that **TC1 creates**. If that employee does not exist yet on the site, they fail at login. Run TC1 first, or use `mvn test` to run the whole suite in order. TC3 and TC5 can run alone.

---

## 10. running.xml explained

`running.xml` is the **TestNG suite file**. It lists the test classes to run. Maven's Surefire plugin is told about it in `pom.xml`:

```xml
<suiteXmlFiles>
    running.xml
</suiteXmlFiles>
```

The file itself:

```xml
<suite name="MySuite">
    <test name="LoginTestsMoudul">
        <classes>
            <class name="TC1.TC1"/>
            <class name="TC2.TC2"/>
            <class name="TC3.TC3"/>
            <class name="TC4.TC4"/>
            <class name="TC5.TC5"/>
        </classes>
    </test>
</suite>
```

| Part | Meaning |
|------|---------|
| `suite name="MySuite"` | The name of the whole run |
| `test name="LoginTestsMoudul"` | A group of classes (spelled this way in the file) |
| `class name="TC1.TC1"` | Package name, a dot, then class name |

**Order matters.** TestNG runs the classes in the order they are listed: TC1, TC2, TC3, TC4, TC5.

- TC1 creates the employee.
- TC2 and TC4 then log in as that employee.
- If you move TC2 or TC4 before TC1, or remove TC1, they will not find the employee.

**To remove a test:** delete its `<class .../>` line. (Remember that TC2 and TC4 need TC1.)

**To add a test:** add a line inside `<classes>` with the package name and the class name:

```xml
<class name="TC6.TC6"/>
```

---

## 11. Reading the logs

The project writes a detailed log of every step. Logging is set up in `src/main/resources/log4j2.properties`.

### 11.1 Where the logs go

| Place | Format |
|-------|--------|
| **Console** | `date - message` (no level tag) |
| **`logs/application.log`** | `date [LEVEL] ClassName:line - message` |

Typical console line:

```text
2026-01-15 10:21:07 - Step 1: Login as admin
```

Typical file line:

```text
2026-01-15 10:21:07 [INFO ] TC1:33 - Step 1: Login as admin
```

The root log level is **debug**. That means DEBUG, INFO, WARN, ERROR, and FATAL are printed. TRACE lines are **not** printed.

The log file is opened in **append** mode. It keeps growing between runs. Delete `logs/application.log` for a fresh start.

### 11.2 Level tags (file only)

| Tag | Meaning in this project |
|-----|-------------------------|
| `TRACE` | Very small details. Not printed at the current level. |
| `DEBUG` | Technical details, such as the element that was found |
| `INFO` | Normal progress: steps, clicks, passes |
| `WARN` | Something odd, but the run continues (a slow element, an empty value) |
| `ERROR` | A failed step or assertion |
| `FATAL` | A serious problem: the browser did not start, a data file is missing, or login failed |

### 11.3 Markers in the messages

| Marker | Meaning |
|--------|---------|
| `################ Test started: TC1 ...` | Start, pass, or fail line of a test |
| `==================== Test setup started` | `BaseTest` setup, and `Test PASSED / FAILED / SKIPPED` result |
| `Step 1: ...`, `Step 2: ...` | The numbered steps of a test |
| `PASS:` / `FAIL:` | Hard assertion result (a FAIL stops the test) |
| `SOFT PASS:` / `SOFT FAIL:` | Soft assertion result (collected, checked at the end) |
| `Step FAILED (assertion)` / `Step ERROR (exception)` | The step that broke, with its duration |
| `Test ABORTED (unexpected exception)` | The test stopped because of an unexpected error |
| 📄 | A PDF report was saved (`InfoReportPage`) |
| ❌ | The PDF could not be saved or read (`InfoReportPage`) |

### 11.4 How to find the failing step

1. Open `logs/application.log`.
2. Search for `FAILED`, then `ERROR`, then `FATAL`.
3. Scroll up to the closest line that starts with `Step` followed by a number. That is the step that was running.
4. Read the lines just before the error. They show which element or value was involved.

Example idea:

```text
... [INFO ] TC2:70 - Step 3: Update personal details (marital status + nationality)
... [ERROR] BasePage:... - Timed out after 10s waiting for element: By.xpath: ...
... [ERROR] TC2:... - Step ERROR (exception) after 10412 ms: Step 3: ...
```

### 11.5 A note about privacy in logs

- `ConfigHandler` hides values whose key contains "pass" (it prints `****`). The typed text is never logged by `BasePage.sendText`.
- But `JSONFileManger.getValue` prints **every value it reads at DEBUG level**. That includes the `username` and `password` from `newEmployee.json` and the contact details from `EmployeeInfo.json`.
- Usernames are also logged when logging in.

So the console output and `logs/application.log` can contain test data. Check them before you share them publicly. This is another reason to keep `logs/` out of Git.

---

## 12. Allure reporting

Allure turns the test results into a web report. The `allure-testng` library is already in `pom.xml`. The `AllureTestNg` listener writes result files into the folder **`allure-results`** in the project root while the tests run. (This is the default folder name in the Allure documentation, and it is already listed in `.gitignore`.)

The listener is not written in `BaseTest` or `running.xml`. The `allure-testng` library adds it to TestNG by itself.

### 12.1 Install the Allure command-line tool (Windows)

This project uses the Allure 2 integration, so use the Allure 2 command-line tool. It needs Java, which you already installed.

**Option 1: Scoop** (a Windows package manager; install it first from <https://scoop.sh>)

```cmd
scoop install allure
```

**Option 2: manual**

1. Download the `allure-*.zip` file from the latest release at <https://github.com/allure-framework/allure2/releases/latest>.
2. Extract it, for example to `C:\Tools\allure-2.x.x`.
3. Add the `bin` folder (for example `C:\Tools\allure-2.x.x\bin`) to your `PATH`.

Open a **new** Command Prompt and check:

```cmd
allure --version
```

Typical output:

```text
2.x.x
```

### 12.2 Create and open the report

1. Run the tests (`mvn clean test`). This creates `allure-results`.
2. From the project folder, run:

```cmd
allure serve allure-results
```

It builds the report, starts a small local web server, and opens the report in your browser. Press `Ctrl+C` in the console to stop it.

Typical output:

```text
Generating report to temp directory...
Report successfully generated to C:\Users\...\Temp\...
Starting web server...
Server started at <http://192.168.x.x:xxxxx/>. Press <Ctrl+C> to exit
```

**Or: generate a report folder and open it later**

```cmd
allure generate allure-results --clean -o allure-report
allure open allure-report
```

### 12.3 What the report shows

| Section | What you see |
|---------|--------------|
| **Overview** | Totals of passed, failed, and broken tests, and the pass rate |
| **Suites** | Each test class and its result, with duration and any error message |
| **Graphs** | Charts of status, severity, and duration |
| **Timeline** | When each test started and how long it took |
| **Behaviors / Packages** | The same tests grouped in other ways |

The tests in this project do not use Allure's `@Step` or `@Description` annotations. So the report shows each test's result, duration, and error. The **step-by-step story** is in the log file ([section 11](#11-reading-the-logs)).

### 12.4 Failure screenshot

When a test fails, `BaseTest` (in `failedTestCase`) takes a screenshot and attaches it to that test in Allure under the name **Screenshot**. To see it:

1. Open **Suites**.
2. Click the failed test.
3. Look at the **Screenshot** attachment on the test page. It shows the browser at the moment of failure.

> The screenshot is a **temporary file** made by Selenium. It is sent to Allure, but it is **not** saved in a project folder such as `target/screenshots`. If you want the picture without Allure, you need to copy the file yourself in `ScreenShot.java`.

---

## 13. Troubleshooting

Do not worry if something fails. Most problems are setup problems and are easy to fix.

| Problem or message | Likely cause | Fix |
|--------------------|--------------|-----|
| `'mvn' is not recognized as an internal or external command` | Maven is not installed, or its `bin` folder is not in `PATH`. | Follow [4.2](#42-apache-maven). Close and open a **new** Command Prompt, then run `mvn -version`. |
| `release version 25 not supported` or `invalid target release: 25` | Maven is using an older JDK. | Install JDK 25. Make sure `JAVA_HOME` points to it and `mvn -version` shows Java 25. |
| `java` is not recognized, or a version lower than 25 | JDK missing or an old one is first in `PATH`. | Reinstall JDK 25 and fix `PATH` / `JAVA_HOME`. |
| In IntelliJ: red code, or "Language level is invalid" | The Project SDK is not set. | **File > Project Structure > Project > SDK = 25**, then reload Maven. |
| ChromeDriver or Chrome version mismatch, session not created, or driver download error | Chrome is very new or old, or Selenium Manager cannot download the driver (no internet or blocked). | Update Chrome. Check your internet connection and any proxy or firewall. Run again. |
| `Failed to start the chrome driver - tests cannot run` (FATAL) | Chrome is not installed, or the driver could not start. | Install Chrome. Read the error under the FATAL line. |
| `Invalid browser name : ...` | The `browser` value in `cong.properties` is not `chrome`, `edge`, or `firefox`. | Fix the spelling. |
| `Browser name must not be null or empty` | `cong.properties` was not found or has no `browser` key. This almost always means you ran from the wrong folder. | `cd /d` to the folder with `pom.xml` and run again. |
| `Config file NOT found: ...` (FATAL) | Wrong working folder. | Run from the folder with `pom.xml`. |
| `JSON file NOT found`, `Could not read JSON file`, or `JSON file has invalid format` (FATAL), then `JSON data was not loaded - cannot read key: ...` | Wrong working folder, a missing file, or a typo in the JSON (missing comma or quote). | Run from the right folder. Check the JSON with a validator. |
| `Could not open Excel file ...` (FATAL), then `The Excel sheet was not loaded` | Wrong working folder, or the `.xlsx` file is open in Excel or damaged. | Run from the right folder. Close Excel. |
| `Missing key in newEmployee.json: ...` | A key such as `fristName` or `username` is missing or misspelled. | Compare the file with the key list in [8.2](#82-newemployeejson). |
| `Missing key in json file` or `Empty Excel cell` | A key in `EmployeeInfo.json`, or a cell in the Excel file, is missing. | Compare with [8.3](#83-employeeinfojson) and [8.4](#84-employeeemergencyxlsx). |
| `NoSuchElementException` | The page has not finished loading, or the website's HTML changed, so the XPath no longer matches. | Run again. If it repeats, open the page in Chrome DevTools and update the locator in the page object. |
| `TimeoutException` / `Timed out after 10s waiting for element: By.xpath...` | The element did not appear within 10 seconds: slow site, wrong page, an earlier step failed, or the locator changed. | Check the log line above it. Check your internet speed. Open the page manually. Update the locator if the site changed. |
| Dropdown or option is not selected (dropdowns time out waiting for `div[@role="option"][2]` or similar) | The OrangeHRM dropdowns are custom lists. The page objects click the list, then click an option **by position** (for example the 2nd option). If the list is empty or loads slowly, the click fails. | Run again. Check the list on the site by hand. The `selectedBy...` helper methods in `BasePage` print `Dropdown ... NOT found` messages, but the page objects in this project do not use them. |
| TC2 or TC4 fails at login | TC1 did not create the employee, TC1 failed earlier, or the site was reset. | Run the whole suite with `mvn clean test` (TC1 first). Check that `username` and `password` in `newEmployee.json` are right. |
| TC1 fails when you run it a second time (the success message is missing) | The employee ID or username already exists on the site. | Change `ID` and `username` in `newEmployee.json` to new values. Keep TC2 and TC4 in step with them. Change the fixed name in `PimPage.java`, `TimePage.java`, and `TC4.java` if the full name changed. |
| PDF not saved, or `ClassCastException` in TC5 | `browser` is not `chrome`. The PDF step needs ChromeDriver. | Set `browser=chrome` in `cong.properties`. |
| The test seems frozen on the login page | `LoginPage.clickLogin` waits **up to 5000 seconds** (more than 83 minutes) for the login button. If the page never loads, it does not fail quickly. | Stop the run. Check your internet and the site. (The author may want to change this to a few seconds.) |
| Tests pass on one run and fail on the next | The demo site is public. Other people can change its data, and it can reset or be slow. | Run again. Check the log for the failing step. See the box below. |

**Why tests can fail even when the code is fine**

- The demo data is shared and can change at any time.
- The employee or user you created may be gone after a reset, or may already exist.
- The site can be slow or down.
- OrangeHRM can change its page layout, which breaks XPath locators.

---

## 14. Adding a new test or page object

The project follows one pattern. Copy it for a new screen or test.

### Step 1: Create the page object

1. Create a folder and file in `src/main/java/pages/<Area>/<Name>/<Name>.java`. For example `src/main/java/pages/AdminPage/Example/ExamplePage.java`.
2. Make the class extend `BasePage`, keep locators as `private final By` fields, and call `super(driver)` in the constructor.

```java
package pages.AdminPage.Example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class ExamplePage extends BasePage {
    private static final Logger log = LogManager.getLogger(ExamplePage.class);

    private final By title = By.xpath("//h6");

    public ExamplePage(WebDriver driver) {
        super(driver);
        log.info("ExamplePage initialized");
    }

    public String getTitle() {
        return getText(title);
    }
}
```

`BasePage` gives you `click(by)`, `sendText(by, text)`, `getText(by)`, `findElement(by)`, and the `selectedBy...` helpers. Each waits up to 10 seconds for the element to be visible.

### Step 2: Create the test class

1. Create `src/test/java/TC6/TC6.java`.
2. Extend `BaseTest`. Add one `@Test` method with the same name as the class.
3. Use `configHandler`, `jsonFileManger`, `newEmployee`, and `excelFile` from `BaseTest` for data. The `driver` is ready and the login page is already open.

```java
package TC6;

import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage.LoginPage;
import pages.AdminPage.Example.ExamplePage;

public class TC6 extends BaseTest {

    @Test
    public void TC6() {
        LoginPage loginPage = new LoginPage(driver);
        ExamplePage examplePage = new ExamplePage(driver);

        loginPage.login(configHandler.getValue("username"), configHandler.getValue("password"));
        Assert.assertEquals(examplePage.getTitle(), "Expected title");
    }
}
```

The existing tests also use small private helper methods: `step`, `verifySoft`, `verifyHard`, and `logPageState`. They are **copied into each test class** and are not in `BaseTest`. Not every class has all four (TC1 has only `verifySoft` and `verifyHard`; TC4 has no `verifyHard`). Copy them from an existing test, for example `TC3.java` (it has all four), if you want the same detailed logging.

### Step 3: Register the test in `running.xml`

```xml
<class name="TC6.TC6"/>
```

### Step 4: Run it

```cmd
mvn test -Dtest=TC6
```

---

## 15. FAQ

**Why does every test open a new browser?**
`BaseTest` has an `@BeforeMethod` that starts the browser and opens the login page, and `@AfterMethod` methods that quit it. Each class has one `@Test`, so each test starts clean at the login page.

**How does the "one browser" (singleton) idea work here?**
Each driver class (`GetChromeDriver`, `GetEdgeDriver`, `GetFireFoxDriver`) holds one static driver. It creates the browser only when that driver is `null`, returns the same browser on later calls, and sets the driver back to `null` after `quit`.

**Why incognito?**
An incognito (or private) window starts without cookies, saved logins, or old pages. Every run starts the same way.

**Can I use Edge or Firefox?**
Yes. Change `browser` in `cong.properties` to `edge` or `firefox`. The PDF step in TC5 works only in Chrome.

**Can I run TC2 or TC4 alone?**
Only if TC1 has already created the employee on the site. TC3 and TC5 can run alone.

**Where is the PDF?**
In `target/downloads/Employee report.pdf` after TC5 runs. `mvn clean` deletes it.

**Where are the screenshots?**
Failure screenshots are attached to the test in the Allure report. They are not saved as files in the project.

**Why does TC1 fail the second time I run it?**
The same employee ID and username already exist on the demo site. Change `ID` and `username` in `newEmployee.json`.

**A test passed yesterday but fails today. Is my code broken?**
Not necessarily. The demo site is public and can change. See [Troubleshooting](#13-troubleshooting).

**Why are some names misspelled?**
They come from the original code and are used in many places. Renaming them without care would break the project. Type them as they are.

**Does the report show soft assertion failures in TC1?**
No. In TC1, the line `softAssert.assertAll()` is commented out. Soft failures are written to the log as warnings, but they do not fail the test. A failed "Success" message check (a hard assertion) does fail it.

**Is it safe to share my log file?**
Check it first. It can contain test data from the JSON files, including the new employee's password. See [11.5](#115-a-note-about-privacy-in-logs).
