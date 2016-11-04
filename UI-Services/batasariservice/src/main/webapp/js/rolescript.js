$(document)
		.ready(
				function() {
					$('[class*="nonview_check_box"]')
							.click(
									function() {

										var className = $(this).attr('class');
										var splitClass = className.split("-");
										if (!$(
												'.view_checkbox-'
														+ splitClass[1]).is(
												":checked")) {
											var checkedAtleastOneBox = checkClassesChecked(className);
											if (checkedAtleastOneBox) {
												$(
														'.view_checkbox-'
																+ splitClass[1])
														.prop('checked', true);
											} else {
												$(
														'.view_checkbox-'
																+ splitClass[1])
														.prop('checked', false);
											}
										}
									});

					$('[class*="view_checkbox"]')
							.click(
									function() {

										if (!this.checked) {
											var className = $(this).attr(
													'class');
											var splitClass = className
													.split("-");

											var checkedAtleastOneBox = checkClassesChecked('nonview_check_box-'
													+ splitClass[1]);
											if (checkedAtleastOneBox) {
												$(
														'.view_checkbox-'
																+ splitClass[1])
														.prop('checked', true);
											} else {
												$(
														'.view_checkbox-'
																+ splitClass[1])
														.prop('checked', false);
											}
										}
									});
				});
function checkClassesChecked(className) {
	var checkedAtleastOneBox = false;
	$('.' + className).each(function() {
		if (this.checked && !checkedAtleastOneBox) {
			checkedAtleastOneBox = true;
		}
	});
	return checkedAtleastOneBox;
}