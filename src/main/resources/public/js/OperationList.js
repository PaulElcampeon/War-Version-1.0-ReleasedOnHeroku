var warriorData;

if (document.readyState !== 'loading') {
    ready();
} else {
    document.addEventListener('DOMContentLoaded', ready);
}

function ready () {
    checkSessionStorage();
    setInterval(checkWhenUserWasLastActive, 300000)//5 minutes
}

function populatePage(data){
    let listOfButtons = document.getElementsByClassName("operationInfo");
    console.log(listOfButtons);
    for(let i=0; i<data.length; i++){
        console.log(i);
        listOfButtons[i].innerHTML = data[i].name+"<br> Level: " + data[i].level + "<br> Experience: " + data[i].experience+"" +
            "<br> Duration: " + data[i].duration + " minutes<br> Money: £" + data[i].money + "<br> Prize type: " + data[i].prizeType + "" +
            "<br> Chance Of Success: "+data[i].chanceOfSuccess+" (If same level as operation)";
        listOfButtons[i].addEventListener("click",()=>{
            sessionStorage.setItem('operationData', JSON.stringify(data[i]));
            location.href="./OperationProfile.html"
        })
    }

    for(let i = data.length; i < 10; i++){
        listOfButtons[i].style.display = "none";
     }
}

function next10(){

    getOperationList();
 }


function logout(){
    console.log("LOGGING OUT");
    warriorData.isOnline = false;
    saveUserDetails(warriorData)
    sessionStorage.removeItem("warriorData");
    try {
         sessionStorage.removeItem("otherUser");
    }
    catch(err) {
        alert("NO OTHER USER DATA")
    }

    location.href = './index.html'
 }

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////REQUESTS///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////

function getOperationList(){
    let url = "https://war-version-0.herokuapp.com/api/get/operation/random/"+warriorData.level;
    fetch(url, {
            method:'GET'
        })
            .then(res => res.json())
            .catch(error => console.error('Error:', error))
            .then((data) => {
                console.log(data)
                populatePage(data)
            })
}

function saveUserDetails(data){
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
                 })
}

////////////////////////////////////////////////////////////////////////////////////////
///////////////////FUNCTION TO CHECK SESSION STORAGE HAS USER INFO//////////////////////
///////////////////////////////////////////////////////////////////////////////////////

 function checkSessionStorage(){
     warriorData = JSON.parse(sessionStorage.getItem("warriorData"));
     if(warriorData != null){
        getOperationList();
     }else{
         location.href ="./Home.html"
     }

 }

 ///////////////////////////////////////////////////////////////////////////////////////
 ///////////////////////////BUTTON EVENT LISTENERS//////////////////////////////////////
 ///////////////////////////////////////////////////////////////////////////////////////

 document.getElementById("backToUserProfileBtn").addEventListener("click",()=>{
     location.href ="./UserAccountTemplate.html"
 })

document.getElementById("logoutBtn").addEventListener("click",()=>{
    logout();
})

 document.getElementById("next10").addEventListener("click",()=>{
    next10();
 })


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
     console.log((new Date(lastActive)).toDateString())
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