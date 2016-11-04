function onSubmitGroupRegistration() {
	if (!isBlank($("#userNameDisplay").val())) {
		var userName = $("#userNameDisplay").val() + "@"
				+ $("#usercompany").html();
		$("#username").val(userName);
	}
	return true;
}

function isBlank(str) {
	return (!str || /^\s*$/.test(str));
}