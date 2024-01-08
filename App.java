import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static class Job {
        private String category;
        private String title;
        private String details;
        private double salary;
        private String location;

        public void setCategory(String category) {
            this.category = category;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        @Override
        public String toString() {
            return "Job{" +
                    "category='" + category + '\'' +
                    ", title='" + title + '\'' +
                    ", details='" + details + '\'' +
                    ", salary=" + salary +
                    ", location='" + location + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<Job> jobList = new ArrayList<>();

            // Read job details
            System.out.println("Enter job details (type 'exit' for the job category to stop):");
            while (true) {
                Job job = new Job();

                System.out.print("Job category: ");
                String category = scanner.nextLine();

                if (category.equalsIgnoreCase("exit")) {
                    break;
                }

                job.setCategory(category);

                System.out.print("Job title: ");
                job.setTitle(scanner.nextLine());

                System.out.print("Job details: ");
                job.setDetails(scanner.nextLine());

                System.out.print("Job salary: ");
                job.setSalary(Double.parseDouble(scanner.nextLine()));

                System.out.print("Job location: ");
                job.setLocation(scanner.nextLine());

                // Add job to the list
                jobList.add(job);
            }

            // Convert the job list to JSON
            Gson gson = new Gson();
            String json = gson.toJson(jobList);

            // Save the JSON to a file
            String filePath = "jobs.json";
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(json);
                System.out.println("JSON written to " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Print the JSON
            System.out.println("JSON: " + json);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
