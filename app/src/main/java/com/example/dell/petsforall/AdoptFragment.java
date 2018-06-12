package com.example.dell.petsforall;


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

import com.example.dell.petsforall.Data.Database.Pet.PetDatabase;
import com.example.dell.petsforall.Domain.Models.Pet;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AdoptFragment extends Fragment {

    ListView listView;
    View view;
    List<Pet> pets;


    public AdoptFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adopt, container, false);

        listView = view.findViewById(R.id.adoptListView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), Adopt.class);
                Pet pet  = pets.get(position);

                intent.putExtra("pet", pet);

                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        pets = PetDatabase.shared.list();

        if(pets != null) {
            String[] petNames = new String[pets.size()];

            int i = 0;
            for(Pet pet: pets)
                petNames[i++] = pet.name;

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, petNames);
            listView.setEmptyView(view.findViewById(R.id.emptyAdopt));
            listView.setAdapter(adapter);
        }
    }
}
