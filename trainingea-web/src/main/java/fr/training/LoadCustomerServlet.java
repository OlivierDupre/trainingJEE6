package fr.training;

import static fr.training.CustomerCreationServlet.FIRST_NAME;
import static fr.training.CustomerCreationServlet.LAST_NAME;
import fr.training.trainingea.model.Customer;
import fr.training.trainingea.model.CustomerPK;
import fr.training.trainingea.service.AccountManagerLocal;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shuttle
 */
@WebServlet(name = "LoadCustomerServlet", urlPatterns = {"/loadCustomer"})
public class LoadCustomerServlet extends HttpServlet {

    @EJB
    private AccountManagerLocal accountManager;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoadCustomerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoadCustomerServlet at " + request.getContextPath() + "</h1>");

            Customer customer1 = accountManager.findCustomer(FIRST_NAME, LAST_NAME);
            out.printf("Client %s %s de %s agé de %d ans chargé.<br/>", customer1.getFirstName(), customer1.getLastName(), customer1.getAddress(), customer1.getAge());
            customer1.setAge(20);

            Customer customer2 = accountManager.findCustomer(FIRST_NAME, LAST_NAME);
            out.printf("Client %s %s de %s agé de %d ans chargé.<br/>", customer2.getFirstName(), customer2.getLastName(), customer2.getAddress(), customer2.getAge());
            customer2.setAge(30);

            accountManager.updateCustomer(customer2);
            out.printf("Client %s %s de %s agé de %d ans enregistré.<br/>", customer2.getFirstName(), customer2.getLastName(), customer2.getAddress(), customer2.getAge());
            accountManager.updateCustomer(customer1);
            out.printf("Client %s %s de %s agé de %d ans enregistré.<br/>", customer1.getFirstName(), customer1.getLastName(), customer1.getAddress(), customer1.getAge());

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
