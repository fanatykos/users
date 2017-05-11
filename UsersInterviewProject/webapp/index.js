var inputFileHTTP = 'http://localhost:8080/users/inputfile.html';
var usersTableHTTP = 'http://localhost:8080/users/userstable.html';

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
			showUsersTablePage()
		});