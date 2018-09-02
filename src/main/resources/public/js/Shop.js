var warriorData, warriorItems, bagItems;

bagItems = document.getElementById("bagItems");
warriorItems = document.getElementById("warriorItems");

warriorItems.style.display = "none";

if (document.readyState !== 'loading') {

    ready();

} else {

    document.addEventListener('DOMContentLoaded', ready);

}

function ready () {

    warriorData = JSON.parse(sessionStorage.getItem("warriorData"));
    populateWithMyItems();
    setInterval(checkWhenUserWasLastActive, 300000)//5 minutes

}


function populateWithMyItems() {

    bagItems.innerHTML = "";

    let warriorWeaponBagItems = warriorData.bag.weaponBag;
    let warriorArmourBagItems = warriorData.bag.armourBag;
    let warriorElixirBagItems = warriorData.bag.elixirBag;

    for (let i = 0; i < warriorWeaponBagItems.length; i++) {

        let div = document.createElement("div");
        let nameAndPrice = document.createElement("p");
        let sellButton = document.createElement("Button");
        let image = document.createElement("img");
        let breakTag1 = document.createElement("br");
        let breakTag2 = document.createElement("br");

         nameAndPrice.innerHTML = warriorWeaponBagItems[i].name + "<br> Price: " + warriorWeaponBagItems[i].price;
         image.src = warriorWeaponBagItems[i].imageUrl;
         sellButton.innerHTML = "SELL";
         sellButton.addEventListener("click",() => {
            warriorData.money += warriorData.bag.weaponBag[i].price
            warriorData.bag.weaponBag.splice(i, 1);
            console.log(warriorData);
//            populateWithMyItems();
            updateBagAndMoney(warriorData);
         });

         div.appendChild(nameAndPrice);
         div.appendChild(breakTag1);
         div.appendChild(image);
         div.appendChild(breakTag2);
         div.appendChild(sellButton);
         bagItems.appendChild(div);
    }

    for (let i = 0; i < warriorArmourBagItems.length; i++) {

            let div = document.createElement("div");
            let nameAndPrice = document.createElement("p");
            let sellButton = document.createElement("Button");
            let image = document.createElement("img");
            let breakTag1 = document.createElement("br");
            let breakTag2 = document.createElement("br");

             nameAndPrice.innerHTML = warriorArmourBagItems[i].name + "<br> Price: " + warriorArmourBagItems[i].price;
             image.src = warriorArmourBagItems[i].imageUrl;
             sellButton.innerHTML = "SELL";
             sellButton.addEventListener("click",()=>{
                warriorData.money += warriorArmourBagItems[i].price;
                warriorData.bag.armourBag.splice(i, 1);
                console.log(warriorData);
//                populateWithMyItems();
                updateBagAndMoney(warriorData);
             });

        div.appendChild(nameAndPrice);
        div.appendChild(breakTag1);
        div.appendChild(sellButton);
        div.appendChild(breakTag2);
        div.appendChild(image);
        bagItems.appendChild(div);

    }

    for (let i = 0; i < warriorElixirBagItems.length; i++) {

        let div = document.createElement("div");
        let nameAndPrice = document.createElement("p");
        let sellButton = document.createElement("Button");
        let image = document.createElement("img");
        let breakTag1 = document.createElement("br");
        let breakTag2 = document.createElement("br");

        nameAndPrice.innerHTML = warriorElixirBagItems[i].name + "<br> Price: " + warriorElixirBagItems[i].price;
        image.src = warriorElixirBagItems[i].imageUrl;
        sellButton.innerHTML = "SELL";
        sellButton.addEventListener("click",()=>{
            warriorData.money += warriorElixirBagItems[i].price;
            warriorData.bag.elixirBag.splice(i, 1);
            console.log(warriorData);
//           populateWithMyItems();
            updateBagAndMoney(warriorData);
        });

        div.appendChild(nameAndPrice);
        div.appendChild(breakTag1);
        div.appendChild(sellButton);
        div.appendChild(breakTag2);
        div.appendChild(image);
        bagItems.appendChild(div);
    }
}


function logout() {

    console.log("LOGGING OUT");

    logOutWarrior(warriorData);
    sessionStorage.removeItem("warriorData");

    try {
        sessionStorage.removeItem("otherUser");
    } catch(err) {
        alert("NO OTHER USER DATA")
    }

    location.href = './index.html';
}


///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////REQUESTS///////////////////////////////////////////
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


setWarriorData(data);
            setSessionStorage(data);
            populateWithMyItems();


function updateBagAndMoney(data) {

    let url = 'https://war-version-0.herokuapp.com/api/update/warrior/bag/money';

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
            setWarriorData(data);
            setSessionStorage(data);
            populateWithMyItems();
        })
}

////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////SETTING WARRIOR DATA//////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function setWarriorData(data) {

    warriorData = data;

}


////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////SETTING SESSION STORAGE//////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function setSessionStorage(data) {

    sessionStorage.removeItem("warriorData");
    sessionStorage.setItem("warriorData", JSON.stringify(data));

}

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////BUTTON EVENT LISTENERS//////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


document.getElementById("buyBtn").addEventListener("click",() => {

    warriorItems.style.display = "none";
    alert("THIS FEATURE HAS NOT BEEN ADDED YET");


});


document.getElementById("sellBtn").addEventListener("click",() => {

    warriorItems.style.display = "block";

});


document.getElementById("logoutBtn").addEventListener("click",()=>{
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