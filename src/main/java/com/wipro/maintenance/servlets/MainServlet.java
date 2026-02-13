package com.wipro.maintenance.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.wipro.maintenance.bean.MaintenanceBean;
import com.wipro.maintenance.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("menu.html");
    }
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");
        Administrator admin = new Administrator();

        try {
            if ("newRecord".equals(operation)) {

                MaintenanceBean bean = new MaintenanceBean();
                bean.setRequesterName(request.getParameter("requesterName"));
                bean.setIssueType(request.getParameter("issueType"));
                bean.setPriority(request.getParameter("priority"));
                bean.setRemarks(request.getParameter("remarks"));

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date requestDate = sdf.parse(request.getParameter("requestDate"));
                bean.setRequestDate(requestDate);

                String result = admin.addRecord(bean);

                if (result.equalsIgnoreCase("FAIL") ||
                    result.contains("INVALID") ||
                    result.equalsIgnoreCase("ALREADY EXISTS")) {

                    response.sendRedirect("error.html");
                } else {
                    response.sendRedirect("success.html");
                }
            }
            else if ("viewRecord".equals(operation)) {

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date requestDate = sdf.parse(request.getParameter("requestDate"));

                MaintenanceBean bean =
                        admin.viewRecord(request.getParameter("requesterName"), requestDate);

                request.setAttribute("bean", bean);

                RequestDispatcher rd =
                        request.getRequestDispatcher("displayRequest.jsp");
                rd.forward(request, response);
            }
            else if ("viewAllRecords".equals(operation)) {

                List<MaintenanceBean> list = admin.viewAllRecords();

                request.setAttribute("list", list);

                RequestDispatcher rd =
                        request.getRequestDispatcher("displayAllRequests.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();
            response.setContentType("text/html");
            response.getWriter().println("<h3>Error Occurred</h3>");
            e.printStackTrace(response.getWriter());
        }
    }
}
