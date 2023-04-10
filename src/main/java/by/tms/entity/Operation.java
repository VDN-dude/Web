package by.tms.entity;

import java.time.LocalDateTime;

public class Operation implements Comparable<Operation> {
    private int operationId;
    protected double num1;
    protected double num2;
    private double result;
    private OperationType type;
    private int userId;
    private LocalDateTime time;

    public Operation(int operationId, double num1, double num2, OperationType type, double result, int userId, LocalDateTime time) {
        this.operationId = operationId;
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
        this.type = type;
        this.userId = userId;
        this.time = time;
    }

    public Operation(int operationId, double num1, double num2, OperationType type, double result, LocalDateTime time) {
        this.operationId = operationId;
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.result = result;
        this.time = time;
    }

    public Operation(double num1, double num2, int userId) {
        this.num1 = num1;
        this.num2 = num2;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Operation setResult(double result) {
        this.result = result;
        return this;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "operationId=" + operationId +
                ", num1=" + num1 +
                ", num2=" + num2 +
                ", result=" + result +
                ", type=" + type +
                ", userId=" + userId +
                ", time=" + time +
                '}';
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
