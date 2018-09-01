document.getElementById("userDetails").style.display = "none";

document.getElementById("createWeaponBtn").addEventListener("click",()=>{
    let weaponName = document.getElementById("weaponName").value;
    let weaponTopDamage = document.getElementById("weaponTopDamage").value;
    let weaponBottomDamage = document.getElementById("weaponBottomDamage").value;
    let weaponImageUrl = document.getElementById("weaponImageUrl").value;
    let url = 'https://war-version-0.herokuapp.com/api/add/weapon';
    let weaponData = {name:weaponName, topDamage:weaponTopDamage, bottomDamage:weaponBottomDamage, imageUrl: weaponImageUrl};
    let weaponDataAsString = JSON.stringify(weaponData);
    (weaponTopDamage=="" || weaponBottomDamage=="" || weaponName.length==0 || weaponImageUrl.length==0) ? alert("You left some fields empty") : createWeapon(url,weaponDataAsString);
})

function createWeapon(url, data){
    fetch(url, {
            method: 'POST', // or 'PUT'
            body: JSON.stringify(data), // data can be `string` or {object}!
            headers:{
                'Content-Type': 'application/json'
            }
        })
//            .then(res => res.json())
//            .catch(error => console.error('Error:', error))
//            .then((data) => {
//             console.log(data)
//
//            })
}

document.getElementById("createOperationBtn").addEventListener("click",()=>{
    let operationName = document.getElementById("operationName").value;
    let operationLevel = document.getElementById("operationLevel").value;
    let operationExperience = document.getElementById("operationExperience").value;
    let operationDuration = document.getElementById("operationDuration").value;
    let operationMoney = document.getElementById("operationMoney").value;
    let operationImageUrl = document.getElementById("OperationImageUrl").value;
    let operationPrizeType = document.getElementById("OperationPrizeType").value;
    let operationChanceOfSuccess = document.getElementById("OperationChanceOfSuccess").value;
    let url = 'https://intense-peak-18063.herokuapp.com/api/add/operation';
    let operationData = {name:operationName, level:operationLevel, experience:operationExperience, duration:operationDuration, imageUrl:operationImageUrl, money:operationMoney, chanceOfSuccess:operationChanceOfSuccess, prizeType:operationPrizeType};
    let operationDataAsString = operationData;
    console.log(operationDataAsString);
    (operationMoney=="" || operationDuration=="" || operationExperience=="" || operationLevel=="" || operationName.length==0 || operationImageUrl.length==0 || operationPrizeType.length==0 || operationChanceOfSuccess=="") ? alert("You left some fields empty") : createoperation(url,operationDataAsString);
})

function createOperation(url, data){
    fetch(url, {
        method: 'POST', // or 'PUT'
        body: JSON.stringify(data), // data can be `string` or {object}!
        headers:{
            'Content-Type': 'application/json'
        }
    })
//            .then(res => res.json())
//            .catch(error => console.error('Error:', error))
//            .then((data) => {
//             console.log(data)
//
//            })
}

document.getElementById("getUserBtn").addEventListener("click",()=>{

})

document.getElementById("getUserBtn").addEventListener("click",()=>{

})