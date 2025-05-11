package reports;

import constants.FrameworkConstants;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class AllureReportUtil {

    private static final String ALLURE_RESULTS_PATH = "allure-results";
    private static final String ALLURE_REPORT_PATH = "reports/AllureReports";
    private static final int HTTP_SERVER_PORT = 8000;

    public static void openReport() {

        try {
            ProcessBuilder generateBuilder = new ProcessBuilder("C:/Program Files/Allure/allure-2.34.0/allure-2.34.0/bin/allure.bat", "generate", ALLURE_RESULTS_PATH, "-o", ALLURE_REPORT_PATH, "--clean");
            generateBuilder.redirectErrorStream(true);
            Process generateProcess = generateBuilder.start();
            int generateExitCode = generateProcess.waitFor();

            if (generateExitCode == 0) {
                System.out.println("Allure report generated successfully in: " + ALLURE_REPORT_PATH);
                ProcessBuilder openBuilder = new ProcessBuilder("C:/Program Files/Allure/allure-2.34.0/allure-2.34.0/bin/allure.bat", "open", ALLURE_REPORT_PATH);
                openBuilder.redirectErrorStream(true);
                Process openProcess = openBuilder.start();
                int openExitCode = openProcess.waitFor();
                if (openExitCode == 0) {
                    System.out.println("Allure report opened in the browser.");
                } else {
                    System.err.println("Error opening Allure report. Exit code: " + openExitCode);
                    java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(openProcess.getErrorStream()));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.err.println(line);
                    }
                }
            } else {
                System.err.println("Error generating Allure report. Exit code: " + generateExitCode);
                java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(generateProcess.getErrorStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.err.println(line);
                }
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Failed to serve Allure Report");
        }
    }

//    private static void startSimpleHttpServer() {
//        File reportDir = new File(ALLURE_REPORT_PATH);
//        try {
//            String command = String.format("python -m http.server %d", HTTP_SERVER_PORT);
//            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
//            processBuilder.directory(reportDir);
//            Process serverProcess = processBuilder.start();
//            System.out.println("Simple HTTP server started on port " + HTTP_SERVER_PORT + " in directory: " + reportDir.getAbsolutePath());
//
//            // You might want to store this process and potentially shut it down later
//            // However, for a simple automatic opening, letting it run in the background is usually sufficient.
//            // Be mindful of resource usage if running many reports.
//        } catch (IOException e) {
//            System.err.println("Error starting simple HTTP server: " + e.getMessage());
//        }
//    }
//
//    private static void openAllureReportInBrowserViaHttp() {
//        String reportUrl = String.format("http://localhost:%d", HTTP_SERVER_PORT);
//        try {
//            if (Desktop.isDesktopSupported()) {
//                Desktop.getDesktop().browse(new java.net.URI(reportUrl));
//                System.out.println("Allure report opened in the default browser via HTTP.");
//            } else {
//                System.err.println("Desktop browsing is not supported on this platform.");
//                String os = System.getProperty("os.name").toLowerCase();
//                if (os.contains("win")) {
//                    Runtime.getRuntime().exec("explorer " + reportUrl);
//                } else if (os.contains("mac")) {
//                    Runtime.getRuntime().exec("open " + reportUrl);
//                } else if (os.contains("nix") || os.contains("nux")) {
//                    Runtime.getRuntime().exec("xdg-open " + reportUrl);
//                } else {
//                    System.err.println("Could not automatically open the report on this operating system.");
//                }
//            }
//        } catch (IOException | URISyntaxException e) {
//            System.err.println("Error opening Allure report in browser via HTTP: " + e.getMessage());
//        }
//    }
}
