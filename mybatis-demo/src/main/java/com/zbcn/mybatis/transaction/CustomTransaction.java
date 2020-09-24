package com.zbcn.mybatis.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *  自定义事物
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/22 14:22
 */
public class CustomTransaction extends JdbcTransaction implements Transaction {

    public CustomTransaction(DataSource ds, TransactionIsolationLevel desiredLevel, boolean desiredAutoCommit) {
        super(ds, desiredLevel, desiredAutoCommit);
    }

    public CustomTransaction(Connection connection) {
        super(connection);
    }

    @Override
    public Connection getConnection() throws SQLException {
        System.out.println("调用自定义获取链接信息方法");
        return super.getConnection();
    }

    @Override
    public void commit() throws SQLException {
        System.out.println("自定义提交事物方法");
        super.commit();
    }

    @Override
    public void rollback() throws SQLException {
        System.out.println("自定义回滚事物方法");
        super.rollback();
    }

    @Override
    public void close() throws SQLException {
        System.out.println("自定义close方法");
        super.close();
    }

    @Override
    public Integer getTimeout() throws SQLException {
        System.out.println("自定义timeout方法");
        return super.getTimeout();
    }
}
