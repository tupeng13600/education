package com.xcjy.web.common;

import com.xcjy.web.controller.req.Page;

/**
 * Created by tupeng on 2017/7/22.
 */
public class XcjyThreadLocal {

    private static ThreadLocal<String> school = new ThreadLocal();

    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal();

    private static ThreadLocal<Integer> totalCountThreadLocal = new ThreadLocal();

    public static void setSchoolId(String schoolId) {
        school.set(schoolId);
    }

    public static String getSchoolId() {
        return school.get();
    }

    public static void removeSchoolId() {
        school.remove();
    }

    public static void setPage(Page page) {
        pageThreadLocal.set(page);
    }

    public static Page getPage() {
        return pageThreadLocal.get();
    }

    public static void setTotalCount(Integer totalCount){
        totalCountThreadLocal.set(totalCount);
    }

    public static Integer getTotalCount(){
        return totalCountThreadLocal.get();
    }

    public static void removePage(){
        pageThreadLocal.remove();
    }

}
