$(function() {
    var b = $("#passwd");
    var a = $("#phone");
    a.keydown(function(c) {
        if (c.keyCode == 13) {
            if ($.trim(this.value) == "") {
                this.focus()
            } else {
                if (b.val() != "") {
                    formSubmit()
                } else {
                    b[0].focus()
                }
            }
        }
    });
    b.keydown(function(c) {
        if (c.keyCode == 13) {
            if (this.value == "") {
                this.focus()
            } else {
                if ($.trim(a.val()) != "") {
                    formSubmit()
                } else {
                    a[0].focus()
                }
            }
        }
    })
});