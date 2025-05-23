# VezeetaAutomationTest

## Overview
This project contains automated test cases for the Vezeeta website using Selenium WebDriver and TestNG.  
It tests core functionalities such as homepage display, search, login, filtering doctors, and booking appointments.

## Technologies Used
- Java
- Selenium WebDriver
- TestNG
- ChromeDriver

## Test Cases

1. **Home Page Test**  
   Checks if the search bar is visible on the homepage.

2. **Search Test**  
   Searches for "Dentist" and verifies that results are displayed.

3. **Login Test**  
   Tests login using sample credentials.

4. **Filter Doctors Test**  
   Filters doctors by specialty (Dermatology) and location (Cairo), and verifies filtered results.

5. **Book Appointment Test**  
   Selects the first doctor and initiates appointment booking, verifying the booking form appears.

## Setup Instructions

### Prerequisites
- Java JDK installed.
- Chrome browser installed.
- ChromeDriver installed and configured in system PATH.
- Maven or another build tool configured (optional but recommended).

### Running Tests
1. Clone or download this project.  
2. Open your IDE or command line.  
3. Run the test class `VezeetaAutomationTest` via TestNG.  
4. Tests will launch Chrome and perform automated actions.

## Notes
- Replace the login credentials in the test with valid ones if needed.  
- Locator values and waits might need adjustment if the website UI changes.  
- Implement the `takeScreenshot` method to capture screenshots on test failures.

## Author
Your Name  
your.email@example.com

## License
Specify your license here (e.g., MIT, Apache 2.0) or remove this section.
