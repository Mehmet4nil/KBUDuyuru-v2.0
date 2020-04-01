package com.example.ar.kbu_duyuru;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DuyuruAdapter extends BaseAdapter{
    private Context context;
    private List<DuyuruModel> rowItems;

    public DuyuruAdapter(Context context, List<DuyuruModel> items) {
        this.context = context;
        this.rowItems = items;
    }
    private class ViewHolder {
        TextView txtTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_duyuru, null);
            holder = new ViewHolder();
            holder.txtTitle = convertView.findViewById(R.id.txtDuyuru);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final DuyuruModel rowItem = (DuyuruModel) getItem(position);

        holder.txtTitle.setText(rowItem.getTitle());
        return convertView;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }


}
