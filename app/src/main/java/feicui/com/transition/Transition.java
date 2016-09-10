package feicui.com.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import feicui.com.app.R;
import feicui.com.hk.BaseActivity;

/**
 * @description 布局过渡动画类
 * @author Administrator 2016-08-31
 */

public class Transition extends BaseActivity {
    //被添加的view的容器
    LinearLayout ll_tran_container;

    //当前被添加的按钮的数量
    int i_count;

    //自定义布局过渡动画
    LayoutTransition transition=new LayoutTransition();
    //展示item过渡动画的列表
    ListView lv_transition;


    @Override
    protected int setContent() {
        return R.layout.activity_transition;
    }

    @Override
    protected void initView() {
        ll_tran_container = (LinearLayout) findViewById(R.id.ll_tran_container);
        lv_transition= (ListView) findViewById(R.id.lv_transition);
        //设置数据列表
        setListView();
        //装载过渡动画
        setTransition();
    }

    /**
     * @description 设置数据列表
     */
    private void setListView() {
        //设置列表的适配器
        lv_transition.setAdapter(new TransitionAdapter(this));
        //加载单个item的动画
        Animation rightin = AnimationUtils.loadAnimation(this, R.anim.rightin);
        //根据单个的item动画创建布局过渡动画控制器
        LayoutAnimationController lc = new LayoutAnimationController(rightin);
        //设置动画组延迟
        lc.setDelay(0.3f);
        //设置动画组播放次序
        lc.setOrder(LayoutAnimationController.ORDER_RANDOM);
        //给目标ListView设置item布局过渡动画
        lv_transition.setLayoutAnimation(lc);
    }

    @Override
    protected void setListener() {

    }

    /**
     * @description 向容器中添加按钮,点击后添加
     * @param view 监听的view
     */
    public void addButton(View view){
        i_count++;//数量增加
        //实例化一个按钮
        Button button = new Button(this);
        //按钮设置文本
        button.setText("button" + i_count);
        //取消文本字母大写
        button.setAllCaps(false);
        //实例化布局参数
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //给待添加的按钮添加布局参数
        button.setLayoutParams(params);
        ////添加到容器中,子view大于2个时在第2个位置加入控件，否则直接加入
//        if (i_count>2){
//            ll_tran_container.addView(button,0);
//        }else
            ll_tran_container.addView(button,0);


    }

    /**
     * @description 向容器中移除按钮,点击后触发
     * @param view 监听的view
     */
    public void removeButton(View view){
        //判断当前是否有足够的按钮
        if(i_count > 0){
            //数量递减
            i_count --;
            //删除下标为0的控件
            ll_tran_container.removeViewAt(0);
        }


    }
    /**
     * @descripition 设置布局的过渡动画
     */
    private void setTransition(){
        //给指定容器设置过渡动画
        ll_tran_container.setLayoutTransition(transition);
        //自定义添加view动画
        //target参数填null，最终setAnimator的时候会自动设置
        ValueAnimator appearAnim = ObjectAnimator.ofFloat(null,"rotationY",90f,0f);

        //设置一个默认的播放时间(3000ms)
        appearAnim.setDuration(3000);

        //给过渡动画指定动画类型和动画效果
        transition.setAnimator(LayoutTransition.APPEARING, appearAnim);


        //自定义删除View的动画
        //target参数填null，最终setAnimator的时候会自动设置
        ValueAnimator disappearAnim = ObjectAnimator.ofFloat(null, "rotationY", 0f, 180f, 0f);
        //设置一个默认的播放时间(3000ms)
        disappearAnim.setDuration(3000);
        //给过渡动画指定动画类型和动画效果
        transition.setAnimator(LayoutTransition.DISAPPEARING, disappearAnim);


        //对于CHANGE_APPEARING 和 CHANGE_DISAPPEARING 类型的过渡动画
        //1.必须使用PropertyValuesHolder来构造动画
        //2.必须同时指定left top right bottom属性 否则无法正确显示
        //3.动画结束后也需要将view改为原样，否则会出现BUG
        //4.构建ObjectAnimator时需要设置对象

        //设置CHANGE_APPEARING类型的动画
        PropertyValuesHolder left=PropertyValuesHolder.ofInt("left",0,0);
        PropertyValuesHolder right=PropertyValuesHolder.ofInt("right",0,0);
        PropertyValuesHolder top=PropertyValuesHolder.ofInt("top",0,0);
        PropertyValuesHolder bottom=PropertyValuesHolder.ofInt("bottom",0,0);
        //编写自定义的CHANGE_APPEARING动画
        PropertyValuesHolder ScaleX=PropertyValuesHolder.ofFloat("ScaleX",1f,2f,1f);
        //通过PropertyValuesHolder构建动画
        ValueAnimator cgapAnim=ObjectAnimator.ofPropertyValuesHolder(ll_tran_container,left,right,top,bottom,ScaleX);
        //
        cgapAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ((View)((ObjectAnimator)animation).getTarget()).setScaleX(1f);
            }
        });
        //给过渡动画指定动画类型和动画效果
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING, cgapAnim);
        //设置动画组之间的延迟
        transition.setStagger(LayoutTransition.CHANGE_APPEARING, 30);

        //这里借用了CHANGE_APPEARING的动画效果
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, cgapAnim);
        //设置动画组之间的延迟
        transition.setStagger(LayoutTransition.CHANGE_DISAPPEARING, 30);


    }
}
