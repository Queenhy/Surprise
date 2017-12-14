package com.example.d064989.surprise;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class setTimer extends DialogFragment {

    public static boolean isDismissed;
    public static long delay;

    View rootView;
    EditText delayInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.settimes_fragment, container, false);
        getDialog().setTitle("Simple Dialog");

        Button button = rootView.findViewById(R.id.dismiss);
        delayInput = rootView.findViewById(R.id.delay);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onDismiss(getDialog());
            }
        });

        return rootView;
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);

        setTimer.isDismissed = true;

        String delayInputStrng = delayInput.getText().toString();

        if (delayInputStrng.isEmpty()) {
            delay = 5000;
        } else {
            delay = Long.parseLong(delayInputStrng);
        }

        MainActivity mA = new MainActivity();
        FragmentActivity fA = getActivity();
        mA.setDelay(delay, fA);
    }
}