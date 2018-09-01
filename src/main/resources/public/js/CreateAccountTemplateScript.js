
document.getElementById("createAccountBtn").addEventListener("click", () => {

     let username1 = document.getElementById("username").value;
     let password1 = document.getElementById("password1").value;
     let password2 = document.getElementById("password2").value;
     let imageUrl = document.getElementById("imageUrl").value;
     let data = {username: username1, passwordX: password1, passwordY: password2, image: imageUrl};
     createAccount(data);

});


function checkData(data) {

    if (data.message == "passwordError") {
        alert("PASSWORD ERROR");
    }

    if (data.message == "usernameError") {
        alert("USERNAME ALREADY EXISTS");
    }

    if (data.message == "success") {
        sessionStorage.setItem("warriorData", JSON.stringify(data.clientData))
        location.href = './UserAccountTemplate.html';
    }

}

function createAccount(data) {

    let url = 'https://war-version-0.herokuapp.com/createUser';

    fetch(url, {
        method: 'POST', // or 'PUT'
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers:{
            'Content-Type': 'application/json; charset=utf-8'
        }
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {
         console.log(data);
         checkData(data);
        })
}