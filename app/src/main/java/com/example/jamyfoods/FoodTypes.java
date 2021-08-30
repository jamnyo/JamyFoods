package com.example.jamyfoods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FoodTypes extends AppCompatActivity {
      String Images;
      String hotelName;
      String getHotelDescription;
    String [] foodName={"BreakFast", "Lunch","Evening Dinner","Supper"};
    String [] foodDescriptions={"From 7:00am-11:00am","1:00pm-2:30pm","From 04:00pm-05:30pm","from 7:00pm-3:00pm"};
    int [] foodImages={R.drawable.meal2,R.drawable.matooke,R.drawable.tea,R.drawable.meal6};
    ListView lvfood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_types);
        Images=getIntent().getStringExtra("hotelImages");
        getHotelDescription=getIntent().getStringExtra("hotelDescriptions");
        hotelName = getIntent().getStringExtra("hotelName");
        lvfood = findViewById(R.id.lvfood);
        FoodTypes.FoodAdapter hotelAdapter=new FoodTypes.FoodAdapter(this,foodName,foodImages,foodDescriptions);
        lvfood.setAdapter(hotelAdapter);
    }
    class FoodAdapter  extends ArrayAdapter<String> {
        Context context;
        int[] images;
        String[] foodName;
        String[] getFoodDescription;
        public FoodAdapter( Context context, String[] foodName, int [] images, String[] getFoodDescription) {
            super(context, R.layout.single_items, R.id.text1,foodName);
            this.context=context;
            this.images=images;
            this.foodName=foodName;
            this.getFoodDescription=getFoodDescription;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View singleItem =convertView;
            foodTypesViewHolder holder=null;
            if(singleItem==null){
                LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                singleItem= layoutInflater.inflate(R.layout.single_items,parent,false);
                holder =new foodTypesViewHolder(singleItem);
                singleItem.setTag(holder);
            }
            else {
                holder = (foodTypesViewHolder) singleItem.getTag();
            }
            holder.itemImage.setImageResource(images[position]);
            holder.foodTitle.setText(foodName[position]);
            holder.FoodDescription.setText(getFoodDescription[position]);
            singleItem.setOnClickListener(new AdapterView.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(position == 0){
                        Intent intent= new Intent(FoodTypes.this,Breakfast.class);
                        startActivity(intent);
                    }
                    if(position == 1){
                        Intent intent= new Intent(FoodTypes.this,Lunch.class);
                        startActivity(intent);
                    }
                    if(position == 2){
                        Intent intent= new Intent(FoodTypes.this,EveningDinner.class);
                        startActivity(intent);
                    }
                    if(position == 3){
                        Intent intent= new Intent(FoodTypes.this,Supper.class);
                        startActivity(intent);
                    }
                }

                /*@Override
                public void onClick(AdapterView<?> parent,View view,int position, long id) {


                    Intent intent=new Intent(FoodTypes.this,Breakfast.class);
                    intent.putExtra("foodName",foodName[position]);
                    intent.putExtra("foodDescription",getFoodDescription[position]);
                    intent.putExtra("foodImages",images[position]);
                    startActivity(intent);

                }*/
            });
            return singleItem;
        }


    }

    class foodTypesViewHolder {
        ImageView itemImage;
        TextView foodTitle;
        TextView FoodDescription;
        foodTypesViewHolder(View v){
            itemImage=v.findViewById(R.id.imageViews);
           foodTitle=v.findViewById(R.id.text1);
            FoodDescription=v.findViewById(R.id.text2);
        }
    }
}
