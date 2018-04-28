package com.tuacy.fuzzysearchlibrary;

import java.util.List;

/**
 * 先匹配原始数据，再匹配模糊数据
 */
public interface IFuzzySearchItem {

	/**
	 * 获取item原始字符串
	 *
	 * @return 原始item字符串
	 */
	String getSourceKey();

	/**
	 * 获取item模糊字符串,item对应的拼音 江西省->["jiang", "xi", "sheng"]
	 *
	 * @return 模糊item字符串
	 */
	List<String> getFuzzyKey();

}
