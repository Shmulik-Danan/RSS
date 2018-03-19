package com.example.elixi.rssnews.Fragment;


import android.arch.lifecycle.LifecycleFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.elixi.rssnews.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class descriptionFragment extends LifecycleFragment {
    private static final String TITLE = "title";
    private static final String IMAGE = "image";
    private static final String SRC = "src";
    private static final String URL = "url";


    private TextView titleTextField;
    private ImageView imageView;
    private TextView descriptionTextField;
    private Button button;

    public descriptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_description, container, false);

        titleTextField = (TextView) v.findViewById(R.id.title);
        imageView = (ImageView) v.findViewById(R.id.image);
        descriptionTextField = (TextView) v.findViewById(R.id.des);
        button = (Button) v.findViewById(R.id.bt);

        String img = getArguments().getString(IMAGE);

        Picasso.with(getContext()).load(img).into(imageView);

        descriptionTextField.setText(getArguments().getString(SRC));
        titleTextField.setText(getArguments().getString(TITLE));

        //Open url windows -- when button is clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(getArguments().getString(URL)));
                startActivity(browserIntent);
            }
        });
        return v;
    }

}
