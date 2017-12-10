package onc.appproject.firstonc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static onc.appproject.firstonc.MyAdapter.dialogshow;
import static onc.appproject.firstonc.MyAdapter.mFirebaseUser;


public class MyAdapter2  extends RecyclerView.Adapter<MyAdapter2.ViewHolder>
{
    private static ArrayList<League> mDataset;
    static ArrayList<League> leagueArraylist = new ArrayList<>();
   static  FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    static User myuser = null;
    static Team myteam = null;
    static String leaguename = null;
    static String myleaguekey = null;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
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
            v.setOnLongClickListener(this);
        }
        @Override
        public void onClick(View v){
            User myuser = DatabaseManager.getUser(mFirebaseUser.getEmail());
            myteam = DatabaseManager.showmyteam(myuser.getUsername());
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    User myuser = DatabaseManager.getUser(mFirebaseUser.getEmail());
                    myteam = DatabaseManager.showmyteam(myuser.getUsername());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            myteam = DatabaseManager.showmyteam(myuser.getUsername());
                        }
                    },200);
                }
            },200);
            leaguename = mDataset.get(getAdapterPosition()).getName();
            myleaguekey = DatabaseManager.getLeague(leaguename);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    myleaguekey = DatabaseManager.getLeague(leaguename);
                }
            },200);
            dialogshow(v,getAdapterPosition(),myleaguekey, myteam );
            leagueArraylist= new ArrayList<>();

        }
        @Override
        public boolean onLongClick(View view) {
            Intent intent = new Intent(view.getContext(),LeagueInfoActivity.class);
            intent.putExtra("l name",mDataset.get(getAdapterPosition()).getName());
            intent.putExtra("l sponsor",mDataset.get(getAdapterPosition()).getSponsor());
            intent.putExtra("l location",mDataset.get(getAdapterPosition()).getRegion());
            intent.putExtra("l date",mDataset.get(getAdapterPosition()).getDate());
            intent.putExtra("l cost",mDataset.get(getAdapterPosition()).getCost());
            intent.putExtra("l teamnum",mDataset.get(getAdapterPosition()).getTeamnumber());
            view.getContext().startActivity(intent);
            return true;
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

    static void dialogshow(View v,int position, String myleague,Team  myteam)
    {
        Toast.makeText(v.getContext(),myleague+"해당리그의 키값입니다.",Toast.LENGTH_LONG).show();
        AlertDialog.Builder  builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("리그 참가 신청");
        builder.setMessage( myteam.getTeamName()+"팀은 해당 대회로 참가 신청하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseRef = firebaseDatabase.getReference("league");
                        databaseRef.child(myleague).child("leaguemember").push().setValue(myteam);
                        /*Toast.makeText(v.getContext(),mDataset.get(position).getTeamName()+"팀으로 가입 신청이 완료되었습니다.",Toast.LENGTH_LONG).show();*/

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
