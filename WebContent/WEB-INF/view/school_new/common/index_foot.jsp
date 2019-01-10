<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<p>
		<c:if test="${advertisementThird!=null }">
			<c:if test="${advertisementThird[0]!=null}">
				<a target="_blank" href="${advertisementThird[0].url_path }" title="${advertisementThird[0].title }">${advertisementThird[0].title }</a>
			</c:if>
			<c:if test="${advertisementThird[1]!=null}">
				<a target="_blank" href="${advertisementThird[1].url_path }" title="${advertisementThird[1].title }">${advertisementThird[1].title }</a>
			</c:if>
			<c:if test="${advertisementThird[2]!=null}">
				<a target="_blank" href="${advertisementThird[2].url_path }" title="${advertisementThird[2].title }">${advertisementThird[2].title }</a>
			</c:if>
			<c:if test="${advertisementThird[3]!=null}">
				<a target="_blank" href="${advertisementThird[3].url_path }" title="${advertisementThird[3].title }">${advertisementThird[3].title }</a>
			</c:if>
			<c:if test="${advertisementThird[4]!=null}">
				<a target="_blank" href="${advertisementThird[4].url_path }" title="${advertisementThird[4].title }">${advertisementThird[4].title }</a>
			</c:if>
			<c:if test="${advertisementThird[5]!=null}">
				<a target="_blank" href="${advertisementThird[5].url_path }" title="${advertisementThird[5].title }">${advertisementThird[5].title }</a>
			</c:if>
		</c:if>
	</p>


	<%-- <div style="margin-left:60px">
		<img alt="" src="${basePath }/resources_new/adv/001.png" width="1050px" height="129px">
    </div> --%>

    <div class="PrimarySelect" style="margin-top:20px;">
	
	 <select onchange="eval(this.value);">
	 	<option value="void(0)">---体育相关网站---</option>
		<c:if test="${advertisementFouth1 != null }">
			<c:forEach items="${advertisementFouth1 }" var="ad">
				<option value="window.open(&#39;${ad.url_path }/&#39;,&#39;_blank&#39;);">${ad.title }</option>
			</c:forEach>
		</c:if>
		 <c:if test="${advertisementFouth1 == null }">
	         <option value="window.open(&#39;http://www.cdedu.gov.cn/&#39;,&#39;_blank&#39;);">成都市教育局</option>
	         <option value="window.open(&#39;http://www.my-edu.net/&#39;,&#39;_blank&#39;);">绵阳市教育局</option>
	         <option value="window.open(&#39;http://www.dy-edu.cn/&#39;,&#39;_blank&#39;);">德阳市教育局</option>
	         <option value="window.open(&#39;http://www.sczgjy.gov.cn/&#39;,&#39;_blank&#39;);">自贡市教育局 </option>
	         <option value="window.open(&#39;http://www.pzhedu.cn/&#39;,&#39;_blank&#39;);">攀枝花市教育局</option>
	         <option value="window.open(&#39;http://www.sclzjy.gov.cn&#39;,&#39;_blank&#39;);">泸州市教育局</option>
	         <option value="window.open(&#39;http://www.gyedu.gov.cn/&#39;,&#39;_blank&#39;);">广元市教育局</option>
	         <option value="window.open(&#39;http://www.snsedu.gov.cn/&#39;,&#39;_blank&#39;);">遂宁市教育局</option>
	         <option value="window.open(&#39;http://www.scnjedu.net/&#39;,&#39;_blank&#39;);">内江市教育局</option>
	         <option value="window.open(&#39;http://www.sclsedu.gov.cn/&#39;,&#39;_blank&#39;);">乐山市教育局</option>
	         <option value="window.open(&#39;http://www.zysjyj.com/&#39;,&#39;_blank&#39;);">资阳市教育局</option>
	         <option value="window.open(&#39;http://www.ybedu.gov.cn/html/&#39;,&#39;_blank&#39;);">宜宾市教育局</option>
	         <option value="window.open(&#39;http://www.ncedu.net.cn&#39;,&#39;_blank&#39;);">南充市教育局</option>
	         <option value="window.open(&#39;http://www.yaei.gov.cn&#39;,&#39;_blank&#39;);">雅安市教育局</option>
	         <option value="window.open(&#39;http://www.gaedu.gov.cn/&#39;,&#39;_blank&#39;);">广安市教育局</option>
	         <option value="window.open(&#39;http://www.bzedu.com.cn/&#39;,&#39;_blank&#39;);">巴中市教育局</option>
	         <option value="window.open(&#39;http://www.msedu.cn&#39;,&#39;_blank&#39;);">眉山市教育体育局</option>
	         <option value="window.open(&#39;http://www.abedu.gov.cn/&#39;,&#39;_blank&#39;);">阿坝州教育局</option>
	         <option value="window.open(&#39;http://www.lszedu.cn/&#39;,&#39;_blank&#39;);">凉山彝族自治州教育局</option>
	         <option value="window.open(&#39;http://www.dzei.net/&#39;,&#39;_blank&#39;);">达州市教育局</option>
	         <option value="window.open(&#39;http://www.gzzedu.gov.cn/f&#39;,&#39;_blank&#39;);">甘孜州教育局</option>
		</c:if>
   	</select>
        
     

     <select onchange="eval(this.value);">
    	<option value="void(0)">---艺术相关网站---</option>	
		<c:if test="${advertisementFouth2 != null }">
			<c:forEach items="${advertisementFouth2 }" var="ad">
				<option value="window.open(&#39;${ad.url_path }/&#39;,&#39;_blank&#39;);">${ad.title }</option>
			</c:forEach>
		</c:if>
		<c:if test="${advertisementFouth2 == null }">
         <option value="window.open(&#39;http://www.scu.edu.cn/&#39;,&#39;_blank&#39;);">四川大学</option>
         <option value="window.open(&#39;http://www.uestc.edu.cn/&#39;,&#39;_blank&#39;);">电子科技大学</option>
         <option value="window.open(&#39;http://www.swjtu.edu.cn/&#39;,&#39;_blank&#39;);">西南交通大学</option>
         <option value="window.open(&#39;http://www.swufe.edu.cn/&#39;,&#39;_blank&#39;);">西南财经大学</option>
         <option value="window.open(&#39;http://www.sicau.edu.cn/&#39;,&#39;_blank&#39;);">四川农业大学</option>
         <option value="window.open(&#39;http://www.swun.edu.cn/&#39;,&#39;_blank&#39;);">西南民族大学</option>
         <option value="window.open(&#39;http://www.cdut.edu.cn/&#39;,&#39;_blank&#39;);">成都理工大学</option>
         <option value="window.open(&#39;http://www.swust.edu.cn/&#39;,&#39;_blank&#39;);">西南科技大学</option>
         <option value="window.open(&#39;http://www.xhu.edu.cn/&#39;,&#39;_blank&#39;);">西华大学</option>
         <option value="window.open(&#39;http://www.cdutcm.edu.cn/&#39;,&#39;_blank&#39;);">成都中医药大学</option>
         <option value="window.open(&#39;http://www.sicnu.edu.cn/&#39;,&#39;_blank&#39;);">四川师范大学</option>
         <option value="window.open(&#39;http://www.cwnu.edu.cn/&#39;,&#39;_blank&#39;);">西华师范大学</option>
         <option value="window.open(&#39;http://www.swpu.edu.cn/&#39;,&#39;_blank&#39;);">西南石油大学</option>
         <option value="window.open(&#39;http://www.cuit.edu.cn/&#39;,&#39;_blank&#39;);">成都信息工程大学</option>
         <option value="window.open(&#39;http://www.suse.edu.cn/&#39;,&#39;_blank&#39;);">四川理工学院</option>
         <option value="window.open(&#39;http://www.nsu.edu.cn/&#39;,&#39;_blank&#39;);">成都东软学院</option>
         </c:if>
     </select>
     
     <select onchange="eval(this.value);">
         <option value="void(0)">---直属单位网站---</option>
         <c:if test="${advertisementFouth3 != null }">
			<c:forEach items="${advertisementFouth3 }" var="ad">
				<option value="window.open(&#39;${ad.url_path }/&#39;,&#39;_blank&#39;);">${ad.title }</option>
			</c:forEach>
		</c:if>
		
		<c:if test="${advertisementFouth3 == null }">
         <option value="window.open(&#39;http://www.sceea.cn/&#39;,&#39;_blank&#39;);">四川省教育考试院</option>
         <option value="window.open(&#39;http://www.scjks.net/&#39;,&#39;_blank&#39;);">四川省教育科学研究所</option>
         <option value="window.open(&#39;http://www.scjyzb.net/&#39;,&#39;_blank&#39;);">四川教育装备网</option>
         <option value="window.open(&#39;http://www.scyxhq.com/&#39;,&#39;_blank&#39;);">学校后勤与产业网</option>
         <option value="window.open(&#39;http://www.scdjg.com.cn/&#39;,&#39;_blank&#39;);">四川省电教馆</option>
         <option value="window.open(&#39;http://www.cdxzzx.com/&#39;,&#39;_blank&#39;);">四川省成都西藏中学</option>
         <option value="window.open(&#39;http://www.ntjx.org/&#39;,&#39;_blank&#39;);">内江铁路机械学校</option>
         <option value="window.open(&#39;http://www.scxszz.cn/&#39;,&#39;_blank&#39;);">四川学生资助网</option>
         <option value="window.open(&#39;http://jyzdzx.scedu.net/&#39;,&#39;_blank&#39;);">四川省大学生就业创业服务网</option>
         <option value="window.open(&#39;http://psc.scedu.net/&#39;,&#39;_blank&#39;);">四川省普通话水平测试中心</option>
         </c:if>
     </select>
     <select onchange="eval(this.value);">
         <option value="void(0)">---其他教育网站---</option>
         <c:if test="${advertisementFouth4 != null }">
			<c:forEach items="${advertisementFouth4 }" var="ad">
				<option value="window.open(&#39;${ad.url_path }/&#39;,&#39;_blank&#39;);">${ad.title }</option>
			</c:forEach>
		</c:if>
		<c:if test="${advertisementFouth4 == null }">
         <option value="window.open(&#39;http://www.jyb.cn/&#39;,&#39;_blank&#39;);">中国教育新闻网</option>
         <option value="window.open(&#39;http://www.ict.edu.cn/&#39;,&#39;_blank&#39;);">中国教育信息化网</option>
         <option value="window.open(&#39;http://www.qspfw.edu.cn/&#39;,&#39;_blank&#39;);">教育部全国青少年普法网</option>
         <option value="window.open(&#39;http://www.scedu.com.cn/&#39;,&#39;_blank&#39;);">四川省教育资源公共服务平台</option>
         <option value="window.open(&#39;http://www.scjyjc.com.cn/&#39;,&#39;_blank&#39;);">四川省基础教育监测评估中心</option>
         <option value="window.open(&#39;http://www.scmbjy.net/&#39;,&#39;_blank&#39;);">四川民办教育网</option>
         </c:if>
     </select>
    </div>
    <div class="last">
        <p><span><a href="javascript:;">联系方式</a>&nbsp;|&nbsp;<a href="javascript:;">使用帮助</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>主办：四川省教育厅&nbsp;&nbsp;&nbsp;&nbsp;制作维护：成都东软学院</p>
        <p>投稿邮箱：&nbsp;高等院校:scgxxx@163.com&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市州区县:scjyxx@163.com&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;基层学校:scpjxx@163.com&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;蜀ICP备05006273号</p>
    </div>