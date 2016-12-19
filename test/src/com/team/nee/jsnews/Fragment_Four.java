package com.team.nee.jsnews;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Four extends Fragment {


    private TextView textview;

    public Fragment_Four() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__four, container, false);
        textview = ((TextView) view.findViewById(R.id.go));
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), LoginActivity.class);
            	Intent intent = new Intent(textview.getContext(),LoginActivity.class);
            	
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

}
