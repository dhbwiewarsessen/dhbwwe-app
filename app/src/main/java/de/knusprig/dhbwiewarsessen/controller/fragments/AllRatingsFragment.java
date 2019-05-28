package de.knusprig.dhbwiewarsessen.controller.fragments;

import android.widget.AdapterView;
import android.widget.Toast;

public class AllRatingsFragment extends AbstractRatingsFragment {

    @Override
    protected AdapterView.OnItemLongClickListener createLongClickListener() {
        return (parent, view1, position, id) -> {
            Toast.makeText(mainActivity.getApplicationContext(), "Delete/Edit ratings from \"My ratings\"", Toast.LENGTH_LONG).show();
            return false;
        };
    }
}