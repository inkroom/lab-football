<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="info">
	<img src="${basePath }/resources_new/img/1.png" alt="">
	<ul>
		<li>
			<c:if test="${schoolNews[0] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/02/${schoolNews[0].sc_news}">${schoolNews[0].title}</a>
				<span><fmt:formatDate value="${schoolNews[0].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<%--<c:if test="${schoolNews[0] == null}">--%>
				<%--<a href="#">&nbsp&nbsp校&nbsp&nbsp园&nbsp&nbsp新&nbsp&nbsp闻&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${schoolNews[1] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/02/${schoolNews[1].sc_news}">${schoolNews[1].title}</a>
				<span><fmt:formatDate value="${schoolNews[1].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			<%--<c:if test="${schoolNews[1] == null}">--%>
				<%--<a href="#">&nbsp&nbsp校&nbsp&nbsp园&nbsp&nbsp新&nbsp&nbsp闻&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${schoolNews[2] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/02/${schoolNews[2].sc_news}">${schoolNews[2].title}</a>
				<span><fmt:formatDate value="${schoolNews[2].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<c:if test="${schoolNews[2] == null}">
				<a href="#">&nbsp&nbsp校&nbsp&nbsp园&nbsp&nbsp新&nbsp&nbsp闻&nbsp&nbsp标&nbsp&nbsp题</a>
				<span>04-13</span>
			</c:if>
		</li>
		
		<li><c:if test="${schoolNews[3] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/02/${schoolNews[3].sc_news}">${schoolNews[3].title}</a>
				<span><fmt:formatDate value="${schoolNews[3].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<c:if test="${schoolNews[3] == null}">
				<a href="#">&nbsp&nbsp校&nbsp&nbsp园&nbsp&nbsp新&nbsp&nbsp闻&nbsp&nbsp标&nbsp&nbsp题</a>
				<span>04-13</span>
			</c:if>
		</li>
		
		<li><c:if test="${schoolNews[4] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/02/${schoolNews[4].sc_news}">${schoolNews[4].title}</a>
				<span><fmt:formatDate value="${schoolNews[4].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<c:if test="${schoolNews[4] == null}">
				<a href="#">&nbsp&nbsp校&nbsp&nbsp园&nbsp&nbsp新&nbsp&nbsp闻&nbsp&nbsp标&nbsp&nbsp题</a>
				<span>04-13</span>
			</c:if>
		</li>
		
		<li>
			<c:if test="${schoolNews[5] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/02/${schoolNews[5].sc_news}">${schoolNews[5].title}</a>
				<span><fmt:formatDate value="${schoolNews[5].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<c:if test="${schoolNews[5] == null}">
				<a href="#">&nbsp&nbsp校&nbsp&nbsp园&nbsp&nbsp新&nbsp&nbsp闻&nbsp&nbsp标&nbsp&nbsp题</a>
				<span>04-13</span>
			</c:if>
		</li>




		<p>
			<a href="${basePath }/${param.get('schoolUrl') }/02">更多&raquo;</a>
		</p>
	</ul>
</div>
<div class="tui">
	<img src="${basePath }/resources_new/img/1.png" alt="">
	<ul>
		<li>
			<c:if test="${studyRecommend[0] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/06/${studyRecommend[0].sc_news}">${studyRecommend[0].title}</a>
				<span><fmt:formatDate value="${studyRecommend[0].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<%--<c:if test="${studyRecommend[0] == null}">--%>
				<%--<a href="#">&nbsp&nbsp学&nbsp&nbsp习&nbsp&nbsp推&nbsp&nbsp荐&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${studyRecommend[1] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/06/${studyRecommend[1].sc_news}">${studyRecommend[1].title}</a>
				<span><fmt:formatDate value="${studyRecommend[1].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<%--<c:if test="${studyRecommend[1] == null}">--%>
				<%--<a href="#">&nbsp&nbsp学&nbsp&nbsp习&nbsp&nbsp推&nbsp&nbsp荐&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${studyRecommend[2] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/06/${studyRecommend[2].sc_news}">${studyRecommend[2].title}</a>
				<span><fmt:formatDate value="${studyRecommend[2].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<c:if test="${studyRecommend[2] == null}">
				<a href="#">&nbsp&nbsp学&nbsp&nbsp习&nbsp&nbsp推&nbsp&nbsp荐&nbsp&nbsp标&nbsp&nbsp题</a>
				<span>04-13</span>
			</c:if>
		</li>
		
		<li>
			<c:if test="${studyRecommend[3] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/06/${studyRecommend[3].sc_news}">${studyRecommend[3].title}</a>
				<span><fmt:formatDate value="${studyRecommend[3].oper_date }"
						pattern="MM-dd" /></span>
			</c:if> 
			<%--<c:if test="${studyRecommend[3] == null}">--%>
				<%--<a href="#">&nbsp&nbsp学&nbsp&nbsp习&nbsp&nbsp推&nbsp&nbsp荐&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${studyRecommend[4] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/06/${studyRecommend[4].sc_news}">${studyRecommend[4].title}</a>
				<span><fmt:formatDate value="${studyRecommend[4].oper_date }"
						pattern="MM-dd" /></span>
			</c:if> 
			<%--<c:if test="${studyRecommend[4] == null}">--%>
				<%--<a href="#">&nbsp&nbsp学&nbsp&nbsp习&nbsp&nbsp推&nbsp&nbsp荐&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${studyRecommend[5] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/06/${studyRecommend[5].sc_news}">${studyRecommend[5].title}</a>
				<span><fmt:formatDate value="${studyRecommend[5].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			<%--<c:if test="${studyRecommend[5] == null}">--%>
				<%--<a href="#">&nbsp&nbsp学&nbsp&nbsp习&nbsp&nbsp推&nbsp&nbsp荐&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<p>
			<a href="${basePath }/${param.get('schoolUrl') }/06">更多&raquo;</a>
		</p>
	</ul>
</div>
<div class="video">

	<div>
		<a href="${basePath }/${param.get('schoolUrl') }/03"> <span>学生风采</span>
			<img class="mask" src="${basePath }/resources_new/img/masking.png" alt="">
			<img src="${basePath }/resources_new/img/2.png" alt="">
		</a>
	</div>

	<div>
		<a href="${basePath }/${param.get('schoolUrl') }/04"> <span>比赛展示</span>
			<img class="mask" src="${basePath }/resources_new/img/masking.png" alt="">
			<img src="${basePath }/resources_new/img/2.png" alt="">
		</a>
	</div>
	<div>
		<a href="${basePath }/${param.get('schoolUrl') }/05"> <span>作品展示</span>
			<img class="mask" src="${basePath }/resources_new/img/masking.png" alt="">
			<img src="${basePath }/resources_new/img/2.png" alt="">
		</a>
	</div>
</div>
<div class="zqts">
	<img src="${basePath }/resources_new/img/1.png" alt="">
	<ul>
		<li>
			<c:if test="${footballCourse[0] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/03/${footballCourse[0].sc_feature}">${footballCourse[0].title}</a>
				<span><fmt:formatDate value="${footballCourse[0].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<%--<c:if test="${footballCourse[0] == null}">--%>
				<%--<a href="#">&nbsp&nbsp足&nbsp&nbsp球&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${footballCourse[1] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/03/${footballCourse[1].sc_feature}">${footballCourse[1].title}</a>
				<span><fmt:formatDate value="${footballCourse[1].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<%--<c:if test="${footballCourse[1] == null}">--%>
				<%--<a href="#">&nbsp&nbsp足&nbsp&nbsp球&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${footballCourse[2] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/03/${footballCourse[2].sc_feature}">${footballCourse[2].title}</a>
				<span><fmt:formatDate value="${footballCourse[2].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<%--<c:if test="${footballCourse[2] == null}">--%>
				<%--<a href="#">&nbsp&nbsp足&nbsp&nbsp球&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${footballCourse[3] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/03/${footballCourse[3].sc_feature}">${footballCourse[3].title}</a>
				<span><fmt:formatDate value="${footballCourse[3].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			<%--<c:if test="${footballCourse[3] == null}">--%>
				<%--<a href="#">&nbsp&nbsp足&nbsp&nbsp球&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${footballCourse[4] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/03/${footballCourse[4].sc_feature}">${footballCourse[4].title}</a>
				<span><fmt:formatDate value="${footballCourse[4].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			<%--<c:if test="${footballCourse[4] == null}">--%>
				<%--<a href="#">&nbsp&nbsp足&nbsp&nbsp球&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${footballCourse[5] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/03/${footballCourse[5].sc_feature}">${footballCourse[5].title}</a>
				<span><fmt:formatDate value="${footballCourse[5].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			<%--<c:if test="${footballCourse[5] == null}">--%>
				<%--<a href="#">&nbsp&nbsp足&nbsp&nbsp球&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<p>
			<a href="${basePath }/${param.get('schoolUrl') }/04">更多&raquo;</a>
		</p>
	</ul>
</div>
<div class="ysts">
	<img src="${basePath }/resources_new/img/1.png" alt="">
	<ul>
		<li>
			<c:if test="${artCourse[0] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/05/${artCourse[0].sc_feature}">${artCourse[0].title}</a>
				<span><fmt:formatDate value="${artCourse[0].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			
			<%--<c:if test="${artCourse[0] == null}">--%>
				<%--<a href="#">&nbsp&nbsp艺&nbsp&nbsp术&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${artCourse[1] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/05/${artCourse[1].sc_feature}">${artCourse[1].title}</a>
				<span><fmt:formatDate value="${artCourse[1].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			<%--<c:if test="${artCourse[1] == null}">--%>
				<%--<a href="#">&nbsp&nbsp艺&nbsp&nbsp术&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${artCourse[2] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/05/${artCourse[2].sc_feature}">${artCourse[2].title}</a>
				<span><fmt:formatDate value="${artCourse[2].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			<%--<c:if test="${artCourse[2] == null}">--%>
				<%--<a href="#">&nbsp&nbsp艺&nbsp&nbsp术&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${artCourse[3] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/05/${artCourse[0].sc_feature}">${artCourse[3].title}</a>
				<span><fmt:formatDate value="${artCourse[3].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			<%--<c:if test="${artCourse[3] == null}">--%>
				<%--<a href="#">&nbsp&nbsp艺&nbsp&nbsp术&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${artCourse[4] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/05/${artCourse[4].sc_feature}">${artCourse[4].title}</a>
				<span><fmt:formatDate value="${artCourse[4].oper_date }"
						pattern="MM-dd" /></span>
			</c:if> 
			<%--<c:if test="${artCourse[4] == null}">--%>
				<%--<a href="#">&nbsp&nbsp艺&nbsp&nbsp术&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		
		<li>
			<c:if test="${artCourse[5] != null}">
				<a
					href="${basePath }/${param.get('schoolUrl') }/05/${artCourse[5].sc_feature}">${artCourse[5].title}</a>
				<span><fmt:formatDate value="${artCourse[5].oper_date }"
						pattern="MM-dd" /></span>
			</c:if>
			<%--<c:if test="${artCourse[5] == null}">--%>
				<%--<a href="#">&nbsp&nbsp艺&nbsp&nbsp术&nbsp&nbsp课&nbsp&nbsp程&nbsp&nbsp标&nbsp&nbsp题</a>--%>
				<%--<span>04-13</span>--%>
			<%--</c:if>--%>
		</li>
		<p>
			<a href="${basePath }/${param.get('schoolUrl') }/05">更多&raquo;</a>
		</p>
	</ul>
</div>
