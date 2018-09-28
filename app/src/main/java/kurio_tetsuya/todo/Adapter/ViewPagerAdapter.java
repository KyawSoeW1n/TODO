package kurio_tetsuya.todo.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import kurio_tetsuya.todo.Presenter.MainPresenter;
import kurio_tetsuya.todo.R;

public class ViewPagerAdapter extends PagerAdapter {
//    private final List<Presenter> mPresenterList = new ArrayList<>();
    View v;
    RecyclerView rc;
    private MainPresenter mainPresenter;
    Context context;
    public ViewPagerAdapter(Context c){
        this.context=c;
    }
    private Context mContext;



    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.task_list_layout, collection, false);
        rc=layout.findViewById(R.id.recyclerview_tasks);
//        ImageView imageView = layout.findViewById(R.id.image_view);
        switch (position){
            case 0:
                    mainPresenter.getTasks("To Do");
                break;
            case 1:
                mainPresenter.getTasks("Doing");
                break;
            case 2:
                mainPresenter.getTasks("Done");
                break;
        }

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return "";
    }

}