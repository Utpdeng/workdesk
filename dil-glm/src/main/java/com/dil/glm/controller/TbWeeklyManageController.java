package com.dil.glm.controller;

import com.dil.glm.domain.weekly.TbWeeklyManage;
import com.dil.glm.service.OllamaTongyiAi;
import com.dil.glm.service.TbWeeklyManageService;
import com.dil.glm.service.TongyiAi;
import com.dil.glm.service.ZhiPuAi;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.similarity.JaroWinklerSimilarity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @author: "dyy"
 * @Date: 2024/12/25
 */
@RestController
@Slf4j
public class TbWeeklyManageController {

    @Resource
    private TbWeeklyManageService tbWeeklyManageService;

    @Resource
    private ZhiPuAi zhiPuAi;

    @Resource
    private TongyiAi tongyiAi;

    @Resource
    private OllamaTongyiAi olamaTongyiAi;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/summary")
    public String summary() throws Exception{
        TbWeeklyManage tbWeeklyManage = new TbWeeklyManage();
        tbWeeklyManage.setUserId(1L);
        tbWeeklyManage.setScopeWeek("2024-12-16~2024-12-22");
        tbWeeklyManage.setDeptId(101L);
        List<TbWeeklyManage> tbWeeklyManages = tbWeeklyManageService.selectTbWeeklyManageList(tbWeeklyManage);
        StringBuilder req = new StringBuilder();
        req.append("将下列的周报进行整理总结，对于涉及同一项目的多个工作日志，请将它们合并为一个综合性的总结，");
        req.append("具体可以通过（识别共同主题）（量化工作量）（突出重要事件）（简化语言）总结");
        for (TbWeeklyManage tbWeeklyManage1 : tbWeeklyManages) {
            req.append(tbWeeklyManage1.getContext());
        }
        req.append("生成内容要求：按有序编号排列（相同项目归为一起）每一个序号前后添加上 <p></p> 标签，便于显示，并进行换行，不要额外符号");
        log.info("req:\n{}", req);
        String res = zhiPuAi.ai(req.toString());
        log.info("res:\n{} ", res);
        return res;
    }

    @GetMapping("/summary2")
    public String summary2(String scopeWeek, Long deptId) throws Exception{
        TbWeeklyManage tbWeeklyManage = new TbWeeklyManage();
        tbWeeklyManage.setUserId(1L);
        tbWeeklyManage.setScopeWeek(scopeWeek);
        tbWeeklyManage.setDeptId(deptId);
        List<TbWeeklyManage> tbWeeklyManages = tbWeeklyManageService.selectTbWeeklyManageList(tbWeeklyManage);
        StringBuilder req = new StringBuilder();
        req.append("将下列的周报进行整理总结，对于涉及同一项目的多个工作日志，请将它们合并为一个综合性的总结，");
        req.append("具体可以通过（识别共同主题）（量化工作量）（突出重要事件）（简化语言）总结");
        for (TbWeeklyManage tbWeeklyManage1 : tbWeeklyManages) {
            req.append(tbWeeklyManage1.getContext());
        }
        req.append("生成内容要求：按有序编号排列（相同项目归为一起）每一个序号前后添加上 <p></p> 标签，便于显示，并进行换行，不要额外符号");
        log.info("req:\n{}", req);
        String res = tongyiAi.ai(req.toString());
        log.info("res:\n{} ", res);
        return res;
    }

    @GetMapping("/summary3")
    public String summary3() throws Exception{
        TbWeeklyManage tbWeeklyManage = new TbWeeklyManage();
        tbWeeklyManage.setUserId(1L);
        tbWeeklyManage.setScopeWeek("2024-12-16~2024-12-22");
        tbWeeklyManage.setDeptId(101L);
        // 按照 deptId 部门，通过用户、部门、周报表进行查询，能得到该部门所有用户的周报，并将周报内容拼接到 req 中，
        // 利用 ollamaTongyiAi 调用 ai 模型，得到结果 res
        List<TbWeeklyManage> tbWeeklyManages = tbWeeklyManageService.selectTbWeeklyManageList(tbWeeklyManage);
        StringBuilder req = new StringBuilder();
        req.append("将下列的周报进行整理总结，对于涉及同一项目的多个工作日志，请将它们合并为一个综合性的总结，");
        req.append("具体可以通过（识别共同主题）（量化工作量）（突出重要事件）（简化语言）总结");
        for (TbWeeklyManage tbWeeklyManage1 : tbWeeklyManages) {
            req.append(tbWeeklyManage1.getContext());
        }
        req.append("生成内容要求：按有序编号排列（相同项目归为一起）每一个序号前后添加上 <p></p> 标签，便于显示，并进行换行，不要额外符号");
        log.info("req:\n{}", req);
        String res = olamaTongyiAi.ai(req.toString());
        log.info("res:\n{} ", res);
        return res;
    }

    @GetMapping("/summary4")
    public String summary4() throws Exception{
        TbWeeklyManage tbWeeklyManage = new TbWeeklyManage();
        tbWeeklyManage.setUserId(1L);
        tbWeeklyManage.setScopeWeek("2024-12-16~2024-12-22");
        tbWeeklyManage.setDeptId(101L);
        List<TbWeeklyManage> tbWeeklyManages = tbWeeklyManageService.selectTbWeeklyManageList(tbWeeklyManage);
        StringBuilder req = new StringBuilder();
        req.append("将下列的周报进行整理总结，对于涉及同一项目的多个工作日志，请将它们合并为一个综合性的总结，");
        req.append("具体可以通过（识别共同主题）（量化工作量）（突出重要事件）（简化语言）总结");
        for (TbWeeklyManage tbWeeklyManage1 : tbWeeklyManages) {
            req.append(tbWeeklyManage1.getContext());
        }
        req.append("生成内容要求：按有序编号排列（相同项目归为一起）每一个序号前后添加上 <p></p> 标签，便于显示，并进行换行，不要额外符号");
        log.info("req:\n{}", req);
        String res = zhiPuAi.ai(req.toString());
        log.info("res:\n{} ", res);
        return res;
    }

    @GetMapping("/method")
    public String method() throws IOException {
        TbWeeklyManage tbWeeklyManage = new TbWeeklyManage();
        tbWeeklyManage.setUserId(1L);
        tbWeeklyManage.setScopeWeek("2024-12-16~2024-12-22");
        tbWeeklyManage.setDeptId(101L);
//        List<TbWeeklyManage> tbWeeklyManages = tbWeeklyManageService.selectTbWeeklyManageList(tbWeeklyManage);
        List<TbWeeklyManage> tbWeeklyManages = new ArrayList<>();
        TbWeeklyManage t1 = new TbWeeklyManage();
        t1.setContext("<p><strong>山西两个细则工作</strong></p><p>1.储能两个细则系统测试bug 修复</p><p>2.偏差收益测算，功能讨论设计</p><p>3.望狐风电场并网子站系统交流</p>");
        TbWeeklyManage t2 = new TbWeeklyManage();
        t2.setContext("<p><strong>中煤：</strong></p><p>1.火电首页数据展示不全问题修改；</p><p>2.火电收益概况展示问题修改；</p><p>3.火电隐藏中长期基数等信息修改；</p><p>4.火电成本参数维护页面迁移；</p><p>5.现货申报页面调试；</p><p>6.核对日前、中长期、成本等数据；</p><p>7.线上配置菜单与角色等；</p><p>8.配合上线部署火电系统；</p>");
        TbWeeklyManage t3 = new TbWeeklyManage();
        t3.setContext("<p>中煤项目</p><p>1、沟通火电系统修改项</p><p>2、售电需求沟通与安排</p><p>3、客户对接问题沟通等</p><p>4、部署上线火电部分与营销自定义报表部分</p><p>5、数据同步等问题沟通</p><p>6、其他问题沟通</p>");
        TbWeeklyManage t4 = new TbWeeklyManage();
        t4.setContext("<p><strong>象山双碳：</strong></p><p>1.需求变更研发完，并向客户演示</p>\n" +
                "<p><strong>国网新源：</strong></p><p>1.前端页面开发</p><p>2.首页、市场概述、运行统计、全网接口检修接口开发</p><p>3.和客户汇报新需求完成情况</p>");
        TbWeeklyManage t5 = new TbWeeklyManage();
        t5.setContext("<p><strong>中煤项目</strong></p><p>1.根据需求，完成火电系统-数据看板的页面调整</p><p>2.参与中煤开发需求会议</p><p>3.针对legend和toolbox重叠的问题，对echarts进行优化</p><p>4.根据中煤系统的标准，完成块组件的统一化构成</p><p>5.优化中煤火电系统-价差分析的页面布局</p><p>6.解决价差分析页面不显示数据的问题</p><p>7.优化交易日历中触碰的样式显示</p><p>8.优化中煤火电-现货申报辅助决策页面的样式和布局</p><p>9.优化中煤火电-收益概况的样式</p><p>10.解决和优化中煤系统左侧菜单的样式和布局</p><p>11.根据火电系统的需求，完成系统多个页面的业务调整</p><p>12.解决旬交易中页面中长期合约调整的功能不能显示问题</p><p>13.解决中煤系统点击菜单出现的白屏问题</p><p>14.优化中煤火电多个火电页面echarts样式的标准化</p><p>15.完成中煤--售电系统的首页</p><p>16.解决中煤火电系统--成本分析预测页面中dialog不显示的问题</p><p>17.完成售电系统中-披露数据的优化</p><p>18.解决avue-crud中dicUrl的问题</p><p>19.完成售电系统-用户短期负荷预测功能和布局优化</p><p>20.完成售电系统-用户中长期负荷预测功能和布局优化</p><p>21.解决中煤-火电系统-成本预测分析中弹窗不能显示和弹窗显示有误的问题；同时优化火电系统中多个页面的弹窗样式</p>");
        TbWeeklyManage t6 = new TbWeeklyManage();
        t6.setContext("<p><strong>国网新源</strong></p><p>1、国网新源公司抽蓄APP项目首页界面设计</p><p>2、国网新源公司抽蓄APP项目运行指标界面设计</p><p>3、国网新源公司抽蓄APP项目菜单界面设计</p><p>4、国网新源公司抽蓄APP项目量价策略界面设计</p><p>5、国网新源公司抽蓄APP项目界面切图</p><p>6、蒙东霍林郭勒增量配电网项目界面切图</p><p>7、中煤营销管理首页界面设计</p><p>8、中煤营销管理报表管理界面设计</p>");
        TbWeeklyManage t7 = new TbWeeklyManage();
        t7.setContext("<p><strong>其它</strong></p><p>1、日滚动自动申报功能开发</p><p>2、零碳电厂验收文档编写</p><p>3、京能单点登录配合、营销系统登录开发</p>");
        TbWeeklyManage t8 = new TbWeeklyManage();
        t8.setContext("<p><strong>中煤</strong></p><p>1.中煤全网长周期电价预测调试、节点长周期电价预测调试</p><p>2.中煤全网电价预测调试</p><p>3.中煤火电日前申报策略调试、相关火电报价参数梳理、表结构整理</p><p>4.京能统一登录认证结构梳理、相关参数沟通</p><p>5.算法中间库推送出清电价数据存在交易中心披露都是0的情况，各省数据做校验</p><p>6.售电日前申报策略开发、调试</p>");
        TbWeeklyManage t9 = new TbWeeklyManage();
        t9.setContext("<p><strong>新疆：</strong></p><p>1.新能源月结算单数据采集工作完成；</p><p>2.新能源售电方明细采集工作完成；</p><p>3.新能源交易披露采集工作完成；</p><p>4.新能源交易公告，分时段数据上下限，交易明细的数据采集工作完成；</p><p>5.售电的相关采集工作；</p>");
        TbWeeklyManage t10 = new TbWeeklyManage();
        t10.setContext("<p><strong>爱国网：</strong></p><p>1.首页功能页面的修改；</p><p>2.菜单页面布局修改；</p><p>3.新增量价策略页面的开发；</p><p>4.运行指标页面的功能修改。</p>");
        TbWeeklyManage t11 = new TbWeeklyManage();
        t11.setContext("<p><strong>用电管理</strong></p><p>1、用电申报管理：</p><p>（1）按月导出数据（区分居间和售电公司；隐藏偏差率列）；</p><p>（2）修改全选用户点击月份按月查询报错；</p><p>（3）居间商导入进行限制（每月15日）；</p><p>（4）优化按月导入逻辑；</p><p>（5）修复24点查询错误问题；</p><p>（6）优化分解逻辑和加导入限制；</p><p>（7）根据2024年维护2025年尖峰平谷时刻点；</p><p>（8）按年导入：增加提示（提示已经按月导入的用户）</p>\n" +
                "<p><strong>电价预测</strong></p><p>2、电价预测：排查电价预测未预测出原因，并重新进行预测；电价预测时间调整为每天8点、9点各执行一次</p>\n" +
                "<p><strong>售电电价预测</strong></p><p>3、售电电价预测：排查电价预测数据未显示问题；</p>");
        TbWeeklyManage t12 = new TbWeeklyManage();
        t12.setContext("<p><strong>湖北国电投：</strong></p><p>1.开发新版日报功能</p><p><strong>京能：</strong></p><p>1.协助开发统一认证登录接口</p>\n" +
                "<p><strong>象山双碳：</strong></p><p>1.优化界面UI</p>");
        TbWeeklyManage t13 = new TbWeeklyManage();
        t13.setContext("<ol><li>自定义表单月维度发电侧基础页面开发</li><li>自定义表单月维度excel导出样式优化</li><li>自定义表单月维度售电侧基础页面开发</li><li>成本分析模块-成本导入（月维度）</li><li>月报模块金额计算单位错误bug修改</li><li>word月报模板推进，剩余文档中的表格和图表数据。\n" +
                "</li></ol>\n" +
                "\n" +
                "<p><strong>零售管理：</strong></p><p>1:优化零售管理各模块的页面UI显示；</p><p>2：修改套餐管理、用户管理后端，新接口支持指定多个售电公司进行查询；</p><p>3：调整合同管理列表中操作按钮的功能正常显示；</p><p>4：熟悉零售套餐测算、套餐评价的接口逻辑、并造数据，支持页面功能显示。</p>\n" +
                "\n" +
                "<p><strong>电厂</strong></p><p>1、检查专责关于考核结果的问题</p><p>2、核对电厂容量信息</p><p>3、整理并导入上网电量</p><p>4、测试风功率预测计算</p><p>1、售电辅助交易系统（项目经理刘雨欢）</p><p>   孙瑞东测试模块（交易分组、策略维护、交易信息管理、交易申报）， 焦庆测试模块（市场分析、行情动态、市场预测），共提5个bug</p><p>  </p>\n" +
                "<p><strong>双碳项目</strong></p><p>2、双碳项目的测试（项目经理田晓飞）</p><p>   更新客户线上环境，更新完后进行bug的验证测试</p><p>   </p>\n" +
                "\n" +
                "<p><strong>3、中煤项目（项目经理张鑫）</strong></p><p>   （1）测试模块（首页、数据看板、计划管理、交易管理、复盘管理），共提8个bug</p><p>   （2）进行数据库数据的迁移的工作</p><p>   （3）在客户环境部署中煤一体化环境，并解决出现的问题</p>");
        TbWeeklyManage t14 = new TbWeeklyManage();
        t14.setContext("<p><strong>项目管理系统</strong></p><ol><li>周报自动总结</li></ol><p><strong>图书</strong></p><p>图设自动排序</p>");
        tbWeeklyManages.add(t1);
        tbWeeklyManages.add(t2);
        tbWeeklyManages.add(t3);
        tbWeeklyManages.add(t4);
        tbWeeklyManages.add(t5);
        tbWeeklyManages.add(t6);
        tbWeeklyManages.add(t7);
        tbWeeklyManages.add(t8);
        tbWeeklyManages.add(t9);
        tbWeeklyManages.add(t10);
        tbWeeklyManages.add(t11);
        tbWeeklyManages.add(t12);
        tbWeeklyManages.add(t13);
        tbWeeklyManages.add(t14);
        log.info("tbWeeklyManages:\n{}", tbWeeklyManages);
        return summaryWeek(tbWeeklyManages);
    }

    // 辅助方法：清理并规范化输入内容
    private static String cleanAndNormalize(String content) {
        // 移除多余的空白字符
        content = content.trim();
        // 替换连续的换行符为单个换行符
        content = content.replaceAll("\\r\\n|\\r|\\n", "\n");
        // 可以根据需要添加更多的清理规则
        return content;
    }

    public String summaryWeek(List<TbWeeklyManage> tbWeeklyManages) throws IOException {
        // 创建一个 Map 用于存储按项目归类的任务信息
        Map<String, List<String>> projectTasks = new LinkedHashMap<>();
        for (TbWeeklyManage tbWeeklyManage : tbWeeklyManages) {
            // 清理并规范化输入内容
            if (tbWeeklyManage.getContext() == null) continue;
            String cleanedContent = cleanAndNormalize(tbWeeklyManage.getContext());

            // 将内容包裹在一个完整的HTML文档结构中
            String htmlDocument = "<html><body>" + cleanedContent + "</body></html>";

            try {
                // 解析HTML文档
                Document doc = Jsoup.parse(htmlDocument, "", Parser.htmlParser());
                // Elements items = doc.select("p"); // 假设每个任务都在<p>标签内

                String currentProject = null;

                // 遍历所有元素 （包括 p  ol ul）
                Elements items = doc.body().children();

                for (Element item : items) {
                    String task = item.text();

                    // 检查是否为项目标题（即包含<strong>标签）
                    if (item.select("strong").first() != null) {
                        currentProject = item.text().trim();
                    } else if (currentProject != null && !task.trim().isEmpty()) {
                        if (item.tagName().equals("p")) {
                            projectTasks.computeIfAbsent(currentProject, k -> new ArrayList<>()).add(item.text().trim());
                        } else if (item.tagName().equals("ol") || item.tagName().equals("ul")) {
                            // 处理有序列表和无序列表
                            Elements listItems = item.select("li");
                            for (Element listItem : listItems) {
                                projectTasks.computeIfAbsent(currentProject, k -> new ArrayList<>()).add(listItem.text().trim());
                            }
                        }
                    }
                }
            } catch (Exception e) {
                // 处理解析过程中可能出现的异常
                log.error("解析周报内容时出错: " + e.getMessage(), e);
            }
        }
        if (projectTasks.isEmpty()) {return null;}
        // 构建请求字符串
        StringBuilder req = new StringBuilder();
        req.append("将下列的周报进行整理总结，对于涉及同一项目的多个工作日志，请将它们合并为一个综合性的总结，");
        req.append("具体可以通过（识别共同主题）（量化工作量）（突出重要事件）（简化语言）总结，其中 <strong></strong> 标签中内容为项目名称");

        projectTasks.forEach((key, value) -> System.out.println(key + ": " + value));

        mergeSimilarProjects(projectTasks);

        // 按照项目名称排序后追加到请求中
        projectTasks.forEach((project, tasks) -> {
            req.append("<p><strong>").append(project).append("</strong></p>");
            tasks.forEach(task -> req.append("<p>").append(task).append("</p>"));
        });

        projectTasks.forEach((key, value) -> System.out.println(key + ": " + value));

        req.append("生成内容要求：按有序编号排列（相同项目归为一起，编号和项目名称在一起，不要换行）每个项目中的内容不需要编号，每一个序号前后添加上 <p></p> 标签，便于显示，不要额外符号，根据实际内容总结，不可捏造未出现数据");

        log.info("项目名称汇总后的req:\n{}", req);
        String res = zhiPuAi.ai(req.toString());
        log.info("ai汇总后的res:\n{} ", res);
        return res;
    }

    private void handleParagraph(Element p, Map<String, List<String>> projectTasks, String[] currentProject) {
        if (!p.select("strong").isEmpty()) {
            // 更新当前项目名称
            currentProject[0] = p.text().trim();
        } else if (currentProject[0] != null && !p.text().trim().isEmpty()) {
            // 添加非空任务到当前项目的任务列表中
            projectTasks.computeIfAbsent(currentProject[0], k -> new ArrayList<>()).add(p.text().trim());
        }
    }

    private void handleList(Element list, Map<String, List<String>> projectTasks, String[] currentProject) {
        if (currentProject[0] == null) {
            return;
        }

        Elements items = list.select("li");
        for (Element item : items) {
            String task = item.text().trim();
            if (!task.isEmpty()) {
                projectTasks.computeIfAbsent(currentProject[0], k -> new ArrayList<>()).add(task);
            }
        }
    }

    private static final double SIMILARITY_THRESHOLD = 0.5; // 定义相似度阈值

    private static void mergeSimilarProjects(Map<String, List<String>> projectTasks) {
        JaroWinklerSimilarity similarity = new JaroWinklerSimilarity();

        // 创建一个新的LinkedHashMap来保存合并后的结果
        Map<String, List<String>> mergedProjects = new LinkedHashMap<>();

        for (String key : projectTasks.keySet()) {
            boolean merged = false;
            for (String existingKey : new ArrayList<>(mergedProjects.keySet())) {
                if (similarity.apply(existingKey, key) >= SIMILARITY_THRESHOLD) {
                    // 如果相似度超过阈值，则合并任务列表
                    mergedProjects.get(existingKey).addAll(projectTasks.get(key));
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                // 如果没有找到相似的键，则直接添加
                mergedProjects.put(key, projectTasks.get(key));
            }
        }

        // 将合并后的结果赋值给原始map
        projectTasks.clear();
        projectTasks.putAll(mergedProjects);
    }

    @GetMapping("demo")
    public String demo() {
        return "<p>1、Ukey管理工具以及自动化爬虫的重新设计和讨论定型</p>" +
                "<p>2、陕投水电项目建议书多版本修改以及沟通</p>" +
                "<p>3、陕交控售电系统交流介绍以及报价</p>" +
                "<p>4、清水川火电成本分析项目技术规范编写</p>";
    }
}
