package com.example.mailclone.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mailclone.R;

import java.util.ArrayList;
import java.util.List;

public class EmailLayoutAdapter extends BaseAdapter implements Filterable {

    List<Email> emailList, originalData;
    Context ctx;
    int count = 0;
    private final ItemFilter itemFilter = new ItemFilter();
    private final FavorFilter favorFilter = new FavorFilter();

    public EmailLayoutAdapter(List<Email> emailList, Context context) {
        this.emailList = emailList;
        this.originalData = emailList;
        this.ctx = context;
        count = 0;
    }

    @Override
    public int getCount() {
        return emailList.size();
    }

    @Override
    public Object getItem(int i) {
        return emailList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.a_mail_layout, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.avatar = view.findViewById(R.id.avatar);
            viewHolder.subject = view.findViewById(R.id.subject);
            viewHolder.time = view.findViewById(R.id.time);
            viewHolder.content = view.findViewById(R.id.content);
            viewHolder.email = view.findViewById(R.id.email);
            viewHolder.checkBox = view.findViewById(R.id.checkBox);
            view.setTag(viewHolder);
        } else viewHolder = (ViewHolder) view.getTag();
        Email item = emailList.get(i);
        viewHolder.email.setText(item.email);
        viewHolder.avatar.setText(item.avatar);
        viewHolder.subject.setText(item.subject);
        viewHolder.content.setText(item.content);
        viewHolder.time.setText(item.time);

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = item.checked;
                item.checked = !check;
                if (check)
                    viewHolder.checkBox.setBackgroundResource(R.drawable.grey_star_foreground);
                else viewHolder.checkBox.setBackgroundResource(R.drawable.star_foreground);
            }
        });

        return view;
    }

    @Override
    public Filter getFilter() {
        return itemFilter;
    }
    public Filter getFavor() {
        return favorFilter;
    }
    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Email> list = originalData;

            int count = list.size();
            final ArrayList<Email> nlist = new ArrayList<Email>(count);

            Email filterableString;

            for (int i = 0; i < count; i++) {
                filterableString = list.get(i);
                if (filterableString.email.toLowerCase().contains(filterString) || filterableString.subject.toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            emailList = (ArrayList<Email>) results.values;
            notifyDataSetChanged();
        }
    }

    private class FavorFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults results = new FilterResults();

                final List<Email> list = originalData;

                int count = list.size();
                final ArrayList<Email> nlist = new ArrayList<>(count);

                Email filterableString;

                for (int i = 0; i < count; i++) {
                    filterableString = list.get(i);
                    if (filterableString.checked) {
                        nlist.add(filterableString);
                    }
                }

                results.values = nlist;
                results.count = nlist.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            emailList = (ArrayList<Email>) results.values;
            notifyDataSetChanged();
        }
    }

    private class ViewHolder {
        TextView avatar;
        TextView subject;
        TextView content;
        TextView time;
        TextView email;
        ImageButton checkBox;
    }
};

