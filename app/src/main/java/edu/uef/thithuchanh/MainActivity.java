package edu.uef.thithuchanh;


import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    int count = 0;
    private ListView lv;
    static String[] foodName;
    static int[] foodIcon = {R.drawable._bread, R.drawable.cherry_cheesecake, R.drawable.gingerbread_house, R.drawable._hamburger, R.drawable.sunny_side_up_eggs,
            R.drawable.sunny_side_up_eggs, R.drawable._hamburger, R.drawable.gingerbread_house, R.drawable.cherry_cheesecake, R.drawable._bread};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        lv = (ListView) findViewById(R.id.listViewMain);


        lv.setAdapter(new myadapter(this));


        foodName = getResources().getStringArray(R.array.food_name_string);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String itemList = foodName[position];
                String message = getString(R.string.clicked_message, itemList, position);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();


                // Lưu lại vị trí phần tử được click
                int clickedPosition = position;


                count++;


                // Tạo Intent và truyền vị trí sang SubActivity
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("position", clickedPosition);
                intent.putExtra("count", count);
                startActivity(intent);
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            }
        });


    }

    public static class View_An_Item {
        public ImageView imageview;
        public TextView textview;
    }


    public class myadapter extends BaseAdapter {
        Context context;

        public myadapter(Context c) {
            context = c;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return foodIcon.length;
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return foodIcon[position];
        }

        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @SuppressLint("SuspiciousIndentation")
        public View getView(int arg0, View arg1, ViewGroup arg2) {
            // TODO Auto-generated method stub
            View_An_Item an_item;
            LayoutInflater layoutinflater = ((Activity) context).getLayoutInflater();
            if (arg1 == null) {
                an_item = new View_An_Item();
                arg1 = layoutinflater.inflate(R.layout.list_item_icon, null);
                an_item.textview = (TextView) arg1.findViewById(R.id.textView1);
                an_item.imageview = (ImageView) arg1.findViewById(R.id.imageView1);
                arg1.setTag(an_item);
            } else
                an_item = (View_An_Item) arg1.getTag();
            an_item.imageview.setImageResource(foodIcon[arg0]);
            an_item.textview.setText(foodName[arg0]);


            return arg1;
        }
    }
}
