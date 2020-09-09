package com.example.moviebuff;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailsActivity extends AppCompatActivity {
    TextView nameofMovie,plotSynopsis,userrating,releaseDate;
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initCollapsingToolbar();

        imageView= (ImageView)findViewById(R.id.thumbnail_image_header);
        nameofMovie=(TextView)findViewById(R.id.movietitle);
        plotSynopsis=(TextView)findViewById(R.id.plotsynopsis);
        userrating=(TextView)findViewById(R.id.userrating);
        releaseDate=(TextView)findViewById(R.id.releasedate);

        Intent intetget=getIntent();
        if(intetget.hasExtra("original_title")){
            String  thumbnail=getIntent().getExtras().getString("poster_path");
            String  movieName=getIntent().getExtras().getString("original_title");
            String  synopsis=getIntent().getExtras().getString("overview");
            String  rating=getIntent().getExtras().getString("vote_average");
            String  dateofRelease=getIntent().getExtras().getString("release_date");

            Glide.with(this)
                    .load(thumbnail)
                    .placeholder(R.drawable.loading)
                    .into(imageView);
            nameofMovie.setText(movieName);
            plotSynopsis.setText(synopsis);
            userrating.setText(rating);
            releaseDate.setText(dateofRelease);
        }else{
            Toast.makeText(this,"No API DATA",Toast.LENGTH_SHORT).show();
        }

    }

    private void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout=
                (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout=(AppBarLayout)findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow=false;
            int scrollRange=-1;
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(scrollRange==-1){
                    scrollRange=appBarLayout.getTotalScrollRange();
                }
                if(scrollRange+verticalOffset==0){
                    collapsingToolbarLayout.setTitle(getString(R.string.MovieDetails));
                    isShow=true;
                }else if(isShow){
                    collapsingToolbarLayout.setTitle(" ");
                    isShow=false;
                }
            }
        });
    }

}
