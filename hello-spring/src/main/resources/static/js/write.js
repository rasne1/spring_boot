/**
 * 
 */
$().ready(function () {
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
});
