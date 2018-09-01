var music, isMusicPlaying, data, passwordIncorrectWarning;

isMusicPlaying = false;
music = document.getElementById("audio1");
passwordIncorrectWarning = document.getElementById("passwordIncorrectWarning");
passwordIncorrectWarning.style.display = "none";


function formingUserData(){

    console.log("FORMING USER DATA");

    let username1 = document.getElementById("user").value;
    let password1 = document.getElementById("pass").value;
    data = {username: username1, password: password1};

}


function direction(data) {

    if (data == "failure") {

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

    let url = 'https://war-version-1-point-0-proto.herokuapp.com/login';

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

    let url = 'https://war-version-1-point-0-proto.herokuapp.com/api/update/warrior';

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

    formingUserData();
    confirmUserCredentials(data);

});


document.getElementById("createAccount").addEventListener("click",() => {

    location.href = './CreateAccountTemplate.html';

});


document.getElementById("playAudioBtn").addEventListener("click", () => {

    if(isMusicPlaying == false) {

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
