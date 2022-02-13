function getValue(){
	var d = document.getElementById("typelist");
	var displaytext=d.option[d.selectedIndex].text;
	document.getElementById("types").value=displaytext;
}