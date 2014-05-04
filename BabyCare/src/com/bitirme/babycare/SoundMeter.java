package com.bitirme.babycare;

import java.io.IOException;

import android.media.MediaRecorder;

public class SoundMeter {
        static final private double EMA_FILTER = 0.6;

        private MediaRecorder mRecorder = null;
        private double mEMA = 0.0;

        public void start() throws IllegalStateException, IOException {
                if (mRecorder == null) {
                        mRecorder = new MediaRecorder();
                        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mRecorder.setOutputFile("/dev/null"); 
                    mRecorder.prepare();
                    mRecorder.start();
                    mEMA = 0.0;
                }
        }
        
        public void stop() {
                if (mRecorder != null) {
                        mRecorder.stop(); 
                        mRecorder.reset();
                        mRecorder.release();
                        mRecorder = null;
                }
        }
        
        public double getAmplitude() {
                if (mRecorder != null)
                        return  (20*Math.log( mRecorder.getMaxAmplitude()/(6*5*3)));//Amplitute volume/volume of room
                else
                        return 0;

        }

        public double getAmplitudeEMA() {
                double amp = getAmplitude();
                mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
                return mEMA;
        }
}