<@p.index setReferUrl=true>

<form action="update.action" method="post">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
<div class="container">
    <div class="page-container">
        <div class="col-xs-12 text-center">
            <h4 class="title">基础数据</h4>
        </div>
        <div class="mt-20">
            <table class="table table-border table-bordered table-bg table-hover table-sort">
                <tbody>
                <tr class="text-c">
                    <td>原密码</td>
                    <td><input type="password" class="input-text form-control" id="oldPassword" name="oldPassword" onblur="checkhavaTeacher()" value="${havaTeacher?if_exists}"/></td>
                </tr>	
                <tr class="text-c">
                    <td>新密码</td>
                    <td><input type="password" class="input-text form-control" id="newPassword1" name="newPassword1" onblur="checkhavaTeacher()" value="${havaTeacher?if_exists}"/></td>
                </tr>	
                <tr class="text-c">
                    <td>确认密码</td>
                    <td><input type="password" class="input-text form-control" id="newPassword2" name="newPassword2" onblur="checkhavaTeacher()" value="${havaTeacher?if_exists}"/></td>
                </tr>	
                </tbody>
            </table>
        </div>
    </div>
    <div class=" col-sm-12 text-c">
        <button type="submit"  id="idBtn" onclick="return check()" class="btn btn-success radius">保存</button>
    </div>

</div>

</form>
<script src="${base}/lib/js/jquery-2.1.4.js"></script>
<script src="${base}/lib/js/bootstrap.js"></script>
<script src="${base}/lib/layer/2.1/layer.js"></script>
 <script type="text/javascript" src="${base}/js/md5.js"></script>

<script>
	<#if message?exists && message!="">
		layer.msg('${message?if_exists}');
	</#if>
	
	function check(){
		var old = $('#oldPassword').val();
		var ps1 = $('#newPassword1').val();
		var ps2 = $('#newPassword2').val();
		if(old == "" || ps1 =="" || ps2==""){
			layer.msg("密码不能为空");
			return false;	
		}
		if(old.length <6 || old.length > 16 || ps1.length <6 || ps1.length > 16 || ps2.length <6 || ps2.length > 16){
			layer.msg("密码位数：6-16");
			return false;	
		}
		if(old == ps1 ){
			layer.msg("修改密码不能为原密码");
			return false;
		}
		if(ps2 != ps1 ){
			layer.msg("两次密码不一致");
			return false;
		}
		
		encryptPassword($("#oldPassword"),'${_SALT_IN_SESSION_?if_exists}');
		encryptPassword($("#newPassword1"));
		encryptPassword($("#newPassword2"));
		
	}

</script>
</@p.index>