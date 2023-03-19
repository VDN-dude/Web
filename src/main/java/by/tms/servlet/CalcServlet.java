package by.tms.servlet;

import by.tms.entity.Operation;
import by.tms.entity.OperationType;
import by.tms.service.CalculatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/calc", name = "CalcServlet")
public class CalcServlet extends HttpServlet {
    CalculatorService calculatorService = new CalculatorService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        double dNum1 = Double.parseDouble(req.getParameter("num1"));
        double dNum2 = Double.parseDouble(req.getParameter("num2"));
        OperationType opType = OperationType.valueOf(req.getParameter("type").toUpperCase());

        Operation operation = new Operation(dNum1, dNum2, opType);
        Optional<Operation> result = calculatorService.calculate(operation);

        resp.getWriter().print("Result " + result.get().getResult());
    }
}
