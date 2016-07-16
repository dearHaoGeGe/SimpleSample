package com.my.simplesampletest.autohideime;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

/**
 * 限制输入内容
 *
 * 有时候我们根本就不想用户输入一些杂七杂八的内容，
 * 因为这需要程序针对输入的内容做各种处理，
 * 如果处理不当还会有好多不可预见的问题，
 * 索性在输入内容时就禁止用户输入一些非法字符，
 * 这可以通过下面的方式实现，新建一个类InputTxtFilter
 *
 * Created by YJH on 2016/7/16.
 */
public class InputTxtFilter{
    public static final int INPUT_TYPE_EN = 0x01;
    public static final int INPUT_TYPE_CH = 0x02;
    private static final String[] SPELL = new String[]{
            "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
            "ā","á","ǎ","à","ō","ó","ǒ","ò","ē","é","ě","è","ī","í","ǐ","ì","ū","ú","ǔ","ù","ǖ","ǘ","ǚ","ǜ","ü"
    };
    private static char[] chineseParam = new char[]{'」','，','。','？','…','：','～','【','＃','、','％','＊','＆','＄','（','‘','’','“','”','『','〔','｛','【'
            ,'￥','￡','‖','〖','《','「','》','〗','】','｝','〕','』','”','）','！','；','—'};

    private InputTxtFilter( ){

    }

    public static void inputFilter(final Context context, final EditText editText, final int type, final int inputLimit){
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(inputLimit){
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend){
                boolean isRightCharater = false;
                if(type == INPUT_TYPE_EN){
                    isRightCharater = isLetter(source.toString());
                }else if(type == INPUT_TYPE_CH){
                    isRightCharater = isChineseWord(source.toString());
                }

                if ( !isRightCharater|| dest.toString( ).length( )>=inputLimit ){
                    return "";
                }

                return source;
            }
        };
        editText.setFilters(filters);
    }

    /**
     * 检测String是否全是中文
     *
     */
    public static boolean isChineseWord( String name ){
        boolean res=true;
        char[] cTemp = name.toCharArray( );

        for( int i = 0; i < name.length( ); i++ ){
            if( !isChinese( cTemp[ i ] ) ){
                res=false;
                break;
            }
        }

        return res;
    }

    /**
     * 是否为英文字母
     *
     * */
    public static boolean isLetter( String inputStr ){
        char[] inputArray = inputStr.toCharArray( );
        List<String> spellList = Arrays.asList( SPELL );

        for( char input : inputArray ){
            if( !spellList.contains( input + "" ) ){
                return false;
            }
        }

        return true;
    }

    /**
     * 判定输入汉字
     * @param c
     */
    public static boolean isChinese( char c ){
        for( char param : chineseParam ){
            if( param == c ){
                return false;
            }
        }

        Character.UnicodeBlock ub = Character.UnicodeBlock.of( c );
        if ( ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ){
            return true;
        }

        return false;
    }
}