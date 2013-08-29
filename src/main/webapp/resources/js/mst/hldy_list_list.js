function afterLoad() {
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