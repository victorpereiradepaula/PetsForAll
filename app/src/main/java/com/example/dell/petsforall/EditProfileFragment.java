package com.example.dell.petsforall;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.petsforall.Data.Database.User.UserDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditProfileFragment extends Fragment {
    private SeekBar seekBar;
    private TextView textView;
    private Button logoutButton, deleteAccountButton;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        seekBar = view.findViewById(R.id.seekBarKm);
        textView = view.findViewById(R.id.textViewKm);
        logoutButton = view.findViewById(R.id.logoutButton);
        deleteAccountButton = view.findViewById(R.id.deleteAccountButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Log out
                SharedPreferences preferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong("current_user_id", -1);
                editor.commit();

                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("Ao apagar sua conta, todos os seus dados serão apagados.\nDeseja apagar sua conta?");
                alertDialog.setCancelable(true);
                alertDialog.setNegativeButton("Não", null);
                alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Log out
                        SharedPreferences preferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        Long userId = preferences.getLong("current_user_id", -1);
                        UserDatabase.shared.delete(userId);
                        editor.putLong("current_user_id", -1);
                        editor.commit();

                        Toast.makeText(getContext(), "Usuário apagado com sucesso", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getContext(), Login.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
            });
                alertDialog.show();
            }
        });

        seekBar.setProgress(50);
        textView.setText(seekBar.getProgress() + " km");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(progress + " km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        return view;
    }
}
