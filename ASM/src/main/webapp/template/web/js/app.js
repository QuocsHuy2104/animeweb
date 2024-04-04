var cnt = 2;
function showMenubar() {

	if (cnt % 2 == 0) {
		document.getElementById("wrap").classList.add("d-block")
	} else {
		document.getElementById("wrap").classList.remove("d-block")
	}
	cnt++;

}