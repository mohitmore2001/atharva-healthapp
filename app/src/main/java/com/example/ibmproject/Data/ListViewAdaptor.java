package com.example.ibmproject.Data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ibmproject.R;
import com.example.ibmproject.UTILS.ConstantsDatabase;

import java.util.ArrayList;

public class ListViewAdaptor extends ArrayAdapter<HealthData> {

    private int layoutResource;
    private Activity activity;
    private ArrayList<HealthData> statusList=new ArrayList<>();

    public ListViewAdaptor(@NonNull Activity act, int resource, ArrayList<HealthData> data) {
        super(act, resource, data);
        layoutResource=resource;
        activity=act;
        statusList=data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return statusList.size();
    }

    @Nullable
    @Override
    public HealthData getItem(int position) {
        return statusList.get(position);
    }

    @Override
    public int getPosition(@Nullable HealthData item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;
        ViewHolder holder=null;
        if(row==null||(row.getTag()==null)){
            LayoutInflater inflater=LayoutInflater.from(activity);
            row=inflater.inflate(layoutResource,null);

            holder=new ViewHolder();

            holder.recordedinfo=(TextView) row.findViewById(R.id.healthinfo);
            holder.recordedstatus=(TextView) row.findViewById(R.id.healthstatus);
            holder.recordeddate=(TextView) row.findViewById(R.id.healthdate);

            row.setTag(holder);

        }
        else{
            holder=(ViewHolder) row.getTag();
        }
        //String info="Using Sanitizer and Mask The Risk Of Covid -19 Decreases to 3% from 17%.\nKeeping Social Distance the Rick of Covid-19 Decreases to 3% from 13%";
        holder.database=getItem(position);
        holder.recordedinfo.setText("");
        holder.recordeddate.setText(holder.database.getRecorddate());
        holder.recordedstatus.setText(String.valueOf(holder.database.getStatus()));

        final ViewHolder finalHolder=holder;

        return row;
    }
    public class ViewHolder{
        HealthData database;
        TextView recordedinfo;
        TextView recordeddate;
        TextView recordedstatus;
    }
}
