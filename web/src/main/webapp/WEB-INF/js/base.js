
function submitForm(url, method, formId) {
    $.ajax({
        url:url,
        type:method, //GET
        async:false,    //或false,是否异步
        data:$('#' + formId).serialize(),
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        beforeSend:function(xhr){
            console.log(xhr)
            console.log('发送前')
        },
        success:function(data,textStatus,jqXHR){
            console.log(data)
            console.log(textStatus)
            console.log(jqXHR)
            alert("success!!!!");
        },
        error:function(xhr,textStatus){
            console.log('错误')
            console.log(xhr)
            console.log(textStatus)
        },
        complete:function(){
            console.log('结束')
        }
    })
}

function getPage(url, method) {
    $.ajax({
        url:url,
        type:method, //GET
        async:false,    //或false,是否异步
        dataType:'html',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data,textStatus,jqXHR){
            $("#xcjy-main-content").empty().append(data);
        }
    })
}