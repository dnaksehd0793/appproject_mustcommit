package onc.appproject.firstonc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static onc.appproject.firstonc.MyAdapter.dialogshow;


public class MyAdapter2  extends RecyclerView.Adapter<MyAdapter2.ViewHolder>
{
    private static ArrayList<League> mDataset;
    static ArrayList<League> leagueArraylist = new ArrayList<>();
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // 사용될 항목들 선언
        public TextView mName;
        public TextView mAge;
        public TextView mEmail;

        String leaguename;

        public ViewHolder(View v) {
            super(v);

            mName = (TextView) v.findViewById(R.id.info_Lname);
            mAge = (TextView)v.findViewById(R.id.info_Ldate);
            mEmail = (TextView)v.findViewById(R.id.info_Lregion);

            leaguename = mName.getText().toString();

            v.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            leagueArraylist= new ArrayList<>();
            dialogshow(v,getAdapterPosition(),leaguename);
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
        holder.mAge.setText(mDataset.get(position).getDate());
        holder.mEmail.setText(mDataset.get(position).getRegion());
        /*holder.mAge.setText(String.valueOf(mDataset.get(position).getSchedule())); //int를 가져온다는점 유의
        holder.mEmail.setText(mDataset.get(position).getSponsor());
        holder.mEmail2.setText(mDataset.get(position).getJoincost());
        holder.mEmail3.setText(mDataset.get(position).getTeamnumber());*/
        //holder.mPhoto.setImageBitmap(mDataset.get(position).getPhoto()); //첨부된 이미지를 연결해줘야 하는데 이건 또 복잡하다. 이건 나중에...

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    static void dialogshow(View v,int position, String teamname){
        AlertDialog.Builder  builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("리그 참가 신청");
        builder.setMessage("해당 대회로 참가 신청하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(v.getContext(),mDataset.get(position).getName()+"대회로 참가 신청이 완료됬습니다.",Toast.LENGTH_LONG).show();

                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(v.getContext(),"아니오를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

}
