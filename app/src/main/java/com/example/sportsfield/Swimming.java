package com.example.sportsfield;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Swimming extends Fragment {

    public String swimname, swimID, swimnum;
    public String activityID;

    EditText edswimName;
    EditText edswimID;
    EditText edswimNum;
    Button Order;

    public Swimming() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View swimLayout = inflater.inflate(R.layout.fragment_swimming, container, false);
        edswimName = (EditText) swimLayout.findViewById(R.id.edswimName);
        edswimID = (EditText) swimLayout.findViewById(R.id.edswimID);
        edswimNum = (EditText) swimLayout.findViewById(R.id.edswimNum);
        Order = (Button) swimLayout.findViewById(R.id.swimOrder);

        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swimname=edswimName.getText().toString();
                swimID=edswimID.getText().toString();
                swimnum=edswimNum.getText().toString();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mysports", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); //编辑改写都要用editor
                editor.putString("swim_name_key", swimname);
                editor.putString("swim_ID_key", swimID);
                editor.putString("swim_num_key", swimnum);
                editor.apply();

                activityID = "3";
                Intent pay = new Intent(getActivity(), PayOrder.class);
                pay.putExtra("activity_ID_key", activityID);
                startActivity(pay);

            }
        });
        return swimLayout;
    }

}
