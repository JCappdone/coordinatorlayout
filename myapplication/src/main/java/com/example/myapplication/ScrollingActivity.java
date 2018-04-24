package com.example.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.android.volley.Request.Method.POST;

public class ScrollingActivity extends AppCompatActivity {

    @BindView(R.id.txtName)
    TextView mTxtName;
    @BindView(R.id.txtNumber)
    TextView mTxtNumber;

    List<UserModel.DataBean> mList = new ArrayList<UserModel.DataBean>();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mQueue = Volley.newRequestQueue(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        callWebServiceFetchInquiryUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    public void callWebServiceFetchInquiryUser(){
        String url = "https://reqres.in/api/register";

        Map<String,String> params =new HashMap<>();
        params.put("email","morpheus");
        params.put("password","leader");

        DataRequest dataList = new DataRequest(POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("", "==============================onResponse: ");
                Log.d("", "====esponse: "+response.toString());
                Gson gson = new Gson();
                UserModel userModel =  gson.fromJson(response.toString(),UserModel.class);
                mList.clear();
          //      mList.addAll(userModel.getData());

                for(UserModel.DataBean dataBean : mList){
                    Log.d("", "onResponse: "+ dataBean.getId());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("", "==============================onErrorResponse: ");
                error.printStackTrace();
            }
        });

        mQueue.add(dataList);

    }



}
