package com.example.projectandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectandroid.R;
import com.example.projectandroid.activity.GioHangActivity;
import com.example.projectandroid.activity.MainActivity;
import com.example.projectandroid.ultil.GioHang;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> gioHangArrayListArrayList;

    public GioHangAdapter(Context context, ArrayList<GioHang> loaispArrayList) {
        this.context = context;
        this.gioHangArrayListArrayList = loaispArrayList;
    }

    @Override
    public int getCount() {
        return gioHangArrayListArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return gioHangArrayListArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHoder {
        ImageView imgGioHang;
        TextView txtTenSpGioHang, txtGiaSpGioHang;
        Button btnMin;
        Button btnMax;
        Button btnValue;
        Button btndeletez;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;

        if (view == null) {
            viewHoder = new ViewHoder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_giohang, null);
            viewHoder.imgGioHang = view.findViewById(R.id.imgGioHang);
            viewHoder.txtTenSpGioHang = view.findViewById(R.id.txtTenSpGioHang);
            viewHoder.txtGiaSpGioHang = view.findViewById(R.id.txtGiaSpGioHang);

            viewHoder.btnMin = view.findViewById(R.id.btnMin);
            viewHoder.btnMax = view.findViewById(R.id.btnMax);
            viewHoder.btnValue = view.findViewById(R.id.btnValue);
            viewHoder.btndeletez = view.findViewById(R.id.btndelete);


            view.setTag(viewHoder);

        } else {
            viewHoder = (ViewHoder) view.getTag();

        }
        viewHoder.btndeletez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.gioHangArrayList.remove(i);
                GioHangActivity.CactOnItemListView();
            }
        });


        final GioHang gioHang = gioHangArrayListArrayList.get(i);
        viewHoder.txtTenSpGioHang.setText(gioHang.getTensp());

        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(Integer.parseInt(gioHang.getGiasp()));
        viewHoder.txtGiaSpGioHang.setText(str1);
        Picasso.with(context).load(gioHang.getHinh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(viewHoder.imgGioHang);
        viewHoder.btnValue.setText(gioHang.getSoluongsp() + "");
        final int sl = Integer.parseInt(viewHoder.btnValue.getText().toString());
        if (sl >= 10) {
            viewHoder.btnMax.setVisibility(View.INVISIBLE);
            viewHoder.btnMin.setVisibility(View.VISIBLE);
        } else {
            if (sl <= 1) {
                viewHoder.btnMax.setVisibility(View.VISIBLE);
                viewHoder.btnMin.setVisibility(View.INVISIBLE);

            } else {
                if (sl > 1 && sl < 10) {
                    viewHoder.btnMax.setVisibility(View.VISIBLE);
                    viewHoder.btnMin.setVisibility(View.VISIBLE);
                }
            }
        }


        final ViewHoder finalViewHoder = viewHoder;
        viewHoder.btnMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int soluong = gioHang.getSoluongsp() + 1;
                MainActivity.gioHangArrayList.get(i).setSoluongsp(soluong);
                finalViewHoder.btnValue.setText(soluong + "");
                int giacu = Integer.parseInt(gioHang.getGiasp()) / (soluong - 1);
                long giamoi = giacu * soluong;
                MainActivity.gioHangArrayList.get(i).setGiasp(giamoi + "");
                GioHangActivity.eventUltil();

                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                String str1 = currencyVN.format(Integer.parseInt(giamoi + ""));

                finalViewHoder.txtGiaSpGioHang.setText(str1);

                if (soluong >= 10) {
                    finalViewHoder.btnMax.setVisibility(View.INVISIBLE);
                    finalViewHoder.btnMin.setVisibility(View.VISIBLE);
                } else {
                    if (soluong <= 1) {
                        finalViewHoder.btnMax.setVisibility(View.VISIBLE);
                        finalViewHoder.btnMin.setVisibility(View.INVISIBLE);

                    } else {
                        if (soluong > 1 && soluong < 10) {
                            finalViewHoder.btnMax.setVisibility(View.VISIBLE);
                            finalViewHoder.btnMin.setVisibility(View.VISIBLE);
                        }

                    }
                }
            }
        });
        viewHoder.btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int soluong = gioHang.getSoluongsp() - 1;
                MainActivity.gioHangArrayList.get(i).setSoluongsp(soluong);
                finalViewHoder.btnValue.setText(soluong + "");
                int giacu = Integer.parseInt(gioHang.getGiasp()) / (soluong + 1);
                long giamoi = giacu * soluong;
                MainActivity.gioHangArrayList.get(i).setGiasp(giamoi + "");
                GioHangActivity.eventUltil();
                Locale localeVN = new Locale("vi", "VN");
                NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
                String str1 = currencyVN.format(Integer.parseInt(giamoi + ""));

                finalViewHoder.txtGiaSpGioHang.setText(str1);

                if (soluong >= 10) {
                    finalViewHoder.btnMax.setVisibility(View.INVISIBLE);
                    finalViewHoder.btnMin.setVisibility(View.VISIBLE);
                } else {
                    if (soluong <= 1) {
                        finalViewHoder.btnMax.setVisibility(View.VISIBLE);
                        finalViewHoder.btnMin.setVisibility(View.INVISIBLE);

                    } else {
                        if (soluong > 1 && soluong < 10) {
                            finalViewHoder.btnMax.setVisibility(View.VISIBLE);
                            finalViewHoder.btnMin.setVisibility(View.VISIBLE);
                        }

                    }
                }
            }
        });


        return view;
    }
}
