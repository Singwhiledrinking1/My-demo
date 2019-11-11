package com.sdbi.a1713640153;

        import android.support.annotation.NonNull;
        import android.support.v4.view.PagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.view.View;
        import android.view.ViewGroup;

        import java.util.List;

public class mynewAdapter extends PagerAdapter {
    //viewpager的适配器
    //返回view的个数
    private List<View> views;
    public mynewAdapter(List<View> views)
    {
        this.views=views;
    }

    public int getCount(){
        return views.size();
    }
    //用以
    @Override
    public boolean isViewFromObject(@NonNull View arg0, @NonNull Object arg1) {
        return arg0==arg1;

    }
    //销毁view的方法
    @Override
    public void destroyItem(ViewGroup container,int position,Object object)
    {
        ((ViewPager)container).removeView(views.get(position));
    }
    //创建view的方法
    @Override
    public  Object instantiateItem(ViewGroup container, int position)
    {
        View v=views.get(position);

        ViewGroup parent = (ViewGroup) v.getParent();
        if (parent != null) {

            parent.removeAllViews();

        }
        ((ViewPager)container).addView(views.get(position));
        return views.get(position);
    }


}
