package jluzonepro.zyascend.com.common.entity;

/**
 * Created by Administrator on 2016/7/5.
 */
public class ScoreValue {


    /**
     * xkkh : (2015-2016-2)-ac13412601-602780-1
     * teachingTerm : {"termName":"2015-2016第2学期","startDate":"2016-03-07T00:00:00","termSeq":"2","examDate":"2016-07-04T00:00:00","activeStage":"1230","year":"2015-2016","vacationDate":"2016-07-11T00:00:00","weeks":"18","termId":"130","egrade":"2015"}
     * score : 80
     * dateScore : 2016-06-28T14:10:52
     * planDetail : null
     * isPass : Y
     * classHour : 60
     * course : {"englishName":"Basic Industrial Engineering","adviceHour":"60","batch":"13","activeStatus":"103","type5":"4160","extCourseNo":"ac13412601","courName":"基础工业工程","courseId":"11878","courType1":"3012","adviceCredit":"1.5"}
     * isReselect : N
     * scoreNum : 80
     * student : {"studId":"238615","name":"张扬","adminClass":{"adcId":"5191","formalStudCnt":"32","graduateYear":"2018","classNo":"411404","studCnt":"32","className":"411404","admissionYear":"2014","campus":"1402"},"admissionYear":"2014","studStatus":"2700","studNo":"41140423","egrade":"2014"}
     * asId : 5350414
     * type5 : 4160
     * gpoint : 3
     * credit : 1.5
     * notes : null
     * selectType : 3060
     */

    private String xkkh;
    /**
     * termName : 2015-2016第2学期
     * startDate : 2016-03-07T00:00:00
     * termSeq : 2
     * examDate : 2016-07-04T00:00:00
     * activeStage : 1230
     * year : 2015-2016
     * vacationDate : 2016-07-11T00:00:00
     * weeks : 18
     * termId : 130
     * egrade : 2015
     */

    private TeachingTermBean teachingTerm;
    private String score;
    private String dateScore;
    private Object planDetail;
    private String isPass;
    private String classHour;
    /**
     * englishName : Basic Industrial Engineering
     * adviceHour : 60
     * batch : 13
     * activeStatus : 103
     * type5 : 4160
     * extCourseNo : ac13412601
     * courName : 基础工业工程
     * courseId : 11878
     * courType1 : 3012
     * adviceCredit : 1.5
     */

    private CourseBean course;
    private String isReselect;
    private String scoreNum;
    /**
     * studId : 238615
     * name : 张扬
     * adminClass : {"adcId":"5191","formalStudCnt":"32","graduateYear":"2018","classNo":"411404","studCnt":"32","className":"411404","admissionYear":"2014","campus":"1402"}
     * admissionYear : 2014
     * studStatus : 2700
     * studNo : 41140423
     * egrade : 2014
     */

    private StudentBean student;
    private String asId;
    private String type5;
    private String gpoint;
    private String credit;
    private Object notes;
    private String selectType;

    public String getXkkh() {
        return xkkh;
    }

    public void setXkkh(String xkkh) {
        this.xkkh = xkkh;
    }

    public TeachingTermBean getTeachingTerm() {
        return teachingTerm;
    }

    public void setTeachingTerm(TeachingTermBean teachingTerm) {
        this.teachingTerm = teachingTerm;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDateScore() {
        return dateScore;
    }

    public void setDateScore(String dateScore) {
        this.dateScore = dateScore;
    }

    public Object getPlanDetail() {
        return planDetail;
    }

    public void setPlanDetail(Object planDetail) {
        this.planDetail = planDetail;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getClassHour() {
        return classHour;
    }

    public void setClassHour(String classHour) {
        this.classHour = classHour;
    }

    public CourseBean getCourse() {
        return course;
    }

    public void setCourse(CourseBean course) {
        this.course = course;
    }

    public String getIsReselect() {
        return isReselect;
    }

    public void setIsReselect(String isReselect) {
        this.isReselect = isReselect;
    }

    public String getScoreNum() {
        return scoreNum;
    }

    public void setScoreNum(String scoreNum) {
        this.scoreNum = scoreNum;
    }

    public StudentBean getStudent() {
        return student;
    }

    public void setStudent(StudentBean student) {
        this.student = student;
    }

    public String getAsId() {
        return asId;
    }

    public void setAsId(String asId) {
        this.asId = asId;
    }

    public String getType5() {
        return type5;
    }

    public void setType5(String type5) {
        this.type5 = type5;
    }

    public String getGpoint() {
        return gpoint;
    }

    public void setGpoint(String gpoint) {
        this.gpoint = gpoint;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public Object getNotes() {
        return notes;
    }

    public void setNotes(Object notes) {
        this.notes = notes;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public static class TeachingTermBean {
        private String termName;
        private String startDate;
        private String termSeq;
        private String examDate;
        private String activeStage;
        private String year;
        private String vacationDate;
        private String weeks;
        private String termId;
        private String egrade;

        public String getTermName() {
            return termName;
        }

        public void setTermName(String termName) {
            this.termName = termName;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getTermSeq() {
            return termSeq;
        }

        public void setTermSeq(String termSeq) {
            this.termSeq = termSeq;
        }

        public String getExamDate() {
            return examDate;
        }

        public void setExamDate(String examDate) {
            this.examDate = examDate;
        }

        public String getActiveStage() {
            return activeStage;
        }

        public void setActiveStage(String activeStage) {
            this.activeStage = activeStage;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getVacationDate() {
            return vacationDate;
        }

        public void setVacationDate(String vacationDate) {
            this.vacationDate = vacationDate;
        }

        public String getWeeks() {
            return weeks;
        }

        public void setWeeks(String weeks) {
            this.weeks = weeks;
        }

        public String getTermId() {
            return termId;
        }

        public void setTermId(String termId) {
            this.termId = termId;
        }

        public String getEgrade() {
            return egrade;
        }

        public void setEgrade(String egrade) {
            this.egrade = egrade;
        }
    }

    public static class CourseBean {
        private String englishName;
        private String adviceHour;
        private String batch;
        private String activeStatus;
        private String type5;
        private String extCourseNo;
        private String courName;
        private String courseId;
        private String courType1;
        private String adviceCredit;

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public String getAdviceHour() {
            return adviceHour;
        }

        public void setAdviceHour(String adviceHour) {
            this.adviceHour = adviceHour;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getActiveStatus() {
            return activeStatus;
        }

        public void setActiveStatus(String activeStatus) {
            this.activeStatus = activeStatus;
        }

        public String getType5() {
            return type5;
        }

        public void setType5(String type5) {
            this.type5 = type5;
        }

        public String getExtCourseNo() {
            return extCourseNo;
        }

        public void setExtCourseNo(String extCourseNo) {
            this.extCourseNo = extCourseNo;
        }

        public String getCourName() {
            return courName;
        }

        public void setCourName(String courName) {
            this.courName = courName;
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public String getCourType1() {
            return courType1;
        }

        public void setCourType1(String courType1) {
            this.courType1 = courType1;
        }

        public String getAdviceCredit() {
            return adviceCredit;
        }

        public void setAdviceCredit(String adviceCredit) {
            this.adviceCredit = adviceCredit;
        }
    }

    public static class StudentBean {
        private String studId;
        private String name;
        /**
         * adcId : 5191
         * formalStudCnt : 32
         * graduateYear : 2018
         * classNo : 411404
         * studCnt : 32
         * className : 411404
         * admissionYear : 2014
         * campus : 1402
         */

        private AdminClassBean adminClass;
        private String admissionYear;
        private String studStatus;
        private String studNo;
        private String egrade;

        public String getStudId() {
            return studId;
        }

        public void setStudId(String studId) {
            this.studId = studId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public AdminClassBean getAdminClass() {
            return adminClass;
        }

        public void setAdminClass(AdminClassBean adminClass) {
            this.adminClass = adminClass;
        }

        public String getAdmissionYear() {
            return admissionYear;
        }

        public void setAdmissionYear(String admissionYear) {
            this.admissionYear = admissionYear;
        }

        public String getStudStatus() {
            return studStatus;
        }

        public void setStudStatus(String studStatus) {
            this.studStatus = studStatus;
        }

        public String getStudNo() {
            return studNo;
        }

        public void setStudNo(String studNo) {
            this.studNo = studNo;
        }

        public String getEgrade() {
            return egrade;
        }

        public void setEgrade(String egrade) {
            this.egrade = egrade;
        }

        public static class AdminClassBean {
            private String adcId;
            private String formalStudCnt;
            private String graduateYear;
            private String classNo;
            private String studCnt;
            private String className;
            private String admissionYear;
            private String campus;

            public String getAdcId() {
                return adcId;
            }

            public void setAdcId(String adcId) {
                this.adcId = adcId;
            }

            public String getFormalStudCnt() {
                return formalStudCnt;
            }

            public void setFormalStudCnt(String formalStudCnt) {
                this.formalStudCnt = formalStudCnt;
            }

            public String getGraduateYear() {
                return graduateYear;
            }

            public void setGraduateYear(String graduateYear) {
                this.graduateYear = graduateYear;
            }

            public String getClassNo() {
                return classNo;
            }

            public void setClassNo(String classNo) {
                this.classNo = classNo;
            }

            public String getStudCnt() {
                return studCnt;
            }

            public void setStudCnt(String studCnt) {
                this.studCnt = studCnt;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public String getAdmissionYear() {
                return admissionYear;
            }

            public void setAdmissionYear(String admissionYear) {
                this.admissionYear = admissionYear;
            }

            public String getCampus() {
                return campus;
            }

            public void setCampus(String campus) {
                this.campus = campus;
            }
        }
    }
}
