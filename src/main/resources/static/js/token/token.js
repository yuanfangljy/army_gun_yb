function Token() {
    //创建token
    $.ajax({
        url: "../token/saveToken",
        type: "POST",
        cache: false,
        success: function (result) {
                $("#tokenliujiayi").val(result.extend.token);
        }
    })
}