package com.appleframework.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.appleframework.model.page.Pagination;
import com.appleframework.model.utils.TypeCaseHelper;

/**
 * 数据传输对象(DateTransferObject)<br>
 * 建议在参数传递过程中尽量使用Dto来传递<br>
 * 
 * @author xusm
 * @since 2011-05-03
 * @see Dto
 * @see java.io.Serializable
 */
@XmlType(name = "Dto")
@XmlAccessorType(XmlAccessType.FIELD)
public class DefaultDto extends HashMap<String, Object> implements Dto,
		Serializable {

	private static final long serialVersionUID = 6654280697600536538L;

	public DefaultDto() {
	}

	public DefaultDto(String key, Object value) {
		put(key, value);
	}

	/**
	 * 以BigDecimal类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return BigDecimal 键值
	 */
	public BigDecimal getAsBigDecimal(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "BigDecimal", null);
		if (obj != null)
			return (BigDecimal) obj;
		else
			return null;
	}

	/**
	 * 以Date类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return Date 键值
	 */
	public Date getAsDate(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "Date", "yyyy-MM-dd");
		if (obj != null)
			return (Date) obj;
		else
			return null;
	}

	/**
	 * 以Integer类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return Integer 键值
	 */
	public Integer getAsInteger(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "Integer", null);
		if (obj != null)
			return (Integer) obj;
		else
			return null;
	}

	/**
	 * 以Long类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return Long 键值
	 */
	public Long getAsLong(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "Long", null);
		if (obj != null)
			return (Long) obj;
		else
			return null;
	}

	/**
	 * 以String类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return String 键值
	 */
	public String getAsString(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "String", null);
		if (obj != null)
			return (String) obj;
		else
			return "";
	}

	/**
	 * 以Timestamp类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return Timestamp 键值
	 */
	public Timestamp getAsTimestamp(String key) {
		Object obj = TypeCaseHelper.convert(get(key), "Timestamp",
				"yyyy-MM-dd HH:mm:ss");
		if (obj != null)
			return (Timestamp) obj;
		else
			return null;
	}

	/**
	 * 给Dto压入第一个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 * 
	 * @param pList
	 *            压入Dto的List对象
	 */
	public void setDefaultAList(List<?> pList) {
		put("defaultAList", pList);
	}

	/**
	 * 给Dto压入第二个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 * 
	 * @param pList
	 *            压入Dto的List对象
	 */
	public void setDefaultBList(List<?> pList) {
		put("defaultBList", pList);
	}

	/**
	 * 获取第一个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 * 
	 * @param pList
	 *            压入Dto的List对象
	 */
	public List<?> getDefaultAList() {
		return (List<?>) get("defaultAList");
	}

	/**
	 * 获取第二个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 * 
	 * @param pList
	 *            压入Dto的List对象
	 */
	public List<?> getDefaultBList() {
		return (List<?>) get("defaultBList");
	}

	/**
	 * 给Dto压入第一个默认PO对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 * 
	 * @param PO
	 *            压入Dto的PO对象
	 */
	public void setDefaultBo(Object po) {
		put("defaultBo", po);
	}

	/**
	 * 获取第一个默认PO对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 * 
	 * @param PO
	 *            压入Dto的PO对象
	 */
	public Object getDefaultBo() {
		return get("defaultPo");
	}

	/**
	 * 给Pagination压入第一个默认Pagination对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 * 
	 * @param Pagination
	 *            压入Dto的Pagination对象
	 */
	public void setDefaultPage(Pagination page) {
		put("defaultPage", page);
	}

	/**
	 * 获取第一个默认Pagination对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 * 
	 * @param Pagination
	 *            压入Pagination的PO对象
	 */
	public Pagination getDefaultPage() {
		return (Pagination) get("defaultPage");
	}

	@Override
	public void setDefaultOperater(Operater operater) {
		put("defaultOperater", operater);
	}

	@Override
	public Operater getDefaultOperater() {
		return (Operater) get("defaultOperater");
	}

	@Override
	public String getCode() {
		return (String) get("defaultCode");
	}

	@Override
	public void setCode(String code) {
		put("defaultCode", code);
	}

	@Override
	public void setDefaultIds(String ids) {
		put("defaultIds", ids);
	}

	@Override
	public String getDefaultIds() {
		return (String) get("defaultIds");
	}

	@Override
	public void setDefaultId(Object id) {
		put("defaultId", id);

	}

	@Override
	public Object getDefaultId() {
		return get("defaultId");
	}

}
