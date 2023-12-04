let table = document.getElementById("tableBody");
let form = document.getElementById("form");
function handleForm(event) { 
    event.preventDefault(); 

    // creating new row and its content
    let row = table.insertRow(-1);
    let cell1 = row.insertCell(0);
    let cell2 = row.insertCell(1);
    let data = document.createElement("div");
    let img = document.createElement("img");

    // taking value from inputs
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const website = document.getElementById('website').value;
    img.src = document.getElementById('image').value;
    const gender = document.querySelector('input[name="gender"]:checked');
    const skill = document.querySelectorAll('input[type="checkbox"]:checked');
    let skills = "";
    for (let checkbox of skill) {
        if (skills === ""){
            skills = checkbox.value;
        } else {
            skills = skills + ', ' + checkbox.value;
        }    
    }
    data.innerHTML= "<b>" + name + "</b><br />" + gender.value + "<br />" + email + "<br /><a href='https://" + website + "' target='_blank' >" + website + "</a><br />" + skills;
    
    // adding css classes
    cell1.classList.add("col-8","outline");
    cell2.classList.add("col-4","center","outline");
    row.classList.add("row","fadeIn");
    img.classList.add("imgResize");

    // adding data to newly created row
    cell1.appendChild(data);
    cell2.appendChild(img);
} 
form.addEventListener('submit', handleForm);

function clearform() {
    document.getElementById('form').reset()
}