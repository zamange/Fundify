package fundify;

import java.util.*;

public class FinancialLiteracyModule {

    private static Map<String, List<String>> educationalModules = new HashMap<>();
    private static Map<String, String> userProgress = new HashMap<>();
    private static String userLanguage = "English"; // Default language
    private static List<String> supportedLanguages = Arrays.asList("English", "Spanish", "French", "Mandarin");
    private static Map<String, List<QuizQuestion>> quizzes = new HashMap<>();



    static {
        // South African context-specific educational content
        educationalModules.put("Budgeting", Arrays.asList(
                "Module 1: Basics of Budgeting in South Africa\n" +
                        "1. Why Budgeting is Essential for Financial Health:\n" +
                        "   - **Understanding Income and Expenses**: Budgeting helps you clearly see how much money you earn versus how much you spend. This understanding is crucial for financial planning and achieving financial goals.\n" +
                        "   - **Avoiding Debt**: A well-structured budget helps prevent overspending, thereby reducing reliance on credit and avoiding debt accumulation. It empowers you to live within your means.\n" +
                        "   - **Financial Goals Achievement**: Budgeting is key to achieving both short-term and long-term financial goals, such as saving for a home, education, or retirement.\n" +
                        "   - **Peace of Mind**: Having a budget reduces financial stress by providing a clear plan for managing money, making it easier to navigate unexpected expenses.\n" +
                        "\n" +
                        "2. Steps to Start Creating a Budget:\n" +
                        "   - **Gather Financial Information**: Collect all financial documents, including pay stubs, bills, bank statements, and previous spending records to get a complete picture of your finances.\n" +
                        "   - **List Income Sources**: Identify all sources of income, including salary, freelance work, and any additional earnings. Calculate your total monthly income.\n" +
                        "   - **Track Your Expenses**: Monitor your monthly expenses by categorizing them into fixed (rent, utilities) and variable (groceries, entertainment) expenses to see where your money goes.\n" +
                        "   - **Set Budgeting Goals**: Determine your financial goals, such as saving for retirement or paying off debt, and allocate funds accordingly.\n" +
                        "   - **Create Your Budget**: Use the information gathered to create a budget that outlines your income and expenses, ensuring that you allocate money for savings and discretionary spending.",

                "Module 2: Creating a Personal Budget\n" +
                        "1. Tips on Setting Financial Goals:\n" +
                        "   - **SMART Goals**: Apply the SMART criteria (Specific, Measurable, Achievable, Relevant, Time-bound) when setting financial goals. For example, rather than saying, \"I want to save money,\" specify, \"I want to save R5,000 for a holiday by December.\"\n" +
                        "   - **Short-term vs. Long-term Goals**: Differentiate between short-term (within a year) and long-term goals (more than a year) to balance your budgeting priorities effectively.\n" +
                        "   - **Review and Adjust Goals Regularly**: Regularly assess your progress towards your financial goals and make adjustments to your budget as needed.\n" +
                        "\n" +
                        "2. Tracking Income and Expenses:\n" +
                        "   - **Regular Monitoring**: Keep track of your income and expenses weekly or monthly to stay on top of your budget. Consider using spreadsheets or budgeting apps for ease of tracking.\n" +
                        "   - **Expense Categorization**: Break down your expenses into categories such as housing, food, transport, and entertainment to identify areas for potential savings.\n" +
                        "\n" +
                        "3. Tools and Apps for Managing Personal Budgets:\n" +
                        "   - **Budgeting Apps**: Utilize apps like 22seven, Mint, or YNAB (You Need a Budget) that can help you track spending and stay on top of your budget in real-time.\n" +
                        "   - **Spreadsheets**: Create your own budget spreadsheet using software like Microsoft Excel or Google Sheets, which allows for customization and detailed tracking.\n" +
                        "   - **Envelope System**: Consider the envelope budgeting method, where you allocate cash for different spending categories in separate envelopes to limit spending in each category.",

                "Module 3: Managing Monthly Expenses\n" +
                        "1. Strategies to Prioritize and Control Monthly Expenses:\n" +
                        "   - **Needs vs. Wants**: Differentiate between essential needs (housing, food, transportation) and discretionary wants (dining out, subscriptions). Prioritize spending on needs first.\n" +
                        "   - **50/30/20 Rule**: Follow the 50/30/20 rule, where 50% of your income goes to needs, 30% to wants, and 20% to savings and debt repayment. This can help maintain a balanced approach to budgeting.\n" +
                        "   - **Monthly Reviews**: Conduct monthly budget reviews to assess spending patterns and make necessary adjustments to stay aligned with your financial goals.\n" +
                        "\n" +
                        "2. Common Pitfalls to Avoid:\n" +
                        "   - **Neglecting Small Expenses**: Small, regular expenses can add up over time. Be sure to include them in your budget to get a complete view of your finances.\n" +
                        "   - **Inflexibility**: While it's important to stick to your budget, be flexible enough to adjust it as your financial situation or goals change. Life is unpredictable, and your budget should accommodate that.\n" +
                        "   - **Not Planning for Irregular Expenses**: Irregular expenses, such as car maintenance or holiday shopping, can disrupt your budget. Plan for these by allocating funds in advance.\n" +
                        "   - **Failing to Review**: Regularly reviewing and adjusting your budget is essential to account for changes in income, expenses, or financial goals. Neglecting this can lead to financial strain."
        ));

        educationalModules.put("Savings", Arrays.asList(
                "Module 1: Importance of Saving\n" +
                        "1. Benefits of Having Savings:\n" +
                        "   - **Financial Security**: Having savings provides a safety net against unexpected expenses, such as medical emergencies, car repairs, or job loss. It ensures that you can handle financial shocks without falling into debt.\n" +
                        "   - **Meeting Future Needs**: Savings help you plan for significant life events, such as buying a home, funding your children's education, or taking a dream vacation. It allows you to achieve your goals without relying on credit.\n" +
                        "   - **Peace of Mind**: Knowing you have savings can reduce stress and anxiety about financial uncertainty. It provides a sense of control over your financial future.\n" +
                        "\n" +
                        "2. Contribution to Long-term Financial Independence:\n" +
                        "   - **Investment Opportunities**: Savings allow you to invest in opportunities that can grow your wealth over time, such as stocks, bonds, or real estate.\n" +
                        "   - **Retirement Preparedness**: Consistent saving contributes to a comfortable retirement. The earlier you start saving, the more you benefit from compound interest, which can significantly increase your retirement funds.\n" +
                        "   - **Debt Avoidance**: Savings can help you avoid high-interest debt by allowing you to pay for large purchases upfront instead of relying on credit cards or loans. This leads to better financial health and more freedom in your financial decisions.",

                "Module 2: Saving Strategies\n" +
                        "1. Types of Saving Plans:\n" +
                        "   - **Emergency Funds**: An emergency fund is essential to cover 3 to 6 months’ worth of living expenses. This fund is used for unforeseen events and helps you avoid going into debt during financial emergencies.\n" +
                        "   - **Retirement Savings**: Contributing to retirement accounts such as a pension fund, IRA, or 401(k) helps ensure you have sufficient funds for your retirement years. The earlier you start, the more your savings can grow through compound interest.\n" +
                        "   - **Short-term Savings Goals**: Identify specific savings goals for the short term, such as vacations or home renovations. Setting aside a certain amount each month toward these goals can help you achieve them without impacting your daily finances.\n" +
                        "\n" +
                        "2. Techniques for Consistent Saving:\n" +
                        "   - **Pay Yourself First**: Treat your savings like a recurring expense. Set up automatic transfers from your checking account to your savings account each month to prioritize saving before spending.\n" +
                        "   - **Create a Budget**: Developing a budget helps you track your income and expenses, allowing you to identify areas where you can cut back and allocate those funds to savings.\n" +
                        "   - **Use Savings Apps**: Consider using budgeting and savings apps that round up your purchases and save the difference, or offer suggestions on how to save more efficiently. These tools can help you stay accountable and motivated to save.\n" +
                        "   - **Set Clear Goals**: Define specific savings goals with timelines. Having a clear objective, such as saving for a vacation or a new car, can motivate you to save consistently.\n" +
                        "   - **Limit Impulse Purchases**: Implement strategies to reduce impulse buying, such as creating a 24-hour rule before making non-essential purchases. This helps you prioritize your savings over spontaneous spending.",

                "Module 3: Building a Habit of Saving\n" +
                        "1. Tips to Develop a Saving Mindset:\n" +
                        "   - **Visualize Your Goals**: Keep your savings goals visible. Create a vision board or set reminders on your phone to stay focused on what you are saving for. This constant reminder can motivate you to save consistently.\n" +
                        "   - **Celebrate Milestones**: Recognize and reward yourself for reaching savings milestones. Celebrating small achievements keeps you motivated and reinforces the habit of saving.\n" +
                        "   - **Accountability Partner**: Share your savings goals with a trusted friend or family member. Having someone to support and encourage you can help maintain your commitment to saving.\n" +
                        "\n" +
                        "2. Overcoming Challenges to Saving:\n" +
                        "   - **Addressing Financial Stress**: Identify triggers of financial stress and develop coping strategies to manage them. Being proactive about addressing your financial concerns can help you stay focused on saving.\n" +
                        "   - **Finding Extra Income**: Explore opportunities for side jobs or freelance work to increase your income. The extra earnings can be directed toward savings goals, accelerating your progress.\n" +
                        "   - **Changing Habits**: Reevaluate your spending habits and make conscious choices to prioritize savings. Small changes, like dining out less often or canceling unused subscriptions, can free up funds for savings."
        ));

        // Populate quizzes
        populateQuizzes();
    }

    private static void populateQuizzes() {
        quizzes.put("Budgeting", Arrays.asList(
                new QuizQuestion("What is the 50/30/20 rule?", "A budgeting method", "A method for investing", "A loan type", "A savings account", "A budgeting method"),
                new QuizQuestion("What should be prioritized in a budget?", "Savings", "Wants", "Debts", "Expenses", "Savings"),
                new QuizQuestion("What is an emergency fund?", "Money set aside for unexpected expenses", "Money for vacations", "Money for groceries", "Money for investments", "Money set aside for unexpected expenses")
        ));

        quizzes.put("Savings", Arrays.asList(
                new QuizQuestion("Why is it essential to have savings?", "For financial security", "For spending", "For investments", "For gifts", "For financial security"),
                new QuizQuestion("What is a retirement savings plan?", "A fund for future expenses", "A loan type", "A savings account", "A spending account", "A fund for future expenses"),
                new QuizQuestion("What does 'pay yourself first' mean?", "Save before spending", "Spend before saving", "Invest first", "Budget only", "Save before spending")
        ));
    }

    public static void accessFinancialEducation() {
        System.out.println("Welcome to the Financial Literacy Module!");
        System.out.println("Available Topics:");
        for (String topic : educationalModules.keySet()) {
            System.out.println("- " + topic);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Select a topic to learn about (e.g., Budgeting, Savings): ");
        String selectedTopic = scanner.nextLine();

        if (educationalModules.containsKey(selectedTopic)) {
            List<String> modules = educationalModules.get(selectedTopic);
            for (String module : modules) {
                System.out.println(module);
            }
            // Ask if the user wants to complete the module or take a quiz
            System.out.print("Do you want to complete this module? (yes/no): ");
            String complete = scanner.nextLine();
            if (complete.equalsIgnoreCase("yes")) {
                completeModule(selectedTopic);
            }

            System.out.print("Do you want to take a quiz on this module? (yes/no): ");
            String takeQuiz = scanner.nextLine();
            if (takeQuiz.equalsIgnoreCase("yes")) {
                startQuiz(selectedTopic);
            }
        } else {
            System.out.println("Invalid topic selected.");
        }
    }


    public void changeLanguage(String language) {
        if (supportedLanguages.contains(language)) {
            userLanguage = language;
            System.out.println("Language changed to: " + userLanguage);
        } else {
            System.out.println("Language not supported.");
        }
    }

    public static void completeModule(String moduleName) {
        userProgress.put(moduleName, "completed");
        System.out.println("You have completed the module: " + moduleName);
    }

    public static void startQuiz(String moduleName) {
        if (!quizzes.containsKey(moduleName)) {
            System.out.println("No quiz available for this module.");
            return;
        }

        System.out.println("Starting quiz for module: " + moduleName);
        List<QuizQuestion> quizQuestions = quizzes.get(moduleName);
        int score = 0;

        for (QuizQuestion question : quizQuestions) {
            System.out.println(question.getQuestion());
            System.out.println("A: " + question.getOptionA());
            System.out.println("B: " + question.getOptionB());
            System.out.println("C: " + question.getOptionC());
            System.out.println("D: " + question.getOptionD());
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine().toUpperCase();

            if (answer.equals("A") && question.getCorrectAnswer().equals(question.getOptionA()) ||
                    answer.equals("B") && question.getCorrectAnswer().equals(question.getOptionB()) ||
                    answer.equals("C") && question.getCorrectAnswer().equals(question.getOptionC()) ||
                    answer.equals("D") && question.getCorrectAnswer().equals(question.getOptionD())) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect! The correct answer is: " + question.getCorrectAnswer());
            }
        }

        System.out.println("You scored " + score + " out of " + quizQuestions.size());
    }

    public void displayEducationalContent(String moduleName) {
        List<String> content = educationalModules.get(moduleName);
        if (content != null) {
            System.out.println("Educational Content for " + moduleName + ":");
            for (String module : content) {
                System.out.println(module);
            }
        } else {
            System.out.println("Module not found.");
        }
    }
}


class QuizQuestion {
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctAnswer;

    public QuizQuestion(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
