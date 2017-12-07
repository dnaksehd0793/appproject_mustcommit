package onc.appproject.firstonc;


import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class OneFragment extends Fragment{
    TextView newsView;

    public OneFragment() {
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

        View rootView = inflater.inflate(R.layout.fragment_one, container, false);
        newsView = (TextView)rootView.findViewById(R.id.newsView);
       /* String newsstring = "'김정은 23점' 우리은행, KEB하나은행에 70:57 승리" +
                "농구 대표팀 김종규, 왼무릎 인대 파열…6주 이상 결장 불가피" +
                "‘농구월드컵 1승 1패’ 허재호, 희망을 보다" +
                "앤써니, 정규시즌 누적 득점 23위 등극!" +
                "`타운스 32점` 미네소타, 부커 빠진 피닉스 제압";
        newsView.setText(newsstring);*/
        newsView.setSelected(true);

        Button button1 = (Button)rootView.findViewById(R.id.newsIcon1);
        Button button2 = (Button)rootView.findViewById(R.id.newsIcon2);
        Button button3 = (Button)rootView.findViewById(R.id.newsIcon3);
        Button button4 = (Button)rootView.findViewById(R.id.newsIcon4);



        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.sports.naver.com/basketball/index.nhn"));
                //http://www.koreabasketball.or.kr/servlets/org/Main 농구협회
              //  getActivity().finish();
                //Intent intent = new Intent(getActivity(), createTeam.class);
                //Intent intent = new Intent(getActivity(),TeamInfoActivity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sports.media.daum.net/sports/basketvolley/"));
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://sports.news.nate.com/basketvolley/"));
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nba.com/news#/"));
                startActivity(intent);
            }
        });




      /*  VideoView vv = (VideoView)rootView.findViewById(R.id.videoView1);

        // MediaController : 특정 View 위에서 작동하는 미디어 컨트롤러 객체
        MediaController mc = new MediaController(rootView.getContext());
        vv.setMediaController(mc); // Video View 에 사용할 컨트롤러 지정

        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath(); // 기본적인 절대경로 얻어오기
        vv.setVideoPath(path+"/DCIM/INSTADOWN/Bas.mp4");

        vv.requestFocus(); // 포커스 얻어오기
        vv.start(); // 동영상 재생*/

        return rootView;
    }
}

