$().ready(function() {

	$('#add').click(function() {
		return !$('#available option:selected').remove().appendTo('#choosen');
	});
	$('#remove').click(function() {
		return !$('#choosen option:selected').remove().appendTo('#available');
	});
	selectedValuesBeforeValidation();
});

function selectall() {
	$('#choosen option').prop('selected', true);
	if (!isBlank($("#usernameDisplay").val())) {
		var userName = $("#usernameDisplay").val() + "@"
				+ $("#companyShortKey").val();
		$("#username").val(userName);
	}
	return true;
}

function isBlank(str) {
	return (!str || /^\s*$/.test(str));
}

function selectedValuesBeforeValidation() {

	// var data = "1,2,3,4";
	var data = $("#prevselectedRoles").val();

	if (!isBlank(data)) {
		data = data.replace('[', '');
		data = data.replace(']', '');
		var dataarray = data.split(",");
		var obj = $("#available");
		for ( var i in dataarray) {
			var val = dataarray[i].replace(' ', '');
			obj.find('option[value="' + val + '"]').attr('selected', 1);
		}

		$("#add").click();
	}
}

function isBlank(str) {
	return (!str || /^\s*$/.test(str));
}