package com.smartops.tasks;

public class ReportTask extends OperationTask {

    private String reportType;

    public ReportTask(String taskName, String reportType) {
        super(taskName);
        this.reportType = reportType;
    }

    @Override
    public void execute() throws Exception {
        System.out.println("📊 Generating report: " + taskName + " [" + reportType + "]");
        boolean generated = generateReport();
        if (!generated) {
            throw new Exception("Report generation failed: " + taskName);
        }
    }

    @Override
    public void rollback() {
        System.out.println("🗑 Deleting failed report: " + taskName);
    }

    @Override
    public String getName() {
        return taskName;
    }

    // --- Fake report logic ---
    private boolean generateReport() {
        System.out.println("Collecting data for report...");
        return !reportType.isBlank(); // fail if no type
    }
}
