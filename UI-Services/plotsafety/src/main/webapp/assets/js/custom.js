$(document).ready(function() {
	var filesDeleted = [];
	$('#selectFile').MultiFile({
		list : '#selectedFiles'
	});
	$('.thumbnail').click(function() {
		$('.modal-body').empty();
		var title = $(this).attr("title");
		$('.modal-title').html(title);
		$($(this).parents('div').html()).appendTo('.modal-body');
		$('#myModal').modal({
			show : true
		});
	});
	$('a.filesDelete').click(function() {
		var element = $(this).parent('div');
		var rowid = element.attr('rowid');
		element.toggleClass('file-delete');
		if (element.hasClass('file-delete')) {
			filesDeleted.push(rowid);
		} else {
			var index = filesDeleted.indexOf(rowid);
			if (index > -1) {
				filesDeleted.splice(index, 1);
			}
		}
		$("#deletedFiles").val(filesDeleted);
	});
});