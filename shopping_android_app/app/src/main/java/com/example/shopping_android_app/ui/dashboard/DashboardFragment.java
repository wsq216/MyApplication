package com.example.shopping_android_app.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.TopicAdapter;
import com.example.shopping_android_app.base.BaseFragment;
import com.example.shopping_android_app.interfaces.topic.Itopic;
import com.example.shopping_android_app.model.home.topic.TopicBean;
import com.example.shopping_android_app.presenter.home.topic.TopicPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DashboardFragment extends BaseFragment<TopicPresenter> implements Itopic.View {
    @BindView(R.id.linear)
    LinearLayout linear;
    //    @BindView(R.id.rv_topic)
//    RecyclerView rvTopic;
    private TopicAdapter topicAdapter;
//    private List<TopicBean.DataBeanX.DataBean> list;

    @Override
    protected TopicPresenter createPersenter() {
        return new TopicPresenter();
    }

    @Override
    protected void initData() {

//        list = new ArrayList<>();

//        rvTopic.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//        topicAdapter = new TopicAdapter(getActivity(),list);
//
//        rvTopic.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
//
//        rvTopic.setAdapter(topicAdapter);

        presenter.getTopicList();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }

    @Override
    public void getTopicList(TopicBean topicBean) {
        if (topicBean != null) {
//            list.addAll(topicBean.getData().getData());
//            topicAdapter.notifyDataSetChanged();
            List<TopicBean.DataBeanX.DataBean> data = topicBean.getData().getData();
            for (int i = 0; i < data.size(); i++) {
                View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.topic_item, null);
                ImageView image = (ImageView) inflate.findViewById(R.id.new_pic_url);
                TextView name = (TextView) inflate.findViewById(R.id.name);
                TextView floor_price = (TextView) inflate.findViewById(R.id.floor_price);
                TextView goods_brief = (TextView) inflate.findViewById(R.id.goods_brief);

                TopicBean.DataBeanX.DataBean bean= data.get(i);

                Glide.with(getActivity()).load(bean.getScene_pic_url()).into(image);
                name.setText(bean.getTitle());
                floor_price.setText("￥"+bean.getPrice_info());
                goods_brief.setText(bean.getSubtitle());

                linear.addView(inflate);
            }
        }
    }


}