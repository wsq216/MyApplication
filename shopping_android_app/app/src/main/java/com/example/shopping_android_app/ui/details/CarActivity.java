package com.example.shopping_android_app.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.example.shopping_android_app.MainActivity;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.adapter.CarImgAdapter;
import com.example.shopping_android_app.adapter.DetailsAdapter;
import com.example.shopping_android_app.base.BaseActivity;
import com.example.shopping_android_app.base.BaseAdapter;
import com.example.shopping_android_app.dao.DbUtils;
import com.example.shopping_android_app.interfaces.home.IDetail;
import com.example.shopping_android_app.model.home.CategoryListBean;
import com.example.shopping_android_app.model.home.details.DetailsBase;
import com.example.shopping_android_app.model.home.details.RelatedBase;
import com.example.shopping_android_app.model.home.shop.AddCarBean;
import com.example.shopping_android_app.model.home.shop.DeleteCarBean;
import com.example.shopping_android_app.presenter.home.DetailsPresenter;
import com.example.shopping_android_app.ui.login.LoginActivity;
import com.example.shopping_android_app.utils.SpUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class CarActivity extends BaseActivity<IDetail.Presenter> implements IDetail.View {

    //    @BindView(R.id.webView)
//    WebView webView;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.txt_assess)
    TextView txtAssess;
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.img_collect)
    ImageView imgCollect;
    @BindView(R.id.layout_collect)
    FrameLayout layoutCollect;
    @BindView(R.id.img_car)
    ImageView imgCar;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.layout_car)
    FrameLayout layoutCar;
    @BindView(R.id.txt_buy)
    TextView txtBuy;
    @BindView(R.id.txt_addCar)
    TextView txtAddCar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.goods_brief)
    TextView goodsBrief;
    @BindView(R.id.retail_price)
    TextView retailPrice;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.linear_common_problem)
    LinearLayout linearCommonProblem;
    @BindView(R.id.layout_shop)
    ConstraintLayout layoutShop;
    @BindView(R.id.linear_attribute)
    LinearLayout linearAttribute;
    @BindView(R.id.details_num)
    ConstraintLayout detailsNum;
    @BindView(R.id.img_comment_hot)
    ImageView imgCommentHot;
    @BindView(R.id.txt_comment_name)
    TextView txtCommentName;
    @BindView(R.id.txt_comment_time)
    TextView txtCommentTime;
    @BindView(R.id.txt_comment_text)
    TextView txtCommentText;
    @BindView(R.id.linear_comment_img)
    LinearLayout linearCommentImg;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;


    private String h5 = "<html>\n" +
            "            <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"/>\n" +
            "            <head>\n" +
            "                <style>\n" +
            "                    p{\n" +
            "                        margin:0px;\n" +
            "                    }\n" +
            "                    img{\n" +
            "                        width:100%;\n" +
            "                        height:auto;\n" +
            "                    }\n" +
            "                </style>\n" +
            "            </head>\n" +
            "            <body>\n" +
            "                word\n" +
            "            </body>\n" +
            "        </html>";
    private PopupWindow popupWindow;
    private TextView num;
    int i = 1;
    private DetailsBase detailsBase1;
    private DetailsBase.DataBeanX.InfoBean info;
    private Realm realm;


    @Override
    protected int getLayout() {
        return R.layout.activity_car;
    }

    @Override
    protected IDetail.Presenter createPrenter() {
        return new DetailsPresenter();
    }

    @Override
    protected void initView() {
//        RealmConfiguration config = new RealmConfiguration.Builder()
//                .name("myrealm.realm") //文件名
//                .schemaVersion(0) //版本号
//                .build();
//        Realm realm = Realm.getInstance(config);
//        Realm.init(this);
//        RealmConfiguration configuration = new RealmConfiguration.Builder()
//                .name("test.realm")
//                .schemaVersion(0)
//                //.deleteRealmIfMigrationNeeded()
//                .build();
//
//        Realm realm = Realm.getInstance(configuration);
//        String str = realm.getPath();
//        Log.i(TAG, str);

        imgCollect.setOnClickListener(this::onClick);
        layoutCar.setOnClickListener(this::onClick);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent.hasExtra("id")) {
            int id = getIntent().getIntExtra("id", 0);
            if (id > 0) {
                presenter.getDetails(id);
                presenter.getRelated(id);
            } else {
                showToast(getString(R.string.tips_error_goodid));
            }
        }

        realm = MainActivity.getRealm();

        RealmResults<DbUtils> user = realm.where(DbUtils.class).findAll();
        List<DbUtils> list = new ArrayList<>();
        list.addAll(user);
        imgCollect.setTag(0);
        if (info != null) {
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getName().equals(info.getName())) {
                    imgCollect.setImageResource(R.mipmap.ic_collect_select);
                    imgCollect.setTag(1);
                    break;
                }
            }
        }

    }


    /**
     * 商品详情数据  h5
     *
     * @param webData
     */
    private void initGoodDetail(String webData) {
//        getHtmlImgs(webData);
        showImage(webData);
//        String content = h5.replace("word", webData);
//        Log.i("TAG", content);
//        webView.loadDataWithBaseURL("about:blank", content, "text/html", "utf-8", null);
    }

    private void getHtmlImgs(String content) {
        String img = "<img[\\s\\S]*?>";
        Pattern pattern = Pattern.compile(img);
        Matcher matcher = pattern.matcher(content);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            String word = matcher.group();
            int start = word.indexOf("\"") + 1;
            int end = word.indexOf("\"");
            String url = word.substring(start, end);
            url = url + ".jpg";
            list.add(url);
        }
        Toast.makeText(this, "" + list.size(), Toast.LENGTH_SHORT).show();

    }

    private void showImage(String goods_desc) {
        List<String> listUrl = new ArrayList<>();

        String str = null;
        String[] image = goods_desc.split("http");
        for (int i = 0; i < image.length; i++) {
            String[] url = image[i].split("jpg");
            if (url.length != 0) {
                for (int j = 0; j < url.length - 1; j++) {
                    str = "http" + url[0] + "jpg";
                    if (!listUrl.contains(str))
                        listUrl.add(str);
                }
            }
            String[] urls = image[i].split("png");
            if (urls.length != 0) {
                for (int j = 0; j < urls.length - 1; j++) {
                    str = "http" + urls[0] + "png";
                    if (!listUrl.contains(str))
                        listUrl.add(str);
                }
            }

        }
        rvImg.setLayoutManager(new LinearLayoutManager(this));
        CarImgAdapter carImgAdapter = new CarImgAdapter(this, listUrl);
        rvImg.setAdapter(carImgAdapter);


        carImgAdapter.addListClick(new BaseAdapter.IListClick() {
            @Override
            public void itemClick(int pos) {
                Intent intent = new Intent(CarActivity.this, BigImageActivity.class);
                intent.putExtra("listUrl", (Serializable) listUrl);
                intent.putExtra("id", pos);
                startActivity(intent);
            }
        });


    }

    private static final String TAG = "CarActivity";
    public static final int RECOMMEND_CAR = 1000; //打开购物车的指令

    @Override
    public void getDetails(DetailsBase detailsBase) {
        detailsBase1 = detailsBase;
        if (detailsBase != null) {
            //详情图【片
            List<DetailsBase.DataBeanX.GalleryBean> gallery = detailsBase.getData().getGallery();
            if (gallery != null) {
                initBanner(gallery);
            } else {
                Log.d(TAG, "gallery: null");
            }
            //商品  大家都再看
            info = detailsBase.getData().getInfo();
            if (info != null) {
                initText(info);
            } else {
                Log.d(TAG, "info: null");
            }

            //h5 商品详情
            String goods_desc = detailsBase.getData().getInfo().getGoods_desc();
            if (goods_desc != null) {
//                initGoodDetail(goods_desc);
                showImage(goods_desc);
            } else {
                Log.d(TAG, "goods_desc: null");
            }
            //常见问题
            List<DetailsBase.DataBeanX.IssueBean> issue = detailsBase.getData().getIssue();
            if (issue.size() > 0) {
                initCommonProblem(issue);
            } else {
                Log.d(TAG, "issue: null");
            }
            //商品规格
            List<DetailsBase.DataBeanX.AttributeBean> attribute = detailsBase.getData().getAttribute();
            if (attribute.size() > 0) {
                initAttribute(attribute);
            } else {
                Log.d(TAG, "attribute: null");
            }
            if (gallery.size() > 0) {
                //选择商品数量
                String galleryBean = gallery.get(0).getImg_url();
                int retail_price = info.getRetail_price();
                initDetail(galleryBean, retail_price);
            }
            //评论
            DetailsBase.DataBeanX.CommentBean comment = detailsBase.getData().getComment();
            if (comment != null) {
                initComment(comment);
            } else {
                Log.d(TAG, "comment: null");
            }
        } else {
            Toast.makeText(this, "请求数据为空", Toast.LENGTH_SHORT).show();
        }

    }

    private void initComment(DetailsBase.DataBeanX.CommentBean comment) {
        txtAssess.setText("评价(" + comment.getCount() + ")");
        txtCommentName.setText(comment.getData().getNickname() + "");
        txtCommentTime.setText(comment.getData().getAdd_time());
        txtCommentText.setText(comment.getData().getContent());
        List<DetailsBase.DataBeanX.CommentBean.DataBean.PicListBean> pic_list = comment.getData().getPic_list();
        if (pic_list != null) {
            for (int i = 0; i < pic_list.size(); i++) {
                View inflate = LayoutInflater.from(this).inflate(R.layout.details_item_comment_img, null);
                ImageView img = inflate.findViewById(R.id.img_comment);
                Glide.with(this).load(pic_list.get(i).getPic_url()).into(img);
                inflate.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
                linearCommentImg.addView(inflate);
            }
        } else {
            Toast.makeText(this, "评价无图片数据", Toast.LENGTH_SHORT).show();
        }
    }

    private void initDetail(String galleryBean, int retail_price) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.details_pop_home, null);
        ImageView img = inflate.findViewById(R.id.img);
        TextView jian = inflate.findViewById(R.id.jian);
        num = inflate.findViewById(R.id.num);
        TextView jia = inflate.findViewById(R.id.jia);
        TextView no = inflate.findViewById(R.id.txt_no);
        TextView price = inflate.findViewById(R.id.txt_price);
        TextView brief = inflate.findViewById(R.id.txt_brief);
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setAttachedInDecor(true);

        detailsNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtAddCar.setTag(1);
                popupWindow.showAtLocation(linearAttribute, Gravity.BOTTOM, 0, 100);
            }
        });
        Glide.with(this).load(galleryBean).into(img);
        price.setText("价格：" + "\t￥" + retail_price);
        brief.setText("已选择：请选择规格数量");
        txtAddCar.setTag(0);
        no.setOnClickListener(this::onClick);
        jian.setOnClickListener(this::onClick);
        jia.setOnClickListener(this::onClick);
        txtAddCar.setOnClickListener(this::onClick);
    }

    @OnClick
    public void onClick(View view) {
        if (!TextUtils.isEmpty(SpUtils.getInstance().getString("token"))) {
            switch (view.getId()) {
                case R.id.img_collect:
                    int tag1 = (int) imgCollect.getTag();
                    if (tag1 == 0) {
                        imgCollect.setImageResource(R.mipmap.ic_collect_select);
                        imgCollect.setTag(1);
                        realm.executeTransaction(new io.realm.Realm.Transaction() {
                            @Override
                            public void execute(io.realm.Realm realm) {
                                DbUtils user = realm.createObject(DbUtils.class);
                                user.setName(info.getName());
                                user.setPrice(info.getRetail_price() + "");
                                user.setUrl(info.getList_pic_url());
                            }
                        });

                        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                    } else {
                        imgCollect.setImageResource(R.mipmap.ic_collect_normal);
                        imgCollect.setTag(0);
                        RealmResults<DbUtils> user = realm.where(DbUtils.class).findAll();
                        realm.executeTransaction(new io.realm.Realm.Transaction() {
                            @Override
                            public void execute(io.realm.Realm realm) {
                                user.get(user.size() - 1).deleteFromRealm();
                            }
                        });
                        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.jian:
                    i--;
                    i = i <= 0 ? 1 : i;
                    if (i > 0) {
                        num.setText(i + "");
                    }
                    break;
                case R.id.jia:
                    i++;
                    if (i > 0) {
                        num.setText(i + "");
                    }

                    break;
                case R.id.layout_car:
                    openGoodCar();
                    break;
                case R.id.txt_no:
                    txtAddCar.setTag(0);
                    popupWindow.dismiss();
                    break;
                case R.id.txt_addCar:
                    int tag = (int) txtAddCar.getTag();
                    int s = Integer.parseInt(txtNumber.getText().toString());
                    int s1 = Integer.parseInt(num.getText().toString());
                    if (tag == 0) {
                        popupWindow.showAtLocation(linearAttribute, Gravity.BOTTOM, 0, 100);
                        txtAddCar.setTag(1);
                    } else {
                        txtNumber.setText(s + s1 + "");
                        popupWindow.dismiss();
                        txtAddCar.setTag(0);

                        Toast toast = new Toast(getApplicationContext());

                        addCar();

                        //创建一个填充物,用于填充Toast
                        LayoutInflater inflater = LayoutInflater.from(CarActivity.this);

                        //填充物来自的xml文件,在这个改成一个view
                        //实现xml到view的转变哦
                        View viewToast = inflater.inflate(R.layout.toast, null);

                        //不一定需要，找到xml里面的组件，设置组件里面的具体内容
                        ImageView tick = viewToast.findViewById(R.id.image_tick);
                        TextView desc = viewToast.findViewById(R.id.tv_desc);
                        tick.setImageResource(R.drawable.tick);
                        desc.setText("添加成功");

                        //把填充物放进toast
                        toast.setView(viewToast);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        //展示toast
                        toast.show();
                    }
                    break;
            }
        } else {
            startActivity(new Intent(CarActivity.this, LoginActivity.class));
        }
    }

    /**
     * 添加进购物车
     */
    private void addCar() {
        if (i < 0) {
            Toast.makeText(this, "数量不够", Toast.LENGTH_SHORT).show();
            return;
        }
        if (detailsBase1.getData().getProductList().size() > 0) {
            int goodsId = this.detailsBase1.getData().getProductList().get(0).getGoods_id();
            int productid = this.detailsBase1.getData().getProductList().get(0).getId();
            Map<String, String> map = new HashMap<>();
            map.put("goodsId", String.valueOf(goodsId));
            map.put("number", String.valueOf(i));
            map.put("productId", String.valueOf(productid));
            presenter.addGoodCar(map);
        }
    }

    /**
     * 打开购物车
     */
    private void openGoodCar() {
        Intent intent = new Intent();
        intent.setClass(CarActivity.this, MainActivity.class);
        intent.putExtra("id", 3);//其中1表示Fragment的编号，从0开始编，secondFra的编号为1
        startActivity(intent);
    }


    private void initAttribute(List<DetailsBase.DataBeanX.AttributeBean> attribute) {
        for (int i = 0; i < attribute.size(); i++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.details_item_attribute, null);
            TextView value = inflate.findViewById(R.id.value);
            TextView name = inflate.findViewById(R.id.name);
            DetailsBase.DataBeanX.AttributeBean attributeBean = attribute.get(i);
            name.setText(attributeBean.getName());
            value.setText(attributeBean.getValue());
            linearAttribute.addView(inflate);
        }
    }

    private void initCommonProblem(List<DetailsBase.DataBeanX.IssueBean> productList) {
        for (int i = 0; i < productList.size(); i++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.details_item_problem, null);
            TextView answer = inflate.findViewById(R.id.answer);
            TextView question = inflate.findViewById(R.id.question);
            DetailsBase.DataBeanX.IssueBean productListBean = productList.get(i);
            answer.setText(productListBean.getAnswer());
            question.setText(productListBean.getQuestion());
            linearCommonProblem.addView(inflate);
        }
    }

    private void initText(DetailsBase.DataBeanX.InfoBean info) {
        name.setText(info.getName());
        goodsBrief.setText(info.getGoods_brief());
        retailPrice.setText("￥" + info.getRetail_price());
    }

    private void initBanner(List<DetailsBase.DataBeanX.GalleryBean> gallery) {
        banner.setImages(gallery).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                DetailsBase.DataBeanX.GalleryBean galleryBean = (DetailsBase.DataBeanX.GalleryBean) path;
                Glide.with(context).load(galleryBean.getImg_url()).into(imageView);
            }
        }).start();
    }

    @Override
    public void getRelated(RelatedBase brandIdBase) {
        if (brandIdBase != null) {
            rvList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

            rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            rvList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

            DetailsAdapter detailsAdapter = new DetailsAdapter(this, brandIdBase.getData().getGoodsList());

            rvList.setAdapter(detailsAdapter);

            detailsAdapter.addListClick(new BaseAdapter.IListClick() {
                @Override
                public void itemClick(int pos) {
                    RelatedBase.DataBean.GoodsListBean goodsListBean = brandIdBase.getData().getGoodsList().get(pos);
                    Intent intent = new Intent(CarActivity.this, CarActivity.class);
                    intent.putExtra("id", goodsListBean.getId());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void addGoodCarReturn(AddCarBean addCarBean) {

    }

}
