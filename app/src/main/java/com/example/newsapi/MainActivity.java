package com.example.newsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.newsapi.roomdb.AppDatabase;
import com.example.newsapi.roomdb.AppExecutors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    EditText mHeadlineInput;
    TextView infoText;
    RecyclerView recyclerView;
    List<HeadlineModel> headlineList;
    AppDatabase db;
    Button nxtButton;


    /**
     * Creating handlers
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mHeadlineInput = findViewById(R.id.editHeadline);

        recyclerView = findViewById(R.id.recyclerView);

        infoText = findViewById(R.id.textView);

        nxtButton = findViewById(R.id.button2);

        headlineList = new ArrayList<>();

        db = AppDatabase.getInstance(getApplicationContext());
    }


    /**
     * Obtain string from user input and use FetchHeadline method to start API call
     */
    public void getHeadLine(View view) {
        nxtButton.setVisibility(Button.VISIBLE);
        String queryString = mHeadlineInput.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)                                      //When user searches, hide keyboard
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)                                         //Check network connection
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }

        if (networkInfo != null && networkInfo.isConnected()
                && queryString.length() != 0) {

            new FetchHeadline(mHeadlineInput).execute(queryString);
            mHeadlineInput.setText("");
        } else {
            if (queryString.length() == 0) {                                                        //If sting is empty or no internet connection notify user
                mHeadlineInput.setText("");
                infoText.setText("No Results Found!");
            } else {
                mHeadlineInput.setText("");
                infoText.setText("No internet!");
            }
        }
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, roomDBRecyclerActivity.class);
        startActivity(intent);
    }

    public class FetchHeadline extends AsyncTask<String,Void,String> {
        private TextView mHeadLineText;

        public FetchHeadline(TextView mHeadLineText) {
            this.mHeadLineText = mHeadLineText;
        }


        /**
         * @param strings Call NetworkUtil to begin API Call
         * @return
         */
        @Override
        protected String doInBackground(String... strings) {
            return NetworkUtil.getHeadlineInfo(strings[0]);
        }

        /**
         * @param jsonString After API Call, collect JSON data and put into list
         */
        @Override
        protected void onPostExecute(String jsonString) {
            super.onPostExecute(jsonString);

            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray itemsArray = jsonObject.getJSONArray("articles");
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject article = itemsArray.getJSONObject(i); //Get the current item
                    HeadlineModel model = new HeadlineModel();
                    model.setHeadlineText(article.getString("title"));
                    model.setDescText(article.getString("description"));
                    headlineList.add(model);
/**
 * From AppExecutors we use run method and HeadlineDao to add json data into our database
 */
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {

                                db.headlineDao().insertHeadline(model); //we use dao to insert into db
                                Log.i(TAG, "the data inserted to db -- "+model);
                        }

                            });
                        }


                }
             catch (JSONException e) {
                e.printStackTrace();
            }
            PutDataIntoRecyclerView(headlineList);
        }
    }

    /**
     * @param headlineList data collected from postExecute then display in recyclerview using adapter
     */
    private void PutDataIntoRecyclerView(List<HeadlineModel> headlineList){
        RecyclerAdapter adapter = new RecyclerAdapter(this, headlineList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }
}