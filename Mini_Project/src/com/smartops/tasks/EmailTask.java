package com.smartops.tasks;

public class EmailTask extends OperationTask {

    private String recipient;
    private String message;

    public EmailTask(String taskName, String recipient, String message) {
        super(taskName);
        this.recipient = recipient;
        this.message = message;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("📨 Sending email [" + taskName + "] to " + recipient);
        boolean success = sendEmail();
        if (!success) {
            throw new Exception("Failed to send email to " + recipient);
        }
    }

    @Override
    public void rollback() {
        System.out.println("🔁 Email rollback: notifying admin that email failed for " + recipient);
    }

    @Override
    public String getName() {
        return taskName;
    }

    // --- Fake email logic ---
    private boolean sendEmail() {
        System.out.println("Connecting to mail server...");
        return recipient.contains("@"); // fail if no '@'
    }
}

