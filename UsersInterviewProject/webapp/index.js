var inputFileHTTP = 'inputfile.html';
var usersTableHTTP = 'userstable.html';

function showInputFilePage() {
	window.location.href = inputFileHTTP;
}

function showUsersTablePage() {
	window.location.href = usersTableHTTP;
}

document.getElementById('showInputFilePage').addEventListener('click',
		function() {
			showInputFilePage();
		});

document.getElementById('showUsersTablePage').addEventListener('click',
		function() {
			showUsersTablePage();
		});