/**
 *
 */
$().ready(function () {
	
 $("#email").on("keyup", function(){
  var emailValue = $("#email").val();
  var emailPattern =
    /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/;

  if (emailPattern.test(emailValue)) {
    fetch("/regist/check/duplicate/" + emailValue)
      .then(function (fetchResult) {
        return fetchResult.json();
      })
      .then(function (json) {
        var duplicateResult = $("#email")
          .closest(".input-div")
          .children(".validation-error");

        if (duplicateResult.length === 0) {
          duplicateResult = $("#email")
            .closest(".input-div")
            .children(".validation-ok");
        }
        if (duplicateResult.length === 0) {
          duplicateResult = $("<div>");
          $("#email").after(duplicateResult);
        }

        if (!json.duplicate) {
          duplicateResult.removeClass("validation-error");
          duplicateResult.addClass("validation-ok");
          duplicateResult.text(json.email + "은 사용가능 합니다.");
        } else {
          duplicateResult.removeClass("validation-ok");
          duplicateResult.addClass("validation-error");
          duplicateResult.text(json.email + "은 사용중 입니다.");
        }
      });
  } else {
    $(this)
      .closest(".input-div")
      .children(".validation-ok, .validation-error")
      .remove();
  }
  });	

  $("#confirm-password, #password").on("keyup", function () {
    var confirmPasswordValue = $("#confirm-password").val();

    var passwordValue = $("#password").val();

    $("#confirm-password, #password")
      .closest(".input-div")
      .children(".validation-error, .validation-ok")
      .remove();

    if (confirmPasswordValue !== passwordValue) {
      var passwordError = $("<div>");

      passwordError
        .addClass("validation-error")
        .text("비밀번호가 일치하지않음");
      $("#password").after(passwordError);

      var confirmpasswordError = $("<div>");

      confirmpasswordError
        .addClass("validation-error")
        .text("비밀번호가 일치하지않음");
      $("#confirm-password").after(confirmpasswordError);
	
    }
  });
});
