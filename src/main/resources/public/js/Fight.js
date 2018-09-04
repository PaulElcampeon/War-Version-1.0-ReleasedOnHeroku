var attackersName, defendersName, attackersHPHolder, defendersHPHolder, attackersHP, defendersHP,
    attackersDamageP, defendersDamageP, engageBtnDiv, backToUserProfileDiv, healthPointsBarSize,
    multiplierForWarriorA, multiplierForWarriorD, vsDiv, attackersTurn, defendersTurn, defendersData,
    attackersData, indexOfDamagePatternA, indexOfDamagePatternD;

indexOfDamagePatternA = 0;
indexOfDamagePatternD = 0;
attackersTurn = true;
defendersTurn = false;
attackersHPHolder = document.getElementById("warriorHealthPointsBarA");
defendersHPHolder = document.getElementById("warriorHealthPointsBarD");
engageBtnDiv = document.getElementById("engageBtnDiv");
vsDiv = document.getElementsByClassName("vsDiv")[0];
vsDiv.style.display = "none";
backToUserProfileDiv = document.getElementsByClassName("backToUserProfileDiv")[0];
healthPointsBarSize = 200;

document.getElementById("attackersHitDisplay").style.visibility = "hidden";
document.getElementById("defendersHitDisplay").style.visibility = "hidden";


if (document.readyState !== 'loading') {

    ready();

} else {

    document.addEventListener('DOMContentLoaded', ready);
}


function ready () {

    let fightersDetails = JSON.parse(sessionStorage.getItem("fighters"));
    attackersName = fightersDetails.attacker;
    defendersName = fightersDetails.defender;
    getUserDetails(attackersName, "A");
    getUserDetails(defendersName, "D");
    setInterval(checkWhenUserWasLastActive, 300000)//5 minutes

}


function populateUserInfoDiv(data, type) {

    console.log("POPULATING WARRIOR INFO DIV");

    let userNameHolder = document.getElementById("warriorNameHolder" + type);
    let titleHolder = document.getElementById("warriorTitleHolder" + type);
    let levelHolder = document.getElementById("warriorLevelHolder" + type);
    let imageHolder = document.getElementById("warriorImageHolder" + type);
    let healthPointsBar = document.getElementById("warriorHealthPointsBar" + type);

    userNameHolder.innerHTML = data.name;
    titleHolder.innerHTML = data.title;
    levelHolder.innerHTML = "Level: " + data.level;
    imageHolder.src = data.imageUrl;

    let healthPercentage =  ((data.healthPoints/data.healthPointsGoal) * 100).toFixed(2);

    healthPointsBar.style.width = healthPercentage + "%";
    healthPointsBar.innerHTML = data.healthPoints + "/" + data.healthPointsGoal;
    displayAttackAndDefenseStats(data, type);

    if (type == "A") {

        attackersHP = data.healthPoints;

    } else if (type == "D") {

        defendersHP = data.healthPoints;
    }
}

function displayAttackAndDefenseStats(data, type) {

    let warriorAttackAndDefenseStatsDiv = document.getElementById("warriorAttackAndDefenseStatsDiv" + type);

    warriorAttackAndDefenseStatsDiv.innerHTML = ""; //clearing it

    let attackHeader = document.createElement("h2");
    let defenseHeader = document.createElement("h2");
    let damageStats = document.createElement("p");
    let defenseStats = document.createElement("p");
    let breakTag = document.createElement("br");

    attackHeader.innerHTML = "Damage Stats<br>";
    defenseHeader.innerHTML = "Defense Stats<br>";

    let totalDamageTopEnd = data.bonusDamage + data.weapon.topRangeDamage;
    let totalDamageBottomEnd = data.bonusDamage + data.weapon.bottomRangeDamage;
    let totalDefense = data.bonusDefense + data.armourHead.defenseLevel + data.armourChest.defenseLevel + data.armourLeg.defenseLevel;
    let damageReduction = (totalDefense/400) * 100;

    console.log(damageReduction);

    damageStats.innerHTML = "Bonus Damage: " + data.bonusDamage + "<br>Attack Range (with Bonus Damage) : " + totalDamageTopEnd + " - " + totalDamageBottomEnd;
    defenseStats.innerHTML = "Bonus Defense: " + data.bonusDefense + "<br>Total Defense Level (with armour) : " + totalDefense + "<br>Reduces Damage Taken by: "+  damageReduction.toFixed(2) + "%";
    warriorAttackAndDefenseStatsDiv.appendChild(attackHeader);
    warriorAttackAndDefenseStatsDiv.appendChild(damageStats);
    warriorAttackAndDefenseStatsDiv.appendChild(breakTag);
    warriorAttackAndDefenseStatsDiv.appendChild(defenseHeader);
    warriorAttackAndDefenseStatsDiv.appendChild(defenseStats);
}


function simulateFightPreparation() {

    fightSimulation(attackersDamageP, defendersDamageP);

}


function fightSimulation(AttackersDamagePattern, DefendersDamagePattern) {

    attackersTurnAction(AttackersDamagePattern);
    defendersTurnAction(DefendersDamagePattern);
    checkIfAWarriorIsDead();
    switchWhoHits();

}


function attackersTurnAction(AttackersDamagePattern) {

    if (attackersTurn) {

        let defendersHealth;

        console.log("attackers turn");

        defendersHealth = (defendersHP -  AttackersDamagePattern[indexOfDamagePatternA]).toFixed(2);
        defendersHPHolder.innerHTML = defendersHealth+"/" + defendersData.healthPointsGoal;
        defendersHPHolder.style.width = (((defendersHealth/defendersData.healthPointsGoal) * 100).toFixed(2)) + "%";
        defendersHP -= AttackersDamagePattern[indexOfDamagePatternA];

        if (AttackersDamagePattern[indexOfDamagePatternA] > 0) {

            document.getElementById("defendersHitDisplay").style.visibility = "hidden";
            document.getElementById("attackersHitDisplay").innerHTML = "YOU WERE HIT FOR " + AttackersDamagePattern[indexOfDamagePatternA].toFixed(2);
            document.getElementById("attackersHitDisplay").style.visibility = "visible";

        } else {

            document.getElementById("attackersHitDisplay").style.visibility = "hidden";
            document.getElementById("defendersHitDisplay").innerHTML = "YOU MISSED YOUR ATTACK";
            document.getElementById("defendersHitDisplay").style.visibility = "visible";

        }

        indexOfDamagePatternA++;
    }
}


function defendersTurnAction(DefendersDamagePattern) {

    if (defendersTurn) {

        let attackersHealth;

        console.log("defenders turn")

        attackersHealth = (attackersHP -  DefendersDamagePattern[indexOfDamagePatternD]).toFixed(2);
        attackersHPHolder.innerHTML = attackersHealth + "/" + attackersData.healthPointsGoal;
        attackersHPHolder.style.width = ((attackersHealth/attackersData.healthPointsGoal) * 100).toFixed(2) + "%";
        attackersHP -=  DefendersDamagePattern[indexOfDamagePatternD];

        if (DefendersDamagePattern[indexOfDamagePatternD] > 0) {

            document.getElementById("attackersHitDisplay").style.visibility = "hidden";
            document.getElementById("defendersHitDisplay").innerHTML = "YOU WERE HIT FOR " + DefendersDamagePattern[indexOfDamagePatternD].toFixed(2);
            document.getElementById("defendersHitDisplay").style.visibility = "visible";

        } else {

            document.getElementById("defendersHitDisplay").style.visibility = "hidden";
            document.getElementById("attackersHitDisplay").innerHTML = "YOU MISSED YOUR ATTACK";
            document.getElementById("attackersHitDisplay").style.visibility = "visible";


        }

        indexOfDamagePatternD++;
    }
}


function checkIfAWarriorIsDead() {

    if (defendersHP <= 0 || attackersHP <= 0) {

        console.log("SOMEONES HEALTH IS BELOW 0");

        if (defendersHP < attackersHP) {

            console.log("DEFENDERS HEALTH IS BELOW 0");
            defendersHPHolder.innerHTML = "0/" + defendersData.healthPointsGoal;
            defendersHPHolder.style.width = "0%";

        }

        if (defendersHP > attackersHP) {

            console.log("ATTACKERS HEALTH IS BELOW 0");
            attackersHPHolder.innerHTML = "0/" + attackersData.healthPointsGoal;
            attackersHPHolder.style.width = "0%";

        }

        clearInterval(myInterval);
        backToUserProfileDiv.style.display = "block";
    }
}


function switchWhoHits() {

    attackersTurn = !attackersTurn;
    defendersTurn = !defendersTurn;

}


function fightInitializer(aggressor, defender) {

    console.log("INITIALIZING FIGHT");
    console.log(defender);
    console.log("attackersHP");
    console.log(attackersHP);
    console.log("defendersHP");
    console.log(defendersHP);

    let data = {aggressorName: aggressor , defenderName: defender};
    fight(data);

}


function logout() {

    console.log("LOGGING OUT");

//    warriorData.isOnline = false;
//    saveUserDetails(warriorData);
    logoutWarrior(warriorData);
    sessionStorage.removeItem("warriorData");

    try {
        sessionStorage.removeItem("otherUser");
    } catch(err) {
        alert("NO OTHER USER DATA")
    }

//    location.href = './index.html';
}

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////REQUESTS///////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////


function getUserDetails(id, type) {

    console.log("USERS NAME");
    console.log(id);

    let url = "https://war-version-0.herokuapp.com/api/get/warrior/" + id;

    fetch(url, {
        method:'GET',
        headers:{
            'Content-Type': 'application/json'
        }
    })
        .then(res => res.json())
.catch(error => console.error('Error:', error))
.then((data) => {
        console.log(data);
    populateUserInfoDiv(data, type);

    if (type == "A") {

        multiplierForWarriorA = healthPointsBarSize/attackersHP;
        attackersData = data;
        console.log("Multiplier for A");
        console.log(multiplierForWarriorA);

    } else {

        multiplierForWarriorD = healthPointsBarSize/defendersHP;
        defendersData = data;
        console.log("Multiplier for D");
        console.log(multiplierForWarriorD);

    }
})
}


function fight(data) {

    console.log("INITIALIZING FIGHT");

    let url = 'https://war-version-0.herokuapp.com/battle/fight';

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
        sessionStorage.setItem("warriorData", JSON.stringify(data.warrior));
    console.log("DATA FROM FIGHT REQUEST");
    console.log(data.warrior);//aggressor
    console.log("Attackers Damage Pattern");
    console.log(data.AttackersDamagePattern);
    console.log("Defenders Damage Pattern");
    console.log(data.DefendersDamagePattern);
    attackersDamageP = data.AttackersDamagePattern;
    defendersDamageP = data.DefendersDamagePattern;
    myInterval = setInterval(simulateFightPreparation, 2000);
})
}


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


document.getElementById("engageBtn").addEventListener("click",() => {

    fightInitializer(attackersName, defendersName);
vsDiv.style.display = "block";
engageBtnDiv.style.display = "none";
backToUserProfileDiv.style.display = "none";

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