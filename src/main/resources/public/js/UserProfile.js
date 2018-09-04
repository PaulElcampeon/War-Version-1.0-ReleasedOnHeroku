var otherUserData, warriorData;


if (document.readyState !== 'loading') {

    ready();

} else {

    document.addEventListener('DOMContentLoaded', ready);

}

function ready () {

    otherUserData = JSON.parse(sessionStorage.getItem("otherUserData"));
    warriorData = JSON.parse(sessionStorage.getItem("warriorData"));
    console.log(otherUserData);
    populateUserInfoDiv(otherUserData);
    setInterval(checkWhenUserWasLastActive, 300000)//5 minutes

}


function populateUserInfoDiv(data) {

    console.log("POPULATING USER INFO DIV");

    let userNameHolder = document.getElementById("userNameHolder");
    let titleHolder = document.getElementById("titleHolder");
    let levelHolder = document.getElementById("levelHolder");
    let imageHolder = document.getElementById("imageHolder");
    let moneyHolder = document.getElementById("moneyHolder");
    let experienceBar = document.getElementById("experienceBar");
    let healthPointsBar = document.getElementById("healthPointsBar");

    userNameHolder.innerHTML = data.name;
    titleHolder.innerHTML = "Rank: " + data.title;
    levelHolder.innerHTML = "Level: " + data.level;
    imageHolder.src = data.imageUrl

}



function logout() {

    console.log("LOGGING OUT");
//    warriorData.isOnline = false;
    logoutWarrior(warriorData);

}

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////REQUESTS///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////

//function saveUserDetails(data) {
//
//    let url = 'http://localhost:8080/api/update/warrior';
//
//    fetch(url, {
//        method:'PUT',
//        body: JSON.stringify(data),
//        headers:{
//            'Content-Type': 'application/json'
//        }
//    })
//        .then(res => res.json())
//        .catch(error => console.error('Error:', error))
//        .then((data) => {
//            sessionStorage.removeItem("warriorData");
//            sessionStorage.removeItem("otherUser");
//            location.href = './index.html'
//        })
//}

function logoutWarrior(data) {

    let url = 'https://war-version-0.herokuapp.com/api/logout/warrior';

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
        console.log(data);
    location.href = './index.html';


})
}


///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////BUTTON EVENT LISTENERS//////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////



document.getElementById("logoutBtn").addEventListener("click",() => {

    logout();

});

document.getElementById("backToUserProfileBtn").addEventListener("click",() => {

    location.href ="./UserAccountTemplate.html"

});

document.getElementById("backToLeaderBoardBtn").addEventListener("click", () => {

    location.href ="./LeaderBoard.html";

});


////////////////////////////////////////////////////////////////////////////////////////
//////FUNCTION TO CHECK USERS ACTIVITY IF MORE THAN 15 MINUTES INACTIVE LOG OUT/////////
///////////////////////////////////////////////////////////////////////////////////////

function checkWhenUserWasLastActive() {

    if ((new Date().getTime() - lastActive) >= 900000) {//15 minutes 900000

        logout();

    }
}

function setLastActive() {

    lastActive = new Date().getTime();
    console.log((new Date(lastActive)).toDateString());
    console.log(lastActive);

}


////////////////////////////////////////////////////////////////////////////////////////
//////FUNCTION TO SET WHEN USER IS LAST ACTIVE WHEN EVER USER CLICKS ON SCREEN/////////
///////////////////////////////////////////////////////////////////////////////////////


document.onclick = function(event) {

    if (event === undefined) {
        //do nothing
    } else {

        event = window.event;
        var target= 'target' in event? event.target : event.srcElement;
        setLastActive();
    }
};