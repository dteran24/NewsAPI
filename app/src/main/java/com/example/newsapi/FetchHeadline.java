package com.example.newsapi;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class FetchHeadline extends AsyncTask<String,Void,String> {
    private TextView mHeadLineText;

    public FetchHeadline(TextView mHeadLineText) {
        this.mHeadLineText = mHeadLineText;
    }


    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtil.getHeadlineInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray itemsArray = jsonObject.getJSONArray("articles");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject article = itemsArray.getJSONObject(i); //Get the current item
                String title = null;
                try {
                    title = article.getString("title");

                    if (title != null) {

                        mHeadLineText.setText(title);
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
