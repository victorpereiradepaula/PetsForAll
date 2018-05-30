package com.example.dell.petsforall;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonateFragment extends Fragment {


    public DonateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_donate, container, false);

        String[] cats = {
                "Panquequinha",
                "Dante",
                "Belinha",
                "Boris I",
                "Boris II",
                "Mingal"
        };

        ListView listView = view.findViewById(R.id.listDonate);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, cats);
        listView.setAdapter(arrayAdapter);

        return view;
    }

}
