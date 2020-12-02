package wed;

import dao.CommentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comment/del")
public class Commentdelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        try{
            new CommentDAO().delete(id);
            resp.getWriter().print(id);
        }catch (Exception e){
            e.printStackTrace();
            resp.getWriter().print("-1");
        }
    }
}
