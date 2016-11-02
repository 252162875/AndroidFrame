package xy.com.mysoul.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xy.com.mysoul.R;
import xy.com.mysoul.utils.UiUtils;

public class TestAdapter extends RecyclerView.Adapter {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = new TextView(UiUtils.getContext());
        tv.setText(this.getClass().getSimpleName());
        tv.setTextColor(UiUtils.getColor(R.color.pinkline));
        return new MyViewHolder(tv);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder) holder).tv.setText("位置是：" + position);
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
}