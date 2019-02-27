package kurio_tetsuya.todo.ui.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kurio_tetsuya.todo.ui.activity.UpdateTaskActivity;
import kurio_tetsuya.todo.model.TaskModel;
import kurio_tetsuya.todo.R;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<TaskModel> taskList;
    View view;

    public TaskAdapter(Context mCtx, List<TaskModel> taskList) {
        taskList = new ArrayList<>();
        this.mCtx = mCtx;
        this.taskList = taskList;
        Log.e("Size", "Size" + taskList.size());
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        TaskModel t = taskList.get(position);
        holder.textViewTask.setText(t.getTask());
        holder.textViewDesc.setText(t.getDesc());
        holder.textViewEndDate.setText(t.getEnd_date());
      /*  if (t.isFinished())
            holder.textViewStatus.setText("Completed");
        else
            holder.textViewStatus.setText("Not Completed");*/
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView textViewTask, textViewDesc, textViewEndDate;

        public TasksViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewEndDate = itemView.findViewById(R.id.textViewEndDate);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            TaskModel task = taskList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateTaskActivity.class);
            intent.putExtra("task", task);
            ((Activity) mCtx).finish();
            mCtx.startActivity(intent);

        }
    }

    public void changeData(List<TaskModel> list) {
        this.taskList = list;
        Log.e("#3333", "" + list.size());
        notifyDataSetChanged();
    }
}