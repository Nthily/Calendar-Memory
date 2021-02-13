package com.nthily.note.Utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class HitokoRetriever extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    public static class checkConnection extends AsyncTask<String, Boolean, Boolean> {
        private Context globalcontext;
        private TextView hitokoo;
        private String oracion;

        public checkConnection(Context context, TextView view) {
            this.globalcontext = context;
            this.hitokoo = view;
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Boolean doInBackground(String... strings) {
            try {
                JSONObject jsonObject = JsonReader.readJsonFromUrl(strings[0]);
                publishProgress(true);
                oracion = jsonObject.getString("hitokoto");
                /*
                if(!jsonObject.getString("from_who").equals("null")) {
                    create = "———— " + jsonObject.getString("from") + " " +"「" + jsonObject.getString("from_who") + "」";
                } else {
                    create = "———— " + jsonObject.getString("from");
                }*/
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Boolean... values) {
            super.onProgressUpdate(values);
            if (values[0]) {
                Toast.makeText(globalcontext, "获取句子成功", Toast.LENGTH_LONG).show();

                hitokoo.setText(oracion);
                Utils.setShowAnimation(hitokoo, 1000);
               // createrr.setText(create);
             //   misc.setShowAnimation(createrr, 1000);
                Notifications.initNotification(globalcontext, oracion);
            } else {
                Toast.makeText(globalcontext, "连接失败", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            globalcontext.getPackageName();
        }
    }


}
