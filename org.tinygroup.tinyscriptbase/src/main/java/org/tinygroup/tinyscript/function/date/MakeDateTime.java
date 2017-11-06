package org.tinygroup.tinyscript.function.date;

import java.util.Calendar;
import java.util.Date;

import org.tinygroup.tinyscript.ScriptContext;
import org.tinygroup.tinyscript.ScriptException;
import org.tinygroup.tinyscript.ScriptSegment;
import org.tinygroup.tinyscript.function.AbstractScriptFunction;
import org.tinygroup.tinyscript.interpret.ResourceBundleUtil;
import org.tinygroup.tinyscript.interpret.exception.NotMatchException;

public class MakeDateTime extends AbstractScriptFunction {

	@Override
	public String getNames() {
		return "makeDateTime";
	}

	@Override
	public Object execute(ScriptSegment segment, ScriptContext context, Object... parameters) throws ScriptException {
		try {
			Calendar cal = Calendar.getInstance();
			if (parameters == null || parameters.length == 0) {
				throw new ScriptException(ResourceBundleUtil.getDefaultMessage("function.parameter.empty", getNames()));
			} else if (checkParameters(parameters, 2)) {
				Date date = (Date) parameters[0];
				Date time = (Date) parameters[1];
				Calendar timeCal = Calendar.getInstance();
				timeCal.setTime(time);
				cal.setTime(date);
				cal.set(Calendar.HOUR, timeCal.get(Calendar.HOUR));
				cal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
				cal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
				return cal.getTime();
			} else {
				throw new NotMatchException(
						ResourceBundleUtil.getDefaultMessage("function.parameter.error", getNames()));
			}
		} catch (ScriptException e) {
			throw e;
		} catch (Exception e) {
			throw new ScriptException(ResourceBundleUtil.getDefaultMessage("function.run.error", getNames()), e);
		}
	}

}
