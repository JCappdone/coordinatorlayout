package com.example.myapplication;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class DataRequest extends Request<JSONObject> {

    private Response.Listener<JSONObject> mListener;
    Response.ErrorListener mErrorListener;
    private Map<String, String> mParams;


    public DataRequest(int method, String url, Map<String, String> params,
                       Response.Listener<JSONObject> listener,
                       Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        mErrorListener = errorListener;
        mParams = params;
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }


    @Override
    public Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
        error.printStackTrace();
    }
}
