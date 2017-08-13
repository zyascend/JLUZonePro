package jluzonepro.zyascend.com.common.entity;

/**
 *
 * Created by Administrator on 2016/12/5.
 */

public class EvaluateParams {

    public EvaluateParams(String evalItemId, Answers answers) {
        this.evalItemId = evalItemId;
        this.answers = answers;
    }

    /**
     * evalItemId : 3436805
     * answers : {"prob11":"A","prob12":"A","prob13":"D","prob14":"A","prob15":"A","prob21":"A","prob22":"A","prob23":"A","prob31":"A","prob32":"A","prob41":"A","prob42":"A","prob43":"A","prob51":"A","prob52":"A","sat6":"A","mulsel71":"L","advice8":"无"}
     */

    private String evalItemId;
    private Answers answers;

    public String getEvalItemId() {
        return evalItemId;
    }

    public void setEvalItemId(String evalItemId) {
        this.evalItemId = evalItemId;
    }

    public Answers getAnswers() {
        return answers;
    }

    public void setAnswers(Answers answers) {
        this.answers = answers;
    }

    public static class Answers {
        public Answers(String abcd) {
            setProb11(abcd);
            setProb12(abcd);
            setProb13(abcd);
            setProb14(abcd);
            setProb15(abcd);
            setProb21(abcd);
            setProb22(abcd);
            setProb23(abcd);
            setProb23(abcd);
            setProb31(abcd);
            setProb32(abcd);
            setProb41(abcd);
            setProb42(abcd);
            setProb43(abcd);
            setProb51(abcd);
            setProb52(abcd);
            setSat6(abcd);
            setMulsel71("L");
            setAdvice8("");
        }

        /**
         * prob11 : A
         * prob12 : A
         * prob13 : D
         * prob14 : A
         * prob15 : A
         * prob21 : A
         * prob22 : A
         * prob23 : A
         * prob31 : A
         * prob32 : A
         * prob41 : A
         * prob42 : A
         * prob43 : A
         * prob51 : A
         * prob52 : A
         * sat6 : A
         * mulsel71 : L
         * advice8 : 无
         */



        private String prob11;
        private String prob12;
        private String prob13;
        private String prob14;
        private String prob15;
        private String prob21;
        private String prob22;
        private String prob23;
        private String prob31;
        private String prob32;
        private String prob41;
        private String prob42;
        private String prob43;
        private String prob51;
        private String prob52;
        private String sat6;
        private String mulsel71;
        private String advice8;

        public String getProb11() {
            return prob11;
        }

        public void setProb11(String prob11) {
            this.prob11 = prob11;
        }

        public String getProb12() {
            return prob12;
        }

        public void setProb12(String prob12) {
            this.prob12 = prob12;
        }

        public String getProb13() {
            return prob13;
        }

        public void setProb13(String prob13) {
            this.prob13 = prob13;
        }

        public String getProb14() {
            return prob14;
        }

        public void setProb14(String prob14) {
            this.prob14 = prob14;
        }

        public String getProb15() {
            return prob15;
        }

        public void setProb15(String prob15) {
            this.prob15 = prob15;
        }

        public String getProb21() {
            return prob21;
        }

        public void setProb21(String prob21) {
            this.prob21 = prob21;
        }

        public String getProb22() {
            return prob22;
        }

        public void setProb22(String prob22) {
            this.prob22 = prob22;
        }

        public String getProb23() {
            return prob23;
        }

        public void setProb23(String prob23) {
            this.prob23 = prob23;
        }

        public String getProb31() {
            return prob31;
        }

        public void setProb31(String prob31) {
            this.prob31 = prob31;
        }

        public String getProb32() {
            return prob32;
        }

        public void setProb32(String prob32) {
            this.prob32 = prob32;
        }

        public String getProb41() {
            return prob41;
        }

        public void setProb41(String prob41) {
            this.prob41 = prob41;
        }

        public String getProb42() {
            return prob42;
        }

        public void setProb42(String prob42) {
            this.prob42 = prob42;
        }

        public String getProb43() {
            return prob43;
        }

        public void setProb43(String prob43) {
            this.prob43 = prob43;
        }

        public String getProb51() {
            return prob51;
        }

        public void setProb51(String prob51) {
            this.prob51 = prob51;
        }

        public String getProb52() {
            return prob52;
        }

        public void setProb52(String prob52) {
            this.prob52 = prob52;
        }

        public String getSat6() {
            return sat6;
        }

        public void setSat6(String sat6) {
            this.sat6 = sat6;
        }

        public String getMulsel71() {
            return mulsel71;
        }

        public void setMulsel71(String mulsel71) {
            this.mulsel71 = mulsel71;
        }

        public String getAdvice8() {
            return advice8;
        }

        public void setAdvice8(String advice8) {
            this.advice8 = advice8;
        }
    }
}
