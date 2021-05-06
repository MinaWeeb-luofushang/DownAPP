package com.example.downapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.downapp.R;
import com.example.downapp.entity.OrderEntity;

import java.util.List;

public class OrderAdapter extends MyBaseAdapter<OrderEntity> {


    public OrderAdapter(Context c, List<OrderEntity> datas) {
        super(c, datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        EntityOrder entityOrder = null;
        if (entityOrder == null){
            entityOrder = new EntityOrder();
            view = layoutInflater.inflate(R.layout.listview_order,null);
            entityOrder.tv_order_class = view.findViewById(R.id.tv_order_class);
            entityOrder.tv_order_state = view.findViewById(R.id.tv_order_state);
            entityOrder.tv_order_time = view.findViewById(R.id.tv_order_time);
            entityOrder.tv_order_where = view.findViewById(R.id.tv_order_where);
            entityOrder.btn_cancel_order = view.findViewById(R.id.btn_cancel_order);
            entityOrder.tv_order_update = view.findViewById(R.id.tv_order_update);
        }else {
            view.clearAnimation();
            entityOrder = (EntityOrder) view.getTag();
        }
//        System.out.println("test:"+datas.get(1).ClassNames);
        entityOrder.tv_order_class.setText(datas.get(i).OrderConClassName);

        if (datas.get(i).OrderState==1){
            entityOrder.btn_cancel_order.setText("预约成功");
            entityOrder.btn_cancel_order.setEnabled(false);
            entityOrder.tv_order_update.setVisibility(View.GONE);

            entityOrder.tv_order_state.setText("已成功");
            entityOrder.tv_order_state.setBackgroundResource(R.color.colorOrderSuccess);
        }else if (datas.get(i).OrderState==-1){
            entityOrder.btn_cancel_order.setText("过期删除");
            entityOrder.btn_cancel_order.setEnabled(true);
            entityOrder.tv_order_update.setVisibility(View.GONE);

            entityOrder.tv_order_state.setText("已失效");
            entityOrder.tv_order_state.setBackgroundResource(R.color.colorOrderFailure);
        }else{
            entityOrder.tv_order_state.setBackgroundResource(R.color.colorOrderWait);
            entityOrder.tv_order_state.setText("排队中");
        }

        lisTenClick(entityOrder.btn_cancel_order,datas.get(i));
        lisTenClick(entityOrder.tv_order_update,datas.get(i));

        entityOrder.tv_order_time.setText(datas.get(i).OrderConTime);
        entityOrder.tv_order_where.setText(datas.get(i).OrderConWhere);
        return view;

    }
    private class EntityOrder{
        private TextView tv_order_class,tv_order_time,tv_order_where,tv_order_state,tv_order_update;
        private Button btn_cancel_order;
    }

    private void lisTenClick(View view, final OrderEntity orderEntity){
        switch (view.getId()){
            case R.id.btn_cancel_order:
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("这里是右边按钮的点击");
                        listenCancelBtn.CancelClick(orderEntity);
                    }
                });
                break;
            case R.id.tv_order_update:
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("这里是修改的点击");
                        listenUpdateTv.UpdateClick(orderEntity);
                    }
                });
                break;
        }
    }

    private ListenCancelBtn listenCancelBtn;
    public void setListenCancelBtn(OrderAdapter.ListenCancelBtn CancelBtn){
        this.listenCancelBtn = CancelBtn;
    }

    private ListenUpdateTv listenUpdateTv;
    public void setListenUpdateTv(OrderAdapter.ListenUpdateTv UpdateTv){
        this.listenUpdateTv = UpdateTv;
    }
    //设置callback 监听
    public interface ListenCancelBtn{
        void CancelClick(OrderEntity orderEntity);
    }
    public interface ListenUpdateTv{
        void UpdateClick(OrderEntity orderEntity);
    }

}
