jQuery(function($){
	$.datepicker.regional['es'] = {
		closeText: 'Cerrar',
		prevText: '&#x3c;Anterior',
		nextText: 'Siguiente&#x3e;',
		currentText: 'Hoy',
		monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
		'Julio','Agosto','Setiembre','Octubre','Noviembre','Diciembre'],
		monthNamesShort: ['Enr','Feb','Mar','Apr','May','Jun',
		'Jul','Ago','Set','Oct','Nov','Dic'],
		dayNames: ['domingo','lunes','martes','miercoles','jueves','viernes','sabado'],
		dayNamesShort: ['dom','lun','mar','mie','jue','vie','sab'],
		dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
		weekHeader: 'Semana',
		dateFormat: 'dd.mm.yy',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''};
});