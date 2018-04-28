package com.tuacy.fuzzysearchlibrary;

import android.text.TextUtils;

import java.util.List;

public class DefaultFuzzySearchRule implements IFuzzySearchRule {

	@Override
	public boolean accept(CharSequence constraint, String itemSource, List<String> itemPinYinList) {
		/**
		 * 1. 先匹配原始的字符，比如 原始字符是 "中国"  输入 "中" 也能保证匹配到
		 */
		if ((itemSource != null && itemSource.toLowerCase().contains(constraint.toString().toLowerCase()))) {
			return true;
		}
		/**
		 * 2. 拼音匹配 这里咱们匹配每个拼音的首字母
		 */
		if (itemPinYinList != null && !itemPinYinList.isEmpty()) {
			StringBuilder firstWord = null;
			for (String wordPinYin : itemPinYinList) {
				if (!TextUtils.isEmpty(wordPinYin)) {
					if (firstWord == null) {
						firstWord = new StringBuilder(wordPinYin.substring(0, 1));
					} else {
						firstWord.append(wordPinYin.substring(0, 1));
					}
				}
			}
			return firstWord != null && firstWord.toString().toLowerCase().contains(constraint.toString().toLowerCase());
		}
		return false;
	}
}
