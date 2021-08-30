package com.example.jamyfoods;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Hotels extends AppCompatActivity {
    String [] hotelName={"Hotels Ekimeeza", "Kabagarame","Murefu and Friends","Fresh Hotels"};
    String [] hotelDescriptions={"All kinds of Local food","For Quality foods","The Food You want","High quality services"};
    int [] hotelImages={R.drawable.rest1,R.drawable.rest2,R.drawable.rest3,R.drawable.rest4};
    ListView lvHotel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
        lvHotel = findViewById(R.id.lvHotel);
        HotelAdapter hotelAdapter=new HotelAdapter(this,hotelName,hotelImages,hotelDescriptions);
        lvHotel.setAdapter(hotelAdapter);
}
class HotelAdapter  extends ArrayAdapter<String> {
    Context context;
    int[] images;
    String[] hotelName;
    String[] getHotelDescription;
    public HotelAdapter( Context context, String[] hotelName, int [] images, String[] getHotelDescription) {
        super(context, R.layout.single_item, R.id.text1,hotelName);
        this.context=context;
        this.images=images;
        this.hotelName=hotelName;
        this.getHotelDescription=getHotelDescription;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View singleItem =convertView;
        hotelViewHolder holder=null;
        if(singleItem==null){
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            singleItem= layoutInflater.inflate(R.layout.single_item,parent,false);
            holder =new hotelViewHolder(singleItem);
            singleItem.setTag(holder);
        }
        else {
            holder = (hotelViewHolder) singleItem.getTag();
        }
        holder.itemImage.setImageResource(images[position]);
        holder.hotelTitle.setText(hotelName[position]);
        holder.HotelDescription.setText(getHotelDescription[position]);
        singleItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Hotels.this,FoodTypes.class);
                intent.putExtra("hotelName",hotelName[position]);
                intent.putExtra("hotelDescription",getHotelDescription[position]);
                intent.putExtra("hotelImages",images[position]);
                startActivity(intent);
            }
        });
        return singleItem;
    }


}

class hotelViewHolder {
    ImageView itemImage;
    TextView hotelTitle;
    TextView HotelDescription;
    hotelViewHolder(View v){
        itemImage=v.findViewById(R.id.imageView);
        hotelTitle=v.findViewById(R.id.text1);
        HotelDescription=v.findViewById(R.id.text2);
    }
}

}
