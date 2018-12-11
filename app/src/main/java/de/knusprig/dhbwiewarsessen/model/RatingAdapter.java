package de.knusprig.dhbwiewarsessen.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.knusprig.dhbwiewarsessen.R;

public class RatingAdapter extends ArrayAdapter<Rating> {



    public RatingAdapter(Context context, ArrayList<Rating> ratings){
        super(context, 0,ratings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
//        Get data from this position
        Rating rating = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rating_row,parent, false);
        }

        TextView userName = (TextView) convertView.findViewById(R.id.loUsername);
        TextView dish = (TextView) convertView.findViewById(R.id.loDish);
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.loRating);

        userName.setText(rating.getUser().getUsername());
        dish.setText(rating.getDish().getTitle());
        ratingBar.setRating(rating.getRating());
        //return view after modifying it
        return convertView;
    }

}
