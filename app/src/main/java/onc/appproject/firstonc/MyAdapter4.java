package onc.appproject.firstonc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter4 extends RecyclerView.Adapter<MyAdapter4.ItemViewHolder> {
    ArrayList<Team> mItems;

    public MyAdapter4(ArrayList<Team> items){
        mItems = items;
    }


    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lmembercard,parent,false);
        return new ItemViewHolder(view);
    }


    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mNameTv.setText(mItems.get(position).getTeamName());
        holder.msexTv.setText(mItems.get(position).getTeamleader().getUsername());
        holder.mheightTv.setText(mItems.get(position).getTeamregion());
    }

    // 데이터 셋의 크기를 리턴해줍니다.
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    // 커스텀 뷰홀더
// item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView mNameTv;
        private TextView msexTv;
        private TextView mheightTv;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mNameTv = (TextView) itemView.findViewById(R.id.lteam_name);
            msexTv = (TextView) itemView.findViewById(R.id.lteam_leadername);
            mheightTv = (TextView) itemView.findViewById(R.id.lteam_region);
        }
    }
}

