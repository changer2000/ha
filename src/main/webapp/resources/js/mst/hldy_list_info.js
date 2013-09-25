var sample_js_dtl = null;

function afterLoad() {
	sample_js_dtl = $('#dtl_sample').find('tbody').eq(0).html();
	$('#dtl_sample').remove();
}

function line_add() {
	var tmpName = $('input[name=selKey]:last').closest('td').next().find('input').eq(0).attr('name');
	tmpName = tmpName.substring(tmpName.indexOf('[')+1, tmpName.indexOf(']'));
	var tmpIndex = parseInt(tmpName)+1;
	var tmpJs = sample_js_dtl.replace(/##/g,tmpIndex);
	$('#tableb').append(tmpJs);
	initDatePicker();
	return false;
}