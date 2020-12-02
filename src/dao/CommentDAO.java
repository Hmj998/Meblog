package dao;

import comment.Comment;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBHelper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class CommentDAO {
    public List<Comment> getAll()throws Exception{
        Connection connection = DBHelper.getConnection();
        String sql = "select id,author,body from comments";
        try{
            return new QueryRunner().query(connection,sql,new BeanListHandler<Comment>(Comment.class));
        }finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public Comment addComment(Comment comment)throws Exception{
        Connection connection = DBHelper.getConnection();
        String sql = "insert into comments(author,body)values(?,?)";
        Object[]params = {
                comment.getAuthor(),comment.getBody()
        };
        try {
            BigDecimal res = new QueryRunner().insert(connection,sql, new ScalarHandler<BigDecimal>(),params);
            comment.setId(res.intValue());
            return comment;
        }finally {
            DbUtils.closeQuietly(connection);
        }
    }

    public void delete (int id)throws Exception{
        Connection connection = DBHelper.getConnection();
        String sql = "delete from comments where id = ?";
        try{
            new QueryRunner().update(connection,sql,id);
        }finally {
            DbUtils.closeQuietly(connection);
        }
    }
}
