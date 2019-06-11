/**
 * Created by watts on 2017/08/15.
 */
var Global = {
    contextPath: "",
    //前台设置上下文路径
    setContextPath: function (ctxPath) {
        if (this.contextPath === "") {
            this.contextPath = ctxPath;
        }
    },
    //添加tab页
    addItem: function (dataUrl, menuName, dataCode) {
        self.parent.window.addMenuItem(dataUrl, menuName, dataCode);
    },
    //layer弹出层触发添加tab页
    addItem4Layer: function (dataUrl, menuName, dataCode) {
        self.parent.parent.window.addMenuItem(dataUrl, menuName, dataCode);
    },
    //改变iframe高度
    changeHeight: function (iframe) {
        $(iframe).attr("height", window.document.documentElement.clientHeight - 125);
    },
    //sesison过期跳转
    sessionTimeoutRegistory: function () {
        $.ajaxSetup({
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            complete: function (XMLHttpRequest, textStatus) {
                //通过XMLHttpRequest取得响应头，sessionstatus，
                var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
                if (sessionstatus == "timeout") {
                    window.location = Global.contextPath + "/";
                }
            }
        });
    },
    //datatable国际化
    dataTablelLanguageRegistory: function (country) {
        //配置DataTables默认参数
        $.extend(true, $.fn.dataTable.defaults, {
            "language": {
                //Global.contextPath+
                url: (country === 'CN' ? '/static/common/datatable-chinese.txt' : '')
            },
            //使用bootstrap风格
            // "dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>"
            "dom": "<'row'<'col-md-12 col-sm-12'f>r>t<'row'<'col-md-2 col-sm-'l><'col-md-3 col-sm-12'i><'col-md-7 col-sm-12'p>>"

        });
    },
    /**
     * 全文按钮防止多次提交
     */
    buttonDefault: function () {
        $("body").delegate("input[type=button]", "click", function () {
            var btn = $(this);
            btn.attr("disabled", "disabled");
            btn.addClass("disabled");
            setTimeout(function () {
                btn.removeAttr("disabled");
                btn.removeClass("disabled");
            }, 300);
        }).delegate("button[type=button]", "click", function () {
            var btn = $(this);
            btn.attr("disabled", "disabled");
            btn.addClass("disabled");
            setTimeout(function () {
                btn.removeAttr("disabled");
                btn.removeClass("disabled");
            }, 300);
        });
    },
    //0是警告
    info: function (info) {
        layer.msg(info, {
            icon: 0,
            time: 1000
        });
    },
    //1是成功提示
    success: function (info, back) {
        layer.msg(info, {
            icon: 1,
            time: 1000
        }, back);
    },
    //2是错误提示
    error: function (info) {
        layer.msg(info, {
            icon: 2,
            time: 1000
        });
    },
    //确认框
    confirm: function (info, sure) {
        layer.confirm(info, {
            btn: [sureBtn, cancelBtn]
        }, function (index) {
            sure();
            layer.close(index);
        }, function (index) {
            layer.close(index);
        });
    },
    commonAjaxRemoteSelect2: function (inputId, placeholder, url, itemsFormatResult, itemsFormatSelection) {
        commonAjaxRemoteSelect2(inputId, placeholder, url, itemsFormatResult, itemsFormatSelection);
    },
    commonAjaxRemoteMultipleSelect2: function (inputId, placeholder, url, itemsFormatResult, itemsFormatSelection, filtertype) {
        commonAjaxRemoteMultipleSelect2(inputId, placeholder, url, itemsFormatResult, itemsFormatSelection, filtertype);
    },
    commonAjaxRemoteMultipleInitSelect2: function (inputId, placeholder, url, itemsFormatResult, itemsFormatSelection, initSelection) {
        commonAjaxRemoteMultipleInitSelect2(inputId, placeholder, url, itemsFormatResult, itemsFormatSelection, initSelection);
    },

    loadSelect2Info: function (inputId, url, callBack) {
        loadSelect2Info(inputId, url, callBack);
    },
    loadSelect2InfoByAjax: function (inputId, url, idKey, valueKey, setValue) {
        new $ajax(Global.contextPath + url, function (data) {
            idKey = idKey || ['id'];
            valueKey = valueKey || ['name'];
            var htmls = [];
            htmls.push("<option value=''></option>");
            for (var index in data) {
                var finalId = '', finalValue = '';
                for (var idIndex in idKey) {
                    finalId += data[index][idKey[idIndex]];
                }
                for (var valueIndex in valueKey) {
                    finalValue += data[index][valueKey[valueIndex]]+"&nbsp;&nbsp;&nbsp;&nbsp;";
                }
                htmls.push("<option value='" + finalId + "'>" + finalValue + "</option>");
            }
            $('#' + inputId).html(htmls.join(''));
            if ($.isFunction(setValue)) {
                setValue();
            }
        }).start();
    },
    loadDictionaryByJquery: function (selectObj, classid, attributeid) {
        loadDictionaryByJquery(selectObj, classid, attributeid);
    },
    CurentTime: function (date) {
        return CurentTime(date)
    },
    getTimeyyyyMMdd: function (date) {
        return getTimeyyyyMMdd(date)
    },
    AuditCollapse: function () {
        AuditCollapse();
    },
    ExtensionCollapse: function () {
        ExtensionCollapse();
    },
    /**
     * 表单验证
     * @param formId 表单ID
     * @param fields 字段规则[字段以ID作为表单验证标识]
     */
    validateForm: function (formId, fields) {
        $('#' + formId).bootstrapValidator({
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: fields,
            live: 'enabled',
            message: '该字段不能为空'
        });
    },
    /**
     * 摄像头地图标注点信息框
     * @param camera 摄像头
     * @returns {BMap.InfoWindow} 信息窗口对象
     */
    loadCameraMarketInfo: function (camera) {
        //创建信息窗口
        var opts = {
            width: 350,     // 信息窗口宽度
            height: 120,     // 信息窗口高度
            title: "<strong style=\"font-size:16px;font-weight:bold\">" + camera.ptName + "</strong>&nbsp;&nbsp;&nbsp;<a href='javascript:' onclick='Global.loadCameraDetail(" + camera.id + ")'>详情</a>", // 信息窗口标题
            enableMessage: true, //设置允许信息窗发送短息
            message: ""
        };
        var boxNo = camera.onu.boxNo || '';
        var projectName = camera.project.projectName || '';
        var showInfo = "<p style='color:#6a6a6a;font-size: 13px'>项目名称：" + projectName + "<br/>" + "箱体编号：" + boxNo
            + "<br/>宽带账号:" + camera.serviceNo + "<br/>摄像头ip地址:" + camera.cameraIp + "<br/>摄像头装机地址:" + camera.cameraAddress + "</p>";
        // 创建信息窗口对象
        return new BMap.InfoWindow(showInfo, opts);
    },
    loadCameraDetail: function (cameraId) {
        layer.open({
            type: 2,
            title: '<i class="fa fa-edit"/> 摄像头详情',
            area: ['800px', '400px'],
            fix: false,
            maxmin: false,
            content: '/vms/camera/get/get_edit_enable/' + cameraId
        });
    },
    /**
     * 放大图片函数
     * @param target_
     * @param width
     * @param height
     */
    imgFadeInBig: function (target_,width,height) {
        var w = $(target_).width();
        var h = $(target_).height();
        width = w;
        height = h;
        if (height > 50) {
            $(target_).addClass('top-zero');
        }
        var bigWidth = (width < 100 ? width * 3 : width * 2);
        var bigHeight = (height < 100 ? height * 3 : height * 2);
        $(target_).addClass('big');
        $(target_).attr("width", bigWidth + "px;");
        $(target_).attr("height", bigHeight + "px");
    },
    /**
     * 还原图片函数
     * @param target_
     * @param width
     * @param height
     */
    imgFadeOutBig: function (target_,width,height) {
        $(target_).removeClass('big top-zero');
        $(target_).attr("width", width);
        $(target_).attr("height", height);
    }
};

/**
 *加载select2控件数据 这个地方后台获取全部数据。拼装成<option></option>的形式
 */
function loadSelect2Info(inputId, url, callBack) {
    $('#' + inputId).select2({
        placeholder: "",
        allowClear: true
    });
    $.getJSON(url, function (data) {
        $("#" + inputId).empty();
        $("#" + inputId).html(data.assInfo);
        $("#" + inputId).select2("data", "");
        if ($.isFunction(callBack)) {
            callBack();
        }
    });
};
function commonAjaxRemoteSelect2(inputId, placeholder, url, itemsFormatResult, itemsFormatSelection) {
    // 拼装结果展示
    var defaultItemsFormatResult = function (repo) {
        var markup = "<div class='clearfix'><div class='col-sm-20'>" + repo.name + "</div></div>";
        return markup;
    }
    // 选择结果的回调处理函数
    var defaultItemsFormatSelection = function (repo) {
        repo.selected = true;
        $("#" + inputId).val(repo.id);
        return repo.name;
    }
    //如果没有自定义函数，就使用默认的吧，但是返回的json格式要注意
    var $itemsFormatResult = $.isFunction(itemsFormatResult) ? itemsFormatResult : defaultItemsFormatResult;
    var $itemsFormatSelection = $.isFunction(itemsFormatSelection) ? itemsFormatSelection : defaultItemsFormatSelection;
    $("#" + inputId).select2({
        allowClear: true,
        placeholder: " ",
        minimumInputLength: 1,
        ajax: { // 每次输入都要请求后台
            url: url,
            dataType: 'json',
            quietMillis: 1000,
            data: function (term, page) {
                return {
                    q: term, // 查询关键字
                    page_limit: 10,
                    page: page
                };
            },
            results: function (data, page) {
                page = page || 1;//当前页赋值
                return {
                    results: data.items,//查询结果集，可以自定义（不动最好）
                    pagination: {
                        more: (page * 10) < data.total_count//加载下一页，每页显示数
                    }
                };
            }
        },
        formatInputTooShort: function (input, min) {//未输入查询内容的提示
            var n = min - input.length;
            return "Please input value";
        },
        formatResult: $itemsFormatResult, // 拼装结果展示
        formatSelection: $itemsFormatSelection, // 选择结果的回调处理函数
        dropdownCssClass: "bigdrop", // apply css that makes the dropdown taller
        escapeMarkup: function (m) {
            return m;
        }
    });

}
function commonAjaxRemoteMultipleSelect2(inputId, placeholder, url, itemsFormatResult, itemsFormatSelection, filtertype) {
    // 拼装结果展示
    var defaultItemsFormatResult = function (repo) {
        var markup = "<div class='clearfix'><div class='col-sm-20'>" + repo.name + "</div></div>";
        return markup;
    }
    // 选择结果的回调处理函数
    var defaultItemsFormatSelection = function (repo) {
        repo.selected = true;
        $("#" + inputId).val(repo.id);
        return repo.name;
    }
    //如果没有自定义函数，就使用默认的吧，但是返回的json格式要注意
    var $itemsFormatResult = $.isFunction(itemsFormatResult) ? itemsFormatResult : defaultItemsFormatResult;
    var $itemsFormatSelection = $.isFunction(itemsFormatSelection) ? itemsFormatSelection : defaultItemsFormatSelection;
    $("#" + inputId).select2({
        allowClear: true,
        tags: true,
        tokenSeparators: [';', ' '],
        placeholder: " ",
        createSearchChoice: function (term) {
            var filter = null;
            if (filtertype === 1) { //邮箱验证
                filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            }
            if (filter == null) {
                return {id: $.trim(term), code: $.trim(term), name: $.trim(term)};
            } else {
                if (term == '\${createDeptMail}' || term == '\${DNSdivision}'
                    || term == '\${CAdivision}' || filter.test(term)) {
                    return {id: $.trim(term), code: $.trim(term), name: $.trim(term)};
                } else {
                    return null;
                }
            }
        },
        minimumInputLength: 1,
        ajax: { // 每次输入都要请求后台
            url: url,
            dataType: 'json',
            quietMillis: 1000,
            data: function (term, page) {
                return {
                    q: term, // 查询关键字
                    page_limit: 10,
                    page: page
                };
            },
            results: function (data, page) {
                page = page || 1;//当前页赋值
                return {
                    results: data.items,//查询结果集，可以自定义（不动最好）
                    pagination: {
                        more: (page * 10) < data.total_count//加载下一页，每页显示数
                    }
                };
            }
        },
        formatInputTooShort: function (input, min) {//未输入查询内容的提示
            var n = min - input.length;
            return "Please input value";
        },
        formatResult: $itemsFormatResult, // 拼装结果展示
        formatSelection: $itemsFormatSelection, // 选择结果的回调处理函数
        dropdownCssClass: "bigdrop", // apply css that makes the dropdown taller
        escapeMarkup: function (m) {
            return m;
        }
    });
}
//本来想实现成可以初始化的但为实现 只是简单的去掉了键盘输入的结果拼装
function commonAjaxRemoteMultipleInitSelect2(inputId, placeholder, url, itemsFormatResult, itemsFormatSelection, initSeclection) {
    // 拼装结果展示
    var defaultItemsFormatResult = function (repo) {
        if(repo.id ==repo.name){
            return;
        }
        var markup = "<div class='clearfix'><div class='col-sm-20'>" + repo.name + "</div></div>";
        return markup;
    }
    // 选择结果的回调处理函数
    var defaultItemsFormatSelection = function (repo) {
        repo.selected = true;
        $("#" + inputId).val(repo.id);
        return repo.name;
    }
    //如果没有自定义函数，就使用默认的吧，但是返回的json格式要注意
    var $itemsFormatResult = $.isFunction(itemsFormatResult) ? itemsFormatResult : defaultItemsFormatResult;
    var $itemsFormatSelection = $.isFunction(itemsFormatSelection) ? itemsFormatSelection : defaultItemsFormatSelection;
    $("#" + inputId).select2({
        allowClear: true,
        tags: true,
        tokenSeparators: [';', ' '],
        placeholder: " ",
        minimumInputLength: 1,
        ajax: { // 每次输入都要请求后台
            url: url,
            dataType: 'json',
            quietMillis: 1000,
            data: function (term, page) {
                return {
                    q: term, // 查询关键字
                    page_limit: 10,
                    page: page
                };
            },
            results: function (data, page) {
                page = page || 1;//当前页赋值
                return {
                    results: data.items,//查询结果集，可以自定义（不动最好）
                    pagination: {
                        more: (page * 10) < data.total_count//加载下一页，每页显示数
                    }
                };
            }
        },
        formatInputTooShort: function (input, min) {//未输入查询内容的提示
            var n = min - input.length;
            return "Please input value";
        },
        initSelection : function (element, callback) {
            var data = [];
            $(element.val().split(",")).each(function () {
                data.push({id: this, text: this});
            });
            callback(data);
        },
        formatResult: $itemsFormatResult, // 拼装结果展示
        formatSelection: $itemsFormatSelection, // 选择结果的回调处理函数
        dropdownCssClass: "bigdrop", // apply css that makes the dropdown taller
        escapeMarkup: function (m) {
            return m;
        }
    });
}


function loadDictionaryByJquery(selectObj, classid, attributeid) {
    selectObj.empty();
    selectObj.append("<option value=''></option>");
    $.ajax({
        type: "POST",
        url: Global.contextPath + "/sysDictionary/getDicList/",
        dataType: "json",
        data: {"classid": classid, "attributeid": attributeid},
        success: function (data) {
            var type = eval(data);
            for (var i = 0; i < type.dic.length; i++) {
                var att = type.dic[i];
                selectObj.append("<option value='" + att[0] + "'>" + att[1] + "</option>");
            }
        }
    });
}
function CurentTime(date) {
    var d = new Date(date.getTime());
    var dd = d.getDate() < 10 ? "0" + d.getDate() : d.getDate().toString();
    var MM = d.getMonth() + 1;
    var yyyy = d.getFullYear().toString(); //2011
    var hh = d.getHours();
    var mm = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
    // var ss = d.getSeconds()< 10 ? "0" + d.getSeconds():d.getSeconds();
    return yyyy + "-" + MM + "-" + dd + " " + hh + ":" + mm;
}

function getTimeyyyyMMdd(dateString) {
    var f = dateString.split(' ', 2);
    var d = (f[0] ? f[0] : '').split('-', 3);
    var t = (f[1] ? f[1] : '').split(':', 3);
    var date = new Date(parseInt(d[0], 10) || null, (parseInt(d[1], 10) || 1) - 1, parseInt(d[2], 10) || null,
        parseInt(t[0], 10) || null, parseInt(t[1], 10) || null, parseInt(t[2], 10) || null );
    // var d = new Date(date.getTime());
    var dd = date.getDate() < 10 ? "0" + date.getDate() : date.getDate().toString();
    var MM = date.getMonth() + 1;
    var yyyy = date.getFullYear().toString(); //2011
    return yyyy + "-" + MM + "-" + dd;
}
function AuditCollapse() {
    var isexpand = $("#auditinfo").attr("class") == "expand";
    $("#auditinfo_body").attr("style", "display:" + (isexpand == true ? "block" : "none"));
    $("#auditinfo").attr("class", isexpand == true ? "collapse" : "expand");
};
function ExtensionCollapse() {
    var isexpand = $("#extensioninfo").attr("class") == "expand";
    $("#extensioninfo_body").attr("style", "display:" + (isexpand == true ? "block" : "none"));
    $("#extensioninfo").attr("class", isexpand == true ? "collapse" : "expand");
};


/**
 * step框使用
 * 参数解释(按照顺序排列):
 * [form表单的divid，表单id，错误信息展示区域，成功信息区域，最后展示所有信息的tab，表单验证函数，表单提交函数，continue点击下一步函数调用]
 *
 */
function commonFormWizard(formDiv,form,error,success,endTab,formValidate,formSubmit,myStepHandle, myTabShowHandle){
    if (!jQuery().bootstrapWizard) {
        return;
    }
    if($.isFunction(formValidate)){
        formValidate();//表单验证
    }else{
        //alert('validate is undefined');
    }

    //将前面的form表单值放到最后一步展示，通过name来取值，实际提交表单时，取得还是前面步骤填写的name的值，这里只做展示
    var displayConfirm = function() {
        $('#'+endTab+' .form-control-static', form).each(function(){
            var input = $('[name="'+$(this).attr("data-display")+'"]', form);
            if (input.is(":radio")) {
                input = $('[name="'+$(this).attr("data-display")+'"]:checked', form);
            }
            if (input.is(":text") || input.is("textarea")) {
                $(this).html(input.val());
            } else if (input.is("select")) {
                $(this).html(input.find('option:selected').text());
            } else if (input.is(":radio") && input.is(":checked")) {
                $(this).html(input.attr("data-title"));
            } else if ($(this).attr("data-display") == 'payment[]') {
                var payment = [];
                $('[name="payment[]"]:checked', form).each(function(){
                    payment.push($(this).attr('data-title'));
                });
                $(this).html(payment.join("<br>"));
            }
        });
    }
    var handleTitle = function(tab, navigation, index) {//上一步下一步回调函数
        var total = navigation.find('li').length;
        var current = index + 1;
        // 设置当前第几步
        $('.step-title', formDiv).text('Step ' + (index + 1) + ' of ' + total);
        jQuery('li', formDiv).removeClass("done");
        var li_list = navigation.find('li');
        for (var i = 0; i < index; i++) {
            jQuery(li_list[i]).addClass("done");
        }
        if (current == 1) {
            formDiv.find('.button-previous').hide();
        } else {
            formDiv.find('.button-previous').show();
        }
        if (current >= total) {
            formDiv.find('.button-next').hide();
            formDiv.find('.button-submit').show();
            displayConfirm();
        } else {
            formDiv.find('.button-next').show();
            formDiv.find('.button-submit').hide();
        }
        Metronic.scrollTo($('.page-title'));
    }
    // 加载步骤form表单
    formDiv.bootstrapWizard({
        'nextSelector': '.button-next',
        'previousSelector': '.button-previous',
        onTabClick: function (tab, navigation, index, clickedIndex) {//tab点击事件
            return false;
            /*
            success.hide();
            error.hide();
            if (form.valid() == false) {
                return false;
            }
            handleTitle(tab, navigation, clickedIndex);
            */
        },
        onNext: function (tab, navigation, index) {
            success.hide();
            error.hide();
            if (form.valid() == false) {
                return false;
            }
            handleTitle(tab, navigation, index);
            if($.isFunction(myStepHandle)){
                myStepHandle(tab, navigation, index,true);//下一步回调
            }
        },
        onPrevious: function (tab, navigation, index) {
            success.hide();
            error.hide();
            handleTitle(tab, navigation, index);

            if($.isFunction(myStepHandle)){
                myStepHandle(tab,navigation,index,false);//上一步回调
            }
        },
        onTabShow: function (tab, navigation, index) {
            var total = navigation.find('li').length;
            var current = index + 1;
            var $percent = (current / total) * 100;
            formDiv.find('.progress-bar').css({
                width: $percent + '%'
            });
            if($.isFunction(myTabShowHandle)){
                myTabShowHandle(tab,navigation,index,false);//上一步回调
            }
        }
    });
    //formDiv.find('.button-previous').hide();
    formDiv.find('.button-submit').off('click').on('click', function () {
        //form表单提交函数
        if($.isFunction(formSubmit)){
            formSubmit();
        }else{
            alert('formSubmit is undefined');
        }
    })//.hide();
}

