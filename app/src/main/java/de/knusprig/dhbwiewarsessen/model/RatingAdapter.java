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
import java.util.List;

import de.knusprig.dhbwiewarsessen.R;

public class RatingAdapter extends ArrayAdapter<Rating> {



    public RatingAdapter(Context context, List<Rating> ratings){
        super(context, 0,ratings);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
//        Get data from this position
        Rating rating = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rating_row,parent, false);
        }

        TextView userName = convertView.findViewById(R.id.loUsername);
        TextView dish = convertView.findViewById(R.id.loDish);
        RatingBar ratingBar =convertView.findViewById(R.id.loRating);
        TextView comment = convertView.findViewById(R.id.loComment);

        userName.setText("default username"/*rating.getUser_id()*/); //todo: get username from user_id
        dish.setText(rating.getDish());
        ratingBar.setRating((float)rating.getRating()/10);
        comment.setText(rating.getComment());
        //return view after modifying it
        return convertView;
    }
}
