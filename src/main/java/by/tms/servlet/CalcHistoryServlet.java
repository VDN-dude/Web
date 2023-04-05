package by.tms.servlet;

import by.tms.entity.Operation;
import by.tms.entity.User;
import by.tms.service.CalculatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/calchistory")
public class CalcHistoryServlet extends HttpServlet {
    private final CalculatorService calculatorService = new CalculatorService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Operation> operationList = calculatorService.findUserOperations(user.getUsername());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd");
        StringBuilder operationBuilder = new StringBuilder();

        for (Operation operation : operationList) {
            String stringOperation = operation.getNum1() + " " + operation.getType() + " " + operation.getNum2() + " result " + operation.getResult() + " - time: " + operation.getTime().format(formatter);
            operationBuilder.append(stringOperation);
            operationBuilder.append("\n");
        }

        String history = operationBuilder.toString();
        String[] splitHistory = history.split("\n");
        req.setAttribute("splitHistory", splitHistory);
        req.getRequestDispatcher("/pages/calchistory.jsp").forward(req, resp);
    }

}
