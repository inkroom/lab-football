	;(function($, window, document,undefined) {
        var Validate_layer = function(ele,opt){
            var message_config = {
                'required':{'rule':/\S/,'message-text':'不能为空！','type':'normal'},
                'max':{'rule':/^[-+]?\d*$/,'message-text':'数字不能过长！','type':'size'},
                'min':{'rule':/^[-+]?\d*$/,'message-text':'数字不能过短！','type':'size'},
                'email':{'rule':/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,'message-text':'请输入正确邮箱格式！','type':'normal'},
                'string':{'rule':/^[a-zA-Z0-9_]+$/,'message-text':'请输入正常字符','type':'normal'},
                'idcard':{'rule':/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/,'message-text':'请输入身份证号码作为文件编号','type':'normal'},
                'teshu':{'rule':/[^u4e00-u9fa5w]/,'message-text':'请输入正常字符','type':'normal'},
                'double':{'rule':/^[-\+]?\d+(\.\d+)?$/,'message-text':'请输入正常小数',type:'normal'},
                'china':{'rule':/^[\u4E00-\u9FA5A-Za-z]+$/,'message-text':'请输入中文或者英文大于2位小于10位',type:'normal'},
                'integer':{'rule':/^[-+]?\d*$/,'message-text':'请输入正常整数',type:'normal'},
                'zhengnum':{'rule':/^[0-9]*[0-9][0-9]*$/,'message-text':'请输入正整数',type:'normal'},
                'letter':{'rule':/^[a-zA-Z]+$/,'message-text':'请输入英文',type:'normal'},
                'length-min':{'rule':'','message-text':'输入字符过短！','type':'length'},
                'length-max':{'rule':'','message-text':'输入字符过长！','type':'length'},
                'password':{'rule':'','message-text':'密码不一致！','type':'password'},//属性类容为重复输入的id或class
                'id_card':{'rule':'','message-text':'身份证重复！','type':'id_card'},//属性类容为重复输入的id或class
                'file-no':{'rule':/^[a-zA-Z0-9\u4E00-\u9FA5A-Za-z\-_]+$/,'message-text':'请勿输入 特殊符号',type:'normal'}
            };
            this.$element = ele,
            this.defaults = {
                'layer': true,
                'scope': "form",//范围
                'trigger':{"dom":"form",'event-dom':"*[type='submit']",'event':'click'},//触发事件
                'layer-config':{'event':'msg','config':{}},//layer插件config
                'message':message_config,//信息初始化
                set_message_obj:{},//json类型数据根据css选择器修改错误提示
                ajax_config:{},
                success_function:null,//成功之后执行函数
                error_function:null//表单验证失败函数
            },
            this.options = $.extend({},this.defaults,opt),
            this.scope = $(this.options['scope']),
            this.dom = $(this.options['trigger']['dom']),
            this.event_dom = $(this.options['trigger']['event-dom']);
            this.event = this.options['trigger']['event'];
        }
        Validate_layer.prototype = {
            set_message_func: function(options){
               //var options={required:{'message-text':'test success','target':'#test'},text:{'message-test':'test success','target':'#test'}};
                for(var i in options){
                    this.options['set_message_obj'][i] = options[i];
                }

            },
            injection_message: function(target,message,message_style){
                try{
                    if ($(this.options['set_message_obj'][message_style]['target']).is(target)){
                        message['message-text'] =  this.options['set_message_obj'][message_style]['message-text'];
                    }
                    return [target,message];
                }catch (e){
                    return [target,message];
                }
            },
            layer:function(message,dom){
                var func=eval("layer."+this.options['layer-config']['event']);
                var layer_config = this.options['layer-config']['config'];
                if(this.options['layer-config']['event'] == 'tips'){
                    var timestamp=new Date().getTime();
                    dom.addClass("time"+timestamp);
                    var i = layer_config.indexOf(',');
                    var c = layer_config.substring(i+1,layer_config.length);
                    eval('new func("'+message['message-text']+'",".time'+timestamp+'",'+c+')');
                    dom.removeClass("time"+timestamp);

                }
                else{
                    var config = this.options['layer-config']['config'];
              
                    eval('new func("'+message['message-text']+'")');
                }
            },
            $element_bind: function(){
                var $this = this;
                return this.$element.each(function(){
                    switch ($this.event){
                        case 'click':
                            $this.event_dom.click(function(){
                                var is = $this.validate_click();
                                if(!is){
                                    $this.options.success_function();
                                    //$this.dom.submit();
                                    return true;
                                }
                                else{
                                    $this.options.error_function(is);
                                    return false;
                                }
                            });
                            break;
                        case 'blur':
                            var is = $this.validate_blur();
                            if(!is){
                                $this.options.success_function();
                                return true;
                            }
                            else{
                                $this.options.error_function(is);
                                return false;
                            }
                    }
                })
            },
            validate_layer :function(){
                var $this = this;
                this.set_message_func(this.options.set_message_obj);
                $(this.options['scope']).bind('DOMNodeInserted', function(e) {
                    console.log(e);
                    $this.set_message_func($this.options.set_message_obj);
                    $this.$element_bind();
                });
                return  this.$element_bind();
            },
            method :function(type,dom,message){
                switch (message['type']){
                    case 'normal':
                            if(!message['rule'].test($.trim(dom.val()))){
                                this.layer(message,dom);
                                dom.focus();
                                return true;
                            }
                            return false;
                        break;
                    case 'size':
                            if(!message['rule'].test($.trim(dom.val()))){
                                this.layer(message,dom);
                                dom.focus();
                                return true;
                            }
                            else{
                                if(type=='max'&&parseInt($.trim(dom.val()))>parseInt(dom.attr(type))){
                                    this.layer(message,dom);
                                    dom.focus();
                                    return true;
                                }
                                if(type=='min'&&parseInt($.trim(dom.val()))<parseInt(dom.attr(type))){
                                    this.layer(message,dom);
                                    dom.focus();
                                    return true;
                                }
                            }
                            return false;
                        break;
                    case 'length':
                            if(type=='length-max'&&dom.val().length>dom.attr(type)){
                                this.layer(message,dom);
                                dom.focus();
                                return true;
                            }
                            if(type=='length-min'&&dom.val().length<dom.attr(type)){
                                this.layer(message,dom);
                                dom.focus();
                                return true;
                            }
                            return false;
                        break;
                    case 'password':
                            var re_dom =  $(dom.attr(type));
                            if(re_dom.val()!=dom.val()){
                                this.layer(message,re_dom);
                                re_dom.focus();
                                return true;
                            }
                            return false;
                        break;
                    case 'id_card':
                        var re_dom =  $(dom.attr(type));
                        if(re_dom.val()==dom.val()){
                            this.layer(message,re_dom);
                            re_dom.focus();
                            return true;
                        }
                        return false;
                    break;
                    default:
                        break;
                }
            },
            validate_blur: function(){
                var $this = this;
                var inputs = this.scope.find('input');
                for(var i=0;i<inputs.length;i++){
                    inputs.eq(i).blur(function(){
                        for(var j in $this.options['message']){
                            if($(this).attr(j)){
                                var return_value = $this.injection_message($(this),$this.options['message'][j],j);
                                if($this.method(j,return_value[0],return_value[1]))//一次
                                    return $(this);
                            }
                        }
                    });
                }
                return false;
            },
            validate_click: function() {
                var $this = this;
                var inputs = this.scope.find("input");
                for(var i=0;i<inputs.length;i++){
                    for(var j in $this.options['message']){
                        if(inputs.eq(i).attr(j)){
                            var return_value = $this.injection_message(inputs.eq(i),$this.options['message'][j],j);
                            if($this.method(j,return_value[0],return_value[1]))//一次
                                return inputs.eq(i);
                        }
                    }
                }
                return false;
            },
            validate_ajax_method: function(options){
                $.ajax({
                    url:options['url'],
                    type:'POST',
                    data:options['data'],
                    success:options['success'],
                    error:options['error']
                })
            },
            validate_ajax: function(options){
                for(var i in options){

                }

            }
        }
        $.fn.my_validate_layer = function(opt){
            var validate_layer = new Validate_layer(this,opt);

            return validate_layer.validate_layer();
        }

    })(jQuery, window, document);