package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.net.URI;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    LinearLayout layout_1;
    LinearLayout layout_bottom;
    LinearLayout menu_1;
    LinearLayout menu_2;
    LinearLayout menu_3;


    private FragmentManager fragmentManager;
    private MapFragment mapFragment;

    private DrawerLayout drawerLayout;
    private View drawerView;

    private long backKeyPressedTime = 0;
    private Toast toast;
    private View view;
    private AlertDialog.Builder builder;

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);

        //메뉴 소환 버튼
        layout_1 = findViewById(R.id.layout_1);
        layout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               drawerLayout.openDrawer(drawerView);
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });

        //비상시 대피요령
        menu_1 = findViewById(R.id.menu_1);
        menu_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KnowHowActivity.class);
                startActivity(intent);
            }
        });

        //알림
        menu_2 = findViewById(R.id.menu_2);
        menu_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        //전화걸기
        menu_3 = findViewById(R.id.menu_3);
        menu_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:119"));
                startActivity(intent);
            }
        });

        layout_bottom = findViewById(R.id.layout_bottom);
        layout_bottom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("정말로 종료하시겠습니까?");
                builder.setTitle("종료 알림창")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.setTitle("종료 알림창");
                alert.show();
            }
        });

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });
    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList(){
        list.add("순헌관");
        list.add("행파교수회관");
        list.add("수련교수회관");
        list.add("진리관");
        list.add("명신관");
        list.add("명신신관");
        list.add("행정관");
        list.add("학생회관");
        list.add("숙명여자대학교 대강당");
        list.add("프라임관");
        list.add("르네상스");
        list.add("음악대학");
        list.add("사회교육관");
        list.add("약학대학");
        list.add("미술대학");
        list.add("백주년기념관");
        list.add("중앙도서관");
        list.add("과학관");

    }

    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng location = new LatLng(37.545133, 126.964629);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title("숙명여자대학교");
        markerOptions.snippet("대학교");
        markerOptions.position(location);
        googleMap.addMarker(markerOptions);

        MarkerOptions opt1_1 = new MarkerOptions();
        opt1_1.position(new LatLng(37.546432, 126.964717));
        opt1_1.title("순헌관");
        googleMap.addMarker(opt1_1);

        MarkerOptions opt1_2 = new MarkerOptions();
        opt1_2.position(new LatLng(37.546635, 126.965023));
        opt1_2.title("행파교수회관");
        googleMap.addMarker(opt1_2);

        MarkerOptions opt1_3 = new MarkerOptions();
        opt1_3.position(new LatLng(37.546663, 126.964339));
        opt1_3.title("수련교수회관");
        googleMap.addMarker(opt1_3);

        MarkerOptions opt1_4 = new MarkerOptions();
        opt1_4.position(new LatLng(37.546368, 126.963859));
        opt1_4.title("진리관");
        googleMap.addMarker(opt1_4);

        MarkerOptions opt1_5 = new MarkerOptions();
        opt1_5.position(new LatLng(37.545700, 126.963625));
        opt1_5.title("명신관");
        googleMap.addMarker(opt1_5);

        MarkerOptions opt1_6 = new MarkerOptions();
        opt1_6.position(new LatLng(37.545396, 126.963904));
        opt1_6.title("명신신관");
        googleMap.addMarker(opt1_6);

        MarkerOptions opt1_7 = new MarkerOptions();
        opt1_7.position(new LatLng(37.545414, 126.964545));
        opt1_7.title("행정관");
        googleMap.addMarker(opt1_7);

        MarkerOptions opt1_8 = new MarkerOptions();
        opt1_8.position(new LatLng(37.545459, 126.965055));
        opt1_8.title("학생회관");
        googleMap.addMarker(opt1_8);

        MarkerOptions opt1_9 = new MarkerOptions();
        opt1_9.position(new LatLng(37.545919, 126.965728));
        opt1_9.title("숙명여자대학교 대강당");
        googleMap.addMarker(opt1_9);

        MarkerOptions opt2_1 = new MarkerOptions();
        opt2_1.position(new LatLng(37.544687,126.964465));
        opt2_1.title("프라임관");
        googleMap.addMarker(opt2_1);

        MarkerOptions opt2_2 = new MarkerOptions();
        opt2_2.position(new LatLng(37.544760,126.964062));
        opt2_2.title("르네상스");
        googleMap.addMarker(opt2_2);

        MarkerOptions opt2_3= new MarkerOptions();
        opt2_3.position(new LatLng(37.544270,126.964023));
        opt2_3.title("음악대학");
        googleMap.addMarker(opt2_3);

        MarkerOptions opt2_4= new MarkerOptions();
        opt2_4.position(new LatLng(37.543811,126.963847));
        opt2_4.title("사회교육관");
        googleMap.addMarker(opt2_4);

        MarkerOptions opt2_5= new MarkerOptions();
        opt2_5.position(new LatLng(37.543856,126.964510));
        opt2_5.title("약학대학");
        googleMap.addMarker(opt2_5);

        MarkerOptions opt2_6= new MarkerOptions();
        opt2_6.position(new LatLng(37.544326,126.964906));
        opt2_6.title("미술대학");
        googleMap.addMarker(opt2_6);

        MarkerOptions opt2_7= new MarkerOptions();
        opt2_7.position(new LatLng(37.543775,126.965453));
        opt2_7.title("백주년기념관");
        googleMap.addMarker(opt2_7);

        MarkerOptions opt2_8= new MarkerOptions();
        opt2_8.position(new LatLng(37.544183,126.965894));
        opt2_8.title("중앙도서관");
        googleMap.addMarker(opt2_8);

        MarkerOptions opt2_9= new MarkerOptions();
        opt2_9.position(new LatLng(37.544598,126.966359));
        opt2_9.title("과학관");
        googleMap.addMarker(opt2_9);

        googleMap.setOnMarkerClickListener(this) ;

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 17));


    }


    @Override
    public boolean onMarkerClick(Marker marker) {

        if(marker.getTitle().equals("프라임관"))
        {
            final String[] words = new String[] {"B2층", "B1층", "1층", "2층", "3층"};


                new AlertDialog.Builder(this).setTitle("층수를 선택하세요.").setSingleChoiceItems(words, -1, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "words : " + words[which], Toast.LENGTH_SHORT).show(); } }).setNeutralButton("closed",null).setPositiveButton("OK",null).setNegativeButton("cancel", null).show();


        }
        return true;
    }


}