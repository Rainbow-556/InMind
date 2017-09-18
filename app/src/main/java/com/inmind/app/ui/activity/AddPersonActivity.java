package com.inmind.app.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.inmind.app.R;
import com.inmind.app.common.ViewUtil;
import com.inmind.app.common.entity.Person;
import com.inmind.app.model.PersonRepositoryFileImpl;
import com.inmind.app.mvp.contract.AddPersonContract;
import com.inmind.app.mvp.presenter.AddPersonPresenter;
import com.inmind.app.ui.base.BaseActivity;

/**
 * Created by lixiang on 2017/9/4.
 */
public final class AddPersonActivity extends BaseActivity implements AddPersonContract.IAddPersonView, View.OnClickListener{
    private AddPersonContract.IAddPersonPresenter mAddPersonPresenter;
    private EditText etName, etYear, etMonth, etDay;
    private RadioButton rbSolar, rbLunar;
    private LinearLayout llLeap;
    private CheckBox cbLeap;
    private Person mCurPerson;
    private boolean isEditExistPerson;

    @Override
    protected int getLayoutId(){
        return R.layout.activity_add_person;
    }

    @Override
    protected void initView(){
        mCurPerson = (Person) getIntent().getSerializableExtra("person");
        isEditExistPerson = mCurPerson != null;
        TextView tvTitle = ViewUtil.findView(this, R.id.tv_title);
        tvTitle.setText(isEditExistPerson ? "编辑" : "添加");
        etName = ViewUtil.findView(this, R.id.et_name);
        etYear = ViewUtil.findView(this, R.id.et_year);
        etMonth = ViewUtil.findView(this, R.id.et_month);
        etDay = ViewUtil.findView(this, R.id.et_day);
        rbLunar = ViewUtil.findView(this, R.id.rb_lunar);
        rbSolar = ViewUtil.findView(this, R.id.rb_solar);
        llLeap = ViewUtil.findView(this, R.id.ll_leap);
        cbLeap = ViewUtil.findView(this, R.id.cb_leap);
        ViewUtil.findView(this, R.id.btn_save).setOnClickListener(this);
        rbLunar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked){
                llLeap.setVisibility(checked ? View.VISIBLE : View.INVISIBLE);
                if(!isEditExistPerson){
                    cbLeap.setChecked(false);
                }
            }
        });
        fillPersonInfoForEdit();
    }

    private void fillPersonInfoForEdit(){
        if(!isEditExistPerson){
            return;
        }
        View btnDel = ViewUtil.findView(this, R.id.btn_del);
        btnDel.setVisibility(View.VISIBLE);
        btnDel.setOnClickListener(this);
        etName.setText(mCurPerson.nickName);
        etName.setSelection(mCurPerson.nickName.length());
        rbLunar.setChecked(mCurPerson.isLunar);
        rbSolar.setChecked(!mCurPerson.isLunar);
        cbLeap.setChecked(mCurPerson.isLunar && mCurPerson.lunar.isLeap);
        int year = mCurPerson.isLunar ? mCurPerson.lunar.lunarYear : mCurPerson.solar.solarYear;
        int month = mCurPerson.isLunar ? mCurPerson.lunar.lunarMonth : mCurPerson.solar.solarMonth;
        int day = mCurPerson.isLunar ? mCurPerson.lunar.lunarDay : mCurPerson.solar.solarDay;
        etYear.setText(String.valueOf(year));
        etMonth.setText(String.valueOf(month));
        etDay.setText(String.valueOf(day));
    }

    @Override
    protected void initPresenter(){
        mAddPersonPresenter = new AddPersonPresenter(new PersonRepositoryFileImpl());
        mAddPersonPresenter.attachView(this);
    }

    @Override
    protected void destroyPresenter(){
        mAddPersonPresenter.detachView();
    }

    @Override
    public void showLoading(boolean cancelable){
        showLoadingInner(cancelable);
    }

    @Override
    public void hideLoading(){
        hideLoadingInner();
    }

    @Override
    public void showToast(String msg){
        showToastInner(msg);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_save:
                addPerson();
                break;
            case R.id.btn_del:
                delPerson();
                break;
        }
    }

    private void delPerson(){
        if(!isEditExistPerson){
            return;
        }
        mAddPersonPresenter.deletePerson(mCurPerson);
    }

    private void addPerson(){
        String name = etName.getText().toString();
        if(TextUtils.isEmpty(name)){
            showToastInner("name is empty");
            return;
        }
        String yearStr = etYear.getText().toString();
        String monthStr = etMonth.getText().toString();
        String dayStr = etDay.getText().toString();
        if(TextUtils.isEmpty(yearStr) || TextUtils.isEmpty(monthStr) || TextUtils.isEmpty(dayStr)){
            showToastInner("year, month or day is empty");
            return;
        }
        int year = Integer.parseInt(yearStr);
        int month = Integer.parseInt(monthStr);
        int day = Integer.parseInt(dayStr);
        Person person = new Person(name, year, month, day, rbLunar.isChecked(), cbLeap.isChecked());
        if(isEditExistPerson){
            person.id = mCurPerson.id;
            mAddPersonPresenter.updatePerson(person);
        }else{
            mAddPersonPresenter.addPerson(person);
        }
    }
}
