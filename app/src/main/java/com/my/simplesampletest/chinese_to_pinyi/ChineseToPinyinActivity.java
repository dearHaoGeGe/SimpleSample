package com.my.simplesampletest.chinese_to_pinyi;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.my.simplesampletest.R;
import com.my.simplesampletest.base.BaseActivity;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 中文转拼音
 * <a href="https://mp.weixin.qq.com/s/FHvLbtZj49voFH2Wn-DZTw"/>
 * <a href="http://www.2cto.com/kf/201306/222721.html"/>
 * <p>
 * Created by YJH on 2017/5/17 21:51.
 */

public class ChineseToPinyinActivity extends BaseActivity {

    private EditText et_pinyin;
    private Button btn_pinyin;
    private TextView tv_context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.MenuTextAllCaps);
        setContentView(R.layout.activity_chinese_to_pinyin);
        setToolbar("汉字转拼音", true, 0);

        initView();
        initData();
    }

    @Override
    public void initView() {
        et_pinyin = (EditText) findViewById(R.id.et_pinyin);
        btn_pinyin = (Button) findViewById(R.id.btn_pinyin);
        tv_context = (TextView) findViewById(R.id.tv_context);
    }

    @Override
    public void initData() {
        HanZi2PinYin("我爱你");
//        tv_context.setText(cn2Spell("我爱你"));
    }

    private void HanZi2PinYin(String src) {

        Toast.makeText(this, src, Toast.LENGTH_SHORT).show();

        char[] chars = src.toCharArray();
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        StringBuffer sb = new StringBuffer();
//        下面注释掉是因为报BadHanyuPinyinOutputFormatCombination异常
//        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
//        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        for (int i = 0; i < chars.length; i++) {
            try {
                String[] strings = PinyinHelper.toHanyuPinyinStringArray(chars[i], format);
                if (strings != null) {
                    for (int j = 0; j < strings.length; j++) {
                        sb.append(strings[j]);
                        if (j != strings.length - 1)
                            sb.append(",");
                    }
                    sb.append("\r\n");
                }
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
        }
        tv_context.setText(sb);
    }

    /**
     * 获取汉字串拼音首字母，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String cn2FirstSpell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] _t = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (_t != null) {
                        pybf.append(_t[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }

    /**
     * 获取汉字串拼音，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音
     */
    public static String cn2Spell(String chinese) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();

        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

//        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0] + "\n");
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i] + "\n");
            }
        }
        return pybf.toString();
    }

}
