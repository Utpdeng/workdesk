package com.dil.glm.domain.weekly;

import java.util.Date;

public class TbWeeklyManage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户id */
    private Long userId;
    private Long deptId;

    /** 周报周期 */
    private String scopeWeek;
    private String nickName;

    /** 周报内容 */
    private String context;

    /** $column.columnComment */
    private String loguser;

    /** $column.columnComment */
    private Date logtime;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setScopeWeek(String scopeWeek) 
    {
        this.scopeWeek = scopeWeek;
    }

    public String getScopeWeek() 
    {
        return scopeWeek;
    }
    public void setContext(String context) 
    {
        this.context = context;
    }

    public String getContext() 
    {
        return context;
    }
    public void setLoguser(String loguser) 
    {
        this.loguser = loguser;
    }

    public String getLoguser() 
    {
        return loguser;
    }
    public void setLogtime(Date logtime) 
    {
        this.logtime = logtime;
    }

    public Date getLogtime() 
    {
        return logtime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "TbWeeklyManage{" +
                "id=" + id +
                ", userId=" + userId +
                ", deptId=" + deptId +
                ", scopeWeek='" + scopeWeek + '\'' +
                ", nickName='" + nickName + '\'' +
                ", context='" + context + '\'' +
                ", loguser='" + loguser + '\'' +
                ", logtime=" + logtime +
                '}';
    }
}