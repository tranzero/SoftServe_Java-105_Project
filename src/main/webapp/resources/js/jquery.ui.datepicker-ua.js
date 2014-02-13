jQuery(function($){
	$.datepicker.regional['ua'] = {
		closeText: 'Закрити',
		prevText: '&#x3c;Наз',
		nextText: 'Далі&#x3e;',
		currentText: 'Сьогодні',
		monthNames: ['Січень','Лютий','Березень','Квітень','Травень','Червень',
		'Липень','Серпень','Вересень','Жовтень','Листопад','Грудень'],
		monthNamesShort: ['Січ','Лют','Бер','Квт','Трв','Чер',
		'Лип','Срп','Вер','Жвт','Лст','Грд'],
		dayNames: ['неділя','понеділок','вівторок','середа','четвер','п\'ятница','субота'],
		dayNamesShort: ['ндл','пнд','ввт','срд','чтв','птн','сбт'],
		dayNamesMin: ['Нд','Пн','Вт','Ср','Чт','Пт','Сб'],
		weekHeader: 'Тж',
		dateFormat: 'dd.mm.yy',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''};
});