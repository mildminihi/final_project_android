package wanroj.supanat.pomodoro_knight.Controller;

/**
 * Created by mild supanat on 22/11/2560.
 */

public class Validate {

    public String validateTaskName(String name){
        if (name == null)return "Task Name is null";
        else if (name.length() >= 15)return "Task Name is too long";
        else if (name.isEmpty())return "Task Name is empty";
        else if(!name.matches("^[ A-Za-z]+$"))return "Task Name contain non Alphabet Characters";
        else return "Validate Task Name Complete";
    }

    public String validateWork(String input){
        if (input == null) return "Work Interval is null";
        else if (input.isEmpty())return "Work Interval is empty";
        else if (!input.matches("^[0123456789]+$"))return "Work Interval contain non Number character";
        else if (Integer.parseInt(input) > 30)return "Work Interval more than 30 Minute";
        else return "Validate Work Interval Complete";
    }

    public String validateTarget(String input){
        if (input == null)return "Target is null";
        else if (input.isEmpty())return "Target is empty";
        else if (!input.matches("^[0123456789]+$"))return "Target contain non Number character";
        else if (Integer.parseInt(input) > 30)return "Target more than 30 times";
        else return "Validate Target Complete";
    }
}
