package com.taskmanagement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class User extends Application {
    private List<Task> tasks = new ArrayList<>();
    private ListView<Task> taskListView = new ListView<>();
    private TextField titleInput = new TextField();
    private TextArea descriptionInput = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Task Management System");

        BorderPane layout = new BorderPane();

        VBox addTaskForm = new VBox(10);
        addTaskForm.setPadding(new Insets(10));
        addTaskForm.setMaxWidth(300);

        Label titleLabel = new Label("Title:");
        Label descriptionLabel = new Label("Description:");

        Button addButton = new Button("Add Task");
        addButton.setOnAction(e -> addTask(titleInput.getText(), descriptionInput.getText()));

        addTaskForm.getChildren().addAll(titleLabel, titleInput, descriptionLabel, descriptionInput, addButton);

        taskListView.setMinWidth(300);
        taskListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem markCompleteMenuItem = new MenuItem("Mark as Complete");
        markCompleteMenuItem.setOnAction(e -> markTaskComplete(taskListView.getSelectionModel().getSelectedItem()));
        MenuItem deleteMenuItem = new MenuItem("Delete Task");
        deleteMenuItem.setOnAction(e -> deleteTask(taskListView.getSelectionModel().getSelectedItem()));
        contextMenu.getItems().addAll(markCompleteMenuItem, deleteMenuItem);

        taskListView.setContextMenu(contextMenu);

        layout.setLeft(addTaskForm);
        layout.setCenter(taskListView);

        HBox buttons = new HBox(10);
        buttons.setPadding(new Insets(10));
        Button modifyButton = new Button("Modify Task");
        modifyButton.setOnAction(e -> modifyTask(taskListView.getSelectionModel().getSelectedItem()));
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(e -> primaryStage.close());
        buttons.getChildren().addAll(modifyButton, exitButton);

        layout.setBottom(buttons);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void addTask(String title, String description) {
        Task newTask = new Task(title, description);
        tasks.add(newTask);
        taskListView.getItems().add(newTask);
        clearInputFields();
    }

    private void markTaskComplete(Task task) {
        if (task != null) {
            task.markComplete();
            taskListView.refresh();
        }
    }

    private void deleteTask(Task task) {
        if (task != null) {
            tasks.remove(task);
            taskListView.getItems().remove(task);
            clearInputFields();
        }
    }

    private void modifyTask(Task task) {
        if (task != null) {
            task.setTitle(titleInput.getText());
            task.setDescription(descriptionInput.getText());
            taskListView.refresh();
            clearInputFields();
        }
    }

    private void clearInputFields() {
        titleInput.clear();
        descriptionInput.clear();
    }

    public class Task {
        private String title;
        private String description;
        private boolean isComplete;

        public Task(String title, String description) {
            this.title = title;
            this.description = description;
            this.isComplete = false;
        }

        public void markComplete() {
            this.isComplete = true;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isComplete() {
            return isComplete;
        }

        @Override
        public String toString() {
            return title + " (" + (isComplete ? "Complete" : "Incomplete") + ")";
        }
    }
}
