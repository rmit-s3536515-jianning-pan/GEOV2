package com.example.pan.assignment1.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URLConnection;


public class MyJobService extends JobService {
    public static final String TAG = "MY_JOB_SERVICE";
    public MyJobService() {
    }

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        Log.i(TAG,"on Start Job: " + jobParameters.getJobId());

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
//                    Log.i(TAG,"in the thread ");
                    apiCon();
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.i(TAG,"run : job finished!");
                jobFinished(jobParameters,false);
            }
        }).start();


        return true; // true for doing background
    }

//    private static class JobTask extends AsyncTask<JobParameters, Void, JobParameters> {
//        private final JobService jobService;
//
//        public JobTask(JobService jobService) {
//            this.jobService = jobService;
//        }
//
//        @Override
//        protected JobParameters doInBackground(JobParameters... params) {
//
//            return params[0];
//        }
//
//        @Override
//        protected void onPostExecute(JobParameters jobParameters) {
//            jobService.jobFinished(jobParameters, false);
//        }
//    }
    private void apiCon(){
        HttpURLConnection connection = null;

        String s = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=-37.819548,144.960009&destinations=-37.810045,144.964220&key=AIzaSyBgGGd4aLrlMwAwdWbwGjTCpcbww7XB9S4";

        try {
            URI url = new URI(s);
             connection = (HttpURLConnection) url.toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            if(statusCode != HttpURLConnection.HTTP_OK){
                Log.e(TAG, "Invalid Response Code: " + statusCode);
                return;
            }
            else{
                BufferedReader br =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                int length = connection.getContentLength();
                StringBuilder sb = new StringBuilder();
                String line;
                while((line=br.readLine())!=null){
                    sb.append(line);
                }
                String json = sb.toString();
                Log.i(TAG,json);
//                JSONObject root  = new JSONObject(json);

            }
        }catch(MalformedURLException e) {
            e.printStackTrace();
        }catch (Exception e)
        {
            Log.e(TAG, "Exception caught: " + e.getMessage());
            e.printStackTrace();
        }
        finally
        {
            if (connection != null)
                connection.disconnect();
        }

    }
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.i(TAG,"on Stop Job : " + jobParameters.getJobId());
        return false;
    }




}
