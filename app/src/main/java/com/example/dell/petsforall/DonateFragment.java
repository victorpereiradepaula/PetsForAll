package com.example.dell.petsforall;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dell.petsforall.Data.Database.Pet.PetDatabase;
import com.example.dell.petsforall.Data.Database.User.UserDatabase;
import com.example.dell.petsforall.Domain.Models.Pet;
import com.example.dell.petsforall.Domain.Models.User;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonateFragment extends Fragment {
    private FloatingActionButton floatingActionButton;

    ListView listView;
    View view;
    String[] petNames;
    Long[] petIds;
    ArrayAdapter<String> arrayAdapter;
    int currentId;

    public DonateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        User user = UserDatabase.shared.getCurrentUser(getContext());

        if(user != null) {
            int arraySize = user.pets.size();
            petNames = new String[arraySize];
            petIds = new Long[arraySize];
            int i = 0;
            for(Pet pet: user.pets) {
                petNames[i] = pet.name;
                petIds[i++] = pet.id;
            }


            arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, petNames);
            listView.setEmptyView(view.findViewById(R.id.emptyDonate));
            listView.setAdapter(arrayAdapter);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_donate, container, false);
        floatingActionButton = view.findViewById(R.id.buttonAddPet);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPet.class);
                startActivity(intent);
            }
        });

        listView = view.findViewById(R.id.listDonate);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentId = i;
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                final String selectedItem = petNames[i];
                alertDialog.setMessage("Deseja remover " + selectedItem + "?");
                alertDialog.setCancelable(true);
                alertDialog.setNegativeButton("No", null);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean petDeleted = PetDatabase.shared.delete(petIds[currentId]);
                        if (petDeleted) {
                            arrayAdapter.notifyDataSetChanged();
                            onResume();
                            Toast.makeText(getContext(), selectedItem + " foi removido.",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getContext(), "Algo deu errado :(",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                alertDialog.show();
                return true;
            }
        });

        return view;
    }

}
