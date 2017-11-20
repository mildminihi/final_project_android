package wanroj.supanat.pomodoro_knight.View;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import wanroj.supanat.pomodoro_knight.Model.TaskInfo;
import wanroj.supanat.pomodoro_knight.R;

/**
 * Created by mild supanat on 19/11/2560.
 */

public class AdapterList extends ArrayAdapter<TaskInfo> {

    private Context context;
    private List<TaskInfo> taskInfos;
    private int resource;

    public AdapterList(@NonNull Context context, int resource, @NonNull List<TaskInfo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.taskInfos = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(resource, null, false);
        if (taskInfos.get(position).getDone() == taskInfos.get(position).getTarget()) {
            convertView.setBackgroundColor(Color.parseColor("#6aa84f"));
        }
        else if (taskInfos.get(position).getDone() == 0) {
            convertView.setBackgroundColor(Color.parseColor("#cc0000"));
        }
        else convertView.setBackgroundColor(Color.parseColor("#f1c232"));
        TextView textviewName = (TextView) convertView.findViewById(R.id.textViewListName);
        TextView textViewWork = (TextView)convertView.findViewById(R.id.textViewListWork);
        TextView textViewTarget = (TextView)convertView.findViewById(R.id.textViewListTarget);
        textviewName.setText(taskInfos.get(position).getTaskName());
        textViewWork.setText(taskInfos.get(position).getWorkInterval()+" minute");
        textViewTarget.setText(taskInfos.get(position).getDone()+"/"+taskInfos.get(position).getTarget()+ " times");
        return convertView;
    }
}
