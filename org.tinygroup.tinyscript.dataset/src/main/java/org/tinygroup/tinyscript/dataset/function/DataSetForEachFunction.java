package org.tinygroup.tinyscript.dataset.function;

import org.tinygroup.tinyscript.ScriptContext;
import org.tinygroup.tinyscript.ScriptException;
import org.tinygroup.tinyscript.ScriptSegment;
import org.tinygroup.tinyscript.dataset.AbstractDataSet;
import org.tinygroup.tinyscript.dataset.DataSet;
import org.tinygroup.tinyscript.dataset.impl.DefaultDataSetRow;
import org.tinygroup.tinyscript.dataset.util.DataSetUtil;
import org.tinygroup.tinyscript.function.AbstractScriptFunction;
import org.tinygroup.tinyscript.interpret.LambdaFunction;
import org.tinygroup.tinyscript.interpret.ResourceBundleUtil;
import org.tinygroup.tinyscript.interpret.ScriptContextUtil;
import org.tinygroup.tinyscript.interpret.ScriptResult;

/**
 * 数据集执行forEach代码块
 * 
 * @author yancheng11334
 *
 */
public class DataSetForEachFunction extends AbstractScriptFunction {

	public String getNames() {
		return "forEach";
	}

	public String getBindingTypes() {
		return DataSet.class.getName();
	}

	public Object execute(ScriptSegment segment, ScriptContext context, Object... parameters) throws ScriptException {
		try {
			if (parameters == null || parameters.length == 0) {
				throw new ScriptException(ResourceBundleUtil.getDefaultMessage("function.parameter.empty", getNames()));
			} else if (checkParameters(parameters, 2)) {
				AbstractDataSet dataSet = (AbstractDataSet) parameters[0];
				LambdaFunction function = (LambdaFunction) parameters[1];

				return forEach(dataSet, function, context);
			} else {
				throw new ScriptException(ResourceBundleUtil.getDefaultMessage("function.parameter.error", getNames()));
			}
		} catch (ScriptException e) {
			throw e;
		} catch (Exception e) {
			throw new ScriptException(ResourceBundleUtil.getDefaultMessage("function.run.error", getNames()), e);
		}
	}

	private ScriptResult forEach(AbstractDataSet dataSet, LambdaFunction function, ScriptContext context)
			throws Exception {
		for (int i = 0; i < dataSet.getRows(); i++) {
			ScriptContextUtil.setCurData(context, new DefaultDataSetRow(dataSet, dataSet.getShowIndex(i)));
			DataSetUtil.setRowContext(dataSet, context, i);
			function.execute(context, dataSet.getShowIndex(i));
		}
		DataSetUtil.clearRowContext(dataSet, context);
		return ScriptResult.VOID_RESULT;
	}

}
