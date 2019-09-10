package com.example.aluno.running;

import android.app.Fragment;
import android.content.Context;
import android.hardware.SensorManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class senso extends Fragment implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private SensoAngulos sangulos;

    public senso() {
    }

    public final void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        mSensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        int lado= getResources().getDimensionPixelSize(R.dimen.maximo);
        sangulos = new SensoAngulos(getActivity(),lado);
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        return sangulos;
    }

    public void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
     @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
     @Override
    public void onSensorChanged(SensorEvent event) {
         sangulos.angulos(event.values);
    }

}