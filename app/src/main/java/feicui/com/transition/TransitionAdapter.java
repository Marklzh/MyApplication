package feicui.com.transition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import feicui.com.app.R;


/**
 * 2016/8/31.
 *
 * @description 展示ListView item过渡动画的适配器
 */

public class TransitionAdapter extends BaseAdapter {
    //上下文对象
    Context mContext;

    /**
     * @param context 上下文
     * @description 本适配器构造
     */
    public TransitionAdapter(Context context) {
        mContext = context;
    }

    //注意，此处数据不能过多，如超出屏幕范围产生滚动效果的话，动画可能会失效
    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //此处为了简便未做优化
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载子item的布局并返回
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.item_transition, null);
        return convertView;
    }
}
