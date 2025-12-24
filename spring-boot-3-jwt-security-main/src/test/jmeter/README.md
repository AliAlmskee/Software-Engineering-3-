# JMeter Stress Testing Guide

This directory contains JMeter test plans for stress testing the Spring Boot JWT Security application.

## Prerequisites

1. **Maven** - The project uses the JMeter Maven plugin
2. **Running Application** - Ensure your Spring Boot application is running before executing tests
3. **Test Data** - Make sure you have valid test users and accounts in your database

## Test Plan Overview

The `stress-test.jmx` file includes:

1. **Thread Group Configuration**
   - 100 concurrent users
   - 10 second ramp-up time
   - 10 iterations per user

2. **Test Scenarios**
   - Login and JWT token extraction
   - Get Account Tax (GET `/api/accounts/tax/{id}`)
   - Get Group Tax (POST `/api/accounts/tax/group`)
   - Transfer Money (POST `/api/transaction/transfer`)

3. **Features**
   - Automatic JWT token extraction and reuse
   - Response assertions for validation
   - Think time between requests (1 second)
   - Summary report generation

## Running Tests

### Option 1: Using Maven (Recommended)

Run the JMeter tests as part of the Maven build:

```bash
# Run JMeter tests
mvn verify

# Or specifically run JMeter
mvn jmeter:jmeter
```

### Option 2: Generate Reports

Generate HTML reports from test results:

```bash
mvn jmeter:jmeter jmeter:results
```

### Option 3: Custom Configuration

You can override default properties:

```bash
# Custom server host and port
mvn jmeter:jmeter -Dserver.host=localhost -Dserver.port=8080

# Custom test credentials
mvn jmeter:jmeter -Dtest.phone=1234567890 -Dtest.otp=123456
```

## Configuration

### Test Plan Variables

You can customize the test plan by modifying these variables in the JMeter GUI or via command line:

- `server.host` - Server hostname (default: `localhost`)
- `server.port` - Server port (default: `8080`)
- `test.phone` - Phone number for authentication (default: `1234567890`)
- `test.otp` - OTP code for authentication (default: `123456`)

### Thread Group Settings

To modify the stress test parameters, edit `stress-test.jmx`:

- **Number of Threads**: Number of concurrent users
- **Ramp-up Period**: Time to start all threads (in seconds)
- **Loop Count**: Number of iterations per user

## Test Results

Test results are generated in the following locations:

- **JTL Files**: `target/jmeter/results/` - Raw test results
- **HTML Reports**: `target/jmeter/reports/` - HTML reports (if generated)
- **Logs**: `target/jmeter/logs/` - JMeter execution logs

## Viewing Results

### Using JMeter GUI

1. Open JMeter GUI
2. File → Open → Select `stress-test.jmx`
3. Run the test
4. View results in the "View Results Tree" and "Summary Report" listeners

### Using HTML Reports

After running tests, generate HTML reports:

```bash
mvn jmeter:results
```

Then open `target/jmeter/reports/index.html` in your browser.

## Customizing the Test Plan

### Adding New Test Scenarios

1. Open `stress-test.jmx` in JMeter GUI
2. Right-click on the Thread Group
3. Add → Sampler → HTTP Request
4. Configure the request (method, path, parameters)
5. Add assertions and post-processors as needed
6. Save the test plan

### Modifying Authentication

The test plan automatically extracts the JWT token from the login response. If your authentication flow changes:

1. Update the JSON Extractor in the "Extract JWT Token" post-processor
2. Modify the JSON path expression (currently: `$.access_token`)
3. Update the Authorization header format if needed

## Best Practices

1. **Start Small**: Begin with fewer threads (10-20) and gradually increase
2. **Monitor Resources**: Watch server CPU, memory, and database connections
3. **Test Environment**: Always use a test environment, never production
4. **Warm-up**: Consider adding a warm-up phase before the main test
5. **Clean Data**: Ensure test data is properly set up and cleaned after tests

## Troubleshooting

### Common Issues

1. **401 Unauthorized Errors**
   - Check if JWT token is being extracted correctly
   - Verify the token format in the Authorization header
   - Ensure test credentials are valid

2. **Connection Refused**
   - Verify the application is running
   - Check server host and port configuration
   - Ensure firewall allows connections

3. **Timeout Errors**
   - Increase timeout values in HTTP Request Defaults
   - Check server performance and resource usage
   - Reduce number of concurrent threads

4. **Out of Memory**
   - Reduce number of threads
   - Increase JMeter heap size: `export HEAP="-Xms1g -Xmx4g"`
   - Remove unnecessary listeners during test execution

## Maven Plugin Configuration

The JMeter Maven plugin is configured in `pom.xml`:

```xml
<plugin>
    <groupId>com.lazerycode.jmeter</groupId>
    <artifactId>jmeter-maven-plugin</artifactId>
    <version>3.5.0</version>
</plugin>
```

## Additional Resources

- [JMeter Official Documentation](https://jmeter.apache.org/usermanual/)
- [JMeter Maven Plugin Documentation](https://github.com/jmeter-maven-plugin/jmeter-maven-plugin)
- [JMeter Best Practices](https://jmeter.apache.org/usermanual/best-practices.html)

