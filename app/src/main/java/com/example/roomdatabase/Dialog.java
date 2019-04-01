package com.example.roomdatabase;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class Dialog extends AppCompatDialogFragment {
    DialogListener dialogListener;
    static String name;
    EditText etName;
    @Override
    public android.app.Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflator = getActivity().getLayoutInflater();
        View view = inflator.inflate(R.layout.layout_dialog, null);

        builder.setView(view)
                .setTitle("Add User")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        name = etName.getText().toString().trim();
                        dialogListener.sendData(name);
                    }
                });

        etName = view.findViewById(R.id.et_Name);
        return builder.create();
    }
    public interface DialogListener {
        void sendData(String name);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dialogListener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " Implement DialogListener");
        }
    }
}

