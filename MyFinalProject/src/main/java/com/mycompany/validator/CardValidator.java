/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.validator;

import com.mycompany.pojo.DebitCard;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author mahit
 */
@Component
public class CardValidator implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void validate(Object obj, Errors errors) {
        DebitCard debitCard = (DebitCard)obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardName", "error.cardName", "cardName is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardNumber", "error.cardNumber", "cardNumber is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardExpiration", "error.cardExpiration", "cardExpiration is Empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardCVV", "error.cardCVV", "cardCVV is Empty");

        if (!debitCard.getCardName().equals("myCardName")) {
            errors.reject("error.cardName", "Card Name is invalid!");
        }
          if (!debitCard.getCardNumber().equals("myCardNumber")) {
            errors.reject("error.cardNumber", "Card Number is invalid!");
        }

           if (!debitCard.getCardExpiration().equals("myCardExpiration")) {
            errors.reject("error.cardExpiration", "Card Expiration is invalid!");
        }

             if (!debitCard.getCardCVV().equals("myCardCVV")) {
            errors.reject("error.cardCVV", "Card CVV is invalid!");
        }


       
    }

}
