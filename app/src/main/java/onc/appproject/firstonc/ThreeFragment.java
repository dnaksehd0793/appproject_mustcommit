package onc.appproject.firstonc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ThreeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    //private static ArrayList<Team> itemArrayList = new ArrayList<>(); //대회로 변경 필요!
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
        //return inflater.inflate(R.layout.fragment_three, container, false);
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
