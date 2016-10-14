// Функции для HTML форм. 

/**
 * Выполняет сабмит указанной формы по указанной
 * строке запроса.
 * 
 * @param formId ID формы
 * @param action url запроса
 * @param params массив параметров запроса. массив объектов вида
 * {name: <param_name>, value: <param_value>}
 *
 * @return
 */
function submitWithAction (formId, action, params){
	var form = document.getElementById(formId);
	    
	    
    if(action != null && action != ''){
        form.action = action;
    }
    for(var i = 0; i < params.length; i++){
        var param = params[i];
        form.elements[param.name].value = param.value;
    }
	form.submit();
	
	return false;
}

/**
 * Открывает URL в указанном окне.
 * 
 * @param url
 * @param dest указатель окна, в котором открыть URL.
 * @return
 */
function openUrl(url, dest) {
	if (dest == 0) // current window
		window.location = url;
	else if (dest == 1) // new window
		window.open(url);
	else if (dest == 2) // background window
	{
		window.open(url);
		self.focus();
	}
}