function sendData(section) {
	var sect = document.createElement("input");
	sect.name = "section";
	sect.type = "hidden";
	sect.value = section;
	
	var form = document.createElement("form");
	form.method = "post";
	form.action = "/WebProject/conf";
	form.style = "none";
	
	form.appendChild(sect);
	
	document.body.append(form);
	
	form.submit();
}
function getConf(id) {
	document.viewConfForm.idConf.value = id;
	document.viewConfForm.submit();
}
function addEvent(){
	var newEvent = document.getElementById("event").cloneNode(true);
	newEvent.firstElementChild.value = '';
	addConf.append(newEvent);
}
function addCheckBox(){
	var rows = document.querySelectorAll("tr");
	for (var i = 1; i < rows.length; i++) {
		let idConf = rows[i].firstElementChild.firstChild.valueNode;
		let td = createCell(idConf);
		rows[i].insertAdjectElement("beforeend",td);
	}
}
function createCell(idConf){
	var td = document.createElement("td");
	var input = document.createElement("input");
	input.name = "idConf";
	input.type = "checkbox";
	input.value = idConf;
	
	td.append(input);
	
	return td;
}
