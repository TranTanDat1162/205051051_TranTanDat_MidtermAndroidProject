package edu.uef.thithuchanh;


import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;


public class SubActivity extends AppCompatActivity {
    Button btnBack;
    private ListView lv;
    static ArrayList<String> foodNames = new ArrayList<>();
    static ArrayList<Integer> foodIcons = new ArrayList<>();
    // Khai báo biến adapter
    SubActivity.myadapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        lv = (ListView) findViewById(R.id.listViewSub);


        // Lấy danh sách các mục đã chọn từ Intent


        // Khởi tạo adapter
        adapter = new myadapter(this, foodNames, foodIcons);
        lv.setAdapter(adapter);


        int count = getIntent().getIntExtra("count", 0);
        int position = getIntent().getIntExtra("position", 0);


        // Xác định vị trí để add vào cuối danh sách
        int insertPos = foodNames.size() + count;


        // Thêm món ăn mới vào cuối ArrayList


        if (insertPos < foodNames.size()) {
            foodNames.add(insertPos, MainActivity.foodName[position]);
            foodIcons.add(insertPos, MainActivity.foodIcon[position]);
        } else {
            foodNames.add(MainActivity.foodName[position]);
            foodIcons.add(MainActivity.foodIcon[position]);
        }


        // Cập nhật adapter
        adapter.notifyDataSetChanged();


        btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
        ArrayList<String> foodNames;
        ArrayList<Integer> foodIcons;


        public myadapter(Context context, ArrayList<String> foodNames, ArrayList<Integer> foodIcons) {
            this.context = context;
            this.foodNames = foodNames;
            this.foodIcons = foodIcons;
        }

        public myadapter(Context c) {
            context = c;
        }


        public int getCount() {
            return foodIcons.size();
        }


        public Object getItem(int position) {
            return foodIcons.get(position);
        }


        public long getItemId(int arg0) {
            return 0;
        }


        public View getView(int arg0, View arg1, ViewGroup arg2) {
            View_An_Item an_item;
            LayoutInflater layoutinflater = ((Activity) context).getLayoutInflater();
            if (arg1 == null) {
                an_item = new SubActivity.View_An_Item();
                arg1 = layoutinflater.inflate(R.layout.list_item_icon, null);
                an_item.textview = (TextView) arg1.findViewById(R.id.textView1);
                an_item.imageview = (ImageView) arg1.findViewById(R.id.imageView1);
                arg1.setTag(an_item);
            } else
                an_item = (SubActivity.View_An_Item) arg1.getTag();
            an_item.imageview.setImageResource(foodIcons.get(arg0));
            an_item.textview.setText(foodNames.get(arg0));


            return arg1;
        }
    }
}
