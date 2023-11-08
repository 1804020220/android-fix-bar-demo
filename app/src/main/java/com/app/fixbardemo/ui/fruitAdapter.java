package com.app.fixbardemo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fixbardemo.R;

import java.util.List;

public class fruitAdapter extends RecyclerView.Adapter<fruitAdapter.fruitViewHolder> {

    private Context context;
    private List<itemBean> fruitList;

    public fruitAdapter(Context context, List<itemBean> fruitList) {
        this.context = context;
        this.fruitList = fruitList;
    }

    /**
     * 判断当前position上是否是头部
     * @param position 每个item的位置
     * @return 头部 == true
     */
    public boolean isGroupHeader(int position){
        if (position == 0) {
            return true; //第一个位置肯定是头部
        }else {
            String currentGroupName = getGroupName(position);
            String preGroupName = getGroupName(position - 1);
            if (currentGroupName.equals(preGroupName)) {
                return false; // currentGroupName与前一个item是同一个组的，返回false
            }else {
                return true;
            }
        }
    }

    /**
     * 获得当前item的fruitGroup
     * @param position 每个item的位置
     * @return 当前的item是属于那个fruitGroup
     */
    public String getGroupName(int position){
        return fruitList.get(position).getFruitGroup();
    }

    @NonNull
    @Override
    public fruitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.main_item, null);

        return new fruitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull fruitViewHolder holder, int position) {
        holder.tv.setText(fruitList.get(position).getFruitName());
    }

    @Override
    public int getItemCount() {
        return fruitList == null ? 0 : fruitList.size();
    }

    public class fruitViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public fruitViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.main_item);
        }
    }
}
