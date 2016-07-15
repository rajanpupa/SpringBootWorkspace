$(document).ready=function(){
	alert('Hello World!');
}

function serializeObject (form)
{
    var jsonObject = {};
    var a = form.serializeArray();
    $.each(a, function() {
        if (jsonObject[this.name] !== undefined) {
            if (!jsonObject[this.name].push) {
            	jsonObject[this.name] = [jsonObject[this.name]];
            }
            jsonObject[this.name].push(this.value || '');
        } else {
        	jsonObject[this.name] = this.value || '';
        }
    });
    return jsonObject;
};