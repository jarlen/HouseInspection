package cn.jarlen.houseinspection.http;

import java.util.List;

import cn.jarlen.houseinspection.data.Problem;

/**
 * DESCRIBE:
 * Created by hjl on 2016/11/17.
 */

public class ProblemResponse extends BaseResponse {

    private ProblemInfo content;

    public ProblemInfo getContent() {
        return content;
    }

    public class ProblemInfo {

        private int nums;

        private int cur_page;

        private List<Problem> info;

        public int getCurPage() {
            return cur_page;
        }

        public int getNums() {
            return nums;
        }

        public List<Problem> getInfo() {
            return info;
        }
    }


}
