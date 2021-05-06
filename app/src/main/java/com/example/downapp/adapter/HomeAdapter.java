package com.example.downapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.downapp.R;
import com.example.downapp.entity.RegisterType;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<RegisterType> listData;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final RegisterType registerType = listData.get(position);
        holder.lady_tv.setText(registerType.getTypeName());

        switch (registerType.getTypeId()){
            case "001":
                holder.lady_iv.setImageResource(R.drawable.library);
                break;
            case "091":
                holder.lady_iv.setImageResource(R.drawable.foodroom);
                break;
            case "002":
                holder.lady_iv.setImageResource(R.drawable.athletic_field);
                break;
            case "003":
                holder.lady_iv.setImageResource(R.drawable.rowing);
                break;
            case "004":
                holder.lady_iv.setImageResource(R.drawable.tableball);
                break;
            case "005":
                holder.lady_iv.setImageResource(R.drawable.volleyball);
                break;
            case "006":
                holder.lady_iv.setImageResource(R.drawable.pingpang);
                break;
            case "012":
                holder.lady_iv.setImageResource(R.drawable.badminton);
                break;
            case "031":
                holder.lady_iv.setImageResource(R.drawable.football);
                break;
            case "121":
                holder.lady_iv.setImageResource(R.drawable.classroom);
                break;
            case "520":
                holder.lady_iv.setImageResource(R.drawable.myself);
                break;
            case "051":
                holder.lady_iv.setImageResource(R.drawable.basketball);
                break;
            default:
                holder.lady_iv.setImageResource(R.drawable.music_item1);
                break;
        }


        holder.lady_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen.myClick(registerType.getTypeId(),registerType.getTypeName());
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("getItemCount");
        return listData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView lady_iv;
        TextView lady_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lady_iv = itemView.findViewById(R.id.lady_iv);
            lady_tv = itemView.findViewById(R.id.lady_tv);
        }
    }
//      需要添加实体类
    public HomeAdapter(List<RegisterType> list){
        listData = list;
    }

    //添加事件监听
    private ListenOnClick listen;
    public void setOnMyClick(HomeAdapter.ListenOnClick listenOnClick){
        this.listen = listenOnClick;
    }
    public interface ListenOnClick{
        void myClick(String typeId,String typeName);
    }

}
