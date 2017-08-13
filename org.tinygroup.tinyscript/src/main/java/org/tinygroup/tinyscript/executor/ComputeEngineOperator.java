package org.tinygroup.tinyscript.executor;

import org.tinygroup.tinyscript.ScriptEngine;
import org.tinygroup.tinyscript.ScriptException;
import org.tinygroup.tinyscript.executor.AbstractTinyScriptOperator;
import org.tinygroup.tinyscript.impl.DefaultComputeEngine;

/**
 * tinyscript的脚本模拟器实现
 * @author yancheng11334
 *
 */
public class ComputeEngineOperator extends AbstractTinyScriptOperator{

	//生成tinyscript引擎
	public ScriptEngine createScriptEngine() throws ScriptException {
		return new DefaultComputeEngine();
	}

}
