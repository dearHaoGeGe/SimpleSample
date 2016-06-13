package com.my.simplesampletest.picasso;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by YJH on 2016/6/12.
 */
public class PicassoActivity extends BaseActivity {

    private ListView lv_PicassoAct;
    private ProgressDialog dialog;
    private OkHttpClient client;
    private Request request;
    private static String url = "http://litchiapi.jstv.com/api/GetFeeds?column=17&PageSize=20&pageIndex=1&val=AD908EDAB9C3ED111A58AF86542CCF50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);

        initView();
        initData();
    }

    @Override
    public void initView() {
        lv_PicassoAct = (ListView) findViewById(R.id.lv_PicassoAct);
    }

    @Override
    public void initData() {
        dialog = new ProgressDialog(this);
        dialog.setTitle("Load...");
        client = new OkHttpClient();
        request = new Request.Builder().get().url(url).build();
        JSONObject object;
        JSONArray array;
        List<Item> list = new ArrayList<>();
        try {
            object = new JSONObject("{\"status\":\"ok\",\"paramz\":{\"feeds\":[{\"id\":260414,\"oid\":252444,\"category\":\"article\",\"data\":{\"subject\":\"07月07日10时整点语音播报\",\"summary\":\"中国民间第一纪念馆在成都建馆；今年中央第二轮巡视全部进驻。\",\"cover\":\"/Attachs/Article/252444/87015790b2724a13a46fbad8712c99dc_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-07 10:12:37\"}},{\"id\":260413,\"oid\":252431,\"category\":\"article\",\"data\":{\"subject\":\"07月07日新闻早高峰\",\"summary\":\"沪指昨收涨2.41%；南京市住宅物业征求意见稿出炉；未来两天我省将迎中雨。\",\"cover\":\"/Attachs/Article/252431/b112125fc562485c9b29ed18bbd87ba0_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-07 09:19:10\"}},{\"id\":260412,\"oid\":252373,\"category\":\"article\",\"data\":{\"subject\":\"07月07日江苏新闻联播\",\"summary\":\"五月江苏居民存款下降；江苏将建月嫂档案库；我省做好土耳其旅游安全防范。\",\"cover\":\"/Attachs/Article/252373/7d22c0c837aa4495bf15680fb0281bb3_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-07 07:32:31\"}},{\"id\":260411,\"oid\":252340,\"category\":\"article\",\"data\":{\"subject\":\"新闻评弹：社交工具促离婚\",\"summary\":\"社交工具并非本质，婚恋观念的变化、熟人社会到陌生人社会的转变才是离婚主因。\",\"cover\":\"/Attachs/Article/252340/f7a5328d2d444f2f8a02b65103c72dcb_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-07 00:09:53\"}},{\"id\":260410,\"oid\":252338,\"category\":\"article\",\"data\":{\"subject\":\"新闻故事：近百患者医托受骗\",\"summary\":\"医托跟小医院倒三七分成，患者消费1万元，医托拿走7000元。\",\"cover\":\"/Attachs/Article/252338/bc862ba63d204aa69b72fdbb539cfa2f_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-06 21:31:01\"}},{\"id\":260189,\"oid\":252121,\"category\":\"article\",\"data\":{\"subject\":\"07月06日10时整点语音播报\",\"summary\":\"海淘灭蚊神器失灵了；六成民众反对欧盟救助方案；教育部公布有偿补课监督电话。\",\"cover\":\"/Attachs/Article/252121/3799923e0c2145efacefc2ab0e566d19_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-06 10:12:40\"}},{\"id\":260188,\"oid\":252109,\"category\":\"article\",\"data\":{\"subject\":\"07月06日新闻早高峰\",\"summary\":\"证监会表示不会阻止新股上市；南京渣土车又闯祸；南京江北楼市进入三伏天气。\",\"cover\":\"/Attachs/Article/252109/f38f7d22f7294c5cb11cf3ade13d2c32_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-06 09:19:08\"}},{\"id\":260187,\"oid\":252076,\"category\":\"article\",\"data\":{\"subject\":\"07月06日江苏新闻联播\",\"summary\":\"罗志军在盐城调研；抗战老兵聚首南京纪念抗战胜利70周年；1/3大学生炒股。\",\"cover\":\"/Attachs/Article/252076/b66164820d024b77a1c95cc7a0705d95_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-06 07:32:37\"}},{\"id\":260185,\"oid\":252023,\"category\":\"article\",\"data\":{\"subject\":\"新闻评弹：南京宝马肇事案车主被捕\",\"summary\":\"4号，南京宝马肇事案车主王季进因涉嫌交通肇事罪被南京市秦淮区检察院批准逮捕。\",\"cover\":\"/Attachs/Article/252023/602d1d4597424be7b4be5fd7145f8729_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-05 21:02:48\"}},{\"id\":260186,\"oid\":252025,\"category\":\"article\",\"data\":{\"subject\":\"新闻故事：父亲寻亲多次被骗\",\"summary\":\"7岁女儿在家睡觉被抱走，父亲寻亲多次被骗。\",\"cover\":\"/Attachs/Article/252025/900c7db2fd9d430b86f0fca55e9d00ba_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-05 21:30:53\"}},{\"id\":259436,\"oid\":251585,\"category\":\"article\",\"data\":{\"subject\":\"07月04日11时整点语音播报\",\"summary\":\"气象局:今年厄尔尼诺或将延续至冬季；教育部公布举报电话 严禁有偿补课。\",\"cover\":\"/Attachs/Article/251585/8d3fea2417894efca21131a5ad5727c1_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-04 11:12:31\"}},{\"id\":259435,\"oid\":251560,\"category\":\"article\",\"data\":{\"subject\":\"07月04日新闻早高峰\",\"summary\":\"沪指周五再挫5.8%本周累计下跌12.1%； 多地高温费标准已数年未涨。\",\"cover\":\"/Attachs/Article/251560/b81685df6267454ba3951d53e9db108a_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-04 09:18:56\"}},{\"id\":259434,\"oid\":251522,\"category\":\"article\",\"data\":{\"subject\":\"07月04日江苏新闻联播\",\"summary\":\"罗志军：创新创业提升开放型经济水平；前五月我省工业利润全国居首。\",\"cover\":\"/Attachs/Article/251522/be722e9928cc4adb9e7d8b8425a0cc28_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-04 07:32:27\"}},{\"id\":259433,\"oid\":251489,\"category\":\"article\",\"data\":{\"subject\":\"新闻评弹：捡到乌木应该归谁\",\"summary\":\"广东男子打捞44根乌木被警方暂扣引争议，拾得物、埋藏物归属谁呢？\",\"cover\":\"/Attachs/Article/251489/7be20ae2a9414b79a6b41535fb9cc34e_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-04 00:04:00\"}},{\"id\":259432,\"oid\":251485,\"category\":\"article\",\"data\":{\"subject\":\"新闻故事:宁警方摧毁特大传销\",\"summary\":\"南京550名警力出击摧毁特大传销团伙，落网者装满20辆大巴。\",\"cover\":\"/Attachs/Article/251485/7448721ceece4133b817a507f70db0d8_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-04 11:43:54\"}},{\"id\":259127,\"oid\":251246,\"category\":\"article\",\"data\":{\"subject\":\"07月03日11时整点语音播报\",\"summary\":\"新疆皮山县6.5级地震后发生10余次余震；南京地铁5号线年底前开工建设。\",\"cover\":\"/Attachs/Article/251246/3fe48ba5d5e54307ad0d158ba3b5be42_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-03 11:12:32\"}},{\"id\":259126,\"oid\":251169,\"category\":\"article\",\"data\":{\"subject\":\"07月03日新闻早高峰\",\"summary\":\"南京江北新区成为江苏首个国家级新区；南京教育局调查后回应中考“黑幕” 。\",\"cover\":\"/Attachs/Article/251169/eb1b70e43342493dbb4d1f57a2a2c010_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-03 09:20:15\"}},{\"id\":259125,\"oid\":251134,\"category\":\"article\",\"data\":{\"subject\":\"07月03日江苏新闻联播\",\"summary\":\"南京江北新区跻身\\\"国家级\\\"；罗志军；推动从严治党迈上新台阶。\",\"cover\":\"/Attachs/Article/251134/0ab14237c4c6431aa863a0fb6c82070c_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-03 07:32:30\"}},{\"id\":259124,\"oid\":251099,\"category\":\"article\",\"data\":{\"subject\":\"新闻评弹:《职业教育法》修改\",\"summary\":\"19年前就正式施行的《职业教育法》，早已到了要“大修”的阶段了。\",\"cover\":\"/Attachs/Article/251099/2a1a903cae274861b83de23dfa2286c4_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-03 00:09:57\"}},{\"id\":259123,\"oid\":251095,\"category\":\"article\",\"data\":{\"subject\":\"新闻故事:患肿瘤女童赴美治疗\",\"summary\":\"南京4岁女童柯蕾得罕见病，截至目前共获善款648.43万，家属回应质疑。\",\"cover\":\"/Attachs/Article/251095/cc77c2d51b5e47d88e6b9b5a9d0725ba_padmini.PNG\",\"pic\":\"\",\"format\":\"radio\",\"changed\":\"2015-07-03 11:57:11\"}}],\"PageIndex\":1,\"PageSize\":20,\"TotalCount\":2557,\"TotalPage\":128}}");
            array = object.getJSONObject("paramz").getJSONArray("feeds");
            for (int i = 0; i < array.length(); i++) {
                JSONObject element = array.getJSONObject(i).getJSONObject("data");
                Item item = new Item();
                item.setCover(element.getString("cover"));
                item.setSubject(element.getString("subject"));
                item.setSummary(element.getString("summary"));
                list.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    private void

    /**
     * 获取网络
     */
    private void getNetData() {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("999999999", "失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        JSONObject object = new JSONObject(response.body().toString());
                        Log.d("999999999", response.body().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private class MyTask extends AsyncTask<String, Void, List<Item>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<Item> doInBackground(String... params) {

            return null;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            super.onPostExecute(items);
            dialog.dismiss();
        }
    }
}
