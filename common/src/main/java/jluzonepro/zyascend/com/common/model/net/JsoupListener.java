package jluzonepro.zyascend.com.common.model.net;



import java.util.List;

import jluzonepro.zyascend.com.common.base.BaseCallBack;
import jluzonepro.zyascend.com.common.entity.MainImage;
import jluzonepro.zyascend.com.common.entity.News;

/**
 *
 * Created by Administrator on 2016/8/4.
 */
public interface JsoupListener {

    void getJwNews(NewsCallBack newsCallBack, int page);
    void getSchoolNews(NewsCallBack newsCallBack, int page);
    void getJwContent(String url, ContentCallback callback);
    void getXiaoContent(String url, ContentCallback callback);
    void getMainImages(ImageCallback callback);
    interface ImageCallback extends BaseCallBack<List<MainImage>> {}
    interface NewsCallBack extends BaseCallBack<List<News>>{}
    interface ContentCallback extends BaseCallBack<String>{}
}
