package com.example.customlistviewvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    String baseUrl;
    ProgressDialog progressDialog;
    JsonModel jsonModel;
    MyJsonAdapter myJsonAdapter;
    RequestQueue queue;
    List<JsonModel> jsonModelList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.JsonList);

        queue=Volley.newRequestQueue(this);

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

        baseUrl=getResources().getString(R.string.placeholder_api);

        myJsonAdapter=new MyJsonAdapter(this,jsonModelList);
        listView.setAdapter(myJsonAdapter);

        loadMethod(baseUrl);

    }

    private void loadMethod(String baseUrl) {

        progressDialog.show();
        progressDialog.setMessage("Fetching Api Data!....");

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, baseUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                // Toast.makeText(MainActivity2.this, ""+response, Toast.LENGTH_SHORT).show();

                for(int i=0;i< response.length();i++)
                {

                    try {
                        JSONObject jsonObject= response.getJSONObject(i);
                        int userId=jsonObject.getInt("userId");
                        int id=jsonObject.getInt("id");
                        String title=jsonObject.getString("title");
                        String body=jsonObject.getString("body");

                        jsonModel=new JsonModel(userId,id,title,body);
                        jsonModelList.add(jsonModel);
                        myJsonAdapter.notifyDataSetChanged();
                        if (i==9)
                            break;

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();

                Toast.makeText(MainActivity.this, ""+error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);


    }
}