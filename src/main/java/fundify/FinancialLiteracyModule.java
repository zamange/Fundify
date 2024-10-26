package fundify;

import java.util.*;

public class FinancialLiteracyModule {

    private static Map<String, List<String>> educationalModules = new HashMap<>();
    private static Map<String, String> userProgress = new HashMap<>();
    private static String userLanguage = "English"; // Default language
    private static List<String> supportedLanguages = Arrays.asList("English", "Spanish", "French", "Mandarin");

    static {
        educationalModules.put("Budgeting", Arrays.asList("Module 1: Basics of Budgeting", "Module 2: Creating a Budget"));
        educationalModules.put("Saving", Arrays.asList("Module 1: Importance of Saving", "Module 2: Saving Strategies"));
        educationalModules.put("Debt Management", Arrays.asList("Module 1: Understanding Debt", "Module 2: Managing Debt"));
    }

    public static void main(String[] args) {
        // Simulate business owner logging into the app
        System.out.println("Business owner logs into the Fundify app.");

        // Navigate to the "Financial Education" section
        accessFinancialEducation();

        // Simulate selecting a preferred language
        selectPreferredLanguage("English");
    }

    public static void accessFinancialEducation() {
        System.out.println("Navigating to the 'Financial Education' section...");
        System.out.println("Available Modules:");

        for (String module : educationalModules.keySet()) {
            System.out.println(module + ": " + educationalModules.get(module));
        }

        // Simulate taking a quiz and saving progress
        String moduleName = "Budgeting";
        takeQuiz(moduleName);
    }

    public static void takeQuiz(String moduleName) {
        if (educationalModules.containsKey(moduleName)) {
            System.out.println("Taking quiz for " + moduleName + "...");
            // Here, implement the quiz logic and save progress
            userProgress.put(moduleName, "Completed");
            System.out.println("Progress saved for " + moduleName + ".");
        } else {
            System.out.println("Module not found.");
        }
    }

    public static void selectPreferredLanguage(String language) {
        if (supportedLanguages.contains(language)) {
            userLanguage = language;
            System.out.println("Language updated to " + userLanguage + ".");
            updateContentLanguage();
        } else {
            System.out.println("Selected language is not supported.");
        }
    }

    public static void updateContentLanguage() {
        // This method should update the educational content based on the selected language.
        System.out.println("Updating educational content to " + userLanguage + "...");
        // Here you would load the translations for the modules and any instructions.
    }
}
