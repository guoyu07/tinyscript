package org.tinygroup.tinyscript.impl;

import java.util.Map;

import org.tinygroup.tinyscript.ComputeEngine;
import org.tinygroup.tinyscript.ScriptException;
import org.tinygroup.tinyscript.assignvalue.AssignValueProcessor;
import org.tinygroup.tinyscript.assignvalue.AssignValueUtil;
import org.tinygroup.tinyscript.collection.expression.IntersectionOperator;
import org.tinygroup.tinyscript.collection.expression.SubtractOperator;
import org.tinygroup.tinyscript.collection.expression.UnionOperator;
import org.tinygroup.tinyscript.collection.expression.XorOperator;
import org.tinygroup.tinyscript.collection.function.list.CopyFunction;
import org.tinygroup.tinyscript.collection.function.list.FillFunction;
import org.tinygroup.tinyscript.collection.function.list.FilterFunction;
import org.tinygroup.tinyscript.collection.function.list.IntersectionFunction;
import org.tinygroup.tinyscript.collection.function.list.RemoveFunction;
import org.tinygroup.tinyscript.collection.function.list.SortFunction;
import org.tinygroup.tinyscript.collection.function.list.SubtractFunction;
import org.tinygroup.tinyscript.collection.function.list.UnionFunction;
import org.tinygroup.tinyscript.collection.function.list.XorFunction;
import org.tinygroup.tinyscript.collection.function.map.MapIntersectionFunction;
import org.tinygroup.tinyscript.collection.function.map.MapSubtractFunction;
import org.tinygroup.tinyscript.collection.function.map.MapUnionFunction;
import org.tinygroup.tinyscript.collection.function.map.MapXorFunction;
import org.tinygroup.tinyscript.collection.function.math.AllPermutationFunction;
import org.tinygroup.tinyscript.collection.function.math.CombinationFunction;
import org.tinygroup.tinyscript.collection.function.math.PermutationFunction;
import org.tinygroup.tinyscript.collection.function.set.SetIntersectionFunction;
import org.tinygroup.tinyscript.collection.function.set.SetSubtractFunction;
import org.tinygroup.tinyscript.collection.function.set.SetUnionFunction;
import org.tinygroup.tinyscript.collection.function.set.SetXorFunction;
import org.tinygroup.tinyscript.collection.objectitem.ListToListProcessor;
import org.tinygroup.tinyscript.collection.objectitem.MapItemProcessor;
import org.tinygroup.tinyscript.database.function.ExecuteSqlFunction;
import org.tinygroup.tinyscript.database.function.QuerySqlFunction;
import org.tinygroup.tinyscript.database.sql.JDBCNamedSqlProcessor;
import org.tinygroup.tinyscript.dataset.attribute.DataSetColumnAttributeProcessor;
import org.tinygroup.tinyscript.dataset.attribute.ExcelAssignValueProcessor;
import org.tinygroup.tinyscript.dataset.attribute.ExcelAttributeProcessor;
import org.tinygroup.tinyscript.dataset.function.CursorRowFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetAggregateFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetConvertFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetCopyFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetFieldFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetFillFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetFilterFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetForEachFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetGroupDynamicFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetGroupFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetGroupStagedFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetJoinFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetLimitFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetMatchFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetRemoveFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetRenameFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetReplaceFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetSelectFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetSortFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetSubFunction;
import org.tinygroup.tinyscript.dataset.function.DataSetUpdateFunction;
import org.tinygroup.tinyscript.dataset.function.FirstRowFunction;
import org.tinygroup.tinyscript.dataset.function.GroupDataSetAggregateFunction;
import org.tinygroup.tinyscript.dataset.function.GroupDataSetFilterFunction;
import org.tinygroup.tinyscript.dataset.function.GroupDataSetSortFunction;
import org.tinygroup.tinyscript.dataset.function.LastRowFunction;
import org.tinygroup.tinyscript.dataset.function.NextRowFunction;
import org.tinygroup.tinyscript.dataset.function.PreviewRowFunction;
import org.tinygroup.tinyscript.dataset.objectitem.DataSetColumnItemProcessor;
import org.tinygroup.tinyscript.dataset.objectitem.DataSetItemProcessor;
import org.tinygroup.tinyscript.dataset.objectitem.DataSetRowItemProcessor;
import org.tinygroup.tinyscript.datasetwithtree.function.DataSetToTreeFunction;
import org.tinygroup.tinyscript.excel.function.ReadExcelFunction;
import org.tinygroup.tinyscript.expression.ExpressionUtil;
import org.tinygroup.tinyscript.expression.Operator;
import org.tinygroup.tinyscript.interpret.AttributeProcessor;
import org.tinygroup.tinyscript.interpret.AttributeUtil;
import org.tinygroup.tinyscript.interpret.ScriptContextUtil;
import org.tinygroup.tinyscript.interpret.custom.CustomProcessor;
import org.tinygroup.tinyscript.interpret.custom.CustomUtil;
import org.tinygroup.tinyscript.objectitem.ObjectItemProcessor;
import org.tinygroup.tinyscript.objectitem.ObjectItemUtil;
import org.tinygroup.tinyscript.text.function.ReadTxtFunction;
import org.tinygroup.tinyscript.tree.function.CreateDataTreeFunction;

/**
 * 默认的tinyscript实现
 * @author yancheng11334
 *
 */
public class DefaultComputeEngine extends DefaultScriptEngine implements ComputeEngine{
	
	private static ObjectItemProcessor listToListProcessor = new ListToListProcessor();
	private static ObjectItemProcessor dataSetItemProcessor = new DataSetItemProcessor();
	private static ObjectItemProcessor dataSetColumnItemProcessor = new DataSetColumnItemProcessor();
	private static ObjectItemProcessor dataSetRowItemProcessor = new DataSetRowItemProcessor();
	private static ObjectItemProcessor mapItemProcessor = new MapItemProcessor();
	
	private static AttributeProcessor excelAttributeProcessor = new ExcelAttributeProcessor();
	private static AttributeProcessor dataSetColumnAttributeProcessor = new DataSetColumnAttributeProcessor();
	
	private static AssignValueProcessor excelAssignValueProcessor = new ExcelAssignValueProcessor();
	
	private static Operator unionOperator = new UnionOperator();
	private static Operator subtractOperator = new SubtractOperator();
	private static Operator intersectionOperator = new IntersectionOperator();
	private static Operator xorOperator = new XorOperator();
	
	private static CustomProcessor jDBCNamedSqlProcessor = new JDBCNamedSqlProcessor();
	
	public DefaultComputeEngine() throws ScriptException {
		super();
		initComputeEngine();
	}
	
	public DefaultComputeEngine(Map<?,?> map) throws ScriptException {
		super(map);
		initComputeEngine();
	}

	private void initComputeEngine() throws ScriptException {
		// 设置属性
		setIndexFromOne(true);
		// 注册引擎到上下文
		ScriptContextUtil.setScriptEngine(getScriptContext(), this);

		// 注册函数
		addScriptFunction(new FillFunction());
		addScriptFunction(new IntersectionFunction());
		addScriptFunction(new UnionFunction());
		addScriptFunction(new SubtractFunction());
		addScriptFunction(new FilterFunction());
		addScriptFunction(new SortFunction());
		addScriptFunction(new CopyFunction());
		addScriptFunction(new XorFunction());
		addScriptFunction(new RemoveFunction());

		addScriptFunction(new MapUnionFunction());
		addScriptFunction(new MapIntersectionFunction());
		addScriptFunction(new MapSubtractFunction());
		addScriptFunction(new MapXorFunction());

		addScriptFunction(new SetUnionFunction());
		addScriptFunction(new SetIntersectionFunction());
		addScriptFunction(new SetSubtractFunction());
		addScriptFunction(new SetXorFunction());

		addScriptFunction(new CreateDataTreeFunction());
		addScriptFunction(new DataSetToTreeFunction());

		addScriptFunction(new ExecuteSqlFunction());
		addScriptFunction(new QuerySqlFunction());

		addScriptFunction(new CombinationFunction());
		addScriptFunction(new PermutationFunction());
		addScriptFunction(new AllPermutationFunction());

		addScriptFunction(new DataSetLimitFunction());
		addScriptFunction(new DataSetCopyFunction());
		addScriptFunction(new DataSetRemoveFunction());
		addScriptFunction(new DataSetFillFunction());
		addScriptFunction(new DataSetFilterFunction());
		addScriptFunction(new DataSetSortFunction());
		addScriptFunction(new DataSetGroupFunction());
		addScriptFunction(new DataSetJoinFunction());
		addScriptFunction(new DataSetSelectFunction());
		addScriptFunction(new DataSetMatchFunction());
		addScriptFunction(new DataSetUpdateFunction());
		addScriptFunction(new DataSetRenameFunction());
		addScriptFunction(new DataSetFieldFunction());
		addScriptFunction(new DataSetSubFunction());
		addScriptFunction(new DataSetConvertFunction());
		addScriptFunction(new DataSetForEachFunction());
		addScriptFunction(new DataSetReplaceFunction());
		addScriptFunction(new DataSetGroupStagedFunction());
		addScriptFunction(new DataSetGroupDynamicFunction());

		addScriptFunction(new GroupDataSetFilterFunction());
		addScriptFunction(new GroupDataSetSortFunction());

		addScriptFunction(new GroupDataSetAggregateFunction());
		addScriptFunction(new DataSetAggregateFunction());

		addScriptFunction(new CursorRowFunction());
		addScriptFunction(new FirstRowFunction());
		addScriptFunction(new LastRowFunction());
		addScriptFunction(new NextRowFunction());
		addScriptFunction(new PreviewRowFunction());

		addScriptFunction(new ReadExcelFunction());

		addScriptFunction(new ReadTxtFunction());

		// 注册处理器
		ObjectItemUtil.addObjectItemProcessor(listToListProcessor, 0);
		ObjectItemUtil.addObjectItemProcessor(dataSetItemProcessor);
		ObjectItemUtil.addObjectItemProcessor(dataSetColumnItemProcessor);
		ObjectItemUtil.addObjectItemProcessor(dataSetRowItemProcessor);
		ObjectItemUtil.addObjectItemProcessor(mapItemProcessor);

		AttributeUtil.addAttributeProcessor(excelAttributeProcessor, 0);
		AttributeUtil.addAttributeProcessor(dataSetColumnAttributeProcessor, 1);
		AssignValueUtil.addAssignValueProcessor(excelAssignValueProcessor);

		// 注册操作符处理器
		ExpressionUtil.addOperator(unionOperator);
		ExpressionUtil.addOperator(subtractOperator);
		ExpressionUtil.addOperator(intersectionOperator);
		ExpressionUtil.addOperator(xorOperator);
		
		//注册sql处理器
		CustomUtil.addCustomProcessor(jDBCNamedSqlProcessor);
	}
	
}
