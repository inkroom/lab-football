<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="ad1">
    <div class="ad_img">
        <span id="close1">×</span>
        <c:if test="${advertisementFirst[0]!=null }">
			<div class="ad_img">
				<img alt="" src="${picPath }${advertisementFirst[0].save_path}" width="80px" height="80px">
				<p><a target="_blank" href="${advertisementFirst[0].url_path}">${advertisementFirst[0].title}</a></p>
			</div>
		</c:if>
		<c:if test="${advertisementFirst[0] == null }">
			<div class="ad_img">
		        <img src="${basePath }/resources_new/img/link1.png" alt="app"><br>
		        <p>APP test</p>
		    </div>
		</c:if>
    </div>
    
    
    <div class="ad_img">
        <c:if test="${advertisementFirst[1]!=null }">
			<div class="ad_img">
				<img alt="" src="${picPath }${advertisementFirst[1].save_path}" width="80px" height="80px">
				<p><a target="_blank" href="${advertisementFirst[1].url_path}">${advertisementFirst[1].title}</a></p>
			</div>
		</c:if>
		<c:if test="${advertisementFirst[1] == null }">
			<div class="ad_img">
		        <img src="${basePath }/resources_new/img/link1.png" alt="app"><br>
		        <p>APP test</p>
		    </div>
		</c:if>
    </div>
    
    <div class="ad_img">
        <c:if test="${advertisementFirst[2]!=null }">
			<div class="ad_img">
				<img alt="" src="${picPath }${advertisementFirst[2].save_path}" width="80px" height="80px">
				<p><a target="_blank" href="${advertisementFirst[2].url_path}">${advertisementFirst[2].title}</a></p>
			</div>
		</c:if>
		<c:if test="${advertisementFirst[2] == null }">
			<div class="ad_img">
		        <img src="${basePath }/resources_new/img/link1.png" alt="app"><br>
		        <p>APP test</p>
		    </div>
		</c:if>
    </div>
    
</div>




<div id="ad2">

    
    <div class="ad_img">
        <span id="close2">×</span>
        <c:if test="${advertisementFirst[3]!=null }">
			<div class="ad_img">
				<img alt="" src="${picPath }${advertisementFirst[3].save_path}" width="80px" height="80px">
				<p><a target="_blank" href="${advertisementFirst[3].url_path}">${advertisementFirst[3].title}</a></p>
			</div>
		</c:if>
		<c:if test="${advertisementFirst[3] == null }">
			<div class="ad_img">
		        <img src="${basePath }/resources_new/img/link1.png" alt="app"><br>
		        <p>APP test</p>
		    </div>
		</c:if>
    </div>
    
    
    
     <div class="ad_img">
        <c:if test="${advertisementFirst[4]!=null }">
			<div class="ad_img">
				<img alt="" src="${picPath }${advertisementFirst[4].save_path}" width="80px" height="80px">
				<p><a target="_blank" href="${advertisementFirst[4].url_path}">${advertisementFirst[4].title}</a></p>
			</div>
		</c:if>
		<c:if test="${advertisementFirst[4] == null }">
			<div class="ad_img">
		        <img src="${basePath }/resources_new/img/link1.png" alt="app"><br>
		        <p>APP test</p>
		    </div>
		</c:if>
    </div>
    
    
    
    <div class="ad_img">
        <c:if test="${advertisementFirst[5]!=null }">
			<div class="ad_img">
				<img alt="" src="${picPath }${advertisementFirst[5].save_path}" width="80px" height="80px">
				<p><a target="_blank" href="${advertisementFirst[5].url_path}">${advertisementFirst[5].title}</a></p>
			</div>
		</c:if>
		<c:if test="${advertisementFirst[5] == null }">
			<div class="ad_img">
		        <img src="${basePath }/resources_new/img/link1.png" alt="app"><br>
		        <p>APP test</p>
		    </div>
		</c:if>
    </div>
    
    
</div> --%>