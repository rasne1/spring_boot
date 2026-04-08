/**
 * 
 */

$().ready(function () {
  $("#writeVO").on("submit", function (event) {
    event.preventDefault();
    $(this).find(".validation-error").remove();

    // 제대로 입력하지 않았다 ==> 에러 메시지를 화면에 보여준다.
    var title = $("#title").val();
    if (!title) {
      var titleErrorMessage = $("<div>");
      titleErrorMessage.addClass("validation-error");
      titleErrorMessage.text("제목의 형태가 아닙니다.");

      $("#title").after(titleErrorMessage);
    }
    var posterUrl = $("#posterUrl").val();
    if (!posterUrl) {
      var posterUrlErrorMessage = $("<div>");
      posterUrlErrorMessage.addClass("validation-error");
     posterUrlErrorMessage.text("포스터url 형태가 아닙니다.");

      $("#posterUrl").after(posterUrlErrorMessage);
    }
	
	
	var synopsis = $("#synopsis").val();
	   if (!synopsis) {
	     var synopsisErrorMessage = $("<div>");
	     synopsisErrorMessage.addClass("validation-error");
	     synopsisErrorMessage.text("줄거리의 형태가 아닙니다.");

	     $("#synopsis").after(synopsisErrorMessage);
	   }
	   
	   
	var state = $("#state").val();
	   if (!state) {
	     var stateErrorMessage = $("<div>");
	     stateErrorMessage.addClass("validation-error");
	     stateErrorMessage.text("개봉상황의 형태가 아닙니다.");

	     $("#state").after(stateErrorMessage);
	   }
    var language = $("#language").val();
 	   if (!language) {
 	     var languageErrorMessage = $("<div>");
 	     languageErrorMessage.addClass("validation-error");
 	     languageErrorMessage.text("어떤 언어인지 넣어주세요.");
       
 	     $("#language").after(languageErrorMessage);
 	   }
		   
	   
 

    //이름과 이메일과 비밀번호를 제대로 입력했다 ==> 폼을전송.
    if ($(".validation-error").length === 0) {
      // $(this).submit(); ==>jquery event
      // ==> 14번 라인(preventDefault)에서 전송 이벤트가 사라진 이유 떄문에 동작되지 않는다.
      this.submit(); // ==> javascript event
    }
    // 제대로 입력했다 ==> 폼을 전송.
  });
});