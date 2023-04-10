package by.tms.servlet;

import by.tms.entity.Operation;
import by.tms.entity.OperationType;
import by.tms.entity.User;
import by.tms.service.*;
import by.tms.validator.CalculatorValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "CalcServlet", urlPatterns = "/calc")
public class CalcServlet extends HttpServlet {
    private double result;
    private int userId;
    private final CalculatorValidator validator = new CalculatorValidator();
    private final CalculatorService calculatorService = CalculatorService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/calc.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String type = req.getParameter("type");

        if (validator.isValidNum(num1) & validator.isValidNum(num2)) {
            double dNum1 = Double.parseDouble(num1);
            double dNum2 = Double.parseDouble(num2);
            OperationType opType = OperationType.valueOf(type.toUpperCase());
            if (req.getSession().getAttribute("user") != null) {
                User user = (User) req.getSession().getAttribute("user");
                userId = user.getUserId();
            }
            Operation operation = new Operation(dNum1, dNum2, userId);
            switch (opType){
                case SUM:
                    result = calculatorService.calculate(new SumOperation(operation));
                    break;
                case SUB:
                    result = calculatorService.calculate(new SubOperation(operation));
                    break;
                case MUL:
                    result = calculatorService.calculate(new MulOperation(operation));
                    break;
                case DIV:
                    result = calculatorService.calculate(new DivOperation(operation));
                    break;
            }

            req.setAttribute("result", result);
            req.getRequestDispatcher("/pages/calc.jsp").forward(req, resp);
        } else {
            req.setAttribute("calcMessage", "Numbers inputted wrong, please try again");
            req.getRequestDispatcher("/pages/calc.jsp").forward(req, resp);
        }
    }
}
