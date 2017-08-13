package jluzonepro.zyascend.com.common.entity;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/10/26.
 */

public class ImageValue {


    /**
     * code : 0
     * list : [{"id":1,"title":"title1","url":"http://img4.imgtn.bdimg.com/it/u=413594105,3243217685&fm=21&gp=0.jpg"},{"id":2,"title":"title2","url":"http://img0.imgtn.bdimg.com/it/u=2349959812,669417392&fm=21&gp=0.jpg"}]
     */

    private int code;
    /**
     * id : 1
     * title : title1
     * url : http://img4.imgtn.bdimg.com/it/u=413594105,3243217685&fm=21&gp=0.jpg
     */

    private List<ListBean> list;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int id;
        private String title;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
