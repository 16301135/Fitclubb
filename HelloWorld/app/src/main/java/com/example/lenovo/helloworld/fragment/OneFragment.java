package com.example.lenovo.helloworld.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.helloworld.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/10/9.
 */
public class OneFragment extends Fragment {
    private View view;
    //viewpager相关内容
    private ViewPager mVp;
    private List<ImageView> mList ;
    private TextView tvTitle;
    private String[] titles;//标题数组

    private int[] images = {R.mipmap.qiandao,R.mipmap.chongzhi,R.mipmap.xiaoxi,R.mipmap.jiahao};

    //gridview相关内容
    private GridView gv;
    private List<Map<String,Object>> list;//设置数据源
    private BaseAdapter gv_adapter;
    public OneFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.one_layout,container,false);
        mVp = (ViewPager) view.findViewById(R.id.vp);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);

        int[] imageRes = getImageResource();
        titles = getTitles();


        mList = new ArrayList<ImageView>();
        //标题初始化
        tvTitle.setText(titles[0]);
        for(int i = 0;i<imageRes.length;i++){

            ImageView iv = new ImageView(view.getContext());
            iv.setBackgroundResource(imageRes[i]);
            mList.add(iv);
        }
        mVp.setAdapter(new MyAdapter());

        mVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {




            //当某个子试图被选择后调用的方法
            public void onPageSelected(int position) {
                //改变textview文本内容
                tvTitle.setText(titles[position]);

            }


        });
        gv = view.findViewById(R.id.gv);
        list = new ArrayList<Map<String,Object>>();
        String [] str = {"签到","充值","消息","更多"};
        for(int i =0;i<images.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("img",images[i]);
            map.put("text",str[i]);
            list.add(map);
        }
        //适配器
        MyBaseAdapter gv_adapter = new MyBaseAdapter(getActivity());
        //添加控件适配器
        gv.setAdapter(gv_adapter);
        //添加gridview的监听事件

        return view;
    }

    //创建一个新闻标题数组
    private String[] getTitles() {
        return new String[]{"增肌","塑形","有氧操","动感单车"};
    }

    private int[] getImageResource() {
        return new int[]{R.mipmap.a,R.mipmap.b,R.mipmap.c,R.mipmap.d};
    }

    class MyBaseAdapter extends BaseAdapter {
        private Context context;
        public MyBaseAdapter(Context context){
            this.context = context;
        }
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.grid_item,null);
                holder = new ViewHolder();
                holder.iv=(ImageView) convertView.findViewById(R.id.imageView);
                holder.tv = (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(holder);


            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText((CharSequence)list.get(position).get("text"));
            holder.iv.setImageResource((Integer)list.get(position).get("img"));
            return convertView;
        }
    }
    static class ViewHolder{
        ImageView iv;
        TextView tv;
    }





    //适配器
    class MyAdapter extends PagerAdapter {
        //判断viewpager中可以显示多少个子试图
        @Override
        public int getCount() {
            return mList != null ?mList.size() : 0;
        }
        //判断是否需要重新生成新的子视图
        public boolean isViewFromObject(View view , Object obj){

            return view == obj;
        }
        //产生一个新的视图
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position));
            return mList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }







    }
}
