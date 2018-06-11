package com.example.dell.petsforall;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

    public DonateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();

        User user = UserDatabase.shared.getCurrentUser(getContext());

        if(user != null) {
            String[] petNames = new String[user.pets.size()];
            int i = 0;
            for(Pet pet: user.pets)
                petNames[i++] = pet.name;


            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, petNames);
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

        return view;
    }

}
