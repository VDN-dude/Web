package by.tms.validator;

import java.util.regex.Pattern;

public class CalculatorValidator {
    private static final Pattern NUM = Pattern.compile("^\\d[.\\d]*$");
    private static final Pattern OperationType = Pattern.compile("^(SUM)\\b|(SUB)\\b|(MUL)\\b|(DIV)\\b$");

    public boolean isValidNum(String num){return NUM.matcher(num).matches();}
    public boolean isValidOperationType(String type){return OperationType.matcher(type).matches();}
}
