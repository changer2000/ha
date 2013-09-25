function loadForm() {
	try {
		//do something common
		//...
		
		var allObjs = document.forms[0].elements;
		if (allObjs) {
			for (var i=0; i<allObjs.length; i++) {
				if (!($(allObjs[i]).prop("readonly")) && !($(allObjs[i]).prop("disabled"))) {
					allObjs[i].focus();
					break;
				}
			}
		}
		
		initDatePicker();
		
		if (typeof(afterLoad)!="undefined") {
			afterLoad();
		}

		//do something common
		//...
	} catch (ex) {
		alert(ex.message);
	}
}

function initDatePicker() {
	$('.form_datetime').datetimepicker({
		format:"yyyy-mm-dd", 
		language:  'zh-CN',
		weekStart : 1,
		autoclose : true,
		minView : 2,
		todayBtn : "linked",
		todayHighlight : true,
		forceParse:true
		});
}

function formSubmit() {
	try {
		//do something common
		//...
		
		if (typeof(doFormSubmit)!="undefined") {
			doFormSubmit();
		}
		
		//do something common else
		//...
	} catch (ex) {
		alert(ex.message);
	}
}

function line_del(containerId, chkboxName) {
	$('#'+containerId).find('input[name='+chkboxName+']:checked').closest('tr').remove();
	return false;
}