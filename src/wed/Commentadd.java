package wed;

import comment.Comment;
import dao.CommentDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comment/add")
public class Commentadd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        String author = req.getParameter("author");
        String body = req.getParameter("body");
        try{
            Comment comm = new Comment();
            comm.setAuthor(author);
            comm.setBody(body);
            new CommentDAO().addComment(new Comment(author,body));
            //resp.getWriter().print(new Gson().toJson(new CommentDAO().addComment(new Comment(author,body))));
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("添加错误");
        }
    }
}
