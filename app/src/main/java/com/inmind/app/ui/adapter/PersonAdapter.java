package com.inmind.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inmind.app.R;
import com.inmind.app.common.ViewUtil;
import com.inmind.app.common.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixiang on 2017/9/5.
 */
public final class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder>{
    private Context mContext;
    private List<Person> mData = new ArrayList<>();
    private OnItemClickListener<Person> mOnItemClickListener;

    public PersonAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public PersonHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(mContext).inflate(R.layout.item_person, parent, false);
        PersonHolder holder = new PersonHolder(item);
        return holder;
    }

    @Override
    public void onBindViewHolder(PersonHolder holder, int position){
        holder.setItemInfo(mData.get(position));
    }

    @Override
    public int getItemCount(){
        return mData.size();
    }

    public void setList(List<Person> list){
        mData.clear();
        if(list != null && !list.isEmpty()){
            mData.addAll(list);
        }
        notifyDataSetChanged();
    }

    class PersonHolder extends RecyclerView.ViewHolder{
        View itemView;
        TextView tvName, tvBirthday, tvRemainDays, tvDayChar;
        Person person;

        public PersonHolder(View itemView){
            super(itemView);
            this.itemView = itemView;
            tvName = ViewUtil.findView(itemView, R.id.tv_name);
            tvBirthday = ViewUtil.findView(itemView, R.id.tv_date);
            tvRemainDays = ViewUtil.findView(itemView, R.id.tv_remain_days);
            tvDayChar = ViewUtil.findView(itemView, R.id.tv_day_char);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(person);
                    }
                }
            });
        }

        public void setItemInfo(Person person){
            this.person = person;
//            tvName.setText(person.nickName);
            tvName.setText("name "+person.id);
            tvBirthday.setText(person.birthdayDateText);
            long days = person.remainDays;
            String dayStr;
            if(days != 0){
                dayStr = String.valueOf(days);
                tvDayChar.setVisibility(View.VISIBLE);
            }else{
                dayStr = "今天";
                tvDayChar.setVisibility(View.GONE);
            }
            tvRemainDays.setText(dayStr);
        }
    }

    public void setOnItemClickListener(OnItemClickListener<Person> listener){
        this.mOnItemClickListener = listener;
    }
}
