package jluzonepro.zyascend.com.common.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/12/5.
 */

public class EvaluateResult {

    /**
     * id : evalItemId
     * status : 0
     * value : [{"personInput":null,"subject":null,"target":{"school":{"schoolName":"机械科学与工程学院"},"name":"林跃","personId":"13158"},"evalItemId":"3438647","notes":null,"evalActTime":{"actTimeId":"1299","evalGuideline":{"evalGuidelineId":"100","paperUrl":"page/eval/eval_detail_100.html","displayMode":"page"},"evalTime":{"dateStop":"2016-12-18T16:00:00","title":"2016-2017第1学期学生评教","phaseType":"4012","activeStatus":"103","etimeId":"110","dateStart":"2016-12-05T08:00:00"}},"dateInput":null,"targetClar":{"notes":"讲授《机电产品构造实习》课程"}},{"personInput":null,"subject":null,"target":{"school":{"schoolName":"机械科学与工程学院"},"name":"闫振华","personId":"17394"},"evalItemId":"3439730","notes":null,"evalActTime":{"actTimeId":"1299","evalGuideline":{"evalGuidelineId":"100","paperUrl":"page/eval/eval_detail_100.html","displayMode":"page"},"evalTime":{"dateStop":"2016-12-18T16:00:00","title":"2016-2017第1学期学生评教","phaseType":"4012","activeStatus":"103","etimeId":"110","dateStart":"2016-12-05T08:00:00"}},"dateInput":null,"targetClar":{"notes":"讲授《机械精度设计基础》课程"}},{"personInput":null,"subject":null,"target":{"school":{"schoolName":"机械科学与工程学院"},"name":"龚捷","personId":"11024"},"evalItemId":"3445525","notes":null,"evalActTime":{"actTimeId":"1299","evalGuideline":{"evalGuidelineId":"100","paperUrl":"page/eval/eval_detail_100.html","displayMode":"page"},"evalTime":{"dateStop":"2016-12-18T16:00:00","title":"2016-2017第1学期学生评教","phaseType":"4012","activeStatus":"103","etimeId":"110","dateStart":"2016-12-05T08:00:00"}},"dateInput":null,"targetClar":{"notes":"讲授《机械工程控制基础》课程"}},{"personInput":null,"subject":null,"target":{"school":{"schoolName":"材料科学与工程学院"},"name":"张驰","personId":"1205"},"evalItemId":"3458009","notes":null,"evalActTime":{"actTimeId":"1301","evalGuideline":{"evalGuidelineId":"100","paperUrl":"page/eval/eval_detail_100.html","displayMode":"page"},"evalTime":{"dateStop":"2016-12-18T16:00:00","title":"2016-2017第1学期学生评教","phaseType":"4012","activeStatus":"103","etimeId":"110","dateStart":"2016-12-05T08:00:00"}},"dateInput":null,"targetClar":{"notes":"讲授《材料成型技术基础》课程"}},{"personInput":null,"subject":null,"target":{"school":{"schoolName":"机械基础教学与研究中心"},"name":"寇尊权","personId":"4629"},"evalItemId":"3643355","notes":null,"evalActTime":{"actTimeId":"1330","evalGuideline":{"evalGuidelineId":"100","paperUrl":"page/eval/eval_detail_100.html","displayMode":"page"},"evalTime":{"dateStop":"2016-12-18T16:00:00","title":"2016-2017第1学期学生评教","phaseType":"4012","activeStatus":"103","etimeId":"110","dateStart":"2016-12-05T08:00:00"}},"dateInput":null,"targetClar":{"notes":"讲授《机械设计A》课程"}},{"personInput":null,"subject":null,"target":{"school":{"schoolName":"机械基础教学与研究中心"},"name":"寇尊权","personId":"4629"},"evalItemId":"3643799","notes":null,"evalActTime":{"actTimeId":"1330","evalGuideline":{"evalGuidelineId":"100","paperUrl":"page/eval/eval_detail_100.html","displayMode":"page"},"evalTime":{"dateStop":"2016-12-18T16:00:00","title":"2016-2017第1学期学生评教","phaseType":"4012","activeStatus":"103","etimeId":"110","dateStart":"2016-12-05T08:00:00"}},"dateInput":null,"targetClar":{"notes":"讲授《机械工程综合实验Ⅱ》课程"}},{"personInput":null,"subject":null,"target":{"school":{"schoolName":"机械基础教学与研究中心"},"name":"董景石","personId":"14513"},"evalItemId":"3647095","notes":null,"evalActTime":{"actTimeId":"1330","evalGuideline":{"evalGuidelineId":"100","paperUrl":"page/eval/eval_detail_100.html","displayMode":"page"},"evalTime":{"dateStop":"2016-12-18T16:00:00","title":"2016-2017第1学期学生评教","phaseType":"4012","activeStatus":"103","etimeId":"110","dateStart":"2016-12-05T08:00:00"}},"dateInput":null,"targetClar":{"notes":"讲授《《机械原理A》课程设计》课程"}}]
     * resName : evalItem
     * msg :
     */

    private String id;
    private int status;
    private String resName;
    private String msg;
    private List<ValueBean> value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ValueBean> getValue() {
        return value;
    }

    public void setValue(List<ValueBean> value) {
        this.value = value;
    }

    public static class ValueBean {
        /**
         * personInput : null
         * subject : null
         * target : {"school":{"schoolName":"机械科学与工程学院"},"name":"林跃","personId":"13158"}
         * evalItemId : 3438647
         * notes : null
         * evalActTime : {"actTimeId":"1299","evalGuideline":{"evalGuidelineId":"100","paperUrl":"page/eval/eval_detail_100.html","displayMode":"page"},"evalTime":{"dateStop":"2016-12-18T16:00:00","title":"2016-2017第1学期学生评教","phaseType":"4012","activeStatus":"103","etimeId":"110","dateStart":"2016-12-05T08:00:00"}}
         * dateInput : null
         * targetClar : {"notes":"讲授《机电产品构造实习》课程"}
         */

        private Object personInput;
        private Object subject;
        private TargetBean target;
        private String evalItemId;
        private Object notes;
        private EvalActTimeBean evalActTime;
        private Object dateInput;
        private TargetClarBean targetClar;

        public Object getPersonInput() {
            return personInput;
        }

        public void setPersonInput(Object personInput) {
            this.personInput = personInput;
        }

        public Object getSubject() {
            return subject;
        }

        public void setSubject(Object subject) {
            this.subject = subject;
        }

        public TargetBean getTarget() {
            return target;
        }

        public void setTarget(TargetBean target) {
            this.target = target;
        }

        public String getEvalItemId() {
            return evalItemId;
        }

        public void setEvalItemId(String evalItemId) {
            this.evalItemId = evalItemId;
        }

        public Object getNotes() {
            return notes;
        }

        public void setNotes(Object notes) {
            this.notes = notes;
        }

        public EvalActTimeBean getEvalActTime() {
            return evalActTime;
        }

        public void setEvalActTime(EvalActTimeBean evalActTime) {
            this.evalActTime = evalActTime;
        }

        public Object getDateInput() {
            return dateInput;
        }

        public void setDateInput(Object dateInput) {
            this.dateInput = dateInput;
        }

        public TargetClarBean getTargetClar() {
            return targetClar;
        }

        public void setTargetClar(TargetClarBean targetClar) {
            this.targetClar = targetClar;
        }

        public static class TargetBean {
            /**
             * school : {"schoolName":"机械科学与工程学院"}
             * name : 林跃
             * personId : 13158
             */

            private SchoolBean school;
            private String name;
            private String personId;

            public SchoolBean getSchool() {
                return school;
            }

            public void setSchool(SchoolBean school) {
                this.school = school;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPersonId() {
                return personId;
            }

            public void setPersonId(String personId) {
                this.personId = personId;
            }

            public static class SchoolBean {
                /**
                 * schoolName : 机械科学与工程学院
                 */

                private String schoolName;

                public String getSchoolName() {
                    return schoolName;
                }

                public void setSchoolName(String schoolName) {
                    this.schoolName = schoolName;
                }
            }
        }

        public static class EvalActTimeBean {
            /**
             * actTimeId : 1299
             * evalGuideline : {"evalGuidelineId":"100","paperUrl":"page/eval/eval_detail_100.html","displayMode":"page"}
             * evalTime : {"dateStop":"2016-12-18T16:00:00","title":"2016-2017第1学期学生评教","phaseType":"4012","activeStatus":"103","etimeId":"110","dateStart":"2016-12-05T08:00:00"}
             */

            private String actTimeId;
            private EvalGuidelineBean evalGuideline;
            private EvalTimeBean evalTime;

            public String getActTimeId() {
                return actTimeId;
            }

            public void setActTimeId(String actTimeId) {
                this.actTimeId = actTimeId;
            }

            public EvalGuidelineBean getEvalGuideline() {
                return evalGuideline;
            }

            public void setEvalGuideline(EvalGuidelineBean evalGuideline) {
                this.evalGuideline = evalGuideline;
            }

            public EvalTimeBean getEvalTime() {
                return evalTime;
            }

            public void setEvalTime(EvalTimeBean evalTime) {
                this.evalTime = evalTime;
            }

            public static class EvalGuidelineBean {
                /**
                 * evalGuidelineId : 100
                 * paperUrl : page/eval/eval_detail_100.html
                 * displayMode : page
                 */

                private String evalGuidelineId;
                private String paperUrl;
                private String displayMode;

                public String getEvalGuidelineId() {
                    return evalGuidelineId;
                }

                public void setEvalGuidelineId(String evalGuidelineId) {
                    this.evalGuidelineId = evalGuidelineId;
                }

                public String getPaperUrl() {
                    return paperUrl;
                }

                public void setPaperUrl(String paperUrl) {
                    this.paperUrl = paperUrl;
                }

                public String getDisplayMode() {
                    return displayMode;
                }

                public void setDisplayMode(String displayMode) {
                    this.displayMode = displayMode;
                }
            }

            public static class EvalTimeBean {
                /**
                 * dateStop : 2016-12-18T16:00:00
                 * title : 2016-2017第1学期学生评教
                 * phaseType : 4012
                 * activeStatus : 103
                 * etimeId : 110
                 * dateStart : 2016-12-05T08:00:00
                 */

                private String dateStop;
                private String title;
                private String phaseType;
                private String activeStatus;
                private String etimeId;
                private String dateStart;

                public String getDateStop() {
                    return dateStop;
                }

                public void setDateStop(String dateStop) {
                    this.dateStop = dateStop;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getPhaseType() {
                    return phaseType;
                }

                public void setPhaseType(String phaseType) {
                    this.phaseType = phaseType;
                }

                public String getActiveStatus() {
                    return activeStatus;
                }

                public void setActiveStatus(String activeStatus) {
                    this.activeStatus = activeStatus;
                }

                public String getEtimeId() {
                    return etimeId;
                }

                public void setEtimeId(String etimeId) {
                    this.etimeId = etimeId;
                }

                public String getDateStart() {
                    return dateStart;
                }

                public void setDateStart(String dateStart) {
                    this.dateStart = dateStart;
                }
            }
        }

        public static class TargetClarBean {
            /**
             * notes : 讲授《机电产品构造实习》课程
             */

            private String notes;

            public String getNotes() {
                return notes;
            }

            public void setNotes(String notes) {
                this.notes = notes;
            }
        }
    }
}
