$().ready(function () {
    
    $(".page-navigator").find("a").on("click", function(){
        var pageNo = $(this).data("page-no");
        var listSize = $("#list-size").val();
        var searchType=$("#search-type").val();
        var searchKeyword=$("#search-keyword").val();
        
        location.href = "/?pageNo="+pageNo +"&listSize=" +listSize + "&searchType="+searchType +"&searchKeyword="+searchKeyword;
                
        
    });
    
    
    $("#list-size").on("change", function(){
        location.href="/?pageNo=0&listSize=" + $(this).val();
        $(".search-button").trigger("click");
    });
    
    $(".search-button").on("click",function(){
       // /?pageNo=0&listSize=#list-size값&searchType=#search-type값&searchKeyWord=#search-Keyword값
       var pageNo = 0;
       var listSize = $("#list-size").val();
       var searchType=$("#search-type").val();
       var searchKeyword=$("#search-keyword").val();
       
       location.href = "/?pageNo="+pageNo +"&listSize=" +listSize + "&searchType="+searchType +"&searchKeyword="+searchKeyword;
        
    });
    
    
    $("#writeVO").on("submit", function (event) {
       event.preventDefault();
       $(this).find(".validation-error").remove();

       // 제대로 입력하지 않았다 ==> 에러 메시지를 화면에 보여준다.
       var subject = $("#subject").val();
       if (!subject) {
         var subjectErrorMessage = $("<div>");
         subjectErrorMessage.addClass("validation-error");
         subjectErrorMessage.text("제목의 형태가 아닙니다.");

         $("#subject").after(subjectErrorMessage);
       }
       var email = $("#email").val();
       if (!email) {
         var emailErrorMessage = $("<div>");
         emailErrorMessage.addClass("validation-error");
         emailErrorMessage.text("이메일 형태가 아닙니다.");

         $("#email").after(emailErrorMessage);
       }

       //이름과 이메일과 비밀번호를 제대로 입력했다 ==> 폼을전송.
       if ($(".validation-error").length === 0) {
         // $(this).submit(); ==>jquery event
         // ==> 14번 라인(preventDefault)에서 전송 이벤트가 사라진 이유 떄문에 동작되지 않는다.
         this.submit(); // ==> javascript event
       }
       // 제대로 입력했다 ==> 폼을 전송.
     });
    
    
    
    
    
  // alert("!");

  // ".add-file"을 클릭하면
  // 새로운 파일 인풋과 버튼을
  // ".attach-files" 아래에 추가한다
  $(".attach-files").on("click", ".add-file", function () {
    //   $(".add-file").on("click", function () {
    //     // 새로운 파일이 추가될 때 마다 기존의 "add-file" 을 "del-file"로 변경하고
    //     // 텍스트는 "+" 에서 "-"로 변경한다.

    $(this)
      .closest(".attach-files")
      .children(".add-file")
      .removeClass("add-file")
      .addClass("del-file")
      .text("-")
      .off("click") //할당 되어있던 이벤트를 제거한다.
      .on("click", function () {
        //버튼 왼쪽에있는 인풋태그 삭제.
        $(this).prev().remove();
        //버튼도 삭제.
        $(this).remove();
      }); //새로운 이벤트를 추가한다.

    var fileInput = $("<input />");
    fileInput.attr({
      type: "file",
      name: "attachFile",
    });
    var addButton = $("<button />");
    addButton.attr("type", "button").addClass("add-file").text("+");

    $(".attach-files").append(fileInput).append(addButton);
  });
});
