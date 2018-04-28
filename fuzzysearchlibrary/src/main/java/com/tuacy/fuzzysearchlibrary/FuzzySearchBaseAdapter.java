package com.tuacy.fuzzysearchlibrary;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Filter;
import android.widget.Filterable;

import java.util.ArrayList;
import java.util.List;

public abstract class FuzzySearchBaseAdapter<ITEM extends IFuzzySearchItem, VH extends RecyclerView.ViewHolder>
	extends RecyclerView.Adapter<VH> implements Filterable {

	private   FuzzySearchFilter mFilter;
	private   List<ITEM>        mBackDataList;
	protected List<ITEM>        mDataList;
	private   IFuzzySearchRule  mIFuzzySearchRule;

	public FuzzySearchBaseAdapter(IFuzzySearchRule rule) {
		this(rule, null);
	}

	public FuzzySearchBaseAdapter(IFuzzySearchRule rule, List<ITEM> dataList) {
		if (rule == null) {
			mIFuzzySearchRule = new DefaultFuzzySearchRule();
		}
		mBackDataList = dataList;
		mDataList = dataList;
	}

	public void setDataList(List<ITEM> dataList) {
		mBackDataList = dataList;
		mDataList = dataList;
	}


	@Override
	public int getItemCount() {
		return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public Filter getFilter() {
		if (mFilter == null) {
			mFilter = new FuzzySearchFilter();
		}
		return mFilter;
	}

	private class FuzzySearchFilter extends Filter {

		/**
		 * 执行过滤操作,如果搜索的关键字为空，默认所有结果
		 */
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults result = new FilterResults();
			List<ITEM> filterList;
			if (TextUtils.isEmpty(constraint)) {
				filterList = mBackDataList;
			} else {
				filterList = new ArrayList<>();
				for (ITEM item : mBackDataList) {
					if (mIFuzzySearchRule.accept(constraint, item.getSourceKey(), item.getFuzzyKey())) {
						filterList.add(item);
					}
				}
			}
			result.values = filterList;
			result.count = filterList.size();
			return result;
		}

		/**
		 * 得到过滤结果
		 */
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			mDataList = (List<ITEM>) results.values;
			notifyDataSetChanged();
		}
	}

}
