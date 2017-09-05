package jluzonepro.zyascend.com.common.entity;

/**
 *
 * Created by Administrator on 2016/7/6.
 */
public class ConstValue {

    public static final String KEY_STU_INFO = "key_stu_info";
    public static final String SAVED_NUM = "key_num";
    public static final String SAVED_NAME = "key_name";
    public static final String SAVED_STUID = "key_id";
    public static final String SAVED_PASSWORD = "key_passWord";
    public static final String IS_AUTO_LOGIN = "key_isAutoLogin";

    public static final String IS_SAVE_PWD = "key_isSavePassWord";

    public static final String IS_LOGIN_OUTSIDE = "key_isLoginOutside";
    //校外登录
    public static final String LOGIN_OUT_URL = "http://cjcx.jlu.edu.cn/score/action/security_check.php";
    //校内登录
    public static final String LOGIN_IN_URL = "http://uims.jlu.edu.cn/ntms/j_spring_security_check";
    //获取当前登录用户信息
    public static final String CURRENT_INFO = "http://uims.jlu.edu.cn/ntms/action/getCurrentUserInfo.do";
    //获取学期列表
    //登陆后查成绩
    public static final String SEMES_SCORE_URL = "http://uims.jlu.edu.cn/ntms/service/res.do";
    public static final String SCORE_DETAIL_URL = "http://uims.jlu.edu.cn/ntms/score/course-score-stat.do";

    public static final String CURRENT_WEEK = "current_week";
    public static final int SCORE_TYPE_NEW = 0;
    public static final int SCORE_TYPE_ALL = 1;
    public static final int SCORE_TYPE_YEAR = 2;

    public static final String JWC_URL = "http://jwc.jlu.edu.cn/zxzx/ksap.htm";
    public static final String XIAO_URL = "http://jwc.jlu.edu.cn/zxzx/tzgg.htm";
    public static final String URL_JOB_CONTENT = "http://jdjyw.jlu.edu.cn/index.php?r=app/recruit/details&id=";

    public static final String TAG_XIAOZHAO = "xiaozhao";
    public static final String TAG_SHIXI = "shixi";

    public static final String URL_XIAOZHAO = "http://jdjyw.jlu.edu.cn/index.php?r=app/recruit&type=1&page=";
    public static final String URL_SHIXI = "http://jdjyw.jlu.edu.cn/index.php?r=app/recruit&type=3&page=";
    public static final String URL_MAIN_IMAGE = "http://www.jlu.edu.cn/";
    public static final String URL_HOST_JWC = "http://oldjwc.jlu.edu.cn";
    public static final String URL_HOST_XIAO = "http://oa.jlu.edu.cn/";

    public static final double NANLING_LAT = 43.860974;
    public static final double NANLING_LOT = 125.341302;

    public static final double QIAN_NAN_LAT = 43.829059;
    public static final double QIAN_NAN_LOT = 125.287352;

    //125.326482,43.886239
    public static final double QIAN_BEI_LAT = 43.886239;
    public static final double QIAN_BEI_LOT = 125.326482;
    //125.314966,43.876377
    public static final double XINMIN_LAT = 43.876377;
    public static final double XINMIN_LOT = 125.314966;
    //125.297073,43.856352
    public static final double NANHU_LAT = 43.856352;
    public static final double NANHU_LOT = 125.297073;
    //125.314187,43.889507
    public static final double CHAOYANG_LAT = 43.889507;
    public static final double CHAOYANG_LOT = 125.314187;
    //125.26976,43.91215
    public static final double HEPIN_LAT = 43.91215;
    public static final double HEPIN_LOT = 125.26976;

    //获取待评列表
    //http://uims.jlu.edu.cn/ntms/service/res.do
    //{"tag":"student@evalItem","branch":"self","params":{"blank":"Y"}}

    //全A评价
    //http://uims.jlu.edu.cn/ntms/eduEvaluate/eval-with-answer.do
    //{"evalItemId":"3436805","answers":{"prob11":"A","prob12":"A","prob13":"D","prob14":"A","prob15":"A","prob21":"A","prob22":"A","prob23":"A","prob31":"A","prob32":"A","prob41":"A","prob42":"A","prob43":"A","prob51":"A","prob52":"A","sat6":"A","mulsel71":"L","advice8":"无"}}
    public static final String URL_EVALUATE = "http://uims.jlu.edu.cn/ntms/eduEvaluate/eval-with-answer.do";

    public static final String URL_CHANNEL = "http://202.98.18.57:18080/webservice/m/api/getWebCastList";

    public static final String URL_WEATHER = "http://wthrcdn.etouch.cn/weather_mini?citykey=101060101";
    public static final String CURRENT_TERM = "current_term";
}
