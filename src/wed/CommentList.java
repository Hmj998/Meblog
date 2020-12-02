package wed;

import com.google.gson.Gson;
import comment.Comment;
import dao.CommentDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/comments/list")
public class CommentList  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        try{
            CommentDAO commentDAO = new CommentDAO();
            List<Comment> comments = commentDAO.getAll();
            PrintWriter writer = resp.getWriter();
            writer.write(new Gson().toJson(comments));
        }catch (Exception e){
            PrintWriter writer = resp.getWriter();
            e.printStackTrace();
            writer.write("<div class='error'>Load failed.</div>");
        }
    }
//    public String index(HttpServletResponse resp){
//        resp.addHeader("Access-Control-Allow-Origin","*");
//        return "hello";
//    }
}
