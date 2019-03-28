package com.athena.holosimulator;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;

import java.util.List;

public class SensorActivity extends AppCompatActivity {

    SensorManager sensorManager;
    List list;
    TextView textView;
    GraphView graphXY, graphYZ, graphXZ;
    float lastX=0, lastY=0, lastZ=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        graphXY = findViewById(R.id.graphXY);
        graphYZ = findViewById(R.id.graphYZ);
        graphXZ = findViewById(R.id.graphXZ);
        textView = (TextView)findViewById(R.id.textViewShowData);

        list = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if(list.size()>0){
            sensorManager.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Accelerometer Detected!", Toast.LENGTH_LONG).show();
        }
    }

    SensorEventListener sel = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            textView.setText("X: "+ Math.round(values[0])+"\t\tY: "+ Math.round(values[1])+"\t\tZ: "+ Math.round(values[2]));

            // get the change of the x,y,z values of the accelerometer
            float deltaX = Math.abs(lastX - event.values[0]);
            float deltaY = Math.abs(lastY - event.values[1]);
            float deltaZ = Math.abs(lastZ - event.values[2]);

            // if the change is below 2, it is just plain noise
            if (deltaX < 2)
                deltaX = 0;
            if (deltaY < 2)
                deltaY = 0;
            if (deltaZ < 2)
                deltaZ = 0;

            // set the last know values of x,y,z
             lastX = event.values[0];
             lastY = event.values[1];
             lastZ = event.values[2];

            PointsGraphSeries<DataPoint> seriesXY = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(deltaX, deltaY),
            });
            graphXY.addSeries(seriesXY);

            PointsGraphSeries<DataPoint> seriesYZ = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(deltaY, deltaZ),
            });
            graphYZ.addSeries(seriesYZ);

            PointsGraphSeries<DataPoint> seriesXZ = new PointsGraphSeries<DataPoint>(new DataPoint[] {
                    new DataPoint(deltaX, deltaZ),
            });
            graphXZ.addSeries(seriesXZ);

        }
    };
}
