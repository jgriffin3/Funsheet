package com.centennialdesigns.funsheet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    public static final String PARCEL_ID = "parcel_id";
    private Card mCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton navigateButton = (FloatingActionButton) findViewById(R.id.fab);
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent = new Intent();
                mapIntent.setAction(Intent.ACTION_VIEW);
                String uriString = "geo:0,0?q="
                        + mCard.getLatitude() + "," + mCard.getLongitude()
                        + "(" + mCard.getTitle() + ")";
                mapIntent.setData(Uri.parse(uriString));
                startActivity(mapIntent);
            }
        });

        mCard = getIntent().getExtras().getParcelable(PARCEL_ID);

        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle(mCard.getTitle());

        String price = "";
        if (mCard.getPrice() > 0) {
            price = new String(new char[mCard.getPrice()]).replace("\0", "$");
        } else {
            price = "Free";
        }

        TextView distanceTextView = (TextView) findViewById(R.id.detail_distance);
        distanceTextView.setText(mCard.getDistance());
        TextView priceTextView = (TextView) findViewById(R.id.detail_price);
        priceTextView.setText(price);
        TextView descriptionTextView = (TextView) findViewById(R.id.location_description);
        descriptionTextView.setText(mCard.getDescription());
        ImageView background = (ImageView) findViewById(R.id.toolbarBG);
        Picasso.with(getApplicationContext()).load("https://funsheet.centennialdesigns.com/img/" + mCard.getId() + ".jpg").into(background);

    }


}
