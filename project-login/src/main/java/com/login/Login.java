package com.login;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
// import javafx.animation.ScaleTransition;

// import javafx.util.Duration;

public class Login extends Application {

    public void start(Stage primaryStage) throws Exception {

        // Create the main container
        Image backgroundImage = new Image(getClass().getResource("/Assets/Images/barber3.jpeg").toExternalForm());
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(1550);
        backgroundView.setFitHeight(870);
        backgroundView.opacityProperty().set(0.6);
        StackPane root = new StackPane();

        

        root.setPrefSize(1000, 700);
        VBox loginCard = createLoginCard();
        DropShadow shadow = new DropShadow();
        shadow.setRadius(20);
        shadow.setOffsetX(5);
        shadow.setOffsetY(10);
        shadow.setColor(Color.rgb(0, 0, 0, 0.9));
        loginCard.setEffect(shadow);

        root.getChildren().addAll(backgroundView, loginCard);

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Modern Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);

        primaryStage.show();
    }

    private VBox createLoginCard() {
        VBox card = new VBox(25);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(40));
        card.setMaxWidth(700);
        card.setMaxHeight(700);

        Image loginLogo = new Image(getClass().getResource("/Assets/Images/login_logo.jpeg").toExternalForm());
        ImageView logoView = new ImageView(loginLogo);
        logoView.setFitWidth(100);
        logoView.setFitHeight(100);
        logoView.setPreserveRatio(true);
        logoView.setEffect(new DropShadow());
        logoView.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.2);" +
                        "-fx-background-radius: 90;" +
                        "-fx-padding: 10;");
        javafx.scene.shape.Circle clip = new javafx.scene.shape.Circle(50, 50, 50);
        logoView.setClip(clip);

        // Create glass effect background
        card.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.15);" +
                        "-fx-background-radius: 20;" +
                        "-fx-border-radius: 20;" +
                        "-fx-border-color: rgba(255, 255, 255, 0.3);" +
                        "-fx-border-width: 1.5;"
                    );

        // Create title
        Text title = new Text("Welcome Back");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        title.setFill(Color.BLACK);

        Text subtitle = new Text("Please sign in to your account");
        subtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        subtitle.setFill(Color.BLACK);

        // Create input fields
        TextField usernameField = createStyledTextField("Username");
        usernameField.setMaxWidth(400);
        usernameField.setMinHeight(40);
        TextField emailField = createStyledTextField("Email");
        emailField.setMaxWidth(400);
        emailField.setMinHeight(40);
        PasswordField passwordField = createStyledPasswordField("Password");
        passwordField.setMaxWidth(400);
        passwordField.setMinHeight(40);


        // Create login button
        Button loginButton = createStyledButton("SIGN IN");
        Button exploreButton = createStyledButton("EXPLORE");
        HBox expLogButtonsBox = new HBox(15,loginButton,exploreButton);
        expLogButtonsBox.setAlignment(Pos.CENTER);

        // Create forgot password link
        Hyperlink forgotLink = new Hyperlink("Forgot Password?");
        forgotLink.setTextFill(Color.BLACK);
        forgotLink.setFont(Font.font("Arial", 12));
        forgotLink.setOnAction(e -> System.out.println("Forgot password clicked"));

        // Create sign up section
        HBox signUpSection = new HBox(5);
        signUpSection.setAlignment(Pos.CENTER);
        Text signUpText = new Text("Don't have an account? ");
        signUpText.setFill(Color.BLACK);
        signUpText.setFont(Font.font("Arial", 12));

        Hyperlink signUpLink = new Hyperlink("Sign Up");
        signUpLink.setTextFill(Color.BLACK);
        signUpLink.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        signUpLink.setOnAction(e -> System.out.println("Sign up clicked"));

        Hyperlink Explore = new Hyperlink("Explore");
        Explore.setTextFill(Color.BLACK);
        Explore.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        Explore.setOnAction(e -> System.out.println("Sign up clicked"));

        signUpSection.getChildren().addAll(signUpText, signUpLink);

        // Add all components to card
        card.getChildren().addAll(
                logoView,
                title,
                subtitle,
                // usernameField,
                emailField,
                passwordField,
                expLogButtonsBox,
                forgotLink,
                signUpSection);

        return card;
    }

    private TextField createStyledTextField(String placeholder) {
        TextField field = new TextField();
        field.setPromptText(placeholder);
        field.setPrefHeight(45);
        field.setPrefWidth(200);
        field.setPadding(new Insets(10));
        field.setStyle(
                        "-fx-background-color: rgba(255, 255, 255, 0.2);" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: rgba(0, 0, 0, 1);" +
                        "-fx-border-width: 1;" +
                        "-fx-text-fill: black;" +
                        "-fx-prompt-text-fill: rgba(0, 0, 0, 1);" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 0 15 0 15;");

        return field;
    }

    private PasswordField createStyledPasswordField(String placeholder) {
        PasswordField field = new PasswordField();
        field.setPromptText(placeholder);
        field.setPrefHeight(45);
        field.setPrefWidth(200);
        field.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.2);" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: rgba(0, 0, 0, 1);" +
                        "-fx-border-width: 1;" +
                        "-fx-text-fill: black;" +
                        "-fx-prompt-text-fill: rgba(0, 0, 0, 1);" +
                        "-fx-font-size: 14px;" +
                        "-fx-padding: 0 15 0 15;");

        // Add focus effects

        return field;
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setPrefHeight(40);
        button.setPrefWidth(150);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        button.setStyle(
                        "-fx-background-color: black;" +
                        "-fx-background-radius: 25;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-radius: 25;" +
                        "-fx-cursor: hand;");

        // Add hover effects
        button.setOnMouseEntered(e -> {
            button.setStyle(
                            "-fx-background-color: darkgrey;" +
                            "-fx-background-radius: 25;" +
                            "-fx-text-fill: white;" +
                            "-fx-border-radius: 25;" +
                            "-fx-cursor: hand;");

        });

        button.setOnMouseExited(e -> {
            button.setStyle(
                            "-fx-background-color: black;" +
                            "-fx-background-radius: 25;" +
                            "-fx-text-fill: white;" +
                            "-fx-border-radius: 25;" +
                            "-fx-cursor: hand;");

        });

        // Add login functionality
        button.setOnAction(e -> {
            System.out.println("Login button clicked!");
            
        });

        return button;
    }
}