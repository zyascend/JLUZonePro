package jluzonepro.zyascend.com.news.news;

import jluzonepro.zyascend.com.common.entity.News;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/8/4.
 */
public interface NewsContract {
    interface Presenter{
        void getNews(String tag, int page);
        void getXiaoContent(String url);
        void getJwcContent(String url);
        void saveShowList(List<String> list);
        void saveHideList(List<String> list);
        List<String> getShowList();
        List<String> getHideList();
        void getNewsByEditor(String editor);
    }

    interface View{
        void loadNews(List<News> newsList);
        void showFailure();
        void loadContent(String content);
    }
}
