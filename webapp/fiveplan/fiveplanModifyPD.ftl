<@p.index setReferUrl=true>

<form action="" id="fiveplanmodifyform" method="post">
<input id="token" type="hidden" name="token" value="${token?if_exists}"/>
<div class="container">
    <div class="page-container">

        <div class="col-xs-12 text-center">
            <h4 class="title">修改密码</h4>
        </div>
        <div>

                <table class="table table-bordered">
                    <tbody>
                    <tr class="text-center">
                        <td>原密码</td>
                        <td><input type="password" id="password" name="password" class="input-text form-control"/></td>
                    </tr>
                    <tr class="text-center">
                        <td>新密码</td>
                        <td><input type="password" id="newPassword" name="newPassword" class="input-text form-control  password"/></td>
                    </tr>
                    <tr class="text-center">
                        <td>重新输入密码</td>
                        <td><input type="password" id="confirmPassword" name="confirmPassword" class="input-text form-control  password"/></td>
                    </tr>
                    </tbody>
                </table>

        </div>
    </div>

    <div class=" col-sm-12 text-center">
        <button id="button" type="button" class="btn btn-success radius"><strong>提交</strong></button>
    </div>
	<div id="div"></div>
</div>
</form>
<script src="${base}/lib/js/jquery-2.1.4.js"></script>
<script src="${base}/lib/js/bootstrap.js"></script>
<script src="${base}/lib/layer/2.1/layer.js"></script>
<script type="text/javascript" src="${base}/js/md5.js"></script>
<script>
    $("#button").click(function(){
        for(var i =0;i<$("input").length;i++){
            if($("input").eq(i).val()==""){
                $("input").eq(i).focus();
                layer.msg('请填写完整信息');
                return false;
            }
        }
        if($(".password").eq(0).val().indexOf(" ")>=0){
        	layer.msg('密码不能有空格');
        	return false;
        }
        if($(".password").eq(0).val()!=$(".password").eq(1).val()){
        	layer.msg('新密码两次输入不一致');
        	return false;
        }
        else if($(".password").eq(1).val().length>20){
        	layer.msg('密码过长');
        	return false;
        }
        else if($(".password").eq(1).val().length<6){
        	layer.msg('密码过短');
        	return false;
        }
        else{
             encryptPassword($("#password"),'${_SALT_IN_SESSION_?if_exists}');
    	         encryptPassword($("#newPassword"));
    		     encryptPassword($("#confirmPassword"));
             $("#fiveplanmodifyform").attr("action", "fiveplanmodifyPD!updatePassword.action");
		     $("#fiveplanmodifyform").submit();
        }

    });
     $(function(){
		<#if errorInfo?exists>
		layer.msg('${(errorInfo)?if_exists}');
		</#if>
	})
</script>

</@p.index>