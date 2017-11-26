package onc.appproject.firstonc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by js on 2017-03-18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    static Team jointeam = null;
    private static ArrayList<Team> mDataset; //MainActivity에 item class를 정의해 놓았음
    static FirebaseAuth mFirebaseAuth;
    static FirebaseUser mFirebaseUser;
    static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    static DatabaseReference databaseReference = firebaseDatabase.getReference();
    static ArrayList<Team> teamArraylist = new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // 사용될 항목들 선언
        public TextView mName;
        public TextView mAge;
        //public TextView mteamleadername;
        public ImageView mPhoto;
        public TextView mOfficialInfo;
        public String name;
        String teamname;

        public ViewHolder(View v) {
            super(v);
            mName = (TextView) v.findViewById(R.id.info_name);
            mAge = (TextView) v.findViewById(R.id.info_region);
            teamname = mName.getText().toString();
            //mteamleadername = (TextView) v.findViewById(R.id.teamleadername);
            v.setOnClickListener(this);

        }
        @Override
        public void onClick(View v){
            mFirebaseAuth = FirebaseAuth.getInstance();
            mFirebaseUser =  mFirebaseAuth.getCurrentUser();
            teamArraylist = new ArrayList<>();
            dialogshow(v,getAdapterPosition(),teamname);
        }
    }

    // 생성자 - 넘어 오는 데이터파입에 유의해야 한다.
    public MyAdapter(ArrayList<Team> myDataset) {
        mDataset = myDataset;
    }


    //뷰홀더
    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mName.setText(mDataset.get(position).getTeamName());
        holder.mAge.setText(String.valueOf(mDataset.get(position).getTeamregion())); //int를 가져온다는점 유의
        //holder.mteamleadername.setText(String.valueOf(mDataset.get(position).getTeamleader()));
        //holder.mOfficialInfo.setText();
        //holder.mPhoto.setImageBitmap(mDataset.get(position).getPhoto()); //첨부된 이미지를 연결해줘야 하는데 이건 또 복잡하다. 이건 나중에...
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //static void dialogshow(View v,int position, String teamname,ArrayList<Team> mDataset){
    static void dialogshow(View v,int position, String teamname){
        AlertDialog.Builder  builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("팀 가입 선택");
        builder.setMessage("해당 팀으로 팀 가입하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(v.getContext(),mDataset.get(position).getTeamName()+" 팀으로 가입 신청이 완료됬습니다.",Toast.LENGTH_LONG).show();
                        //Toast.makeText(v.getContext(),mFirebaseUser.getEmail(),Toast.LENGTH_LONG).show();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference databaseRef = database.getReference("team");
                        String key = null;
                        databaseRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                                    //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                                    //하위키들의 value를 어떻게 가져오느냐???
                                    Team readTeam= fileSnapshot.getValue(Team.class);
                                    teamArraylist.add(readTeam);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        for(Team searchteam : teamArraylist)
                        {
                            if(searchteam.getTeamName().equals(mDataset.get(position).getTeamName()))
                            {
                                jointeam = searchteam ;
                                break;
                            }
                        }
                        Toast.makeText(v.getContext(),jointeam.getTeamName(),Toast.LENGTH_LONG).show();//오류남.
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
