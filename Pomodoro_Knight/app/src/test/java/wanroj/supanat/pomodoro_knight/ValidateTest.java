package wanroj.supanat.pomodoro_knight;

import org.junit.Test;

import wanroj.supanat.pomodoro_knight.Controller.Validate;

import static junit.framework.Assert.assertEquals;

/**
 * Created by mild supanat on 22/11/2560.
 */


public class ValidateTest {
    private Validate validate;



    @Test
    public void taskNameIsEmpty(){
        validate = new Validate();
        assertEquals(validate.validateTaskName(""), "Task Name is empty");
    }

    @Test
    public void taskNameIsNull(){
        validate = new Validate();
        assertEquals(validate.validateTaskName(null), "Task Name is null");
    }

    @Test
    public void taskNameIsNonAlpha(){
        validate = new Validate();
        assertEquals(validate.validateTaskName("2342"), "Task Name contain non Alphabet Characters");
    }
    @Test
    public void taskNameIsTooLong(){
        validate = new Validate();
        assertEquals(validate.validateTaskName("I am handsome boy in the world"), "Task Name is too long");
    }
    @Test
    public void taskNameIsStandard(){
        validate = new Validate();
        assertEquals(validate.validateTaskName("Do homework"), "Validate Task Name Complete");
    }

    @Test
    public void workIntervalIsEmpty(){
        validate = new Validate();
        assertEquals(validate.validateWork(""), "Work Interval is empty");
    }

    @Test
    public void workIntervalIsNull(){
        validate = new Validate();
        assertEquals(validate.validateWork(null), "Work Interval is null");
    }

    @Test
    public void workIntervalIsNonAlpha(){
        validate = new Validate();
        assertEquals(validate.validateWork("five minnute"), "Work Interval contain non Number character");
    }
    @Test
    public void workIntervalIsTooMuch(){
        validate = new Validate();
        assertEquals(validate.validateWork("400"), "Work Interval more than 30 Minute");
    }
    @Test
    public void workIntervalIsStandard(){
        validate = new Validate();
        assertEquals(validate.validateWork("20"), "Validate Work Interval Complete");
    }

    @Test
    public void targetIsEmpty(){
        validate = new Validate();
        assertEquals(validate.validateTarget(""), "Target is empty");
    }

    @Test
    public void targetIsNull(){
        validate = new Validate();
        assertEquals(validate.validateTarget(null), "Target is null");
    }

    @Test
    public void targetIsNonAlpha(){
        validate = new Validate();
        assertEquals(validate.validateTarget("five minnute"), "Target contain non Number character");
    }
    @Test
    public void targetIsTooMuch(){
        validate = new Validate();
        assertEquals(validate.validateTarget("400"), "Target more than 30 times");
    }
    @Test
    public void targetIsStandard(){
        validate = new Validate();
        assertEquals(validate.validateTarget("20"), "Validate Target Complete");
    }

}
