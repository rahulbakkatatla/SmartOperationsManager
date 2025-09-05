package com.smartops.tasks;

public abstract class OperationTask {

    protected String taskName;

    public OperationTask(String taskName) {
        this.taskName = taskName;
    }

    // Methods every task must implement
    public abstract void execute() throws Exception;
    public abstract void rollback();
    public abstract String getName();
}
