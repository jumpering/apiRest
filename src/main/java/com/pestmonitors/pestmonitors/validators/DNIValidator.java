package com.pestmonitors.pestmonitors.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DNIValidator implements ConstraintValidator<DNI, String> {

    final char[] LETTERS_DNI = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};

    @Override
    public boolean isValid(String DNI, ConstraintValidatorContext context) {

            int total = 0;
            for (int i = 0; i < DNI.length(); i++) {
                total += DNI.charAt(i);
            }
            int remainderNumber = total % 23;
            String hola;
            char lastChar = DNI.charAt(DNI.length() - 1);
            int characterComparator = Character.compare(lastChar, LETTERS_DNI[remainderNumber]);
            if (characterComparator == 0) {
                return true;
            }
        return false;
    }
}
