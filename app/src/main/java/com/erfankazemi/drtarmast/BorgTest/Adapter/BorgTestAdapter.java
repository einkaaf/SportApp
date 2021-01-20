package com.erfankazemi.drtarmast.BorgTest.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.erfankazemi.drtarmast.R;
import com.erfankazemi.drtarmast.Util.Util;

import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class BorgTestAdapter extends RecyclerView.Adapter<BorgTestAdapter.MyViewHolder> {
  private Context context;
  private List<String> descriptions;
  private List<String> descriptions2;
  private List<String> colors = Arrays.asList(
    "#B2EBF2",
    "#80DEEA",
    "#4DD0E1",
    "#B2DFDB",
    "#80CBC4",
    "#FFF176",
    "#FFD54F",
    "#FFCA28",
    "#F4511E",
    "#ff1744");


  public BorgTestAdapter(Context context, List<String> descriptions,List<String> descriptions2) {
    this.context = context;
    this.descriptions = descriptions;
    this.descriptions2 = descriptions2;

  }


  @Override
  public BorgTestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.borg_cat, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(BorgTestAdapter.MyViewHolder holder, int position) {
    holder.number.setText(Util.toPersianNumber("" + (position + 1)));
    holder.desc.setText(descriptions.get(position));
    holder.desc2.setText(descriptions2.get(position));
    holder.background.setBackgroundColor(Color.parseColor(colors.get(position)));
  }

  @Override
  public int getItemCount() {
    return descriptions.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView number;
    TextView desc;
    TextView desc2;
    LinearLayout background;

    public MyViewHolder(final View itemView) {
      super(itemView);
      number = itemView.findViewById(R.id.catnum);
      desc = itemView.findViewById(R.id.catdesc);
      desc2 = itemView.findViewById(R.id.catdesc2);
      background = itemView.findViewById(R.id.borgCatBackground);

      background.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
          final int IDClick = getAdapterPosition();
          Toast.makeText(context, "" + IDClick, Toast.LENGTH_SHORT).show();
//          switch (IDClick) {
//            case 0:
//              break;
//            default:
//              break;
//          }
        }
      });
    }
  }
}