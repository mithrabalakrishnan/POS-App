package com.example.pos_android.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.pos_android.R;

import java.util.ArrayList;
import java.util.Locale;

public class VoiceDialog extends Dialog {
    private final Context context;
    private OnVoiceReceivedListener listener;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;

    public VoiceDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    public VoiceDialog(@NonNull Context context, OnVoiceReceivedListener listener) {
        super(context);
        this.context = context;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCancelable(true);
        setContentView(R.layout.voice_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView textView = findViewById(R.id.tv_dialog);
        ImageView micButton = findViewById(R.id.imageView2);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {
                textView.setText("Say something...");
            }

            @Override
            public void onBeginningOfSpeech() {
                micButton.setImageResource(R.drawable.mic_on);
                textView.setText("Listening...");
            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {
                textView.setText("Oops! Something went wrong");
            }

            @Override
            public void onResults(Bundle bundle) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    micButton.setImageResource(R.drawable.mic_off);
                    ArrayList<String> data = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                    if (data != null) {
                        textView.setText(data.get(0));
                    }

                    new Handler().postAtTime(() -> {
                        listener.onReceiveText(data.get(0));
                        dismiss();
                    }, 3000);
                }

            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });

        micButton.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                speechRecognizer.stopListening();
            }
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                micButton.setImageResource(R.drawable.mic_off);
                speechRecognizer.startListening(speechRecognizerIntent);
            }
            return false;
        });

    }


    @Override
    protected void onStop() {
        super.onStop();
        speechRecognizer.destroy();
    }

    public interface OnVoiceReceivedListener {
        void onReceiveText(String value);
    }


}
