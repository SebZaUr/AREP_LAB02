let data = []
let sportName = ''
function getCountries(sport) {
    sportName = sport;
    var previous = document.getElementById("previous");
    var next = document.getElementById("next");
    previous.setAttribute('data-page',0);
    next.setAttribute('data-page', 1);
    nextCourses("previous");
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

function nextCourses(button) {
    getJSON();
    var page = parseInt(document.getElementById(button).getAttribute('data-page'));
    var course = document.getElementById('text');
    course.textContent = sportName+":Pagina-"+ String(page+1);
    var parar = 0;
    var previous = document.getElementById("previous");
    var next = document.getElementById("next");
    if ((page+1) * 10 > data.length) {
        previous.setAttribute('aria-disabled', false);
        next.setAttribute('aria-disabled', true);
        next.setAttribute('tabindex', -1);
        previous.setAttribute('tabindex', 1);
        previous.setAttribute('data-page', page - 1);
        next.setAttribute('data-page', page);
        parar = data.length;
    } else if (page == 0) {
        previous.setAttribute('aria-disabled', true);
        next.setAttribute('aria-disabled', false);
        previous.setAttribute('tabindex', -1);
        next.setAttribute('tabindex', 1);
        previous.setAttribute('data-page', 0);
        next.setAttribute('data-page', 1);
        parar = 10;
    } else {
        previous.setAttribute('aria-disabled', false);
        next.setAttribute('aria-disabled', false);
        previous.setAttribute('tabindex', 1);
        next.setAttribute('tabindex', 2);
        previous.setAttribute('data-page', page - 1);
        next.setAttribute('data-page', page + 1);
        parar = (page*10) + 10;
    }
    createDivs(parar, page*10);
}

function getJSON(){
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        data =JSON.parse(this.responseText);
    }
    xhttp.open("GET", "/App/sport?name=" + encodeURIComponent(sportName), true);
    xhttp.send();
}