package com.example.customlistviewvolley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyJsonAdapter extends BaseAdapter {
    Context context;
    List<JsonModel> jsonModelList=new ArrayList<>();
    LayoutInflater layoutInflater;

    public MyJsonAdapter(Context context, List<JsonModel> jsonModelList) {
        this.context = context;
        this.jsonModelList = jsonModelList;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return jsonModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View root=layoutInflater.inflate(R.layout.custom_json,null);
        TextView User,Id,Title,Body;
        User=root.findViewById(R.id.UserID);
        Id=root.findViewById(R.id.ID);
        Title=root.findViewById(R.id.Title);
        Body=root.findViewById(R.id.Body);

        User.setText(""+jsonModelList.get(i).getUserid());
        Id.setText(""+jsonModelList.get(i).getId());
        Title.setText(""+jsonModelList.get(i).getTitle());
        Body.setText(""+jsonModelList.get(i).getBody());


        return root;
    }
}