package org.tinygroup.tinyscript.expression;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.tinygroup.tinyscript.ScriptContext;
import org.tinygroup.tinyscript.ScriptException;
import org.tinygroup.tinyscript.expression.booleanconvert.ArrayBooleanConverter;
import org.tinygroup.tinyscript.expression.booleanconvert.CollectionBooleanConverter;
import org.tinygroup.tinyscript.expression.booleanconvert.EnumeratorBooleanConverter;
import org.tinygroup.tinyscript.expression.booleanconvert.IteratorBooleanConverter;
import org.tinygroup.tinyscript.expression.booleanconvert.MapBooleanConverter;
import org.tinygroup.tinyscript.expression.booleanconvert.StringBooleanConverter;
import org.tinygroup.tinyscript.expression.calculator.AvgCalculator;
import org.tinygroup.tinyscript.expression.calculator.CountAllCalculator;
import org.tinygroup.tinyscript.expression.calculator.CountCalculator;
import org.tinygroup.tinyscript.expression.calculator.DistinctCalculator;
import org.tinygroup.tinyscript.expression.calculator.MaxCalculator;
import org.tinygroup.tinyscript.expression.calculator.MedianCalculator;
import org.tinygroup.tinyscript.expression.calculator.MinCalculator;
import org.tinygroup.tinyscript.expression.calculator.PercentileCalculator;
import org.tinygroup.tinyscript.expression.calculator.RangeCalculator;
import org.tinygroup.tinyscript.expression.calculator.SampleStdevpCalculator;
import org.tinygroup.tinyscript.expression.calculator.SampleVarianceCalculator;
import org.tinygroup.tinyscript.expression.calculator.StandardDeviationCalculator;
import org.tinygroup.tinyscript.expression.calculator.SumCalculator;
import org.tinygroup.tinyscript.expression.calculator.VarianceCalculator;
import org.tinygroup.tinyscript.expression.convert.ByteBigDecimal;
import org.tinygroup.tinyscript.expression.convert.ByteCharacter;
import org.tinygroup.tinyscript.expression.convert.ByteDouble;
import org.tinygroup.tinyscript.expression.convert.ByteFloat;
import org.tinygroup.tinyscript.expression.convert.ByteInteger;
import org.tinygroup.tinyscript.expression.convert.CharacterBigDecimal;
import org.tinygroup.tinyscript.expression.convert.CharacterDouble;
import org.tinygroup.tinyscript.expression.convert.CharacterFloat;
import org.tinygroup.tinyscript.expression.convert.CharacterInteger;
import org.tinygroup.tinyscript.expression.convert.DoubleBigDecimal;
import org.tinygroup.tinyscript.expression.convert.DoubleFloat;
import org.tinygroup.tinyscript.expression.convert.DoubleInteger;
import org.tinygroup.tinyscript.expression.convert.DoubleLong;
import org.tinygroup.tinyscript.expression.convert.FloatBigDecimal;
import org.tinygroup.tinyscript.expression.convert.FloatDouble;
import org.tinygroup.tinyscript.expression.convert.FloatInteger;
import org.tinygroup.tinyscript.expression.convert.FloatLong;
import org.tinygroup.tinyscript.expression.convert.IntegerBigDecimal;
import org.tinygroup.tinyscript.expression.convert.IntegerDouble;
import org.tinygroup.tinyscript.expression.convert.IntegerFloat;
import org.tinygroup.tinyscript.expression.convert.IntegerLong;
import org.tinygroup.tinyscript.expression.convert.LongBigDecimal;
import org.tinygroup.tinyscript.expression.convert.LongDouble;
import org.tinygroup.tinyscript.expression.convert.LongFloat;
import org.tinygroup.tinyscript.expression.convert.LongInteger;
import org.tinygroup.tinyscript.expression.convert.StringBigDecimal;
import org.tinygroup.tinyscript.expression.convert.StringDouble;
import org.tinygroup.tinyscript.expression.convert.StringFloat;
import org.tinygroup.tinyscript.expression.convert.StringInteger;
import org.tinygroup.tinyscript.expression.convert.StringLong;
import org.tinygroup.tinyscript.expression.in.ArrayInProcessor;
import org.tinygroup.tinyscript.expression.in.CollectionInProcessor;
import org.tinygroup.tinyscript.expression.in.MapInProcessor;
import org.tinygroup.tinyscript.expression.iteratorconvert.ArrayIteratorConverter;
import org.tinygroup.tinyscript.expression.iteratorconvert.CollectionIteratorConverter;
import org.tinygroup.tinyscript.expression.iteratorconvert.MapIteratorConverter;
import org.tinygroup.tinyscript.expression.iteratorconvert.StringIteratorConverter;
import org.tinygroup.tinyscript.expression.operator.AdOperator;
import org.tinygroup.tinyscript.expression.operator.AddOperator;
import org.tinygroup.tinyscript.expression.operator.AndLogicOperator;
import org.tinygroup.tinyscript.expression.operator.BigEqualsOperator;
import org.tinygroup.tinyscript.expression.operator.BigOperator;
import org.tinygroup.tinyscript.expression.operator.ComplementOperator;
import org.tinygroup.tinyscript.expression.operator.ComplexOperator;
import org.tinygroup.tinyscript.expression.operator.DevideOperator;
import org.tinygroup.tinyscript.expression.operator.EqualsOperator;
import org.tinygroup.tinyscript.expression.operator.LeftAddOperator;
import org.tinygroup.tinyscript.expression.operator.LeftLiteralOperator;
import org.tinygroup.tinyscript.expression.operator.LeftNotOperator;
import org.tinygroup.tinyscript.expression.operator.LeftPlusPlusOperator;
import org.tinygroup.tinyscript.expression.operator.LeftSubtractOperator;
import org.tinygroup.tinyscript.expression.operator.LeftSubtractSubtractOperator;
import org.tinygroup.tinyscript.expression.operator.LessEqualsOperator;
import org.tinygroup.tinyscript.expression.operator.LessOperator;
import org.tinygroup.tinyscript.expression.operator.ModOperator;
import org.tinygroup.tinyscript.expression.operator.MultiplyOperator;
import org.tinygroup.tinyscript.expression.operator.NotEqualsOperator;
import org.tinygroup.tinyscript.expression.operator.OrLogicOperator;
import org.tinygroup.tinyscript.expression.operator.OrOperator;
import org.tinygroup.tinyscript.expression.operator.RightPlusPlusOperator;
import org.tinygroup.tinyscript.expression.operator.RightSubtractSubtractOperator;
import org.tinygroup.tinyscript.expression.operator.ShlOperator;
import org.tinygroup.tinyscript.expression.operator.Shr2Operator;
import org.tinygroup.tinyscript.expression.operator.ShrOperator;
import org.tinygroup.tinyscript.expression.operator.SimpleConditionOperator;
import org.tinygroup.tinyscript.expression.operator.SubtractOperator;
import org.tinygroup.tinyscript.expression.operator.XorOperator;
import org.tinygroup.tinyscript.expression.range.CharRangeOperator;
import org.tinygroup.tinyscript.expression.range.IntegerRangeOperator;
import org.tinygroup.tinyscript.expression.range.LongRangeOperator;
import org.tinygroup.tinyscript.expression.range.NumberRangeOperator;
import org.tinygroup.tinyscript.interpret.ResourceBundleUtil;

/**
 * 表达式工具类
 * 
 * @author yancheng11334
 *
 */
@SuppressWarnings("rawtypes")
public final class ExpressionUtil {

	private ExpressionUtil() {

	}

	private static Map<String, ComplexOperator> operationMap = new HashMap<String, ComplexOperator>();
	private static Map<String, OperatorWithContext> operationWithContextMap = new HashMap<String, OperatorWithContext>();

	private static Converter[][] converters = new Converter[8][8];
	private static Map<Class, Integer> typeMap = new HashMap<Class, Integer>();

	private static List<BooleanConverter> booleanConverters = new ArrayList<BooleanConverter>();
	private static List<IteratorConverter> iteratorConverters = new ArrayList<IteratorConverter>();
	private static Map<String, NumberCalculator> numberCalculatorMap = new HashMap<String, NumberCalculator>();
	private static List<RangeOperator> rangeOperators = new ArrayList<RangeOperator>();

	private static List<InExpressionProcessor> inProcessors = new ArrayList<InExpressionProcessor>();
	static {
		typeMap.put(Byte.class, 0);
		typeMap.put(Character.class, 1);
		typeMap.put(Integer.class, 2);
		typeMap.put(Long.class, 3);
		typeMap.put(Float.class, 4);
		typeMap.put(Double.class, 5);
		typeMap.put(BigDecimal.class, 6);
		typeMap.put(String.class, 7);

		addConverter(new ByteCharacter());
		addConverter(new ByteInteger());
		addConverter(new ByteFloat());
		addConverter(new ByteDouble());
		addConverter(new ByteBigDecimal());

		addConverter(new CharacterInteger());
		addConverter(new CharacterFloat());
		addConverter(new CharacterDouble());
		addConverter(new CharacterBigDecimal());

		addConverter(new IntegerLong());
		addConverter(new IntegerFloat());
		addConverter(new IntegerDouble());
		addConverter(new IntegerBigDecimal());

		addConverter(new LongFloat());
		addConverter(new LongDouble());
		addConverter(new LongInteger());
		addConverter(new LongBigDecimal());

		addConverter(new FloatDouble());
		addConverter(new FloatInteger());
		addConverter(new FloatLong());
		addConverter(new FloatBigDecimal());

		addConverter(new DoubleBigDecimal());
		addConverter(new DoubleInteger());
		addConverter(new DoubleLong());
		addConverter(new DoubleFloat());

		addConverter(new StringBigDecimal());
		addConverter(new StringInteger());
		addConverter(new StringLong());
		addConverter(new StringFloat());
		addConverter(new StringDouble());

		// 数学操作
		addOperator(new AddOperator());
		addOperator(new SubtractOperator());
		addOperator(new MultiplyOperator());
		addOperator(new DevideOperator());
		addOperator(new XorOperator());
		addOperator(new AdOperator());
		addOperator(new OrOperator());
		addOperator(new ModOperator());
		// 简化三元操作符
		addOperator(new SimpleConditionOperator());
		// 一元操作符
		addOperator(new LeftAddOperator());
		addOperator(new LeftSubtractOperator());
		addOperator(new LeftPlusPlusOperator());
		addOperator(new LeftSubtractSubtractOperator());
		addOperator(new RightPlusPlusOperator());
		addOperator(new RightSubtractSubtractOperator());
		addOperator(new LeftLiteralOperator());
		addOperator(new ComplementOperator());
		addOperator(new LeftNotOperator());

		// 逻辑比较符
		addOperator(new AndLogicOperator());
		addOperator(new OrLogicOperator());
		addOperator(new EqualsOperator());
		addOperator(new NotEqualsOperator());
		addOperator(new LessEqualsOperator());
		addOperator(new LessOperator());
		addOperator(new BigOperator());
		addOperator(new BigEqualsOperator());
		// 移位运算符
		addOperator(new ShlOperator());
		addOperator(new ShrOperator());
		addOperator(new Shr2Operator());

		// 添加boolean表达式转换器
		addBooleanConverter(new StringBooleanConverter());
		addBooleanConverter(new CollectionBooleanConverter());
		addBooleanConverter(new ArrayBooleanConverter());
		addBooleanConverter(new IteratorBooleanConverter());
		addBooleanConverter(new EnumeratorBooleanConverter());
		addBooleanConverter(new MapBooleanConverter());

		// 添加Iterator转换器
		addIteratorConverter(new StringIteratorConverter());
		addIteratorConverter(new CollectionIteratorConverter());
		addIteratorConverter(new MapIteratorConverter());
		addIteratorConverter(new ArrayIteratorConverter());

		// 添加计算器
		addNumberCalculator(new AvgCalculator());
		addNumberCalculator(new MaxCalculator());
		addNumberCalculator(new MinCalculator());
		addNumberCalculator(new MedianCalculator());
		addNumberCalculator(new RangeCalculator());
		addNumberCalculator(new StandardDeviationCalculator());
		addNumberCalculator(new SumCalculator());
		addNumberCalculator(new VarianceCalculator());
		addNumberCalculator(new CountCalculator());
		addNumberCalculator(new DistinctCalculator());
		addNumberCalculator(new SampleVarianceCalculator());
		addNumberCalculator(new SampleStdevpCalculator());
		addNumberCalculator(new PercentileCalculator());
		addNumberCalculator(new CountAllCalculator());

		// 添加range处理器
		addRangeOperator(new IntegerRangeOperator());
		addRangeOperator(new LongRangeOperator());
		addRangeOperator(new NumberRangeOperator());
		addRangeOperator(new CharRangeOperator());
		
		// 添加in表达式处理器
		addInProcessor(new ArrayInProcessor());
		addInProcessor(new CollectionInProcessor());
		addInProcessor(new MapInProcessor());
	}

	@SuppressWarnings("unchecked")
	public static Object convert(Object object, Class<?> sourceType, Class<?> destType) {
		return converters[typeMap.get(sourceType)][typeMap.get(destType)].convert(object);
	}

	public static void addInProcessor(InExpressionProcessor inProcessor) {
		inProcessors.add(inProcessor);
	}

	public static Object convert(Object value, Class<?> destType) throws ScriptException {
		try {
			if (value == null || value.getClass() == destType) {
				return value;
			}
			return convert(value, value.getClass(), destType);
		} catch (Exception e) {
			throw new ScriptException(ResourceBundleUtil.getDefaultMessage("number.convert.error",
					value.getClass().getName(), destType.getName()), e);
		}
	}

	public static Double convertDouble(Object value) throws ScriptException {
		return (Double) convert(value, Double.class);
	}

	public static Long convertLong(Object value) throws ScriptException {
		return (Long) convert(value, Long.class);
	}

	public static Float convertFloat(Object value) throws ScriptException {
		return (Float) convert(value, Float.class);
	}

	public static Integer convertInteger(Object value) throws ScriptException {
		return (Integer) convert(value, Integer.class);
	}

	public static int compare(Class<?> type1, Class<?> type2) {
		int index1 = typeMap.get(type1);
		int index2 = typeMap.get(type2);
		if (index1 == index2) {
			return 0;
		}
		if (index1 > index2) {
			return 1;
		} else {
			return -1;
		}
	}

	public static boolean isNumber(Class<?> type) {
		return typeMap.containsKey(type) && type != String.class;
	}

	public static void addConverter(Converter converter) {
		converters[typeMap.get(converter.getSourceType())][typeMap.get(converter.getDestType())] = converter;
	}

	public static void addOperator(Operator operator) {
		ComplexOperator complexOperator = operationMap.get(operator.getOperation());
		if (complexOperator != null) {
			complexOperator.addOperator(operator);
		} else {
			complexOperator = new ComplexOperator(operator);
			operationMap.put(complexOperator.getOperation(), complexOperator);
		}

	}

	public static void addOperator(OperatorWithContext operator) {
		operationWithContextMap.put(operator.getOperation(), operator);
	}

	public static void addBooleanConverter(BooleanConverter converter) {
		for (BooleanConverter oldBooleanConverter : booleanConverters) {
			if (oldBooleanConverter.equals(converter) || oldBooleanConverter.getClass().isInstance(converter)) {
				return;
			}
		}
		booleanConverters.add(converter);
	}

	public static void addIteratorConverter(IteratorConverter converter) {
		for (IteratorConverter oldIteratorConverter : iteratorConverters) {
			if (oldIteratorConverter.equals(converter) || oldIteratorConverter.getClass().isInstance(converter)) {
				return;
			}
		}
		iteratorConverters.add(converter);
	}

	public static Object executeOperation(String op, Object... parameters) throws ScriptException {
		Operator operator = operationMap.get(op);
		if (operator == null) {
			throw new ScriptException(ResourceBundleUtil.getDefaultMessage("op.notfound.error", op));
		}
		return operator.operation(parameters);
	}

	public static Object executeOperationWithContext(ScriptContext context, String op, String name, Object value)
			throws ScriptException {
		OperatorWithContext operator = operationWithContextMap.get(op);
		if (operator == null) {
			throw new ScriptException(ResourceBundleUtil.getDefaultMessage("op.notfound.error", op));
		}
		return operator.operation(context, name, value);
	}

	/**
	 * 判断布尔值是否成立
	 *
	 * @param object
	 * @return
	 */
	public static boolean getBooleanValue(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof Boolean) {
			return (Boolean) object;
		}
		for (BooleanConverter converter : booleanConverters) {
			if (converter.isMatch(object)) {
				return converter.convert(object);
			}
		}
		return true;
	}

	/**
	 * 得到Iterator
	 * 
	 * @param object
	 * @return
	 */
	public static Iterator getIterator(Object object) {
		if (object != null) {
			if (object instanceof Iterator) {
				return (Iterator) object;
			}
			for (IteratorConverter converter : iteratorConverters) {
				if (converter.isMatch(object)) {
					return converter.convert(object);
				}
			}
		}
		return null;
	}

	@SuppressWarnings({ "unchecked" })
	public static int compareNumber(Comparable c1, Comparable c2) {
		Class t1 = c1.getClass();
		Class t2 = c2.getClass();

		if (!t1.equals(t2)) {
			Comparable leftObject = c1;
			Comparable rightObject = c2;
			if (ExpressionUtil.compare(t1, t2) > 0) {
				rightObject = (Comparable) ExpressionUtil.convert(rightObject, t2, t1);
			} else {
				leftObject = (Comparable) ExpressionUtil.convert(leftObject, t1, t2);
			}
			return leftObject.compareTo(rightObject);
		}
		return c1.compareTo(c2);
	}

	public static void addNumberCalculator(NumberCalculator numberCalculator) {
		numberCalculatorMap.put(numberCalculator.getName(), numberCalculator);
	}

	/**
	 * 查询计算器
	 * 
	 * @param name
	 * @return
	 */
	public static NumberCalculator getNumberCalculator(String name) {
		return numberCalculatorMap.get(name);
	}

	/**
	 * 获得计算器名称列表
	 * 
	 * @return
	 */
	public static List<String> getCalculatorNames() {
		return new ArrayList<String>(numberCalculatorMap.keySet());
	}

	/**
	 * 执行计算
	 * 
	 * @param name
	 * @param numbers
	 * @return
	 * @throws ScriptException
	 */
	public static Object compute(String name, List<Object> numbers, Object... params) throws ScriptException {
		NumberCalculator numberCalculator = numberCalculatorMap.get(name);
		if (numberCalculator == null) {
			throw new ScriptException(ResourceBundleUtil.getDefaultMessage("calculator.notfound.error", name));
		}
		if (numbers == null || numbers.isEmpty()) {
			return numberCalculator.getEmptyValue();
		}
		return numberCalculator.compute(numbers, params);
	}

	/**
	 * 判断序列是否包含集合元素
	 * 
	 * @param numbers
	 * @return
	 */
	public static boolean containsCollection(List<Object> numbers) {
		for (Object number : numbers) {
			if (isCollection(number)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否集合元素
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isCollection(Object object) {
		if (object.getClass().isArray()) {
			return true;
		} else if (object instanceof Collection) {
			return true;
		} else if (object instanceof Map) {
			return true;
		}
		return false;
	}

	/**
	 * 转换集合元素为序列
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static List<Object> convertCollection(Object object) {
		List<Object> parameterList = new ArrayList<Object>();
		if (object.getClass().isArray()) {
			// 数组
			int length = Array.getLength(object);
			for (int i = 0; i < length; i++) {
				Object v = Array.get(object, i);
				if (v != null) {
					parameterList.add(v);
				}
			}
		} else if (object instanceof Collection) {
			// 集合
			parameterList.addAll((Collection) object);
		} else if (object instanceof Map) {
			// Map
			Map map = (Map) object;
			parameterList.addAll(map.values());
		}
		return parameterList;
	}

	/**
	 * 拆分序列
	 * 
	 * @param numbers
	 * @return
	 */
	public static List<List<Object>> splitCollection(List<Object> numbers) {
		List<List<Object>> result = new ArrayList<List<Object>>();
		List<Object> tempList = null;
		for (Object number : numbers) {
			if (isCollection(number)) {
				if (tempList != null) {
					result.add(tempList);
					tempList = null;
				}
				result.add(convertCollection(number));
			} else {
				if (tempList == null) {
					tempList = new ArrayList<Object>();
				}
				tempList.add(number);
			}
		}
		if (tempList != null) {
			result.add(tempList);
		}
		return result;
	}

	public static void addRangeOperator(RangeOperator operator) {
		for (RangeOperator rangeOperator : rangeOperators) {
			if (rangeOperator.equals(operator) || rangeOperator.getClass().isInstance(operator)) {
				return;
			}
		}
		rangeOperators.add(operator);
	}

	public static List<Object> createRange(Object start, Object end) throws ScriptException {
		if (start == null || end == null) {
			throw new ScriptException(ResourceBundleUtil.getDefaultMessage("range.empty.error"));
		}
		for (RangeOperator rangeOperator : rangeOperators) {
			if (rangeOperator.isMatch(start, end)) {
				return rangeOperator.createRange(start, end);
			}
		}
		return null;
	}

	/**
	 * 判断是否包含某元素
	 * 
	 * @param collection
	 * @param item
	 * @return
	 * @throws Exception 
	 */
	public static boolean in(Object collection, Object item) throws Exception {
		for(InExpressionProcessor inProcessor : inProcessors) {
			if(inProcessor.isMatch(collection)) {
				return inProcessor.checkIn(collection, item);
			}
		}
		return false;
	}
}
