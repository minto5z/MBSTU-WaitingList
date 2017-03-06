package com.blogspot.larn4android.materialtabs.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.blogspot.larn4android.materialtabs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MahadiurJaman on 2/2/2017.
 */

public class CardArrayAdapter  extends ArrayAdapter<Choose> {
    private static final String TAG = "CardArrayAdapter";
    private List<Choose> cardList = new ArrayList<Choose>();

    static class CardViewHolder {
        TextView name;
        TextView father;
        TextView merit;
        TextView adRoll;
    }

    public CardArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    @Override
    public void add(Choose object) {
        cardList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public Choose getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item_card, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.name = (TextView) row.findViewById(R.id.line1);
            viewHolder.father = (TextView) row.findViewById(R.id.line2);
            viewHolder.merit = (TextView) row.findViewById(R.id.line3);
            viewHolder.adRoll = (TextView) row.findViewById(R.id.line4);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder)row.getTag();
        }
        Choose card = getItem(position);
        viewHolder.name.setText(card.getApplicant_name());
        viewHolder.father.setText(card.getFather_name());
        viewHolder.merit.setText(card.getMerit_position()+"");
        viewHolder.adRoll.setText(card.getAd_exam_roll()+"");
        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}