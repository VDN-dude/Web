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
import java.util.List;

@WebServlet("/calchistory")
public class CalcHistoryServlet extends HttpServlet {
    private final CalculatorService calculatorService = CalculatorService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Operation> operationList = calculatorService.findByUserId(user.getUserId());
        req.setAttribute("operationList", operationList);

        req.getRequestDispatcher("/pages/calchistory.jsp").forward(req, resp);
    }

}
