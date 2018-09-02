var leaderBoardDiv, upperLimit, lowerLimit, warriorData;

upperLimit = 10;
lowerLimit = 0;
leaderBoardDiv = document.getElementById("leaderBoardDiv");

if (document.readyState !== 'loading') {

    ready();

} else {

    document.addEventListener('DOMContentLoaded', ready);

}

function ready () {

    warriorData = JSON.parse(sessionStorage.getItem("warriorData"));
    getWarriorsList();

}

function displayWarriorList(data) {

    leaderBoardDiv.innerHTML = "";

    for (let i = 0; i < data.length; i++) {

        let div = document.createElement("div");
        let p = document.createElement("p");
        let img = document.createElement("img");
        let button = document.createElement("button");
        let breakTag1 = document.createElement("br");
        let breakTag2 = document.createElement("br");

        p.innerHTML = "Name: " + data[i].name + "<br> Level: " + data[i].level + "<br> Rank: " + data[i].title + "<br> Online: " + data[i].isOnline;
        img.src = data[i].imageUrl;
        button.innerHTML = "MORE";
        button.addEventListener("click", () => {
            sessionStorage.setItem("otherUserData",JSON.stringify(data[i]));
            location.href = "./UserProfile.html"
        });

        div.appendChild(p);
        div.appendChild(breakTag1);
        div.appendChild(img);
        div.appendChild(breakTag2);
        div.appendChild(button);
        leaderBoardDiv.appendChild(div);
    }
}


function increaseUpperLimitBy10() {

     upperLimit += 10;
     lowerLimit += 10;
     console.log("Value of upperLimit is");
     console.log(upperLimit);

}


function decreaseUpperLimitBy10() {

    upperLimit -= 10;
    lowerLimit -= 10;
    console.log("Value of upperLimit is");
    console.log(upperLimit)

}


function logout() {

     console.log("LOGGING OUT");
     logOutWarrior(warriorData);
     sessionStorage.removeItem("warriorData");

     try {

         sessionStorage.removeItem("otherUser");

     } catch(err) {

         alert("NO OTHER USER DATA");

     }

     location.href = './index.html';
 }

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////REQUESTS////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////



function logOutWarrior(data) {

    let url = 'https://war-version-0.herokuapp.com/api/logout/warrior/' + warriorData.name;

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



function getWarriorsList() {

    console.log("GETTING WARRIOR LIST");

    let url = 'https://war-version-0.herokuapp.com/api/getAll/warrior/' + lowerLimit;

    fetch(url, {
        method:'GET'
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {
            console.log("DATA FROM WARRIOR LIST REQUEST");
            console.log(data);

            if (data.message == "failure") {

                  decreaseUpperLimitBy10();

                } else {

                    console.log(data.warriorListData);
                    displayWarriorList(data.warriorListData);

                }
        })
}




///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////BUTTON EVENT LISTENERS//////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


document.getElementById("next10").addEventListener("click", () => {

    increaseUpperLimitBy10();
    getWarriorsList();

});


document.getElementById("previous10").addEventListener("click", () => {

    if (upperLimit == 10) {
        //do nothing
    } else {

        decreaseUpperLimitBy10();
        getWarriorsList();

    }
});


document.getElementById("logoutBtn").addEventListener("click",() => {

    logout();

});


document.getElementById("backToUserProfileBtn").addEventListener("click",() => {

     location.href ="./UserAccountTemplate.html";

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