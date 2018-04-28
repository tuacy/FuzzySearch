package com.tuacy.fuzzysearchlibrary;

import java.util.List;

/**
 * 模糊搜索匹配规则接口
 */
public interface IFuzzySearchRule {

	/**
	 * 匹配规则
	 *
	 * @param constraint     匹配字符
	 * @param itemSource     item 对应的原始字符
	 * @param itemPinYinList item 原始字符对应的拼音列表
	 * @return 是否匹配
	 */
	boolean accept(CharSequence constraint, String itemSource, List<String> itemPinYinList);

}
