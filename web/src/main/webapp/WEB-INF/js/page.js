var totalCount;
var currentPage;
var pageSize;
var pageNum;
var updateDataUrl;
$(function () {
    totalCount = $("#page-btn-container").attr("data-totalCount");
    currentPage = $("#page-btn-container").attr("data-page");
    pageSize = $("#page-btn-container").attr("data-pageSize");
    pageSize = $("#page-btn-container").attr("data-pageSize");

    console.log("totalCount:" + totalCount);
    console.log("currentPage:" + currentPage);
    console.log("pageSize:" + pageSize);

    pageNum = 1;

    if (totalCount % pageSize == 0) {
        pageNum = parseInt(totalCount / pageSize);
    } else {
        pageNum = parseInt(totalCount / pageSize) + 1;
    }

    $("#page-btn-container").append('<li><a id="pre-page" href="#">«</a></li>');
    if (pageNum > 0) {
        for (var index = 1; index <= pageNum; index++) {
            $("#page-btn-container").append('<li><a class="act-page" data-page="' + index + '" href="#">' + index + '</a></li>');
        }
    }
    $("#page-btn-container").append('<li><a id="next-page" href="#">»</a></li>');

    $("#next-page").on('click', function () {
        if (currentPage < pageNum) {
            updateData(currentPage + 1);
        }
    })

    $("#pre-page").on('click', function () {
        if (currentPage < pageNum) {
            updateData(currentPage - 1);
        }
    })

    $(".act-page").on('click', function () {
        var pageIndex = $(this).attr("data-page");
        if (pageIndex != currentPage) {
            updateData(pageIndex);
        }
    })
})

function updateData(page) {

}