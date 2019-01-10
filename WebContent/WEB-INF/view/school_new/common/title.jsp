<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<ul>
    <li><a id="home" href="${basePath }/${param.get('schoolUrl')}"
           style="<%=request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")).length()>0?"":"transform: scale(1.3);"%>">首页</a>
    </li>
    <li><a href="${basePath }/${param.get('schoolUrl')}/01" style="<%=request.getRequestURI().contains("01")?"transform: scale(1.3);":""%>">学校概况</a></li>
    <li><a href="${basePath }/${param.get('schoolUrl')}/02" style="<%=request.getRequestURI().contains("02")?"transform: scale(1.3);":""%>">校园资讯</a></li>
    <li><a href="${basePath }/${param.get('schoolUrl')}/03" style="<%=request.getRequestURI().contains("03")?"transform: scale(1.3);":""%>">足球特色</a></li>
    <li><a href="${basePath }/${param.get('schoolUrl')}/04" style="<%=request.getRequestURI().contains("04")?"transform: scale(1.3);":""%>">足球比赛</a></li>
    <li><a href="${basePath }/${param.get('schoolUrl')}/05" style="<%=request.getRequestURI().contains("05")?"transform: scale(1.3);":""%>">艺术特色</a></li>
    <li><a href="${basePath }/${param.get('schoolUrl')}/06" style="<%=request.getRequestURI().contains("06")?"transform: scale(1.3);":""%>">学习推荐</a></li>
    <li><a href="${basePath }/${param.get('schoolUrl')}/07" style="<%=request.getRequestURI().contains("07")?"transform: scale(1.3);":""%>">招生招聘</a></li>
    <li><a href="${ss }">成绩管理</a></li>
    <li><a href="${basePath }/${param.get('schoolUrl')}/08">经费公示</a></li>
</ul>