package onc.appproject.firstonc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ThreeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<League> itemArrayList;

    FirebaseDatabase database;
    DatabaseReference myRef;
    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.overrideFont(getActivity(), "SERIF", "fonts/Roboto-Regular.ttf");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_three, container, false);

        itemArrayList = new ArrayList<>();
        //itemArrayList.add(new League("a","b","c","d","e",new User("a","b")));
        //itemArrayList.add(new League("a","b","c","d","e",new User("a","b")));
        mRecyclerView = (RecyclerView)  rootView.findViewById(R.id.my_recycler_view2);
        mRecyclerView.setHasFixedSize(true);//옵션
        //Linear layout manager 사용
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //어답터 세팅
        mAdapter = new MyAdapter2(itemArrayList); //스트링 배열 데이터 인자로
        mRecyclerView.setAdapter(mAdapter);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference("league");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                    //MyFiles filename = (MyFiles) fileSnapshot.getValue(MyFiles.class);
                    //하위키들의 value를 어떻게 가져오느냐???
                    League readTeam= fileSnapshot.getValue(League.class);
                    itemArrayList.add(readTeam);
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FloatingActionButton button = (FloatingActionButton)rootView.findViewById(R.id.createLeague);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                updetail();
            }
        });
        return rootView;
    }
    public void updetail()
    {
        getActivity().finish();
        Intent intent = new Intent(getActivity(), createLeague.class);
        startActivity(intent);
    }
}
