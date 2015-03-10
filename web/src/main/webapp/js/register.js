(function(global) {
    var RegisterModule = uc.Module.extend({
        createCmps: function() {
            var self = this;
            self.currCount = 60;
            self.counting = false;
            self.regStruts = true;
            self.regStep = 1
        },
        getInputValue: function(inputName, _parent) {
            if (_parent) {
                return $("input[name=" + inputName + "]", _parent).val()
            } else {
                return $("input[name=" + inputName + "]").val()
            }
        },
        submit: function() {
            $("#fromRegister").submit()
        },
        bindEvents: function() {
            var self = this;
            $("#butRegister").click(function() {
                if (self.regStruts == false) {
                    return false
                }
                $(".tips-wrap").html("");
                var registerType = $("#registerType").val();
                var openId = $("#openId").val();
                var type = $("#type").val();
                var logonName = "";
                if (1 == self.regStep) {
                    var phone = $.trim($("#phone").val());
                    if (phone.length < 1) {
                        $(".tips-wrap.phone").html("手机号码不能为空");
                        return false
                    }
                    logonName = phone;
                    var validCode = $.trim($("#validCode").val());
                    if (validCode.length < 1) {
                        $(".tips-wrap.validCode").html("验证码不能为空");
                        return false
                    }
                    var url = self.baseRestUrl + "/reg?regStep=1&openId=" + openId + "&type=" + type,
                        type = "POST";
                    $.ajax({
                        url: url,
                        type: type,
                        dataType: "json",
                        data: self.getValues(),
                        form: self.form,
                        success: function(data, textStatus, jqXHR) {
                            console.log(data);
                            if (data.msg == "exist") {
                                window.location = basePath
                            }
                            self.regStep++;
                            self.initDatum()
                        },
                        error: function(errorObj) {
                            var obj = eval("(" + errorObj.responseText + ")");
                            if (obj.phone) {
                                $(".tips-wrap.phone").html(obj.phone)
                            }
                            if (obj.validCode) {
                                $(".tips-wrap.validCode").html(obj.validCode)
                            }
                        }
                    })
                } else {
                    if (2 == self.regStep) {
                        var password = $.trim($("#password").val());
                        if (password.length < 1) {
                            $(".tips-wrap.password").html("密码不能为空");
                            return false
                        }
                        var rePassword = $.trim($("#rePassword").val());
                        if (rePassword.length < 1) {
                            $(".tips-wrap.rePassword").html("确认密码不能为空");
                            return false
                        }
                        if ($("#rePassword").val() != $("#password").val()) {
                            $(".tips-wrap.rePassword").html("两次密码输入不一致");
                            return false
                        }
                        var url = self.baseRestUrl + "/reg?regStep=2&openId=" + openId + "&type=" + type,
                            type = "POST";
                        $.ajax({
                            url: url,
                            type: type,
                            dataType: "json",
                            data: self.getValues(),
                            form: self.form,
                            success: function(data, textStatus, jqXHR) {
                                self.regStep++;
                                self.initDatum()
                            },
                            error: function(errorObj) {
                                var obj = eval("(" + errorObj.responseText + ")");
                                if (obj.name) {
                                    $(".tips-wrap.name").html(obj.name)
                                }
                                if (obj.company) {
                                    $(".tips-wrap.companyName").html(obj.company)
                                }
                                if (obj.mainProducts) {
                                    $(".tips-wrap.mainProducts").html(obj.mainProducts)
                                }
                                if (obj.password) {
                                    $(".tips-wrap.password").html(obj.password)
                                }
                                if (obj.validCode) {
                                    $(".tips-wrap.validCode").html(obj.validCode)
                                }
                            }
                        })
                    } else {
                        var redirectURL = $("#ru").val();
                        if (redirectURL) {
                            window.location = basePath + redirectURL
                        } else {
                            window.location = basePath
                        }
                    }
                }
            });
            $(".reg-types-wrap.clearfix").click(function(event) {
                if (self.currCount != 60) {
                    return
                }
                var obj = $(event.target);
                var className = obj.attr("class");
                if (className && className.indexOf("item") != -1) {
                    $(".item").removeClass("selected");
                    obj.addClass("selected");
                    if (className.indexOf("email") != -1) {
                        $(".reg-form-row.email").show();
                        $(".reg-form-row.phone").hide();
                        $("#registerType").val("EMAIL")
                    } else {
                        $(".reg-form-row.phone").show();
                        $(".reg-form-row.email").hide();
                        $("#registerType").val("PHONE")
                    }
                }
            });
            $(".inline-block.reg-btn-getcode").click(function(event) {
                $(".tips-wrap").html("");
                var obj = $(event.target);
                var className = obj.attr("class");
                if (self.counting == false) {
                    var registerType = $("#registerType").val();
                    if ("EMAIL" == registerType) {
                        var email = $.trim($("#email").val());
                        if (email.length < 1) {
                            $(".tips-wrap.email").html("邮箱不能为空");
                            return
                        }
                    } else {
                        var phone = $.trim($("#phone").val());
                        if (phone.length < 1) {
                            $(".tips-wrap.phone").html("手机号码不能为空");
                            return
                        }
                    }
                    self.showCountDown();
                    var url = self.baseRestUrl + "/verification_code",
                        type = "GET";
                    $.ajax({
                        url: url,
                        type: type,
                        data: self.getValues(),
                        form: self.form,
                        success: function(data, textStatus, jqXHR) {
                            uc.showSuccess("获取验证码成功！");
                            $(".reg-form .reg-tips").show()
                        },
                        error: function(errorObj) {
                            self.stopCountDown();
                            var obj = eval("(" + errorObj.responseText + ")");
                            if (obj.phone) {
                                $(".tips-wrap.phone").html(obj.phone)
                            }
                            if (obj.email) {
                                $(".tips-wrap.email").html(obj.email)
                            }
                            if (obj.companyName) {
                                $(".tips-wrap.companyName").html(obj.companyName)
                            }
                            if (obj.password) {
                                $(".tips-wrap.password").html(obj.password)
                            }
                            if (obj.validCode) {
                                $(".tips-wrap.validCode").html(obj.validCode)
                            }
                        }
                    })
                }
            });
            $("#chContract").change(function(event) {
                var obj = $(event.target);
                if (obj.attr("checked") == "checked") {
                    $("#butRegister").removeClass("disabled");
                    self.regStruts = true
                } else {
                    $("#butRegister").addClass("disabled");
                    self.regStruts = false
                }
            });
            $(".reg-type").change(function() {
                var selectedvalue = $("input[name='type']:checked").val();
                if ("seller" == selectedvalue) {
                    $(".reg-form-row.mainProducts").show()
                } else {
                    $(".reg-form-row.mainProducts").hide()
                }
            });
            $(".close").click(function() {
                $(".bg-1").show();
                $(".bg-2").hide();
                $("#TB_overlayBG").css("display", "none");
                $(".red-wrap ").css("display", "none")
            });
            $(".f-button").click(function() {
                $(".bg-1").hide();
                $(".bg-2").show();
                hongbao()
            });
            $(".a-button").click(weixin);
            function hongbao() {
                $(".a-button").unbind("click");
                $.ajax({
                    url: basePath + "/yuandan/doingLuckyActivity.htm?activityId=REGISTER_WEIXINHONGBAO&type=ZHUCE&ra=" + Math.random(),
                    dataType: "json",
                    success: function(data, textStatus) {
                        console.log(data);
                        if (data[0] == true && data[3] != null) {
                            $(".a-span").html("刮得" + data[3].awardItemName);
                            $(".b-span").html("兑换码:" + data[3].redeemcode)
                        } else {
                            if (data[4] != null) {
                                $(".a-span").html("你已刮得" + data[4].awardItemName);
                                $(".b-span").html("兑换码:" + data[4].awardItemRedeemcode)
                            } else {
                                alert(data[1])
                            }
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        alert("网络超时")
                    }
                });
                $(".a-button").click(weixin)
            }
            function weixin() {
                $(".bg-1").show();
                $(".bg-2").hide();
                $("#TB_overlayBG").css("display", "none");
                $(".red-wrap ").css("display", "none");
                var url = absolutePath + "/yuandan/yuandan.htm#topPic";
                console.log(url);
                window.location.href = url
            }
            $("#huodong").click(function() {
                var url = absolutePath + "/coupons/index.htm?pageStatus=0";
                window.location.href = url
            });
            $("#shouye").click(function() {
                var url = absolutePath;
                window.location.href = url
            })
        },
        getValues: function() {
            var self = this;
            var data = {};
            data.id = $("#id").val();
            data.type = $('input:radio[name="type"]:checked').val();
            data.phone = $("#phone").val();
            data.email = $("#email").val();
            data.name = $("#name").val();
            data.company = $("#company").val();
            data.mainProducts = $("#mainProducts").val();
            data.password = $("#password").val();
            data.rePassword = $("#rePassword").val();
            data.validCode = $("#validCode").val();
            data.registerType = $("#registerType").val();
            data.flag = "REG";
            return data
        },
        showCountDown: function() {
            var self = this;
            self.counting = true;
            $("#regBtnGetCode").html(self.currCount + "秒后重新获取");
            $("#regBtnGetCode").addClass("disabled");
            self.countInterval = setInterval(function() {
                    self.currCount--;
                    if (self.currCount < 1) {
                        clearInterval(self.countInterval);
                        $("#regBtnGetCode").html("获取验证码");
                        $("#regBtnGetCode").removeClass("disabled");
                        self.currCount = 60;
                        self.counting = false
                    } else {
                        $("#regBtnGetCode").html(self.currCount + "秒后重新获取")
                    }
                },
                1000)
        },
        stopCountDown: function() {
            var self = this;
            $("#regBtnGetCode").html("获取验证码");
            $("#regBtnGetCode").removeClass("disabled");
            self.currCount = 60;
            self.counting = false;
            clearInterval(self.countInterval)
        },
        initVars: function() {
            var self = this;
            self.baseRestUrl = basePath + "/passport"
        },
        initDatum: function() {
            var self = this;
            if (self.regStep == 1) {
                $(".reg-form-row.phone").show();
                $(".reg-form-row.validCode").show();
                $(".reg-form-row.membership").hide();
                $(".reg-form-row.name").hide();
                $(".reg-form-row.companyName").hide();
                $(".reg-form-row.mainProducts").hide();
                $(".reg-form-row.password").hide();
                $(".reg-form-row.rePassword").hide();
                $(".reg-succeed").hide();
                $("#backHome").hide();
                $(".reg-process-wrap.wrap1").show();
                $(".reg-process-wrap.wrap2").hide();
                $(".reg-process-wrap.wrap3").hide()
            } else {
                if (self.regStep == 2) {
                    $(".reg-form-row.phone").hide();
                    $(".reg-form-row.validCode").hide();
                    $(".reg-form-row.membership").show();
                    $(".reg-form-row.name").show();
                    $(".reg-form-row.companyName").show();
                    $(".reg-form-row.mainProducts").show();
                    $(".reg-form-row.password").show();
                    $(".reg-form-row.rePassword").show();
                    $("#butRegister").text("完成注册");
                    $(".reg-succeed").hide();
                    $("#backHome").hide();
                    $(".reg-contract").hide();
                    $(".reg-process-wrap.wrap2").show();
                    $(".reg-process-wrap.wrap1").hide();
                    $(".reg-process-wrap.wrap3").hide();
                    $(".login-wrap").hide();
                    $("#detail-wrap").addClass("detail-wrap")
                } else {
                    $(".reg-process-wrap.wrap3").show();
                    $(".reg-process-wrap.wrap1").hide();
                    $(".reg-process-wrap.wrap2").hide();
                    $(".form-wrap").hide();
                    $(".notice-result").show();
                }
            }
        },
        ready: function() {
            this.initVars();
            this.createCmps();
            this.bindEvents();
            this.initDatum()
        }
    });
    new RegisterModule({
        name: "RegisterIndexModule",
        containerId: "registerIndex"
    })
})(window);