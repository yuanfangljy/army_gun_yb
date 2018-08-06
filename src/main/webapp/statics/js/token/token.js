function Token() {
    //创建token
    $.ajax({
        url: "../gun/saveToken",
        type: "POST",
        cache: false,
        success: function (result) {
                $("#tokenliujiayi").val(result.extend.token);
        }
    })
}