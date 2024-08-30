let data = []
let sportName = ''
function getCountries(sport) {
    sportName = sport;
    getJSON();
}

function createDivs(limit,start) {
    let table = document.getElementById("countriesTable").getElementsByTagName('tbody')[0];
    table.innerHTML = '';
    for (let i = start; i < limit; i++) {
        let country = data[i];
        let row = table.insertRow(i);
        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(1);
        cell1.innerHTML = country.code;
        cell2.innerHTML = country.name; 
    }
}

function getJSON(){
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        data =JSON.parse(this.responseText);
        createDivs(200,0);
    }
    xhttp.open("GET", "/App/sport?name=" + sportName);
    xhttp.send();
}