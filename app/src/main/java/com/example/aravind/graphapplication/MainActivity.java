package com.example.aravind.graphapplication;

import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.aravind.graphapplication.databasehelper.DatabaseMethods;
import com.example.aravind.graphapplication.sensoractivities.sensorHelper;
import com.jjoe64.graphview.GraphView;       // Graph view library downloaded from http://www.android-graphview.org/
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity { // parts of code used from https://github.com/jjoe64/GraphView-Demos
    private final Handler mHandler = new Handler();
    GraphView graph;
    LineGraphSeries<DataPoint> series;   // declares line graph
    private Runnable mTimer2;
    int x=1,y=19;
    private double graph2LastXValue = 40d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sensorHelper obj = new sensorHelper(this);
        buildUI();

        String state = Environment.getExternalStorageState();

        if (!(state.equals(Environment.MEDIA_MOUNTED))) {
            Toast.makeText(this, "There is no any sd card", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void buildUI() {

       /* StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"old", "middle", "new"});
        staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        */
    }

    private void generateAcceleratorData() { //DataPoint[]
        DatabaseMethods obj = new DatabaseMethods(this);
        try {
            obj.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<String> values = obj.GetAccelerometerValues();
       // ArrayList<Float[]> list = obj.values;
        //int count = 30;
       // DataPoint[] value = new DataPoint[10];
        //int i=0;
       // Float[] val = new Float[3];
        Log.d("mytag", values.toString());
        //for (Float[] val: list) {
        //    double x = val[0];
        //    double y = val[1];
        //    double z = val[2];
//
        //    DataPoint v = new DataPoint(x,y);
        //    values[i++] = v;
        //}
     //   return value;
    }

    public void OnRun(View view){

        Log.d("VERBOSE", "button press");
       // generateAcceleratorData();
        mHandler.removeCallbacks(mTimer2);
        this.graph = (GraphView) findViewById(R.id.graph);
        graph.removeAllSeries();
        graph2LastXValue = 40d;
        series = new LineGraphSeries<DataPoint>(); // initializes graph

        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);   // defines visible grid bounds
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(40);

        mTimer2 = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 2d;
                series.appendData(new DataPoint(graph2LastXValue, getRandom()), true, 40); // appends random y axis data to graph
                mHandler.postDelayed(this, 200);
            }
        };
        mHandler.postDelayed(mTimer2, 1000);  //calls function every sec.

    }

    public void OnStop (View view){   // clears form fields and graph

        try {
            EditText name = (EditText) findViewById(R.id.nameText);
            EditText id = (EditText) findViewById(R.id.idText);
            EditText age = (EditText) findViewById(R.id.ageText);
            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sexGroup);

            name.setText("");
            age.setText("");
            id.setText("");
            radioGroup.clearCheck();


            graph2LastXValue = 0d;
            mHandler.removeCallbacks(mTimer2);
            graph.removeAllSeries();    // removes graph from screen
        }
        catch (Exception ex){

        };

    }

    public void OnUpload(View view){
        FileWriter Fw = new FileWriter();
        Fw.Test(this.getApplicationContext());
        //new uploader().execute(this.getApplicationContext());
        new uploadtoserver().execute(this);
    }

    public void OnDownload(View view){
        new DownloadFromServer().execute("https://impact.asu.edu/Appenstance/"+"Foodbank.db","Foodbank.db");
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

    double mLastRandom = 2;
    Random mRand = new Random();
    private double getRandom() {
        return mLastRandom += mRand.nextDouble()*1 - 0.25;
    }//  get random function taken from https://github.com/jjoe64/GraphView-Demos

}
