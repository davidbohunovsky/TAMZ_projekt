package com.example.david.edison;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class listAdapter<T> extends ArrayAdapter<T> {

    Context context;
    int layoutResID;
    String databaseType;
    List<T> data = null;

    public listAdapter(Context context, int layoutResID, List<T> data, String databaseType) {
    super(context,layoutResID,data);
    this.context = context;
    this.layoutResID = layoutResID;
    this.data = data;
    this.databaseType = databaseType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        EntryHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row = inflater.inflate(layoutResID, parent, false);

            holder = new EntryHolder();
            holder.value1 = row.findViewById(R.id.txtListValue1);
            holder.value2 = row.findViewById(R.id.txtListValue2);
            holder.value3 = row.findViewById(R.id.txtListValue3);
            row.setTag(holder);
        }
        else {
            holder = (EntryHolder) row.getTag();
        }

        holder.value3.setText("");

        if(databaseType.equals("subject")){
            subjectDB OneData = (subjectDB) data.get(position);
            holder.value1.setText(OneData.name);
            holder.value2.setText("Kredity: " + OneData.credits);
        }

        if(databaseType.equals("student")){
            studentDB OneData = (studentDB) data.get(position);
            holder.value1.setText(OneData.name);
            holder.value2.setText(OneData.surname);
        }

        if(databaseType.equals("teacher")){
            teacherDB OneData = (teacherDB) data.get(position);
            holder.value1.setText(OneData.name);
            holder.value2.setText(OneData.surname);
        }

        if(databaseType.equals("room")){
            roomDB OneData = (roomDB)data.get(position);
            holder.value1.setText(OneData.faculty);
            holder.value2.setText(OneData.number);
        }

        if(databaseType.equals("examTeach")){
            examDB OneData = (examDB)data.get(position);
            holder.value1.setText(OneData.subject.name);
            holder.value2.setText(OneData.date);
            holder.value3.setText(OneData.start + " - " + OneData.end);
        }

        if(databaseType.equals("examStud")){
            examDB OneData = (examDB)data.get(position);
            holder.value1.setText(OneData.date);
            holder.value2.setText(OneData.start + " - " + OneData.end);
            holder.value3.setText(OneData.room.faculty + " - " + OneData.room.number);
        }

        if(databaseType.equals("examListStud")){
            examResultDB OneData = (examResultDB)data.get(position);
            holder.value1.setText(OneData.exam.subject.name);
            holder.value2.setText(OneData.exam.date + "   " + OneData.exam.start + " - " + OneData.exam.end);
            holder.value3.setText(OneData.exam.room.faculty + " - " + OneData.exam.room.number);
        }

        return row;
    }

    static class EntryHolder
    {
        TextView value1;
        TextView value2;
        TextView value3;
    }
}
