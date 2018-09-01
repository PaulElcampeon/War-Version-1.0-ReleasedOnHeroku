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


/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {

    document.getElementById("myDropdown").classList.toggle("show");

}


// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {

    if (!event.target.matches('.dropbtn')) {

        var dropdowns = document.getElementsByClassName("dropdown-content");
        var i;

        for (i = 0; i < dropdowns.length; i++) {

            var openDropdown = dropdowns[i];

            if (openDropdown.classList.contains('show')) {
                openDropdown.classList.remove('show');
            }
        }
    }
};


function logout() {

     console.log("LOGGING OUT");
     warriorData.isOnline = false;
     saveUserDetails(warriorData);
     sessionStorage.removeItem("warriorData");

     try {

         sessionStorage.removeItem("otherUser");

     } catch(err) {

         alert("NO OTHER USER DATA");

     }

     location.href = './Home.html';
 }

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////REQUESTS////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function getWarriorsList() {

    console.log("GETTING WARRIOR LIST");

    let url = 'https://intense-peak-18063.herokuapp.com/api/getAll/warrior/' + lowerLimit;

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


function saveUserDetails(data) {

    let url = 'https://intense-peak-18063.herokuapp.com/api/update/warrior';

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
            console.log(data)
        })
}

// function getWarriorListBasedOnLevel() {
//     console.log("GETTING WARRIOR LIST BASED ON LEVEL");
//
//     let url = 'http://localhost:8080/api/getAll/warrior/level';
//
//     fetch(url, {
//         method:'GET'
//     })
//         .then(res => res.json())
//         .catch(error => console.error('Error:', error))
//         .then((data) => {
//             console.log("DATA FROM WARRIOR LIST BASED ON LEVEL REQUEST");
//             console.log(data);
//
//             if (data.message == "failure") {
//
//                 decreaseUpperLimitBy10();
//
//             } else {
//
//                 console.log(data.warriorListData);
//                 displayWarriorList(data.warriorListData);
//
//             }
//         })
// }
//
// function getWarriorListBasedOnVictories() {
//     console.log("GETTING WARRIOR LIST BASED ON VICTORIES");
//
//     let url = 'http://localhost:8080/api/getAll/warrior/victories';
//
//     fetch(url, {
//         method:'GET'
//     })
//         .then(res => res.json())
//         .catch(error => console.error('Error:', error))
//         .then((data) => {
//             console.log("DATA FROM WARRIOR LIST BASED ON VICTORIES REQUEST");
//             console.log(data);
//
//             if (data.message == "failure") {
//
//                 decreaseUpperLimitBy10();
//
//             } else {
//
//                 console.log(data.warriorListData);
//                 displayWarriorList(data.warriorListData);
//
//             }
//         })
// }

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


// document.getElementById("sortByLevel").addEventListener("click", () => {
//
//     getWarriorsLisBasedOnLevel();
// });
//
//
// document.getElementById("sortByVictories").addEventListener("click", () => {
//
// });


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