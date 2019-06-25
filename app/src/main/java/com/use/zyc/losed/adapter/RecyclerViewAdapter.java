package com.use.zyc.losed.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.use.zyc.losed.R;
import com.use.zyc.losed.bean.JsonBean;
import com.use.zyc.losed.util.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<JsonBean> jsonBeans;//25条数据的集合对象

    /**
     *
     * @param jsonBeans
     * 构造函数，将数据集合对象传进来的方法
     */
    public RecyclerViewAdapter(List<JsonBean> jsonBeans) {
        this.jsonBeans = jsonBeans;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //将RecycleView和item布局绑定
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repositories_recyclervie_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //将数据绑定起来
        JsonBean jsonBean = jsonBeans.get(position);
        viewHolder.author.setText(jsonBean.getAuthor());//对应赋值~~~
        viewHolder.description.setText(jsonBean.getDescription());
        viewHolder.fork.setText(jsonBean.getForks()+"");
        viewHolder.star.setText(jsonBean.getStars()+"");
        viewHolder.language.setText(jsonBean.getLanguage());
        //用来加载图片的 因为没有联网先空在这
//        for (int i = 0; i < jsonBean.getBuiltBy().size(); i++) {
//            if (i < jsonBean.getBuiltBy().size()) {
//                Log.i("xxx",i+"");
//                viewHolder.avatars.get(i).setImageResource();
//                viewHolder.avatars.get(i).setVisibility(View.VISIBLE);
//            } else {
//                avatars.get(i).setVisibility(View.GONE);
//            }
//        }
    }


    @Override
    public int getItemCount() {
        return jsonBeans.size();//item的数量就是List的长度
    }

    /**
     * ViewHolder 将自定义item中的控件和RecycleView绑定起来
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        List<RoundedImageView> avatars  ;//自定义圆形图片（有五个所以用List） 用ImageView替代
        TextView author,description,star,fork,language;//对应自定义的item上的控件
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            author=itemView.findViewById(R.id.item_title_topleft);//作者
            description=itemView.findViewById(R.id.item_description_center);//描述
            star=itemView.findViewById(R.id.item_star_bottomleft);//星星
            fork=itemView.findViewById(R.id.item_fork_bottom_center);//叉子
            language=itemView.findViewById(R.id.item_language_bottomright);//语言
            avatars = new ArrayList<>();
            avatars.add((RoundedImageView) itemView.findViewById(R.id.item_avatar_bottom1));//添加五个图片控件
            avatars.add((RoundedImageView) itemView.findViewById(R.id.item_avatar_bottom2));
            avatars.add((RoundedImageView) itemView.findViewById(R.id.item_avatar_bottom3));
            avatars.add((RoundedImageView) itemView.findViewById(R.id.item_avatar_bottom4));
            avatars.add((RoundedImageView) itemView.findViewById(R.id.item_avatar_bottom5));
        }
    }
}
