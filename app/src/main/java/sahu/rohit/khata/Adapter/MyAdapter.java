package sahu.rohit.khata.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.zip.DataFormatException;
import sahu.rohit.khata.Database.DatabseHelper;
import sahu.rohit.khata.Model.customer;

public class MyAdapter extends BaseAdapter {

    //DatabseHelper db = new DatabseHelper(this);

    ArrayList<customer> details;



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
