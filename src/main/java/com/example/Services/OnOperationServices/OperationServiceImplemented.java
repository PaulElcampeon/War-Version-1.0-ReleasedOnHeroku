package com.example.Services.OnOperationServices;

import com.example.Models.OperationSection.OnOperation.OnOperation;
import com.example.Models.Warrior.Warrior;

public class OperationServiceImplemented {

        public void clearInActiveOperation(Warrior warrior){
            OnOperation.clearInActiveOperation(warrior);
        }
}


//NEED TO USE THIS IN END POINT CALL