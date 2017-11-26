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

import static onc.appproject.firstonc.DatabaseManager.findUserByKey;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>  {
    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference databaseReference = firebaseDatabase.getReference();
    private static ArrayList<Team> mDataset; //MainActivity에 item class를 정의해 놓았음
    static  FirebaseUser mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // 사용될 항목들 선언
        public TextView mName;
        public TextView mAge;
        //public TextView mteamleadername;
        public ImageView mPhoto;
        public TextView mOfficialInfo;
        public String name;
        User myuser = null;
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
           // String tempstring = DatabaseManager.findUserByKey(mFirebaseUser.getUid());
            dialogshow(v,getAdapterPosition());
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

        ViewHolder holder = new ViewHolder(v);
        return holder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mName.setText(mDataset.get(position).getTeamName());
        holder.mAge.setText(String.valueOf(mDataset.get(position).getTeamregion())); //int를 가져온다는점 유의

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    //static void dialogshow(View v,int position, String teamname,ArrayList<Team> mDataset){
    static void dialogshow(View v,int position){
        AlertDialog.Builder  builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("팀 가입 선택");
        builder.setMessage("해당 팀으로 팀 가입하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Team myteam = DatabaseManager.getTeam(mDataset.get(position).getTeamName());
                        databaseReference.child("team").child(myteam.getTeamName()).child("teammember").push().setValue(DatabaseManager.getUser(mFirebaseUser.getEmail()));
                        //databaseReference.child("team").push().setValue(myteam);
                       // Toast.makeText(v.getContext(),myuser.getUsername()+"가 팀에 가입하려는 유저입니다.",Toast.LENGTH_LONG).show();
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
