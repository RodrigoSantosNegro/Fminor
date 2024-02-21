package com.example.aptmc;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class CorrectIntervalDialogFragment extends DialogFragment {
    public static CorrectIntervalDialogFragment newInstance(int interval) {
        CorrectIntervalDialogFragment fragment = new CorrectIntervalDialogFragment();
        // Argument
        Bundle args = new Bundle();
        args.putInt("semitones", interval);
        fragment.setArguments(args);
        return fragment;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // Create the AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Retrieve the interval type from the fragment arguments
        int semitones = getArguments().getInt("semitones");
        int[] intervalsByNumberOfSemitones = {R.string.interval_0, R.string.interval_1, R.string.interval_2, R.string.interval_3, R.string.interval_4,
                R.string.interval_5, R.string.interval_6, R.string.interval_7, R.string.interval_8, R.string.interval_9,
                R.string.interval_10, R.string.interval_11};
        String interval = getResources().getString(intervalsByNumberOfSemitones[semitones]);

        builder.setTitle("Actually...");
        builder.setMessage("The interval was a "+interval);

        // Set the behavior for the positive button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();  // Dismiss the dialog
            }
        });

        return builder.create();  // Create and return the AlertDialog
    }


}
