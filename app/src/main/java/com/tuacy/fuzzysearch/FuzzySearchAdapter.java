package com.tuacy.fuzzysearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuacy.fuzzysearchlibrary.FuzzySearchBaseAdapter;
import com.tuacy.fuzzysearchlibrary.IFuzzySearchRule;

import java.util.List;

public class FuzzySearchAdapter extends FuzzySearchBaseAdapter<ItemEntity, FuzzySearchAdapter.ItemHolder> {

	public FuzzySearchAdapter() {
		super(null);
	}

	public FuzzySearchAdapter(IFuzzySearchRule rule) {
		super(rule);
	}

	public FuzzySearchAdapter(List<ItemEntity> dataList) {
		super(null, dataList);
	}

	public FuzzySearchAdapter(IFuzzySearchRule rule, List<ItemEntity> dataList) {
		super(rule, dataList);
	}

	@NonNull
	@Override
	public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new FuzzySearchAdapter.ItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adapter, parent, false));
	}

	@Override
	public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
		holder.mTextName.setText(mDataList.get(position).getValue());
	}

	static class ItemHolder extends RecyclerView.ViewHolder {

		TextView mTextName;

		ItemHolder(View itemView) {
			super(itemView);
			mTextName = itemView.findViewById(R.id.text_item_name);
		}
	}

}
