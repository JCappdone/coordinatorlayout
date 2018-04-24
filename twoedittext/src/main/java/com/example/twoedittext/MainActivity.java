package com.example.twoedittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @BindView(R.id.edtName)
    EditText mEdtName;
    @BindView(R.id.edtNumber)
    EditText mEdtNumber;
    private String mName;
    private String mNumber;
    private boolean callwebservicebyname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mName = mEdtName.getText().toString();
        mNumber = mEdtNumber.getText().toString();

        mEdtNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });


        mEdtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(s.length() > 0 && mEdtName.isFocused()){
                    mEdtNumber.setText("");
                }

                if(s.length() >= 3) {
                    callwebservicebyname = true;
                    Log.d(TAG, "onTextChanged: ========= " + callwebservicebyname);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        mEdtNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() > 0 && mEdtNumber.isFocused()){
                    mEdtName.setText("");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





    }

    private void performSearch() {

        Log.d(TAG, "performSearch: ======== Clicked" );
        if(!mEdtName.getText().toString().isEmpty()){

            callwebservicebyname = true;
            Log.d(TAG, "onCreate: ========== "+callwebservicebyname);

        }else{
            callwebservicebyname = false;
            Log.d(TAG, "onCreate: =============== "+callwebservicebyname);

        }

    }



}
