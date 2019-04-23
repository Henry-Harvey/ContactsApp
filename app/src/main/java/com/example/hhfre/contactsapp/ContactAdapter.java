package com.example.hhfre.contactsapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class ContactAdapter extends BaseAdapter {

    Activity mActivity;
    List<BaseContact> filteredList;

    public ContactAdapter(Activity mActivity, List<BaseContact> filteredList) {
        this.mActivity = mActivity;
        this.filteredList = filteredList;

    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public BaseContact getItem(int position) {
        return filteredList.get(position);
    }

    //Do not need
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View onePersonLine;
        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onePersonLine = inflater.inflate(R.layout.one_line_contact, parent, false);

        TextView tv_firstName = onePersonLine.findViewById(R.id.tv_firstName);
        TextView tv_lastName = onePersonLine.findViewById(R.id.tv_lastName);
        TextView tv_bp = onePersonLine.findViewById(R.id.tv_bp);
        ImageView iv_icon = onePersonLine.findViewById(R.id.iv_icon);

        BaseContact c = this.getItem(position);

        tv_firstName.setText(c.getFirstName());
        tv_lastName.setText(c.getLastName());
        if (c.getClass() == BusinessContact.class) {
            tv_bp.setText("B");
        } else {
            tv_bp.setText("P");
        }

        if (c.getPictureNumber().startsWith("/storage")){
            Glide.with(mActivity).load(new File(c.getPictureNumber())).into(iv_icon);
        }
        else{
            Glide.with(mActivity).load(Uri.parse(c.getPictureNumber())).into(iv_icon);
        }

        return onePersonLine;
    }

}
