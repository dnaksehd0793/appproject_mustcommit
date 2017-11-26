package onc.appproject.firstonc;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter2  extends RecyclerView.Adapter<MyAdapter2.ViewHolder>
{
    private static ArrayList<League> mDataset;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // 사용될 항목들 선언
        public TextView mName;
        public TextView mAge;
        public TextView mEmail;
        public TextView mEmail2;
        public TextView mEmail3;
        public ImageView mPhoto;

        public ViewHolder(View v) {
            super(v);

            mName = (TextView) v.findViewById(R.id.info_Lname);
            mAge = (TextView) v.findViewById(R.id.info_Lschedule);
            mEmail = (TextView) v.findViewById(R.id.info_Lsponsor);
            mEmail2 = (TextView) v.findViewById(R.id.info_Lcost);
            mEmail3 = (TextView) v.findViewById(R.id.info_Lteamnumber);
        }
    }

    public MyAdapter2(ArrayList<League> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public MyAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview2, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mName.setText(mDataset.get(position).getName());
        holder.mAge.setText(String.valueOf(mDataset.get(position).getSchedule())); //int를 가져온다는점 유의
        holder.mEmail.setText(mDataset.get(position).getSponsor());
        holder.mEmail2.setText(mDataset.get(position).getJoincost());
        holder.mEmail3.setText(mDataset.get(position).getTeamnumber());
        //holder.mPhoto.setImageBitmap(mDataset.get(position).getPhoto()); //첨부된 이미지를 연결해줘야 하는데 이건 또 복잡하다. 이건 나중에...

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
