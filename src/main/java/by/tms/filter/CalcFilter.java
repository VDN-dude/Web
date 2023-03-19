package by.tms.filter;

import by.tms.validator.CalculatorValidator;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = "CalcServlet")
public class CalcFilter extends HttpFilter {
    CalculatorValidator validator = new CalculatorValidator();
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String type = req.getParameter("type");

        if(validator.isValidNum(num1) & validator.isValidNum(num2) & validator.isValidOperationType(type.toUpperCase())){
            chain.doFilter(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, "Invalid request or mistake in request");
        }
    }
}
