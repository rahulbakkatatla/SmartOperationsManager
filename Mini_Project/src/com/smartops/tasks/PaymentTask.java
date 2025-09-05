package com.smartops.tasks;

import java.util.Random;

public class PaymentTask extends OperationTask {

    private double amount;
    private boolean executed = false;

    public PaymentTask(String taskName, double amount) {
        super(taskName);
        this.amount = amount;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("🔄 Starting Payment Task: " + taskName + " | Amount: $" + amount);

        // Simulated fraud check
        if (isFraudulent(amount)) {
            throw new Exception("🚨 Fraud detected! Payment blocked for task: " + taskName);
        }

        // Payment gateway
        boolean paymentSuccess = processPayment();

        if (!paymentSuccess) {
            throw new Exception("❌ Payment failed for task: " + taskName);
        }

        executed = true;
        System.out.println("✅ Payment of $" + amount + " completed successfully for: " + taskName);
    }

    @Override
    public void rollback() {
        if (!executed) {
            System.out.println("⚠️ Rollback not needed. Payment never executed for: " + taskName);
            return;
        }

        System.out.println("↩️ Rolling back Payment Task: " + taskName);
        refundPayment();
        executed = false;
        System.out.println("✅ Rollback complete for: " + taskName);
    }

    @Override
    public String getName() {
        return "PaymentTask - " + taskName + " | $" + amount;
    }

    // --- Custom Payment Logic ---

    private boolean processPayment() {
        System.out.println("💳 Contacting payment gateway...");

        // Random success/failure simulation
        Random rand = new Random();
        int result = rand.nextInt(10); // 0-9
        if (result < 7) { // 70% chance success
            return true;
        } else {
            return false;
        }
    }

    private boolean isFraudulent(double amount) {
        // Very naive fraud detection
        return amount > 10000; // Block any payment above 10k
    }

    private void refundPayment() {
        System.out.println("💰 Refund of $" + amount + " issued for: " + taskName);
    }
}

