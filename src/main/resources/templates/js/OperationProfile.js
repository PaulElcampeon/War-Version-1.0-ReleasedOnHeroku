var operationData, warriorData;


if (document.readyState !== 'loading') {

    ready();

} else {

    document.addEventListener('DOMContentLoaded', ready);
}


function ready () {

    checkSessionStorage();
    setInterval(checkWhenUserWasLastActive, 300000)//5 minutes

}


function populateOperationProfile(data) {

    console.log("POPULATE OPERATION PROFILE");

    let nameHolder = document.getElementById("nameHolder");
    let levelHolder = document.getElementById("levelHolder");
    let durationHolder = document.getElementById("durationHolder");
    let imageHolder = document.getElementById("imageHolder");
    let experienceHolder = document.getElementById("experienceHolder");
    let moneyHolder = document.getElementById("moneyHolder");

    nameHolder.innerHTML = "Operation: " + data.name;
    levelHolder.innerHTML = "Level: " + data.level;
    durationHolder.innerHTML = "Duration: " + data.duration + " minutes";
    imageHolder.src = data.image;
    experienceHolder.innerHTML = "Experience: " + data.experience;
    moneyHolder.innerHTML = "Money: £" + data.money;
    checkIfBagIsFull();
}


function logout() {

    console.log("LOGGING OUT");

    warriorData.isOnline = false;
    saveUserDetails(warriorData);
    sessionStorage.removeItem("warriorData");

    try {
             sessionStorage.removeItem("otherUser");
         }
         catch(err) {
             alert("NO OTHER USER DATA");
         }
    location.href = './Home.html'
}


function direction(data) {

    if (data.message == "false") {

        return alert("Your are already on a operation");

    }

    if (data.message == "success") {

        sessionStorage.setItem('warriorData', JSON.stringify(data.userData));
        return location.href = './UserAccountTemplate.html';

    }
}

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////REQUESTS///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function goOnOperation(data) {

    let url = "https://war-version-1-point-0-proto.herokuapp.com/GoOnOperation/" + warriorData.name + "/" + operationData.name;

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
            console.log("DATA FROM GOONOPERATIONCONTROLLER");
            direction(data);
        })
}


function saveUserDetails(data) {

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

        })
}


///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////BUTTON EVENT LISTENERS//////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


document.getElementById("logoutBtn").addEventListener("click",() => {

    logout();

});


document.getElementById("goOnOperationBtn").addEventListener("click",() => {

    goOnOperation(operationData);

    //need to send request to mission  end point
    //player field isOnMission needsto be set to true
    //this will stop him from being able to engage in any activities like battles or hospital

});


document.getElementById("backToUserProfileBtn").addEventListener("click",() => {

     location.href ="./UserAccountTemplate.html"

 });

////////////////////////////////////////////////////////////////////////////////////////
///////////////////FUNCTION TO CHECK SESSION STORAGE HAS USER INFO//////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function checkSessionStorage() {

    console.log("checking session storage");

    warriorData = JSON.parse(sessionStorage.getItem("warriorData"));

    if (warriorData != null) {

        operationData = JSON.parse(sessionStorage.getItem("operationData"));
        console.log(warriorData);
        console.log(operationData);
        populateOperationProfile(operationData);

    } else {

        location.href ="./Home.html";

    }
}


function checkIfBagIsFull() {

    if (warriorData.isBagFull) {

        confirm("BAG IS FULL, IF YOU GO ON ANOTHER OPERATION YOU WILL NOT RECEIVE ANY ITEMS, SELL ITEMS IN YOUR BAG TO MAKE SPACE");

    }
}


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