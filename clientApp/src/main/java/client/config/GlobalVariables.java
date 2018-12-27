package client.config;

public class GlobalVariables {
    private static Long totalVisitorsCount = 0L;
    public static final String REGEX_NUMBER = "^\\d+$";


    public static String getRegexNumber() {
        return REGEX_NUMBER;
    }

    public static Long getTotalVisitorsCount() {
        return totalVisitorsCount;
    }

    public static void setTotalVisitorsCount(Long totalVisitorsCount) {
        GlobalVariables.totalVisitorsCount = totalVisitorsCount;
    }
}
