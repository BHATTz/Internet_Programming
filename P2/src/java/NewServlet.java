import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(true);
            Integer count = (Integer)session.getAttribute("count");
            if (count == null) {
                out.println("Welcome New comer");
                count = 0;
            } else {
                out.println("Welcome, Back");
            }
            count++;
            session.setAttribute("count", count);
            out.println("<table border='1' align='center'>\n");
            out.println("<tr bgcolor='#FFAD00'>\n");
            out.println("<td>ID</td><td>" + session.getId() + "</td></tr>\n");
            out.println("<tr><td>Creation Time</td><td>" + new Date(session.getCreationTime()) + "</td></tr>\n");
            out.println("<tr><td>Time of Last Access</td><td>" + new Date(session.getLastAccessedTime()) + "</td></tr>\n");
            out.println("<tr><td>Number of Previous Access</td><td>" + count + "</td></tr>\n");
            out.println("</table>");
            if (count >= 10) {
                session.invalidate();
                out.println("<p>Your session has expired.</p>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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