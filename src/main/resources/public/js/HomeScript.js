var music, isMusicPlaying, data, passwordIncorrectWarning, username1, password1;

isMusicPlaying = false;
music = document.getElementById("audio1");
passwordIncorrectWarning = document.getElementById("passwordIncorrectWarning");
passwordIncorrectWarning.style.display = "none";
username1 = document.getElementById("user");
password1 = document.getElementById("pass");


function formingUserData(){

    console.log("FORMING USER DATA");

    data = {username: username1.value, password: password1.value};

}


function direction(data) {

    if (data == "failure") {

        passwordIncorrectWarning. innerHTML = "PASSWORD INCORRECT";
        return passwordIncorrectWarning.style.display = "inline";

    } else if (data == "admin") {

        return location.href = './Admin.html';

    } else {

        data.isOnline = true;//SETTING USER TO ONLINE HERE
        saveUserDetails(data);

    }
}

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////REQUESTS///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function confirmUserCredentials(data) {

    console.log("CONFIRMING CREDENTIALS");

    let url = 'https://war-version-0.herokuapp.com/login';

    fetch(url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
.catch(error => console.error('Error:', error))
.then((data) => {
        console.log("DATA FROM CONFIRMING CREDENTIALS REQUEST " + data);
    direction(data.message);
})
}


function saveUserDetails(data){

    console.log("SAVING USERS DATA");

    let url = 'https://war-version-0.herokuapp.com/api/update/warrior';

    fetch(url, {
        method:'PUT',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
.catch(error => console.error('Error:', error))
.then((data) => {
        console.log("DATA RECEIVED FROM SAVE DATA REQUEST");
    console.log(data);
    sessionStorage.setItem('warriorData', JSON.stringify(data));
    //dont really want to have this here
    return location.href = './UserAccountTemplate.html';
})
}


///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////BUTTON EVENT LISTENERS//////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


document.getElementById("login").addEventListener("click",() => {

    if (username1.value == "" || password1.value == "") {

    passwordIncorrectWarning.innerHTML = "ENTER A USERNAME AND PASSWORD";
    return passwordIncorrectWarning.style.display = "inline";

} else {

    formingUserData();
    confirmUserCredentials(data);

}

});


document.getElementById("createAccount").addEventListener("click",() => {

    console.log("you clicked me");
location.href = './CreateAccountTemplate.html';

});


document.getElementById("playAudioBtn").addEventListener("click", () => {

    if (isMusicPlaying == false) {

    playAudio1();
    isMusicPlaying = true;

} else {

    pauseAudio1();
    isMusicPlaying = false;

}
});

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////MUSIC FUNCTIONS///////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function playAudio1() {

    music.play();

}

function pauseAudio1() {

    music.pause();
    music.currentTime = 0;

}
