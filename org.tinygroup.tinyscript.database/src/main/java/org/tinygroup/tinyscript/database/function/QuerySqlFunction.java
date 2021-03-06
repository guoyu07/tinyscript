package org.tinygroup.tinyscript.database.function;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.tinygroup.tinyscript.ScriptContext;
import org.tinygroup.tinyscript.ScriptException;
import org.tinygroup.tinyscript.ScriptSegment;
import org.tinygroup.tinyscript.database.ResultSetDataSet;
import org.tinygroup.tinyscript.dataset.DataSet;
import org.tinygroup.tinyscript.dataset.impl.VariableDataSet;
import org.tinygroup.tinyscript.function.AbstractScriptFunction;
import org.tinygroup.tinyscript.interpret.ResourceBundleUtil;

public class QuerySqlFunction extends AbstractScriptFunction {

	public String getNames() {
		return "query";
	}
	
	public String getBindingTypes() {
		return "javax.sql.DataSource,java.sql.Connection";
	}

	public Object execute(ScriptSegment segment, ScriptContext context,
			Object... parameters) throws ScriptException {
		try{
			if (parameters == null || parameters.length == 0) {
				throw new ScriptException(ResourceBundleUtil.getDefaultMessage("function.parameter.empty", getNames()));
			}else if(parameters.length == 2 && parameters[0] != null && parameters[1] != null){
				String sql = (String) parameters[1];
				if(parameters[0] instanceof DataSource){
					return queryDataSource((DataSource)parameters[0],sql);
				}else if(parameters[0] instanceof Connection){
					return queryConnection((Connection)parameters[0],sql);
				}else{
					throw new ScriptException(ResourceBundleUtil.getDefaultMessage("function.parameter.error", getNames()));
				}
			}else {
				throw new ScriptException(ResourceBundleUtil.getDefaultMessage("function.parameter.error", getNames()));
			}
		}catch (ScriptException e) {
			throw e;
		} catch (Exception e) {
			throw new ScriptException(ResourceBundleUtil.getDefaultMessage("function.run.error", getNames()), e);
		}
	}
	
	private DataSet queryDataSource(DataSource dataSource,String sql) throws Exception{
		Connection conn = null;
		Statement statement=null;
		try{
			conn = dataSource.getConnection();
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			return new VariableDataSet(new ResultSetDataSet(result,getScriptEngine().isIndexFromOne()));
		}finally{
			if(statement!=null){
			   statement.close();
			}
			if(conn!=null){
			   conn.close();
			}
		}
	}
	
	private DataSet queryConnection(Connection conn,String sql) throws Exception{
		Statement statement=null;
		try{
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			return new VariableDataSet(new ResultSetDataSet(result,getScriptEngine().isIndexFromOne()));
		}finally{
			if(statement!=null){
			   statement.close();
			}
		}
	}

}
