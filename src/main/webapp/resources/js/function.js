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
		
		if (typeof(afterLoad)!="undefined") {
			afterLoad();
		}

		//do something common
		//...
	} catch (ex) {
		alert(ex.message);
	}
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