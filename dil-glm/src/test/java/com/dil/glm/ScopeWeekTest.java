package com.dil.glm;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;

/**
 * @author: "xx"
 * @Date: 2024/12/29
 */
public class ScopeWeekTest {

    public static void main(String[] args) {
        // 获取当前日期
        LocalDate now = LocalDate.now();
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 计算本周的开始（周一）和结束（周日）
        LocalDate startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        // 格式化输出
        String weekRange = startOfWeek.format(formatter) + "~" + endOfWeek.format(formatter);
        System.out.println("当前周的范围是：" + weekRange);

        // 如果需要获取周数（一年中的第几周），可以这样做：
        int weekOfYear = now.get(WeekFields.ISO.weekOfWeekBasedYear());
        System.out.println("这是第 " + weekOfYear + " 周");
    }
}
