package jluzonepro.zyascend.com.common.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Administrator on 2016/7/4.
 */
@Entity
public class Term {
        @Id
        private long id;
        private String termName;
        private String startDate;
        private String termSeq;
        private String examDate;
        private String termType;
        private String activeStage;
        private String year;
        private String vacationDate;
        private String weeks;
        private String termId;
        private String egrade;
        public String getEgrade() {
                return this.egrade;
        }
        public void setEgrade(String egrade) {
                this.egrade = egrade;
        }
        public String getTermId() {
                return this.termId;
        }
        public void setTermId(String termId) {
                this.termId = termId;
        }
        public String getWeeks() {
                return this.weeks;
        }
        public void setWeeks(String weeks) {
                this.weeks = weeks;
        }
        public String getVacationDate() {
                return this.vacationDate;
        }
        public void setVacationDate(String vacationDate) {
                this.vacationDate = vacationDate;
        }
        public String getYear() {
                return this.year;
        }
        public void setYear(String year) {
                this.year = year;
        }
        public String getActiveStage() {
                return this.activeStage;
        }
        public void setActiveStage(String activeStage) {
                this.activeStage = activeStage;
        }
        public String getTermType() {
                return this.termType;
        }
        public void setTermType(String termType) {
                this.termType = termType;
        }
        public String getExamDate() {
                return this.examDate;
        }
        public void setExamDate(String examDate) {
                this.examDate = examDate;
        }
        public String getTermSeq() {
                return this.termSeq;
        }
        public void setTermSeq(String termSeq) {
                this.termSeq = termSeq;
        }
        public String getStartDate() {
                return this.startDate;
        }
        public void setStartDate(String startDate) {
                this.startDate = startDate;
        }
        public String getTermName() {
                return this.termName;
        }
        public void setTermName(String termName) {
                this.termName = termName;
        }
        public long getId() {
                return this.id;
        }
        public void setId(long id) {
                this.id = id;
        }
        @Generated(hash = 1976312064)
        public Term(long id, String termName, String startDate, String termSeq,
                        String examDate, String termType, String activeStage,
                        String year, String vacationDate, String weeks, String termId,
                        String egrade) {
                this.id = id;
                this.termName = termName;
                this.startDate = startDate;
                this.termSeq = termSeq;
                this.examDate = examDate;
                this.termType = termType;
                this.activeStage = activeStage;
                this.year = year;
                this.vacationDate = vacationDate;
                this.weeks = weeks;
                this.termId = termId;
                this.egrade = egrade;
        }
        @Generated(hash = 142182234)
        public Term() {
        }
}
