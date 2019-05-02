package com.rimon.restaurantsnearby.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.appcompat.widget.AppCompatTextView;

import com.rimon.restaurantsnearby.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter implements Filterable {
    private List<SuggestionModel> mData;
    private List<SuggestionModel> mSuggestions;
    private LayoutInflater mInflater;

    public SearchAdapter(Context mContext, List<SuggestionModel> mSuggestions) {
        this.mSuggestions = mSuggestions;
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public SuggestionModel getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int mPosition) {
        return mPosition;
    }

    @Override
    public View getView(int mPosition, View mConvertView, ViewGroup parent) {
        SuggestionsViewHolder mViewHolder;
        if (mConvertView == null) {
            mConvertView = mInflater.inflate(R.layout.item_suggest, parent, false);
            mViewHolder = new SuggestionsViewHolder(mConvertView);
            mConvertView.setTag(mViewHolder);
        } else {
            mViewHolder = (SuggestionsViewHolder) mConvertView.getTag();
        }
        final SuggestionModel item = getItem(mPosition);
        mViewHolder.mSuggestionTextView.setText(item.getTitle());
        return mConvertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                Filter.FilterResults filterResults = new Filter.FilterResults();
                if (!TextUtils.isEmpty(constraint)) {
                    final List<SuggestionModel> mSearchData = new ArrayList<>();
                    for (SuggestionModel marker : mSuggestions) {
                        final String title = marker.getTitle();
                        final String prefix = constraint.toString().toLowerCase();
                        if (title != null && title.toLowerCase().startsWith(prefix)) {
                            mSearchData.add(marker);
                        }
                    }
                    filterResults.values = mSearchData;
                    filterResults.count = mSearchData.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.values != null) {
                    mData = (ArrayList<SuggestionModel>) results.values;
                    notifyDataSetChanged();
                }
            }
        };
    }

    private class SuggestionsViewHolder {
        AppCompatTextView mSuggestionTextView;

        SuggestionsViewHolder(View convertView) {
            mSuggestionTextView = convertView.findViewById(R.id.suggestion_text);
        }
    }
}
