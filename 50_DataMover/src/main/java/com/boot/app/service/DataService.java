package com.boot.app.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class DataService {
	String schema = "mp1.abc";
	public int BATCH_EXECUTE_SIZE=1000;
	
	@Autowired
	@Qualifier("SOURCE")
	Connection src;
	
	@Autowired
	@Qualifier("TARGET")
	Connection trg;
	
	public Integer move(String tableName, String whereCondition){
		Integer count = 0;
		String selectQuery = "select * from " + schema + tableName+" where " + whereCondition + " with ur;";
		System.out.println("selectQuery: " + selectQuery);
		
		PreparedStatement selectStatement = null;
		PreparedStatement insertStatement = null;
		ResultSet resultSet = null;
		try{
			selectStatement = src.prepareStatement(selectQuery);
			resultSet = selectStatement.executeQuery();
			
			String insertQuery = createInsertSql(resultSet.getMetaData());
			System.out.println("insert Query: " + insertQuery);
			
			insertStatement = trg.prepareStatement(insertQuery);
			
			int batchSize = 0;
			while(resultSet.next()){
				setParameters(insertStatement, resultSet);
				insertStatement.addBatch();
				batchSize++;
				
				if( batchSize >= BATCH_EXECUTE_SIZE){
					insertStatement.executeBatch();
					count += batchSize;
					batchSize = 0;
				}
			}
			insertStatement.executeBatch();
			count += batchSize;
		} catch (SQLException e){
			System.out.println("inserted=" + count);
			e.printStackTrace();
		}
		
		return count;
	}

	private void setParameters(PreparedStatement preparedStatement,ResultSet resultSet) throws SQLException {
		for(int i=1; i<=resultSet.getMetaData().getColumnCount(); i++){
			preparedStatement.setObject(i, resultSet.getObject(i));
		}
	}

	private String createInsertSql(ResultSetMetaData resultSetMetaData) throws SQLException {
		StringBuffer insertSql = new StringBuffer("INSERT INTO ");
		StringBuffer values = new StringBuffer(" VALUES (");
		
		insertSql.append( resultSetMetaData.getTableName(1).trim()+ " (");
		
		int columnCount = resultSetMetaData.getColumnCount();
		
		int i = 1;
		for( ; i<columnCount; i++){
			insertSql.append(resultSetMetaData.getColumnName(i) + ", ");
			values.append("?, ");
		}
		insertSql.append(resultSetMetaData.getColumnName(i) + " )");
		values.append("? )");
		
		return insertSql.toString() + values.toString();
	}

}
