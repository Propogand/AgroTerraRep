package kltuk.com.agroterra.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kltuk.com.agroterra.R;
import kltuk.com.agroterra.models.Act;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultHolder> {


    public ResultAdapter(List<Act> data) {
        this.data = data;
    }

    @Override
    public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.recycler_item_result, parent, false);

        return new ResultHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultHolder holder, int position) {

        Act result = data.get(position);

        holder.updateValus(result);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private List<Act> data;

    class ResultHolder extends RecyclerView.ViewHolder {

        TextView fieldNameView;
        TextView countWeedsView;
        TextView fieldAreaView;
        TextView totalCountWeedsView;

        ResultHolder(View itemView) {
            super(itemView);

            fieldNameView = itemView.findViewById(R.id.result_field_name);
            countWeedsView = itemView.findViewById(R.id.result_count_weeds);
            fieldAreaView = itemView.findViewById(R.id.result_field_area);
            totalCountWeedsView = itemView.findViewById(R.id.result_total_count_weeds);

        }

        void updateValus(Act result) {
            fieldNameView.setText(result.getField().getName());
            countWeedsView.setText(result.getCountWeeds().toString());
            fieldAreaView.setText(result.getFieldArea().toString());
            totalCountWeedsView.setText(result.getTotalCountWeeds().toString());
        }

    }

}
