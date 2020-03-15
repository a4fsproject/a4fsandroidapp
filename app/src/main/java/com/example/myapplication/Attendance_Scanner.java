package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myapplication.utils.Constants;
import com.example.myapplication.utils.DataInterface;
import com.example.myapplication.utils.Webservice_Volley;
import com.google.zxing.Result;

import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.SimpleTimeZone;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Attendance_Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler, DataInterface {

    private ZXingScannerView mScannerView;

    Webservice_Volley volley;

    String enroll_number = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view

        volley = new Webservice_Volley(this,this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("SCANNER", rawResult.getText()); // Prints scan results
        Log.v("SCANNER", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        String data = rawResult.getText().trim();

        String sep = "~~";

        String[] data_arr = data.split(sep);

        addAttendance(data_arr[0],data_arr[1],data_arr[2],data_arr[3],data_arr[4]);

        // If you would like to resume scanning, call this method below:
//        mScannerView.resumeCameraPreview(this);
    }

    public void addAttendance(String uid,String branch,String sem,String scode,String sname){

        String url = Constants.Webserive_Url+"attendance.php";
        HashMap<String,String> params = new HashMap<>();

        params.put("s_enroll_number",enroll_number);
        params.put("s_branch",branch);
        params.put("s_semester",sem);
        params.put("sub_code",scode);
        params.put("sub_name",sname);
        params.put("a_status","P");
        params.put("a_date",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        params.put("a_time",new SimpleDateFormat("HH:mm").format(new Date()));
        params.put("u_id",uid);

        volley.CallVolley(url,params,"attendance");

    }

    @Override
    public void getData(JSONObject jsonObject, String tag) {

        try {

            Toast.makeText(this, jsonObject.getString("message"), Toast.LENGTH_LONG).show();

//            mScannerView.resumeCameraPreview(this);

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }
}
