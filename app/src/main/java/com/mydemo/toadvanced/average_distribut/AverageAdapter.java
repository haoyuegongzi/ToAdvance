package com.mydemo.toadvanced.average_distribut;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mydemo.toadvanced.R;

import java.util.List;

/**
 * @创建者
 * @TODO:
 */
public class AverageAdapter extends RecyclerView.Adapter<AverageAdapter.ViewHolder> {
    Context context;
    LayoutInflater inflater;
    List<String> list;

    public AverageAdapter(Context mContext, List<String> dataList){
        context = mContext;
        list = dataList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.adapter_average, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvItem.setText(list.get(position));
        holder.vDivider.setVisibility((position == 0) ? View.GONE : View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return null == list ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvItem;
        View vDivider;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.tvItem);
            vDivider = itemView.findViewById(R.id.vDivider);
        }
    }
}
