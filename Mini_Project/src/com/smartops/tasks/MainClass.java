package com.smartops.tasks;

public class MainClass {
    public static void main(String[] args) {
        System.out.println("=== Smart Operations Manager ===");

        // Create tasks
        OperationTask payment = new PaymentTask("Order#1234 Payment", 2500.50);
        OperationTask email = new EmailTask("Welcome Email", "rahul@example.com", "Welcome to SmartOps 🚀");
        OperationTask report = new ReportTask("Daily Sales Report", "Sales");

        // Run tasks in sequence
        runTask(payment);
        runTask(email);
        runTask(report);

        System.out.println("\n=== All tasks completed ===");
    }

    private static void runTask(OperationTask task) {
        try {
            System.out.println("\n➡ Executing: " + task.getName());
            task.execute();
            System.out.println("✅ Task finished: " + task.getName());
        } catch (Exception e) {
            System.out.println("❌ Task failed: " + task.getName() + " | Reason: " + e.getMessage());
            task.rollback();
        }
    }
}


