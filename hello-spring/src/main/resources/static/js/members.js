/**
 *
 * 회원페이지와 관련된 스크립트 작성
 *
 */
$().ready(function () {
  // 회원 가입 폼이 전송이 되기전에 입력값을 제대로 작성했는지 체크.
  // 1.폼 전송할떄 체크하는 방법
  // 2. 입력폼에 값을 입력을 할 떄 체크방법. (keyup 이벤트 활용.)
  // 폼이 전송이 될떄 이벤트를 처리.
  $("#membersVO").on("submit", function (event) {
    //이미 브라우저에 할당된 서브밋 콜백 이벤트를 제거한다.

    event.preventDefault();
    $(this).find(".validation-error").remove();

    // 제대로 입력하지 않았다 ==> 에러 메시지를 화면에 보여준다.
    var email = $("#email").val();
    if (!email) {
      var emailErrorMessage = $("<div>");
      emailErrorMessage.addClass("validation-error");
      emailErrorMessage.text("이메일 형태가 아닙니다.");

      $("#email").after(emailErrorMessage);
    }
    var name = $("#name").val();
    if (!name) {
      var nameErrorMessage = $("<div>");
      nameErrorMessage.addClass("validation-error");
      nameErrorMessage.text("아이디 형태가 아닙니다.");

      $("#name").after(nameErrorMessage);
    }
    var passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/;
    var password = $("#password").val();
    if (!passwordPattern.test(password)) {
      var passwordErrorMessage = $("<div>");
      passwordErrorMessage.addClass("validation-error");
      passwordErrorMessage.text("비밀번호 형태가 아닙니다.");

      $("#password").after(passwordErrorMessage);
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
