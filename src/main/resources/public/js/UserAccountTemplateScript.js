var warriorData, warriorBattleReceipts, warriorOperation, warriorName, myInterval,
    activeOperationsDiv, activeOperationTitle, activeOperationResults, bagDiv, armourDiv,
    weaponDiv, checkForOperations, warriorBag, warriorArmourHead, warriorArmourLeg,
    warriorArmourChest, warriorWeapon, warriorArmourBag, warriorWeaponBag, warriorElixirBag,
    attackAndDefenseStatsDiv, bagRows, isOnOperation, hasMessage, lastActive, startTimeProgressBar,
    endTimeProgressBar, progressBarFunc, progressBarDiv, progressBarDivOuter, checkElixirInterval;

checkForOperations = true;
bagDiv = document.getElementById("bagDiv");
bagRows = document.getElementsByClassName("bagRows");
armourDiv = document.getElementById("armourDiv");
weaponDiv = document.getElementById("weaponDiv");
activeOperationsDiv = document.getElementById("activeOperationsDiv");
activeOperationTitle = document.getElementById("activeOperationTitle");
activeOperationResults = document.getElementById("activeOperationResults");
attackAndDefenseStatsDiv = document.getElementById("attackAndDefenseStatsDiv");
progressBarDiv = document.getElementById("progressBarDiv");
progressBarDivOuter = document.getElementById("progressBarDivOuter");

bagDiv.style.display = "none";
armourDiv.style.display = "none";
weaponDiv.style.display = "none";
attackAndDefenseStatsDiv.style.display = "none";
progressBarDivOuter.style.display = "none";

if (document.readyState !== 'loading') {

    ready();

} else {

    document.addEventListener('DOMContentLoaded', ready);

}

function ready () {

    checkSessionStorage();
    setInterval(checkWhenUserWasLastActive, 300000)//5 minutes
//    checkElixirInterval = setInterval(checkIfElixirHasRunOutPrep, 10000);
//    displayActiveElixir();


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
    imageHolder.src = data.imageUrl;

    let multiplierForExperienceDiv = 200/data.experiencePointsGoal;
    let experiencePointsWidth = ((data.experiencePoints * multiplierForExperienceDiv)/200) * 100;

    experienceBar.style.width = experiencePointsWidth + "%";
    experienceBar.innerHTML = data.experiencePoints + "/" + data.experiencePointsGoal;

    let multiplierForHealthDiv = 200/data.healthPointsGoal;
    let healthPointsWidth = ((data.healthPoints * multiplierForHealthDiv)/200) * 100;

    healthPointsBar.style.width = healthPointsWidth + "%";
    healthPointsBar.innerHTML = data.healthPoints + "/" + data.healthPointsGoal;
    moneyHolder.innerHTML = "Money: £" + data.money;

    displayBag();
    displayArmour();
    displayWeapon();
    displayAttackAndDefenseStats();
}

function displayAttackAndDefenseStats() {

    attackAndDefenseStatsDiv.innerHTML = ""; //clearing it

    let attackHeader = document.createElement("h2");
    let defenseHeader = document.createElement("h2");
    let damageStats = document.createElement("p");
    let defenseStats = document.createElement("p");
    let breakTag = document.createElement("br");

    attackHeader.innerHTML = "Damage Stats<br>";
    defenseHeader.innerHTML = "Defense Stats<br>";

    let tp = warriorData.bonusDamage+warriorWeapon.topRangeDamage;
    let bp = warriorData.bonusDamage+warriorWeapon.bottomRangeDamage;
    let totalDefense = warriorData.bonusDefense + warriorArmourHead.defenseLevel + warriorArmourChest.defenseLevel + warriorArmourLeg.defenseLevel;
    let damageReduction = (totalDefense/400) * 100;

    damageStats.innerHTML = "Bonus Damage: " + warriorData.bonusDamage + "<br> Attack Range: " + warriorWeapon.topRangeDamage + " - " + warriorWeapon.bottomRangeDamage + "<br>Attack Range (with Bonus Damage) : " + tp + " - " + bp;
    defenseStats.innerHTML = "Bonus Defense: " + warriorData.bonusDefense + "<br> Total Defense Level (with armour) : " + totalDefense + "<br> Reduces Damage Taken by: " + (damageReduction.toFixed(2)) + "%";

    attackAndDefenseStatsDiv.appendChild(attackHeader);
    attackAndDefenseStatsDiv.appendChild(damageStats);
    attackAndDefenseStatsDiv.appendChild(breakTag);
    attackAndDefenseStatsDiv.appendChild(defenseHeader);
    attackAndDefenseStatsDiv.appendChild(defenseStats);
}

function displayBattleReceipts(data) {

    console.log("DISPLAYING BATTLE RECEIPTS");

    let arrayOfDivElements = [];
    let battleReceiptListSize = data.length;
    let battleReceiptDiv = document.getElementById("battleReceiptDiv");

    battleReceiptDiv.innerHTML = "";

    for(let i = 0; i < battleReceiptListSize; i++){
        arrayOfDivElements.push(document.createElement("div"));
    }

    for(let j = 0; j < battleReceiptListSize; j++){
       arrayOfDivElements[j].innerHTML = "Victor: " + data[j].victor+" Loser: " + data[j].loser + " Date: " + data[j].dateOfBattle;
    }

    for(let z = 0; z < battleReceiptListSize; z++){
        battleReceiptDiv.appendChild(arrayOfDivElements[z]);
    }
}


function displayWarriorList(data) {

    console.log("DISPLAYING WARRIOR LIST");

    let warriorListSize = data.length;
    let warriorListDiv = document.getElementById("warriorListDiv");
    let arrayOfDivElements = [];
    let arrayOfButtonElements = [];

    warriorListDiv.innerHTML = "";

    for(let i = 0; i < warriorListSize; i++){
        arrayOfDivElements.push(document.createElement("div"));
        arrayOfButtonElements.push(document.createElement("button"));
    }

    for(let j = 0; j < warriorListSize; j++) {
       arrayOfDivElements[j].innerHTML = data[j].name + " " + data[j].healthPoints;
       arrayOfButtonElements[j].addEventListener("click",() => {
           let fightersNames = {attacker:warriorName, defender:data[j].name};
           sessionStorage.setItem("fighters", JSON.stringify(fightersNames));
           location.href = "./Fight.html"
       })
       arrayOfButtonElements[j].innerHTML = "Fight";
       arrayOfDivElements[j].appendChild(arrayOfButtonElements[j])
    }

    for(let z = 0; z < warriorListSize; z++) {
        warriorListDiv.appendChild(arrayOfDivElements[z]);
    }
}

//I HAVE PUT THIS IN THE POPULATEUSERINFO FUNCTION
function displayBag() {

    bagRows[0].innerHTML = "";
    bagRows[1].innerHTML = "";
    bagRows[2].innerHTML = "";

    var indexOfItem = 0;

    for(let k = 0; k < warriorArmourBag.length; k++) {

        let div = document.createElement("div");
        let itemButtons = document.createElement("button");
        let itemStats = document.createElement("p");
        let itemImages = document.createElement("img");

        itemStats.innerHTML = warriorArmourBag[k].name + "<br>Defense Level: " + warriorArmourBag[k].defenseLevel + "<br>Type: " + warriorArmourBag[k].bodyPart;
        itemImages.src = warriorArmourBag[k].imageUrl;
        itemButtons.innerHTML = "EQUIP";
        itemButtons.addEventListener("click",() => {
            equipArmour(warriorData, k);
            bagDiv.style.display = "none"
        });

        let breakTag1 = document.createElement("br");
        let breakTag2 = document.createElement("br");

        div.appendChild(itemStats);
        div.appendChild(breakTag1);
        div.appendChild(itemImages);
        div.appendChild(breakTag2);
        div.appendChild(itemButtons);
        appendItemToCorrectRow(indexOfItem, div);

        indexOfItem++;
    }
    for(let j = 0; j < warriorWeaponBag.length; j++) {

        let div = document.createElement("div");
        let itemButtons = document.createElement("button");
        let itemStats = document.createElement("p");
        let itemImages = document.createElement("img");

        itemStats.innerHTML = warriorWeaponBag[j].name + "<br>Attack Range: " + warriorWeaponBag[j].topRangeDamage + " - " + warriorWeaponBag[j].bottomRangeDamage;
        itemImages.src = warriorWeaponBag[j].imageUrl;
        itemButtons.innerHTML = "EQUIP";
        itemButtons.addEventListener("click",()=>{
            equipWeapon(warriorData, j);
            bagDiv.style.display = "none"
        });

        let breakTag1 = document.createElement("br");
        let breakTag2 = document.createElement("br");

        div.appendChild(itemStats);
        div.appendChild(breakTag1);
        div.appendChild(itemImages);
        div.appendChild(breakTag2);
        div.appendChild(itemButtons);
        appendItemToCorrectRow(indexOfItem, div);
        indexOfItem++;
    }

    for(let z = 0; z < warriorElixirBag.length; z++) {

        let div = document.createElement("div");
        let itemButtons = document.createElement("button");
        let itemStats = document.createElement("p");
        let itemImages = document.createElement("img");

        itemStats.innerHTML = warriorElixirBag[z].name + "<br>Type: " + warriorElixirBag[z].typeOfElixir + "<br>Amount: " + warriorElixirBag[z].amount + "<br>Duration: " + warriorElixirBag[z].duration;
        itemImages.src = warriorElixirBag[z].imageUrl;
        itemButtons.innerHTML = "USE";
        itemButtons.addEventListener("click",() => {
            if (warriorData.elixirAmount == 0) {
                useElixir(warriorData, z);
                bagDiv.style.display = "none";

                if (warriorElixirBag[z].typeOfElixir != "HEALTH") {
                    document.getElementById("elixirAffect").innerHTML = "Elixir Affect: " + warriorElixirBag[z].typeOfElixir + "<br> Expiration Time: " + new Date((new Date()).getTime() + (60000 * warriorElixirBag[z].duration)).toLocaleTimeString();
                }

                try {
                    clearInterval(checkElixirInterval);
                } catch (err) {
                    console.log(err.message);
                }
                checkElixirInterval = setInterval(checkIfElixirHasRunOutPrep, 60000);
            } else {

                alert("YOU CANT DO THAT YOU HAVE ALREADY CONSUMED AN ELIXIR AND STILL HAVE THE AFFECTS");
            }
        });

        let breakTag1 = document.createElement("br");
        let breakTag2 = document.createElement("br");

        div.appendChild(itemStats);
        div.appendChild(breakTag1);
        div.appendChild(itemImages);
        div.appendChild(breakTag2);
        div.appendChild(itemButtons);
        appendItemToCorrectRow(indexOfItem, div);

        indexOfItem++;
    }

    fillEmptyBagSlots(indexOfItem);
}

function appendItemToCorrectRow(indexOfItem, divObject) {

    if (indexOfItem <= 3) {
        bagRows[0].appendChild(divObject);
    }

    if (indexOfItem > 3 && indexOfItem < 8) {
        bagRows[1].appendChild(divObject);
    }

    if (indexOfItem >= 8 && indexOfItem < 12) {
        bagRows[2].appendChild(divObject);
    }
}

function fillEmptyBagSlots(indexOfItem) {

    for (let j = indexOfItem; j < 12; j++) {

        let div = document.createElement("div");
        let itemStats = document.createElement("p");
        let itemImages = document.createElement("img");
        let breakTag1 = document.createElement("br");
        let breakTag2 = document.createElement("br");

        itemStats.innerHTML = "EMPTY";

        div.appendChild(itemStats);
        div.appendChild(breakTag1);
        div.appendChild(breakTag2);
        appendItemToCorrectRow(indexOfItem, div);
        indexOfItem++;
    }
}

function displayArmour() {

    console.log("DISPLAY ARMOUR FUNCTION");

    let armourHeadName = document.getElementById("armourHeadName");
    let armourHeadDefenseLevel = document.getElementById("armourHeadDefenseLevel");
    let armourHeadImage = document.getElementById("armourHeadImage");
    let armourChestName = document.getElementById("armourChestName");
    let armourChestDefenseLevel = document.getElementById("armourChestDefenseLevel");
    let armourChestImage = document.getElementById("armourChestImage");
    let armourLegsName = document.getElementById("armourLegsName");
    let armourLegDefenseLevel = document.getElementById("armourLegDefenseLevel");
    let armourLegsImage = document.getElementById("armourLegsImage");

    //populate head
    armourHeadName.innerHTML = "Name: " + warriorArmourHead.name;
    armourHeadDefenseLevel.innerHTML = "Defense Level: " + warriorArmourHead.defenseLevel;
    armourHeadImage.src = warriorArmourHead.imageUrl;

    //populate chest
    armourChestName.innerHTML = "Name: " + warriorArmourChest.name;
    armourChestDefenseLevel.innerHTML = "Defense Level: " + warriorArmourChest.defenseLevel;
    armourChestImage.src = warriorArmourChest.imageUrl;

    //populate legs
    armourLegsName.innerHTML = "Name: " + warriorArmourLeg.name;
    armourLegDefenseLevel.innerHTML = "Defense Level: " + warriorArmourLeg.defenseLevel;
    armourLegsImage.src = warriorArmourLeg.imageUrl;

}

function displayWeapon() {

    console.log("DISPLAY WEAPON FUNCTION");

    let weaponName = document.getElementById("weaponName");
    let weaponDamage = document.getElementById("weaponDamage");
    let weaponImage = document.getElementById("weaponImage");

    weaponName.innerHTML = "Name: " + warriorWeapon.name;
    weaponDamage.innerHTML = "Attack Range: " + warriorWeapon.topRangeDamage + " - " + warriorWeapon.bottomRangeDamage;
    weaponImage.src = warriorWeapon.imageUrl;
}

function logout() {

    console.log("LOGGING OUT");

    warriorData.isOnline = false;
    saveUserDetails(warriorData);
    sessionStorage.removeItem("warriorData");

    try {
        sessionStorage.removeItem("otherUser");
    } catch(err) {
        alert("NO OTHER USER DATA")
    }

    location.href = './index.html';
}

function populateActiveOperationsDiv(operationData) {

    if (checkIfUserHasOperations()) {

        document.getElementById("previousOperationResults").innerHTML = "";

        console.log("POPULATING ACTIVE OPERATIONS DIV");
        console.log(operationData);

        activeOperationTitle.style.display = "none";
        activeOperationResults.style.display = "none";
        activeOperationsDiv.innerHTML = "";
        activeOperationTitle.innerHTML = "ACTIVE OPERATION";

        for (let i = 0; i < operationData.length; i++) {

            if (operationData[i].isActive) {

                let p = document.createElement("p");

                p.innerHTML = "Operation: " + operationData[i].name + "<br> Level: " + operationData[i].level + "<br> ExperienceSetter Points: " + operationData[i].experience + "<br> Payment: £" + operationData[i].money + "<br> End Time: " + (new Date(Number(operationData[i].endTime)).toLocaleTimeString());

                activeOperationsDiv.appendChild(p);
                startTimeProgressBar = operationData[i].startTime;
                endTimeProgressBar  = operationData[i].endTime;
                progressBarDivOuter.style.display = "block";
                progressBarFunc = setInterval(progressBarAnimation, 2000);
            }
        }

        if(checkIfAnyActiveOperations()) {

            console.log("CHECKING FOR ACTIVE OPERATION COMPLETION");
            myInterval = setInterval(checkIfOperationIsCompleteInitializer, 5000);

        } else {

            console.log("YOU HAVE NO ACTIVE OPERATIONS, WE ARE NO LONGER CHECKING FOR OPERATIONS");
            clearInterval(myInterval);
        }
    }

}

function checkIfUserHasOperations() {

    if (warriorOperation.length > 0) {

        console.log("YOU HAVE AN OPERATION")
        return true;

    }

    console.log("YOU HAVE NO OPERATION");
//    clearInterval()//CLEAR INTERVALE FOR CHECK IF OPERATION IS COMPLETEiNITIALIZER
    return false;
}


function checkIfAnyActiveOperations(){

    if (warriorOperation[0].isActive) {

        return true;

    }

    return false;
}


function checkIfOperationIsCompleteInitializer() {

    console.log("INITIALIZING CHECK IF OPERATION IS COMPLETE FUNC");

    let data = {name: warriorData.name};

    checkIfOperationIsComplete(data);
}


function directResponse(data) {

    if (data.message == "completed") {

        if (data.success == true) {
            document.getElementById("previousOperationResults").innerHTML = "You have completed your last quest and were successful check your bag for a reward"
            // confirm("You have completed your operation and were successful");
        }

        if (data.success == false) {
            document.getElementById("previousOperationResults").innerHTML = "You have completed your last quest and were unsuccessful"
        }

        warriorData = data.avatar;
        warriorData.operationList[0].isActive = false;
        console.log("WE HAVE SET THE OPERATION isActive TO FALSE");
        warriorData.isOnOperation = false;
        console.log("WE HAVE SET THE isOnOperation TO FALSE");
        activeOperationsDiv.innerHTML = "";
        activeOperationTitle.style.display = "block";
        activeOperationResults.style.display = "block";
        warriorData.operationList.pop();

        clearInterval(myInterval);

        console.log(warriorData);
        console.log("we have removed the operation");

        saveUserDetails(warriorData);

//        setWarriorData(warriorData);
//        populateUserInfoDiv(warriorData);
//        populateActiveOperationsDiv(warriorOperation);
////        saveUserDetails(warriorData);
//        displayBag();
    }
}

function checkIfElixirHasRunOutPrep() {

    checkIfElixirHasRunOut(warriorData);

}

function checkIfElixirIsOut(data) {

    if (!data.hasConsumedElixir) {

        document.getElementById("elixirAffect").innerHTML = "";
        clearInterval(checkElixirInterval);
        console.log("WE ARE NO LONGER CHECKING FOR ELIXIRS")

    }

}

function displayActiveElixir() {

    if (warriorData.elixirAmount != 0) {

        document.getElementById("elixirAffect").innerHTML = "Elixir Affect: " + warriorData.elixirAffect + "<br> Expiration Time: " + new Date(Number(warriorData.elixirEndTime)).toLocaleTimeString();

    }

}

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////REQUESTS////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function checkIfOperationIsComplete(data) {

    console.log("SENDING REQUEST TO CHECK IF ACTIVE OPERATION IS COMPLETE");

    let url = "https://war-version-0.herokuapp.com/operationComplete/" + warriorData.name;

    fetch(url, {
        method:'POST',
        mode: 'no-cors',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then(data => {
            console.log("DATA FROM CHECKING IF ACTIVE OPERATION IS COMPLETE REQUEST");
            console.log(data);
            directResponse(data);
        })
}


function getWarriorsList() {

    console.log("GETTING WARRIOR LIST");

    let url = 'https://war-version-0.herokuapp.com/api/getAll/warrior';

    fetch(url, {
        method:'GET'
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {
            if(data != undefined){
                console.log("DATA FROM WARRIOR LIST REQUEST");
                console.log(data);
                displayWarriorList(data)
            }
        })
}


function getWarriorsListExcept(name) {

    console.log("GETTING WARRIOR LIST");

    let url = 'https://war-version-0.herokuapp.com/api/getAll/warrior/except/' + name;

    fetch(url, {
        method:'GET'
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {
            if(data != undefined){
                console.log("DATA FROM WARRIOR LIST EXCEPT REQUEST");
                console.log(data);
                displayWarriorList(data)
            }
        })
}

function saveUserDetails(data) {

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
            console.log(data);
            console.log(warriorData);
            setSessionStorage(JSON.stringify(data));
            setWarriorData(data);
            populateUserInfoDiv(data);
            populateActiveOperationsDiv(data);
        })
}


function heal(data) {

    console.log("HEALING WARRIOR");

    let url = "https://war-version-0.herokuapp.com/hospital/fullheal";

    fetch(url, {
        method:'POST',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {
            console.log("DATA FROM HEAL REQUEST");
            console.log(data);
            setWarriorData(data);
            setSessionStorage(JSON.stringify(data));;
            populateUserInfoDiv(warriorData);
        })
}


function equipArmour(data, armourPosition) {

    console.log("EQUIPPING ARMOUR");

    let url = "https://war-version-0.herokuapp.com/equip/armour/" + armourPosition;

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
            console.log("DATA FROM EQUIPPING ARMOUR REQUEST");
            console.log(data);
            setWarriorData(data);
            setSessionStorage(JSON.stringify(data));
            populateUserInfoDiv(warriorData);
        })
}


function equipWeapon(data, weaponPosition) {

    console.log("EQUIPPING WEAPON");

    let url = "https://war-version-0.herokuapp.com/equip/weapon/" + weaponPosition;

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
            console.log("DATA FROM EQUIPPING WEAPON REQUEST");
            console.log(data);
            setWarriorData(data);
            setSessionStorage(JSON.stringify(data));
            populateUserInfoDiv(warriorData);
        })
}


function useElixir(data, elixirPosition){

    console.log("USING ELIXIR");

    let url = "https://war-version-0.herokuapp.com/use/elixir/" + elixirPosition;

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
            console.log("DATA FROM USE ELIXIR REQUEST");
            console.log(data);
            setWarriorData(data);
            setSessionStorage(JSON.stringify(data));
            populateUserInfoDiv(warriorData);
        })
}


function checkIfElixirHasRunOut(data){

    console.log("CHECKING IF ELIXIR HAS RUN OUT");

    let url = "https://war-version-0.herokuapp.com/check/elixir/";

    fetch(url, {
        method: 'PUT',
        mode: 'no-cors',
        body: JSON.stringify(data),
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
        .catch(error => console.error('Error:', error))
        .then((data) => {
            console.log("DATA FROM USE ELIXIR REQUEST");
            console.log(data);
            checkIfElixirIsOut(data);
            setWarriorData(data);
            setSessionStorage(JSON.stringify(data));
            populateUserInfoDiv(warriorData);
        })
}

////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////SETTING WARRIOR DATA//////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function setWarriorData(data) {
    //console logged in checkSessionStorage
    warriorData = data;
    warriorName = warriorData.name;
    warriorBattleReceipts = warriorData.battleReceipts;
    warriorOperation = warriorData.operationList;
    warriorBag = warriorData.bag;
    warriorArmourBag = warriorBag.armourBag;
    warriorWeaponBag = warriorBag.weaponBag;
    warriorElixirBag = warriorBag.elixirBag;
    warriorArmourHead = warriorData.armourHead;
    warriorArmourLeg = warriorData.armourLeg;
    warriorArmourChest = warriorData.armourChest;
    warriorWeapon = warriorData.weapon;
    isOnOperation = warriorData.isOnOperation;
    hasMessage = warriorData.hasMessage;

    console.log("WARRIOR DATA WE ARE SETTING");
    console.log(warriorData);
}


////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////SETTING SESSION STORAGE//////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function setSessionStorage(data) {

    sessionStorage.removeItem("warriorData");
    sessionStorage.setItem("warriorData", data);

}


///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////BUTTON EVENT LISTENERS//////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


document.getElementById("getBattleHistoryBtn").addEventListener("click",() => {

    hideBagWeaponArmourAttAndDefDivs();

    let battleReceiptDiv = document.getElementById("battleReceiptDiv");

    if (battleReceiptDiv.hasChildNodes()) {

        battleReceiptDiv.innerHTML = "";

    } else {

        displayBattleReceipts(warriorBattleReceipts);

    }
});


document.getElementById("warriorListExceptBtn").addEventListener("click",() => {

    hideBagWeaponArmourAttAndDefDivs();

    let warriorListDiv = document.getElementById("warriorListDiv");

    if(warriorListDiv.hasChildNodes()) {

        warriorListDiv.innerHTML = "";

    } else {

        getWarriorsListExcept(warriorData.name);

    }
});


document.getElementById("logoutBtn").addEventListener("click",() => {

    logout();

});


document.getElementById("healWarrior").addEventListener("click",() => {

    if (isOnOperation) {

        alert("CURRENTLY ON OPERATION, YOU CANNOT DO THIS");

    } else {

        hideBagWeaponArmourAttAndDefDivs();
        heal(warriorData);
    }
});


document.getElementById("operationListBtn").addEventListener("click",() => {

    if (isOnOperation) {

        alert("CURRENTLY ON OPERATION, YOU CANNOT DO THIS");

    } else {

        location.href="./OperationListTemplate.html"
    }
});


document.getElementById("getBagBtn").addEventListener("click",() => {

    if (isOnOperation) {

        alert("CURRENTLY ON OPERATION, YOU CANNOT DO THIS");

    } else {

        if (bagDiv.style.display == "none") {

            bagDiv.style.display = "block";
            weaponDiv.style.display = "none";
            armourDiv.style.display = "none";
            attackAndDefenseStatsDiv.style.display = "none";

        } else {

            bagDiv.style.display = "none";

        }
     }
});


document.getElementById("getArmourBtn").addEventListener("click",() => {

    if (armourDiv.style.display == "none") {

        armourDiv.style.display = "block";
        weaponDiv.style.display = "none";
        attackAndDefenseStatsDiv.style.display = "none";
        bagDiv.style.display = "none";

    } else {

        armourDiv.style.display = "none";

    }

});


document.getElementById("getWeaponBtn").addEventListener("click",() => {

    if (weaponDiv.style.display == "none") {

        weaponDiv.style.display = "block";
        bagDiv.style.display = "none";
        armourDiv.style.display = "none";
        attackAndDefenseStatsDiv.style.display = "none";

    } else {

        weaponDiv.style.display = "none";
    }
});


document.getElementById("attackAndDefenseStatsBtn").addEventListener("click",() => {

    if (attackAndDefenseStatsDiv.style.display == "none") {

        attackAndDefenseStatsDiv.style.display = "block";
        bagDiv.style.display = "none";
        weaponDiv.style.display = "none";
        armourDiv.style.display = "none";

    } else {

        attackAndDefenseStatsDiv.style.display = "none";
    }
});


document.getElementById("battleStatsBtn").addEventListener("click", () => {

    alert("THIS FEATURE HAS NOT BEEN ADDED YET");

});


document.getElementById("shopBtn").addEventListener("click",() => {

    if (isOnOperation) {

        alert("CURRENTLY ON OPERATION, YOU CANNOT DO THIS");

    } else {

        location.href = "./Shop.html";
    }
});


document.getElementById("leaderBoardBtn").addEventListener("click", () => {

    location.href = "./LeaderBoard.html";

});


////////////////////////////////////////////////////////////////////////////////////////
///////////////////FUNCTION TO CHECK SESSION STORAGE HAS USER INFO//////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function checkSessionStorage() {

    warriorData = JSON.parse(sessionStorage.getItem("warriorData"));

    if (warriorData != null) {

        console.log("DATA FROM SESSION STORAGE");
        console.log(warriorData);

        setWarriorData(warriorData);
        populateUserInfoDiv(warriorData);
        populateActiveOperationsDiv(warriorOperation);
        checkElixirInterval = setInterval(checkIfElixirHasRunOutPrep, 10000);
        displayActiveElixir();

    } else {

        location.href ="./Home.html";
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
    //    alert('clicked on '+target.tagName);
//        console.log("click on " + target.tagName)
        setLastActive();

    }
};


///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////PROGRESS BAR/////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////

function progressBarAnimation() {

    let divWidth = Math.round((endTimeProgressBar - new Date().getTime())/(endTimeProgressBar - startTimeProgressBar) * 100);

    progressBarDiv.style.width = divWidth + "%";
    progressBarDiv.innerHTML = divWidth + "%";

    if (new Date().getTime() >= endTimeProgressBar) {

        clearInterval(progressBarFunc);
        progressBarDivOuter.style.display = "none";

    }
}


function hideBagWeaponArmourAttAndDefDivs() {

    bagDiv.style.display = "none";
    weaponDiv.style.display = "none";
    armourDiv.style.display = "none";
    attackAndDefenseStatsDiv.style.display = "none";

}