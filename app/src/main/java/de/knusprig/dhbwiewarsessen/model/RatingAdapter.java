package de.knusprig.dhbwiewarsessen.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.knusprig.dhbwiewarsessen.R;

public class RatingAdapter extends ArrayAdapter<Rating> {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy | HH:mm");

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
        TextView date = convertView.findViewById(R.id.loDate);


        userName.setText(rating.getUsername());
        dish.setText(rating.getDish());
        ratingBar.setRating((float)rating.getRating()/10);
        comment.setText(rating.getComment());
        date.setText(simpleDateFormat.format(rating.getDate().getTime()));
        //return view after modifying it
        return convertView;
    }
}
