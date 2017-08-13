package jluzonepro.zyascend.com.common.entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17.
 */

public class CourseResult {


    /**
     * maxStudCnt : 267
     * lessonSchedules : [{"classroom":{"roomId":"497","fullName":"南岭-逸夫楼#B416"},"timeBlock":{"classSet":"96","name":"周二第5,6节{第7-20周}","endWeek":"20","beginWeek":"7","tmbId":"9210","dayOfWeek":"2"},"lsschId":"81401"}]
     * studCnt : 131
     * lessonTeachers : [{"teacher":{"name":"张驰","teacherId":"1205"}}]
     * name : (2016-2017-1)-ac13432002-600868-1
     * tcmId : 64028
     * lessonSegment : {"lssgId":"36006","lesson":{"courseInfo":{"courName":"材料成型技术基础"}},"fullName":"材料成型技术基础"}
     */

    private TeachClassMasterBean teachClassMaster;
    /**
     * teachClassMaster : {"maxStudCnt":"267","lessonSchedules":[{"classroom":{"roomId":"497","fullName":"南岭-逸夫楼#B416"},"timeBlock":{"classSet":"96","name":"周二第5,6节{第7-20周}","endWeek":"20","beginWeek":"7","tmbId":"9210","dayOfWeek":"2"},"lsschId":"81401"}],"studCnt":"131","lessonTeachers":[{"teacher":{"name":"张驰","teacherId":"1205"}}],"name":"(2016-2017-1)-ac13432002-600868-1","tcmId":"64028","lessonSegment":{"lssgId":"36006","lesson":{"courseInfo":{"courName":"材料成型技术基础"}},"fullName":"材料成型技术基础"}}
     * tcsId : 4786942
     * student : null
     * dateAccept : 2016-06-12T17:22:09
     */

    private String tcsId;
    private Object student;
    private String dateAccept;

    public TeachClassMasterBean getTeachClassMaster() {
        return teachClassMaster;
    }

    public void setTeachClassMaster(TeachClassMasterBean teachClassMaster) {
        this.teachClassMaster = teachClassMaster;
    }

    public String getTcsId() {
        return tcsId;
    }

    public void setTcsId(String tcsId) {
        this.tcsId = tcsId;
    }

    public Object getStudent() {
        return student;
    }

    public void setStudent(Object student) {
        this.student = student;
    }

    public String getDateAccept() {
        return dateAccept;
    }

    public void setDateAccept(String dateAccept) {
        this.dateAccept = dateAccept;
    }

    public static class TeachClassMasterBean {
        private String maxStudCnt;
        private String studCnt;
        private String name;
        private String tcmId;
        /**
         * lssgId : 36006
         * lesson : {"courseInfo":{"courName":"材料成型技术基础"}}
         * fullName : 材料成型技术基础
         */

        private LessonSegmentBean lessonSegment;
        /**
         * classroom : {"roomId":"497","fullName":"南岭-逸夫楼#B416"}
         * timeBlock : {"classSet":"96","name":"周二第5,6节{第7-20周}","endWeek":"20","beginWeek":"7","tmbId":"9210","dayOfWeek":"2"}
         * lsschId : 81401
         */

        private List<LessonSchedulesBean> lessonSchedules;
        /**
         * teacher : {"name":"张驰","teacherId":"1205"}
         */

        private List<LessonTeachersBean> lessonTeachers;

        public String getMaxStudCnt() {
            return maxStudCnt;
        }

        public void setMaxStudCnt(String maxStudCnt) {
            this.maxStudCnt = maxStudCnt;
        }

        public String getStudCnt() {
            return studCnt;
        }

        public void setStudCnt(String studCnt) {
            this.studCnt = studCnt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTcmId() {
            return tcmId;
        }

        public void setTcmId(String tcmId) {
            this.tcmId = tcmId;
        }

        public LessonSegmentBean getLessonSegment() {
            return lessonSegment;
        }

        public void setLessonSegment(LessonSegmentBean lessonSegment) {
            this.lessonSegment = lessonSegment;
        }

        public List<LessonSchedulesBean> getLessonSchedules() {
            return lessonSchedules;
        }

        public void setLessonSchedules(List<LessonSchedulesBean> lessonSchedules) {
            this.lessonSchedules = lessonSchedules;
        }

        public List<LessonTeachersBean> getLessonTeachers() {
            return lessonTeachers;
        }

        public void setLessonTeachers(List<LessonTeachersBean> lessonTeachers) {
            this.lessonTeachers = lessonTeachers;
        }

        public static class LessonSegmentBean {
            private String lssgId;
            /**
             * courseInfo : {"courName":"材料成型技术基础"}
             */

            private LessonBean lesson;
            private String fullName;

            public String getLssgId() {
                return lssgId;
            }

            public void setLssgId(String lssgId) {
                this.lssgId = lssgId;
            }

            public LessonBean getLesson() {
                return lesson;
            }

            public void setLesson(LessonBean lesson) {
                this.lesson = lesson;
            }

            public String getFullName() {
                return fullName;
            }

            public void setFullName(String fullName) {
                this.fullName = fullName;
            }

            public static class LessonBean {
                /**
                 * courName : 材料成型技术基础
                 */

                private CourseInfoBean courseInfo;

                public CourseInfoBean getCourseInfo() {
                    return courseInfo;
                }

                public void setCourseInfo(CourseInfoBean courseInfo) {
                    this.courseInfo = courseInfo;
                }

                public static class CourseInfoBean {
                    private String courName;

                    public String getCourName() {
                        return courName;
                    }

                    public void setCourName(String courName) {
                        this.courName = courName;
                    }
                }
            }
        }

        public static class LessonSchedulesBean {
            /**
             * roomId : 497
             * fullName : 南岭-逸夫楼#B416
             */

            private ClassroomBean classroom;
            /**
             * classSet : 96
             * name : 周二第5,6节{第7-20周}
             * endWeek : 20
             * beginWeek : 7
             * tmbId : 9210
             * dayOfWeek : 2
             */

            private TimeBlockBean timeBlock;
            private String lsschId;

            public ClassroomBean getClassroom() {
                return classroom;
            }

            public void setClassroom(ClassroomBean classroom) {
                this.classroom = classroom;
            }

            public TimeBlockBean getTimeBlock() {
                return timeBlock;
            }

            public void setTimeBlock(TimeBlockBean timeBlock) {
                this.timeBlock = timeBlock;
            }

            public String getLsschId() {
                return lsschId;
            }

            public void setLsschId(String lsschId) {
                this.lsschId = lsschId;
            }

            public static class ClassroomBean {
                private String roomId;
                private String fullName;

                public String getRoomId() {
                    return roomId;
                }

                public void setRoomId(String roomId) {
                    this.roomId = roomId;
                }

                public String getFullName() {
                    return fullName;
                }

                public void setFullName(String fullName) {
                    this.fullName = fullName;
                }
            }

            public static class TimeBlockBean {
                private String classSet;
                private String name;
                private String endWeek;
                private String beginWeek;
                private String tmbId;
                private String dayOfWeek;

                public String getClassSet() {
                    return classSet;
                }

                public void setClassSet(String classSet) {
                    this.classSet = classSet;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getEndWeek() {
                    return endWeek;
                }

                public void setEndWeek(String endWeek) {
                    this.endWeek = endWeek;
                }

                public String getBeginWeek() {
                    return beginWeek;
                }

                public void setBeginWeek(String beginWeek) {
                    this.beginWeek = beginWeek;
                }

                public String getTmbId() {
                    return tmbId;
                }

                public void setTmbId(String tmbId) {
                    this.tmbId = tmbId;
                }

                public String getDayOfWeek() {
                    return dayOfWeek;
                }

                public void setDayOfWeek(String dayOfWeek) {
                    this.dayOfWeek = dayOfWeek;
                }
            }
        }

        public static class LessonTeachersBean {
            /**
             * name : 张驰
             * teacherId : 1205
             */

            private TeacherBean teacher;

            public TeacherBean getTeacher() {
                return teacher;
            }

            public void setTeacher(TeacherBean teacher) {
                this.teacher = teacher;
            }

            public static class TeacherBean {
                private String name;
                private String teacherId;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTeacherId() {
                    return teacherId;
                }

                public void setTeacherId(String teacherId) {
                    this.teacherId = teacherId;
                }
            }
        }
    }
}
