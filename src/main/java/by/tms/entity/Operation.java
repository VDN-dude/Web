package by.tms.entity;

import java.time.LocalDateTime;

public class Operation implements Comparable<Operation> {
    private int id;
    private final double num1;
    private final double num2;
    private final OperationType type;
    private double result;
    private LocalDateTime time;
    private String username;

    public Operation(double num1, double num2, OperationType type, String username) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.username = username;

    }

    public Operation(int id, double num1, OperationType type, double num2, double result, LocalDateTime time) {
        this.id = id;
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
        this.type = type;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public double getNum1() {
        return num1;
    }

    public double getNum2() {
        return num2;
    }

    public double getResult() {
        return result;
    }

    public OperationType getType() {
        return type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Operation setResult(double result) {
        this.result = result;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public int compareTo(Operation o) {
        if (this.getTime().isBefore(o.getTime())) {
            return 1;
        } else if (this.getTime().isAfter(o.getTime())) {
            return -1;
        }
        return 0;
    }
}
