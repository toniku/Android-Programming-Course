package com.example.heatapp_ver_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WorkOutArrayAdapter extends ArrayAdapter<WorkOutType> {

    static final int VIEW_TYPE_WORKOUT = 0;
    static final int VIEW_TYPE_PAUSE = 1;
    static final int VIEW_TYPE_COUNT = 2;

    public WorkOutArrayAdapter(Context context, ArrayList<WorkOutType> workOutTypes) {
        super(context, 0, workOutTypes);
    }

    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        WorkOutType workOutType = getItem(position);

        if (workOutType instanceof Workout) {
            return VIEW_TYPE_WORKOUT;
        } else {
            return VIEW_TYPE_PAUSE;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        WorkOutType workOutType = getItem(position);

        if (convertView == null) {
            int layoutId = 0;
            if (getItemViewType(position) == VIEW_TYPE_WORKOUT) {
                layoutId = R.layout.workout_list_item;
            } else {
                layoutId = R.layout.pause_list_item;
            }
            convertView = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);
        }
        TextView workout_name = convertView.findViewById(R.id.event_list_item);
        workout_name.setText(workOutType.getEvent() + "\n" + workOutType.getSeconds());
        return convertView;
    }
}

