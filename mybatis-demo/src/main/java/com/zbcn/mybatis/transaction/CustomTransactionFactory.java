package com.zbcn.mybatis.transaction;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * 可以用 TransactionFactory 接口实现类的全限定名或类型别名代替默认的事物
 * eg:
 * <transactionManager type="com.zbcn.mybatis.transaction.CustomTransactionFactory"/>
 */
public class CustomTransactionFactory implements TransactionFactory {
    @Override
    public void setProperties(Properties props) {

    }

    @Override
    public Transaction newTransaction(Connection conn) {
        return new CustomTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new CustomTransaction(dataSource, level, autoCommit);
    }
}
